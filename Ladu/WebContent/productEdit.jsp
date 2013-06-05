<%@page import="model.AttributeModel"%>
<%@page import="model.ProductModel"%>
<%@page import="hibernate.TypeAttribute"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Toote andemete muutmine</title>
</head>
<body>
<%@ include file="logout.jsp" %>
<%
if(request.getAttribute("productModel") != null){
    ProductModel model = (ProductModel) request.getAttribute("productModel");
%>
<form action="" method="POST">
<table>
<tr><td>Nimetus</td><td><input type="text" name="name" value="<%=model.getName().getAttributeValue()%>"/>
<span><%=model.getName().getErrorMessage()%></span></td></tr>
<tr><td>Kirjeldus</td><td><input type="text" name="description" value="<%=model.getDescription().getAttributeValue()%>" />
<span><%=model.getDescription().getErrorMessage()%></span></td></tr>
<tr><td>M��gihind</td><td><input type="text" name="price" value="<%=model.getPrice().getAttributeValue()%>"/>
<span><%=model.getPrice().getErrorMessage()%></span></td></tr>
<tr><td colspan="2">------------------------ attribuudid ------------------------ </td></tr>
<%
    for(Long key : model.getAttributes().keySet()){
		AttributeModel attributeModel = model.getAttributes().get(key);
		out.println("<tr><td>"+attributeModel.getAttributeName()+"</td><td>");
		out.println("<input type='text' name='"+key+"' placeholder='"
		+attributeModel.getAttributeName()+"' value='"+attributeModel.getAttributeValue()+"'/>"+
		"<input type='hidden' name='"+key+"'value='"+attributeModel.getAttributeName()+"' />"+
		"<input type='hidden' name='"+key+"'value='"+attributeModel.getAttributeId()+"' />"
		+"<span>"+attributeModel.getErrorMessage()+"</span></td></tr>");
    }
	out.println("<tr><td>Toote t��p</td><td>"+model.getType()+
	    "<input type='hidden' name='type' value='"+model.getType()+"' />"+
	    "<input type='hidden' name='itemType' value='"+model.getItemType()+"'</td><tr>");
%>
<tr><td><input type="submit" value="Salvesta" /></td></tr>
</table>
<div>
<%if(request.getAttribute("error") != null) {
    out.println(request.getAttribute("error"));
}%>
</div><br>
<input type="submit" name="delete" value="Kustuta" />

<%}else{
    out.println("<p>Sellist toodet pole olemas!</p>");
} %>
</form>

</body>
</html>