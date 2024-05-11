<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Add User</title>
		<style>
			.error{color:red}
		</style>
	</head>
<body>
<h3>Thêm mới </h3>
<hr align="left" width="30%">
<br>
<form:form action="saveUser" modelAttribute="user" method="POST">
	<form:hidden path="status" value="Active"/>
	<table>
		<tbody>
			<tr>
				<td><label>Họ và tên</label></td>
				<td><form:input path="fullName"/></td>
				<td><form:errors path = "fullName" cssClass="error"/></td>
				
			</tr>
			<tr>
				<td><label>Email</label></td>
				<td><form:input path="email"/></td>
				<td><form:errors path = "email" cssClass="error"/></td>
			</tr>
			<tr>
				<td><label>Số điện thoại</label></td>
				<td><form:input path="phoneNumber"/></td>
				<td><form:errors path = "phoneNumber" cssClass="error"/></td>
			</tr>
			<tr>
				<td><label>Địa chỉ</label></td>
				<td><form:input path="address"/></td>
				<td><form:errors path = "address" cssClass="error"/></td>
			</tr>
			<tr>
				<td><label>Tên tài khoản</label></td>
				<td><form:input path="userName"/></td>
				<td><form:errors path = "userName" cssClass="error"/></td>
			</tr>
			<tr>
				<td><label>Mật khẩu</label></td>
				<td><form:input path="password"/></td>
				<td><form:errors path = "password" cssClass="error"/></td>
			</tr>
			<tr>
				<td><label>Vai trò</label></td>
				<td><label>
					<form:select path="roleId">
					<form:option value="User" label="User"/>
					<form:option value="Admin" label="Admin"/>
					</form:select>
				</label></td>
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