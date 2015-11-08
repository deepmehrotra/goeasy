package com.goeasy.dao;

import java.util.List;

import com.goeasy.model.Category;

/**
 * @author Deep Mehrotra
 *
 */
public interface CategoryDao {
 
	 public void addCategory(Category category,int sellerId);

	 public List<Category> listCategories(int sellerId);
	 
	 public List<Category> listParentCategories(int sellerId);
	 
	 public Category getCategory(int categoryId);
	 
	 public int deleteCategory(Category category,int sellerId);

	public List<Long> getSKuCount(String catname, int catId, int sellerId);
	
	public Category getCategory(String catname ,int sellerId);
}