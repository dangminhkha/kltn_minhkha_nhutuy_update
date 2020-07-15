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
    <title>Giới thiệu</title>  
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
	<!-- Start header -->
	<header class="top-navbar">
		<header class="top-navbar">
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<div class="container">
			
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbars-rs-food" aria-controls="navbars-rs-food" aria-expanded="false" aria-label="Toggle navigation">
				  <span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbars-rs-food">
					<ul class="navbar-nav ml-auto">
						<li class="nav-item"><a class="nav-link" href="/">Trang Chủ</a></li>
						<li class="nav-item active"><a class="nav-link" href="gioithieu">Giới Thiệu</a></li>
						<li class="nav-item"><a class="nav-link" href="/dattiec">Đặt Tiệc</a></li>
						<li class="nav-item"><a class="nav-link" href="sanh">Sảnh Tiệc</a></li>
						<li class="nav-item dropdown">
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
					<h1>GIỚI THIỆU</h1>
				</div>
			</div>
		</div>
	</div>
	<!-- End header -->
	
	<!-- Start About -->
	<div class="about-section-box">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6 text-center">
					<div class="inner-column">
						<h1>Welcome To <span>Live Dinner Restaurant</span></h1>
						<p>Live Dinner Restaurant là một trong những Trung tâm hội nghị - tiệc cưới có vị trí thuận lợi hàng đầu cho một loạt các quận miền Đông thành phố như quận Trung tâm Q.1, Tân Bình. Nhà hàng cưới Hoàng Long không chỉ tọa lạc tại khuôn viên rợp bóng cây xanh mà còn nằm trên mặt tiền tuyến đường trung tâm Lê Duẫn của Quận 1, tiện lợi cho giao thông di chuyển trong địa bàn quận và các khu vực xung quanh. Điều này góp phần tạo nên một Hoàng Long sang trọng nhưng lại đủ Xanh - Sạch - Yên tĩnh cho những buổi sự kiện, hội nghị, đại hội cổ đông... </p>
						<p>Bên cạnh đó Live Dinner Restaurant còn chuyên về tổ chức tiệc cưới cho các cặp đôi, với chi phí phù hợp cùng những dịch vụ đa dạng sẽ mang lại cho bạn một sự hài lòng khi thực hiện buổi tiệc cưới như mơ của mình.</p>
					</div>
				</div>
				<div class="col-lg-6 col-md-6">
					<img src="images/about-img.jpg" alt="" class="img-fluid">
				</div>
				<div class="col-md-12 ">
					<div class="inner-pt">
						<p>Kể về Live Dinner Restaurant, không chỉ nhắc đến những bữa tiệc hào nhoáng trong khuôn viên rộng lớn, những đường lượn sóng mềm mại đặc trưng, mỗi sâu khấu nền được chăm chút từng chi tiết, những món ngon đúng khẩu vị Á -Âu từ bàn tay tài hoa của các đầu bếp chuyên nghiệp chế biến công phu. Ẩm thực là phần quan trọng của tiệc chiêu đãi, thể hiện sự giàu có, mến khách của gia đình cô dâu chú rể. </p>
						<p>Về cơ bản các món ăn trong cùng một bữa tiệc phải hòa hợp về nguyên liệu, khẩu vị và phong cách trang trí; mỗi set menu món ăn tiệc cưới, hội nghị cũng cần đáp đứng được khẩu vị của khách mời đó cũng là kim chỉ nam khi chuẩn bị bàn tiệc cho mỗi đám cưới, sự kiện của tất cả các nhân viên tại địa điểm tổ chức tiệc cưới, tổ chức hội nghị sự kiện Hoàng Long. Từ khâu tư vấn xây dựng thực đơn, chọn nguyên liệu, hải sản, nấu và nếm gia vị đến lúc trang trí món ăn… tất cả đều được các đầu bếp của chăm chút từng chút một bằng tất cả lòng tận tâm và đam mê nghề nghiệp. Chính vì vậy khi thưởng thức món ăn tại Hoàng Long, ngoài cảm nhận sự hài hòa của món ăn thực khách còn cảm được nét tinh thần của các đầu bếp trong đó. </p>
						<p>Nổi bật với những sảnh tiệc sang trọng mang đậm chất Châu Âu hiện đại và những sảnh tiệc được thiết kế truyền thống phù hợp với sở thích của từng cặp đôi. Chính sự linh hoạt và đa dạng trong thiết kế đã giúp Live Dinner Restaurant viết nên câu chuyện cổ tích trong mơ của chính bạn chứ không phải của ai khác.</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End About -->
	
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