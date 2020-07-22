package com.mindtree.cartapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.cartapplication.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	@Query("select u from User u where u.userName=?1")
    List<User> getUsersByUserName(String userName);
	

}
