package com.mindtree.cartapplication.service;

import java.util.List;

import com.mindtree.cartapplication.dto.BookDto;
import com.mindtree.cartapplication.dto.ProductDto;
import com.mindtree.cartapplication.entity.Book;
import com.mindtree.cartapplication.exception.NoCartFoundException;
import com.mindtree.cartapplication.exception.ShoppingCartApplicationException;

public interface ProductService {


	ProductDto createProduct(ProductDto productDto);

	ProductDto addProductsToCart(int userId, int productId) throws ShoppingCartApplicationException;

	ProductDto searchProductById(int prodId) throws ShoppingCartApplicationException;

	ProductDto searchProductByName(String prodName) throws ShoppingCartApplicationException;

	ProductDto updateProductQuantity(int userId, int productId, int quantity) throws ShoppingCartApplicationException;

	List<ProductDto> searchProductByCategory(String category) throws ShoppingCartApplicationException;



}
