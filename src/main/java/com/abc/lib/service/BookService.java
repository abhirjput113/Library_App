package com.abc.lib.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.abc.lib.entity.Book;

public interface BookService {

	ResponseEntity<String> addbook(Book book);

	ResponseEntity<String> addbooks(List<Book> books);

	ResponseEntity<Book> fetchBook(String bookId);
	
	ResponseEntity<List<Book>> fetchBooks();
	
	ResponseEntity<List<Book>> findByBookTitleContaining(String bookTitle);
	
	ResponseEntity<String> removeBook(String bookId);
	
	/*ResponseEntity<String> editBookDetailsById(String bookId);*/
	
	ResponseEntity<Book> updateBook(Book book);
}
