package org.pentakill.blog.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller("demo")
public class Demo {

	@RequestMapping("/")
	public String resource() {
		return "index.html";
	}

	@RequestMapping(value = "/upload/batch", method = RequestMethod.POST)
	public @ResponseBody String batchUpload(HttpServletRequest request) {
		List<MultipartFile> files = ((MultipartHttpServletRequest) request)
				.getFiles("file");
		MultipartFile file = null;
		BufferedOutputStream stream = null;
		for (int i = 0; i < files.size(); ++i) {
			file = files.get(i);
			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();
					File file1 = new File(file.getOriginalFilename());
					System.out.println(file.getName());
					stream = new BufferedOutputStream(new FileOutputStream(
							file1));
					stream.write(bytes);
					stream.close();
				} catch (Exception e) {
					stream = null;
					return "You failed to upload " + i + " => "
							+ e.getMessage();
				}
			} else {
				return "You failed to upload " + i
						+ " because the file was empty.";
			}
		}
		return "upload successful";
	}
}
