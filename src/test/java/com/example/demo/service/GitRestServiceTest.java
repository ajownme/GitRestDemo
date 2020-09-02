package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.dto.CommitterDTO;
import com.example.demo.dto.RepoBaseInfoDTO;

import com.example.demo.dto.SearchKey;
import com.example.demo.dto.SearchDTO;
import com.example.demo.dto.UserInfoDTO;

public class GitRestServiceTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(GitRestServiceTest.class);

	@Test
	public void testSearchByProjOwner() {
		SearchDTO searchModel = new SearchDTO();
		searchModel.setSearchBy(SearchKey.BY_OWNER);
		searchModel.setSearchText("mojombo");
		searchModel.setPage(1);

		GitRestService gitRestService = new GitRestService();

		RepoBaseInfoDTO repoBaseInfoDTO = gitRestService.search(searchModel);

		LOGGER.info("Test here: {}", repoBaseInfoDTO.toString());
		assertEquals(true, repoBaseInfoDTO.getItems().get(0).getFullName().contains("mojombo"));

	}

	@Test
	public void testSearchByProjName() {
		SearchDTO searchModel = new SearchDTO();
		searchModel.setSearchBy(SearchKey.IN_REPO_NAME);
		searchModel.setSearchText("chronic");
		searchModel.setPage(1);

		GitRestService gitRestService = new GitRestService();

		RepoBaseInfoDTO repoBaseInfoDTO = gitRestService.search(searchModel);

		LOGGER.info("Test here: {}", repoBaseInfoDTO.toString());
		assertEquals(true, repoBaseInfoDTO.getItems().get(0).getFullName().contains("chronic"));

	}

	@Test
	public void testSearchByProjOwnerAndProjName() {
		SearchDTO searchModel = new SearchDTO();
		searchModel.setSearchBy(SearchKey.BY_OWNER_SLASH_REPO_NAME);
		searchModel.setSearchText("mojombo/chronic");
		searchModel.setPage(1);

		GitRestService gitRestService = new GitRestService();

		RepoBaseInfoDTO repoBaseInfoDTO = gitRestService.search(searchModel);

		LOGGER.info("Test here: {}", repoBaseInfoDTO.toString());
		assertEquals("mojombo/chronic", repoBaseInfoDTO.getItems().get(0).getFullName());

	}

	@Test
	public void testGetAllCommitter() {
		GitRestService gitRestService = new GitRestService();

		List<CommitterDTO> committerDTOList = new ArrayList<CommitterDTO>();
		committerDTOList = gitRestService.getAllCommitterList("mojombo/chronic", 1);
		LOGGER.info("test result: {}", committerDTOList);
		LOGGER.info("test result size: {}", committerDTOList.size());
		assertTrue(committerDTOList.size() > 0);

	}

	
	@Test
	public void testGetRecent100Committer() {
		GitRestService gitRestService = new GitRestService();

		List<CommitterDTO> committerDTOList = new ArrayList<CommitterDTO>();
		committerDTOList = gitRestService.getAllCommitterList("mojombo/chronic", 1);
		LOGGER.info("test result: {}", committerDTOList);
		LOGGER.info("test result size: {}", committerDTOList.size());
		assertTrue(committerDTOList.size() > 0);

		Map<String, Long> map = committerDTOList.stream().map(m -> m.getCommit().getCommitter())
				.collect(Collectors.groupingBy(UserInfoDTO::getName, Collectors.counting()));

		Map<String, Long> resultMap = map.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> {
					throw new IllegalStateException();
				}, LinkedHashMap::new));
		LOGGER.info("resultMap value: {}", resultMap);

	}

	@Test
	public void testGetRecentCommit() {
		GitRestService gitRestService = new GitRestService();

		List<CommitterDTO> committerDTOList = new ArrayList<CommitterDTO>();
		committerDTOList = gitRestService.getAllCommitterList("mojombo/chronic", 1);
		assertTrue(committerDTOList.size() > 0);

	}
}
