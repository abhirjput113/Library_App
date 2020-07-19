package com.abc.lib.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.abc.lib.constants.ValidationConstant;
import com.abc.lib.utils.CustomIdGenerator;

/*
*/
@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq_generator")
	@GenericGenerator(name = "book_seq_generator", strategy = "com.abc.lib.utils.CustomIdGenerator", parameters = {
			@Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CustomIdGenerator.VALUE_PREFIX_PARAMETER, value = "BOOK_"),
			@Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d") })
	@Column(name = "book_id", updatable = false, nullable = false)
	private String bookId;

	@NotBlank(message = ValidationConstant.Validation_BookTitle_Message)
	private String bookTitle;
	@NotNull(message = ValidationConstant.Validation_BookSubTitle_Message)
	private String bookSubTitle;
	@NotBlank(message = ValidationConstant.Validation_BookAuthor_Message)
	private String bookAuthor;
	@NotBlank(message = ValidationConstant.Validation_BookPublisher_Message)
	private String bookPublisher;
	@NotBlank(message = ValidationConstant.Validation_BookEdition_Message)
	private String bookEdition;

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookSubTitle() {
		return bookSubTitle;
	}

	public void setBookSubTitle(String bookSubTitle) {
		this.bookSubTitle = bookSubTitle;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public String getBookPublisher() {
		return bookPublisher;
	}

	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}

	public String getBookEdition() {
		return bookEdition;
	}

	public void setBookEdition(String bookEdition) {
		this.bookEdition = bookEdition;
	}

}
