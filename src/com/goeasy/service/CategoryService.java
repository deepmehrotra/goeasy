package com.goeasy.service;

import java.util.List;

import com.goeasy.model.Category;

/**
 * @author Deep Mehrotra
 *
 */
public interface CategoryService {
 
 public void addCategory(Category category , int sellerId);

 public List<Category> listCategories(int sellerId);
 
 public List<Category> listParentCategories(int sellerId);
 
 public Category getCategory(int catId);
 
 public Category getCategory(String catname ,int sellerId);
 
 
 public int deleteCategory(Category category,int sellerId);

public List<Long> getSKuCount(String catname, int catId, int sellerId);
}