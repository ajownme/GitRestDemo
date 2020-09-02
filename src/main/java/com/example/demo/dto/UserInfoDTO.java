package com.example.demo.dto;


import lombok.Data;

/**
 * This DTO class is for holding the user(e.g. author,committer,owner,contributor and etc.) information of a certain github repository
 * 
 * @author Arjay C Jesalva
 * @since 2020-09-02
 */ 


@Data
public class UserInfoDTO {

	private String name;

	private String email;

	private String date;
}
