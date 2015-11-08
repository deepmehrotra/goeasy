package com.goeasy.bean;

import java.util.Deque;
import java.util.List;
import java.util.Map;


public class DashboardBean {
	
	private double profitThisYear;
	private double profitLastYear;
	private double percentChangeInProfit;
	private long saleQuantityThisYear;
	private long saleQuantityTLastYear;
	private double percentChangeInSQ;
	private long totalCustomers;
	private long totalStock;
	private long ordersThisMonth;
	private long ordersLastMonth;
	private long ordersToday;
	private long ordersYesterday;
	private long ordersThisYear;
	private long ordersLastYear;
	private double incomeThisYear;
	private double incomeLastYear;
	private double incomeThisMonth;
	private double incomeLastMonth;
	private double incomeToday;
	private double incomeYesterday;
	private Deque<Long> weeklyOrderCount;
	private Deque<Long> weeklyPaymentCount;
	private List<PendingPaymentsBean> pendingPayments;
	private Map<String,Long> topSellingSKU;
	private Map<String,Long> topSellingRegion;
	private Map<String,Double> expenditurePercentage;
	private Map<String,Double> profitLastSixMonth;
	private List<PendingPaymentsBean> taxorTDSAlerts;
	private Deque<Long> saleQuantity;
	private Deque<Long> returnQuantity;
	private Deque<Double> saleAmount;
	private Deque<Double> returnAmount;
	public double getProfitThisYear() {
		return profitThisYear;
	}
	public void setProfitThisYear(double profitThisYear) {
		this.profitThisYear = profitThisYear;
	}
	public double getProfitLastYear() {
		return profitLastYear;
	}
	public void setProfitLastYear(double profitLastYear) {
		this.profitLastYear = profitLastYear;
	}
	public double getPercentChangeInProfit() {
		return percentChangeInProfit;
	}
	public void setPercentChangeInProfit(double percentChangeInProfit) {
		this.percentChangeInProfit = percentChangeInProfit;
	}
	public long getSaleQuantityThisYear() {
		return saleQuantityThisYear;
	}
	public void setSaleQuantityThisYear(long saleQuantityThisYear) {
		this.saleQuantityThisYear = saleQuantityThisYear;
	}
	public long getSaleQuantityTLastYear() {
		return saleQuantityTLastYear;
	}
	public void setSaleQuantityTLastYear(long saleQuantityTLastYear) {
		this.saleQuantityTLastYear = saleQuantityTLastYear;
	}
	public double getPercentChangeInSQ() {
		return percentChangeInSQ;
	}
	public void setPercentChangeInSQ(double percentChangeInSQ) {
		this.percentChangeInSQ = percentChangeInSQ;
	}
	public long getTotalCustomers() {
		return totalCustomers;
	}
	public void setTotalCustomers(long totalCustomers) {
		this.totalCustomers = totalCustomers;
	}
	public long getTotalStock() {
		return totalStock;
	}
	public void setTotalStock(long totalStock) {
		this.totalStock = totalStock;
	}
	public long getOrdersThisMonth() {
		return ordersThisMonth;
	}
	public void setOrdersThisMonth(long ordersThisMonth) {
		this.ordersThisMonth = ordersThisMonth;
	}
	public long getOrdersLastMonth() {
		return ordersLastMonth;
	}
	public void setOrdersLastMonth(long ordersLastMonth) {
		this.ordersLastMonth = ordersLastMonth;
	}
	public long getOrdersToday() {
		return ordersToday;
	}
	public void setOrdersToday(long ordersToday) {
		this.ordersToday = ordersToday;
	}
	public long getOrdersYesterday() {
		return ordersYesterday;
	}
	public void setOrdersYesterday(long ordersYesterday) {
		this.ordersYesterday = ordersYesterday;
	}
	public long getOrdersThisYear() {
		return ordersThisYear;
	}
	public void setOrdersThisYear(long ordersThisYear) {
		this.ordersThisYear = ordersThisYear;
	}
	public long getOrdersLastYear() {
		return ordersLastYear;
	}
	public void setOrdersLastYear(long ordersLastYear) {
		this.ordersLastYear = ordersLastYear;
	}
	public double getIncomeThisYear() {
		return incomeThisYear;
	}
	public void setIncomeThisYear(double incomeThisYear) {
		this.incomeThisYear = incomeThisYear;
	}
	public double getIncomeLastYear() {
		return incomeLastYear;
	}
	public void setIncomeLastYear(double incomeLastYear) {
		this.incomeLastYear = incomeLastYear;
	}
	public double getIncomeThisMonth() {
		return incomeThisMonth;
	}
	public void setIncomeThisMonth(double incomeThisMonth) {
		this.incomeThisMonth = incomeThisMonth;
	}
	public double getIncomeLastMonth() {
		return incomeLastMonth;
	}
	public void setIncomeLastMonth(double incomeLastMonth) {
		this.incomeLastMonth = incomeLastMonth;
	}
	public double getIncomeToday() {
		return incomeToday;
	}
	public void setIncomeToday(double incomeToday) {
		this.incomeToday = incomeToday;
	}
	public double getIncomeYesterday() {
		return incomeYesterday;
	}
	public void setIncomeYesterday(double incomeYesterday) {
		this.incomeYesterday = incomeYesterday;
	}
	public Deque<Long> getWeeklyOrderCount() {
		return weeklyOrderCount;
	}
	public void setWeeklyOrderCount(Deque<Long> weeklyOrderCount) {
		this.weeklyOrderCount = weeklyOrderCount;
	}
	public Deque<Long> getWeeklyPaymentCount() {
		return weeklyPaymentCount;
	}
	public void setWeeklyPaymentCount(Deque<Long> weeklyPaymentCount) {
		this.weeklyPaymentCount = weeklyPaymentCount;
	}
	public List<PendingPaymentsBean> getPendingPayments() {
		return pendingPayments;
	}
	public void setPendingPayments(List<PendingPaymentsBean> pendingPayments) {
		this.pendingPayments = pendingPayments;
	}
	public Map<String, Long> getTopSellingSKU() {
		return topSellingSKU;
	}
	public void setTopSellingSKU(Map<String, Long> topSellingSKU) {
		this.topSellingSKU = topSellingSKU;
	}
	public Map<String, Long> getTopSellingRegion() {
		return topSellingRegion;
	}
	public void setTopSellingRegion(Map<String, Long> topSellingRegion) {
		this.topSellingRegion = topSellingRegion;
	}
	public Map<String, Double> getExpenditurePercentage() {
		return expenditurePercentage;
	}
	public void setExpenditurePercentage(Map<String, Double> expenditurePercentage) {
		this.expenditurePercentage = expenditurePercentage;
	}
	public Map<String, Double> getProfitLastSixMonth() {
		return profitLastSixMonth;
	}
	public void setProfitLastSixMonth(Map<String, Double> profitLastSixMonth) {
		this.profitLastSixMonth = profitLastSixMonth;
	}
	public List<PendingPaymentsBean> getTaxorTDSAlerts() {
		return taxorTDSAlerts;
	}
	public void setTaxorTDSAlerts(List<PendingPaymentsBean> taxorTDSAlerts) {
		this.taxorTDSAlerts = taxorTDSAlerts;
	}
	public Deque<Long> getSaleQuantity() {
		return saleQuantity;
	}
	public void setSaleQuantity(Deque<Long> saleQuantity) {
		this.saleQuantity = saleQuantity;
	}
	public Deque<Long> getReturnQuantity() {
		return returnQuantity;
	}
	public void setReturnQuantity(Deque<Long> returnQuantity) {
		this.returnQuantity = returnQuantity;
	}
	public Deque<Double> getSaleAmount() {
		return saleAmount;
	}
	public void setSaleAmount(Deque<Double> saleAmount) {
		this.saleAmount = saleAmount;
	}
	public Deque<Double> getReturnAmount() {
		return returnAmount;
	}
	public void setReturnAmount(Deque<Double> returnAmount) {
		this.returnAmount = returnAmount;
	}
	
	
}

