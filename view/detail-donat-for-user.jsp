<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <title>Donation website</title>
</head>
<body id="top">  
<a href="listDonatForUser?userId=${user.id}">
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
				<td><c:out value="${moneyDonated}"></c:out></td>
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
			<tr>        
				<c:if test="${donat.status == 'Processing'}">
					<button type="button" method="POST" onclick="location.href='${pageContext.request.contextPath}/landing/showFormForDonat?donatId=${donat.id}&userId=${user.id}';">Quyên góp</button>
				</c:if> 
			</tr>
		</tbody>
	</table>
	<br>
<h3>Danh sách những người quyên góp</h3>
	<table border="1">
		<tr>
			<th> STT </th>
			<th> Họ tên </th>
			<th> Số tiền quyên góp </th>
			<th> Lời nhắn gửi </th>
		</tr>
		<c:forEach var="tempDonatUser" items="${donatUsers}" varStatus="loop">	        
		<tr>
			<td> ${loop.index + 1}</td>
			<td> ${tempDonatUser.name}</td>   
			<td> ${tempDonatUser.money}</td>   
			<td> ${tempDonatUser.text}</td>     	
		</tr>		
		</c:forEach> 
	</table>
</body>
</html>