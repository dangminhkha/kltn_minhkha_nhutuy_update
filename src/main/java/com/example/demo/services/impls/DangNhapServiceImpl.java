package com.example.demo.services.impls;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.converters.NguoiDungConverter;
import com.example.demo.converters.NhanVienConverter;
import com.example.demo.dtos.NguoiDungDTO;
import com.example.demo.dtos.NhanVienDTO;
import com.example.demo.entitys.NguoiDung;
import com.example.demo.entitys.NhanVien;
import com.example.demo.entitys.ResultEntity;
import com.example.demo.repositorys.NguoiDungRepository;
import com.example.demo.repositorys.NhanVienRepository;
import com.example.demo.services.DangNhapService;
import com.example.demo.utils.MD5;
import com.example.demo.utils.StringUtil;

@Service
public class DangNhapServiceImpl implements DangNhapService{
	@Autowired
	private NguoiDungRepository nguoidungRepository;
	@Autowired 
	private NhanVienRepository nhanVienRepository;
	@Override
	public ResultEntity<NguoiDungDTO> dangnhap(String username, String pass) {
		ResultEntity<NguoiDungDTO> result = new ResultEntity<>();
		String matkhau = MD5.maHoaMatKhau(pass);
		Optional<NguoiDung> khachhangOptional = nguoidungRepository.dangNhap(username, username, matkhau,StringUtil.DANGHOATDONG);
		NguoiDungDTO khDTO = new NguoiDungDTO();
		if(khachhangOptional.isPresent()) {
			NguoiDung kh = khachhangOptional.get();
			khDTO = NguoiDungConverter.toDTO(kh);
			result.setDulieu(khDTO);
			result.setKetqua(StringUtil.THANHCONG);
			result.setNoidung("Đăng nhập thành công");
			return result;
		}
		result.setDulieu(khDTO);
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("Tài khoản hoặc mật khẩu không đúng");
		return result;
	}
	@Override
	public ResultEntity<NhanVienDTO> nvDN(String tk, String mk) {
		ResultEntity<NhanVienDTO> result = new ResultEntity<NhanVienDTO>();
		String matkhau = MD5.maHoaMatKhau(mk);
		Optional<NhanVien> nvOptional = nhanVienRepository.dangNhap(tk, tk, matkhau,StringUtil.DANGHOATDONG);
		NhanVienDTO nvDTO = new NhanVienDTO();
		if(nvOptional.isPresent()) {
			NhanVien nv = nvOptional.get();
			nvDTO = NhanVienConverter.toDTO(nv);
			result.setDulieu(nvDTO);
			result.setKetqua(StringUtil.THANHCONG);
			result.setNoidung("Đăng nhập thành công");
			return result;
		}
		result.setDulieu(null);
		result.setKetqua(StringUtil.THATBAI);
		result.setNoidung("Tài khoản hoặc mật khẩu không đúng");
		return result;
	}

}
