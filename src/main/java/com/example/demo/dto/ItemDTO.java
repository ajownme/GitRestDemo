package com.example.demo.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ItemDTO {

	private String id;

	@JsonProperty("full_name")
	private String fullName;

	private String name;

	@JsonProperty("html_url")
	private String htmlUrl;
}
