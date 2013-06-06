<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@page import="hibernate.Item"%>
   <%@page import="hibernate.Store"%>
   <%@page import="hibernate.ItemStore"%>
   <%@page import="java.util.List"%>
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lao toimingud</title>
</head>
<%Item item = (Item)request.getAttribute("item");%>
<%List<Store> allStores = (List<Store>)request.getAttribute("allStores");%>
<%List<ItemStore> itemStores = (List<ItemStore>)request.getAttribute("itemStores");%>
<%String registerSuccessful = (String)request.getAttribute("register_successful"); %>
<%String removeSuccessful = (String)request.getAttribute("remove_successful"); %>
<%String moveFailed = (String)request.getAttribute("move_from_err"); %>
<%String moveSuccessful = (String)request.getAttribute("move_successful"); %>
<%String moveCountError = (String)request.getAttribute("move_from_err_counts"); %>
<%String paramActionNeeded = (String)request.getAttribute("parameter_needed"); %>
<body>
<%@ include file="logout.jsp" %>
<h1>LAO TOIMINGUD</h1>
<%if(item != null){ 
	out.println("<strong>"+item.getName() + "- " + item.getDescription()+"</strong><br><br>");
if(registerSuccessful != null){ 
	out.println(registerSuccessful);
	}
if(removeSuccessful != null){
	out.println(removeSuccessful);
}
if(moveFailed != null){
out.println(moveFailed);
} 
if(moveCountError != null){
out.println(moveCountError); 
}
if(paramActionNeeded != null){
out.println(paramActionNeeded);
} 
if(moveSuccessful != null){ 
out.println(moveSuccessful);
} 
out.println("<br><br>");%>
<%
if(itemStores.size() > 0){ %>
	<c:import url="/storeList.xml" var="xmldocument"/>
	<c:import url="/storeList.xsl" var="xslt"/>
	<x:transform xml="${xmldocument}" xslt="${xslt}"/>
<%}else{ out.println("<p>Toodet pole yheski laos!</p>");} %>
<br><br>
<form method="post" id="warehouse_register_form" accept-charset="UTF-8" action="?action=register">
	<table border='1'>
		<tr>
			<th bgcolor=lightgrey colspan="2">ARVELE VÕTMINE</th>
		</tr>
		<tr>
			<th bgcolor=lightgrey>Toode:</th>
			<td><%out.println(item.getName() + "- " + item.getDescription() );%>
				<input type="hidden" name="item_id" value="<%out.print(item.getItem());%>" />
			</td>
		</tr>
		<tr>
			<th bgcolor=lightgrey>Ladu:</th>
			<td>
				<select name="register_select_store">
    				<option value="" disabled="disabled" selected="selected">--Vali ladu--</option>
    				<%for(Store store : allStores){ %>
    					<option value="<%out.print(store.getStore());%>"><%out.print(store.getName());%></option>
    				<%} %>
				</select>
			</td>
		</tr>
		<tr>
			<th bgcolor=lightgrey>Laohind:</th>
			<td><input type="text" name="warehouse_register_price" /></td>
		</tr>
		<tr>
			<th bgcolor=lightgrey>Kogus:</th>
			<td><input type="text" name="warehouse_register_quantity" /><%out.print(item.getUnitType().getTypeName());%></td>
		</tr>
		<tr>
			<th bgcolor=lightgrey>M2rkus:</th>
			<td>
				<textarea rows="4" cols="50" name="warehouse_register_notes"></textarea>
			</td>
		</tr>
		</table>
		<input type="submit" name="register_submit" value="Salvesta" />
</form>
	<br />
	<br />
<form method="post" id="warehouse_remove_form" accept-charset="UTF-8" action="?action=remove">
	<table border='1'>
		<tr>
			<th bgcolor=lightgrey colspan="2">MAHA KANDMINE</th>
		</tr>
		<tr>
			<th bgcolor=lightgrey>Toode:</th>
			<td><%out.println(item.getName() + "- " + item.getDescription() );%>
				<input type="hidden" name="item_id" value="<%out.print(item.getItem());%>" />
			</td>
		</tr>
		<tr>
			<th bgcolor=lightgrey>Ladu:</th>
			<td>
				<select name="remove_from_store">
    				<option value="" disabled="disabled" selected="selected">--Vali ladu--</option>
    				<%for(Store store : allStores){ %>
    					<option value="<%out.print(store.getStore());%>"><%out.print(store.getName());%></option>
    				<%} %>
				</select>
			</td>
		</tr>
		<tr>
			<th bgcolor=lightgrey>Kogus:</th>
			<td><input type="text" name="warehouse_remove_quantity" /><%out.print(item.getUnitType().getTypeName());%></td>
		</tr>
		<tr>
			<th bgcolor=lightgrey>M2rkus:</th>
			<td>
				<textarea rows="4" cols="50" name="warehouse_remove_notes"></textarea>
			</td>
		</tr>
		</table>
	<input type="submit" name="remove_submit" value="Salvesta" />
</form>
	<br />
	<br />
<form method="post" id="warehouse_remove_form" accept-charset="UTF-8" action="?action=move">
	<table border='1'>
		<tr>
			<th bgcolor=lightgrey colspan="2">LIIGUTAMINE LADUDE VAHEL</th>
		</tr>
		<tr>
			<th bgcolor=lightgrey>Toode:</th>
			<td><%out.println(item.getName() + "- " + item.getDescription() );%>
				<input type="hidden" name="item_id" value="<%out.print(item.getItem());%>" />
			</td>
		</tr>
		<tr>
			<th bgcolor=lightgrey>Laost:</th>
			<td>
				<select name="move_from_store">
    				<option value="" disabled="disabled" selected="selected">--Vali ladu--</option>
    				<%for(Store store : allStores){ %>
    					<option value="<%out.print(store.getStore());%>"><%out.print(store.getName());%></option>
    				<%} %>
				</select>
			</td>
		</tr>
		<tr>
			<th bgcolor=lightgrey>Lattu:</th>
			<td>
				<select name="move_to_store">
    				<option value="" disabled="disabled" selected="selected">--Vali ladu--</option>
    				<%for(Store store : allStores){ %>
    					<option value="<%out.print(store.getStore());%>"><%out.print(store.getName());%></option>
    				<%} %>
				</select>
			</td>
		</tr>
		<tr>
			<th bgcolor=lightgrey>Kogus:</th>
			<td><input type="text" name="warehouse_move_quantity" /><%out.print(item.getUnitType().getTypeName());%></td>
		</tr>
		<tr>
			<th bgcolor=lightgrey>M2rkus:</th>
			<td>
				<textarea rows="4" cols="50" name="warehouse_move_notes"></textarea>
			</td>
		</tr>
		</table>
	<input type="submit" name="move_submit" value="Salvesta" />
		
</form>
<%}else{ %>
	Toodet ei leitud!
<%} %>
</body>
</html>
