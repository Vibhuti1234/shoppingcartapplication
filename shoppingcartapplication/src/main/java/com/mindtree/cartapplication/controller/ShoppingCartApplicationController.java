package com.mindtree.cartapplication.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.cartapplication.dto.CartDto;
import com.mindtree.cartapplication.dto.ProductDto;
import com.mindtree.cartapplication.dto.ResponseDto;
import com.mindtree.cartapplication.dto.UserDto;
import com.mindtree.cartapplication.exception.ShoppingCartApplicationException;
import com.mindtree.cartapplication.service.CartService;
import com.mindtree.cartapplication.service.ProductService;
import com.mindtree.cartapplication.service.UserService;



@RestController
public class ShoppingCartApplicationController {
@Autowired
UserService userService;
@Autowired
CartService cartService;
@Autowired
ProductService productService;
Logger logger=Logger.getLogger(ShoppingCartApplicationController.class.getName());

@PostMapping(value="/addUser")
public ResponseEntity<ResponseDto<UserDto>> addUser(@RequestBody UserDto user)
{   logger.info("inside addUser in ShoppingCartApplicationController");
	ResponseDto<UserDto> response=new ResponseDto<UserDto>(userService.addUser(user), null,"User Added Successfull", true);
	logger.info("Before return statement addUser in ShoppingCartApplicationController ");
	return ResponseEntity.status(HttpStatus.OK).body(response);

}


@PostMapping(value="/createProduct")
public ResponseEntity<ResponseDto<ProductDto>> createProduct(@RequestBody ProductDto productDto) 
{  logger.info("inside createProduct in ShoppingCartApplicationController");
	ResponseDto<ProductDto> response=new ResponseDto<ProductDto>(productService.createProduct(productDto), null,"Product Added Successfully", true);
	logger.info("Before return statement createProduct in ShoppingCartApplicationController ");

	return ResponseEntity.status(HttpStatus.OK).body(response);
	

}
@PostMapping(value="/addProductsToCart/{userId}/{productId}")
public ResponseEntity<ResponseDto<ProductDto>> addProductsToCart(@PathVariable int userId,@PathVariable int productId) throws ShoppingCartApplicationException 
{   logger.info("inside addProductsToCart in ShoppingCartApplicationController");
	ResponseDto<ProductDto> response=new ResponseDto<ProductDto>(productService.addProductsToCart(userId,productId), null,"Product Added Successfully", true);
	logger.info("Before return statement addProductsToCart in ShoppingCartApplicationController ");

	return ResponseEntity.status(HttpStatus.OK).body(response);

}
@GetMapping(value="/searchProductById/{prodId}")
public ResponseEntity<ResponseDto<ProductDto>> searchProductById(@PathVariable int prodId) throws ShoppingCartApplicationException 
{   logger.info("inside searchProductById in ShoppingCartApplicationController");
	ResponseDto<ProductDto> response=new ResponseDto<ProductDto>(productService.searchProductById(prodId), null,"Product Found", true);
	logger.info("Before return statement searchProductById in ShoppingCartApplicationController ");

	return ResponseEntity.status(HttpStatus.OK).body(response);

}
@GetMapping(value="/searchProductByName/{prodName}")
public ResponseEntity<ResponseDto<ProductDto>> searchProductByName(@PathVariable String prodName) throws ShoppingCartApplicationException 
{   logger.info("inside searchProductByName in ShoppingCartApplicationController");
	ResponseDto<ProductDto> response=new ResponseDto<ProductDto>(productService.searchProductByName(prodName), null,"Product Found", true);
	logger.info("Before return statement searchProductByName in ShoppingCartApplicationController ");

	return ResponseEntity.status(HttpStatus.OK).body(response);

}
@GetMapping(value="/searchProductByCategory/{category}")
public ResponseEntity<ResponseDto<List<ProductDto>>> searchProductByCategory(@PathVariable String category) throws ShoppingCartApplicationException 
{    logger.info("inside searchProductByCategory in ShoppingCartApplicationController");
	ResponseDto<List<ProductDto>> response=new ResponseDto<List<ProductDto>>(productService.searchProductByCategory(category), null,"Product Found", true);
	logger.info("Before return statement searchProductByCategory in ShoppingCartApplicationController ");

	return ResponseEntity.status(HttpStatus.OK).body(response);

}
@DeleteMapping(value="/deleteSingleProduct/{userId}/{productId}")
public ResponseEntity<ResponseDto<ProductDto>> deleteSingleProduct(@PathVariable int userId,@PathVariable int productId) throws ShoppingCartApplicationException 
{   logger.info("inside deleteSingleProduct in ShoppingCartApplicationController");
	ResponseDto<ProductDto> response=new ResponseDto<ProductDto>(userService.deleteSingleProduct(userId,productId), null,"Product deleted Successfully", true);
	logger.info("Before return statement deleteSingleProduct in ShoppingCartApplicationController ");

	return ResponseEntity.status(HttpStatus.OK).body(response);

}
@DeleteMapping(value="/deleteAllProduct/{userId}")
public ResponseEntity<ResponseDto<ProductDto>> deleteAllProduct(@PathVariable int userId) throws ShoppingCartApplicationException 
{   logger.info("inside deleteAllProduct in ShoppingCartApplicationController");
	ResponseDto<ProductDto> response=new ResponseDto<ProductDto>(userService.deleteAllProduct(userId), null," All Product Deleted Successfully", true);
	logger.info("Before return statement deleteAllProduct in ShoppingCartApplicationController ");

	return ResponseEntity.status(HttpStatus.OK).body(response);

}
@GetMapping(value="/getAllProducts/{userId}")
public ResponseEntity<ResponseDto<List<ProductDto>>> getAllProducts(@PathVariable int userId) throws ShoppingCartApplicationException 
{    logger.info("inside getAllProducts in ShoppingCartApplicationController");
	ResponseDto<List<ProductDto>> response=new ResponseDto<List<ProductDto>>(userService.getAllProducts(userId), null,"Product Found", true);
	logger.info("Before return statement getAllProducts in ShoppingCartApplicationController ");

	return ResponseEntity.status(HttpStatus.OK).body(response);

}
@PutMapping(value="/updateProductQuantity/{userId}/{productId}/{quantity}")
public ResponseEntity<ResponseDto<ProductDto>> updateProductQuantity(@PathVariable int userId,@PathVariable int productId,@PathVariable int quantity) throws ShoppingCartApplicationException 
{   logger.info("inside updateProductQuantity in ShoppingCartApplicationController");
	ResponseDto<ProductDto> response=new ResponseDto<ProductDto>(productService.updateProductQuantity(userId,productId,quantity), null,"Product updated Successfully", true);
	logger.info("Before return statement updateProductQuantity in ShoppingCartApplicationController ");

	return ResponseEntity.status(HttpStatus.OK).body(response);

}
@GetMapping(value="/viewCart/{userId}")
public ResponseEntity<ResponseDto<CartDto>> viewCart(@PathVariable int userId) throws ShoppingCartApplicationException 
{   logger.info("inside viewCart in ShoppingCartApplicationController");
	ResponseDto<CartDto> response=new ResponseDto<CartDto>(userService.viewCart(userId), null,"cart details Found", true);
	logger.info("Before return statement viewCart in ShoppingCartApplicationController ");

	return ResponseEntity.status(HttpStatus.OK).body(response);

}

}


 