package com.goeasy.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.goeasy.model.Category;
import com.goeasy.model.Product;
import com.goeasy.model.Seller;


/**
 * @author Deep Mehrotra
 *
 */
@Repository("productDao")
public class ProductDaoImpl implements ProductDao {

	 @Autowired
	 private SessionFactory sessionFactory;
	 

	@Override
	public void addProduct(Product product,int sellerId) {
		//sellerId=4;
		Seller seller=null;
		Category productcat=null;
		int productId=product.getProductId();
		String sku=product.getProductSkuCode();
		String productName=product.getProductName();
		String catName=product.getCategoryName();
		int quant=product.getQuantity();
		float price=product.getProductPrice();
		Date proddate=product.getProductDate();
		   try
		   {
		   Session session=sessionFactory.openSession();
		   session.beginTransaction();
		   Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id", sellerId));
		   seller=(Seller)criteria.list().get(0);
		   for(Category category:seller.getCategories())
		   {
			   if(category.getCatName().equalsIgnoreCase(catName))
			   {
				   productcat=category;
			   }
		   }
		   if(productId==0)
		   {
			  
			   if(productcat!=null)
			   {
			   product.setCategory(productcat);
			   productcat.getProducts().add(product);
			   productcat.setProductCount(productcat.getProductCount()+product.getQuantity());
			   session.saveOrUpdate(productcat);
			   }
			   if(seller.getProducts()!=null)
			   seller.getProducts().add(product);
			   session.saveOrUpdate(product);
			   session.saveOrUpdate(seller);
		   } 
		   else
		   {
			  Product productObj=(Product)session.get(Product.class,productId);
			  productObj.setProductName(productName);
			  productObj.setProductDate(proddate);
			  productObj.setProductPrice(price);
			  productObj.setProductSkuCode(sku);
			  
			  productObj.getCategory().setProductCount(productObj.getCategory().getProductCount()-productObj.getQuantity()+quant);
			  productObj.setQuantity(quant);
			  session.saveOrUpdate(productObj);
			  
		   }
		   
		   session.getTransaction().commit();
		   session.close();
		   }
		   catch (Exception e) {
			   System.out.println("Inside exception  "+e.getLocalizedMessage());
			// TODO: handle exception
		}
		
		
	}

	@Override
	public List<Product> listProducts(int sellerId) {
		//sellerId=4;
		List<Product> returnlist=null;
		try
		{
		Session session=sessionFactory.openSession();
		   session.beginTransaction();
		   Seller seller=(Seller)session.get(Seller.class, sellerId);
		   if(seller.getProducts()!=null&&seller.getProducts().size()!=0)
			   returnlist=seller.getProducts();
		   session.getTransaction().commit();
		   session.close();
		}
		catch(Exception e)
		{
			System.out.println(" Exception in getting order list :"+e.getLocalizedMessage());
		}
		return returnlist;
	}

	@Override
	public Product getProduct(int productId) {
		return (Product) sessionFactory.getCurrentSession().get(Product.class, productId);
	}

	@Override
	public void deleteProduct(Product product,int sellerId) {
		 System.out.println(" In Category delete sku id "+product.getProductSkuCode());
		 //sellerId=4;
		 try
		 {
			 Session session=sessionFactory.openSession();
			  session.beginTransaction();
			  Query deleteQuery = session.createSQLQuery(
					    "delete from Seller_Product where seller_Id=? and product_productId=?");
			  		deleteQuery.setInteger(0,sellerId);
					deleteQuery.setInteger(1, product.getProductId());
					
					int updated = deleteQuery.executeUpdate();
					int catdelete=session.createQuery("DELETE FROM Product WHERE productId = "+product.getProductId()).executeUpdate();
			
					System.out.println("  Deleteing category updated:"+updated+" catdelete :"+catdelete);
			  session.getTransaction().commit();
			  session.close();

		 }
		 catch(Exception e)
		 {
			 System.out.println(" Inside delleting order"+e.getLocalizedMessage());
			 e.printStackTrace();
		 }
		
	}

	public void updateInventory(String sku , int currentInventory , int quantoAdd , int quantoSub , int sellerId)
	{
		System.out.println(" Inside inventory update method :"+sku);
		Product product=null;
		Seller seller=null;
		//sellerId=4;
		try
		{
		Session session=sessionFactory.openSession();
		   session.beginTransaction();
		   Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id",sellerId));
		   criteria.createAlias("products", "product", CriteriaSpecification.LEFT_JOIN)
		   .add(Restrictions.eq("product.productSkuCode", sku))
		   .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		   if(criteria.list().size()!=0)
		   {
		   seller=(Seller)criteria.list().get(0);
		   product=seller.getProducts().get(0);
		   if(product==null)
			   System.out.println(" Null product");
		   System.out.println(" Present value :"+product.getQuantity());
		   if(currentInventory!=0)
		   {
			   product.setQuantity(currentInventory);
		   }
		   else if(quantoAdd!=0)
		   {
			   product.setQuantity(product.getQuantity()+quantoAdd);
			   product.getCategory().setProductCount(product.getCategory().getProductCount()+quantoAdd);
		   }
		   else if(quantoSub!=0&&!(quantoSub>product.getQuantity()))
		   {
			   product.setQuantity(product.getQuantity()-quantoSub);
			   product.getCategory().setProductCount(product.getCategory().getProductCount()-quantoSub);
				  
		   }
		   session.saveOrUpdate(product);
		   }
		   session.getTransaction().commit();
		   session.close();
		}
		catch(Exception e)
		{
			System.out.println(" Exception in updating inventories  :"+e.getMessage());
			e.printStackTrace();
		}
	}

}