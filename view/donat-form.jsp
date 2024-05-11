<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Donat</title>
	<style>
		.error{color:red}
	</style>
</head>
<body>
<form:form action="saveDonatForUser" modelAttribute="donate" method="POST">
    <form:label path="name">Quyên góp: ${donat.name}</form:label>
	<table>
		<tbody>
		 	<tr>
      			<td><form:hidden path="donatId" value="${donat.id}"/></td>
      			<td><form:hidden path="userId" value="${user.id}"/></td>     			     			
   			</tr>
			<tr>
				<td><label>Họ tên</label></td>
				<td><form:input path="name"/></td>
			</tr>
			<tr>
				<td><label>Số tiền quyên góp</label></td>
				<td><form:input path="money" required="true"/></td>		
				<td><form:errors path="money" cssClass="error"/></td>						
			</tr>
			<tr>
				<td><label>Lời nhắn</label></td>
				<td><form:input path="text"/></td>
			</tr>
		</tbody>
	</table>
	<table>
	<tr>
		<td><label></label></td>
		<td><input type="button" value="Close" style="position: absolute; left: 2%;"
			onclick="window.location.href='listDonatForUser?userId=' + ${user.id}; return false;"/>
		<td><label></label></td>
		<td><input type="submit" value="Submit" style="position: absolute; left: 10%;"></td>
	</tr>
</table>
</form:form>	
</body>
</html>