package com.abc.lib.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.abc.lib.constants.Constant;
import com.abc.lib.constants.ErrorConstant;
import com.abc.lib.entity.Book;
import com.abc.lib.exceptions.BookNotFoundException;
import com.abc.lib.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public ResponseEntity<String> addbook(Book book) {

		bookRepository.save(book);
 
		return new ResponseEntity<String>(Constant.BOOK_ADDEED_SUCCESS_MESSAGE + book.getBookId(), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<String> addbooks(List<Book> books) {

		bookRepository.saveAll(books);
		return new ResponseEntity<String>(Constant.ALL_BOOKS_ADDED_SUCCESS_MESSAGE, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Book> fetchBook(String bookId) {
		Optional<Book> optionalBook = bookRepository.findById(bookId);

		if (optionalBook.isPresent()) {

			Book book = optionalBook.get();

			return new ResponseEntity<Book>(book, HttpStatus.OK);
		}

		throw new BookNotFoundException(ErrorConstant.BOOK_NOT_FOUND_EXCEPTION_MESSAGE + bookId);
	} 

	@Override
	public ResponseEntity<List<Book>> fetchBooks() {

		List<Book> books = bookRepository.findAll();

		if (!books.isEmpty()) {

			return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
		}
		throw new BookNotFoundException(ErrorConstant.NO_BOOKS_IN_RECORD_EXCEPTION_MESSAGE);
	}

	@Override
	public ResponseEntity<List<Book>> findByBookTitleContaining(String bookTitle) {
		List<Book> books = bookRepository.findByBookTitleContaining(bookTitle);
		if (!books.isEmpty())
			return new ResponseEntity<List<Book>>(books, HttpStatus.OK);

		throw new BookNotFoundException(ErrorConstant.BOOK_NOT_FOUND_FOR_GIVEN_TITLE_MESSAGE + bookTitle);
	}

	@Override
	public ResponseEntity<String> removeBook(String bookId) {
		Optional<Book> optionalBook = bookRepository.findById(bookId);
		if (optionalBook.isPresent()) {
			bookRepository.deleteById(bookId);

			return new ResponseEntity<String>(Constant.BOOK_DELETED_SUCCESS_MESSAGE + bookId, HttpStatus.OK);
		}
		throw new BookNotFoundException(ErrorConstant.BOOK_NOT_FOUND_EXCEPTION_MESSAGE + bookId);
	}

	/*
	 * @Override public ResponseEntity<String> editBookDetailsById(String bookId) {
	 * Optional<Book> optionalBook= bookRepository.findById(bookId);
	 * if(optionalBook.isPresent()) { bookRepository.editBookDetailsById(bookId);
	 * 
	 * return new ResponseEntity<String>(Constant.BOOK_EDIT_SUCCESS_MESSAGE +
	 * bookId,HttpStatus.OK); } throw new
	 * BookNotFoundException(ErrorConstant.BOOK_NOT_FOUND_EXCEPTION_MESSAGE +
	 * bookId); }
	 */

	@Override
	public ResponseEntity<Book> updateBook(Book book) {
		Optional<Book> optionalBook = bookRepository.findById(book.getBookId());
		if (optionalBook.isPresent()) {
			bookRepository.save(book);
			return new ResponseEntity<Book>(book, HttpStatus.OK);
		}
		throw new BookNotFoundException(ErrorConstant.BOOK_NOT_FOUND_EXCEPTION_MESSAGE + book.getBookId());

	}
}