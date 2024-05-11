<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Detail User</title>
</head>
<body>
	<a href="list">
         Danh sách người dùng
    </a>
<h3>Chi tiết người dùng </h3>
<hr align="left" width="30%">
<br>
	<table>
		<tbody>
			<tr>
				<td><label>Họ tên</label></td>
				<td><c:out value="${user.fullName}"></c:out></td>
			</tr>
			<tr>
				<td><label>Email</label></td>
				<td><c:out value="${user.email}"></c:out></td>
			</tr>
			<tr>
				<td><label>Số điện thoại</label></td>
				<td><c:out value="${user.phoneNumber}"></c:out></td>
			</tr>
			<tr>
				<td><label>Tên tài khoản</label></td>
				<td><c:out value="${user.userName}"></c:out></td>
			</tr>
			<tr>
				<td><label>Địa chỉ</label></td>
				<td><c:out value="${user.address}"></c:out></td>
			</tr>
			<tr>
				<td><label>Tổng số tiền đã quyên góp</label></td>
				<td><c:out value="${moneyDonated}"></c:out></td>
			</tr>
		</tbody>
	</table>
<br>
<h3>Danh sách những đợt quyên góp đã thực hiện</h3>
	<table border="1">
		<tr>
			<th> STT </th>
			<th> Tên đợt quyên góp </th>
			<th> Số tiền quyên góp </th>
			<th> Nội dung </th>
		</tr>
		<c:forEach var="tempDonatUser" items="${donatUsers}" varStatus="loop">
		    <tr>
		        <td>${loop.index + 1}</td>
		        <td>${tempDonatUser.theDonat.name}</td>
		        <td>${tempDonatUser.money}</td>
		        <td>${tempDonatUser.text}</td>
		    </tr>
		</c:forEach>
	</table>
</body>
</html>