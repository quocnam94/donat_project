<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html  lang="en">
<head>
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
  <meta name="description" content="" />
  <meta name="author" content="" />
  <title>Quản trị</title>
<style>
.isActive {
    display: none;
}
</style>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js"
          crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
          crossorigin="anonymous"></script>
  <script src="../../static/admin1/assets/js/scripts.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>

  <script src="../../static/admin1/assets/js/datatables-simple-demo.js"></script>

  <script src="https://cdn.ckeditor.com/ckeditor5/29.0.0/classic/ckeditor.js"></script>
</head>

<body class="sb-nav-fixed">
  <nav th:fragment="html_nav" class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
    <!-- Navbar Brand-->
    <a href="home"><h1>QUẢN TRỊ</h1></a>    
    <!-- Navbar-->

			<a
            th:if="${session.admin}"
            class="nav-link dropdown-toggle"
	        href="login"
            role="button"
            ><i class="fas fa-user fa-fw"></i><span> Logout</span></a>                
	</nav>

<div id="layoutSidenav">
  <div id="layoutSidenav_nav">
    <div th:fragment="html_menu" id="layoutSidenav_nav">
      <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
          <div class="sb-sidenav-menu">
              <div class="nav">
                  <a class="nav-link" href="list">
                      <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                      Quản lý người dùng
                  </a>
                  <a class="nav-link" href="listDonat">
                      <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                      Quản lý đợt quyên góp
                  </a>
              </div>
          </div>

      </nav>
  </div>
  </div>

  <div id="layoutSidenav_content">
    <main>
      <div class="container-fluid px-4">
        <h1 class="mt-4">Danh sách đợt quyên góp</h1>
        <!-- Navbar Search-->
	    <form:form action="searchDonat" method="GET" onsubmit="return validateForm()">
	          <input type="text" name="theSearch" id="theSearch" />
	          <input type="submit" value="Search" />
	    </form:form>
	    <br>
        <div class="card mb-4">
          <div class="card-header">
            <button type="button" class="btn btn-success" data-bs-toggle="modal"
                    data-bs-target="#exampleModalAdd" onclick="window.location.href='addDonat'; return false;"/>
              Thêm mới
            </button>
            </div>
            <br>
            <!-- Modal Add-->
            <div class="modal fade" id="exampleModalAdd" tabindex="-1" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
              <div class="modal-dialog modal-lg">
                <div class="modal-content">
                </div>
              </div>
          </div>
          <div class="card-body">
            <table border="1" id="datatablesSimple">
              <thead>
              <tr style="background-color: gray !important;">
              	<th>STT</th>
                <th>Mã</th>
                <th>Tên</th>
                <th>Ngày bắt đầu</th>
                <th>Ngày kết thúc</th>
                <th>Tổ chức</th>
                <th>Số điện thoại</th>
                <th>Số tiền đã quyên góp</th>
                <th>Số tiền mục tiêu</th>
                <th>Trạng thái</th>
                <th>Hành động</th>
              </tr>
              </thead>
              <tbody>
              <c:set var="stt" value="1" scope="application" />
             	 <c:forEach var="tempDonat" items="${donats.content}" varStatus="loop">           
	             	 <c:if test="${tempDonat.isActive}">  	 
	             	 	<c:url var="update" value="/landing/showFormForUpdateDonat">
	        				<c:param name="donatId" value="${tempDonat.id}"/>
	    				</c:url>
	    				<c:url var="detail" value="/landing/detailDonat">
	        				<c:param name="donatId" value="${tempDonat.id}"/>
	    				</c:url>
	    				<c:url var="delete" value="/landing/deleteDonat">
	        				<c:param name="donatId" value="${tempDonat.id}"/>
	    				</c:url>
	    				<c:url var="changeStatusDonat" value="/landing/changeStatusDonat">
        					<c:param name="donatId" value="${tempDonat.id}"/>
    					</c:url>
				    	<tr>
							<td> ${stt}</td>  <c:set var="stt" value="${stt + 1}" scope="application" />
							<td> ${tempDonat.code}</td>
					        <td> ${tempDonat.name}</td> 
					        <td> ${tempDonat.startDate}</td>
					        <td> ${tempDonat.endDate}</td>
					        <td> ${tempDonat.organization}</td>              
					        <td> ${tempDonat.phoneNumber}</td>
					        <td> ${tempDonat.moneyDonated}</td>
					        <td> ${tempDonat.moneyTarget}</td>
					        <td> ${tempDonat.status}</td>
					        <td class="isActive"> ${tempDonat.isActive}</td>
					        <td> 
					            <form action="${update}" method="POST">
					            	<c:if test="${tempDonat.status == 'Created' || tempDonat.status == 'Processing' || tempDonat.status == 'Ended'}">
					                	<button type="submit">Cập nhật</button>
			            			</c:if> 
					            </form>
					            <form action="${detail}" method="POST">
					                <button type="submit">Chi tiết</button>
					            </form>
					            <form action="${delete}" method="POST">
					            	<c:if test="${tempDonat.status == 'Created'}">
					                	<button type="submit" onclick="return handleDelete('${tempDonat.name}')">Xóa</button>
			            			</c:if> 
					            </form>
					            <form action="${changeStatusDonat}" method="POST">	
			             			<c:if test="${tempDonat.status == 'Created'}">
			                			<button type="submit" onclick ="window.location.href='changeStatusDonat'">Quyên góp</button>
			            			</c:if> 
			            			<c:if test="${tempDonat.status == 'Processing'}">
			            				<button type="submit" onclick ="window.location.href='changeStatusDonat'">Kết thúc </button>
			            			</c:if>
			            			<c:if test="${tempDonat.status == 'Ended'}">
			            				<button type="submit" onclick ="window.location.href='changeStatusDonat'">Đóng </button>
			            			</c:if>
			            		</form>
					        </td>
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
			    <form action="${pageContext.request.contextPath}/landing/listDonat" method="GET">
			        <select name="size" name="page" value="${donats.number}">
			            <option value="5" ${donats.size == 5 ? 'selected' : ''}>5</option>
			            <option value="10" ${donats.size == 10 ? 'selected' : ''}>10</option>
			        </select>
			        <input type="hidden" name="page" value="${donats.number}">
			        <input type="submit" value="Xem">
			    </form>
			</p>
          </div>
        </div>
      </div>
      </div>
    </main>
				<script>
				function handleDelete(name) {
				    return confirm('Bạn chắc chắn muốn xóa? \n' + 'Đợt quyên góp: ' + name);
				}
				</script>
				<script>
					function validateForm() {
    					var x = document.getElementById("theSearch").value;
    					if (x == null || x == "") {
        				alert("Nhập số điện thoại, mã số, tên tổ chức hoặc trạng thái quyên góp để tìm kiếm");
        				return false;
        				}
	    				return true;
				}
				</script>

    <footer th:replace="admin/fragments :: footer" class="py-4 bg-light mt-auto">

    </footer>
  </div>
</div>

<script src="js/JQuery3.3.1.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
<script src="js/scripts.js"></script>
<script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
<script src="js/datatables-simple-demo.js"></script>
</body>

</html>