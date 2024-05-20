package com.icia.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.icia.project.dto.RequestsBoardDto;
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
	
	@GetMapping("writeForm")
	public String writeForm() {
		log.info("writeForm()");
		
		return "writeForm";
	}
	
	@PostMapping("writeProc")
	public String writeProc(@RequestParam("files") List<MultipartFile> files,
							RequestsBoardDto rebDto,
							HttpSession session,
							RedirectAttributes rttr) {
		log.info("writeProc()");
		
		String view = bSer.requestsBoardWrite(files, rebDto, session, rttr);
		
		return view;
	}
	
	@GetMapping("requestsBoardDetail")
	public String requestsBoardDetail(@RequestParam("r_num") int r_num,
									  Model model) {
		log.info("requestsBoardDetail()");
		
		String view = bSer.getRequestsBoardDetail(r_num, model);
		
		return view;
	}
	
	
} // class end
