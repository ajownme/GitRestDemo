package com.example.demo.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


/**
 * This DTO class is part of the @see com.example.demo.dto.RepoBaseInfoDTO
 *  is to hold the item information of a github repository
 * 
 * @author Arjay C Jesalva
 * @since 2020-09-02
 */ 

@Data
public class ItemDTO {

	private String id;

	@JsonProperty("full_name")
	private String fullName;

	private String name;

	@JsonProperty("html_url")
	private String htmlUrl;
}
