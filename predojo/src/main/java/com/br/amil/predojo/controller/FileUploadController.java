package com.br.amil.predojo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.br.amil.predojo.configs.FileConfig;

@Controller
public class FileUploadController {

	@RequestMapping(value = "/isAlive", method = RequestMethod.GET)
	public @ResponseBody String isAlive() {
		return "OK";
	}
	
	/**
	 * Metodo criado para a realizacao de testes devido a limitacoes encontradas no Rest Assured
	 * 
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody String handleUpload(@RequestParam("file") String file) {
		if (!file.isEmpty()) {
			return "OK";
		} else {
			return "Empty File!";
		}
	}
	
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file) {
		try {
			File tempFile = File.createTempFile(file.getOriginalFilename(), FileConfig.PREFIX);
			
			file.transferTo(tempFile);
			
			return handleUpload(Files.readAllBytes(tempFile.toPath()).toString());
		} catch (IllegalStateException | IOException e) {
			return "Ocorreu um erro ao processar o arquivo!";
		}
	}
}
