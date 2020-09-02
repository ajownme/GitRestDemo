package com.example.demo.controller;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CommitterDTO;
import com.example.demo.dto.RepoBaseInfoDTO;
import com.example.demo.dto.SearchDTO;
import com.example.demo.dto.UserInfoDTO;
import com.example.demo.service.GitRestService;

/**
 * This controller class defines the REST API endpoints that are expose to be consume
 * 
 * @author Arjay C Jesalva
 * @since 2020-09-02
 */

@RestController
public class APIController {
	private static final Logger LOGGER = LoggerFactory.getLogger(APIController.class);
	@Autowired
	GitRestService gitRestService;

	@PostMapping("/search")
	public ResponseEntity<RepoBaseInfoDTO> search(@RequestBody SearchDTO searchModel) {
		LOGGER.info("Test here: {}", searchModel);
		RepoBaseInfoDTO result = gitRestService.search(searchModel);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/get-all-committer")
	public ResponseEntity<List<CommitterDTO>> getAllCommitter(@RequestParam String repository, @RequestParam int page) {

		List<CommitterDTO> result = gitRestService.getAllCommitterList(repository, page);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/get-latest-committer")
	public ResponseEntity<Map<String, Long>> getLatestCommitter(@RequestParam String repository) {
		LOGGER.info("getLatestCommitter here");
		LOGGER.info("Controller Test here: {}", repository.toString());

		List<CommitterDTO> committerDTOList = gitRestService.getLatestCommitterList(repository, 100);

		Map<String, Long> map = committerDTOList.stream().map(m -> m.getCommit().getCommitter())
				.collect(Collectors.groupingBy(UserInfoDTO::getName, Collectors.counting()));

		Map<String, Long> resultMap = map.entrySet().stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2) -> {
					throw new IllegalStateException();
				}, LinkedHashMap::new));
		LOGGER.info("resultMap value: {}", resultMap);

		return new ResponseEntity<>(resultMap, HttpStatus.OK);
	}

	@PostMapping("/get-latest-timeline")
	public ResponseEntity<List<CommitterDTO>> getLatestTimeline(@RequestParam String repository) {
		LOGGER.info("getLatestCommitter here");
		LOGGER.info("Controller Test here: {}", repository.toString());

		List<CommitterDTO> result = gitRestService.getLatestCommitterList(repository, 100);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
