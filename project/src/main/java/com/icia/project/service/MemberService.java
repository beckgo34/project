package com.icia.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.icia.project.dao.MemberDao;
import com.icia.project.dto.MemberDto;

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
			view = "redirect:/";
			msg = "가입성공";
		}catch (Exception e) {
			e.printStackTrace();
			view = "redirect:/joinForm";
			msg = "가입실패";
			
		}
		
		rttr.addFlashAttribute("msg", msg);
		
		return view;
	}
	
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
}
