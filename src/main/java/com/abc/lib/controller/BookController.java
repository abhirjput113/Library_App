package com.abc.lib.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.lib.entity.Book;
import com.abc.lib.service.BookServiceImpl;

@RestController
@RequestMapping("/v1/book")
public class BookController {

	private static final Logger log = LoggerFactory.getLogger(BookController.class);

	@Autowired
	private BookServiceImpl bookServiceImpl;

	@PostMapping("/add")
	public ResponseEntity<String> addBook(@RequestBody Book book) {

		return bookServiceImpl.addbook(book);

	}

	@PostMapping("/add/multiple")
	public ResponseEntity<String> savebooks(@RequestBody List<Book> books) {
 
		return bookServiceImpl.addbooks(books);

	}

	@GetMapping("/fetch/{bookId}")
	public ResponseEntity<Book> fetchBook(@PathVariable String bookId) {

		log.info("########################" + "Book Searched: " + bookId + "########################");
		return bookServiceImpl.fetchBook(bookId);
	}

	@GetMapping("/fetch/all")
	public ResponseEntity<List<Book>> fetchBooks() {
		return bookServiceImpl.fetchBooks();
	}

	@GetMapping("/fetch/title/{bookTitle}")
	public ResponseEntity<List<Book>> findByBookTitleContaining(@PathVariable String bookTitle) {
		return bookServiceImpl.findByBookTitleContaining(bookTitle);

	}

	@DeleteMapping("/remove/{bookId}")
	public ResponseEntity<String> removeBook(@PathVariable String bookId) {
		return bookServiceImpl.removeBook(bookId);
	}

	/*
	 * @PutMapping("/edit/book/{bookId}") public ResponseEntity<String>
	 * editBookDetailsByBookId(@PathVariable String bookId) { return
	 * bookServiceImpl.editBookDetailsById(bookId); }
	 */
	@PutMapping("/update")
	public ResponseEntity<Book> updateBookIdByContainin(@RequestBody Book book) {
		return bookServiceImpl.updateBook(book);
	}

}
