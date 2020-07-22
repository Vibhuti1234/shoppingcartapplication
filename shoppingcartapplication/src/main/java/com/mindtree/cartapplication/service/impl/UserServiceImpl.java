package com.mindtree.cartapplication.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.cartapplication.dto.CartDto;
import com.mindtree.cartapplication.dto.ProductDto;
import com.mindtree.cartapplication.dto.UserDto;
import com.mindtree.cartapplication.entity.Cart;
import com.mindtree.cartapplication.entity.Product;
import com.mindtree.cartapplication.entity.User;
import com.mindtree.cartapplication.exception.NoCartFoundException;
import com.mindtree.cartapplication.exception.NoProductFoundException;
import com.mindtree.cartapplication.exception.NoUserFoundException;
import com.mindtree.cartapplication.exception.ShoppingCartApplicationException;
import com.mindtree.cartapplication.repository.CartRepo;
import com.mindtree.cartapplication.repository.ProductRepo;
import com.mindtree.cartapplication.repository.UserRepo;
import com.mindtree.cartapplication.service.UserService;
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
public class UserServiceImpl implements UserService {
   @Autowired 
   UserRepo userRepo;
   @Autowired
   ProductRepo productRepo;
   @Autowired
   CartRepo cartRepo;
   static int c=0;
   static int index;
   static int count=0;
   static int freq=0;

   Logger logger=Logger.getLogger(UserServiceImpl.class.getName());

   ModelMapper modelMapper =new ModelMapper();
	
	private User convertDtoToEntity(UserDto user) {
		// TODO Auto-generated method stub
		return modelMapper.map(user,User.class);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public ProductDto deleteSingleProduct(int userId, int productId) throws ShoppingCartApplicationException {
		// TODO Auto-generated method stub
		logger.warning("If such user id is not available in user table then it will throw no user found exception:");

		User user=userRepo.findAll().stream().filter(i->i.getUserId()==userId).findAny().orElseThrow(()->new NoUserFoundException("No User found"));
         if(user.getCart()==null || user.getCart().getProductList()==null || user.getCart().getProductList().isEmpty())
         {		logger.warning("If any of the above conditions becomes true then it will throw no product  found exception:");

        	 throw new NoProductFoundException("Product Not found");
         }
 		logger.warning("If such product id is not available in product table then it will throw no product  found exception:");

 		Product prod=productRepo.findAll().stream().filter(i->i.getProductId()==productId).findAny().orElseThrow(()->new NoProductFoundException("No Product Found"));
        user.getCart().getProductList().stream().forEach(i->{if(i.getProductId()==productId) {c=1;}});

        if(c==0)
        {logger.warning("If above condition becomes true then it will throw no product  found exception:");
       	 throw new NoProductFoundException("Product Not found");

        }	
        user.getCart().getProductList().stream().forEach(i->{if(i.getProductId()==productId) {count++;}});
        prod.setCart(null);
        prod.setProductQuantity(prod.getProductQuantity()+count);
        user.getCart().setCartQuantity(user.getCart().getCartQuantity()-count);
        user.getCart().setCartPrice(user.getCart().getCartPrice()-prod.getProductPrice()*count);
        

        cartRepo.saveAndFlush(user.getCart());

        userRepo.saveAndFlush(user);
		return convertEntityToDto(prod);
	}
	private ProductDto convertEntityToDto(Product prod) {
		// TODO Auto-generated method stub
		return modelMapper.map(prod, ProductDto.class);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public ProductDto deleteAllProduct(int userId) throws ShoppingCartApplicationException {
		// TODO Auto-generated method stub
		logger.warning("If such user id is not available in user table then it will throw no user found exception:");

		User user=userRepo.findAll().stream().filter(i->i.getUserId()==userId).findAny().orElseThrow(()->new NoUserFoundException("No User found"));
		if(user.getCart()==null || user.getCart().getProductList()==null || user.getCart().getProductList().isEmpty())
        {logger.warning("If any of the above conditions becomes true then it will throw no product  found exception:");

       	 throw new NoProductFoundException("Product Not found");
        }
		user.getCart().getProductList().stream().forEach(i->{freq=Collections.frequency(user.getCart().getProductList(), i);i.setProductQuantity(i.getProductQuantity()+freq);i.setCart(null);freq=0;});
		user.getCart().setCartQuantity(0);
		user.getCart().setCartPrice(0);
		cartRepo.saveAndFlush(user.getCart());

        userRepo.saveAndFlush(user);
		return null;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)

	public List<ProductDto> getAllProducts(int userId) throws ShoppingCartApplicationException {
		// TODO Auto-generated method stub
		logger.warning("If such user id is not available in user table then it will throw no user found exception:");

		User user=userRepo.findAll().stream().filter(i->i.getUserId()==userId).findAny().orElseThrow(()->new NoUserFoundException("No User found"));
		List<ProductDto> productDtos=new ArrayList<ProductDto>();
         for (Product product : user.getCart().getProductList()) {
        	 productDtos.add(convertEntityToDto(product));
			
		}
		return productDtos;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)

	public CartDto viewCart(int userId) throws ShoppingCartApplicationException {
		// TODO Auto-generated method stub
		logger.warning("If such user id is not available in user table then it will throw no user found exception:");

		User user=userRepo.findAll().stream().filter(i->i.getUserId()==userId).findAny().orElseThrow(()->new NoUserFoundException("No User found"));
         if(user.getCart()==null)
         {   logger.warning("If no cart is assigned to or associated with the given user then it will throws NoCartFoundException:");
        	 throw new NoCartFoundException("User dont have any cart");
         }
         
		return convertEntityToDto(user.getCart());
	}
	private CartDto convertEntityToDto(Cart cart) {
		// TODO Auto-generated method stub
		return modelMapper.map(cart, CartDto.class);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public UserDto addUser(UserDto user) {
		// TODO Auto-generated method stub
		userRepo.save(convertDtoToEntity(user));
		return user;
	}
	

}
