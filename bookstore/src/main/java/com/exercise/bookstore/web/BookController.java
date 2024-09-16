package com.exercise.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exercise.bookstore.domain.Book;
import com.exercise.bookstore.domain.BookRepository;
import com.exercise.bookstore.domain.CategoryRepository;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {
    @Autowired
    private BookRepository repository;

    @Autowired
    private CategoryRepository drepository;

    @RequestMapping(value = "/index")
    public String bookList(Model model) {
        model.addAttribute("books", repository.findAll());

        return "books";
    }

    @RequestMapping(value = "/booklist", method = RequestMethod.GET)
    public String studentList(Model model) {
        model.addAttribute("books", repository.findAll());

        return "booklist";
    }

    @RequestMapping(value = "/add")
    public String addStudent(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", drepository.findAll());
        return "addbook";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveBook(Book book) {
        System.out.println("We are trying to save new book");
        repository.save(book);
        return "redirect:booklist";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        repository.deleteById(bookId);
        return "redirect:../booklist";
    }

    @RequestMapping(value = "/modify/{id}", method = RequestMethod.GET)
    public String updateBook(@PathVariable("id") Long bookId, Model model) {
        Book book = repository.findById(bookId).orElse(null);
        model.addAttribute("book", book);
        model.addAttribute("categories", drepository.findAll());
        return "editbook";

    }

}
