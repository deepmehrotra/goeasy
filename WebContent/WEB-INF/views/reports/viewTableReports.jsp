<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

 <body>
	<form role="form" class="form-horizontal">
		<div class="col-sm-12">
			<table
				class="table table-striped table-bordered table-hover dataTables-example">
				<thead>
					<tr>
						<th></th>
						<th>#</th>
						<th>Order ID/PO No</th>
						<th>AWD</th>
						<th>Invoice ID</th>
						<th>Sub Order ID</th>
						<th>PI Reference</th>
						<th>Flipkart Unique Item ID</th>
						<th>Partner</th>
						<th>SKU</th>
						<th>Order Recieved Date</th>
						<th>Order Shipped Date</th>
						<th>GoEasy Unique ID</th>
						<th>GoEasy Barcode</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td><input type="checkbox"></td>
						<td>1</td>
						<td>Mark</td>
						<td>01/05/215</td>
						<td>@mdo</td>
						<td>1</td>
						<td>Mark</td>
						<td>Otto</td>
						<td>Mark</td>
						<td class="new-tooltip"><a href="">Mark SKU
								<div>
									John doe <br> 9900990090 <br> email@gmail.com
								</div>
						</a></td>
						<td>Otto</td>
						<td>1</td>
						<td>Mark</td>
						<td>Otto</td>
						<td class="tooltip-demo"><a href=""><i
								class="fa fa-edit text-navy" data-toggle="tooltip"
								data-placement="top" data-original-title="Edit"></i></a></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="col-sm-12">
			<div class="hr-line-dashed"></div>
			<button class="btn btn-primary pull-right" type="submit">Save</button>
		</div>

	</form>
</body>
</html>