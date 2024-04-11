package com.icia.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icia.project.service.MemberService;
import com.icia.project.service.RequestsBoardService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProjectRestController {
	
	@Autowired
	private MemberService mser;

	@GetMapping("idCheck")
	public String idCheck(@RequestParam("mid") String mid) {
		log.info("idCheck()");
		
		String res = mser.idCheck(mid);
		
		return res;
	}
}
