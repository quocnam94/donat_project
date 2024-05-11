<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html  lang="en">
<head>
  <title>Quản trị</title>
  <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
  <link href="../../static/admin1/assets/css/styles.css" rel="stylesheet" />
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
      <div class="container-fluid px-4">
        <h1 class="mt-4">Danh sách người dùng</h1>
        <!-- Navbar Search-->
	    <form:form action="search" method="GET" onsubmit="return validateForm()">
	          <input type="text" name="theSearch" id="theSearch" />
	          <input type="submit" value="Search" />
	    </form:form>
	    <br>
        <div class="card mb-4">
          <div class="card-header">
            <button type="button" class="btn btn-success" data-bs-toggle="modal"
                    data-bs-target="#exampleModalAdd" onclick="window.location.href='addUser'; return false;"/>
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
				        <th>Họ tên</th>
				        <th>Email</th>
				        <th>Số điện thoại</th>
				        <th>Tài khoản</th>
				        <th>Vai trò</th>
				        <th>Trạng thái</th>
				        <th>Hành động</th>
				    </tr>
			    </thead>

			    <tbody>
			    <c:set var="stt" value="1" scope="application" />
			    <c:forEach var="tempUser" items="${users.content}" varStatus="loop">
			        <c:url var="update" value="/landing/showFormForUpdate">
			            <c:param name="userId" value="${tempUser.id}"/>
			        </c:url>
			        <c:url var="delete" value="/landing/delete">
			            <c:param name="userId" value="${tempUser.id}"/>
			        </c:url>
			        <c:url var="detail" value="/landing/detailUser">
			            <c:param name="userId" value="${tempUser.id}"/>
			        </c:url>
			        <c:url var="changeStatus" value="/landing/changeStatus">
			            <c:param name="userId" value="${tempUser.id}"/>
			        </c:url>
			        <tr>
						<td> ${stt}</td>  <c:set var="stt" value="${stt + 1}" scope="application" />
			            <td> ${tempUser.fullName}</td>
			            <td> ${tempUser.email}</td>
			            <td> ${tempUser.phoneNumber}</td>
			            <td> ${tempUser.userName}</td>
			            <td> ${tempUser.roleId}</td>
			            <td style="color: ${tempUser.status == 'Active' ? '#1c7430' : '#ff0000'}; font-weight: bold;">
			                ${tempUser.status}
			            </td>
			            <td>
			                <form action="${update}" method="POST">
			                    <button type="submit">Sửa</button>
			                </form>
			                <form action="${detail}" method="POST">
			                    <button type="submit">Chi tiết</button>
			                </form>
			                <form action="${delete}" method="POST">
			                    <button type="submit" onclick="return handleDelete('${tempUser.fullName}')">Xóa</button>
			                </form>
			                <form action="${changeStatus}" method="POST">
			                    <c:if test="${tempUser.status == 'Active'}">
			                        <button type="submit" onclick="window.location.href='changeStatus'">Khóa</button>
			                    </c:if>
			                    <c:if test="${tempUser.status == 'Inactive'}">
			                        <button type="submit" onclick="window.location.href='changeStatus'">Mở khóa </button>
			                    </c:if>
			                </form>
			            </td>
			        </tr>
			    </c:forEach>
			    </tbody>
			</table>
			<p>
			    Tổng số người dùng: ${users.totalElements} <br>
			</p>
			<p>
		    Chọn số lượng hiển thị:
			    <form action="${pageContext.request.contextPath}/landing/list" method="GET">
			        <select name="size" name="page" value="${users.number}">
			            <option value="5" ${users.size == 5 ? 'selected' : ''}>5</option>
			            <option value="10" ${users.size == 10 ? 'selected' : ''}>10</option>
			        </select>
			        <input type="hidden" name="page" value="${users.number}">
			        <input type="submit" value="Xem">
			    </form>
			</p>
          </div>
        </div>
      </div>
      </div>
    </main>
				<script>
				function handleDelete(fullName) {
				    return confirm('Bạn chắc chắn muốn xóa? \n' + 'Người dùng: ' + fullName);
				}
				</script>
				<script>
					function validateForm() {
    					var x = document.getElementById("theSearch").value;
    					if (x == null || x == "") {
        				alert("Nhập số điện thoại hoặc email để tìm kiếm người dùng");
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