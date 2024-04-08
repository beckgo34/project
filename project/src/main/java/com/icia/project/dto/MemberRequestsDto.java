package com.icia.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequestsDto {
	private String mr_id;
	private String mr_password;
	private String mr_name;
	private String mr_department;
	private String mr_email;
}
