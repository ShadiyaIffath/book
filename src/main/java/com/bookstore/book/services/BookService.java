package com.bookstore.book.services;

import com.bookstore.book.dto.BookDto;
import com.bookstore.book.dto.CreateBookDto;
import com.bookstore.book.dto.GenreDto;
import com.bookstore.book.entities.Book;
import com.bookstore.book.entities.Genre;
import com.bookstore.book.repositories.BookRepository;
import com.bookstore.book.repositories.GenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<GenreDto> getAllGenre() {
        return genreRepository.findAll()
                .stream().map(x -> modelMapper.map(x, GenreDto.class))
                .collect(Collectors.toList());
    }

    public boolean isBookAlreadyAvailable(String ISBN) {
        boolean valid = false;
        if (bookRepository.findByISBN(ISBN) != null)
            valid = true;
        return valid;
    }

    public void saveBook(CreateBookDto createBookDto) {
        Book book = modelMapper.map(createBookDto, Book.class);
        try {
            book.setImage(createBookDto.getImage().getBytes());
            System.out.println(createBookDto.getGenreId());
            Genre genre = genreRepository.findById(createBookDto.getGenreId()).orElse(null);
            book.setGenre(genre);
        } catch (Exception ex) {
            System.out.println("Exception caught");
        }
        bookRepository.save(book);
    }

    public List<BookDto> getAllBooks() {
        List<BookDto> books = bookRepository.findAll()
                .stream().map(x -> {
                    BookDto bookDto = modelMapper.map(x, BookDto.class);
                    bookDto.setImageString(Base64.getEncoder().encodeToString(x.getImage()));
                    return  bookDto;
                })
                .collect(Collectors.toList());
//        System.out.println(books.get(0).getImage());
        return books;
    }
}
