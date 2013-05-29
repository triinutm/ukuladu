<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ include file="MenuBar.jsp" %>
<%
String treeView = (String)request.getAttribute("treeView"); 
out.println(treeView);
if ( request.getAttribute("cat").toString() != null &&
		!request.getAttribute("cat").toString().equalsIgnoreCase("-1") ) {
	out.println("Otsi");
}
if ( request.getAttribute("editCat") != null && 
		!request.getAttribute("editCat").toString().equalsIgnoreCase("-1") ) {
	out.println("Lisa uus" + request.getAttribute("editCat").toString());
}
%>
</body>
</html>