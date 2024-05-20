package com.icia.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.ui.Model;

import com.icia.project.dto.RequestsBoardDto;
import com.icia.project.dto.RequestsBoardFileDto;
import com.icia.project.dto.SearchDto;

import jakarta.servlet.http.HttpSession;

@Mapper
public interface RequestsBoardDao {

	List<RequestsBoardDto> selectreBoard(SearchDto sDto);

	int selectBoardCnt(SearchDto sDto);

	void insertRequests(RequestsBoardDto rebDto);
	// 게시글 불러오기
	RequestsBoardDto selectRequestsBoard(int r_num);
	
	void insertFile(RequestsBoardFileDto bfd);

	


}
	