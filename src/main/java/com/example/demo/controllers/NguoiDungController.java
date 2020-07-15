package com.example.demo.controllers;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dtos.BangCongDTO;
import com.example.demo.dtos.CTComboDTO;
import com.example.demo.dtos.CTNuocDatTiecDTO;
import com.example.demo.dtos.ComboDTO;
import com.example.demo.dtos.HoaDonDTO;
import com.example.demo.dtos.LoaiMonDTO;
import com.example.demo.dtos.LuongDTO;
import com.example.demo.dtos.MonDTO;
import com.example.demo.dtos.NguoiDungDTO;
import com.example.demo.dtos.NhanVienDTO;
import com.example.demo.dtos.NhanVienThoiVuDTO;
import com.example.demo.dtos.NhomThoiVuDTO;
import com.example.demo.dtos.SanhDTO;
import com.example.demo.dtos.TTDatTiecDTO;
import com.example.demo.dtos.ThongKeDTO;
import com.example.demo.entitys.ResultEntity;
import com.example.demo.services.BangCongService;
import com.example.demo.services.ComboService;
import com.example.demo.services.GuiMailService;
import com.example.demo.services.HoaDonService;
import com.example.demo.services.LoaiMonService;
import com.example.demo.services.LuongService;
import com.example.demo.services.MonService;
import com.example.demo.services.NguoiDungService;
import com.example.demo.services.NhanVienService;
import com.example.demo.services.NhanVienThoiVuService;
import com.example.demo.services.NhomThoiVuService;
import com.example.demo.services.SanhService;
import com.example.demo.services.TTDatTiecService;
import com.example.demo.services.ThongKeService;
import com.example.demo.services.UploadFileService;
import com.example.demo.utils.DateUI;
import com.example.demo.utils.PageConverter;
import com.example.demo.utils.RandomStringExmple;
import com.example.demo.utils.StringUtil;
import com.ibm.icu.text.SimpleDateFormat;

@Controller
@RequestMapping("/")
public class NguoiDungController {
	@Autowired
	private NguoiDungService nguoiDungService;
	@Autowired
	private NhanVienService nhanvienService;
	@Autowired
	private LoaiMonService loaiMonService;
	@Autowired
	private MonService monService;
	@Autowired
	private SanhService sanhService;
	@Autowired
	private ComboService comboService;
	@Autowired
	private TTDatTiecService ttdattiecService;
	@Autowired
	private HoaDonService hoaDonService;
	@Autowired
	private UploadFileService upload;
	@Autowired
	private LuongService luongService;
	@Autowired
	private NhomThoiVuService nhomThoiVuService;
	@Autowired
	private NhanVienThoiVuService nhanVienThoiVuService;
	@Autowired
	private ThongKeService thongkeService;
	@Autowired
	private GuiMailService guiMailService;
	@Autowired
	private BangCongService bangcongService;

	@GetMapping("")
	public String index(HttpSession session, Model model) {
		NhanVienDTO userDN;
		try {
			userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		} catch (Exception e) {
			userDN = null;
		}

		if (session.getAttribute("loaimon") == null) {
			ResultEntity<List<LoaiMonDTO>> resultLoai = loaiMonService.loaiMonAll();
			session.setAttribute("loaimon", resultLoai.getDulieu());
		}
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			return "admin";
		} else {
			return "index";
		}

	}

	@GetMapping("admin")
	public String dangnhapadmin(HttpSession session) {
		NhanVienDTO userDN;
		try {
			userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		} catch (Exception e) {
			userDN = null;
		}
		if (userDN == null) {
			return "admindangnhap";
		}
		return "redirect:/";
	}

	@GetMapping("formdangky")
	public String dangky(HttpSession session) {
		session.removeAttribute("dto");
		return "dangky";
	}

	@GetMapping("formdangnhap")
	public String dangnhap(HttpSession session) {
		NguoiDungDTO userDN;
		try {
			userDN = (NguoiDungDTO) session.getAttribute("userDangNhap");
		} catch (Exception e) {
			userDN = null;
		}
		if (userDN == null) {
			session.removeAttribute("username");
			session.removeAttribute("pass");
			return "dangnhap";
		}
		return "redirect:/";
	}

	@GetMapping("gioithieu")
	public String gioithieu() {
		return "gioithieu";
	}

	@GetMapping("sanh")
	public String sanh(HttpSession session, Model model,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "15") Integer size) {
		ResultEntity<List<SanhDTO>> result = sanhService.dsSanh(page, size);
		if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
			int slPage = sanhService.slPage(StringUtil.DANGHOATDONG);
			PageConverter.pageFormat(model, page, slPage);
			session.setAttribute("sanh", result.getDulieu());
			return "sanh";
		}
		return "redirect:/";
	}

	@GetMapping("themsanh")
	public String themsanh() {
		return "themsanh";
	}

	@PostMapping("tieptucdattiec")
	public String tieptucdattiec(@Valid TTDatTiecDTO dto, HttpSession session, Model model) {
		NguoiDungDTO ngdung = (NguoiDungDTO) session.getAttribute("userDangNhap");
		dto.setNguoidung(ngdung);
		List<CTNuocDatTiecDTO> ncDTO = new ArrayList<CTNuocDatTiecDTO>();
		dto.setCtNuocDatTiecs(ncDTO);
		session.setAttribute("ttdattiec", dto);

		ResultEntity<List<SanhDTO>> result = sanhService.dsSanhCho(dto);
		session.setAttribute("dsSanhCho", result.getDulieu());
		return "dattiec2";

	}

	@GetMapping("timsanh")
	public String timsanh(@RequestParam(name = "tim", required = false, defaultValue = "") String tim,
			HttpSession session, Model model) {
		model.addAttribute("keyword", tim);
		if (tim.length() == 0) {
			return "redirect:/sanh";
		}
		if (tim.equalsIgnoreCase("a")) {
			tim = tim + "_";
		}
		ResultEntity<List<SanhDTO>> result = sanhService.timKeyword(tim);
		session.setAttribute("sanh", result.getDulieu());
		if (result.getDulieu().size() == 0) {
			model.addAttribute("dsrong", "Không tìm thấy sãnh phù hợp!");
		}
		return "sanh";
	}

	@GetMapping("admintimsanh")
	public String admintimsanh(@RequestParam(name = "tim", required = false, defaultValue = "") String tim,
			HttpSession session, Model model) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			model.addAttribute("keyword", tim);
			if (tim.length() == 0) {
				return "redirect:/qlsanh";
			}
			if (tim.equalsIgnoreCase("a")) {
				tim = tim + "_";
			}
			ResultEntity<List<SanhDTO>> result = sanhService.timKeyword(tim);
			session.setAttribute("sanh", result.getDulieu());
			if (result.getDulieu().size() == 0) {
				model.addAttribute("dsrong", "Không tìm thấy sãnh phù hợp!");
			}
			return "admin_sanh";
		}
		return "redirect:/";
	}

	@GetMapping("timmon")
	public String timmon(@RequestParam(name = "tim", required = false, defaultValue = "") String tim,
			HttpSession session, Model model) {
		model.addAttribute("keyword", tim);
		if (tim.length() == 0) {
			return "redirect:/mon";
		}
		if (tim.equalsIgnoreCase("a")) {
			tim = tim + "_";
		}
		ResultEntity<List<MonDTO>> result = monService.tim(tim);
		session.setAttribute("mon", result.getDulieu());
		if (result.getDulieu().size() == 0) {
			model.addAttribute("dsrong", "Không tìm thấy món phù hợp!");
		}
		return "thucdon";
	}

	@GetMapping("admintimmon")
	public String admintimmon(@RequestParam(name = "tim", required = false, defaultValue = "") String tim,
			HttpSession session, Model model) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			model.addAttribute("keyword", tim);
			if (tim.length() == 0) {
				return "redirect:/qlmon";
			}
			if (tim.equalsIgnoreCase("a")) {
				tim = tim + "_";
			}
			ResultEntity<List<MonDTO>> result = monService.tim(tim);
			session.setAttribute("dsmon", result.getDulieu());
			if (result.getDulieu().size() == 0) {
				model.addAttribute("dsrong", "Không tìm thấy món phù hợp!");
			}
			return "admin_thucdon";
		}
		return "redirect:/";
	}

	@GetMapping("timcombo")
	public String timcombo(@RequestParam(name = "tim", required = false, defaultValue = "") String tim,
			HttpSession session, Model model) {
		model.addAttribute("keyword", tim);
		if (tim.length() == 0) {
			return "redirect:/combo";
		}
		if (tim.equalsIgnoreCase("a")) {
			tim = tim + "_";
		}
		ResultEntity<List<ComboDTO>> result = comboService.tim(tim);
		session.setAttribute("combos", result.getDulieu());
		if (result.getDulieu().size() == 0) {
			model.addAttribute("dsrong", "Không tìm thấy combo phù hợp!");
		}
		return "combo";
	}

	@GetMapping("admintimcombo")
	public String admintimcombo(@RequestParam(name = "tim", required = false, defaultValue = "") String tim,
			HttpSession session, Model model) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			model.addAttribute("keyword", tim);
			if (tim.length() == 0) {
				return "redirect:/qlcombo";
			}
			if (tim.equalsIgnoreCase("a")) {
				tim = tim + "_";
			}
			ResultEntity<List<ComboDTO>> result = comboService.tim(tim);
			session.setAttribute("dscombo", result.getDulieu());
			if (result.getDulieu().size() == 0) {
				model.addAttribute("dsrong", "Không tìm thấy món phù hợp!");
			}
			return "admin_combo";
		}
		return "redirect:/";
	}

	@GetMapping("timnhanvien")
	public String timnhanvien(@RequestParam(name = "tim", required = false, defaultValue = "") String tim,
			HttpSession session, Model model) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null && (StringUtil.ADMIN.equals(userDN.getChucvu()))) {
			model.addAttribute("keyword", tim);
			if (tim.length() == 0) {
				return "redirect:/qlnhanvien";
			}
			if (tim.equalsIgnoreCase("a")) {
				tim = tim + "_";
			}
			ResultEntity<List<NhanVienDTO>> result = nhanvienService.tim(tim, StringUtil.NHANVIEN);
			session.setAttribute("dsNV", result.getDulieu());
			if (result.getDulieu().size() == 0) {
				model.addAttribute("dsrong", "Không tìm thấy nhân viên phù hợp!");
			}
			return "adminqlnhanvien";
		}
		return "redirect:/";
	}

	@GetMapping("timkh")
	public String timkh(@RequestParam(name = "tim", required = false, defaultValue = "") String tim,
			HttpSession session, Model model) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null && (StringUtil.ADMIN.equals(userDN.getChucvu()))) {
			model.addAttribute("keyword", tim);
			if (tim.length() == 0) {
				return "redirect:/qlkhachhang";
			}
			if (tim.equalsIgnoreCase("a")) {
				tim = tim + "_";
			}
			ResultEntity<List<NguoiDungDTO>> result = nguoiDungService.tim(tim);
			session.setAttribute("dsKH", result.getDulieu());
			if (result.getDulieu().size() == 0) {
				model.addAttribute("dsrong", "Không tìm thấy khách hàng phù hợp!");
			}
			return "adminqlkhachhang";
		}
		return "redirect:/";
	}

	@PostMapping("tieptucthanhtoan")
	public ResultEntity<HoaDonDTO> tieptucthanhtoan(@RequestBody HoaDonDTO hoadon, HttpSession session, Model model) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			ResultEntity<HoaDonDTO> result = hoaDonService.formatHoaDon(hoadon, userDN.getId());
			session.setAttribute("hoadonthanhtoan", result.getDulieu());
			session.setAttribute("cthoadonthanhtoan", result.getDulieu().getCtHoaDons());
			return result;
		}
		return null;
	}

	@GetMapping("tieptucthanhtoan")
	public String tieptucthanhtoan(HttpSession session) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			HoaDonDTO dto = (HoaDonDTO) session.getAttribute("hoadonthanhtoan");
			dto.setNgaythanhtoan(new Date());
			DecimalFormat dcf = new DecimalFormat("#,###");
			SimpleDateFormat datefm = new SimpleDateFormat("hh:mm:a dd-MM-yyyy");
			dto.setNgaythanhtoanFM(datefm.format(dto.getNgaythanhtoan()));
			String tientra = dcf.format(dto.getGia() - dto.getTtDatTiec().getTiencoc());
			session.setAttribute("tientra", tientra);
			return "admin_thanhtoan2";
		}
		return "redirect:/";
	}

	@GetMapping("thanhtoanhoadon")
	public String thanhtoanhoadon(HttpSession session, Model model) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			HoaDonDTO dto = (HoaDonDTO) session.getAttribute("hoadonthanhtoan");
			ResultEntity<HoaDonDTO> result = hoaDonService.thanhtoan(dto, userDN.getId());
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				return "redirect:/dsdattiecadmin";
			}
			model.addAttribute("thongbaott", result.getNoidung());
			return "redirect:/tieptucthanhtoan";
		}
		return "redirect:/";
	}

	@GetMapping("chonsanh")
	public String chonsanh(HttpSession session, Model model,
			@RequestParam(name = "id", required = false, defaultValue = "0") String id) {
		ResultEntity<SanhDTO> resultSanh = sanhService.timId(id);
		TTDatTiecDTO ttdt = (TTDatTiecDTO) session.getAttribute("ttdattiec");
		ttdt.setSanh(resultSanh.getDulieu());
		session.setAttribute("ttdattiec", ttdt);
		ResultEntity<List<ComboDTO>> result = comboService.dsChon();
		model.addAttribute("dscombochon", result.getDulieu());
		return "dattiec3";
	}

	@GetMapping("khtaocombo")
	public String khtaocombo(HttpSession session, Model model) {
		NguoiDungDTO ngdung = (NguoiDungDTO) session.getAttribute("userDangNhap");
		ComboDTO comboDTO = (ComboDTO) session.getAttribute("combokh");
		if(comboDTO==null) {
			comboDTO = new ComboDTO();
			comboDTO.setCtcombos(new ArrayList<>());
			comboDTO.setNguoidung(ngdung);
			comboDTO.setGia(0);
			session.setAttribute("combokh", comboDTO);
			Set<String> dsId = new HashSet<String>();
			session.setAttribute("dsId", dsId);
		}
		
		ResultEntity<List<LoaiMonDTO>> result = loaiMonService.loaiMonAns(StringUtil.DANGHOATDONG, StringUtil.DOAN);
		session.setAttribute("loaimons", result.getDulieu());
		return "khtaocombo";
	}

	@GetMapping("chonmonan")
	public String chonmonan(HttpSession session, Model model,
			@RequestParam(name = "id", required = false, defaultValue = "0") String id) {
		Set<String> dsId = (Set<String>) session.getAttribute("dsId");
		if (dsId.add(id) == true) {
			ComboDTO comboDTO = (ComboDTO) session.getAttribute("combokh");
			DecimalFormat df = new DecimalFormat("#,###");
			ResultEntity<MonDTO> result = monService.timMon(id);
			CTComboDTO ctDTO = new CTComboDTO();
			ctDTO.setMon(result.getDulieu());
			ctDTO.setStt(comboDTO.getCtcombos().size() + 1);
			comboDTO.getCtcombos().add(ctDTO);
			comboDTO.setGia(comboDTO.getGia() + result.getDulieu().getGia());
			comboDTO.setGiaFM(df.format(comboDTO.getGia()));
			session.setAttribute("combokh", comboDTO);
		}
		session.setAttribute("dsId", dsId);
		return "khtaocombo";
	}

	@GetMapping("bochonmon")
	public String bochonmon(HttpSession session, Model model,
			@RequestParam(name = "id", required = false, defaultValue = "0") String id,
			@RequestParam(name = "stt", required = false, defaultValue = "0") int stt) {
		Set<String> dsId = (Set<String>) session.getAttribute("dsId");
		dsId.remove(id);

		ComboDTO comboDTO = (ComboDTO) session.getAttribute("combokh");

		comboDTO.getCtcombos().removeIf(x -> x.getStt() == stt);

		DecimalFormat df = new DecimalFormat("#,###");

		double gia = 0;

		for (int i = 0; i < comboDTO.getCtcombos().size(); i++) {
			if (comboDTO.getCtcombos().get(i).getStt() > stt) {
				comboDTO.getCtcombos().get(i).setStt(comboDTO.getCtcombos().get(i).getStt() - 1);
			}
			gia = gia + comboDTO.getCtcombos().get(i).getMon().getGia();
		}
		comboDTO.setGia(gia);

		comboDTO.setGiaFM(df.format(comboDTO.getGia()));
		session.setAttribute("combokh", comboDTO);

		session.setAttribute("dsId", dsId);
		return "khtaocombo";
	}

	@GetMapping("quaylai")
	public String quaylai1() {
		return "redirect:/dattiec";
	}

	@GetMapping("quaylaichoncombo")
	public String quaylaichoncombo(Model model) {
		ResultEntity<List<ComboDTO>> result = comboService.dsChon();
		model.addAttribute("dscombochon", result.getDulieu());
		return "dattiec3";
	}

	@GetMapping("tieptucdattiec")
	public String quaylaichonsanh() {

		return "dattiec2";
	}
	
	@GetMapping("combokhtieptuc")
	public String combokhtieptuc(HttpSession session, Model model) {
		TTDatTiecDTO ttdt = (TTDatTiecDTO) session.getAttribute("ttdattiec");
		ComboDTO comboDTO = (ComboDTO) session.getAttribute("combokh");
		ResultEntity<List<MonDTO>> resultMon = monService.dsAll(StringUtil.NUOC);

		model.addAttribute("dsNuoc", resultMon.getDulieu());
		ttdt.setCombo(comboDTO);
		session.setAttribute("ttdattiec", ttdt);
		return "dattiec4";
	}

	@GetMapping("choncombo")
	public String choncombo(HttpSession session, Model model,
			@RequestParam(name = "id", required = false, defaultValue = "0") String id) {
		TTDatTiecDTO ttdt = (TTDatTiecDTO) session.getAttribute("ttdattiec");
		ResultEntity<ComboDTO> resultCombo = comboService.timID(id);
		ResultEntity<List<MonDTO>> resultMon = monService.dsAll(StringUtil.NUOC);

		model.addAttribute("dsNuoc", resultMon.getDulieu());
		ttdt.setCombo(resultCombo.getDulieu());
		session.setAttribute("ttdattiec", ttdt);
		return "dattiec4";
	}

	@GetMapping("xoasanh")
	public String xoasanh(HttpSession session, Model model,
			@RequestParam(name = "id", required = false, defaultValue = "0") String id) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			ResultEntity<SanhDTO> result = sanhService.xoa(id, userDN.getId());
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				return "redirect:/qlsanh";
			} else {
				model.addAttribute("thongbaoxoasanh", result.getNoidung());
				model.addAttribute("sanhct", result.getDulieu());
				return "admin_xemchitietsanh";
			}
		}
		return "redirect:/";
	}

	@GetMapping("xoaloai")
	public String xoaloai(HttpSession session, Model model,
			@RequestParam(name = "id", required = false, defaultValue = "0") String id) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			ResultEntity<LoaiMonDTO> result = loaiMonService.xoa(id, userDN.getId());
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				return "redirect:/qlloai";
			}
		}
		return "redirect:/";
	}

	@GetMapping("xoamon")
	public String xoamon(HttpSession session, Model model,
			@RequestParam(name = "id", required = false, defaultValue = "0") String id) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			ResultEntity<MonDTO> result = monService.xoa(id, userDN.getId());
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				return "redirect:/qlmon";
			} else {
				model.addAttribute("thongbaoxoamon", result.getNoidung());
				model.addAttribute("monct", result.getDulieu());
				return "admin_xemchitietmon";
			}
		}
		return "redirect:/";
	}

	@GetMapping("xoanhanvien")
	public String xoanhanvien(HttpSession session, Model model,
			@RequestParam(name = "id", required = false, defaultValue = "0") String id) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null && StringUtil.ADMIN.equals(userDN.getChucvu())) {
			ResultEntity<NhanVienDTO> result = nhanvienService.xoa(userDN.getId(), id);
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				return "redirect:/qlnhanvien";
			} else {
				model.addAttribute("thongbaoxoanhanvien", result.getNoidung());
				return "redirect:/qlnhanvien";
			}
		}
		return "redirect:/";
	}

	@GetMapping("xoacombo")
	public String xoacombo(HttpSession session, Model model,
			@RequestParam(name = "id", required = false, defaultValue = "0") String id) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			ResultEntity<ComboDTO> result = comboService.xoa(id, userDN.getId());
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				return "redirect:/qlcombo";
			} else {
				model.addAttribute("thongbaocombo", result.getNoidung());
				model.addAttribute("comboct", result.getDulieu());
				return "admin_xemchitietcombo";
			}
		}
		return "redirect:/";
	}

	@GetMapping("quaylaichonmon")
	public String quaylaichonmon(HttpSession session, Model model) {
		ResultEntity<List<MonDTO>> resultMon = monService.dsAll(StringUtil.NUOC);
		model.addAttribute("dsNuoc", resultMon.getDulieu());
		return "dattiec4";
	}

	@GetMapping("xemthongtindt")
	public String choncombo(HttpSession session, Model model) {
		TTDatTiecDTO ttdt = (TTDatTiecDTO) session.getAttribute("ttdattiec");
		model.addAttribute("ctcombodat", ttdt.getCombo().getCtcombos());
		model.addAttribute("dsnuocuong", ttdt.getCtNuocDatTiecs());
		double giauocluongmonan = ttdt.getCombo().getGia() * ttdt.getSoban();

		double giatrungbinh = 0;
		double tonggianuoc = 0;
		double giauocluongnuocuong = 0;

		for (int i = 0; i < ttdt.getCtNuocDatTiecs().size(); i++) {
			tonggianuoc = tonggianuoc + ttdt.getCtNuocDatTiecs().get(i).getMon().getGia();
		}
		giatrungbinh = (tonggianuoc / ttdt.getCtNuocDatTiecs().size()) * 2;
		giauocluongnuocuong = giatrungbinh * ttdt.getSokhach();

		DecimalFormat df = new DecimalFormat("#,###");
		model.addAttribute("giauocluong", df.format(giauocluongmonan + giauocluongnuocuong));
		return "thongtindattiec";
	}

	@GetMapping("chonnuoc")
	public String chonnuoc(HttpSession session, Model model,
			@RequestParam(name = "id", required = false, defaultValue = "0") String id) {

		ResultEntity<List<MonDTO>> resultMon = monService.dsAll(StringUtil.NUOC);
		ResultEntity<MonDTO> monId = monService.timMon(id);

		TTDatTiecDTO ttdt = (TTDatTiecDTO) session.getAttribute("ttdattiec");
		CTNuocDatTiecDTO nuocdto = new CTNuocDatTiecDTO();
		String idCtNuoc = RandomStringExmple.id(6);
		nuocdto.setId(idCtNuoc);
		nuocdto.setMon(monId.getDulieu());
		AtomicBoolean kiemtra = new AtomicBoolean(true);
		ttdt.getCtNuocDatTiecs().stream().map(z -> {
			if (z.getMon().getId().equals(monId.getDulieu().getId())) {
				kiemtra.set(false);
			}
			return z;
		}).collect(Collectors.toList());
		if (kiemtra.get() == true) {
			ttdt.getCtNuocDatTiecs().add(nuocdto);
		}
		session.setAttribute("ttdattiec", ttdt);
		model.addAttribute("dsNuoc", resultMon.getDulieu());
		return "dattiec4";
	}

	@GetMapping("xoanuocdattiec")
	public String xoanuocdattiec(HttpSession session,Model model,
			@RequestParam(name = "id", required = false, defaultValue = "0") String id,
			@RequestParam(name = "trang", required = false, defaultValue = "0") String trang) {
		TTDatTiecDTO ttdt = (TTDatTiecDTO) session.getAttribute("ttdattiec");
		for (int i = 0; i < ttdt.getCtNuocDatTiecs().size(); i++) {
			if (ttdt.getCtNuocDatTiecs().get(i).getMon().getId().equals(id)) {
				ttdt.getCtNuocDatTiecs().remove(ttdt.getCtNuocDatTiecs().get(i));
			}
		}
		if(trang.equals("0")) {
			session.setAttribute("ttdattiec", ttdt);
			return "redirect:/xemthongtindt";
		}else {
			ResultEntity<List<MonDTO>> resultMon = monService.dsAll(StringUtil.NUOC);
			model.addAttribute("dsNuoc", resultMon.getDulieu());
			return "dattiec4";
		}
	}

	@GetMapping("luudattiec")
	public String luudattiec(HttpSession session, Model model) {
		TTDatTiecDTO ttdt = (TTDatTiecDTO) session.getAttribute("ttdattiec");
		ResultEntity<TTDatTiecDTO> result = ttdattiecService.dattiec(ttdt);
		if (result.getKetqua().equalsIgnoreCase(StringUtil.THANHCONG)) {

			model.addAttribute("thongbao", result.getNoidung());
			SimpleDateFormat datefm = new SimpleDateFormat("dd-MM-yyyy");

			String tieude = "ĐẶT TIỆC TẠI LIVE DINNER RESTAURANT NGÀY " + datefm.format(new Date());
			String noidung = "Live Dinner Restaurant cảm ơn quý khách đã đặt " + result.getDulieu().getLoai() + " vào "
					+ result.getDulieu().getCatochuc() + " ngày " + result.getDulieu().getNgaytochuc() + " tại sãnh "
					+ result.getDulieu().getSanh().getTen() + "\n" + "Với số khách là: "
					+ result.getDulieu().getSokhach() + "\n" + "Số bàn là: " + result.getDulieu().getSoban() + "\n"
					+ "với danh sách món là: \n";

			for (int i = 0; i < result.getDulieu().getCombo().getCtcombos().size(); i++) {
				noidung = noidung + result.getDulieu().getCombo().getCtcombos().get(i).getStt() + " - "
						+ result.getDulieu().getCombo().getCtcombos().get(i).getMon().getTen() + " \n";
			}
			int stt = 0;
			noidung = noidung + "Và các loại nước:\n";
			for (int i = 0; i < result.getDulieu().getCtNuocDatTiecs().size(); i++) {
				stt = i + 1;
				noidung = noidung + stt + " - " + result.getDulieu().getCtNuocDatTiecs().get(i).getMon().getTen()
						+ " \n";
			}
			noidung = noidung + "Vui lòng đợi nhân viên chúng tôi xác nhận lại đơn tiệc của bạn\n"
					+ "Xin chân thành cảm ơn quý khách.";
			guiMailService.guimail(result.getDulieu().getNguoidung().getEmail().trim(), tieude, noidung);

			session.removeAttribute("ttdattiec");
			return "index";
		}
		model.addAttribute("thongbao", result.getNoidung());
		return "thongtindattiec";
	}

	@PostMapping("luusanh")
	public String luusanh(@Valid SanhDTO dto, @RequestParam("file") MultipartFile file, HttpSession session,
			Model model) {

		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (dto.getSokhachtoithieu() < 0) {
			session.setAttribute("dto", dto);
			model.addAttribute("sokhachtt", "Số khách tối thiểu phải lớn hơn 0");
			return "themsanh";
		}
		if (dto.getSokhachtoithieu() >= dto.getSokhachtoida()) {
			session.setAttribute("dto", dto);
			model.addAttribute("loisokhach", "Số khách tối đa phải lớn hơn số khách tối thiểu");
			return "themsanh";
		}
		if (file.getOriginalFilename().length() == 0) {
			model.addAttribute("dto", dto);
			model.addAttribute("hinhloi", "Vui lòng chọn hình");
			return "themsanh";
		}

		if (null != userDN) {
			ResultEntity<String> resultUpfile = upload.upload(file);
			dto.setHinh(resultUpfile.getDulieu());
			ResultEntity<SanhDTO> result = sanhService.themSanh(userDN.getId(), dto);
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				return "redirect:/qlsanh";
			} else {
				session.setAttribute("dto", dto);
				model.addAttribute("loiten", result.getNoidung());
				return "themsanh";
			}
		}
		return "redirect:/";

	}

	@GetMapping("qlsanh")
	public String qlsanh(HttpSession session, Model model,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "15") Integer size) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			ResultEntity<List<SanhDTO>> result = sanhService.dsSanh(page, size);
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				int slPage = sanhService.slPage(StringUtil.DANGHOATDONG);
				PageConverter.pageFormat(model, page, slPage);
				session.setAttribute("sanh", result.getDulieu());
				return "admin_sanh";
			}
		}
		return "redirect:/";
	}

	@GetMapping("qlloai")
	public String qlloai(HttpSession session, Model model,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "15") Integer size) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			ResultEntity<List<LoaiMonDTO>> result = loaiMonService.pageLoaiMon(userDN.getId(), page, size);
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				int slPage = loaiMonService.slPage(StringUtil.DANGHOATDONG);
				PageConverter.pageFormat(model, page, slPage);
				session.setAttribute("loaimon", result.getDulieu());
				return "admin_loaimon";
			}

		}
		return "redirect:/";
	}

	@GetMapping("dsdattiecadmin")
	public String admindsdattiec(HttpSession session, Model model,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "15") Integer size,
			@RequestParam(name = "trangthai", required = false, defaultValue = StringUtil.DANGCHODUYET) String trangthai) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			ResultEntity<List<TTDatTiecDTO>> result = ttdattiecService.dsTheoTT(userDN.getId(), trangthai, page, size);
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				if (trangthai.equalsIgnoreCase(StringUtil.DANGCHODUYET)) {
					model.addAttribute("titleAdminDs", "Danh Sách Đang Chờ Duyệt");
				} else if (trangthai.equalsIgnoreCase(StringUtil.DADUYET)) {
					model.addAttribute("titleAdminDs", "Danh Sách Đã Duyệt");
				} else if (trangthai.equalsIgnoreCase(StringUtil.DATHANHTOAN)) {
					model.addAttribute("titleAdminDs", "Danh Sách Đã Thanh Toán");
				} else {
					model.addAttribute("titleAdminDs", "Danh Sách Đã Hủy");
				}
				model.addAttribute("trangthai", trangthai);
				model.addAttribute("dsTTDatTiec", result.getDulieu());
				int slPage = ttdattiecService.slPageAdmin(trangthai);
				PageConverter.pageFormat(model, page, slPage);
				session.setAttribute("sanh", result.getDulieu());
				return "admin_dsdattiec";
			}
		}
		return "redirect:/";
	}

	@GetMapping("qlcombo")
	public String qlcombo(HttpSession session, Model model,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "15") Integer size) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			ResultEntity<List<ComboDTO>> result = comboService.dsComBo(page, size);
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				int slPage = comboService.slPage(StringUtil.DANGHOATDONG);
				PageConverter.pageFormat(model, page, slPage);
				session.setAttribute("dscombo", result.getDulieu());
				return "admin_combo";
			}
		}
		return "redirect:/";
	}

	@GetMapping("combo")
	public String combo(HttpSession session, Model model,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "15") Integer size) {
		ResultEntity<List<ComboDTO>> result = comboService.dsComBo(page, size);
		if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
			int slPage = comboService.slPage(StringUtil.DANGHOATDONG);
			PageConverter.pageFormat(model, page, slPage);
			session.setAttribute("combos", result.getDulieu());
			return "combo";
		}
		return "redirect:/";
	}

	@GetMapping("dattiec")
	public String dattiec(HttpSession session) {
		NguoiDungDTO dto = (NguoiDungDTO) session.getAttribute("userDangNhap");
		if (dto != null)
			return "dattiec1";
		return "dangnhap";
	}

	@GetMapping("tiecdadat")
	public String tiecdadat(HttpSession session, Model model,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "15") Integer size) {
		NguoiDungDTO dto = (NguoiDungDTO) session.getAttribute("userDangNhap");
		if (dto != null) {
			ResultEntity<List<TTDatTiecDTO>> result = ttdattiecService.dsTheoIdKh(dto.getId(), StringUtil.DANGCHODUYET,
					page, size);
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				int slPage = ttdattiecService.slPage(dto.getId(), StringUtil.DANGCHODUYET);
				PageConverter.pageFormat(model, page, slPage);
				model.addAttribute("urldattiec", "tiecdadat");
				model.addAttribute("dsTTDatTiec", result.getDulieu());
				model.addAttribute("titleds", "Danh Sách Đang Chờ Duyệt");
				return "lichsudattiec";
			}
			return "redirect:/";
		}
		return "dangnhap";
	}

	@GetMapping("dsdaduyet")
	public String dsdaduyet(HttpSession session, Model model,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "15") Integer size) {
		NguoiDungDTO dto = (NguoiDungDTO) session.getAttribute("userDangNhap");
		if (dto != null) {
			ResultEntity<List<TTDatTiecDTO>> result = ttdattiecService.dsTheoIdKh(dto.getId(), StringUtil.DADUYET, page,
					size);
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				model.addAttribute("dsTTDatTiec", result.getDulieu());
				int slPage = ttdattiecService.slPage(dto.getId(), StringUtil.DADUYET);
				PageConverter.pageFormat(model, page, slPage);
				model.addAttribute("urldattiec", "dsdaduyet");
				model.addAttribute("dsTTDatTiec", result.getDulieu());
				model.addAttribute("titleds", "Danh Sách Đã Duyệt");
				return "lichsudattiec";
			}
			return "redirect:/";
		}
		return "dangnhap";
	}

	@GetMapping("dsdatochuc")
	public String dsdatochuc(HttpSession session, Model model,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "15") Integer size) {
		NguoiDungDTO dto = (NguoiDungDTO) session.getAttribute("userDangNhap");
		if (dto != null) {
			ResultEntity<List<TTDatTiecDTO>> result = ttdattiecService.dsTheoIdKh(dto.getId(), StringUtil.DATHANHTOAN,
					page, size);
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				model.addAttribute("dsTTDatTiec", result.getDulieu());
				int slPage = ttdattiecService.slPage(dto.getId(), StringUtil.DATHANHTOAN);
				PageConverter.pageFormat(model, page, slPage);
				model.addAttribute("urldattiec", "dsdatochuc");
				model.addAttribute("dsTTDatTiec", result.getDulieu());
				model.addAttribute("titleds", "Danh Sách Đã Tổ Chức");
				return "lichsudattiec";
			}
			return "redirect:/";
		}
		return "dangnhap";
	}

	@GetMapping("dsdahuy")
	public String dsdahuy(HttpSession session, Model model,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "15") Integer size) {
		NguoiDungDTO dto = (NguoiDungDTO) session.getAttribute("userDangNhap");
		if (dto != null) {
			ResultEntity<List<TTDatTiecDTO>> result = ttdattiecService.dsTheoIdKh(dto.getId(), StringUtil.HUY, page,
					size);
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				model.addAttribute("dsTTDatTiec", result.getDulieu());
				int slPage = ttdattiecService.slPage(dto.getId(), StringUtil.HUY);
				PageConverter.pageFormat(model, page, slPage);
				model.addAttribute("urldattiec", "dsdahuy");
				model.addAttribute("dsTTDatTiec", result.getDulieu());
				model.addAttribute("titleds", "Danh Sách Đã Hủy");
				return "lichsudattiec";
			}
			return "redirect:/";
		}
		return "dangnhap";
	}

	@GetMapping("xemttdt")
	public String xemttdt(HttpSession session, Model model,
			@RequestParam(name = "id", required = false, defaultValue = "0") String id) {
		NguoiDungDTO dto = (NguoiDungDTO) session.getAttribute("userDangNhap");
		if (dto != null) {
			ResultEntity<TTDatTiecDTO> result = ttdattiecService.timid(id, dto.getId(), StringUtil.KHACHHANG);
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				session.setAttribute("ttdt", result.getDulieu());
				session.setAttribute("ctdt", result.getDulieu().getCombo().getCtcombos());
				session.setAttribute("ctnuoc", result.getDulieu().getCtNuocDatTiecs());
				return "chitietdattiec";
			}
			return "redirect:/";
		}
		return "dangnhap";
	}

	@GetMapping("xemhoadon")
	public String xemhoadon(HttpSession session, Model model,
			@RequestParam(name = "id", required = false, defaultValue = "0") String id) {
		NguoiDungDTO dto = (NguoiDungDTO) session.getAttribute("userDangNhap");
		if (dto != null) {
			ResultEntity<HoaDonDTO> result = hoaDonService.timid(id, dto.getId(), StringUtil.KHACHHANG);
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				model.addAttribute("hoadon", result.getDulieu());
				model.addAttribute("cthoadons", result.getDulieu().getCtHoaDons());
				return "chitiethoadon";
			}
			return "redirect:/";
		}
		return "dangnhap";
	}

	@GetMapping("adminhoadon")
	public String adminhoadon(HttpSession session, Model model,
			@RequestParam(name = "id", required = false, defaultValue = "0") String id) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			ResultEntity<HoaDonDTO> result = hoaDonService.timid(id, userDN.getId(), StringUtil.NHANVIEN);
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				model.addAttribute("hoadon", result.getDulieu());
				model.addAttribute("cthoadons", result.getDulieu().getCtHoaDons());
				return "admin_chitiethoadon";
			}
			return "redirect:/";
		}
		return "dangnhap";
	}

	@GetMapping("adminxemttdt")
	public String adminxemttdt(HttpSession session, Model model,
			@RequestParam(name = "id", required = false, defaultValue = "0") String id) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			ResultEntity<TTDatTiecDTO> result = ttdattiecService.timid(id, userDN.getId(), StringUtil.NHANVIEN);
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				session.setAttribute("ttdt", result.getDulieu());
				session.setAttribute("ctdt", result.getDulieu().getCombo().getCtcombos());
				session.setAttribute("nguoidung", result.getDulieu().getNguoidung());
				session.setAttribute("ctnuoc", result.getDulieu().getCtNuocDatTiecs());
				return "admin_chitietdattiec";
			}
			return "redirect:/";
		}
		return "dangnhap";
	}

	@PostMapping("adminduyet")
	public String adminduyet(@Valid int tiencoc, HttpSession session, Model model) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			TTDatTiecDTO ttdt = (TTDatTiecDTO) session.getAttribute("ttdt");
			ResultEntity<TTDatTiecDTO> result = ttdattiecService.duyetTTDT(userDN.getId(), ttdt.getId(), tiencoc);
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {

				SimpleDateFormat datefm = new SimpleDateFormat("dd-MM-yyyy");

				String tieude = "XÁC NHẬN ĐƠN ĐẶT TIỆC TỪ LIVE DINNER RESTAURANT NGÀY " + datefm.format(new Date());
				String noidung = "Live Dinner Restaurant đã xác nhận " + result.getDulieu().getLoai() + " vào "
						+ result.getDulieu().getCatochuc() + " ngày " + result.getDulieu().getNgaytochuc()
						+ " tại sãnh " + result.getDulieu().getSanh().getTen() + "\n" + "Với số khách là: "
						+ result.getDulieu().getSokhach() + "\n" + "Số bàn là: " + result.getDulieu().getSoban() + "\n"
						+ " với danh sách món là: \n";
				int stt = 0;
				for (int i = 0; i < result.getDulieu().getCombo().getCtcombos().size(); i++) {
					noidung = noidung + result.getDulieu().getCombo().getCtcombos().get(i).getStt() + " - "
							+ result.getDulieu().getCombo().getCtcombos().get(i).getMon().getTen() + " \n";
				}
				noidung = noidung + "Và các loại nước:\n";
				for (int i = 0; i < result.getDulieu().getCtNuocDatTiecs().size(); i++) {
					stt = i + 1;
					noidung = noidung + stt + " - " + result.getDulieu().getCtNuocDatTiecs().get(i).getMon().getTen()
							+ " \n";
				}
				noidung = noidung + "Tiền cọc là: " + result.getDulieu().getTiencocFM() + " \n"
						+ "Vui lòng đến đúng thời gian để chúng tôi phục vụ quý khách\n"
						+ "Xin chân thành cảm ơn quý khách.";
				guiMailService.guimail(result.getDulieu().getNguoidung().getEmail().trim(), tieude, noidung);

				return "redirect:/dsdattiecadmin";
			}
			model.addAttribute("thongbao", result.getNoidung());
			return "admin_chitietdattiec";
		} else {
			return "dangnhap";
		}
	}

	@GetMapping("huyttdt")
	public String huyttdt(HttpSession session, Model model,
			@RequestParam(name = "id", required = false, defaultValue = "0") String id) {
		NguoiDungDTO dto = (NguoiDungDTO) session.getAttribute("userDangNhap");
		if (dto != null) {
			ResultEntity<TTDatTiecDTO> result = ttdattiecService.doiTT(id, dto.getId(), StringUtil.HUY,
					StringUtil.KHACHHANG);
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				session.setAttribute("ttdt", result.getDulieu());
				session.setAttribute("ctdt", result.getDulieu().getCombo().getCtcombos());
				return "chitietdattiec";
			}
			return "redirect:/";
		}
		return "dangnhap";
	}

	@GetMapping("adminhuyttdt")
	public String adminhuyttdt(HttpSession session, Model model,
			@RequestParam(name = "id", required = false, defaultValue = "0") String id) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			ResultEntity<TTDatTiecDTO> result = ttdattiecService.doiTT(id, userDN.getId(), StringUtil.HUY,
					StringUtil.NHANVIEN);
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				session.setAttribute("ttdt", result.getDulieu());
				session.setAttribute("ctdt", result.getDulieu().getCombo().getCtcombos());
				session.setAttribute("nguoidung", result.getDulieu().getNguoidung());
				return "admin_chitietdattiec";
			}
			return "redirect:/";
		}
		return "dangnhap";
	}

	@GetMapping("themcombo")
	public String themcombo(HttpSession session) {
		ResultEntity<List<MonDTO>> result = monService.dsAll(StringUtil.DOAN);
		if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
			session.setAttribute("moncombos", result.getDulieu());
			return "admin_themcombo";
		}
		return "redirect:/";
	}

	@PostMapping("combosession")
	public String luucombo(@RequestBody ComboDTO dto, HttpSession session) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		DecimalFormat df = new DecimalFormat("#,###");
		dto.setGiaFM(df.format(dto.getGia()));

		session.setAttribute("combosession", dto);
		session.setAttribute("dsctcombo", dto.getCtcombos());
		System.out.println(dto);

		return "redirect:/";
	}

	@GetMapping("tieptuccombo")
	public String tieptuccombo(HttpSession session) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");

		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {

			return "admin_tieptuccombo";
		}
		return "redirect:/";
	}

	@GetMapping("luucombo")
	public String luucombo(HttpSession session) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			ComboDTO dto = (ComboDTO) session.getAttribute("combosession");

			ResultEntity<ComboDTO> result = comboService.themCombo(userDN.getId(), dto);
			return "redirect:/qlcombo";
		}
		return "redirect:/";
	}

	@GetMapping("mon")
	public String mon(Model model, @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "15") Integer size,
			@RequestParam(name = "id", required = false, defaultValue = "allmon") String id) {
		ResultEntity<List<MonDTO>> result = monService.dsMonTheoLoai(id, page, size);
		if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
			int slPage = monService.slPage(id, StringUtil.DANGHOATDONG);
			model.addAttribute("idloai", id);
			PageConverter.pageFormat(model, page, slPage);
			model.addAttribute("mon", result.getDulieu());
			return "thucdon";
		}
		return "redirect:/";
	}

	@GetMapping("ttcanhan")
	public String ttcanhan(HttpSession session) {
		NguoiDungDTO dto = (NguoiDungDTO) session.getAttribute("userDangNhap");
		if (dto != null) {
			ResultEntity<NguoiDungDTO> result = nguoiDungService.timId(dto.getId());
			if (result.getKetqua().equals(StringUtil.THANHCONG)) {
				session.setAttribute("userDangNhap", result.getDulieu());
			}
			return "ttcanhan";
		}
		return "dangnhap";
	}

	@GetMapping("ttnhanvien")
	public String ttnhanvien(HttpSession session) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null) {
			return "ttnhanvien";
		} else {
			return "redirect:/admin";
		}
	}

	@GetMapping("formcapnhat")
	public String formcapnhat(HttpSession session, Model model) {
		NguoiDungDTO dto = (NguoiDungDTO) session.getAttribute("userDangNhap");
		if (dto != null) {
			model.addAttribute("dtocapnhat", dto);
			return "formcapnhat";
		} else {
			return "dangnhap";
		}

	}

	@PostMapping("luucapnhap")
	public String luucapnhat(@Valid NguoiDungDTO dto, HttpSession session, Model model) {
		NguoiDungDTO userDN = (NguoiDungDTO) session.getAttribute("userDangNhap");
		if (userDN != null) {
			ResultEntity<NguoiDungDTO> result = nguoiDungService.luucapnhat(dto, userDN.getId());
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				session.setAttribute("userDangNhap", result.getDulieu());
				return "redirect:/ttcanhan";
			} else {
				model.addAttribute("dtocapnhat", dto);
				model.addAttribute("trungsdtemail", result.getNoidung());
				return "formcapnhat";
			}
		} else {
			return "dangnhap";
		}
	}

	@GetMapping("formdoimatkhau")
	public String formdoimatkhau(HttpSession session) {
		NguoiDungDTO dto = (NguoiDungDTO) session.getAttribute("userDangNhap");
		if (dto != null) {
			return "formdoimatkhau";
		} else {
			return "dangnhap";
		}

	}

	@GetMapping("formdoimknv")
	public String formdoimknv(HttpSession session) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null) {
			return "formdoimatkhaunv";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("luumatkhau")
	public String luumatkhau(@Valid String pass, @Valid String newpass, @Valid String repass, HttpSession session,
			Model model) {
		NguoiDungDTO userDN = (NguoiDungDTO) session.getAttribute("userDangNhap");
		if (userDN != null) {
			if (newpass.equals(repass)) {
				ResultEntity<NguoiDungDTO> result = nguoiDungService.doiMatKhau(userDN.getId(), pass, newpass);
				if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
					return "redirect:/";
				} else {
					model.addAttribute("loidoimk", "Mật khẩu không đúng");
					return "formdoimatkhau";
				}
			} else {
				model.addAttribute("loidoimk", "Mật khẩu không khớp");
				return "formdoimatkhau";
			}
		} else {
			return "dangnhap";
		}
	}

	@PostMapping("luumatkhaunv")
	public String luumatkhaunv(@Valid String pass, @Valid String newpass, @Valid String repass, HttpSession session,
			Model model) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null) {
			if (newpass.equals(repass)) {
				ResultEntity<NhanVienDTO> result = nhanvienService.doiMatKhau(userDN.getId(), pass, newpass);
				if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
					return "redirect:/";
				} else {
					model.addAttribute("loidoimk", "Mật khẩu không đúng");
					return "formdoimatkhaunv";
				}
			} else {
				model.addAttribute("loidoimk", "Mật khẩu không khớp");
				return "formdoimatkhaunv";
			}
		} else {
			return "redirect:/admin";
		}
	}

	@PostMapping("dangky")
	public String add(@Valid NguoiDungDTO dto, HttpSession session, Model model) {
		if (dto.getMatkhau().equals(dto.getRematkhau())) {
			ResultEntity<NguoiDungDTO> result = nguoiDungService.dangKy(dto);
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				return "dangnhap";
			} else {
				session.setAttribute("dto", dto);
				model.addAttribute("sdtemail", result.getNoidung());
				return "dangky";
			}
		} else {
			session.setAttribute("dto", dto);
			model.addAttribute("passfail", "Mật khẩu không trùng khớp");
			return "dangky";
		}

	}

	@GetMapping("chitietmon")
	public String chitietmon(Model model, @RequestParam(name = "id", required = false, defaultValue = "0") String id) {
		ResultEntity<MonDTO> result = monService.timMon(id);
		if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
			model.addAttribute("monct", result.getDulieu());
			ResultEntity<List<MonDTO>> resultds = monService.tim(result.getDulieu().getTen());
			List<MonDTO> dsMonTuongTu = new ArrayList<>();
			int soluong = 0;
			for (int i = 0; i < resultds.getDulieu().size(); i++) {
				if (!resultds.getDulieu().get(i).getTen().equals(result.getDulieu().getTen()) && soluong < 3) {
					dsMonTuongTu.add(resultds.getDulieu().get(i));
					soluong++;
				}
			}

			model.addAttribute("dsTuongTu", dsMonTuongTu);

			return "xemchitietmon";
		}

		return "redirect:/";
	}

	@GetMapping("adminchitietmon")
	public String adminchitietmon(HttpSession session, Model model,
			@RequestParam(name = "id", required = false, defaultValue = "0") String id) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			ResultEntity<MonDTO> result = monService.timMon(id);
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				model.addAttribute("monct", result.getDulieu());
				return "admin_xemchitietmon";
			}
		}
		return "redirect:/";
	}

	@GetMapping("thanhtoan")
	public String thanhtoan(HttpSession session, Model model,
			@RequestParam(name = "id", required = false, defaultValue = "0") String id) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			ResultEntity<TTDatTiecDTO> result = ttdattiecService.timid(id, userDN.getId(), StringUtil.NHANVIEN);
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				model.addAttribute("ttdtthanhtoan", result.getDulieu());
				model.addAttribute("dsctdt", result.getDulieu().getCtNuocDatTiecs());
				model.addAttribute("slnuoc", result.getDulieu().getCtNuocDatTiecs().size());
				return "admin_thanhtoan";
			}
		}
		return "redirect:/";
	}

	@GetMapping("adminchitietsanh")
	public String adminchitietsanh(HttpSession session, Model model,
			@RequestParam(name = "id", required = false, defaultValue = "0") String id) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			ResultEntity<SanhDTO> result = sanhService.timId(id);
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				model.addAttribute("sanhct", result.getDulieu());
				return "admin_xemchitietsanh";
			}

		}

		return "redirect:/";
	}

	@GetMapping("chitietsanh")
	public String chitietsanh(Model model, @RequestParam(name = "id", required = false, defaultValue = "0") String id) {
		ResultEntity<SanhDTO> result = sanhService.timId(id);
		if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
			model.addAttribute("sanhct", result.getDulieu());
			return "xemchitietsanh";
		}

		return "redirect:/";
	}

	@GetMapping("qlnhanvien")
	public String qlnhanvien(HttpSession session, Model model,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "15") Integer size) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");

		if (userDN != null && StringUtil.ADMIN.equals(userDN.getChucvu())) {
			ResultEntity<List<NhanVienDTO>> result = nhanvienService.dsNhanVien(userDN.getId(), StringUtil.NHANVIEN,
					page, size);
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				int slPage = nhanvienService.slPage(StringUtil.DANGHOATDONG, StringUtil.NHANVIEN);
				PageConverter.pageFormat(model, page, slPage);
				model.addAttribute("dsNV", result.getDulieu());
				return "adminqlnhanvien";
			}

		}
		return "redirect:/";

	}

	@GetMapping("qlkhachhang")
	public String qlkhachhang(HttpSession session, Model model,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "15") Integer size) {

		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null && StringUtil.ADMIN.equals(userDN.getChucvu())) {
			ResultEntity<List<NguoiDungDTO>> result = nguoiDungService.dsKH(userDN.getId(), page, size);
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				int slPage = nguoiDungService.slPage(StringUtil.DANGHOATDONG);
				PageConverter.pageFormat(model, page, slPage);
				model.addAttribute("dsKH", result.getDulieu());
				return "adminqlkhachhang";
			}
		}
		return "redirect:/";
	}

	@GetMapping("themnhanvien")
	public String formthemnv(HttpSession session) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null && StringUtil.ADMIN.equals(userDN.getChucvu())) {
			return "themnhanvien";
		} else {
			return "redirect:/";
		}
	}
	
	@GetMapping("formcapnhatnhanvien")
	public String formcapnhatnhanvien(HttpSession session,Model model) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null && StringUtil.ADMIN.equals(userDN.getChucvu())) {
			model.addAttribute("dto", userDN);
			return "formcapnhatnhanvien";
		} else {
			return "redirect:/";
		}
	}
	
	@GetMapping("admincapnhatnv")
	public String admincapnhatnv(HttpSession session,Model model,
			@RequestParam(name = "id", required = false, defaultValue = "") String id) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null && StringUtil.ADMIN.equals(userDN.getChucvu())) {
			ResultEntity<NhanVienDTO> result = nhanvienService.nhanvienId(id);
			model.addAttribute("dto", result.getDulieu());
			return "admin_formcapnhatnhanvien";
		} else {
			return "redirect:/";
		}
	}
	
	@GetMapping("capnhatnvtv")
	public String capnhatnvtv(HttpSession session,Model model,
			@RequestParam(name = "id", required = false, defaultValue = "") String id) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			ResultEntity<NhanVienDTO> result = nhanvienService.nhanvienId(id);
			model.addAttribute("dto", result.getDulieu());
			return "admin_formcapnhatnhanvienthoivu";
		} else {
			return "redirect:/";
		}
	}
	
	

	@PostMapping("luunhanvien")
	public String luunhanvien(@Valid NhanVienDTO dto, HttpSession session, Model model) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (null != userDN) {
			if (dto.getMatkhau().equals(dto.getRematkhau())) {
				dto.setChucvu(StringUtil.NHANVIEN);

				ResultEntity<NhanVienDTO> result = nhanvienService.themNV(dto, userDN.getId());
				if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
					return "redirect:/qlnhanvien";
				} else {
					session.setAttribute("dto", dto);
					model.addAttribute("thongbaothemnv", result.getNoidung());
					return "themnhanvien";
				}
			} else {
				session.setAttribute("dto", dto);
				model.addAttribute("passfail", "Mật khẩu không trùng khớp");
				return "themnhanvien";
			}
		}
		return "redirect:/";

	}
	
	
	@PostMapping("luucapnhatnhanvien")
	public String luucapnhatnhanvien(@Valid NhanVienDTO dto, HttpSession session, Model model) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (null != userDN) {
			dto.setLuong(0);
			ResultEntity<NhanVienDTO> result = nhanvienService.capNhat(dto);
			if (result.getKetqua().equals(StringUtil.THANHCONG)) {
				session.setAttribute("userDangNhap", result.getDulieu());
				return "redirect:/ttnhanvien";
			}else {
				model.addAttribute("dto", dto);
				model.addAttribute("thongbao", result.getNoidung());
				return "formcapnhatnhanvien";
			}
		}
		return "redirect:/";

	}
	
	@PostMapping("luucapnhatnhanvienadmin")
	public String luucapnhatnhanvienadmin(@Valid NhanVienDTO dto, HttpSession session, Model model) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (null != userDN) {
			ResultEntity<NhanVienDTO> result = nhanvienService.capNhat(dto);
			if (result.getKetqua().equals(StringUtil.THANHCONG)) {
				if (result.getDulieu().getChucvu().equals(StringUtil.NHANVIEN)) {
					return "redirect:/qlnhanvien";
				}else {
					return "redirect:/dsnvthoivu";
				}
				
			}else {
				model.addAttribute("dto", dto);
				model.addAttribute("thongbao", result.getNoidung());
				if (dto.getChucvu().equals(StringUtil.NHANVIEN)) {
					return "admin_formcapnhatnhanvien";
				}else {
					return "admin_formcapnhatnhanvienthoivu";
				}
				
			}
		}
		return "redirect:/";

	}
	

	@GetMapping("qlmon")
	public String thucdon(HttpSession session, Model model,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "15") Integer size,
			@RequestParam(name = "id", required = false, defaultValue = "allmon") String id) {
		ResultEntity<List<LoaiMonDTO>> resultLoai = loaiMonService.loaiMonAll();
		session.setAttribute("loaimon", resultLoai.getDulieu());
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			ResultEntity<List<MonDTO>> result = monService.dsMonTheoLoai(id, page, size);
			if (StringUtil.THANHCONG.equals(result.getKetqua())) {
				int slPage = monService.slPage(id, StringUtil.DANGHOATDONG);
				PageConverter.pageFormat(model, page, slPage);
				model.addAttribute("idloai", id);
				model.addAttribute("dsmon", result.getDulieu());
				return "admin_thucdon";
			}
		}
		return "redirect:/";
	}

	@GetMapping("themloai")
	public String nuocloc(HttpSession session) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {

			return "admin_themloai";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("luuloaimon")
	public String themloai(@Valid LoaiMonDTO dto, HttpSession session, Model model) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			ResultEntity<LoaiMonDTO> result = loaiMonService.themLoai(userDN.getId(), dto);
			ResultEntity<List<LoaiMonDTO>> resultLoai = loaiMonService.loaiMonAll();
			session.setAttribute("loaimon", resultLoai.getDulieu());
			if (StringUtil.THANHCONG.equals(result.getKetqua())) {
				return "redirect:/qlloai";
			}
		}
		session.setAttribute("loaimaloi", dto);
		model.addAttribute("tenloailoi", "Tên loại đã tồn tại");
		return "admin_themloai";

	}

	@GetMapping("themmon")
	public String themmon(HttpSession session) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			return "admin_themmon";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("luumon")
	public String themMon(@Valid MonDTO dto, @RequestParam("file") MultipartFile file, HttpSession session,
			Model model) {
		if (file.getOriginalFilename().length() == 0) {
			model.addAttribute("monnew", dto);
			model.addAttribute("hinhloi", "Vui lòng chọn hình");
			return "admin_themmon";
		}

		if (dto.getGia() < 0) {
			model.addAttribute("monnew", dto);
			model.addAttribute("gialoi", "Giá phải lớn hơn 0");
			return "admin_themmon";
		}
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (null != userDN) {
			ResultEntity<String> resultUpfile = upload.upload(file);
			dto.setHinh(resultUpfile.getDulieu());
			ResultEntity<MonDTO> result = monService.themMon(userDN.getId(), dto);
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				return "redirect:/qlmon";
			} else {
				model.addAttribute("monnew", dto);
				model.addAttribute("monloi", result.getNoidung());
				return "admin_themmon";
			}
		}
		return "redirect:/";

	}

	@GetMapping("bangluong")
	public String bangluong(Model model, HttpSession session,
			@RequestParam(name = "thang", required = false, defaultValue = "0") Integer thang,
			@RequestParam(name = "nam", required = false, defaultValue = "0") Integer nam) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		Date hientai = new Date();
		if (thang == 0) {
			thang = hientai.getMonth() + 1;
		}
		if (nam == 0) {
			nam = hientai.getYear() + 1900;
		}
		if (null != userDN && StringUtil.ADMIN.equals(userDN.getChucvu())) {
			ResultEntity<List<LuongDTO>> result = luongService.dsTheoThang(userDN.getId(), thang, nam);
			if (result.getKetqua().equals(StringUtil.THANHCONG)) {
				model.addAttribute("dsLuong", result.getDulieu());

				double tongluongthang = 0;
				for (int i = 0; i < result.getDulieu().size(); i++) {
					tongluongthang = tongluongthang + result.getDulieu().get(i).getTienluong();
				}
				DecimalFormat df = new DecimalFormat("#,###");
				model.addAttribute("tongluongthang", df.format(tongluongthang));
				model.addAttribute("thanghientai", thang);
				model.addAttribute("namhientai", nam);
				model.addAttribute("tongdsluong", result.getDulieu().size());

				return "admin_bangluong";
			}

		}
		return "redirect:/";
	}

	@GetMapping("formtinhluong")
	public String formtinhluong(Model model, HttpSession session,
			@RequestParam(name = "thang", required = false, defaultValue = "0") Integer thang,
			@RequestParam(name = "nam", required = false, defaultValue = "0") Integer nam) {

		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");

		if (null != userDN && StringUtil.ADMIN.equals(userDN.getChucvu())) {
			ResultEntity<List<NhanVienDTO>> result = nhanvienService.dsAll(StringUtil.DANGHOATDONG, userDN.getId(), nam,
					thang);
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				model.addAttribute("dsNV", result.getDulieu());
				model.addAttribute("thanghientai", thang);
				model.addAttribute("namhientai", nam);
				return "admin_tinhluong";
			}

		}
		return "redirect:/";

	}

	@PostMapping("luutinhluong")
	public String luutinhluong(@RequestBody List<LuongDTO> luong, HttpSession session) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");

		if (null != userDN && StringUtil.ADMIN.equals(userDN.getChucvu())) {
			ResultEntity<List<LuongDTO>> result = luongService.tinhLuong(luong);
		}
		return "";
	}

	@GetMapping("dsnhomphucvu")
	public String thoivu(HttpSession session, Model model,
			@RequestParam(name = "ngaylam", required = false, defaultValue = "hientai") String ngaylam) {

		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			Date d;
			if (ngaylam.equalsIgnoreCase("hientai")) {
				d = new Date();
			} else {
				d = DateUI.formatDateUI(ngaylam, StringUtil.CU);
			}
			DecimalFormat df = new DecimalFormat("#,###");
			SimpleDateFormat datefm = new SimpleDateFormat("dd-MM-yyyy");
			session.setAttribute("ngaythemnhom", d);
			Date dhientai = new Date();
			dhientai.setDate(dhientai.getDate() - 1);
			boolean themnhoms = true;
			if (d.compareTo(dhientai) != 1) {
				themnhoms = false;
			}
			model.addAttribute("themnhoms", themnhoms);

			model.addAttribute("datexem", datefm.format(d));
			ResultEntity<List<NhomThoiVuDTO>> result = nhomThoiVuService.dsNhom(d);
			model.addAttribute("nhoms", result.getDulieu());

			int tongnv = 0;
			double tongluongnhom = 0;
			for (int i = 0; i < result.getDulieu().size(); i++) {
				tongnv = tongnv + result.getDulieu().get(i).getSlnv();
				tongluongnhom = tongluongnhom + result.getDulieu().get(i).getTongluong();
			}
			model.addAttribute("tongnhoms", result.getDulieu().size());
			model.addAttribute("tongnvnhoms", tongnv);
			model.addAttribute("tongluongnhoms", df.format(tongluongnhom));

			return "admin_thoivu";
		}
		return "redirect:/";
	}

	@GetMapping("formthemnhom")
	public String formthemnhom(HttpSession session) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			return "admin_formthemnhom";
		}
		return "redirect:/";

	}

	@PostMapping("luunhomthoivu")
	public String luunhomthoivu(@Valid NhomThoiVuDTO dto, HttpSession session, Model model) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			Date d = (Date) session.getAttribute("ngaythemnhom");
			dto.setNgaylam(d);
			ResultEntity<NhomThoiVuDTO> result = nhomThoiVuService.luuNhom(userDN.getId(), dto);
			if (result.getKetqua().equals(StringUtil.THANHCONG)) {
				SimpleDateFormat datefm = new SimpleDateFormat("yyyy-MM-dd");
				return "redirect:/dsnhomphucvu?ngaylam=" + datefm.format(d);
			}
			model.addAttribute("thongbaothemnhom", result.getNoidung());
			return "admin_formthemnhom";
		}
		return "redirect:/";
	}

	@GetMapping("xemnhom")
	public String xemnhom(HttpSession session, Model model,
			@RequestParam(name = "id", required = false, defaultValue = "") String id) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			ResultEntity<NhomThoiVuDTO> result = nhomThoiVuService.timId(id);
			if (result.getKetqua().equals(StringUtil.THANHCONG)) {
				model.addAttribute("nhom", result.getDulieu());
				model.addAttribute("nhanvientvs", result.getDulieu().getNhanVienThoiVuDTOs());
				return "admin_xemnhom";
			}
		}
		return "redirect:/";

	}

	@GetMapping("formthemnvthoivu")
	public String formthemnvthoivu(HttpSession session,
			@RequestParam(name = "nhomid", required = false, defaultValue = "") String nhomid) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			session.setAttribute("idnhomthem", nhomid);
			return "admin_formthemnvthoivu";
		}
		return "redirect:/";

	}

	@PostMapping("lunhanvienthoivu")
	public String lunhanvienthoivu(@Valid NhanVienThoiVuDTO dto, HttpSession session, Model model) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			String nhomId = (String) session.getAttribute("idnhomthem");
			ResultEntity<NhanVienThoiVuDTO> result = nhanVienThoiVuService.themNvtv(nhomId, dto);
			if (result.getKetqua().equals(StringUtil.THANHCONG)) {
				return "redirect:/xemnhom?id=" + nhomId;
			}
			model.addAttribute("thongbaonvtv", result.getNoidung());
			return "admin_formthemnhom";
		}
		return "redirect:/";
	}

	@GetMapping("xemluongcanhan")
	public String xemluongcanhan(HttpSession session, Model model,
			@RequestParam(name = "nam", required = false, defaultValue = "0") Integer nam) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			if (nam == 0) {
				Date d = new Date();
				nam = d.getYear() + 1900;
			}
			System.out.println(nam);
			ResultEntity<List<LuongDTO>> result = luongService.dsTheoNhanVien(userDN.getId(), nam);
			model.addAttribute("luongcanhans", result.getDulieu());
			model.addAttribute("namxemluong", nam);
			return "xemluongcanhan";
		}
		return "redirect:/";

	}

	@GetMapping("chamcong")
	public String chamcong(HttpSession session, Model model,
			@RequestParam(name = "ngaycong", required = false, defaultValue = "hientai") String ngaycong) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null && StringUtil.ADMIN.equals(userDN.getChucvu())) {
			Date d;
			if (ngaycong.equalsIgnoreCase("hientai")) {
				d = new Date();
			} else {
				d = DateUI.formatDateUI(ngaycong, StringUtil.CU);
			}
			model.addAttribute("ngaycong", ngaycong);
			SimpleDateFormat datefm = new SimpleDateFormat("dd-MM-yyyy");
			model.addAttribute("ngaychamcong", datefm.format(d));
			ResultEntity<List<NhanVienDTO>> result = nhanvienService.dsCongTheoNgay(d, StringUtil.DANGHOATDONG,
					StringUtil.NHANVIEN);
			model.addAttribute("dsnvchamcong", result.getDulieu());

			ResultEntity<List<LuongDTO>> resultluong = luongService.dsTheoThang(userDN.getId(), d.getMonth() + 1,
					d.getYear() + 1900);
			model.addAttribute("thanghientai", d.getMonth() + 1);
			model.addAttribute("namhientai", d.getYear() + 1900);
			model.addAttribute("tongdsluong", resultluong.getDulieu().size());

			return "admin_dschamcong";
		}
		return "redirect:/";

	}

	@GetMapping("chamcongnhanvien")
	public String chamcongnhanvien(HttpSession session, Model model,
			@RequestParam(name = "id", required = false, defaultValue = "") String id,
			@RequestParam(name = "ngaycong", required = false, defaultValue = "hientai") String ngaycong) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null && StringUtil.ADMIN.equals(userDN.getChucvu())) {
			Date d;
			if (ngaycong.equalsIgnoreCase("hientai")) {
				d = new Date();
			} else {
				d = DateUI.formatDateUI(ngaycong, StringUtil.CU);
			}
			BangCongDTO dto = new BangCongDTO();
			NhanVienDTO nvDTO = new NhanVienDTO();
			nvDTO.setId(id);
			dto.setNhanvien(nvDTO);
			dto.setNgaycong(d);
			dto.setCong(StringUtil.CANGAY);
			bangcongService.chamCong(dto);

			return "redirect:/chamcong?ngaycong=" + ngaycong;
		}
		return "redirect:/";

	}

	@GetMapping("xembangcongnv")
	public String xembangcongnv(HttpSession session, Model model,
			@RequestParam(name = "id", required = false, defaultValue = "") String id,
			@RequestParam(name = "nam", required = false, defaultValue = "0") int nam,
			@RequestParam(name = "thang", required = false, defaultValue = "0") int thang) {

		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");

		if (null != userDN && StringUtil.ADMIN.equals(userDN.getChucvu())) {
			Date d = new Date();
			if (nam == 0) {
				nam = d.getYear() + 1900;
			}
			if (thang == 0) {
				thang = d.getMonth() + 1;
			}

			ResultEntity<List<BangCongDTO>> result = bangcongService.dsCongNhanVien(id, thang, nam);
			model.addAttribute("congnhanviens", result.getDulieu());
			ResultEntity<NhanVienDTO> nvResult = nhanvienService.nhanvienId(id);
			if (nvResult.getKetqua().equals(StringUtil.THANHCONG)) {
				model.addAttribute("nhanvienxem", nvResult.getDulieu());
			}

			model.addAttribute("congthang", result.getDulieu().size());
			model.addAttribute("timebangcong", "tháng " + thang + " năm " + nam);
			return "admin_xembangcongnhanvien";

		}
		return "redirect:/";

	}

	@GetMapping("/nhanvienthoivu")
	public String nhanvienthoivu(HttpSession session, Model model,
			@RequestParam(name = "ngaylam", required = false, defaultValue = "hientai") String ngaylam) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			SimpleDateFormat datefm = new SimpleDateFormat("dd-MM-yyyy");
			DecimalFormat df = new DecimalFormat("#,###");
			model.addAttribute("ngaylam", ngaylam);
			Date d;
			if (ngaylam.equalsIgnoreCase("hientai")) {
				d = new Date();
			} else {
				d = DateUI.formatDateUI(ngaylam, StringUtil.CU);
			}
			SimpleDateFormat sfm = new SimpleDateFormat("dd-MM-yyyy");
			System.out.println(sfm.format(d));
			ResultEntity<List<BangCongDTO>> result = bangcongService.dsCongTheoNgay(d, StringUtil.DANGHOATDONG,
					StringUtil.NHANVIENTHOIVU);

			Date dhientai = new Date();
			dhientai.setDate(dhientai.getDate() - 1);
			boolean themnvtrue = true;
			if (d.compareTo(dhientai) != 1) {
				themnvtrue = false;
			}
			model.addAttribute("themnvtrue", themnvtrue);

			model.addAttribute("bangcongs", result.getDulieu());
			model.addAttribute("timelam", datefm.format(d));
			model.addAttribute("tongnv", result.getDulieu().size());
			double luong = 0;
			for (int i = 0; i < result.getDulieu().size(); i++) {
				luong = luong + result.getDulieu().get(i).getLuong();
			}
			model.addAttribute("tongluong", df.format(luong));
			return "admin_nhanvienthoivu";
		}
		return "redirect:/";
	}

	@GetMapping("dsnhanvienthoivu")
	public String dsnhanvienthoivu(HttpSession session, Model model,
			@RequestParam(name = "ngaylam", required = false, defaultValue = "hientai") String ngaylam) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");

		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			Date d;
			if (ngaylam.equalsIgnoreCase("hientai")) {
				d = new Date();
			} else {
				d = DateUI.formatDateUI(ngaylam, StringUtil.CU);
			}
			ResultEntity<List<NhanVienDTO>> result = nhanvienService.dsCongTheoNgay(d, StringUtil.DANGHOATDONG,
					StringUtil.NHANVIENTHOIVU);
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				SimpleDateFormat datefm = new SimpleDateFormat("dd-MM-yyyy");
				model.addAttribute("ngaylam", ngaylam);

				model.addAttribute("dsNVthoivu", result.getDulieu());
				return "admin_themnvlam";
			}

		}
		return "redirect:/";

	}

	@GetMapping("/themnvthoivu")
	public String themnvthoivu(HttpSession session, Model model,
			@RequestParam(name = "trang", required = false, defaultValue = "") String trang) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			session.setAttribute("trangthemthoivu", trang);
			return "admin_themnhanvientv";
		}
		return "redirect:/";
	}

	@PostMapping("luunhanvientv")
	public String luunhanvientv(@Valid NhanVienDTO dto, HttpSession session, Model model) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			dto.setChucvu(StringUtil.NHANVIENTHOIVU);
			ResultEntity<NhanVienDTO> result = nhanvienService.themNV(dto, userDN.getId());
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {

				String trang = (String) session.getAttribute("trangthemthoivu");
				if (trang.equalsIgnoreCase("quanly")) {
					return "redirect:/dsnvthoivu";
				}
				return "redirect:/dsnhanvienthoivu";
			} else {
				session.setAttribute("dto", dto);
				model.addAttribute("thongbaothemnv", result.getNoidung());
				return "admin_themnhanvientv";

			}
		}
		return "redirect:/";

	}

	@GetMapping("themnvlam")
	public String themnvlam(HttpSession session, Model model,
			@RequestParam(name = "id", required = false, defaultValue = "") String id,
			@RequestParam(name = "calam", required = false, defaultValue = "Cả ngày") String calam,
			@RequestParam(name = "ngaylam", required = false, defaultValue = "hientai") String ngaylam) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			Date d;
			if (ngaylam.equalsIgnoreCase("hientai")) {
				d = new Date();
			} else {
				d = DateUI.formatDateUI(ngaylam, StringUtil.CU);
			}
			BangCongDTO dto = new BangCongDTO();
			NhanVienDTO nvDTO = new NhanVienDTO();
			nvDTO.setId(id);
			dto.setNhanvien(nvDTO);
			dto.setNgaycong(d);
			dto.setCong(calam);
			bangcongService.chamCong(dto);

			return "redirect:/dsnhanvienthoivu?ngaylam=" + ngaylam;
		}
		return "redirect:/";

	}

	@GetMapping("dsnvthoivu")
	public String dsnvthoivu(HttpSession session, Model model,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "15") Integer size) {

		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			ResultEntity<List<NhanVienDTO>> result = nhanvienService.dsNhanVien(userDN.getId(),
					StringUtil.NHANVIENTHOIVU, page, size);
			if (StringUtil.THANHCONG.equalsIgnoreCase(result.getKetqua())) {
				int slPage = nhanvienService.slPage(StringUtil.DANGHOATDONG, StringUtil.NHANVIENTHOIVU);
				PageConverter.pageFormat(model, page, slPage);
				model.addAttribute("dsNV", result.getDulieu());
				return "admin_qlnhanvienthoivu";
			}

		}
		return "redirect:/";

	}

	@GetMapping("timnhanvienthoivu")
	public String timnhanvientv(@RequestParam(name = "tim", required = false, defaultValue = "") String tim,
			HttpSession session, Model model) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			model.addAttribute("keyword", tim);
			if (tim.length() == 0) {
				return "redirect:/qlnhanvien";
			}
			if (tim.equalsIgnoreCase("a")) {
				tim = tim + "_";
			}
			ResultEntity<List<NhanVienDTO>> result = nhanvienService.tim(tim, StringUtil.NHANVIENTHOIVU);
			session.setAttribute("dsNV", result.getDulieu());
			if (result.getDulieu().size() == 0) {
				model.addAttribute("dsrong", "Không tìm thấy nhân viên phù hợp!");
			}
			return "admin_qlnhanvienthoivu";
		}
		return "redirect:/";
	}
	
	@GetMapping("xemcongnvtv")
	public String xemcongnvtv(
			@RequestParam(name = "nam", required = false, defaultValue = "0") int nam,
			@RequestParam(name = "thang", required = false, defaultValue = "0") int thang,
			@RequestParam(name = "id", required = false, defaultValue = "") String id,
			HttpSession session, Model model) {
		NhanVienDTO userDN = (NhanVienDTO) session.getAttribute("userDangNhap");
		if (userDN != null
				&& (StringUtil.ADMIN.equals(userDN.getChucvu()) || StringUtil.NHANVIEN.equals(userDN.getChucvu()))) {
			Date d = new Date();
			if (nam == 0) {
				nam = d.getYear() + 1900;
			}
			if (thang == 0) {
				thang = d.getMonth() + 1;
			}
			DecimalFormat df = new DecimalFormat("#,###");
			ResultEntity<NhanVienDTO> resultNV = nhanvienService.nhanvienId(id);
			model.addAttribute("nv", resultNV.getDulieu());
			ResultEntity<List<BangCongDTO>> result = bangcongService.dsCongNhanVien(id, thang, nam);
			model.addAttribute("congs", result.getDulieu());
			model.addAttribute("thang", thang);
			model.addAttribute("nam", nam);
			double tong = 0;
			for (int i = 0; i < result.getDulieu().size(); i++) {
				tong = tong+result.getDulieu().get(i).getLuong();
			}
			model.addAttribute("tong", df.format(tong));
			return "admin_xemcongnvtv";
		}
		return "redirect:/";
	}

}
