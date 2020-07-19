package com.abc.lib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abc.lib.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

	List<Book> findByBookTitleContaining(String bookTitle);

}
