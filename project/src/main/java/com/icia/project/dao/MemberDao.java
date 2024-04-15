package com.icia.project.dao;

import org.apache.ibatis.annotations.Mapper;

import com.icia.project.dto.MemberDto;

@Mapper
public interface MemberDao {

	// 회원가입 메소드
	void InsertMember(MemberDto mDto);
	// 아이디 중복
	int selectId(String mid);
	
	String selectPassword(String m_id);
	// 로그인
	MemberDto selectMember(String m_id);

}
