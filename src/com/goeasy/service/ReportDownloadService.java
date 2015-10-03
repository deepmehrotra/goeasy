
package com.goeasy.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goeasy.helper.FillManager;
import com.goeasy.helper.Layouter;
import com.goeasy.helper.Writer;
import com.goeasy.model.Order;


/**
 * Service for processing Apache POI-based reports
 * 
 * @author 'Deep Mehrotra
 */
@Service("reportDownloadService")
@Transactional
public class ReportDownloadService {

	
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Processes the download for Excel format.
	 * It does the following steps:
	 * <pre>1. Create new workbook
	 * 2. Create new worksheet
	 * 3. Define starting indices for rows and columns
	 * 4. Build layout 
	 * 5. Fill report
	 * 6. Set the HttpServletResponse properties
	 * 7. Write to the output stream
	 * </pre>
	 */
	
	public void downloadReport(HttpServletResponse response , List<Order> orderlist, String[] headers,String reportname ,int sellerId) throws ClassNotFoundException {
		
		
		// 1. Create new workbook
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		// 2. Create new worksheet
		HSSFSheet worksheet = workbook.createSheet(reportname);
		
		// 3. Define starting indices for rows and columns
		int startRowIndex = 0;
		int startColIndex = 0;
		
		// 4. Build layout 
		// Build title, date, and column headers
		Layouter.buildOrderReport(worksheet, startRowIndex, startColIndex,reportname,headers);

		// 5. Fill report
		FillManager.fillReport(worksheet, startRowIndex, startColIndex, orderlist , headers);
		
		// 6. Set the response properties
		String fileName = "OrderReport.xls";
		response.setHeader("Content-Disposition", "inline; filename=" + fileName);
		// Make sure to set the correct content type
		response.setContentType("application/vnd.ms-excel");
		
		//7. Write to the output stream
		Writer.write(response, worksheet);
	}
	

}