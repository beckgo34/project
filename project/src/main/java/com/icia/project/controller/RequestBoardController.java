package com.icia.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.icia.project.dto.SearchDto;
import com.icia.project.service.RequestsBoardService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class RequestBoardController {

	@Autowired
	private RequestsBoardService bSer;
	
	@GetMapping("requestsBoard")
	public String requestBoardList(SearchDto sDto,
								   HttpSession session,
								   Model model) {
		log.info("requestsBoard()");
		
		String view = bSer.getRboardList(sDto,session, model);
		
		return view;
	}
}
