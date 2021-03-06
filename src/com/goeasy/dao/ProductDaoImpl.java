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
import com.goeasy.model.ProductStockList;
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
		String skuChannel=product.getChannelSKU();
		long quant=product.getQuantity();
		float price=product.getProductPrice();
		Date proddate=product.getProductDate();
		Date todayDate=new Date();
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
			   productcat.setSkuCount(productcat.getSkuCount()+1);
			   
			   //Update opening stock if not
			  /* if(productcat.getOsUpdate()!=null)
				 {
					 if(productcat.getOsUpdate().getMonth()<todayDate.getMonth())
							 {
						 productcat.setOpeningStock(productcat.getProductCount());
						 productcat.setOsUpdate(todayDate);
							 }
					 else if(productcat.getOsUpdate().getMonth()==12)
					 {
						 if(todayDate.getMonth()==1)
						 {
							 productcat.setOpeningStock(productcat.getProductCount());
							 productcat.setOsUpdate(todayDate);
								
								 
						 }
					 }
				 }*/
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
			  productObj.setChannelSKU(skuChannel);
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
	 public Product getProduct(String skuCode, int sellerId)
	 {
		 Product returnObject=null;
		 Seller seller=null;
		  
		 System.out.println(" ***Insid get product from sku ***"+skuCode);
		
			 try
			 {
		   Session session=sessionFactory.openSession();
		   session.beginTransaction();
		   Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id", sellerId));
		   criteria.createAlias("products", "product", CriteriaSpecification.LEFT_JOIN)
		   .add(Restrictions.eq("product.productSkuCode", skuCode).ignoreCase())
		   .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		   
		   if(criteria.list()!=null&&criteria.list().size()!=0)
		   {
		   seller=(Seller)criteria.list().get(0);
		   returnObject=seller.getProducts().get(0);
		   }
		   else
		   {
			   System.out.println("Product sku "+skuCode+" not found");
		   }
		   session.getTransaction().commit();
		   session.close();
			 }
			 catch (Exception e) {
				System.out.println(" Product   DAO IMPL :"+e.getLocalizedMessage());
				e.printStackTrace();
			}
			 
			 return returnObject;
		 
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

	public void updateInventory(String sku , int currentInventory , int quantoAdd , int quantoSub ,boolean status, int sellerId)
	{
		System.out.println(" Inside inventory update method :"+sku);
		Product product=null;
		Seller seller=null;
		Session session=null;
		Date todayDate=new Date();
		long currentValue=0;
		
		//sellerId=4;
		try
		{
			if(status)
			{
			session=sessionFactory.openSession();
			}
			else
			{
			session=sessionFactory.getCurrentSession();
			}
			if(session==null)
				session=sessionFactory.openSession();
		   session.beginTransaction();
		   Criteria criteria=session.createCriteria(Seller.class).add(Restrictions.eq("id",sellerId));
		   criteria.createAlias("products", "product", CriteriaSpecification.LEFT_JOIN)
		   .add(Restrictions.eq("product.productSkuCode", sku))
		   .setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
		   if(criteria.list()!=null&&criteria.list().size()!=0)
		   {
		   seller=(Seller)criteria.list().get(0);
		   product=seller.getProducts().get(0);
		   currentValue=product.getQuantity();
		   if(currentInventory!=0)
		   {
			   product.setQuantity(currentInventory);
		   }
		   else if(quantoAdd!=0)
		   {
			   product.setQuantity(product.getQuantity()+quantoAdd);
			   product.getCategory().setProductCount(product.getCategory().getProductCount()+quantoAdd);
		   }
		   else if(quantoSub!=0)
		   {
			   System.out.println(" Subtracting quantity from product quantity : "+quantoSub);
			   product.setQuantity(product.getQuantity()-quantoSub);
			   product.getCategory().setProductCount(product.getCategory().getProductCount()-quantoSub);
				System.out.println(" Quantity after sub :"+product.getQuantity());  
		   }
		   
		   //Updating Closing stock for a month for each SKU
		   List<ProductStockList> stocklist=product.getClosingStocks();
		  if(stocklist!=null)
		 // Collections.sort(stocklist);
		  if(stocklist!=null&&stocklist.size()!=0&&stocklist.get(0).getMonth()==todayDate.getMonth()&&stocklist.get(0).getYear()==todayDate.getYear())
		  {
			  System.out.println(" No need to update stock list");
		  }
		  else
		  {
			  ProductStockList newObj=new ProductStockList();
			  newObj.setStockAvailable(currentValue);
			  newObj.setCreatedDate(todayDate);
			  newObj.setUpdatedate(todayDate.getDate());
			  newObj.setMonth(todayDate.getMonth());
			  newObj.setYear(todayDate.getYear());
			  product.getClosingStocks().add(newObj);
		  }
		   
		  //Updating Closing stock for a month for each product Category
		   Category productcat=product.getCategory();
		   if(productcat!=null)
		   {
		   if(productcat.getOsUpdate()!=null)
			 {
				 if(productcat.getOsUpdate().getMonth()<todayDate.getMonth())
						 {
					 productcat.setOpeningStock(productcat.getProductCount());
					 productcat.setOsUpdate(todayDate);
						 }
				 else if(productcat.getOsUpdate().getMonth()==12)
				 {
					 if(todayDate.getMonth()==1)
					 {
						 productcat.setOpeningStock(productcat.getProductCount());
						 productcat.setOsUpdate(todayDate);
						
					 }
				 }
			 }
		   else
		   {
			   productcat.setOpeningStock(productcat.getProductCount());
				 productcat.setOsUpdate(todayDate);
		   }
		   session.saveOrUpdate(productcat);
		   }
		   session.saveOrUpdate(product);
		   }
		   else
		   {
			   throw new NullPointerException();
		   }
		  /* session.getTransaction().commit();
		   session.close();*/
		}
		catch(Exception e)
		{
			System.out.println(" Exception in updating inventories  :"+e.getMessage());
			e.printStackTrace();
			throw e;
		}
		finally
		{
			if(status)
			{
				System.out.println(" Commiting inventory update");
			session.getTransaction().commit();
			   session.close();
			   }
		}
	
	}

}