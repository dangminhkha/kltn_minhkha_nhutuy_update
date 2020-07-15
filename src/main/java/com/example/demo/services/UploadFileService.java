package com.example.demo.services;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entitys.ResultEntity;

public interface UploadFileService {
	ResultEntity<String> upload(MultipartFile file);
}
