package com.mindtree.cartapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mindtree.cartapplication.entity.Apparal;
@Repository
public interface ApparalRepo extends JpaRepository<Apparal, Integer>{
	@Query("select a from Apparal a where a.type=?1")
List<Apparal> findApparalsByType(String type);
	@Query("select a from Apparal a where a.brand=?1")
List<Apparal> findApparalsByBrand(String brand);
	@Query("select a from Apparal a where a.design=?1")
List<Apparal> findApparalsByDesign(String design);

}
