package com.example.demo.dto;


/**
 * This enum class is part of the @see com.example.demo.dto.SearchDTO upon searching a github repository
 * 
 * @author Arjay C Jesalva
 * @since 2020-09-02
 */ 
public enum SearchKey {
	BY_OWNER("user"), IN_REPO_NAME("in:name"), BY_OWNER_SLASH_REPO_NAME("repo");

	private final String code;

	SearchKey(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}
}
