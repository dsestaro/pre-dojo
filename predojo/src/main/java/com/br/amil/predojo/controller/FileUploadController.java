package com.br.amil.predojo.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.br.amil.predojo.configs.FileConfig;
import com.br.amil.predojo.service.FileUploadService;
import com.google.common.io.CharStreams;
import com.google.gson.Gson;

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
		FileUploadService fileUploadService = new FileUploadService();
		
		if (!file.isEmpty()) {
			String matches = new Gson().toJson(fileUploadService.processFile(file));
			
			return matches;
		} else {
			return "Empty File!";
		}
	}
	
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file) {
		try {
			String fileContent = CharStreams.toString(new InputStreamReader(file.getInputStream())); 
			
			return handleUpload(fileContent);
		} catch (IllegalStateException | IOException e) {
			return "Ocorreu um erro ao processar o arquivo!";
		}
	}
}
