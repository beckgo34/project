package com.icia.project.service;

import java.io.File;
import java.util.List;

import org.apache.catalina.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.icia.project.dao.RequestsBoardDao;
import com.icia.project.dto.RequestsBoardDto;
import com.icia.project.dto.RequestsBoardFileDto;
import com.icia.project.dto.SearchDto;
import com.icia.project.util.PagingUtil;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RequestsBoardService {

	@Autowired
	private RequestsBoardDao rbDao;
	@Autowired
	private PlatformTransactionManager manager;
	@Autowired
	private TransactionDefinition definition;

	private int lcnt = 10;

	public String getRboardList(SearchDto sDto, HttpSession session, Model model) {
		log.info("getRboardList()");

		String view = "requestsBoard";

		int num = sDto.getPageNum();

		if (sDto.getListCnt() == 0) {
			sDto.setListCnt(lcnt);
		}

		sDto.setPageNum((num - 1) * sDto.getListCnt());
		List<RequestsBoardDto> reLists = rbDao.selectreBoard(sDto);
		model.addAttribute("reLists", reLists);
		log.info("reLists : {}", reLists);

		return view;
	}

	public String getPaging(SearchDto sDto) {
		log.info("getpaging()");

		String pageHtml = null;

		int maxNum = rbDao.selectBoardCnt(sDto);

		int pageCnt = 3;

		String listName = "requestsBoard";

		if (sDto.getColname() != null) {
			listName += "colname" + sDto.getColname() + "&keyword=" + sDto.getKeyword() + "&";
		}

		PagingUtil Paging = new PagingUtil(maxNum, sDto.getPageNum(), sDto.getListCnt(), pageCnt, listName);

		pageHtml = Paging.makePaging();

		return pageHtml;
	}

	public String requestsBoardWrite(List<MultipartFile> files, RequestsBoardDto rebDto, HttpSession session,
			RedirectAttributes rttr) {
		log.info("requestsBoardWrite()");
		TransactionStatus status = manager.getTransaction(definition);

		String view = null;
		String msg = null;

		try {
			rbDao.insertRequests(rebDto);

			if (!files.get(0).isEmpty()) {
				FileUpload(files, session, rebDto.getR_num());
			}

			manager.commit(status);
			view = "redirect:/requestsBoard";
			msg = "작성완료";
		} catch (Exception e) {
			e.printStackTrace();
			view = "redirect:/writeForm";
			msg = "작성실패";
		}

		return view;

	}

	private void FileUpload(List<MultipartFile> files, HttpSession session, Long r_num) throws Exception {
		log.info("fileUpload()");

		String realPath = session.getServletContext().getRealPath("/");
		log.info("realPath : {}", realPath);

		realPath += "upload/"; 

		File folder = new File(realPath);
		if (folder.isDirectory() == false) {
			folder.mkdir(); 
		}
		
		for(MultipartFile mf : files) {
			// 파일명 추출
			String oriname = mf.getOriginalFilename();
			
			RequestsBoardFileDto bfd = new RequestsBoardFileDto();
			bfd.setBe_oriname(oriname);
			bfd.setBe_bnum(r_num);
			String sysname = System.currentTimeMillis()
					+ oriname.substring(oriname.lastIndexOf("."));
			// 확장자 : 파일을 구분하기 위한 식별 체계. (예. image.jpg)
			bfd.setBe_sysname(sysname);
			
			// 파일 저장
			File file = new File(realPath + sysname);
			mf.transferTo(file);
			
			rbDao.insertFile(bfd);
		}

	}

	public String getRequestsBoardDetail(int r_num, Model model) {
		log.info("getRequestsBoardDetail()");
		
		RequestsBoardDto rbDto = rbDao.selectRequestsBoard(r_num);
		model.addAttribute(rbDto);
		
		return "requestsBoardDetail";
	}

} // class end
