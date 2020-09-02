package com.example.demo.dto;

import lombok.Data;

/**
 * This DTO class is part of the @see com.example.demo.dto.CommitterDTO
 *  is to hold the committer information of a certain github repository
 * 
 * @author Arjay C Jesalva
 * @since 2020-09-02
 */ 

@Data
public class CommitDTO {
	private UserInfoDTO committer;

}