package com.icia.project.dao;

import org.apache.ibatis.annotations.Mapper;

import com.icia.project.dto.MemberDto;

@Mapper
public interface MemberDao {

	// 회원가입 메소드
	void InsertMember(MemberDto mDto);

}
