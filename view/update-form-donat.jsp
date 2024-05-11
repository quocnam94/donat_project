<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Donation</title>
	<style>
		.error{color:red}
	</style>
</head>
<body>
<h3>Cập nhật </h3>
<hr align="left" width="30%">
<br>
<form:form action="saveDonatInUpdate" modelAttribute="donat" method="POST">
<form:hidden path="id"/>
<form:hidden path="status"/>
<form:hidden path="isActive"/>
<form:hidden path="moneyDonated"/>
<form:hidden path="moneyTarget"/>
	<table>
		<tbody>
			<tr>
				<td><label>Mã đợt quyên góp</label></td>
				<td><form:input path="code" required="true"/></td>
			</tr>
			<tr>
				<td><label>Tên đợt quyên góp</label></td>
				<td><form:input path="name" required="true"/></td>
			</tr>
			<tr>
				<td><label>Ngày bắt đầu</label></td>
				<td><form:input path="startDate" required="true"/></td>
				<td><form:errors path = "startDate" cssClass="error"/></td>
			</tr>
			<tr>
				<td><label>Ngày kết thúc</label></td>
				<td><form:input path="endDate" required="true"/></td>
				<td><form:errors path = "endDate" cssClass="error"/></td>
			</tr>
			<tr>
				<td><label>Tên tổ chức</label></td>
				<td><form:input path="organization" required="true"/></td>	
			</tr>
			<tr>
				<td><label>Số điện thoại</label></td>
				<td><form:input path="phoneNumber"/></td>
				<td><form:errors path = "phoneNumber" cssClass="error"/></td>
			</tr>
			<tr>
				<td><label>Nội dung</label></td>
				<td><form:input path="content" required="true"/></td>
			</tr>
		</tbody>
	</table>
	<br>
	<table>
			<tr>
		        <td><label></label></td>
		        <td><input type="button" value="Close" style="position: absolute; left: 2%;"
		            onclick="window.location.href='listDonat'; return false;"/></td>
		        <td><label></label></td>
		        <td><input type="submit" value="Submit" style="position: absolute; left: 10%;"></td>
		    </tr>
		</table>
</form:form>
</body>
</html>