<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update User</title>
	<style>
		.error{color:red}
	</style>
</head>
<body>
<h3>Cập nhật </h3>
<hr align="left" width="30%">
<br>
<form:form action="saveUpdateUser" modelAttribute="user" method="POST">
<form:hidden path="id"/>
<form:hidden path="password"/>
<form:hidden path="status"/>
	<table>
		<tbody>
			<tr>
				<td><label>Họ và tên</label></td>
				<td><form:input path="fullName" required="true"/></td>
			</tr>
			<tr>
				<td><label>Email</label></td>
				<td><form:input path="email" readonly="true" style="background-color: #E0E0E0;" required="true"/></td>
				<td><form:errors path = "email" cssClass="error"/></td>
			</tr>
			<tr>
				<td><label>Số điện thoại</label></td>
				<td><form:input path="phoneNumber"/></td>
				<td><form:errors path = "phoneNumber" cssClass="error"/></td>
			</tr>
			<tr>
				<td><label>Địa chỉ</label></td>
				<td><form:input path="address" required="true"/></td>
			</tr>
			<tr>
				<td><label>Tên tài khoản</label></td>
				<td><form:input path="userName" readonly="true" style="background-color: #E0E0E0;" required="true"/></td>
			</tr>
			<tr>
				<td><label>Vai trò</label></td>
				<td><label>
					<form:select path="roleId">
					<form:option value="User" label="User"/>
					<form:option value="Admin" label="Admin"/>
					</form:select>
					</label>
				</td>
			</tr>
		</tbody>
	</table>
	<br>
	<table>
			<tr>
		        <td><label></label></td>
		        <td><input type="button" value="Close" style="position: absolute; left: 2%;"
		            onclick="window.location.href='list'; return false;"/></td>
		        <td><label></label></td>
		        <td><input type="submit" value="Submit" style="position: absolute; left: 10%;"></td>
		    </tr>
		</table>
</form:form>
</body>
</html>