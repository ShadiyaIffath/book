package com.bookstore.book.services;

import com.bookstore.book.dto.GenreDto;
import com.bookstore.book.repositories.BookRepository;
import com.bookstore.book.repositories.GenreRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<GenreDto> getAllGenre(){
        List<GenreDto> genreDtos = genreRepository.findAll()
                .stream().map( x -> modelMapper.map(x, GenreDto.class))
                .collect(Collectors.toList());
        return genreDtos;
    }

    public boolean isBookAlreadyAvailable(String ISBN){
        boolean valid = false;
        if(bookRepository.findByISBN(ISBN) != null)
            valid = true;
        return valid;
    }
}
