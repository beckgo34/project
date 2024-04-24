package com.icia.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.icia.project.dao.RequestsBoardDao;
import com.icia.project.dto.RequestsBoardDto;
import com.icia.project.dto.SearchDto;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RequestsBoardService {
	
	@Autowired
	private RequestsBoardDao rbDao;

	private int lcnt = 10;	
	
	public String getRboardList(SearchDto sDto, 
							    HttpSession session, 
							    Model model) {
		log.info("getRboardList()");
		
		String view = "requestsBoard";
		
		int num = sDto.getPageNum();
		
		if(sDto.getListCnt() == 0) {
			sDto.setListCnt(lcnt);
		}
		
		sDto.setPageNum((num - 1) * sDto.getListCnt());
		List<RequestsBoardDto> reLists = rbDao.selectreBoard(sDto);
		model.addAttribute("reLists", reLists);
		log.info("reLists : {}", reLists);
		
		return view;
	}
	

	
} // class end
