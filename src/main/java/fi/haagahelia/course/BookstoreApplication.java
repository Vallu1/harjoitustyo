package fi.haagahelia.course;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.course.domain.Book;
import fi.haagahelia.course.domain.BookRepository;
import fi.haagahelia.course.domain.Category;
import fi.haagahelia.course.domain.CategoryRepository;


@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
		@Bean
		public CommandLineRunner bookDemo(BookRepository srepository, CategoryRepository drepository) {
			return (args) -> {
				log.info("save a couple of books");
				
				drepository.save(new Category("IT"));
				drepository.save(new Category("Business"));
				drepository.save(new Category("Law"));
				
				
				srepository.save(new Book("A Farewell to Arms", "Ernest Hemingway", "1929", "1232323-21", 12.12, drepository.findByName("IT").get(0)));
				srepository.save(new Book("Animal Farm", "Yrjo Orwell", "1945", "2212343-5", 99.99, drepository.findByName("Business").get(0)));				
				log.info("fetch all books");
				for (Book book : srepository.findAll()) {
					log.info(book.toString());
				}
			};
		}
}
