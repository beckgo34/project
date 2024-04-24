package com.icia.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.ui.Model;

import com.icia.project.dto.RequestsBoardDto;
import com.icia.project.dto.SearchDto;

import jakarta.servlet.http.HttpSession;

@Mapper
public interface RequestsBoardDao {

	List<RequestsBoardDto> selectreBoard(SearchDto sDto);


}
