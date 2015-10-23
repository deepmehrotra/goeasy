package com.goeasy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.goeasy.dao.ProductDao;
import com.goeasy.model.Product;

/**
 * @author Deep Mehortra
 *
 */
@Service("productService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class ProductServiceImpl implements ProductService {

 @Autowired
 private ProductDao productDao;
 

@Override
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public void addProduct(Product product ,int sellerId) {
	System.out.println("Inside add order OrderServiceImpl awb :"+product.getProductName());
	productDao.addProduct(product,sellerId);
	
}

@Override
public List<Product> listProducts(int sellerId) {
	return productDao.listProducts(sellerId);
}

@Override
public Product getProduct(int orderId) {
	return productDao.getProduct(orderId);
}

@Override
public void deleteProduct(Product product,int sellerId) {
	
	productDao.deleteProduct(product,sellerId);
}

@Override
public void updateInventory(String sku , int currentInventory , int quantoAdd , int quantoSub ,int sellerId)
{
	productDao.updateInventory(sku ,currentInventory ,quantoAdd ,quantoSub ,sellerId);
}

@Override
public Product getProduct(String skuCode, int sellerId)
{
	return productDao.getProduct(skuCode, sellerId);
}

}