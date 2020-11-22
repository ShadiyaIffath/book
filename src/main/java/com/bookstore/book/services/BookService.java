package com.bookstore.book.services;

import com.bookstore.book.dto.*;
import com.bookstore.book.entities.Book;
import com.bookstore.book.entities.Genre;
import com.bookstore.book.entities.Review;
import com.bookstore.book.entities.Account;
import com.bookstore.book.repositories.BookRepository;
import com.bookstore.book.repositories.GenreRepository;
import com.bookstore.book.repositories.ReviewRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
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

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private AccountService accountService;

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
            } else if (book.getId() != id) {
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

    public void newBook(CreateBookDto createBookDto){
        Book book = modelMapper.map(createBookDto, Book.class);
        book.setGenre(modelMapper.map(createBookDto.getGenreDto(), Genre.class));
        bookRepository.save(book);
    }

    public List<BookDto> getAllBooks() {
        List<BookDto> books = bookRepository.findAll()
                .stream().map(x -> {
                    BookDto bookDto = modelMapper.map(x, BookDto.class);
                    if(x.getImage()!= null) {
                        bookDto.setImageString(Base64.getEncoder().encodeToString(x.getImage()));
                    }
                    bookDto.setGenreDto(modelMapper.map(x, GenreDto.class));
                    return bookDto;
                })
                .collect(Collectors.toList());
        bookDtos = books;
        return books;
    }

    public List<BookDto> searchFilter(String search) {
        List<BookDto> books = new ArrayList<>();
        for (BookDto b : bookDtos) {
            if (search.equalsIgnoreCase(b.getAuthor()) || search.equalsIgnoreCase(b.getTitle()) || search.equalsIgnoreCase(b.getISBN())) {
                books.add(b);
            }
        }
        return books;
    }

    public List<BookDto> genreFilter(String genre) {
        List<BookDto> books = new ArrayList<>();
        for (BookDto b : bookDtos) {
            if (genre.equalsIgnoreCase(b.getGenreDto().getGenre())) {
                books.add(b);
            }
        }
        return books;
    }

    public void removeBook(int id) {
        bookRepository.deleteById(id);
    }

    public BookDto findBookById(int id) {
        Book book = bookRepository.findById(id);
        BookDto dto = modelMapper.map(book, BookDto.class);
        dto.setImageString(Base64.getEncoder().encodeToString(dto.getImage()));
        dto.setGenreDto(modelMapper.map(book.getGenre(), GenreDto.class));
        dto.setGenreId(book.getGenre().getId());
        return dto;
    }

    public void updateBook(BookDto dto) {
        Book book = bookRepository.findById(dto.getId());
        if(dto.getGenreDto() != null){
            book.setGenre(modelMapper.map(dto.getGenreDto(),Genre.class));
        }else {
            Genre genre = genreRepository.findById(dto.getGenreId());
            book.setGenre(genre);
        }
        book.setImageUrl(dto.getImageUrl());
        book.setPublisher(dto.getPublisher());
        book.setAuthor(dto.getAuthor());
        book.setISBN(dto.getISBN());
        book.setTitle(dto.getTitle());
        book.setSummary(dto.getSummary());
        book.setQuantity(dto.getQuantity());
        bookRepository.save(book);
    }

    public Book createReview(CreateReviewDto dto) {
        Review review = modelMapper.map(dto, Review.class);
        Book book = bookRepository.findById(dto.getBookId());
        review.setBook(book);
        review.setAccount(accountService.findLoggedInAccount());
        reviewRepository.save(review);
        return book;
    }

    public List<ReviewDto> getAllReviews(int id) {
        return reviewRepository.findAllByBook_Id(id).stream()
                .map(x -> {
                    ReviewDto r = modelMapper.map(x, ReviewDto.class);
                    r.setAccountDto(modelMapper.map(x.getAccount(),AccountDto.class));
                    return r;
                })
                .collect(Collectors.toList());
    }

    public List<ReviewDtoForAndroid> getAllReviewsForAndroid(int id) {
        return reviewRepository.findAllByBook_Id(id).stream()
                .map(x -> {
                    ReviewDtoForAndroid r = modelMapper.map(x, ReviewDtoForAndroid.class);
                    r.setAccountDto(modelMapper.map(x.getAccount(),AccountDto.class));
                    return r;
                })
                .collect(Collectors.toList());
    }

    public Book deleteReview(int id){
        Review review = reviewRepository.findById(id).get();
        reviewRepository.delete(review);
        return review.getBook();
    }

    public List<BookDtoForAndroid> getNewestBooks(){
        return bookRepository.findTop4ByOrderByIdDesc()
                .stream().map(x -> {
                    BookDtoForAndroid bookDto = modelMapper.map(x, BookDtoForAndroid.class);
                    bookDto.setGenreDto(modelMapper.map(x, GenreDto.class));
                    return bookDto;
                })
                .collect(Collectors.toList());
    }

    public List<BookDtoForAndroid> getAllBooksForAndroid(){
        return bookRepository.findAll()
                .stream().map(x -> {
                    BookDtoForAndroid bookDto = modelMapper.map(x, BookDtoForAndroid.class);
                    bookDto.setGenreDto(modelMapper.map(x, GenreDto.class));
                    return bookDto;
                })
                .collect(Collectors.toList());
    }

    public ReviewDtoForAndroid saveReview(CreateReviewDto dto){
        Review review = new Review();
        review.setDateCreated(dto.getDateCreated());
        review.setRating(dto.getRating());
        review.setReview(dto.getReview());
        Account account = accountService.findAccountById(dto.getAccountId());
        review.setAccount(account);
        Book book = bookRepository.findById(dto.getBookId());
        review.setBook(book);
        review= reviewRepository.save(review);
        ReviewDtoForAndroid reviewDto = modelMapper.map(review, ReviewDtoForAndroid.class);
        reviewDto.setAccountDto(modelMapper.map(account, AccountDto.class));
        return reviewDto;
    }
}
