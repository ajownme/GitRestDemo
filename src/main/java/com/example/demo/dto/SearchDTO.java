package com.example.demo.dto;

import lombok.Data;
/**
 * This DTO class is to hold the search filters and qualifiers upon searching a github repository
 * 
 * @author Arjay C Jesalva
 * @since 2020-09-02
 */ 
@Data
public class SearchDTO {
	private SearchKey searchBy;

	private String searchText;
	
	private int page;
}
