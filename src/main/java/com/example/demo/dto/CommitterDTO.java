package com.example.demo.dto;


import lombok.Data;

/**
 * This DTO root class is use to hold the committer information of a certain github repository
 * 
 * @author Arjay C Jesalva
 * @since 2020-09-02
 */ 

@Data
public class CommitterDTO {

	private CommitDTO commit;

}
