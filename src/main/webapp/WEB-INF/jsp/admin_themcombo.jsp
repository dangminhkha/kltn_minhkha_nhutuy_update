<%@page import="com.example.demo.utils.StringUtil"%>
<%@page import="com.example.demo.dtos.NhanVienDTO"%>
<%@page import="com.example.demo.dtos.MonDTO"%>
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
    <title>Thêm combo</title>  
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
							</li>						<li class="nav-item dropdown active">
								<a class="nav-link dropdown-toggle" href="#" id="dropdown-a" data-toggle="dropdown"><i class="fa fa-bars"></i> Thực Đơn</a>
								<div class="dropdown-menu" aria-labelledby="dropdown-a">
									<a class="dropdown-item" href="/qlmon"><i class="fa fa-life-ring"></i> Món</a>
									<a class="dropdown-item" href="/qlloai"><i class="fa fa-tags" aria-hidden="true"></i> Loại Món</a>
									<a class="dropdown-item" href="/qlcombo"><i class="fa fa-cutlery"></i> Combo</a>
								</div>
							</li>
						
						<li class="nav-item"><a class="nav-link" href="/qlsanh">Sãnh</a></li>
						<li class="nav-item"><a class="nav-link" href="/dsdattiecadmin">Đơn Tiệc</a></li>
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
					<h1>QUẢN LÝ COMBO</h1>
				</div>
			</div>
		</div>
	</div>
	<!-- End All Pages -->
	
	<!-- Start Menu -->
	<div class="menu-box">
		<div class="container">
			<div class="row inner-menu-box">
				
				<div class="col-8">
					<div class="tab-content" id="v-pills-tabContent">
					
						<div class="tab-pane fade show active" id="v-pills-home" role="tabpanel" aria-labelledby="v-pills-home-tab">
							<form id="formdata">
								<p>Tên</p><input class="form-control" type="text" onblur="themcombo()" name="ten" id="ten" placeholder="Tên combo" value="${combo.ten}" /><br>
								<p class="error-ten color-red"></p>
								<p>Giá</p><input class="form-control" type="text" onblur="themcombo()" name="gia" id="gia" placeholder="Giá" value="${combo.gia}" /><br>
								<p class="error-gia color-red"></p>
								<p>Danh sách món</p>
								<div id="ctcombos">
									<div class="row inner-menu-box">
									<div class="col-2">
										<p>Số thứ tự</p><p class="form-control" type="text" name="stt1" id="stt1" placeholder="Số thứ tự">1</p><br>
										</div>
										<div class="col-10">
										<p>Món</p>
										<select class="form-control" onclick="themcombo()" name="ten1" id="ten1">
										<c:forEach var="mon" items="${moncombos }">
							 				<option>${mon.ten }</option>
										</c:forEach>
										
										</select>
										</div>
									</div>
								</div>
								
								            <input  type="hidden" id="slmon" name="slmon" value="1"/>
								
								<div class="submit-button text-center">
								<div class="top-section">
					                <img src="/images/cong.png" onclick="addItem()" width="50px" style="margin-right: 100px;"/>
					                <img src="/images/tru.png" onclick="removeItem()" width="50px" style="margin-left: 100px;"/>
					            </div>
									
									<p class="error color-red"></p>
								</div>
								
							</form>
							<div class="submit-button text-center">
							<div class="tieptuc"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End Menu -->
	
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
	<script >
        var itemND = document.getElementById('ctcombos');
        var a = 1;
        function addItem() {
            a++;
            if(a <=8){
            var div1 = document.createElement("div");
            div1.className = "col-2";
            

			var input1 = document.createElement("p");
			input1.className = "form-control";
			input1.setAttribute("type","text");
			input1.setAttribute("id","stt"+a);
			input1.setAttribute("name","stt"+a);
			input1.innerText = a;

			var br = document.createElement("br");
			div1.appendChild(input1);
			div1.appendChild(br);

			var div2 = document.createElement("div");
            div2.className = "col-10";
            
			var select = document.createElement("select");
			select.className = "form-control";
			select.setAttribute("onclick","themcombo()");
			select.setAttribute("type","text");
			select.setAttribute("id","ten"+a);
			select.setAttribute("name","ten"+a);
			<% List<MonDTO> ds = (List<MonDTO>)session.getAttribute("moncombos");%>
			<% for(int i = 0; i < ds.size() ; i++){%>
			<% String ten = ds.get(i).getTen();%>
			var option = document.createElement("option");
			option.innerText = "<%=ten%>";
			select.appendChild(option);
			<%}%>
			
			
			div2.appendChild(select);
			var div = document.createElement("div");
            div.className = "row inner-menu-box";
            div.setAttribute("id",a);
            
            
            div.appendChild(div1);
            div.appendChild(div2);

            itemND.appendChild(div);

            document.getElementById("slmon").setAttribute("value",a);
            themcombo();
            }else{
                alert("không thể thêm");
            }
        
        }

        function removeItem(){
            if(a !==1){
                if(confirm('Xóa món khỏi combo')){
                    var myobj = document.getElementById(a);
                    myobj.remove();
                    a--;
                    document.getElementById("slmon").setAttribute("value",a);
                    themcombo();
                }
            }else{
                alert("không thể xóa");
            }
        }
    </script>
	
	
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
	<script src="js/jquery.mapify.js"></script>
	<script src="js/form-validator.min.js"></script>
    <script src="js/contact-form-script.js"></script>
    <script src="js/custom.js"></script>
	<script>
		$('.map-full').mapify({
			points: [
				{
					lat: 40.7143528,
					lng: -74.0059731,
					marker: true,
					title: 'Marker title',
					infoWindow: 'Live Dinner Restaurant'
				}
			]
		});	
	</script>
</body>
</html>