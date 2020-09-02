package com.example.demo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.CommitterDTO;
import com.example.demo.dto.RepoBaseInfoDTO;

import com.example.demo.dto.SearchKey;
import com.example.demo.dto.SearchDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GitRestService {
	private static final Logger LOGGER = LoggerFactory.getLogger(GitRestService.class);

	private String requestGitRest(String strUrl) {

		String output = null;
		HttpURLConnection conn = null ;
		try {
			URL url = new URL(strUrl);
			LOGGER.info("URL here: {}", url);

			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/vnd.github.cloak-preview");
			if (conn.getResponseCode() != 200) {
				throw new IOException("Failed : HTTP Error code : " + conn.getResponseCode());
			}

			InputStreamReader in = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(in);
			output = br.readLine();

			conn.disconnect();
		} catch (IOException e) {
			LOGGER.error("requestGitRest() exception: ", e);
		}finally {
			
		}

		return output;
	}

	public List<CommitterDTO> getAllCommitterList(String repository, int page) {
		List<CommitterDTO> committerDTOList = new ArrayList<>();
		try {
			String output = requestGitRest(
					"https://api.github.com/repos/" + repository + "/commits?page=" + page + "&per_page=50");
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			committerDTOList = mapper.readValue(output, new TypeReference<List<CommitterDTO>>() {
			});

		} catch (IOException e) {
			LOGGER.error("getAllCommitterList() execption: ", e);
		}
		return committerDTOList;
	}
	
	
	
	public List<CommitterDTO> getLatestCommitterList(String repository,int dataRow) {
		List<CommitterDTO> committerDTOList = new ArrayList<>();
		try {
			String output = requestGitRest(
					"https://api.github.com/repos/" + repository + "/commits?page=1&per_page="+ dataRow);
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			committerDTOList = mapper.readValue(output, new TypeReference<List<CommitterDTO>>() {
			});

		} catch (IOException e) {
			LOGGER.error("getLatestCommitterList() execption: ", e);
		}
		return committerDTOList;
	}

	public RepoBaseInfoDTO search(SearchDTO searchModel) {

		String filter = "";
		if (searchModel.getSearchBy().equals(SearchKey.IN_REPO_NAME)) {
			filter = searchModel.getSearchText() + "+" + searchModel.getSearchBy().getCode();
		} else {
			filter = searchModel.getSearchBy().getCode() + ":" + searchModel.getSearchText();
		}

		RepoBaseInfoDTO repoBaseInfoDTO = new RepoBaseInfoDTO();
		String output = requestGitRest("https://api.github.com/search/repositories?q=" + filter + "&page="
				+ searchModel.getPage() + "&per_page=50");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		try {
			repoBaseInfoDTO = mapper.readValue(output, RepoBaseInfoDTO.class);
		} catch (IOException e) {
			LOGGER.error("search() execption: ", e);
		}

		LOGGER.info("repoBaseInfoDTO: {}", repoBaseInfoDTO);
		LOGGER.info("repoBaseInfoDTO size: {}", repoBaseInfoDTO.getItems().size());

		return repoBaseInfoDTO;
	}
}
