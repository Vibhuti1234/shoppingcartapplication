package com.mindtree.cartapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.cartapplication.entity.Book;
@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {
	@Query("select b from Book b where b.genre=?1")
	List<Book> findBooksByGenre(String genre);
	@Query("select b from Book b where b.author=?1")
	List<Book> findBooksByAuthor(String author);
	@Query("select b from Book b where b.publications=?1")
	List<Book> findBooksByPublications(String publications);


}
