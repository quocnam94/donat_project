<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
    <head>
        <title>Website Donation</title>
     <style>
		.isActive {
    		display: none;
		}
	</style>
    </head>
<body id="top">

<div class="site-wrap">
    <div class="site-mobile-menu site-navbar-target">
    	<a
            th:if="${session.admin}"
            class="nav-link dropdown-toggle"
	        href="login"
            role="button"
            ><i class="fas fa-user fa-fw"></i><span> Logout</span></a
          >
    <section class="site-section">
        <div class="container">
            <div class="row mb-5 justify-content-center">
                <div class="col-md-7 text-center">
                    <h2 class="section-title mb-2" >Các đợt quyên góp</h2>
                </div>
            </div>
 			<div class="card-body">
	            <table id="datatablesSimple">
	              <tbody>			
					<c:forEach var="tempDonat" items="${donats.content}">	        
						<c:if test="${tempDonat.isActive}">
							<c:url var="detail" value="/landing/detailDonatForUser">
								<c:param name="donatId" value="${tempDonat.id}"/>
								<c:param name="userId" value="${userId}"/>
							</c:url> 	 
							<c:url var="showFormForDonat" value="/landing/showFormForDonat">
								<c:param name="user" value="${userId}"/>								
							</c:url> 
							<tr>
								<td> 
									<a href="${detail}">${tempDonat.name}</a>
								</td> 
								<td> Ngày bắt đầu</td>
								<td> Ngày kết thúc</td>
								<td> ${tempDonat.organization}</td>   
								<td> 
									<c:if test="${tempDonat.status == 'Processing'}">
										<form action="${showFormForDonat}" method="POST">
                       			 			<input type="hidden" name="donatId" value="${tempDonat.id}" /> 
                       						<input type="hidden" name="userId" value="${userId}" />
											<button type="submit">Quyên góp</button>      
										</form>
								   </c:if> 
								</td>           
								<td class="isActive"> ${tempDonat.isActive}</td>
							</tr>
							<tr>
								<td> ${tempDonat.status}</td> 
								<td> ${tempDonat.startDate}</td>
								<td> ${tempDonat.endDate}</td>
								<td> ${tempDonat.phoneNumber}</td>
							</tr>
						</c:if>  	
					</c:forEach>
	              </tbody>
	            </table>
	            <p>
			    Tổng số đợt quyên góp: ${donats.totalElements} <br>
			</p>
			<p>
			    Chọn số lượng hiển thị:
			    <form action="${pageContext.request.contextPath}/landing/listDonatForUser" method="GET">
			        <select name="size" name="page" value="${donats.number}">
			            <option value="5" ${donats.size == 5 ? 'selected' : ''}>5</option>
			            <option value="10" ${donats.size == 10 ? 'selected' : ''}>10</option>
			        </select>
				        <input type="hidden" name="page" value="${donats.number}">
				        <input type="submit" value="Xem">
				        <input type="hidden" name="userId" value="${userId}"/>
			    </form>
			</p>
	          </div>
	        </div>
	    </section>
	</div>
</body>
</html>