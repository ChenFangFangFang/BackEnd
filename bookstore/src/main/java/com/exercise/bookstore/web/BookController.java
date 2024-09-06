package com.exercise.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exercise.bookstore.domain.BookRepository;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {
    @Autowired
    private BookRepository repository;

    @RequestMapping(value = "/index")
    public String bookList(Model model) {
        model.addAttribute("books", repository.findAll());

        return "books";
    }

    @RequestMapping(value = "/booklist", method = RequestMethod.GET)
    public String requestMethodName(Model model) {
        model.addAttribute("books", repository.findAll());

        return "booklist";
    }

}
