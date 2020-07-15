<%@page import="com.example.demo.dtos.NhanVienDTO"%>
<%@page import="com.example.demo.utils.StringUtil"%>
<%@page import="com.example.demo.dtos.TTDatTiecDTO"%>
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
    <title>Thanh toán</title>  
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
						<li class="nav-item "><a class="nav-link" href="/">Trang Chủ</a></li>
						<%NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap"); 
						if(StringUtil.ADMIN.equals(userDN.getChucvu())){
						%>
						<li class="nav-item"><a class="nav-link" href="/thongke">Thống Kê</a></li>
						
						<li class="nav-item dropdown">
								<a class="nav-link dropdown-toggle" href="#" id="dropdown-a" data-toggle="dropdown"><i class="fa fa-users"></i> Người Dùng</a>
								<div class="dropdown-menu" aria-labelledby="dropdown-a">
									<a class="dropdown-item" href="/qlnhanvien"><i class="fa fa-sitemap"></i> Nhân Viên</a>
									<a class="dropdown-item" href="/bangluong"><i class="fa fa-money" aria-hidden="true"></i> Xem Bảng Lương</a>
									<a class="dropdown-item" href="/qlkhachhang"><i class="fa fa-male"></i> Khách Hàng</a>
								</div>
							</li>
						<%} %>
<li class="nav-item dropdown">
								<a class="nav-link dropdown-toggle" href="#" id="dropdown-a" data-toggle="dropdown"><i class="fa fa-th-large" aria-hidden="true"></i> Thời Vụ</a>
								<div class="dropdown-menu" aria-labelledby="dropdown-a">
									<a class="dropdown-item" href="/dsnvthoivu"><i class="fa fa-th-list" aria-hidden="true"></i> Danh sách nhân viên thời vụ</a>
									<a class="dropdown-item" href="/nhanvienthoivu"><i class="fa fa-odnoklassniki" aria-hidden="true"></i> Nhân viên thời vụ hôm nay</a>
									<a class="dropdown-item" href="/dsnhomphucvu"><i class="fa fa-object-group" aria-hidden="true"></i> Nhóm phục vụ</a>
								</div>
							</li>						<li class="nav-item dropdown">
								<a class="nav-link dropdown-toggle" href="#" id="dropdown-a" data-toggle="dropdown"><i class="fa fa-bars"></i> Thực Đơn</a>
								<div class="dropdown-menu" aria-labelledby="dropdown-a">
									<a class="dropdown-item" href="/qlmon"><i class="fa fa-life-ring"></i> Món</a>
									<a class="dropdown-item" href="/qlloai"><i class="fa fa-tags" aria-hidden="true"></i> Loại Món</a>
									<a class="dropdown-item" href="/qlcombo"><i class="fa fa-cutlery"></i> Combo</a>
								</div>
							</li>
						
						<li class="nav-item"><a class="nav-link" href="/qlsanh">Sãnh</a></li>
						<li class="nav-item active"><a class="nav-link" href="/dsdattiecadmin">Đơn Tiệc</a></li>
						<c:if test="${userDangNhap==null}">
							<li class="nav-item"><a class="nav-link" href="formdangky">ĐĂNG KÝ</a></li>
							<li class="nav-item"><a class="nav-link" href="formdangnhap">ĐĂNG NHẬP</a></li>
						</c:if>
						<c:if test="${userDangNhap!=null}">
							<li class="nav-item dropdown">
								<a class="nav-link dropdown-toggle" href="#" id="dropdown-a" data-toggle="dropdown"><i class="fa fa-user"></i> ${userDangNhap.ten}</a>
								<div class="dropdown-menu" aria-labelledby="dropdown-a">
									<a class="dropdown-item" href="ttnhanvien"><i class="fa fa-newspaper-o"></i> XEM THÔNG TIN CÁ NHÂN</a>
									<a class="dropdown-item" href="formdoimknv"><i class="fa fa-edit"></i> ĐỔI MẬT KHẨU</a>
									<a class="dropdown-item" href="/dangnhap/dangxuat"><i class="fa fa-sign-out"></i> ĐĂNG XUẤT</a>
								</div>
							</li>
						</c:if>
					</ul>
				</div>
			</div>
		</nav>
	</header>
	<div class="all-page-title page-breadcrumb">
		<div class="container text-center">
			<div class="row">
				<div class="col-lg-12">
					<h1>THANH TOÁN</h1>
				</div>
			</div>
		</div>
	</div>
	<!-- End header -->
	
	<<!-- Start Menu -->
	<div class="menu-box">
		<div class="container">
			
			<div class="row inner-menu-box">
				<div class="col-12">
				<div class="row">
						<div class="col-lg-12">
							<div class="heading-title text-center">
								<h2>Thông tin thanh toán</h2>
							</div>
						</div>
					</div>
					<div class="row">
				<div class="col-lg-12">
							<form id="thanhtoanform">
							<table class="table"  id="cthoadons" >
								<tr>
									<td>Tên</td>
									<td>Giá</td>
									<td>Số lượng</td>
								</tr>
								<tr>
									<td>${ttdtthanhtoan.combo.ten }</td>
									<td>${ttdtthanhtoan.combo.giaFM }</td>
									<td>
										<input type="text" id="montt" name="montt" class="form-control" onblur="thanhtoan()" value="${ttdtthanhtoan.soban }">
										<input  type="hidden" id="idmon" name="idmon" value="${ttdtthanhtoan.combo.id }"/>
										<input  type="hidden" id="idttdt" name="idttdt" value="${ttdtthanhtoan.id }"/>
									</td>
								</tr>
								<%int i=1; %>
								<c:forEach var="ctnuoc" items="${dsctdt}">
								<tr>
								<td>${ctnuoc.mon.ten }</td>
									<td>${ctnuoc.mon.giaFM }</td>
									<td>
										<input type="text" id="<%=i %>" name="<%=i %>" onblur="thanhtoan()" class="form-control" >
										<input  type="hidden" id="idnuoc<%=i %>" name="idnuoc<%=i %>" value="${ctnuoc.mon.id}"/>
										<%i++; %>
									</td>
									</tr>
								</c:forEach>									
							</table>
							
						
							<input  type="hidden" id="slnuoc" name="slnuoc" value="${slnuoc }"/>
							<input  type="hidden" id="slcthoadon" name="slcthoadon" value="0"/>
							
							<div class="submit-button text-center">
							<div class="top-section">
							 <input  type="hidden" id="slcthoadon" name="slcthoadon" value="0"/>
					                <img src="/images/cong.png" onclick="addItem()" width="50px" style="margin-right: 100px;"/>
					                <img src="/images/tru.png" onclick="removeItem()" width="50px" style="margin-left: 100px;"/>
					            </div>
							<p class="error color-red"></p>
								<div class="tieptuc"></div>
								
							</div>
							</form>
							</div>
						</div>
				</div>
			</div>
		</div>
	</div>
	
	<script >
        var itemND = document.getElementById('cthoadons');
        var a = 0;
        function addItem() {
            a++;
            var tr = document.createElement("tr");
            tr.setAttribute("id","tr"+a);
            var td1 = document.createElement("td");
            var inputten = document.createElement("input");
            inputten.setAttribute("id","ten"+a);
            inputten.setAttribute("name","ten"+a);
            inputten.setAttribute("placeholder","Tên");
            inputten.setAttribute("type","text");
            inputten.setAttribute("class","form-control");
            inputten.setAttribute("onblur","thanhtoan()");
            td1.appendChild(inputten);
            

                
            var td2 = document.createElement("td");
            var inputgia = document.createElement("input");
            inputgia.setAttribute("id","gia"+a);
            inputgia.setAttribute("name","gia"+a);
            inputgia.setAttribute("placeholder","Giá");
            inputgia.setAttribute("type","text");
            inputgia.setAttribute("class","form-control");
            inputgia.setAttribute("onblur","thanhtoan()");
            td2.appendChild(inputgia);

            
            var td3 = document.createElement("td");
            var inputsl = document.createElement("input");
            inputsl.setAttribute("id","sl"+a);
            inputsl.setAttribute("name","sl"+a);
            inputsl.setAttribute("placeholder","Số Lượng");
            inputsl.setAttribute("type","text");
            inputsl.setAttribute("class","form-control");
            inputsl.setAttribute("onblur","thanhtoan()");
            td3.appendChild(inputsl);

            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            

            itemND.appendChild(tr);
            document.getElementById("slcthoadon").setAttribute("value",a);
            $('.tieptuc').html('');
    
        }

        function removeItem(){
        	var myobj = document.getElementById("tr"+a);
            myobj.remove();
            a--;
            document.getElementById("slcthoadon").setAttribute("value",a);
        }
    </script>
	
	
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