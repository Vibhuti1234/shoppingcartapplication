package com.mindtree.cartapplication.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.cartapplication.dto.CartDto;
import com.mindtree.cartapplication.entity.Cart;
import com.mindtree.cartapplication.entity.User;
import com.mindtree.cartapplication.exception.NoSuchUserFoundException;
import com.mindtree.cartapplication.repository.CartRepo;
import com.mindtree.cartapplication.repository.UserRepo;
import com.mindtree.cartapplication.service.CartService;
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
public class CartServiceImpl implements CartService{
    @Autowired
    UserRepo userRepo;
    @Autowired
    CartRepo cartRepo;
    ModelMapper modelMapper =new ModelMapper();

	

	private Cart convertDtoToEntity(CartDto cartDto) {
		// TODO Auto-generated method stub
		return modelMapper.map(cartDto, Cart.class);
	}

}
