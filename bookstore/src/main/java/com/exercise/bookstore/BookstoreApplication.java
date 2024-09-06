package com.exercise.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.exercise.bookstore.domain.Book;
import com.exercise.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookstoreDemo(BookRepository repository) {
		return (args) -> {
			// Your code...add some demo data to db
			log.info("save a couple of books");
			repository.save(new Book("The Catcher in the Rye", "J.D. Salinger", 1951, "9780316769488", 8.99));
			repository.save(new Book("To Kill a Mockingbird", "Harper Lee", 1960, "9780060935467", 7.19));
			repository.save(new Book("1984", "George Orwell", 1949, "9780451524935", 9.99));
			repository.save(new Book("Moby Dick", "Herman Melville", 1851, "9781503280786", 11.49));
			repository.save(new Book("Pride and Prejudice", "Jane Austen", 1813, "9781503290563", 6.99));

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};
	}

}
