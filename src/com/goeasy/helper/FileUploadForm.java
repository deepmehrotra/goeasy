package com.goeasy.helper;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadForm {

	private List<MultipartFile> files;
	
	private String sheetValue;

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	public String getSheetValue() {
		return sheetValue;
	}

	public void setSheetValue(String sheetValue) {
		this.sheetValue = sheetValue;
	}
}
