package com.bookstore.book.controllers;

import com.bookstore.book.dto.BookDto;
import com.bookstore.book.dto.CreateBookDto;
import com.bookstore.book.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Base64;

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
}
