package com.example.demo.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


/**
 * This DTO root class is use to hold the information of a github repository
 * 
 * @author Arjay C Jesalva
 * @since 2020-09-02
 */ 


@Data
public class RepoBaseInfoDTO {

	@JsonProperty("total_count")
	private Integer totalCount;

	@JsonProperty("items")
	private List<ItemDTO> items;

}
