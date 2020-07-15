package com.example.demo.services.impls;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entitys.ResultEntity;
import com.example.demo.services.UploadFileService;
import com.example.demo.utils.MD5;
import com.example.demo.utils.RandomStringExmple;
import com.example.demo.utils.StringUtil;

@Service
public class UploadFileServiceImpl implements UploadFileService{
	@Value("${app.pathfile}")
	private String path;
	
	RandomStringExmple rd = new RandomStringExmple();
	
	@Override
	public ResultEntity<String> upload(MultipartFile file) {
		ResultEntity<String> result = new ResultEntity<>();
		InputStream inStream = null;
        OutputStream outStream = null;
        
        String randomname = rd.randomAlphaNumeric(10);
        String name = MD5.maHoaMatKhau(randomname);
        
        String filePath = "/opt/files/";
        String filename = name+file.getOriginalFilename();
        
        try {
        	inStream = file.getInputStream();
            outStream = new FileOutputStream(new File(filePath+filename));
 
            int length;
            byte[] buffer = new byte[4096];
 
            // copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }
            System.out.println("File is copied successful!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
            	inStream.close();
	            outStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        result.setDulieu(filename);
        result.setKetqua(StringUtil.THANHCONG);
        return result;
	}

}
