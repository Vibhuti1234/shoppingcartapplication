package com.mindtree.cartapplication.service;

import java.util.List;

import com.mindtree.cartapplication.dto.CartDto;
import com.mindtree.cartapplication.dto.ProductDto;
import com.mindtree.cartapplication.dto.UserDto;
import com.mindtree.cartapplication.entity.User;
import com.mindtree.cartapplication.exception.ShoppingCartApplicationException;

public interface UserService {

	UserDto addUser(UserDto user);

	ProductDto deleteSingleProduct(int userId, int productId) throws ShoppingCartApplicationException;

	ProductDto deleteAllProduct(int userId) throws ShoppingCartApplicationException;

	List<ProductDto> getAllProducts(int userId) throws ShoppingCartApplicationException;

	CartDto viewCart(int userId) throws ShoppingCartApplicationException;


}
