package com.bookstore.book.controllers;

import com.bookstore.book.dto.BookDto;
import com.bookstore.book.dto.CreateBookDto;
import com.bookstore.book.dto.CreateReviewDto;
import com.bookstore.book.entities.Book;
import com.bookstore.book.services.BookService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/create", method = RequestMethod.GET)
    public String showCreateBook(Model model) {
        model.addAttribute("bookForm", new CreateBookDto());
        model.addAttribute("genres", service.getAllGenre());
        model.addAttribute("edit", false);
        return "book";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/create", method = RequestMethod.POST )
    public ModelAndView createBook(CreateBookDto createBookDto){
        ModelAndView modelAndView = new ModelAndView();
        try {
            createBookDto.setImageString(Base64.getEncoder().encodeToString(createBookDto.getImage().getBytes()));
                service.saveBook(createBookDto);
                modelAndView.setViewName("redirect:../books");
        }
        catch(Exception ex){
            modelAndView.addObject("error", true);
            modelAndView.addObject("bookForm", createBookDto);
            modelAndView.addObject("genres", service.getAllGenre());
            modelAndView.addObject("edit", false);
            modelAndView.setViewName("book");
        }
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/removeBook", method = RequestMethod.POST)
    public ModelAndView removeBook(@RequestParam("id") int id){
        ModelAndView model = new ModelAndView();
        service.removeBook(id);
        model.setViewName("redirect:../books");
        return model;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editBook(@PathVariable("id")int id){
        ModelAndView model = new ModelAndView();
        model.addObject("edit",true);
        model.addObject("bookForm", service.findBookById(id));
        model.addObject("genres", service.getAllGenre());
        model.addObject("id", id);
        model.setViewName("book");
        return model;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/editBook/{id}", method = RequestMethod.POST)
    public ModelAndView editBook(@PathVariable("id")int id, BookDto bookDto){
        ModelAndView model = new ModelAndView();
       try{
            bookDto.setId(id);
            service.updateBook(bookDto);
            model.setViewName("redirect:../../books");
        }
       catch(Exception ex){
           ex.printStackTrace();
           model.addObject("edit",true);
           model.addObject("genres", service.getAllGenre());
           model.addObject("id", id);
           model.addObject("bookForm", bookDto);
           model.addObject("error",true);
           model.setViewName("book");
       }
        return model;
    }

    @RequestMapping(value="/{title}", method = RequestMethod.GET)
    public ModelAndView bookDetail(@PathVariable("title") String title, @RequestParam(value = "id") int id){
        ModelAndView model = new ModelAndView("bookDetail");
        model.addObject("book", service.findBookById(id));
        model.addObject("reviews", service.getAllReviews(id));
        return model;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @RequestMapping(value="/review", method = RequestMethod.POST)
    public ModelAndView makeReview(CreateReviewDto createReviewDto){
        ModelAndView modelAndView = new ModelAndView();
        Book book = service.createReview(createReviewDto);
        modelAndView.setViewName("redirect:"+book.getTitle()+"?id="+book.getId());
        return modelAndView;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value="/review/{id}", method = RequestMethod.POST)
    public ModelAndView deleteReview(@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView();
        Book book = service.deleteReview(id);
        modelAndView.setViewName("redirect:../"+book.getTitle()+"?id="+book.getId());
        return modelAndView;
    }

    @GetMapping("csv-books")
    public void GetCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
        List<Book>books = service.write_books();
        String[] csvHeader = {"Book Id", "Title", "Summary"};
        String[] nameMapping = {"id", "title", "summary"};

        csvWriter.writeHeader(csvHeader);

        for (Book b : books) {
            csvWriter.write(b, nameMapping);
        }

        csvWriter.close();
    }
}
