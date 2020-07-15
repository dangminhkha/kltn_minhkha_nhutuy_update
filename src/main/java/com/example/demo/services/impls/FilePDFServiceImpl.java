package com.example.demo.services.impls;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.example.demo.dtos.ThongKeDTO;
import com.example.demo.services.FilePDFService;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class FilePDFServiceImpl implements FilePDFService{

	@Override
	public void Export(ThongKeDTO thongke,String thoigian, HttpServletResponse response) {

		Document document = new Document(PageSize.A4);
		try {
			PdfWriter.getInstance(document, response.getOutputStream());
			document.open();
			PdfPTable table = new PdfPTable(2);
			
			Font fontTitle = new Font(Font.BOLD);
			fontTitle.setSize(25);
			Paragraph title = new Paragraph("Live Dinner Restaurant",fontTitle);
			title.setAlignment(Paragraph.ALIGN_CENTER);
			
			Paragraph o = new Paragraph("--------------------------------------------------------------------");
			o.setAlignment(Paragraph.ALIGN_CENTER);
			
			Paragraph enter = new Paragraph("\n");
			enter.setAlignment(Paragraph.ALIGN_CENTER);
			
			Font fontTitleTK = new Font(Font.BOLD);
			fontTitleTK.setSize(17);
			Paragraph titleTk = new Paragraph("Bảng thống kê của tháng "+thoigian,fontTitleTK);
			titleTk.setAlignment(Paragraph.ALIGN_CENTER);
						
			document.add(title);
			document.add(o);
			document.add(titleTk);
			document.add(enter);
			
			PdfPCell cellTongDonDatTiet = new PdfPCell();
			cellTongDonDatTiet.setPhrase(new Phrase("Tổng đơn đặt tiệc"));
			table.addCell(cellTongDonDatTiet);
			
			PdfPCell celldatatongdondattiec = new PdfPCell();
			celldatatongdondattiec.setPhrase(new Phrase(String.valueOf(thongke.getTongdondattiec())));
			table.addCell(celldatatongdondattiec);
			
			
			PdfPCell celltongdontochuc = new PdfPCell();
			celltongdontochuc.setPhrase(new Phrase("Tổng đơn đã tổ chức"));
			table.addCell(celltongdontochuc);
			
			PdfPCell celldatadontochuc = new PdfPCell();
			celldatadontochuc.setPhrase(new Phrase(String.valueOf(thongke.getTongdondathanhtoan())));
			table.addCell(celldatadontochuc);
			
			PdfPCell celldonhuy = new PdfPCell();
			celldonhuy.setPhrase(new Phrase("Tổng đơn đã đã hủy"));
			table.addCell(celldonhuy);
			
			PdfPCell calldatahuy = new PdfPCell();
			calldatahuy.setPhrase(new Phrase(String.valueOf(thongke.getTongdondahuy())));
			table.addCell(calldatahuy);
			
			PdfPCell cellDoanhthu = new PdfPCell();
			cellDoanhthu.setPhrase(new Phrase("Doanh thu"));
			table.addCell(cellDoanhthu);
			
			PdfPCell cellDatadoanhthu = new PdfPCell();
			cellDatadoanhthu.setPhrase(new Phrase(thongke.getTongtiendathanhtoanFM()));
			table.addCell(cellDatadoanhthu);
			
			PdfPCell cellluongnvchinhthuc = new PdfPCell();
			cellluongnvchinhthuc.setPhrase(new Phrase("Tổng lương nhân viên chính thức"));
			table.addCell(cellluongnvchinhthuc);
			
			PdfPCell celldataluongchinhthuc = new PdfPCell();
			celldataluongchinhthuc.setPhrase(new Phrase(thongke.getTongluongnhanvienchinhthucFM()));
			table.addCell(celldataluongchinhthuc);
			
			PdfPCell cellluongthoivu = new PdfPCell();
			cellluongthoivu.setPhrase(new Phrase("Tổng lương nhân viên thời vụ"));
			table.addCell(cellluongthoivu);
			
			PdfPCell celldatathoivu = new PdfPCell();
			celldatathoivu.setPhrase(new Phrase(thongke.getTongluongnhanvienthoivuFM()));
			table.addCell(celldatathoivu);
			
			document.add(table);
			document.close();
			
			
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}}
