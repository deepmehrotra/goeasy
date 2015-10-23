package com.goeasy.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goeasy.helper.Layouter;
import com.goeasy.helper.Writer;


/**
 * Service for processing Apache POI-based reports
 * 
 * @author 'Deep Mehrotra
 */
@Service("downloadService")
@Transactional
public class DownloadService {

	
	
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
	
	public void downloadXLS(HttpServletResponse response) throws ClassNotFoundException {
		
		
		// 1. Create new workbook
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		// 2. Create new worksheet
		HSSFSheet worksheet = workbook.createSheet("OrderReport");
		
		// 3. Define starting indices for rows and columns
		int startRowIndex = 0;
		int startColIndex = 0;
		
		// 4. Build layout 
		// Build title, date, and column headers
		Layouter.buildReport(worksheet, startRowIndex, startColIndex,"Order Report");

		/*// 5. Fill report
		FillManager.fillReport(worksheet, startRowIndex, startColIndex, getDatasource());
		*/
		// 6. Set the response properties
		String fileName = "OrderReport.xls";
		response.setHeader("Content-Disposition", "inline; filename=" + fileName);
		// Make sure to set the correct content type
		response.setContentType("application/vnd.ms-excel");
		
		//7. Write to the output stream
		Writer.write(response, worksheet);
	}
	
	
	public void downloadProductXLS(HttpServletResponse response) throws ClassNotFoundException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet worksheet = workbook.createSheet("ProductReport");
		int startRowIndex = 0;
		int startColIndex = 0;
		Layouter.buildReport(worksheet, startRowIndex, startColIndex , "ProductReport");
		String fileName = "ProductReport.xls";
		response.setHeader("Content-Disposition", "inline; filename=" + fileName);
		response.setContentType("application/vnd.ms-excel");
		Writer.write(response, worksheet);
	}
	
	public void downloadOrderPOXLS(HttpServletResponse response) throws ClassNotFoundException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet worksheet = workbook.createSheet("OrderPOSheet");
		int startRowIndex = 0;
		int startColIndex = 0;
		Layouter.buildReport(worksheet, startRowIndex, startColIndex , "OrderPOSheet");
		String fileName = "OrderPOSheet.xls";
		response.setHeader("Content-Disposition", "inline; filename=" + fileName);
		response.setContentType("application/vnd.ms-excel");
		Writer.write(response, worksheet);
	}
	
	public void downloadPaymentXLS(HttpServletResponse response) throws ClassNotFoundException {
		
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet worksheet = workbook.createSheet("PaymentReport");
		int startRowIndex = 0;
		int startColIndex = 0;
		Layouter.buildReport(worksheet, startRowIndex, startColIndex , "PaymentReport");
		String fileName = "PaymentReport.xls";
		response.setHeader("Content-Disposition", "inline; filename=" + fileName);
		response.setContentType("application/vnd.ms-excel");
		Writer.write(response, worksheet);
	}
	
public void downloadInventoryXLS(HttpServletResponse response) throws ClassNotFoundException {
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet worksheet = workbook.createSheet("InventoryReport");
		int startRowIndex = 0;
		int startColIndex = 0;
		Layouter.buildReport(worksheet, startRowIndex, startColIndex , "InventoryReport");
		String fileName = "InventoryReport.xls";
		response.setHeader("Content-Disposition", "inline; filename=" + fileName);
		response.setContentType("application/vnd.ms-excel");
		Writer.write(response, worksheet);
	}

public void downloadReturnXLS(HttpServletResponse response) throws ClassNotFoundException {
	HSSFWorkbook workbook = new HSSFWorkbook();
	HSSFSheet worksheet = workbook.createSheet("OrderReturnReport");
	int startRowIndex = 0;
	int startColIndex = 0;
	Layouter.buildReport(worksheet, startRowIndex, startColIndex , "OrderReturnReport");
	String fileName = "ReturnReport.xls";
	response.setHeader("Content-Disposition", "inline; filename=" + fileName);
	response.setContentType("application/vnd.ms-excel");
	Writer.write(response, worksheet);
}

public void downloadDebitNoteXLS(HttpServletResponse response) throws ClassNotFoundException {
	HSSFWorkbook workbook = new HSSFWorkbook();
	HSSFSheet worksheet = workbook.createSheet("DebitNoteSheet");
	int startRowIndex = 0;
	int startColIndex = 0;
	Layouter.buildReport(worksheet, startRowIndex, startColIndex , "DebitNoteSheet");
	String fileName = "DebitNoteSheet.xls";
	response.setHeader("Content-Disposition", "inline; filename=" + fileName);
	response.setContentType("application/vnd.ms-excel");
	Writer.write(response, worksheet);
}

public void downloadPOPaymentXLS(HttpServletResponse response) throws ClassNotFoundException {
	HSSFWorkbook workbook = new HSSFWorkbook();
	HSSFSheet worksheet = workbook.createSheet("POPaymentSheet");
	int startRowIndex = 0;
	int startColIndex = 0;
	Layouter.buildReport(worksheet, startRowIndex, startColIndex , "POPaymentSheet");
	String fileName = "POPaymentSheet.xls";
	response.setHeader("Content-Disposition", "inline; filename=" + fileName);
	response.setContentType("application/vnd.ms-excel");
	Writer.write(response, worksheet);
}

public void downloadExpensesXLS(HttpServletResponse response) throws ClassNotFoundException {
	HSSFWorkbook workbook = new HSSFWorkbook();
	HSSFSheet worksheet = workbook.createSheet("ExpenseSheet");
	int startRowIndex = 0;
	int startColIndex = 0;
	Layouter.buildReport(worksheet, startRowIndex, startColIndex , "ExpenseSheet");
	String fileName = "ExpenseSheet.xls";
	response.setHeader("Content-Disposition", "inline; filename=" + fileName);
	response.setContentType("application/vnd.ms-excel");
	Writer.write(response, worksheet);
}

}
