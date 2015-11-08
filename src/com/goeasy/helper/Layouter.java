package com.goeasy.helper;

import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

/**
 * Builds the report layout, the template, the design, the pattern 
 * 
 * @author Deep Mehrotra
 */
public class Layouter {

	private static Logger logger = Logger.getLogger("service");
	
	/**
	 * Builds the report layout. 
	 * <p>
	 * This doesn't have any data yet. This is your template.
	 */
	public static void buildReport(HSSFSheet worksheet, int startRowIndex, int startColIndex , String sheetName) {
		// Set column widths
		worksheet.setColumnWidth(0, 5000);
		worksheet.setColumnWidth(1, 5000);
		worksheet.setColumnWidth(2, 5000);
		worksheet.setColumnWidth(3, 5000);
		worksheet.setColumnWidth(4, 5000);
		worksheet.setColumnWidth(5, 5000);
		
		// Build the title and date headers
		buildTitle(worksheet, startRowIndex, startColIndex,sheetName);
		// Build the column headers
		if(sheetName.equalsIgnoreCase("Order Report"))
		buildHeaders(worksheet, startRowIndex, startColIndex);
		else if(sheetName.equalsIgnoreCase("ProductReport"))
		{
			buildProductHeaders(worksheet, startRowIndex, startColIndex);
		}
		else if(sheetName.equalsIgnoreCase("PaymentReport"))
		{
			buildPaymentHeaders(worksheet, startRowIndex, startColIndex);
		}
		else if(sheetName.equalsIgnoreCase("InventoryReport"))
		{
			buildInventoryHeaders(worksheet, startRowIndex, startColIndex);
		}
		else if(sheetName.equalsIgnoreCase("OrderReturnReport"))
		{
			buildReturnHeaders(worksheet, startRowIndex, startColIndex);
		}
		else if(sheetName.equalsIgnoreCase("OrderPOSheet"))
		{
			buildOrderPOHeaders(worksheet, startRowIndex, startColIndex);
		}
		else if(sheetName.equalsIgnoreCase("DebitNoteSheet"))
		{
			buildDebitNoteHeaders(worksheet, startRowIndex, startColIndex);
		}
		else if(sheetName.equalsIgnoreCase("POPaymentSheet"))
		{
			buildPOPaymentHeaders(worksheet, startRowIndex, startColIndex);
		}
		else if(sheetName.equalsIgnoreCase("ExpenseSheet"))
		{
			buildPExpenseHeaders(worksheet, startRowIndex, startColIndex);
		}
	}
	
	
	public static void buildOrderReport(HSSFSheet worksheet, int startRowIndex, int startColIndex , String sheetName ,String[] headers) {
		// Set column widths
		worksheet.setColumnWidth(0, 5000);
		worksheet.setColumnWidth(1, 5000);
		worksheet.setColumnWidth(2, 5000);
		worksheet.setColumnWidth(3, 5000);
		worksheet.setColumnWidth(4, 5000);
		worksheet.setColumnWidth(5, 5000);
		
		// Build the title and date headers
		buildTitle(worksheet, startRowIndex, startColIndex,sheetName);
		// Build the column headers
		buildReportHeaders(worksheet,headers,startRowIndex, startColIndex);
		
	}

	
	public static void buildReportHeaders(HSSFSheet worksheet, String[] headers,int startRowIndex, int startColIndex) {
		// Create font style for the headers
		Font font = worksheet.getWorkbook().createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // Create cell style for the headers
		HSSFCellStyle headerCellStyle = worksheet.getWorkbook().createCellStyle();
		headerCellStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
		headerCellStyle.setFillPattern(CellStyle.FINE_DOTS);
		headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		headerCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		headerCellStyle.setWrapText(true);
		headerCellStyle.setFont(font);
		headerCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		
		
		// Create the column headers
		HSSFRow rowHeader = worksheet.createRow((short) startRowIndex +2);
		rowHeader.setHeight((short) 500);
		int i=0;
		for(String headervalue:headers)
		{
		HSSFCell cell = rowHeader.createCell(startColIndex+ i++);
		cell.setCellValue(headervalue);
		cell.setCellStyle(headerCellStyle);
		
		}
	}
	/**
	 * Builds the report title and the date header
	 * 
	 * @param worksheet
	 * @param startRowIndex starting row offset
	 * @param startColIndex starting column offset
	 */
	@SuppressWarnings("deprecation")
	public static void buildTitle(HSSFSheet worksheet, int startRowIndex, int startColIndex, String sheetName) {
		// Create font style for the report title
		Font fontTitle = worksheet.getWorkbook().createFont();
		fontTitle.setBoldweight(Font.BOLDWEIGHT_BOLD);
		fontTitle.setFontHeight((short) 280);
		
        // Create cell style for the report title
        HSSFCellStyle cellStyleTitle = worksheet.getWorkbook().createCellStyle();
        cellStyleTitle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyleTitle.setWrapText(true);
        cellStyleTitle.setFont(fontTitle);
		
        // Create report title
		HSSFRow rowTitle = worksheet.createRow((short) startRowIndex);
		rowTitle.setHeight((short) 500);
		HSSFCell cellTitle = rowTitle.createCell(startColIndex);
		cellTitle.setCellValue(sheetName);
		cellTitle.setCellStyle(cellStyleTitle);
		
		// Create merged region for the report title
		worksheet.addMergedRegion(new CellRangeAddress(0,0,0,5));
		
		// Create date header
		HSSFRow dateTitle = worksheet.createRow((short) startRowIndex +1);
		HSSFCell cellDate = dateTitle.createCell(startColIndex);
		cellDate.setCellValue("This report was generated at " + new Date());
	}
	
	/**
	 * Builds the column headers
	 * 
	 * @param worksheet
	 * @param startRowIndex starting row offset
	 * @param startColIndex starting column offset
	 */
	public static void buildHeaders(HSSFSheet worksheet, int startRowIndex, int startColIndex) {
		// Create font style for the headers
		Font font = worksheet.getWorkbook().createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // Create cell style for the headers
		HSSFCellStyle headerCellStyle = worksheet.getWorkbook().createCellStyle();
		headerCellStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
		headerCellStyle.setFillPattern(CellStyle.FINE_DOTS);
		headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		headerCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		headerCellStyle.setWrapText(true);
		headerCellStyle.setFont(font);
		headerCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		
		// Create the column headers
		HSSFRow rowHeader = worksheet.createRow((short) startRowIndex +2);
		rowHeader.setHeight((short) 500);
		
		HSSFCell cell1 = rowHeader.createCell(startColIndex+0);
		cell1.setCellValue("ChannelOrderID");
		cell1.setCellStyle(headerCellStyle);
		

		HSSFCell cell2 = rowHeader.createCell(startColIndex+1);
		cell2.setCellValue("OrderRecievedDate");
		cell2.setCellStyle(headerCellStyle);

		HSSFCell cell3 = rowHeader.createCell(startColIndex+2);
		cell3.setCellValue("SkUCode");
		cell3.setCellStyle(headerCellStyle);

		HSSFCell cell4 = rowHeader.createCell(startColIndex+3);
		cell4.setCellValue("Partner");
		cell4.setCellStyle(headerCellStyle);
		

		HSSFCell cell5 = rowHeader.createCell(startColIndex+4);
		cell5.setCellValue("Customer Name");
		cell5.setCellStyle(headerCellStyle);
	

		HSSFCell cell6 = rowHeader.createCell(startColIndex+5);
		cell6.setCellValue("Payment Type");
		cell6.setCellStyle(headerCellStyle);
		

		HSSFCell cell7 = rowHeader.createCell(startColIndex+6);
		cell7.setCellValue("AWB No.");
		cell7.setCellStyle(headerCellStyle);
	
		HSSFCell cell8 = rowHeader.createCell(startColIndex+7);
		cell8.setCellValue("InvoiceID");
		cell8.setCellStyle(headerCellStyle);
		
		HSSFCell cell9 = rowHeader.createCell(startColIndex+8);
		cell9.setCellValue("subOrderID");
		cell9.setCellStyle(headerCellStyle);
		
		HSSFCell cell10 = rowHeader.createCell(startColIndex+9);
		cell10.setCellValue("PIreferenceNo");
		cell10.setCellStyle(headerCellStyle);
		
		HSSFCell cell11 = rowHeader.createCell(startColIndex+10);
		cell11.setCellValue("Logistic Partner");
		cell11.setCellStyle(headerCellStyle);
		
		HSSFCell cell12 = rowHeader.createCell(startColIndex+11);
		cell12.setCellValue("Order MRP");
		cell12.setCellStyle(headerCellStyle);
		
		HSSFCell cell13 = rowHeader.createCell(startColIndex+12);
		cell13.setCellValue("Order SP");
		cell13.setCellStyle(headerCellStyle);
		
		HSSFCell cell14 = rowHeader.createCell(startColIndex+13);
		cell14.setCellValue("Shipping Charges");
		cell14.setCellStyle(headerCellStyle);
		
		HSSFCell cell15 = rowHeader.createCell(startColIndex+14);
		cell15.setCellValue("Shipped Date");
		cell15.setCellStyle(headerCellStyle);
		
		HSSFCell cell16 = rowHeader.createCell(startColIndex+15);
		cell16.setCellValue("Delivery Date");
		cell16.setCellStyle(headerCellStyle);

		
		HSSFCell cell17 = rowHeader.createCell(startColIndex+16);
		cell17.setCellValue("Quantity");
		cell17.setCellStyle(headerCellStyle);
		
		HSSFCell cell18 = rowHeader.createCell(startColIndex+17);
		cell18.setCellValue("Net Rate");
		cell18.setCellStyle(headerCellStyle);
		
		HSSFCell cell19 = rowHeader.createCell(startColIndex+18);
		cell19.setCellValue("Customer Email");
		cell19.setCellStyle(headerCellStyle);
		
		HSSFCell cell20 = rowHeader.createCell(startColIndex+19);
		cell20.setCellValue("Customer Phone No");
		cell20.setCellStyle(headerCellStyle);
		
		HSSFCell cell21 = rowHeader.createCell(startColIndex+20);
		cell21.setCellValue("Customer City");
		cell21.setCellStyle(headerCellStyle);
		
		HSSFCell cell22 = rowHeader.createCell(startColIndex+21);
		cell22.setCellValue("Customer Address");
		cell22.setCellStyle(headerCellStyle);
		
		HSSFCell cell23 = rowHeader.createCell(startColIndex+22);
		cell23.setCellValue("Tax Category");
		cell23.setCellStyle(headerCellStyle);
		
		HSSFCell cell24 = rowHeader.createCell(startColIndex+23);
		cell24.setCellValue("Seller Notes");
		cell24.setCellStyle(headerCellStyle);
		
		
		
	
	}
	
	/**
	 * Builds the column headers
	 * 
	 * @param worksheet
	 * @param startRowIndex starting row offset
	 * @param startColIndex starting column offset
	 */
	public static void buildProductHeaders(HSSFSheet worksheet, int startRowIndex, int startColIndex) {
		// Create font style for the headers
		Font font = worksheet.getWorkbook().createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // Create cell style for the headers
		HSSFCellStyle headerCellStyle = worksheet.getWorkbook().createCellStyle();
		headerCellStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
		headerCellStyle.setFillPattern(CellStyle.FINE_DOTS);
		headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		headerCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		headerCellStyle.setWrapText(true);
		headerCellStyle.setFont(font);
		headerCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		
		// Create the column headers
		HSSFRow rowHeader = worksheet.createRow((short) startRowIndex +2);
		rowHeader.setHeight((short) 500);
		
		HSSFCell cell1 = rowHeader.createCell(startColIndex+0);
		cell1.setCellValue("ProductName");
		cell1.setCellStyle(headerCellStyle);

		HSSFCell cell2 = rowHeader.createCell(startColIndex+1);
		cell2.setCellValue("SkUCode");
		cell2.setCellStyle(headerCellStyle);

		HSSFCell cell3 = rowHeader.createCell(startColIndex+2);
		cell3.setCellValue("Category");
		cell3.setCellStyle(headerCellStyle);

		HSSFCell cell4 = rowHeader.createCell(startColIndex+3);
		cell4.setCellValue("ProductPrice");
		cell4.setCellStyle(headerCellStyle);
	
		HSSFCell cell5 = rowHeader.createCell(startColIndex+4);
		cell5.setCellValue("Quantity");
		cell5.setCellStyle(headerCellStyle);
		
		HSSFCell cell6 = rowHeader.createCell(startColIndex+5);
		cell6.setCellValue("Threshold Limit");
		cell6.setCellStyle(headerCellStyle);
		
		HSSFCell cell7 = rowHeader.createCell(startColIndex+6);
		cell7.setCellValue("ChanelSKU(Separated by ;)");
		cell7.setCellStyle(headerCellStyle);
		
	
	}
	
	/**
	 * Builds the column headers
	 * 
	 * @param worksheet
	 * @param startRowIndex starting row offset
	 * @param startColIndex starting column offset
	 */
	public static void buildPaymentHeaders(HSSFSheet worksheet, int startRowIndex, int startColIndex) {
		// Create font style for the headers
		Font font = worksheet.getWorkbook().createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // Create cell style for the headers
		HSSFCellStyle headerCellStyle = worksheet.getWorkbook().createCellStyle();
		headerCellStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
		headerCellStyle.setFillPattern(CellStyle.FINE_DOTS);
		headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		headerCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		headerCellStyle.setWrapText(true);
		headerCellStyle.setFont(font);
		headerCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		
		// Create the column headers
		HSSFRow rowHeader = worksheet.createRow((short) startRowIndex +2);
		rowHeader.setHeight((short) 500);
		
		HSSFCell cell1 = rowHeader.createCell(startColIndex+0);
		cell1.setCellValue("ChannelOrderId");
		cell1.setCellStyle(headerCellStyle);

		HSSFCell cell2 = rowHeader.createCell(startColIndex+1);
		cell2.setCellValue("InvoiceId");
		cell2.setCellStyle(headerCellStyle);

		HSSFCell cell3 = rowHeader.createCell(startColIndex+2);
		cell3.setCellValue("SKUCode");
		cell3.setCellStyle(headerCellStyle);

		HSSFCell cell4 = rowHeader.createCell(startColIndex+3);
		cell4.setCellValue("Recieved Amount");
		cell4.setCellStyle(headerCellStyle);

		HSSFCell cell5 = rowHeader.createCell(startColIndex+4);
		cell5.setCellValue("Negative Charges");
		cell5.setCellStyle(headerCellStyle);
	
		HSSFCell cell6 = rowHeader.createCell(startColIndex+5);
		cell6.setCellValue("Payment Date");
		cell6.setCellStyle(headerCellStyle);
		
	}
	
	public static void buildInventoryHeaders(HSSFSheet worksheet, int startRowIndex, int startColIndex) {
		// Create font style for the headers
		Font font = worksheet.getWorkbook().createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // Create cell style for the headers
		HSSFCellStyle headerCellStyle = worksheet.getWorkbook().createCellStyle();
		headerCellStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
		headerCellStyle.setFillPattern(CellStyle.FINE_DOTS);
		headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		headerCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		headerCellStyle.setWrapText(true);
		headerCellStyle.setFont(font);
		headerCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		
		// Create the column headers
		HSSFRow rowHeader = worksheet.createRow((short) startRowIndex +2);
		rowHeader.setHeight((short) 500);
		
		HSSFCell cell1 = rowHeader.createCell(startColIndex+0);
		cell1.setCellValue("SkUCode");
		cell1.setCellStyle(headerCellStyle);

		HSSFCell cell2 = rowHeader.createCell(startColIndex+1);
		cell2.setCellValue("CurrentQuantity");
		cell2.setCellStyle(headerCellStyle);

		HSSFCell cell3 = rowHeader.createCell(startColIndex+2);
		cell3.setCellValue("Quantity to Add");
		cell3.setCellStyle(headerCellStyle);

		HSSFCell cell4 = rowHeader.createCell(startColIndex+3);
		cell4.setCellValue("Quantity to Substract");
		cell4.setCellStyle(headerCellStyle);

	}
	
	
	public static void buildReturnHeaders(HSSFSheet worksheet, int startRowIndex, int startColIndex) {
		// Create font style for the headers
		Font font = worksheet.getWorkbook().createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // Create cell style for the headers
		HSSFCellStyle headerCellStyle = worksheet.getWorkbook().createCellStyle();
		headerCellStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
		headerCellStyle.setFillPattern(CellStyle.FINE_DOTS);
		headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		headerCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		headerCellStyle.setWrapText(true);
		headerCellStyle.setFont(font);
		headerCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		
		// Create the column headers
		HSSFRow rowHeader = worksheet.createRow((short) startRowIndex +2);
		rowHeader.setHeight((short) 500);
		
		HSSFCell cell1 = rowHeader.createCell(startColIndex+0);
		cell1.setCellValue("ChannelOrderId");
		cell1.setCellStyle(headerCellStyle);

		HSSFCell cell2 = rowHeader.createCell(startColIndex+1);
		cell2.setCellValue("AWBNo");
		cell2.setCellStyle(headerCellStyle);

		HSSFCell cell3 = rowHeader.createCell(startColIndex+2);
		cell3.setCellValue("Invoice ID");
		cell3.setCellStyle(headerCellStyle);

		HSSFCell cell4 = rowHeader.createCell(startColIndex+3);
		cell4.setCellValue("SKU Code");
		cell4.setCellStyle(headerCellStyle);

		HSSFCell cell5 = rowHeader.createCell(startColIndex+4);
		cell5.setCellValue("Return/RTO Id");
		cell5.setCellStyle(headerCellStyle);
		
		HSSFCell cell6 = rowHeader.createCell(startColIndex+5);
		cell6.setCellValue(" Reason");
		cell6.setCellStyle(headerCellStyle);
		
		HSSFCell cell7 = rowHeader.createCell(startColIndex+6);
		cell7.setCellValue("Date");
		cell7.setCellStyle(headerCellStyle);
		
		HSSFCell cell8 = rowHeader.createCell(startColIndex+7);
		cell8.setCellValue("Status");
		cell8.setCellStyle(headerCellStyle);
		
		HSSFCell cell9 = rowHeader.createCell(startColIndex+8);
		cell9.setCellValue("Quantity");
		cell9.setCellStyle(headerCellStyle);
		
		HSSFCell cell10 = rowHeader.createCell(startColIndex+9);
		cell10.setCellValue("Return Charge");
		cell10.setCellStyle(headerCellStyle);
	}
	
	public static void buildOrderPOHeaders(HSSFSheet worksheet, int startRowIndex, int startColIndex) {
		// Create font style for the headers
		Font font = worksheet.getWorkbook().createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // Create cell style for the headers
		HSSFCellStyle headerCellStyle = worksheet.getWorkbook().createCellStyle();
		headerCellStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
		headerCellStyle.setFillPattern(CellStyle.FINE_DOTS);
		headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		headerCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		headerCellStyle.setWrapText(true);
		headerCellStyle.setFont(font);
		headerCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		
		// Create the column headers
		HSSFRow rowHeader = worksheet.createRow((short) startRowIndex +2);
		rowHeader.setHeight((short) 500);
		
		HSSFCell cell1 = rowHeader.createCell(startColIndex+0);
		cell1.setCellValue("POOrderID");
		cell1.setCellStyle(headerCellStyle);
		

		HSSFCell cell2 = rowHeader.createCell(startColIndex+1);
		cell2.setCellValue("Order Recieved Date");
		cell2.setCellStyle(headerCellStyle);

		HSSFCell cell3 = rowHeader.createCell(startColIndex+2);
		cell3.setCellValue("SkUCode");
		cell3.setCellStyle(headerCellStyle);

		HSSFCell cell4 = rowHeader.createCell(startColIndex+3);
		cell4.setCellValue("Partner");
		cell4.setCellStyle(headerCellStyle);
		

		HSSFCell cell5 = rowHeader.createCell(startColIndex+4);
		cell5.setCellValue("Seal No.");
		cell5.setCellStyle(headerCellStyle);
	

		HSSFCell cell6 = rowHeader.createCell(startColIndex+5);
		cell6.setCellValue("InvoiceID");
		cell6.setCellStyle(headerCellStyle);
		

		HSSFCell cell7 = rowHeader.createCell(startColIndex+6);
		cell7.setCellValue("Order MRP");
		cell7.setCellStyle(headerCellStyle);
	
		HSSFCell cell8 = rowHeader.createCell(startColIndex+7);
		cell8.setCellValue("Order SP");
		cell8.setCellStyle(headerCellStyle);
		
		HSSFCell cell9 = rowHeader.createCell(startColIndex+8);
		cell9.setCellValue("Shipped Date");
		cell9.setCellStyle(headerCellStyle);
		
		HSSFCell cell10 = rowHeader.createCell(startColIndex+9);
		cell10.setCellValue("Delivery Date");
		cell10.setCellStyle(headerCellStyle);
		
		HSSFCell cell11 = rowHeader.createCell(startColIndex+10);
		cell11.setCellValue("Quantity");
		cell11.setCellStyle(headerCellStyle);
		
		HSSFCell cell12 = rowHeader.createCell(startColIndex+11);
		cell12.setCellValue("Net Rate");
		cell12.setCellStyle(headerCellStyle);
		
		HSSFCell cell13 = rowHeader.createCell(startColIndex+12);
		cell13.setCellValue("PO Price");
		cell13.setCellStyle(headerCellStyle);
		
		HSSFCell cell14 = rowHeader.createCell(startColIndex+13);
		cell14.setCellValue("Seller Notes");
		cell14.setCellStyle(headerCellStyle);
		
		
		
	
	}
	
	public static void buildDebitNoteHeaders(HSSFSheet worksheet, int startRowIndex, int startColIndex) {
		// Create font style for the headers
		Font font = worksheet.getWorkbook().createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // Create cell style for the headers
		HSSFCellStyle headerCellStyle = worksheet.getWorkbook().createCellStyle();
		headerCellStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
		headerCellStyle.setFillPattern(CellStyle.FINE_DOTS);
		headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		headerCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		headerCellStyle.setWrapText(true);
		headerCellStyle.setFont(font);
		headerCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		
		// Create the column headers
		HSSFRow rowHeader = worksheet.createRow((short) startRowIndex +2);
		rowHeader.setHeight((short) 500);
		
		HSSFCell cell1 = rowHeader.createCell(startColIndex+0);
		cell1.setCellValue("POOrderID");
		cell1.setCellStyle(headerCellStyle);
		

		HSSFCell cell2 = rowHeader.createCell(startColIndex+1);
		cell2.setCellValue("Gate Pass Id");
		cell2.setCellStyle(headerCellStyle);

		HSSFCell cell3 = rowHeader.createCell(startColIndex+2);
		cell3.setCellValue("SkUCode");
		cell3.setCellStyle(headerCellStyle);

		HSSFCell cell4 = rowHeader.createCell(startColIndex+3);
		cell4.setCellValue("Partner");
		cell4.setCellStyle(headerCellStyle);
		

		HSSFCell cell5 = rowHeader.createCell(startColIndex+4);
		cell5.setCellValue("Invoice Id");
		cell5.setCellStyle(headerCellStyle);
	

		HSSFCell cell6 = rowHeader.createCell(startColIndex+5);
		cell6.setCellValue("Order Amount");
		cell6.setCellStyle(headerCellStyle);
		
		HSSFCell cell7 = rowHeader.createCell(startColIndex+6);
		cell7.setCellValue("Quantity");
		cell7.setCellStyle(headerCellStyle);
		
		HSSFCell cell8 = rowHeader.createCell(startColIndex+7);
		cell8.setCellValue("Return Date");
		cell8.setCellStyle(headerCellStyle);
		
		HSSFCell cell9 = rowHeader.createCell(startColIndex+8);
		cell9.setCellValue("Return Reason");
		cell9.setCellStyle(headerCellStyle);
		

		
	}
	
	public static void buildPOPaymentHeaders(HSSFSheet worksheet, int startRowIndex, int startColIndex) {
		// Create font style for the headers
		Font font = worksheet.getWorkbook().createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // Create cell style for the headers
		HSSFCellStyle headerCellStyle = worksheet.getWorkbook().createCellStyle();
		headerCellStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
		headerCellStyle.setFillPattern(CellStyle.FINE_DOTS);
		headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		headerCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		headerCellStyle.setWrapText(true);
		headerCellStyle.setFont(font);
		headerCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		
		// Create the column headers
		HSSFRow rowHeader = worksheet.createRow((short) startRowIndex +2);
		rowHeader.setHeight((short) 500);
		
		HSSFCell cell1 = rowHeader.createCell(startColIndex+0);
		cell1.setCellValue("POOrderId");
		cell1.setCellStyle(headerCellStyle);

		HSSFCell cell2 = rowHeader.createCell(startColIndex+1);
		cell2.setCellValue("InvoiceID");
		cell2.setCellStyle(headerCellStyle);

		HSSFCell cell3 = rowHeader.createCell(startColIndex+2);
		cell3.setCellValue("Partner");
		cell3.setCellStyle(headerCellStyle);

		HSSFCell cell4 = rowHeader.createCell(startColIndex+3);
		cell4.setCellValue("Gate Pass Id");
		cell4.setCellStyle(headerCellStyle);

		HSSFCell cell5 = rowHeader.createCell(startColIndex+4);
		cell5.setCellValue("Positive Amount");
		cell5.setCellStyle(headerCellStyle);
	
		HSSFCell cell6 = rowHeader.createCell(startColIndex+5);
		cell6.setCellValue("Negative Amount");
		cell6.setCellStyle(headerCellStyle);
		
		HSSFCell cell7 = rowHeader.createCell(startColIndex+6);
		cell7.setCellValue("Payment Date");
		cell7.setCellStyle(headerCellStyle);
		
		HSSFCell cell8 = rowHeader.createCell(startColIndex+7);
		cell8.setCellValue("Quantity");
		cell8.setCellStyle(headerCellStyle);
		
	
	}
	
	public static void buildPExpenseHeaders(HSSFSheet worksheet, int startRowIndex, int startColIndex) {
		// Create font style for the headers
		Font font = worksheet.getWorkbook().createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // Create cell style for the headers
		HSSFCellStyle headerCellStyle = worksheet.getWorkbook().createCellStyle();
		headerCellStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
		headerCellStyle.setFillPattern(CellStyle.FINE_DOTS);
		headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		headerCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		headerCellStyle.setWrapText(true);
		headerCellStyle.setFont(font);
		headerCellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		
		// Create the column headers
		HSSFRow rowHeader = worksheet.createRow((short) startRowIndex +2);
		rowHeader.setHeight((short) 500);
		
		HSSFCell cell1 = rowHeader.createCell(startColIndex+0);
		cell1.setCellValue("Name");
		cell1.setCellStyle(headerCellStyle);

		HSSFCell cell2 = rowHeader.createCell(startColIndex+1);
		cell2.setCellValue("Description");
		cell2.setCellStyle(headerCellStyle);

		HSSFCell cell3 = rowHeader.createCell(startColIndex+2);
		cell3.setCellValue("Expense Category");
		cell3.setCellStyle(headerCellStyle);

		HSSFCell cell4 = rowHeader.createCell(startColIndex+3);
		cell4.setCellValue("Expense Amount");
		cell4.setCellStyle(headerCellStyle);

		HSSFCell cell5 = rowHeader.createCell(startColIndex+4);
		cell5.setCellValue("Expenditure By");
		cell5.setCellStyle(headerCellStyle);
	
		HSSFCell cell6 = rowHeader.createCell(startColIndex+5);
		cell6.setCellValue("Paid To");
		cell6.setCellStyle(headerCellStyle);
		
		}
	
}
