package com.icia.project.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class successBoardDto {
	private Long s_num;
	private String s_title;
	private String s_contents;
	private String ms_name;
	private int s_progress;
	private Timestamp s_date;
	private Long rs_num;
	private String ms_id;
}
