package com.icia.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SuccessBoardControrller {

	@GetMapping("successBoard")
	public String successBoard() {
		
		return "successBoard";
	}
}
