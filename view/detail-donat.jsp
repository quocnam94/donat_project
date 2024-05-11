<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Detail Donation</title>
</head>
<body>
	<a href="listDonat">
         Danh sách đợt quyên góp
    </a>
<h3>Chi tiết đợt quyên góp </h3>
<hr align="left" width="30%">
<br>
	<table>
		<tbody>
			<tr>
				<td><label>Mã đợt quyên góp</label></td>
				<td><c:out value="${donat.code}"></c:out></td>
			</tr>
			<tr>
				<td><label>Tên đợt quyên góp</label></td>
				<td><c:out value="${donat.name}"></c:out></td>
			</tr>
			<tr>
				<td><label>Ngày bắt đầu</label></td>
				<td><c:out value="${donat.startDate}"></c:out></td>
			</tr>
			<tr>
				<td><label>Ngày kết thúc</label></td>
				<td><c:out value="${donat.endDate}"></c:out></td>
			</tr>
			<tr>
				<td><label>Số tiền đã được quyên góp</label></td>
				<td><c:out value="${donat.moneyDonated}"></c:out></td>
			</tr>
			<tr>
				<td><label>Trạng thái</label></td>
				<td><c:out value="${donat.status}"></c:out></td>
			</tr>
			<tr>
				<td><label>Tổ chức</label></td>
				<td><c:out value="${donat.organization}"></c:out></td>
			</tr>
			<tr>
				<td><label>Số điện thoại</label></td>
				<td><c:out value="${donat.phoneNumber}"></c:out></td>
			</tr>
			<tr>
				<td><label>Nội dung</label></td>
				<td><c:out value="${donat.content}"></c:out></td>
			</tr>
		</tbody>
	</table>
<br>
<h3>Danh sách những người quyên góp</h3>
		<!-- Navbar Search-->
	<form:form action="searchUserDonated" method="GET" onsubmit="return validateForm()">
   		<input type="hidden" name="donatId" value="${donat.id}" />
        <input type="text" name="theSearch" id="theSearch" />
        <input type="submit" value="Search" />
	</form:form>
	    <br>
		<table border="1">
		<tr>
			<th> STT </th>
			<th> Họ tên </th>
			<th> Số tiền quyên góp </th>
			<th> Nội dung </th>
		</tr>
		<c:set var="stt" value="1" scope="application" />
		<c:forEach var="tempDonatUser" items="${donatUsers.content}" varStatus="loop">
		<tr>
			<td> ${stt}</td>  <c:set var="stt" value="${stt + 1}" scope="application" />
			<td> ${tempDonatUser.name}</td>   
			<td> ${tempDonatUser.money}</td> 
			<td> ${tempDonatUser.text}</td>     
		</tr>		
		</c:forEach> 
	</table>
	<p>
		Tổng số người quyên góp: ${donatUsers.totalElements} <br>
	</p>
	<p>
	Chọn số lượng hiển thị:
		<form action="${pageContext.request.contextPath}/landing/detailDonat" method="GET">
			<select name="size" name="page" value="${donatUsers.number}">
			    <option value="5" ${donatUsers.size == 5 ? 'selected' : ''}>5</option>
			     <option value="10" ${donatUsers.size == 10 ? 'selected' : ''}>10</option>
			</select>
			<input type="hidden" name="page" value="${donatUsers.number}">
			<input type="hidden" name="donatId" value="${donat.id}" />
			<input type="submit" value="Xem">
		</form>
	</p>
	<script>
		function validateForm() {
    		var x = document.getElementById("theSearch").value;
    		if (x == null || x == "") {
	        	alert("Nhập họ tên người quyên góp để tìm kiếm");
	        	return false;
        	}
	    	return true;
		}
	</script>
</body>
</html>