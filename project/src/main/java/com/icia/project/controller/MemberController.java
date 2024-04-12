package com.icia.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.icia.project.dto.MemberDto;
import com.icia.project.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemberController {

	@Autowired
	private MemberService mser;
	
	@GetMapping("loginForm")
	public String loginForm() {
		log.info("loginForm()");
		return "loginForm";
	}
	
	@PostMapping("loginProc")
	public String loginProc(MemberDto mDto,
							HttpSession session,
							RedirectAttributes rttr) {
		log.info("loginProc()");
		
		return mser.loginProc(mDto, session, rttr);
	}
	
	@GetMapping("joinForm")
	public String joinForm() {
		log.info("joinForm()");
		return "joinForm";
	}
	
	@PostMapping("joinProc")
	public String joinProc(MemberDto mDto,
						   RedirectAttributes rttr) {
		log.info("joinProc()");
		
		String view = mser.memberJoin(mDto, rttr);
		
		return view;
	}
	
	
	
	
	
	
}
