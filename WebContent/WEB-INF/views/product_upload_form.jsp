<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
	<title>Spring MVC Multiple File Upload</title>
<style>
	body {font-family: "Trebuchet MS";}
	h1 {font-size: 1.5em;}
</style>

<script 
src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>

<script>
$(document).ready(function() {

	$('#addFile').click(function() {
		var fileIndex = $('#fileTable tr').children().length - 1;
		$('#fileTable').append(
				'<tr><td>'+
				'	<input type="file" name="files['+ fileIndex +']" />'+
				'</td></tr>');
	});
	
});
</script>
</head>
<body>
<h1>Spring Multiple File Upload example</h1>
 <h2>1. <a href="download/Product/xls.html">Download</a></h2>
<form:form method="post" action="saveProductSheet.html" 
		modelAttribute="uploadForm" enctype="multipart/form-data">

	<p>Select files to upload. Press Add button to add more file inputs.</p>

	<input id="addFile" type="button" value="Add File" />
	<table id="fileTable">
		<tr>
			<td><input name="files[0]" type="file" /></td>
		</tr>
		
	</table>
	<br/><input type="submit" value="Upload" />
</form:form>
</body>
</html>
