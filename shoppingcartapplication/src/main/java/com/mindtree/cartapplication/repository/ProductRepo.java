package com.mindtree.cartapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.cartapplication.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {
   @Query("select p from Product p where p.productName=?1")
	Product findProductByName(String prodName);
   @Query("select p from Product p where p.productPrice=?1")
   List<Product> findProductsByProductPrice(float productPrice);
   @Query("Select p from Product p where p.productQuantity=?1")
    List<Product> findProductsByProductQuantity(int productQuantity);
}
