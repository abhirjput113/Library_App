package com.abc.lib.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.abc.lib.constants.Constant;
import com.abc.lib.entity.Book;
import com.abc.lib.exceptions.BookNotFoundException;
import com.abc.lib.repository.BookRepository;
import com.abc.lib.service.BookServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
public class BookServiceImplTest {

	@Mock
	static BookRepository bookRepositoryMock;

	static ResourceLoader resourceLoader;
	static ObjectMapper mapper;
	
	static Book book;
	static String bookId;
	static List<Book> booksByTitle;
	static List<Book> books;
	
	static String emtyBooks;
	static String pathEmptyBookPayLoad="emptyListPayload.json";
	
	static String pathBookPayload = "bookPayload.json";
	static String pathBooksPayLoad = "booksPayload.json";
	static String pathBooksTitlePayload = "booksByTitlePayload.json";
	
	@InjectMocks
	BookServiceImpl bookServiceImpl = new BookServiceImpl();

	
	@BeforeClass
	public static void setUp() throws IOException {
		resourceLoader = new DefaultResourceLoader();
		mapper = new ObjectMapper();
		bookId = "BOOK_00002";
		book = mapper.readValue(resourceLoader.getResource(pathBookPayload).getFile(), Book.class);
		books = mapper.readValue(resourceLoader.getResource(pathBooksPayLoad).getFile(), new TypeReference<List<Book>>() {
		});
		booksByTitle = mapper.readValue(resourceLoader.getResource(pathBooksTitlePayload).getFile(), new TypeReference<List<Book>>() {
		});
	}

	@Test
	public void addBookTest() {

		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>(
				Constant.BOOK_ADDEED_SUCCESS_MESSAGE + book.getBookId(), HttpStatus.CREATED);

		when(bookRepositoryMock.save(book)).thenReturn(book);

		assertEquals(expectedResponseEntity, bookServiceImpl.addbook(book));

	}

	@Test
	public void addBooksTest() {

		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>(
				Constant.ALL_BOOKS_ADDED_SUCCESS_MESSAGE, HttpStatus.CREATED);

		when(bookRepositoryMock.saveAll(books)).thenReturn(books);

		assertEquals(expectedResponseEntity, bookServiceImpl.addbooks(books));
	}

	@Test
	public void fetchBookTest() {

		Optional<Book> optionalBook = Optional.of(book);

		ResponseEntity<Book> expectedResponseEntity = new ResponseEntity<Book>(book, HttpStatus.OK);

		when(bookRepositoryMock.findById(book.getBookId())).thenReturn(optionalBook);

		assertEquals(expectedResponseEntity, bookServiceImpl.fetchBook(book.getBookId()));

	}

	@Test(expected = BookNotFoundException.class)
	public void fetchBookInvalidTest() {
		String invalidBookId = "BOOK_1010";

		when(bookRepositoryMock.findById(invalidBookId)).thenReturn(Optional.empty());

		bookServiceImpl.fetchBook(invalidBookId);

	}

	@Test
	public void fetchBooksTest() {

		emtyBooks=pathEmptyBookPayLoad;
		ResponseEntity<List<Book>> expectedResponseEntity = new ResponseEntity<List<Book>>(books, HttpStatus.OK);

		when(bookRepositoryMock.findAll()).thenReturn(books);

		assertEquals(expectedResponseEntity, bookServiceImpl.fetchBooks());

	}

	@Test(expected=BookNotFoundException.class)
	public void fetchBooksInvalidTest() {
		
		List<Book> emptyList=new ArrayList<>();
		
		when(bookRepositoryMock.findAll()).thenReturn(emptyList);
		
		bookServiceImpl.fetchBooks();
	
	}
	@Test
	public void findBooksByTitleContainingTest() {

		String title = "Applied";

		ResponseEntity<List<Book>> expectedResponseEntity = new ResponseEntity<List<Book>>(booksByTitle, HttpStatus.OK);

		when(bookRepositoryMock.findByBookTitleContaining(title)).thenReturn(booksByTitle);

		assertEquals(expectedResponseEntity, bookServiceImpl.findByBookTitleContaining(title));
	}
	
	@Test(expected=BookNotFoundException.class)
	public void invalidFindBookByTitleContainingTest() {
		String emptyTitle="emptyListPayload.json";
		List<Book> emptyBookList=new ArrayList<Book>();
		when(bookRepositoryMock.findAll()).thenReturn(emptyBookList);
		bookServiceImpl.findByBookTitleContaining(emptyTitle);;
	}

	@Test
	public void removeBookTest() {

		ResponseEntity<String> expeResponseEntity = new ResponseEntity<String>(
				Constant.BOOK_DELETED_SUCCESS_MESSAGE + bookId, HttpStatus.OK);
		Optional<Book> optional = Optional.of(book);
		when(bookRepositoryMock.findById(bookId)).thenReturn(optional);
		doNothing().when(bookRepositoryMock).deleteById(bookId);
		assertEquals(expeResponseEntity, bookServiceImpl.removeBook(bookId));
	}
	
	@Test(expected=BookNotFoundException.class)
	public void invalidRemoveBookTest() {
		String invalidId="BOOK_111011";
		
		when(bookRepositoryMock.findById(invalidId)).thenReturn(Optional.empty());
		
		bookServiceImpl.removeBook(invalidId);
	}

	@Test
	public void udateBookTest() {

		ResponseEntity<Book> expeResponseEntity = new ResponseEntity<Book>(book, HttpStatus.OK);

		Optional<Book> optionalBook = Optional.of(book);
		when(bookRepositoryMock.findById(book.getBookId())).thenReturn(optionalBook);

		when(bookRepositoryMock.save(book)).thenReturn(book);
		assertEquals(expeResponseEntity, bookServiceImpl.updateBook(book));
	}
	
	@Test(expected=BookNotFoundException.class)
	public void invalidUpdateBookTest() {
		String invalidBookId="BOOK_1801291";
		
		when(bookRepositoryMock.findById(invalidBookId)).thenReturn(Optional.empty());
		
		bookServiceImpl.updateBook(book);
	}

}
