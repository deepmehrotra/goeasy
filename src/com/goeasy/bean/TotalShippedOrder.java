package com.goeasy.bean;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class TotalShippedOrder {
	
	
	private String pcName;
	private long saleQuantity;
	private double saleQuantityPercent;
	private long returnQuantity;
	private double returnQuantityPercent;
	private double nr;
	private double nrPercent;
	private double returnAmount;
	private double returnAmountPercent;
	private long netSaleQuantity;
	private double netSaleQuantityPercent;
	private double netSaleAmount;
	private double netSaleAmountPercent;
	private List<String> cities;
	private Map<String,Double> cityQuantity;
	private Map<String,Double> cityPercentage;
	private long noOfDeliveredOrder;
	private double deliveredOrderPercent;
	private long noOfReturnOrder;
	private double returnOrderPercent;
	private long noOfRTOOrder;
	private double RTOOrderPercent;
	private long noOfRTOLimitCrossed;
	private double RTOLimitCrossedPercent;
	private long noOfReturnLimitCrossed;
	private double returnLimitCrossedPercent;
	private long noOfActionableOrders;
	private double actionableOrdersPercent;
	private long noOfSettledOrders;
	private double settledOrdersPercent;
	private double netPaymentResult;
	private double netPaymentResultPercent;
	private double totalPaymentDiffference;
	private double paymentDiffferencePercent;
	public long getSaleQuantity() {
		return saleQuantity;
	}
	public void setSaleQuantity(long saleQuantity) {
		this.saleQuantity = saleQuantity;
	}
	public double getSaleQuantityPercent() {
		return saleQuantityPercent;
	}
	public void setSaleQuantityPercent(double saleQuantityPercent) {
		this.saleQuantityPercent = saleQuantityPercent;
	}
	public long getReturnQuantity() {
		return returnQuantity;
	}
	public void setReturnQuantity(long returnQuantity) {
		this.returnQuantity = returnQuantity;
	}
	public double getReturnQuantityPercent() {
		return returnQuantityPercent;
	}
	public void setReturnQuantityPercent(double returnQuantityPercent) {
		this.returnQuantityPercent = returnQuantityPercent;
	}
	public double getNr() {
		return nr;
	}
	public void setNr(double nr) {
		this.nr = nr;
	}
	public double getNrPercent() {
		return nrPercent;
	}
	public void setNrPercent(double nrPercent) {
		this.nrPercent = nrPercent;
	}
	public double getReturnAmount() {
		return returnAmount;
	}
	public void setReturnAmount(double returnAmount) {
		this.returnAmount = returnAmount;
	}
	public double getReturnAmountPercent() {
		return returnAmountPercent;
	}
	public void setReturnAmountPercent(double returnAmountPercent) {
		this.returnAmountPercent = returnAmountPercent;
	}
	public long getNetSaleQuantity() {
		return netSaleQuantity;
	}
	public void setNetSaleQuantity(long netSaleQuantity) {
		this.netSaleQuantity = netSaleQuantity;
	}
	public double getNetSaleQuantityPercent() {
		return netSaleQuantityPercent;
	}
	public void setNetSaleQuantityPercent(double netSaleQuantityPercent) {
		this.netSaleQuantityPercent = netSaleQuantityPercent;
	}
	public double getNetSaleAmount() {
		return netSaleAmount;
	}
	public void setNetSaleAmount(double netSaleAmount) {
		this.netSaleAmount = netSaleAmount;
	}
	public double getNetSaleAmountPercent() {
		return netSaleAmountPercent;
	}
	public void setNetSaleAmountPercent(double netSaleAmountPercent) {
		this.netSaleAmountPercent = netSaleAmountPercent;
	}
	public List<String> getCities() {
		return cities;
	}
	public void setCities(List<String> cities) {
		this.cities = cities;
	}
	public Map<String, Double> getCityQuantity() {
		return cityQuantity;
	}
	public void setCityQuantity(Map<String, Double> cityQuantity) {
		this.cityQuantity = cityQuantity;
	}
	public Map<String, Double> getCityPercentage() {
		return cityPercentage;
	}
	public void setCityPercentage(Map<String, Double> cityPercentage) {
		this.cityPercentage = cityPercentage;
	}
	public long getNoOfDeliveredOrder() {
		return noOfDeliveredOrder;
	}
	public void setNoOfDeliveredOrder(long noOfDeliveredOrder) {
		this.noOfDeliveredOrder = noOfDeliveredOrder;
	}
	public double getDeliveredOrderPercent() {
		return deliveredOrderPercent;
	}
	public void setDeliveredOrderPercent(double deliveredOrderPercent) {
		this.deliveredOrderPercent = deliveredOrderPercent;
	}
	public long getNoOfReturnOrder() {
		return noOfReturnOrder;
	}
	public void setNoOfReturnOrder(long noOfReturnOrder) {
		this.noOfReturnOrder = noOfReturnOrder;
	}
	public double getReturnOrderPercent() {
		return returnOrderPercent;
	}
	public void setReturnOrderPercent(double returnOrderPercent) {
		this.returnOrderPercent = returnOrderPercent;
	}
	public long getNoOfRTOOrder() {
		return noOfRTOOrder;
	}
	public void setNoOfRTOOrder(long noOfRTOOrder) {
		this.noOfRTOOrder = noOfRTOOrder;
	}
	public double getRTOOrderPercent() {
		return RTOOrderPercent;
	}
	public void setRTOOrderPercent(double rTOOrderPercent) {
		RTOOrderPercent = rTOOrderPercent;
	}
	public long getNoOfRTOLimitCrossed() {
		return noOfRTOLimitCrossed;
	}
	public void setNoOfRTOLimitCrossed(long noOfRTOLimitCrossed) {
		this.noOfRTOLimitCrossed = noOfRTOLimitCrossed;
	}
	public double getRTOLimitCrossedPercent() {
		return RTOLimitCrossedPercent;
	}
	public void setRTOLimitCrossedPercent(double rTOLimitCrossedPercent) {
		RTOLimitCrossedPercent = rTOLimitCrossedPercent;
	}
	public long getNoOfReturnLimitCrossed() {
		return noOfReturnLimitCrossed;
	}
	public void setNoOfReturnLimitCrossed(long noOfReturnLimitCrossed) {
		this.noOfReturnLimitCrossed = noOfReturnLimitCrossed;
	}
	public double getReturnLimitCrossedPercent() {
		return returnLimitCrossedPercent;
	}
	public void setReturnLimitCrossedPercent(double returnLimitCrossedPercent) {
		this.returnLimitCrossedPercent = returnLimitCrossedPercent;
	}
	public long getNoOfActionableOrders() {
		return noOfActionableOrders;
	}
	public void setNoOfActionableOrders(long noOfActionableOrders) {
		this.noOfActionableOrders = noOfActionableOrders;
	}
	public double getActionableOrdersPercent() {
		return actionableOrdersPercent;
	}
	public void setActionableOrdersPercent(double actionableOrdersPercent) {
		this.actionableOrdersPercent = actionableOrdersPercent;
	}
	@Override
	public String toString() {
		return "TotalShippedOrder [saleQuantity=" + saleQuantity
				+ ", saleQuantityPercent=" + saleQuantityPercent
				+ ", returnQuantity=" + returnQuantity
				+ ", returnQuantityPercent=" + returnQuantityPercent + ", nr="
				+ nr + ", nrPercent=" + nrPercent + ", returnAmount="
				+ returnAmount + ", returnAmountPercent=" + returnAmountPercent
				+ ", netSaleQuantity=" + netSaleQuantity
				+ ", netSaleQuantityPercent=" + netSaleQuantityPercent
				+ ", netSaleAmount=" + netSaleAmount
				+ ", netSaleAmountPercent=" + netSaleAmountPercent
				+ ", cities=" + cities + ", cityQuantity=" + cityQuantity
				+ ", cityPercentage=" + cityPercentage
				+ ", noOfDeliveredOrder=" + noOfDeliveredOrder
				+ ", deliveredOrderPercent=" + deliveredOrderPercent
				+ ", noOfReturnOrder=" + noOfReturnOrder
				+ ", returnOrderPercent=" + returnOrderPercent
				+ ", noOfRTOOrder=" + noOfRTOOrder + ", RTOOrderPercent="
				+ RTOOrderPercent + ", noOfRTOLimitCrossed="
				+ noOfRTOLimitCrossed + ", RTOLimitCrossedPercent="
				+ RTOLimitCrossedPercent + ", noOfReturnLimitCrossed="
				+ noOfReturnLimitCrossed + ", returnLimitCrossedPercent="
				+ returnLimitCrossedPercent + ", noOfActionableOrders="
				+ noOfActionableOrders + ", actionableOrdersPercent="
				+ actionableOrdersPercent + "]";
	}
	public String getPcName() {
		return pcName;
	}
	public void setPcName(String pcName) {
		this.pcName = pcName;
	}
	public long getNoOfSettledOrders() {
		return noOfSettledOrders;
	}
	public void setNoOfSettledOrders(long noOfSettledOrders) {
		this.noOfSettledOrders = noOfSettledOrders;
	}
	public double getSettledOrdersPercent() {
		return settledOrdersPercent;
	}
	public void setSettledOrdersPercent(double settledOrdersPercent) {
		this.settledOrdersPercent = settledOrdersPercent;
	}
	
	//Implementing comparators
		 /*
	     * Comparator implementation to Sort TSO object based on nrAmount
	     */
	    public static class OrderByNR implements Comparator<TotalShippedOrder> {

	        @Override
	        public int compare(TotalShippedOrder ttso1, TotalShippedOrder ttso2) {
	            return ttso1.nr < ttso2.nr ? 1 : (ttso1.nr > ttso2.nr ? -1 : 0);
	        }
	    }
	    
		 /*
	     * Comparator implementation to Sort TSO object based on sale qua
	     */
	    public static class OrderBySaleQuantity implements Comparator<TotalShippedOrder> {

	        @Override
	        public int compare(TotalShippedOrder ttso1, TotalShippedOrder ttso2) {
	            return ttso1.saleQuantity < ttso2.saleQuantity ? 1 : (ttso1.saleQuantity > ttso2.saleQuantity ? -1 : 0);
	        }
	    }
	    
	    /*
	     * Comparator implementation to Sort TSO object based on return quantity
	     */
	    public static class OrderByReturnQuantity implements Comparator<TotalShippedOrder> {

	        @Override
	        public int compare(TotalShippedOrder ttso1, TotalShippedOrder ttso2) {
	            return ttso1.returnQuantity < ttso2.returnQuantity ? 1 : (ttso1.returnQuantity > ttso2.returnQuantity ? -1 : 0);
	        }
	    }
	    
	    /*
	     * Comparator implementation to Sort TSO object based on return amount
	     */
	    public static class OrderByReturnamount implements Comparator<TotalShippedOrder> {

	        @Override
	        public int compare(TotalShippedOrder ttso1, TotalShippedOrder ttso2) {
	            return ttso1.returnAmount < ttso2.returnAmount ? 1 : (ttso1.returnAmount > ttso2.returnAmount ? -1 : 0);
	        }
	    }
		
	    /*
	     * Comparator implementation to Sort TSO object based on return amount
	     */
	    public static class OrderBySaleamount implements Comparator<TotalShippedOrder> {

	        @Override
	        public int compare(TotalShippedOrder ttso1, TotalShippedOrder ttso2) {
	            return ttso1.netSaleAmount < ttso2.netSaleAmount ? 1 : (ttso1.netSaleAmount > ttso2.netSaleAmount ? -1 : 0);
	        }
	    }

		public double getNetPaymentResult() {
			return netPaymentResult;
		}
		public void setNetPaymentResult(double netPaymentResult) {
			this.netPaymentResult = netPaymentResult;
		}
		public double getNetPaymentResultPercent() {
			return netPaymentResultPercent;
		}
		public void setNetPaymentResultPercent(double netPaymentResultPercent) {
			this.netPaymentResultPercent = netPaymentResultPercent;
		}
		public double getTotalPaymentDiffference() {
			return totalPaymentDiffference;
		}
		public void setTotalPaymentDiffference(double totalPaymentDiffference) {
			this.totalPaymentDiffference = totalPaymentDiffference;
		}
		public double getPaymentDiffferencePercent() {
			return paymentDiffferencePercent;
		}
		public void setPaymentDiffferencePercent(double paymentDiffferencePercent) {
			this.paymentDiffferencePercent = paymentDiffferencePercent;
		}

}

