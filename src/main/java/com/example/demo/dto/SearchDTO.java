package com.example.demo.dto;

import lombok.Data;

@Data
public class SearchDTO {
	private SearchKey searchBy;

	private String searchText;
	
	private int page;
}
