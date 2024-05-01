package com.icia.project.dto;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestsBoardDto {
	private Long r_num;
	private String r_title;
	private String r_contents;
	private int r_progress;
	private Timestamp r_date;
	private String mr_name;
	private String m_id;
}
