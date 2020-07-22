package com.mindtree.cartapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.cartapplication.entity.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer>{
	@Query("select c from Cart c where c.cartQuantity=?1")
List<Cart> findCartsByCartQuantity(int cartQuantity);
	@Query("select c from Cart c where c.cartPrice=?1")
	List<Cart> findCartsByCartPrice(float cartPrice);
} 
