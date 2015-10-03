package com.goeasy.helper;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;

import com.goeasy.bean.OrderBean;
import com.goeasy.model.Order;




public class FillManager {

	/**
	 * Fills the report with content
	 * 
	 * @param worksheet
	 * @param startRowIndex starting row offset
	 * @param startColIndex starting column offset
	 * @param datasource the data source
	 */
	public static void fillReport(HSSFSheet worksheet, int startRowIndex, int startColIndex, List<Order> datasource , String[] headers) {
		// Row offset
		startRowIndex += 2;
		
		// Create cell style for the body
		HSSFCellStyle bodyCellStyle = worksheet.getWorkbook().createCellStyle();
		bodyCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		bodyCellStyle.setWrapText(true);
		
		
		System.out.println(" Insidee fill manage : datasource size : "+datasource.size());
		System.out.println(" Start ro index : "+startRowIndex);
		System.out.println(" header length  : "+headers.length);
		System.out.println(" headers  : "+headers);
		// Create body
		for (int i=startRowIndex; i+startRowIndex-2< datasource.size()+2; i++) {
			// Create a new row
			HSSFRow row = worksheet.createRow((short) i+1);
			// Retrieve the id value
			for(int j=0;j<headers.length;j++)
			{
				if(headers[j].equals("orderId"))
				{
					HSSFCell cell = row.createCell(startColIndex+j);
					cell.setCellValue(datasource.get(i-2).getChannelOrderID());
					cell.setCellStyle(bodyCellStyle);
				}
				else if(headers[j].equals("invoiceId"))
				{
					HSSFCell cell = row.createCell(startColIndex+j);
					cell.setCellValue(datasource.get(i-2).getInvoiceID());
					cell.setCellStyle(bodyCellStyle);
				}
				else if(headers[j].equals("Partner"))
				{
					HSSFCell cell = row.createCell(startColIndex+j);
					cell.setCellValue(datasource.get(i-2).getPcName());
					cell.setCellStyle(bodyCellStyle);
				}
				else if(headers[j].equals("recievedDate"))
				{
					HSSFCell cell = row.createCell(startColIndex+j);
					cell.setCellValue(datasource.get(i-2).getOrderDate());
					cell.setCellStyle(bodyCellStyle);
				}
				else if(headers[j].equals("status"))
				{
					HSSFCell cell = row.createCell(startColIndex+j);
					cell.setCellValue(datasource.get(i-2).getStatus());
					cell.setCellStyle(bodyCellStyle);
				}
				else if(headers[j].equals("shippedDate"))
				{
					HSSFCell cell = row.createCell(startColIndex+j);
					cell.setCellValue(datasource.get(i-2).getShippedDate());
					cell.setCellStyle(bodyCellStyle);
				}
				else if(headers[j].equals("payCycle"))
				{
					HSSFCell cell = row.createCell(startColIndex+j);
					cell.setCellValue(datasource.get(i-2).getOrderPayment().getPaymentCycle());
					cell.setCellStyle(bodyCellStyle);
				}
				else if(headers[j].equals("finalStatus"))
				{
					HSSFCell cell = row.createCell(startColIndex+j);
					cell.setCellValue(datasource.get(i-2).getFinalStatus());
					cell.setCellStyle(bodyCellStyle);
				}
				else if(headers[j].equals("payDate"))
				{
					HSSFCell cell = row.createCell(startColIndex+j);
					if(datasource.get(i-2).getOrderPayment().getDateofPayment()!=null)
					cell.setCellValue(datasource.get(i-2).getOrderPayment().getDateofPayment());
					else
					cell.setCellValue("");	
					cell.setCellStyle(bodyCellStyle);
				}
				else if(headers[j].equals("netPayment"))
				{
					HSSFCell cell = row.createCell(startColIndex+j);
					cell.setCellValue(datasource.get(i-2).getOrderPayment().getNetPaymentResult());
					cell.setCellStyle(bodyCellStyle);
				}
				else if(headers[j].equals("payDiff"))
				{
					HSSFCell cell = row.createCell(startColIndex+j);
					cell.setCellValue(datasource.get(i-2).getOrderPayment().getPaymentDifference());
					cell.setCellStyle(bodyCellStyle);
				}
				
			}
		
		}
	}
}
