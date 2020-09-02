package com.example.demo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RepoBaseInfoDTO {

	@JsonProperty("total_count")
	private Integer totalCount;

	@JsonProperty("items")
	private List<ItemDTO> items;

}
