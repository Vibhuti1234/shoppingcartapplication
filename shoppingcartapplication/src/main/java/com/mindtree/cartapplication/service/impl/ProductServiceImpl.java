package com.mindtree.cartapplication.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.cartapplication.dto.BookDto;
import com.mindtree.cartapplication.dto.ProductDto;
import com.mindtree.cartapplication.entity.Apparal;
import com.mindtree.cartapplication.entity.Book;
import com.mindtree.cartapplication.entity.Cart;
import com.mindtree.cartapplication.entity.Product;
import com.mindtree.cartapplication.entity.User;
import com.mindtree.cartapplication.exception.InvalidUpdationException;
import com.mindtree.cartapplication.exception.NoCartFoundException;
import com.mindtree.cartapplication.exception.NoProductFoundException;
import com.mindtree.cartapplication.exception.NoUserFoundException;
import com.mindtree.cartapplication.exception.OutOfStockException;
import com.mindtree.cartapplication.exception.ShoppingCartApplicationException;
import com.mindtree.cartapplication.repository.ApparalRepo;
import com.mindtree.cartapplication.repository.BookRepo;
import com.mindtree.cartapplication.repository.CartRepo;
import com.mindtree.cartapplication.repository.ProductRepo;
import com.mindtree.cartapplication.repository.UserRepo;
import com.mindtree.cartapplication.service.ProductService;
@Service
@Transactional(propagation=Propagation.SUPPORTS,readOnly=true)
public class ProductServiceImpl implements ProductService{
@Autowired
CartRepo cartRepo;
@Autowired 
UserRepo userRepo;
@Autowired 
BookRepo bookRepo;
@Autowired
ProductRepo productRepo;
@Autowired
ApparalRepo apparalRepo;
static int count=0;
static int c=0;
ModelMapper modelMapper =new ModelMapper();
Logger logger=Logger.getLogger(ProductServiceImpl.class.getName());

	
	private Product convertDtoToEntity(ProductDto productDto) {
		// TODO Auto-generated method stub
		return modelMapper.map(productDto, Product.class);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public ProductDto createProduct(ProductDto productDto) {
		// TODO Auto-generated method stub
		Product product=convertDtoToEntity(productDto);
		productRepo.save(product);
		return productDto;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public ProductDto addProductsToCart(int userId, int productId) throws ShoppingCartApplicationException {
		// TODO Auto-generated method stub
		
		logger.warning("If such user id is not available in user table then it will throw no user found exception:");
		User user=userRepo.findAll().stream().filter(i->i.getUserId()==userId).findAny().orElseThrow(()->new NoUserFoundException("No User found"));
		if(user.getCart()==null)
		{
			Cart cart=new Cart();
			user.setCart(cart);
		}
		logger.warning("If such product id is not available in product table then it will throw no product  found exception:");

		Product prod=productRepo.findAll().stream().filter(i->i.getProductId()==productId).findAny().orElseThrow(()->new NoProductFoundException("No Product Found"));
		if(user.getCart().getProductList()!=null && !user.getCart().getProductList().isEmpty())
		{user.getCart().getProductList().stream().forEach(i->{if(i.getProductId()==productId) { count=1;}});
		
			}
		if(count==1)
		{   if(prod.getProductQuantity()>0) {
			user.getCart().setCartQuantity(user.getCart().getCartQuantity()+1);
			user.getCart().setCartPrice(user.getCart().getCartPrice()+prod.getProductPrice());
			user.getCart().getProductList().add(prod);
             prod.setCart(user.getCart());
			prod.setProductQuantity(prod.getProductQuantity()-1);
		}
		else { logger.warning("If product quantity in product table for that given product is 0 or less than 0 then it will throw out of stock exception:");
			throw new OutOfStockException("out of stock exception");
		}
		}
		if(count==0)
		{
			if(prod.getProductQuantity()>0) {
				user.getCart().setCartQuantity(user.getCart().getCartQuantity()+1);
				user.getCart().setCartPrice(user.getCart().getCartPrice()+prod.getProductPrice());
				user.getCart().getProductList().add(prod);
				prod.setCart(user.getCart());
				prod.setProductQuantity(prod.getProductQuantity()-1);
				
			}
			else {
				logger.warning("If product quantity in product table for that given product is 0 or less than 0 then it will throw out of stock exception:");
				throw new OutOfStockException("out of stock exception");
			}
		}     /*for (Product p : user.getCart().getProductList()) {
		   System.out.println(p.getProductId());

			
		}
*/		   
		     cartRepo.saveAndFlush(user.getCart());
			userRepo.saveAndFlush(user);
		//ProductDto productDto=covertEntityToDto(prod);
			//ProductDto product=covertEntityToDto(prod);

		
		return covertEntityToDto(prod);
	}
	private ProductDto covertEntityToDto(Product prod) {
		// TODO Auto-generated method stub
		return modelMapper.map(prod, ProductDto.class);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)

	public ProductDto searchProductById(int prodId) throws ShoppingCartApplicationException {
		// TODO Auto-generated method stub
		logger.warning("If such product id is not available in product table then it will throw no product  found exception:");

		Product prod=productRepo.findAll().stream().filter(i->i.getProductId()==prodId).findAny().orElseThrow(()->new NoProductFoundException("No Product Found"));

		return covertEntityToDto(prod);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)

	public ProductDto searchProductByName(String prodName) throws ShoppingCartApplicationException {
		// TODO Auto-generated method stub

		//Product prod=productRepo.findAll().stream().filter(i->i.getProductName().equals(prodName)).findAny().orElseThrow(()->new NoProductFoundException("No Product Found"));
		Product prod=productRepo.findProductByName(prodName);
		if(prod==null)
		{  logger.warning("If such product name is not available in product table then it will throw no product  found exception:");

			throw new NoProductFoundException("Product Not found");
		}
		return covertEntityToDto(prod);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public ProductDto updateProductQuantity(int userId, int productId, int quantity) throws ShoppingCartApplicationException {
		// TODO Auto-generated method stub
		User user=userRepo.findAll().stream().filter(i->i.getUserId()==userId).findAny().orElseThrow(()->new NoUserFoundException("No User found"));
		 if(user.getCart()==null || user.getCart().getProductList()==null || user.getCart().getProductList().isEmpty())
         {   logger.warning("if any of the above conditions is true it will throw NoProductFoundException");
        	 throw new NoProductFoundException("Product Not found");
         }
	 		Product prod=productRepo.findAll().stream().filter(i->i.getProductId()==productId).findAny().orElseThrow(()->new NoProductFoundException("No Product Found"));
	        user.getCart().getProductList().stream().forEach(i->{if(i.getProductId()==productId) {c=1;}});
	        if(c==0)
	        {logger.warning("if above condition becomes true i.e. c is equals to 0 then it throws NoProductFoundException");
	       	 throw new NoProductFoundException("Product Not found");

	        }	
	         int count=0;
	         
	        for (Product product : user.getCart().getProductList()) {
	             System.out.println(product.getProductQuantity());
	        	if(product.getProductId()==productId)
	        	{
	        		count=count+1;
	        	}
	        	
				
			}
	       int x=count+quantity;
	       
	       if(prod.getProductQuantity()<=0 || quantity>prod.getProductQuantity())
	       {   logger.warning("if product quantity is less than or equls to 0 or quantity is greater than product quantity in product table then it will throw OutOfStockException");
	    	 throw  new OutOfStockException("Out of Stock");
	    	   
	       }
	       if(x<0)
	       { logger.warning("if above condition becomes true i.e. c is equals to 0 then it throws NoProductFoundException");

	    	   throw new InvalidUpdationException("Invalid Number");
	       }
	       user.getCart().setCartQuantity(user.getCart().getCartQuantity()+quantity);
	       user.getCart().setCartPrice(user.getCart().getCartPrice()+prod.getProductPrice()*quantity);
	       prod.setProductQuantity(prod.getProductQuantity()-quantity);
	       cartRepo.saveAndFlush(user.getCart());
	       userRepo.saveAndFlush(user);
	       
	         
		return convertEntityToDto(prod);
	}
	private ProductDto convertEntityToDto(Product prod) {
		// TODO Auto-generated method stub
		return modelMapper.map(prod, ProductDto.class);
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)

	public List<ProductDto> searchProductByCategory(String category) throws ShoppingCartApplicationException {
		// TODO Auto-generated method stub
		List<Book> books=new ArrayList<Book>();
		List<Apparal> apparals=new ArrayList<>();
		if(!category.equalsIgnoreCase("book")&& !category.equalsIgnoreCase("apparal"))
		{  logger.warning("if category is neither book nor apparal then it will throw NoProductFoundException ");
			throw new NoProductFoundException("Not found");
		}
		if(category.equalsIgnoreCase("book"))
		{
			books.addAll(bookRepo.findAll());
			List<Product> products=new ArrayList<>();
			List<ProductDto> productDtos=new ArrayList<>();

			for (Book book : books) {
			   Product product=productRepo.getOne(book.getProductId());
			   products.add(product);
				
				
			}
			products.stream().forEach(i->{productDtos.add(covertEntityToDto(i));});
			return productDtos;
			
		}
		else
		{
			apparals.addAll(apparalRepo.findAll());
			List<Product> products=new ArrayList<>();
			List<ProductDto> productDtos=new ArrayList<>();
			for (Apparal apparal : apparals) {
				
				Product product=productRepo.getOne(apparal.getProductId());
				products.add(product);
			}
			products.stream().forEach(i->{productDtos.add(covertEntityToDto(i));});
           return productDtos;
		}
		
		
			}
	

}