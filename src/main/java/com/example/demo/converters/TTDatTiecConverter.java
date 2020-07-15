package com.example.demo.converters;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.dtos.CTNuocDatTiecDTO;
import com.example.demo.dtos.TTDatTiecDTO;
import com.example.demo.entitys.TTDatTiec;
import com.example.demo.utils.StringUtil;

public class TTDatTiecConverter {
	public static TTDatTiecDTO toDTO(TTDatTiec entity) {
		DecimalFormat dcf = new DecimalFormat("#,###");
		SimpleDateFormat dfm = new SimpleDateFormat("yyyy-MM-dd");
		TTDatTiecDTO dto = new TTDatTiecDTO();
		dto.setId(entity.getId());
		dto.setCatochuc(entity.getCatochuc());
		dto.setNgaytochuc(dfm.format(entity.getNgaytochuc()));
		dto.setNguoidung(NguoiDungConverter.toDTO(entity.getNguoidung()));
		dto.setCombo(ComboConverter.toDTO(entity.getCombo()));
		dto.setSanh(SanhConverter.toDTO(entity.getSanh()));
		dto.setTrangthai(entity.getTrangthai());
		dto.setNgaytao(entity.getNgaytao());
		dto.setNgaycapnhat(entity.getNgaycapnhat());
		dto.setGhichu(entity.getGhichu());
		dto.setTiencoc(entity.getTiencoc());
		dto.setSoban(entity.getSoban());
		dto.setSokhach(entity.getSokhach());
		dto.setLoai(entity.getLoai());
		
		if (StringUtil.DANGCHODUYET.equalsIgnoreCase(entity.getTrangthai())) {
			dto.setTthientai("Đang chờ duyệt");
		}else if (StringUtil.DADUYET.equalsIgnoreCase(entity.getTrangthai())) {
			dto.setTthientai("Đã duyệt");
		}else if (StringUtil.DATHANHTOAN.equalsIgnoreCase(entity.getTrangthai())) {
			dto.setTthientai("Đang thanh toán");
		}else if (StringUtil.HUY.equalsIgnoreCase(entity.getTrangthai())) {
			dto.setTthientai("Đã hủy");
		}
		double giatrungbinh = 0;
		double tonggianuoc = 0;
		double giauocluongmonan = dto.getCombo().getGia()*dto.getSoban();
		double giauocluongnuocuong = 0;
		if (entity.getCtNuocDatTiecs()!=null && !entity.getCtNuocDatTiecs().isEmpty()) {
			List<CTNuocDatTiecDTO> ctndto = entity.getCtNuocDatTiecs()
					.stream().map(CTNuocDatTiecConverter::toDTO)
					.collect(Collectors.toList());
			dto.setCtNuocDatTiecs(ctndto);	
			for (int i = 0; i < dto.getCtNuocDatTiecs().size(); i++) {
				tonggianuoc = tonggianuoc + dto.getCtNuocDatTiecs().get(i).getMon().getGia();
			}
			giatrungbinh = (tonggianuoc/dto.getCtNuocDatTiecs().size())*2;
			giauocluongnuocuong = giatrungbinh*dto.getSokhach();
		}
		
		System.out.println(giauocluongmonan);
		
		dto.setGiauocluong(giauocluongmonan+giauocluongnuocuong);
		dto.setTiencocFM(dcf.format(entity.getTiencoc()));
		dto.setGiauocluongFM(dcf.format(dto.getGiauocluong()));
		return dto;
	}

}
