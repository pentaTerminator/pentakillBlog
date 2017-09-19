package org.pentakill.blog.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.pentakill.blog.service.OracleDemo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;

@Controller("demo")
public class Demo {

	@Resource(name = "oracleDemo")
	private OracleDemo oracleDemo;

	@RequestMapping("/")
	public String resource() {
		return "";
	}

	@ResponseBody
	@RequestMapping("/getJson")
	public String getJson(int rows, int page) {
		return JSON.toJSONString(oracleDemo.page(rows, page));
	}

	@RequestMapping(value = "/upload/batch", method = RequestMethod.POST)
	public @ResponseBody String batchUpload(HttpServletRequest request) {
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
		MultipartFile file = null;
		BufferedOutputStream stream = null;
		for (int i = 0; i < files.size(); ++i) {
			file = files.get(i);
			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
					File file1 = new File(file.getOriginalFilename());
					System.out.println(file.getName());
					stream = new BufferedOutputStream(new FileOutputStream(file1));
					stream.write(bytes);
					stream.close();
				} catch (Exception e) {
					stream = null;
					return "You failed to upload " + i + " => " + e.getMessage();
				}
			} else {
				return "You failed to upload " + i + " because the file was empty.";
			}
		}
		return "upload successful";
	}

	@ResponseBody
	@RequestMapping("/getRatio")
	public String getRatio(int rows, int page) {
		return JSON.toJSONString(oracleDemo.page(rows, page));
	}

	@ResponseBody
	@RequestMapping("/getUserRadio")
	public String getUserRadio() {
		return JSON.toJSONString(oracleDemo.getUserRadio()).toLowerCase();
	}
}
