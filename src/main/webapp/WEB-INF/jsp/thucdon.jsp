<%@page import="org.springframework.ui.Model"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">   
   
    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
 
     <!-- Site Metas -->
    <title>Thực đơn</title>  
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Site Icons -->
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="apple-touch-icon" href="images/apple-touch-icon.png">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">    
	<!-- Site CSS -->
    <link rel="stylesheet" href="css/style.css">    
    <!-- Responsive CSS -->
    <link rel="stylesheet" href="css/responsive.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/custom.css">

    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
	<header class="top-navbar">
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<div class="container">
			
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbars-rs-food" aria-controls="navbars-rs-food" aria-expanded="false" aria-label="Toggle navigation">
				  <span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbars-rs-food">
					<ul class="navbar-nav ml-auto">
						<li class="nav-item"><a class="nav-link" href="/">Trang Chủ</a></li>
						<li class="nav-item"><a class="nav-link" href="gioithieu">Giới Thiệu</a></li>
						<li class="nav-item"><a class="nav-link" href="/dattiec">Đặt Tiệc</a></li>
						<li class="nav-item"><a class="nav-link" href="sanh">Sảnh Tiệc</a></li>
						<li class="nav-item dropdown active">
							<a class="nav-link dropdown-toggle" href="#" id="dropdown-a" data-toggle="dropdown">Thực Đơn</a>
							<div class="dropdown-menu" aria-labelledby="dropdown-a">
								<c:forEach var="lm" items="${loaimon }">
									<a class="dropdown-item" href="mon?id=${lm.id}">${lm.ten }</a>
								</c:forEach>
							</div>
						</li>
						<li class="nav-item"><a class="nav-link" href="/combo">Combo</a></li>
						<c:if test="${userDangNhap==null}">
							<li class="nav-item"><a class="nav-link" href="formdangky">ĐĂNG KÝ</a></li>
							<li class="nav-item"><a class="nav-link" href="formdangnhap">ĐĂNG NHẬP</a></li>
						</c:if>
						<c:if test="${userDangNhap!=null}">
							<li class="nav-item dropdown">
								<a class="nav-link dropdown-toggle" href="#" id="dropdown-a" data-toggle="dropdown"><i class="fa fa-user"></i> ${userDangNhap.ten}</a>
								<div class="dropdown-menu" aria-labelledby="dropdown-a">
									<a class="dropdown-item" href="tiecdadat"><i class="fa fa-pencil"></i> LỊCH SỬ ĐẶT TIỆC</a>
									<a class="dropdown-item" href="ttcanhan"><i class="fa fa-newspaper-o"></i> XEM THÔNG TIN CÁ NHÂN</a>
									<a class="dropdown-item" href="formdoimatkhau"><i class="fa fa-edit"></i> ĐỔI MẬT KHẨU</a>	
									<a class="dropdown-item" href="/dangnhap/dangxuat"><i class="fa fa-sign-out"></i> ĐĂNG XUẤT</a>
								</div>
							</li>
						</c:if>
					</ul>
				</div>
			</div>
		</nav>
	</header>
	<!-- End header -->
	
	<!-- Start header -->
	<div class="all-page-title page-breadcrumb">
		<div class="container text-center">
			<div class="row">
				<div class="col-lg-12">
					<h1>Thực Đơn</h1>
				</div>
			</div>
		</div>
	</div>
	<!-- End header -->
	
	<<!-- Start Menu -->
	<div class="menu-box">
		<div class="container">
			
			<div class="row inner-menu-box">
			<div class="col-lg-6">
						</div>
						<div class="col-lg-6">
							<div class="heading-title text-center">
								<form action="timmon" method="get">
								<div class="row no-gutters">
								<div class="col-lg-10">
									<input class="form-control" id="tim" name="tim" value="${keyword }" placeholder="Nhập từ khóa tìm" type="text">
									</div>
									<div class="col-lg-2">
									<button type="submit" class="form-control"><i class="fa fa-search" aria-hidden="true"></i> Tìm</button>
									</div>
									</div>
								</form>
							</div>
						</div>
				<div class="col-3">
					<div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
						<c:forEach var="lm" items="${loaimon }">
							<a class="nav-link" href="/mon?id=${lm.id }">${lm.ten}</a>
						</c:forEach></div>
				</div>
				
				<div class="col-9">
					<div class="tab-pane fade show active" id="v-pills-home" role="tabpanel" aria-labelledby="v-pills-home-tab">
							<div class="row">
								<p>${dsrong }</p>
							<c:forEach var="mon" items="${mon }">
							
								<div class="col-lg-4 col-md-6 special-grid drinks">
								
									<div class="gallery-single fix">
									<a href="chitietmon?id=${mon.id }">
										<img src="${mon.hinh }" class="img-fluid khuongds" alt="Image">
									</a>	
									</div>
									<a href="chitietmon?id=${mon.id }">
									<h1 class="color-Maroon">${mon.ten }</h1>
									<p>${mon.giaFM }</p> 
											<br><br>
								
								</div>	
								
							</c:forEach>
								
							</div>
							
						</div>
						<c:if test="${pageHienTai!=null }">
						<div class="submit-button text-center">						
							<a class="page-style1" href="mon?id=${idloai }&page=${pageTruoc}">Trước </a>
							<c:if test="${page1!=null}">
							<a class="page-style1" href="mon?id=${idloai }&page=${page1}">${page1 }</a></c:if>
							<c:if test="${page2!=null}">
							<a class="page-style1" href="mon?id=${idloai }&page=${page2}">${page2 }</a></c:if>
							<a class="page-style" href="">${pageHienTai }</a>
							<c:if test="${page4!=null}">
							<a class="page-style1" href="mon?id=${idloai }&page=${page4}">${page4 }</a></c:if>
							<c:if test="${page5!=null}">
							<a class="page-style1" href="mon?id=${idloai }&page=${page5}">${page5 }</a></c:if>
							<a class="page-style1" href="mon?id=${idloai }&page=${pageSau}">Sau </a>
						</div></c:if>
				</div>
			</div>
		</div>
	</div>
	
	
	<footer class="footer-area bg-f">
		<div class="container">
			<div class="row">
				
				<div class="col-lg-4 col-md-6">
					<h3>Họ và tên</h3>
					<p class="lead">Đặng Minh Kha</p>
					<p class="lead">Nguyễn Vũ Như Thụy</p>
				</div>
				<div class="col-lg-4 col-md-6">
					<h3>Mã số sinh viên</h3>
					<p>15058631</p>
					<p>15027621</p>
				</div>
				<div class="col-lg-4 col-md-6">
					<h3>Lớp</h3>
					<p>DHKTPM11A</p>
					<p>DHKTPM11A</p>
				</div>
			</div>
		</div>
		
		<div class="copyright">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<p class="company-name">Khóa Luận Tốt Nghiệp</p> 
					</div>
				</div>
			</div>
		</div>
		
	</footer>
	
	<a href="#" id="back-to-top" title="Back to top" style="display: none;"><i class="fa fa-paper-plane-o" aria-hidden="true"></i></a>

	<!-- ALL JS FILES -->
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
    <!-- ALL PLUGINS -->
	<script src="js/jquery.superslides.min.js"></script>
	<script src="js/images-loded.min.js"></script>
	<script src="js/isotope.min.js"></script>
	<script src="js/baguetteBox.min.js"></script>
	<script src="js/form-validator.min.js"></script>
    <script src="js/contact-form-script.js"></script>
    <script src="js/custom.js"></script>
</body>
</html>