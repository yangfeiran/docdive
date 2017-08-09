package com.chiancloud.docdive.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.chiancloud.docdive.dao.Dao;
import com.chiancloud.docdive.domain.Category;
import com.chiancloud.docdive.service.FileService;

@Controller  
@RequestMapping("/v1/docdive") 
public class SimpleController {
	
	@Autowired
	private FileService fileService;
	
	@RequestMapping(value ="/test", method = RequestMethod.GET)
    @ResponseBody
    public Object test(HttpServletRequest request){
		String path = request.getServletContext().getRealPath("/");
		File file =  new File(path, "files");
		file.mkdirs();
		try {
			File ff = new File(file, "a.txt");
			ff.createNewFile();
			new PrintStream(ff).println("abcdefg");
			System.out.println("==="+ff.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
        return "http://"+request.getLocalAddr()+":"+request.getLocalPort()
          +"/files/a.txt";
    }
	
	@RequestMapping(value ="/convert/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object convert(HttpServletRequest request,
			@PathVariable("id") String documentId){
		fileService.sendMessage(documentId);
		return null;
	}
	
	@RequestMapping(value ="/convertTest", method = RequestMethod.GET)
	@ResponseBody
	public Object convertTest(){
		FileService.convertFileToPDF(new File("D:\\TDDownload\\培训通知.docx"));
		return null;
	}

}
