package com.exercise.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.exercise.bookstore.domain.Book;
import com.exercise.bookstore.domain.BookRepository;
import com.exercise.bookstore.domain.Category;
import com.exercise.bookstore.domain.CategoryRepository;
import com.exercise.bookstore.domain.User;
import com.exercise.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookstoreDemo(BookRepository repository, CategoryRepository drepository,
			UserRepository urpository) {
		return (args) -> {
			// Your code...add some demo data to db
			log.info("save a couple of books");

			Category category1 = new Category("type1");
			Category category2 = new Category("type2");
			Category category3 = new Category("type3");

			drepository.save(category1);
			drepository.save(category2);
			drepository.save(category3);

			repository
					.save(new Book("The Catcher in the Rye", "J.D. Salinger", 1951, "9780316769488", 8.99, category1));
			repository.save(new Book("To Kill a Mockingbird", "Harper Lee", 1960, "9780060935467", 7.19, category2));
			repository.save(new Book("1984", "George Orwell", 1949, "9780451524935", 9.99, category1));
			repository.save(new Book("Moby Dick", "Herman Melville", 1851, "9781503280786", 11.49, category3));
			repository.save(new Book("Pride and Prejudice", "Jane Austen", 1813, "9781503290563", 6.99, category2));

			User user1 = new User("user", "$2y$10$mbVzVg7IzFqI5025oH05z.sshSXIbJdajGJxdlGtVzzaNVRZly5Bi", "USER",
					"a@jd");
			User user2 = new User("admin", "$2y$10$swotAl9bzpLK4RuU2q1kIeEsXvH2zVjB09eZcnkNWw6fpb/Ik1BwW", "ADMIN",
					"a@jd");
			urpository.save(user1);
			urpository.save(user2);

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};
	}

}
