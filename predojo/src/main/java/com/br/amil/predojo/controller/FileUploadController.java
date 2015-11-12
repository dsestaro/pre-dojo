package com.br.amil.predojo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileUploadController {

	@RequestMapping(value = "/isAlive", method = RequestMethod.GET)
	public @ResponseBody String isAlive() {
		return "OK";
	}
	
	
}
