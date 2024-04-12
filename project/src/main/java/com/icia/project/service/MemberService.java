package com.icia.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.icia.project.dao.MemberDao;
import com.icia.project.dto.MemberDto;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService {

	@Autowired
	private MemberDao mDao;
	// 비밀번호 암호화
	private BCryptPasswordEncoder pEncoder = new BCryptPasswordEncoder();
	
	public String memberJoin(MemberDto mDto, 
							 RedirectAttributes rttr) {
		log.info("memberJoin()");
		
		String view = null;
		String msg = null;
		
		String encPwd = pEncoder.encode(mDto.getM_password());
		mDto.setM_password(encPwd);
		
		try {
			mDao.InsertMember(mDto);
			view = "redirect:/loginForm";
			msg = "가입에 성공하셧습니다.";
		}catch (Exception e) {
			e.printStackTrace();
			view = "redirect:/joinForm";
			msg = "가입에 실패하셧습니다.";
			
		}
		
		rttr.addFlashAttribute("msg", msg);
		
		return view;
	}
	
	@GetMapping("idCheck")
	public String idCheck(String mid) {
		log.info("idCheck()");
		
		String result = null;
		
		int memCnt = mDao.selectId(mid);
		
		if(memCnt == 0) {
			result = "ok";
		}else {
			result = "fail";
		}
		
		return result;
	}
	
	public String loginProc(MemberDto mDto, 
							HttpSession session, 
							RedirectAttributes rttr) {
		log.info("loginProc()");
		
		String view = null;
		String msg = null;
		
		String encPwd = mDao.selectPassword(mDto.getM_id());
		
		if(encPwd != null) {
		if(pEncoder.matches(mDto.getM_password(), encPwd)) {
			mDto = mDao.selectMember(mDto.getM_id());
			session.setAttribute("mDto", mDto);
			log.info("session: {}", session);
			log.info("mDto: {}",mDto);
			view = "redirect:/";
			msg = "로그인 성공";
		}else {
			msg = "비밀번호가 틀립니다";
			view = "redirect:loginForm";
		}
		}else {
			msg = "존재하지 않는 아이디입니다";
			view = "redirect:loginForm";
		}
		
		rttr.addFlashAttribute("msg", msg);
		log.info("msg: {}", msg);
		
		return view;
	}

	public String logout(HttpSession session, 
						 RedirectAttributes rttr) {
		log.info("logout()");
		
		rttr.addAttribute("msg", "로그아웃되었습니다");
		session.invalidate();
		return "redirect:/";
	}
} // class end
