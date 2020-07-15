$("#formtinhluong").validator().on("submit", function(event) {
	if (event.isDefaultPrevented()) {

	} else {
		var errors = false;
		var ngaycongthang = $("#ngaycongthang").val();
		var nam = $("#nam").val();
		var thang = $("#thang").val();
		var slnv = $("#slnv").val();
		

		var regexSoNguyen = /^[0-9]+\b/;

		
		if (!validateTest(regexSoNguyen, ngaycongthang)) {
			$('.error-ngaycongthang').html('Ngày công tháng là số nguyên lơn hơn 0');
			errors = true;
		} else {
			$('.error-ngaycongthang').html('');

		}
		
		for (var i = 1; i <= slnv; i++) {
		var songaycong = $("#songaycong" + i).val();
		if (!validateTest(regexSoNguyen, songaycong)||songaycong<0) {
			$('.error-songaycong'+i).html('Số công là số nguyên lơn hơn 0');
				errors = true;
		} else {
			$('.error-songaycong'+i).html('');

		}
	}
		
		if (errors == true) {
			return false;
		}

		submitFormTinhLuong();
		window.location = "qlnhanvien";
		return false;
	}
});

$("#formduyet").validator().on("submit", function(event) {
	if (event.isDefaultPrevented()) {

	} else {
		var errors = false;
		var tiencoc = $("#tiencoc").val();
		var regextiencoc = /^[0-9]+\b/;
		

		if (!validateTest(regextiencoc, tiencoc)) {
			$('.error-tiencoc').html('Tiền cọc là ký tự số không được bỏ trống');
			errors = true;
		} else {

			$('.error-cmnd').html('');
		}

		if (errors == true) {

			$('.error').html('Dữ liệu vào không hợp lệ vui lòng kiểm tra lại');
			return false;
			
		}

		return true;

	}
});

$("#formnhomthoivu").validator().on("submit", function(event) {
	if (event.isDefaultPrevented()) {

	} else {
		var errors = false;
		var ten = $("#ten").val();
		var luong = $("#luong").val();
		var regexSo = /^[0-9]+\b/;
		
		if (ten.length == 0) {
			$('.error-ten').html('Tên không được bỏ trống');
			errors = true;
		} else {

			$('.error-ten').html('');
		}
		if (!validateTest(regexSo, luong)) {
			$('.error-luong').html('Lương là ký tự số và lớn hơn 0');
			errors = true;
		} else {

			$('.error-luong').html('');
		}

		if (errors == true) {

			return false;
			
		}

		return true;

	}
});

$("#formnvtv").validator().on("submit", function(event) {
	if (event.isDefaultPrevented()) {

	} else {
		var errors = false;
		var ten = $("#ten").val();
		var cmnd = $("#cmnd").val();
		var sodienthoai = $("#sodienthoai").val();
		
		var regexSTD = /((09|03|07|08|05)+([0-9]{8})\b)/;
		var regexCMND = /^[0-9]{9,13}\b/
		
		if (ten.length == 0) {
			$('.error-ten').html('Tên không được bỏ trống');
			errors = true;
		} else {

			$('.error-ten').html('');
		}
		if (!validateTest(regexSTD, sodienthoai)) {
			$('.error-sodienthoai').html('Số điện thoại không đúng định dạng');
			errors = true;
		} else {
			$('.error-sodienthoai').html('');
		}
		
		if (!validateTest(regexCMND, cmnd)) {
			$('.error-cmnd').html('Số chứng minh từ 9 đến 13 số');
		errors = true;
		} else {
			$('.error-cmnd').html('');
		}
		if (errors == true) {

			return false;
			
		}

		return true;

	}
});

function themcombo() {
	var errors = false;
		var ten = $("#ten").val();
		var gia = $("#gia").val();

		var regexGia = /^[0-9]+\b/;

		if (ten.length == 0) {
			$('.error-ten').html('Tên không được bỏ trống');
			errors = true;
		} else {
			$('.error-ten').html('');
		}
		if (!validateTest(regexGia, gia)) {
			$('.error-gia').html('Giá không được bỏ trống, là ký tự số và lớn hơn 0');
			errors = true;
		} else {
			$('.error-gia').html('');

		}
		if (errors == true) {
		$('.tieptuc').html('');
			return false;
		}else{
		$('.tieptuc').html('<a class="btn btn-common" href="tieptuccombo">Tiếp Tục</a>');
		}
		submitForm();
}

function thanhtoan() {
	var errors = false;
	var regexSL = /^[0-9]+\b/;
	var a = $("#slnuoc").val();
	var slcthoadon = $("#slcthoadon").val();
	var slcombo = $("#montt").val();
	if (!validateTest(regexSL, slcombo)||slcombo<0) {
		errors = true;
	}
	for (var i = 1; i <= a; i++) {
		var soluong = $("#" + i).val();
		if (!validateTest(regexSL, soluong)||soluong<0) {
			errors = true;
		}
	}
	
	for (var i = 1; i <= slcthoadon; i++) {
		var ten = $("#ten" + i).val();
		if (ten.length==0) {
			errors = true;
		}
		var gia = $("#gia" + i).val();
		if (!validateTest(regexSL, soluong)||soluong<0) {
			errors = true;
		}
		var soluong = $("#sl" + i).val();
		if (!validateTest(regexSL, soluong)||soluong<0) {
			errors = true;
		}
		
	}
	
	if (errors == true) {
		$('.error').html('Tên không được bỏ trống, số lượng và giá là số nguyên dương');
		$('.tieptuc').html('');
		return false;
	} else {
		$('.error').html('');
		$('.tieptuc').html('<a class="btn btn-common" href="tieptucthanhtoan">Tiếp Tục</a>');
	}
	submitFormTT();
}

$("#dangkyform")
		.validator()
		.on(
				"submit",
				function(event) {
					if (event.isDefaultPrevented()) {

					} else {
						var errors = false;
						var ten = $("#ten").val();
						var ngaysinh = $("#ngaysinh").val();
						var noisinh = $("#noisinh").val();
						var sodienthoai = $("#sodienthoai").val();
						var email = $("#email").val();
						var socmnd = $("#socmnd").val();
						var matkhau = $("#matkhau").val();

						var regexEmail = /^[a-zA-Z0-9_.+-]+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
						var regexSTD = /((09|03|07|08|05)+([0-9]{8})\b)/;
						var regexCMND = /^[0-9]{9,13}\b/
						var regexMK = /(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$/
						if (ten.length == 0) {
							$('.error-ten').html('Tên không được bỏ trống');
							errors = true;
						} else {
							$('.error-ten').html('');
						}

						if (ngaysinh.length == 0) {
							$('.error-ngaysinh')
									.html('Vui lòng chọn ngày sinh');
							errors = true;
						} else {
							$('.error-ngaysinh').html('');
						}
						if (noisinh.length == 0) {
							$('.error-noisinh').html(
									'Nơi sinh không được bỏ trống');
							errors = true;
						} else {
							$('.error-noisinh').html('');
						}
						if (!validateTest(regexSTD, sodienthoai)) {
							$('.error-sodienthoai').html(
									'Số điện thoại không đúng định dạng');
							errors = true;
						} else {
							$('.error-sodienthoai').html('');
						}
						if (!validateTest(regexEmail, email)) {
							$('.error-email')
									.html('Email không đúng định dạng');
							errors = true;
						} else {
							$('.error-email').html('');
						}
						if (!validateTest(regexCMND, socmnd)) {
							$('.error-cmnd').html(
									'Số chứng minh từ 9 đến 13 số');
							errors = true;
						} else {
							$('.error-cmnd').html('');
						}
						if (!validateTest(regexMK, matkhau)) {
							$('.error-mk')
									.html(
											'Mật khẩu ít nhất 8 ký tự bao gồm một chữ cái viết hoa, một chữ cái viết thường và một số hoặc ký tự đặc biệt');
							errors = true;
						} else {
							$('.error-mk').html('');
						}
						if (errors == true) {
							$('.error')
									.html(
											'Dữ liệu vào không hợp lệ vui lòng kiểm tra lại');
							return false;
						}
						return true;

					}
				});

$("#nhanvienForm")
		.validator()
		.on(
				"submit",
				function(event) {
					if (event.isDefaultPrevented()) {

					} else {
						var errors = false;
						var ten = $("#ten").val();
						var ngaysinh = $("#ngaysinh").val();
						var noisinh = $("#noisinh").val();
						var sodienthoai = $("#sodienthoai").val();
						var email = $("#email").val();
						var socmnd = $("#socmnd").val();
						var matkhau = $("#matkhau").val();
						var luong = $("#luong").val();

						var regexEmail = /^[a-zA-Z0-9_.+-]+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
						var regexSTD = /((09|03|07|08|05)+([0-9]{8})\b)/;
						var regexCMND = /^[0-9]{9,13}\b/;
						var regexLuong = /^[0-9]+\b/;
						var regexMK = /(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$/
						if (ten.length == 0) {
							$('.error-ten').html('Tên không được bỏ trống');
							errors = true;
						} else {
							$('.error-ten').html('');
						}

						if (ngaysinh.length == 0) {
							$('.error-ngaysinh')
									.html('Vui lòng chọn ngày sinh');
							errors = true;
						} else {
							$('.error-ngaysinh').html('');
						}
						if (noisinh.length == 0) {
							$('.error-noisinh').html(
									'Nơi sinh không được bỏ trống');
							errors = true;
						} else {
							$('.error-noisinh').html('');
						}
						if (!validateTest(regexSTD, sodienthoai)) {
							$('.error-sodienthoai').html(
									'Số điện thoại không đúng định dạng');
							errors = true;
						} else {
							$('.error-sodienthoai').html('');
						}
						if (!validateTest(regexEmail, email)) {
							$('.error-email')
									.html('Email không đúng định dạng');
							errors = true;
						} else {
							$('.error-email').html('');
						}
						if (!validateTest(regexCMND, socmnd)) {
							$('.error-cmnd').html(
									'Số chứng minh từ 9 đến 13 số');
							errors = true;
						} else {
							$('.error-cmnd').html('');
						}
						if (!validateTest(regexLuong, luong)) {
							$('.error-luong').html(
									'Lương không được bỏ trống và là ký tự số');
							errors = true;
						} else {
							$('.error-luong').html('');
						}
						if (!validateTest(regexMK, matkhau)) {
							$('.error-mk')
									.html(
											'Mật khẩu ít nhất 8 ký tự bao gồm một chữ cái viết hoa, một chữ cái viết thường và một số hoặc ký tự đặc biệt');
							errors = true;
						} else {
							$('.error-mk').html('');
						}
						if (errors == true) {
							$('.error')
									.html(
											'Dữ liệu vào không hợp lệ vui lòng kiểm tra lại');
							return false;
						}
						return true;

					}
				});
				
$("#nhanvienCapNhatAdminForm")
		.validator()
		.on(
				"submit",
				function(event) {
					if (event.isDefaultPrevented()) {

					} else {
						var errors = false;
						var ten = $("#ten").val();
						var ngaysinh = $("#ngaysinh").val();
						var noisinh = $("#noisinh").val();
						var sodienthoai = $("#sodienthoai").val();
						var email = $("#email").val();
						var socmnd = $("#socmnd").val();
						var luong = $("#luong").val();

						var regexEmail = /^[a-zA-Z0-9_.+-]+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
						var regexSTD = /((09|03|07|08|05)+([0-9]{8})\b)/;
						var regexCMND = /^[0-9]{9,13}\b/;
						var regexLuong = /^[0-9]+\b/;
						if (ten.length == 0) {
							$('.error-ten').html('Tên không được bỏ trống');
							errors = true;
						} else {
							$('.error-ten').html('');
						}

						if (ngaysinh.length == 0) {
							$('.error-ngaysinh')
									.html('Vui lòng chọn ngày sinh');
							errors = true;
						} else {
							$('.error-ngaysinh').html('');
						}
						if (noisinh.length == 0) {
							$('.error-noisinh').html(
									'Nơi sinh không được bỏ trống');
							errors = true;
						} else {
							$('.error-noisinh').html('');
						}
						if (!validateTest(regexSTD, sodienthoai)) {
							$('.error-sodienthoai').html(
									'Số điện thoại không đúng định dạng');
							errors = true;
						} else {
							$('.error-sodienthoai').html('');
						}
						if (!validateTest(regexEmail, email)) {
							$('.error-email')
									.html('Email không đúng định dạng');
							errors = true;
						} else {
							$('.error-email').html('');
						}
						if (!validateTest(regexCMND, socmnd)) {
							$('.error-cmnd').html(
									'Số chứng minh từ 9 đến 13 số');
							errors = true;
						} else {
							$('.error-cmnd').html('');
						}
						if (!validateTest(regexLuong, luong)) {
							$('.error-luong').html(
									'Lương không được bỏ trống và là ký tự số');
							errors = true;
						} else {
							$('.error-luong').html('');
						}
						
						if (errors == true) {
							$('.error')
									.html(
											'Dữ liệu vào không hợp lệ vui lòng kiểm tra lại');
							return false;
						}
						return true;

					}
				});				
				
$("#nhanvienCapNhatForm")
		.validator()
		.on(
				"submit",
				function(event) {
					if (event.isDefaultPrevented()) {

					} else {
						var errors = false;
						var ten = $("#ten").val();
						var ngaysinh = $("#ngaysinh").val();
						var noisinh = $("#noisinh").val();
						var sodienthoai = $("#sodienthoai").val();
						var email = $("#email").val();
						var socmnd = $("#socmnd").val();

						var regexEmail = /^[a-zA-Z0-9_.+-]+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
						var regexSTD = /((09|03|07|08|05)+([0-9]{8})\b)/;
						var regexCMND = /^[0-9]{9,13}\b/;
						var regexMK = /(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$/
						if (ten.length == 0) {
							$('.error-ten').html('Tên không được bỏ trống');
							errors = true;
						} else {
							$('.error-ten').html('');
						}

						if (ngaysinh.length == 0) {
							$('.error-ngaysinh')
									.html('Vui lòng chọn ngày sinh');
							errors = true;
						} else {
							$('.error-ngaysinh').html('');
						}
						if (noisinh.length == 0) {
							$('.error-noisinh').html(
									'Nơi sinh không được bỏ trống');
							errors = true;
						} else {
							$('.error-noisinh').html('');
						}
						if (!validateTest(regexSTD, sodienthoai)) {
							$('.error-sodienthoai').html(
									'Số điện thoại không đúng định dạng');
							errors = true;
						} else {
							$('.error-sodienthoai').html('');
						}
						if (!validateTest(regexEmail, email)) {
							$('.error-email')
									.html('Email không đúng định dạng');
							errors = true;
						} else {
							$('.error-email').html('');
						}
						if (!validateTest(regexCMND, socmnd)) {
							$('.error-cmnd').html(
									'Số chứng minh từ 9 đến 13 số');
							errors = true;
						} else {
							$('.error-cmnd').html('');
						}

						if (errors == true) {
							$('.error')
									.html(
											'Dữ liệu vào không hợp lệ vui lòng kiểm tra lại');
							return false;
						}
						return true;

					}
				});
				
				
$("#nhanvientvForm")
		.validator()
		.on(
				"submit",
				function(event) {
					if (event.isDefaultPrevented()) {

					} else {
						var errors = false;
						var ten = $("#ten").val();
						var ngaysinh = $("#ngaysinh").val();
						var noisinh = $("#noisinh").val();
						var sodienthoai = $("#sodienthoai").val();
						var email = $("#email").val();
						var socmnd = $("#socmnd").val();
						var luong = $("#luong").val();

						var regexEmail = /^[a-zA-Z0-9_.+-]+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
						var regexSTD = /((09|03|07|08|05)+([0-9]{8})\b)/;
						var regexCMND = /^[0-9]{9,13}\b/;
						var regexLuong = /^[0-9]+\b/;
						if (ten.length == 0) {
							$('.error-ten').html('Tên không được bỏ trống');
							errors = true;
						} else {
							$('.error-ten').html('');
						}

						if (ngaysinh.length == 0) {
							$('.error-ngaysinh')
									.html('Vui lòng chọn ngày sinh');
							errors = true;
						} else {
							$('.error-ngaysinh').html('');
						}
						if (noisinh.length == 0) {
							$('.error-noisinh').html(
									'Nơi sinh không được bỏ trống');
							errors = true;
						} else {
							$('.error-noisinh').html('');
						}
						if (!validateTest(regexSTD, sodienthoai)) {
							$('.error-sodienthoai').html(
									'Số điện thoại không đúng định dạng');
							errors = true;
						} else {
							$('.error-sodienthoai').html('');
						}
						if (!validateTest(regexEmail, email)) {
							$('.error-email')
									.html('Email không đúng định dạng');
							errors = true;
						} else {
							$('.error-email').html('');
						}
						if (!validateTest(regexCMND, socmnd)) {
							$('.error-cmnd').html(
									'Số chứng minh từ 9 đến 13 số');
							errors = true;
						} else {
							$('.error-cmnd').html('');
						}
						if (!validateTest(regexLuong, luong)) {
							$('.error-luong').html(
									'Lương không được bỏ trống và là ký tự số');
							errors = true;
						} else {
							$('.error-luong').html('');
						}
						
						if (errors == true) {
							$('.error')
									.html(
											'Dữ liệu vào không hợp lệ vui lòng kiểm tra lại');
							return false;
						}
						return true;

					}
				});				

$("#sanhForm")
		.validator()
		.on(
				"submit",
				function(event) {
					if (event.isDefaultPrevented()) {

					} else {
						var errors = false;
						var ten = $("#ten").val();
						var sokhachtoithieu = $("#sokhachtoithieu").val();
						var sokhachtoida = $("#sokhachtoida").val();

						var regexSo = /^[0-9]+\b/;
						if (ten.length == 0) {
							$('.error-ten').html('Tên không được bỏ trống');
							errors = true;
						} else {
							$('.error-ten').html('');
						}

						if (!validateTest(regexSo, sokhachtoithieu)) {
							$('.error-sokhachtoithieu')
									.html(
											'Số khách tối thiểu không bỏ trống và là ký tự số');
							errors = true;
						} else {
							$('.error-sokhachtoithieu').html('');
						}
						if (!validateTest(regexSo, sokhachtoida)) {
							$('.error-sokhachtoida')
									.html(
											'Số khách tối da không bỏ trống và là ký tự số');
							errors = true;
						} else {
							$('.error-sokhachtoida').html('');
						}

						if (errors == true) {
							$('.error')
									.html(
											'Dữ liệu vào không hợp lệ vui lòng kiểm tra lại');
							return false;
						}
						return true;

					}
				});
$("#loaimonForm").validator().on("submit", function(event) {
	if (event.isDefaultPrevented()) {

	} else {
		var errors = false;
		var ten = $("#ten").val();

		if (ten.length == 0) {
			$('.error-ten').html('Tên không được bỏ trống');
			errors = true;
		} else {
			$('.error-ten').html('');
		}

		if (errors == true) {
			$('.error').html('Dữ liệu vào không hợp lệ vui lòng kiểm tra lại');
			return false;
		}
		return true;

	}
});

$("#themmonForm").validator().on("submit", function(event) {
	if (event.isDefaultPrevented()) {

	} else {
		var errors = false;
		var ten = $("#ten").val();
		var gia = $("#gia").val();

		var regexGia = /^[0-9]+\b/;

		if (ten.length == 0) {
			$('.error-ten').html('Tên không được bỏ trống');
			errors = true;
		} else {
			$('.error-ten').html('');
		}
		if (!validateTest(regexGia, gia)) {
			$('.error-gia').html('Giá không được bỏ trống và là ký tự số');
			errors = true;
		} else {
			$('.error-gia').html('');
		}
		if (errors == true) {
			$('.error').html('Dữ liệu vào không hợp lệ vui lòng kiểm tra lại');
			return false;
		}
		return true;

	}
});

$("#capnhatForm").validator().on("submit", function(event) {
	if (event.isDefaultPrevented()) {

	} else {
		var errors = false;
		var ten = $("#ten").val();
		var ngaysinh = $("#ngaysinh").val();
		var noisinh = $("#noisinh").val();
		var socmnd = $("#socmnd").val();
		var sodienthoai = $("#sodienthoai").val();
		var email = $("#email").val();

		var regexCMND = /^[0-9]{9,13}\b/;
		var regexEmail = /^[a-zA-Z0-9_.+-]+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		var regexSTD = /((09|03|07|08|05)+([0-9]{8})\b)/;
		if (ten.length == 0) {
			$('.error-ten').html('Tên không được bỏ trống');
			errors = true;
		} else {
			$('.error-ten').html('');
		}

		if (ngaysinh.length == 0) {
			$('.error-ngaysinh').html('Vui lòng chọn ngày sinh');
			errors = true;
		} else {
			$('.error-ngaysinh').html('');
		}
		if (noisinh.length == 0) {
			$('.error-noisinh').html('Nơi sinh không được bỏ trống');
			errors = true;
		} else {
			$('.error-noisinh').html('');
		}

		if (!validateTest(regexCMND, socmnd)) {
			$('.error-cmnd').html('Số chứng minh từ 9 đến 13 số');
			errors = true;
		} else {
			$('.error-cmnd').html('');
		}
		if (!validateTest(regexSTD, sodienthoai)) {
							$('.error-sodienthoai').html(
									'Số điện thoại không đúng định dạng');
							errors = true;
						} else {
							$('.error-sodienthoai').html('');
						}
						if (!validateTest(regexEmail, email)) {
							$('.error-email')
									.html('Email không đúng định dạng');
							errors = true;
						} else {
							$('.error-email').html('');
						}

		if (errors == true) {
			$('.error').html('Dữ liệu vào không hợp lệ vui lòng kiểm tra lại');
			return false;
		}
		return true;

	}
});
function validateTest(regex, dulieu) {
	return regex.test(dulieu);
}
$("#doimatkhauform")
		.validator()
		.on(
				"submit",
				function(event) {
					if (event.isDefaultPrevented()) {

					} else {
						var errors = false;

						var newpass = $("#newpass").val();

						var regexMK = /(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$/

						if (!validateTest(regexMK, newpass)) {
							$('.error-newpass')
									.html(
											'Mật khẩu ít nhất 8 ký tự bao gồm một chữ cái viết hoa, một chữ cái viết thường và một số hoặc ký tự đặc biệt');
							errors = true;
						} else {
							$('.error-newpass').html('');
						}
						if (errors == true) {
							$('.error')
									.html(
											'Dữ liệu vào không hợp lệ vui lòng kiểm tra lại');
							return false;
						}
						return true;

					}
				});

$("#dattiecForm").validator().on(
		"submit",
		function(event) {
			if (event.isDefaultPrevented()) {

			} else {
				var errors = false;

				var sokhach = $("#sokhach").val();
				var soban = $("#soban").val();
				var ngaytochuc = $("#ngaytochuc").val();

				var regexSo = /^[0-9]+\b/;

				if (!validateTest(regexSo, sokhach)) {
					$('.error-sokhach').html(
							'Số khách không được bỏ trống và là ký tự số');
					errors = true;
				} else {
					if (sokhach < 0) {
						$('.error-sokhach').html('Số khách phải lớn hơn 0');
					} else {
						$('.error-sokhach').html('');
					}

				}
				if (!validateTest(regexSo, soban)) {
					$('.error-soban').html(
							'Số bàn không được bỏ trống và là ký tự số');
					errors = true;
				} else {
					if (soban < 0) {
						$('.error-soban').html('Số bàn lớn hơn không!');
					} else {
						$('.error-soban').html('');
					}

				}
				if (ngaytochuc.length == 0) {
					$('.error-ngaytochuc').html('Vui lòng chọn ngày tổ chức');
					errors = true;
				} else {
					var toDate = new Date();
					var d1 = new Date(ngaytochuc);
					if(Date.parse(d1) < Date.parse(toDate)){
						$('.error-ngaytochuc').html('Ngày tổ chức phải lớn hơn ngày hiện tại');
						errors = true;
					}
					else{
						$('.error-ngaytochuc').html('');
					}
				}

				if (errors == true) {
					$('.error').html(
							'Dữ liệu vào không hợp lệ vui lòng kiểm tra lại');
					return false;
				}
				return true;

			}
		});

function validateTest(regex, dulieu) {
	return regex.test(dulieu);
}

function submitForm() {
	var a = $("#slmon").val();
	var ctcombos = [];
	for (var i = 1; i <= a; i++) {
		var ten = $("#ten" + i).val();
		var mon = {
			ten : ten
		}
		var ctcombo = {
			stt : i,
			mon : mon
		}
		ctcombos.push(ctcombo);
	}

	var ten = $("#ten").val();
	var gia = $("#gia").val();
	var ghichu = $("#ghichu").val();
	var dataCombo = {
		ten : ten,
		gia : gia,
		ghichu : ghichu,
		ctcombos : ctcombos
	}

	$.ajax({
		type : "post",
		contentType : "application/json",
		url : "combosession",
		dataType : "json",
		data : JSON.stringify(dataCombo),
		success : function(data) {
			console.log("SUCCESS : ", data);
		}

	});
}

function submitFormTT() {
	var a = $("#slnuoc").val();
	var ctHoaDons = [];
	
	var slcombo = $("#montt").val();
	var idcombo = $("#idmon").val();
	var combo = {
			id:idcombo
	}
	var cthoadon = {
		soluong: slcombo,
		combo: combo
	}
	ctHoaDons.push(cthoadon);
	for (var i = 1; i <= a; i++) {
		var soluong = $("#" + i).val();
		var idmon = $("#idnuoc"+i).val();
		var mon = {
			id : idmon
		}
		var cthoadon = {
			soluong: soluong,
			mon : mon
		}
		ctHoaDons.push(cthoadon);
	}
	
	var slcthoadon = $("#slcthoadon").val();
	for (var i = 1; i <= slcthoadon; i++) {
		var ten = $("#ten" + i).val();
		
		var gia = $("#gia" + i).val();
		
		var soluong = $("#sl" + i).val();
		
		var cthoadon = {
			soluong: soluong,
			ten: ten,
			gia:gia*soluong
		}
		ctHoaDons.push(cthoadon);
		
	}
	

	var idttdt = $("#idttdt").val();
	var ttDatTiec = {
			id:idttdt
	}
	var dataTTDT = {
			ttDatTiec:ttDatTiec,
			ctHoaDons:ctHoaDons
	}

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/tieptucthanhtoan",
		data : JSON.stringify(dataTTDT),
		dataType : "json"
	
	});
}

function submitFormTinhLuong() {
	var ngaycongthang = $("#ngaycongthang").val();
	var nam = $("#nam").val();
	var thang = $("#thang").val();
	var slnv = $("#slnv").val();
	var ds = [];
	for (var i = 1; i <= slnv; i++) {
		var songaycong = $("#songaycong" + i).val();
		var idnv = $("#idnv" + i).val();
		var nhanvien = {
			id: idnv
		}
		var luong = {
			thang: thang,
			nam: nam,
			ngaycongthang: ngaycongthang,
			nhanvien: nhanvien,
			songaycong: songaycong
		}
		ds.push(luong);
	}

	$.ajax({
		type : "POST",
		contentType : "application/json",
		url : "/luutinhluong",
		data : JSON.stringify(ds),
		dataType : "json"
	
	});
}

function formSuccess() {
	window.location = "admin";
}

function formError() {
	$("#contactForm")
			.removeClass()
			.addClass('shake animated')
			.one(
					'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend',
					function() {
						$(this).removeClass();
					});
}

function submitMSG(valid, msg) {
	if (valid) {
		var msgClasses = "h3 text-center tada animated text-success";
	} else {
		var msgClasses = "h3 text-center text-danger";
	}
	$("#msgSubmit").removeClass().addClass(msgClasses).text(msg);
}
