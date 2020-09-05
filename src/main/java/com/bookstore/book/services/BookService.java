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
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;

    private List<GenreDto> genreDtos = new ArrayList<>();
    private List<BookDto> bookDtos = new ArrayList<>();

    public List<GenreDto> getAllGenre() {
        if (genreDtos.size() == 0) {
            genreDtos = genreRepository.findAll()
                    .stream().map(x -> modelMapper.map(x, GenreDto.class))
                    .collect(Collectors.toList());
        }
        return genreDtos;
    }

    public boolean isBookAlreadyAvailable(String ISBN, int id) {
        boolean valid = false;
        Book book = bookRepository.findByISBN(ISBN);
        if (book != null) {
            if (id == -1) {
                valid = true;
            } else if(book.getId() != id){
                valid = true;
            }
        }
        return valid;
    }

    public void saveBook(CreateBookDto createBookDto) {
        Book book = modelMapper.map(createBookDto, Book.class);
        try {
            book.setImage(createBookDto.getImage().getBytes());
            Genre genre = genreRepository.findById(createBookDto.getGenreId());
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
                    bookDto.setGenreDto(modelMapper.map(x, GenreDto.class));
                    return bookDto;
                })
                .collect(Collectors.toList());
        return books;
    }

    public void removeBook(int id) {
        bookRepository.deleteById(id);
    }

    private GenreDto findGenreDto(int id) {
        GenreDto genreDto = null;
        for (GenreDto g : genreDtos) {
            if (g.getId() == id) {
                genreDto = g;
            }
        }
        return genreDto;
    }

    public BookDto findBookById(int id) {
        Book book = bookRepository.findById(id);
        BookDto dto = modelMapper.map(book, BookDto.class);
        dto.setImageString(Base64.getEncoder().encodeToString(dto.getImage()));
        dto.setGenreDto(findGenreDto(book.getGenre().getId()));
        dto.setGenreId(book.getGenre().getId());
        return dto;
    }

    public void updateBook(BookDto dto) {
        Book book = bookRepository.findById(dto.getId());
        Genre genre = genreRepository.findById(dto.getGenreId());
        book.setGenre(genre);
        book.setAuthor(dto.getAuthor());
        book.setISBN(dto.getISBN());
        book.setTitle(dto.getTitle());
        book.setSummary(dto.getSummary());
        book.setValue(dto.getValue());
        bookRepository.save(book);
    }
}
