<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<script 
src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>

<script>
$(document).ready(function() {
	
	var downloadValue='${downloadValue}';
	var uploadValue='${uploadValue}';
	if(uploadValue!=null&&uploadValue.length!=0)
		$("#sheetValue").val(uploadValue);
	else
		$("#downloadreporttype").val(downloadValue);
	
});

$('#download').click(function(){
	var value = $('#downloadreporttype').val();
	  window.location = 'download/xls.html?sheetvalue='+value;
	});
</script>
</head>
<body>
<div class="row">
		<div class="col-lg-12">
			<div class="ibox float-e-margins">
				<div class="ibox-title">
					<h5>Export Import Files</h5>
				</div>
				<div class="ibox-content overflow-h">
					<div class="col-lg-12">
 						<div class="hr-line-dashed"></div>
 						<div class="col-md-4">
                                            <select class="form-control" id="downloadreporttype" name="downloadreporttype">
                                            <option value="">Select file to download</option>
                                            <option value="ordersummary">Order Summary</option>
									    	<option value="orderPoSummary">Order PO Summary</option>
									    	<option value="paymentSummary">Payment Summary</option>
									    	<option value="poPaymentSummary">PO Payment Summary</option>
									    	<option value="returnSummary">Return Summary</option>
									    	<option value="debitNoteSummary">Debit Note</option>
									    	<option value="productSummary">Product Summary</option>
									    	<option value="inventorySummary">Inventory Summary</option>
                                            </select>
                                        </div>
 						<div class="col-md-2">
                                    <button class="btn btn-success " type="button" id="download"><i class="fa fa-search"></i>&nbsp;&nbsp;<span class="bold">Download</span></button>
                                </div>

						<div class="col-sm-12">
							<div class="form-group">

								<form:form method="post" action="saveSheet.html"
									modelAttribute="uploadForm" enctype="multipart/form-data"
									class="form-horizontal">

									<!-- 	<input id="addFile" type="button" value="Add File" /> -->
									<div class="col-md-4">
                                            <select class="form-control" id="sheetValue" name="sheetValue">
                                            <option value="">Select file to download</option>
                                            <option value="ordersummary">Order Summary</option>
									    	<option value="orderPoSummary">Order PO Summary</option>
									    	<option value="paymentSummary">Payment Summary</option> 
									    	<option value="returnSummary">Return Summary</option>
									    	<option value="debitNoteSummary">Debit Note</option>
									    	<option value="productSummary">Product Summary</option>
									    	<option value="inventorySummary">Inventory Summary</option>
									    	<option value="poPaymentSummary">PO Payment Summary</option>
                                            </select>
                                        </div>
									<div class="col-md-4">
										<table id="fileTable">
											<tr>
												<td><input name="files[0]" type="file" class="form-control"  /></td>
											</tr>

										</table>
									</div>
									<br />
									<div class="col-md-2">
									<input type="submit" value="Upload" class="form-control" />
									</div>
								</form:form>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

 

</body>
</html>
