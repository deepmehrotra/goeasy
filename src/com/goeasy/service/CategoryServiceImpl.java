package com.goeasy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.goeasy.dao.CategoryDao;
import com.goeasy.model.Category;

/**
 * @author Deep Mehortra
 *
 */
@Service("categoryService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CategoryServiceImpl implements CategoryService {

 @Autowired
 private CategoryDao categoryDao;
 

@Override
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public void addCategory(Category category ,int sellerId) {
	System.out.println("Inside add Category CategoryServiceImpl name :"+category.getCatName());
	categoryDao.addCategory(category,sellerId);
	
}

@Override
public List<Category> listCategories(int sellerId) {
	return categoryDao.listCategories(sellerId);
}
@Override
public List<Category> listParentCategories(int sellerId)
{
	return categoryDao.listParentCategories(sellerId);
}
@Override
public Category getCategory(int CategoryId) {
	return categoryDao.getCategory(CategoryId);
}

@Override
public void deleteCategory(Category category,int sellerId) {
	
	categoryDao.deleteCategory(category,sellerId);
}

}