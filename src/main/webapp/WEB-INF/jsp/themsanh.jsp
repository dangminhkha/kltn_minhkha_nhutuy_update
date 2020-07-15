<%@page import="com.example.demo.utils.StringUtil"%>
<%@page import="com.example.demo.dtos.NhanVienDTO"%>
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
    <title>Thêm sãnh</title>  
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
	<link rel="stylesheet" href="css/knockout-file-bindings.css">
	    <style type="text/css">
	    .hinh {
	        max-width: 750px;
	        padding: 15px;
	    }
	</style>
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
						
						<li class="nav-item active"><a class="nav-link" href="/qlsanh">Sãnh</a></li>
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
					<h1>QUẢN LÝ NHÂN VIÊN</h1>
				</div>
			</div>
		</div>
	</div>
	<!-- End All Pages -->
	
	<!-- Start Contact -->
	<div class="contact-box">
		<div class="container">
			<div class="row">
				<div class="col-lg-8">
				
				<form action="/luusanh" id="sanhForm" method="post" enctype="multipart/form-data">
						<p>Tên </p><input class="form-control" type="text" name="ten" id="ten" placeholder="Tên" value="${dto.ten }" /><br>
						<p class="error-ten color-red">${loiten }</p>
						<p>Số khách tối thiểu </p><input class="form-control" type="text" name="sokhachtoithieu" id="sokhachtoithieu" placeholder="Số khách tối thiểu" value="${dto.sokhachtoithieu }" /><br>
						<p class="error-sokhachtoithieu color-red">${sokhachtt }</p>
						<p>Số khách tối đa </p><input class="form-control" type="text" name="sokhachtoida" id="sokhachtoida" placeholder="Số khách tối đa" value="${dto.sokhachtoida }"><br>
						<p class="error-sokhachtoida color-red">${loisokhach }</p>
							<p>Hình</p>
								
									 <div class="hinh">
                                        <div class="well" data-bind="fileDrag: multiFileData">
                                            <div class="form-group row">
                                                <div class="col-md-6">
                                                    <!-- ko foreach: {data: multiFileData().dataURLArray, as: 'dataURL'} -->
                                                    <img style="height: 100px; margin: 5px;" class="img-rounded  thumb"
                                                        data-bind="attr: { src: dataURL }, visible: dataURL">
                                                    <!-- /ko -->
                                                    <div data-bind="ifnot: fileData().dataURL">
                                                        <label class="drag-label">Hình ảnh</label>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                        <input  type="file" id="file"  name="file"  data-bind="fileInput: multiFileData, customFileInput: {
                                                                  buttonClass: 'btn btn-success',
                                                                  fileNameClass: 'disabled form-control',
                                                                  onClear: onClear,
                                                                  onInvalidFileDrop: onInvalidFileDrop
                                                                }" accept="image/*">
                                                            
                                                </div>
                                            </div>
                                        </div>
                                    </div>
										<p class="color-red">${hinhloi }</p>
						<div class="submit-button text-center">
							<input type="submit" class="btn btn-common" value="Lưu">
							<p class="error color-red"></p>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- End Contact -->
	
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
	 <script src='http://cdnjs.cloudflare.com/ajax/libs/knockout/3.1.0/knockout-min.js'></script>
    <script src='js/knockout-file-bindings.js'></script>
    <script>
        var viewModel = {};
        viewModel.fileData = ko.observable({
            dataURL: ko.observable(),
            // can add "fileTypes" observable here, and it will override the "accept" attribute on the file input
            // fileTypes: ko.observable('.xlsx,image/png,audio/*')
        });
        viewModel.multiFileData = ko.observable({ dataURLArray: ko.observableArray() });
        viewModel.onClear = function (fileData) {
            if (confirm('Are you sure?')) {
                fileData.clear && fileData.clear();
            }
        };
        viewModel.debug = function () {
            window.viewModel = viewModel;
            console.log(ko.toJSON(viewModel));
            debugger;
        };
        viewModel.onInvalidFileDrop = function (failedFiles) {
            var fileNames = [];
            for (var i = 0; i < failedFiles.length; i++) {
                fileNames.push(failedFiles[i].name);
            }
            var message = 'Invalid file type: ' + fileNames.join(', ');
            alert(message)
            console.error(message);
        };
        ko.applyBindings(viewModel);
    </script>
</body>
</html>