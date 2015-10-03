package com.goeasy.dao;

import java.util.List;

import com.goeasy.model.Product;

/**
 * @author Deep Mehrotra
 *
 */
public interface ProductDao {
 
	 public void addProduct(Product product,int sellerId);

	 public List<Product> listProducts(int sellerId);
	 
	 public Product getProduct(int orderId);
	 
	 public void deleteProduct(Product product,int sellerId);
	 
	 public void updateInventory(String sku , int currentInventory , int quantoAdd , int quantoSub,int sellerId);
}