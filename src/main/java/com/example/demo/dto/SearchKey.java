package com.example.demo.dto;

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
