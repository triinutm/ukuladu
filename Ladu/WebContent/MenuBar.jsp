<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="hibernate.UserAccount"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
		<%
			UserAccount currentuser = (UserAccount) request.getSession()
					.getAttribute("user");
		%>
		<tr>
			<td>
				<%
					if (currentuser != null) {
				%>

				<form action="${pageContext.request.contextPath }/" method="post">
					<%
						out.println("<input type=\"submit\" value=\"Logi välja! \"/>");
					%>
				</form> <%
 	}
 %>
			</td>
			<td>
				<%
					out.println("<a href='" + request.getContextPath()
							+ "/'>Pealeht</a>&nbsp;&nbsp;");
				%>
			</td>
			<td>
				<%
					out.println("<a href=''>Sisselogimise jadadiagramm </a>&nbsp;&nbsp;");
				%>
			</td>
			<td>
				<%
					out.println("<a href=''>Ladude jadadiagramm </a>&nbsp;&nbsp;");
				%>
			</td>
			<td>
				<%
					out.println("<a href=''>Klassidiagramm </a>&nbsp;&nbsp;");
				%>
			</td>
			<td>
				<%
					out.println("<a href='"+request.getContextPath()+"/pricelist'>Hinnakiri</a>&nbsp;&nbsp;");
				%>
			</td>
		</tr>
	</table>
</body>
</html>