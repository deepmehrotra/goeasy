<%-- <%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>JSP Page</title>
        <c:set var="baseURL" value="${pageContext.servletContext.contextPath}" />
        <link href="${baseURL}/resources/sample/themes/redmond/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
        <link href="${baseURL}/resources/sample/scripts/jtable/themes/standard/green/jtable_green.css" rel="stylesheet" type="text/css" />
 
        <script src="${baseURL}/resources/sample/scripts/jquery-1.6.4.min.js" type="text/javascript"></script>
        <script src="${baseURL}/resources/sample/scripts/jquery-ui-1.8.16.custom.min.js" type="text/javascript"></script>
 
        <script src="${baseURL}/resources/sample/scripts/jtable/jquery.jtable.js" type="text/javascript"></script>
        <script src="${baseURL}/resources/sample/scripts/jtable/external/json2.js" type="text/javascript"></script>
    </head>
    <body>
        <script>
            $(document).ready(function() {              
                //setup the jtable that will display the results
                $('#ExpenseTableContainer').jtable({
                    title: 'Table of Expenses',
                    selecting: true, //Enable selecting 
                    paging: true, //Enable paging
                    pageSize: 10, //Set page size (default: 10)
                    sorting: true, //Enable sorting
                    actions: {
                        listAction: '${baseURL}/datatable/getAllExpenses',
                        createAction: '${baseURL}/datatable/addExpense',
                        updateAction: '${baseURL}/datatable/updateExpense',
                        deleteAction: '${baseURL}/datatable/deleteExpense'
                    },
                    fields: {
                        ExpenseId: {
                            key: true,
                            list: true,
                            create: false,
                            edit: false
                        },
                        Date: {
                            title: 'Date',
                            width: '30%'
                        },
                        Amount: {
                            title: 'Amount',
                            width: '15%'
                        },
                        CategoryId: {
                            title: 'Category',
                            options: '${baseURL}/datatable/categories'
                        },
                        SubcategoryId: {
                            title: 'Sub Category',
                            dependsOn: 'CategoryId',
                            options: function (data) {
                                if (data.source == 'list') {
                                    //Return url of all countries for optimization.
                                    //This method is called for each row on the table and jTable caches options based on this url.
                                    return '${baseURL}datatable/subcategories?categoryId=0';
                                }
                                return '${baseURL}/datatable/subcategories?categoryId=' + data.dependedValues.CategoryId;
                            },
                            list: false
                        },        
                        Description: {
                            title: 'Description',
                            width: '25%'
                        }
                    },
                    rowInserted: function (event, data) {
                        //if (data.record.Name.indexOf('Andrew') >= 0) {
                            $('#ExpenseTableContainer').jtable('selectRows', data.row);
                            console.log("records inserted");
                            //$('#PeopleTableContainer').jtable('load');
                        //}
                    },
                    //Register to selectionChanged event to hanlde events                                     
                    recordAdded: function(event, data){
                        //after record insertion, reload the records
                        $('#ExpenseTableContainer').jtable('load');
                    },
                    recordUpdated: function(event, data){
                        //after record updation, reload the records
                        $('#ExpenseTableContainer').jtable('load');
                    }
                });
                $('#ExpenseTableContainer').jtable('load');              
 
            });    
        </script>
 
        <h2>Groups</h2>     
        <div id="ExpenseTableContainer" style="width:99%;"></div>
    </body>
</html> --%>