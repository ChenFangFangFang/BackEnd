package com.exercise.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import com.exercise.bookstore.domain.Book;
import com.exercise.bookstore.domain.BookRepository;
import com.exercise.bookstore.domain.Category;
import com.exercise.bookstore.domain.CategoryRepository;

@SpringBootTest(classes = BookstoreApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // if you are using real db

public class BookRepositoryTests {
    @Autowired
    private BookRepository repository;
    @Autowired
    private CategoryRepository drepository;

    @Test
    public void findByLastnameShouldReturnStudent() {
        List<Book> books = repository.findByTitle("The Catcher in the Rye");

        assertThat(books).hasSize(1);
        assertThat(books.get(0).getTitle()).isEqualTo("The Catcher in the Rye");
    }

    @Test
    public void createNewBook() {
        Category category = new Category("type1");
        drepository.save(category);
        Book book = new Book("testBook", "J.D. Salinger", 1951, "9780316769488", 8.99, category);
        repository.save(book);
        assertThat(book.getId()).isNotNull();
    }

    @Test
    public void deleteNewBook() {
        List<Book> books = repository.findByTitle("The Catcher in the Rye");
        Book book = books.get(0);
        repository.delete(book);
        List<Book> newBooks = repository.findByTitle("The Catcher in the Rye");
        assertThat(newBooks).hasSize(0);
    }
}
