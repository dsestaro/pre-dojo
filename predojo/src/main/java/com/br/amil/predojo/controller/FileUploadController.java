package com.br.amil.predojo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

	@RequestMapping(value = "/isAlive", method = RequestMethod.GET)
	public @ResponseBody String isAlive() {
		return "OK";
	}
	
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public @ResponseBody String handleFileUpload(@RequestParam("file") String file) {
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("teste")));
				stream.write(bytes);
				stream.close();
				return "OK";
			} catch (Exception e) {
				return "Fail";
			}
		} else {
			return "Empty File!";
		}
	}
}
