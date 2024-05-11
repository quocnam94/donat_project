<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Login - SB Admin</title>
	<style>
		.error{color:red}
	</style>
    <link rel="stylesheet" href="../../static/admin1/assets/css/styles.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
</head>
<body class="bg-primary">

<div id="layoutAuthentication">
    <div id="layoutAuthentication_content">
        <main>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-5">
                        <div class="card shadow-lg border-0 rounded-lg mt-5">
                            <div class="card-header"><h3 class="text-center font-weight-light my-4">Đăng nhập</h3></div>
                            	<form:form action="processLogin" modelAttribute="login">
                            		Email: <form:input path="email"/>
                            		<form:errors path="email" cssClass="error"/>
                            		<br><br>
                            		Password: <form:input path="password"/>
                            		<form:errors path="password" cssClass="error"/>
                            		<br><br>
                            		<div th:if="${error}">
                                        <p style="color: red" >Tài khoản của bạn đã bị khóa. Vui lòng liên hệ admin để biết thêm chi tiết</p>
                                    </div>
                            		<input type="submit" value="Login"/>
                            	</form:form>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="../../static/admin1/assets/js/scripts.js"></script>
</body>
</html>