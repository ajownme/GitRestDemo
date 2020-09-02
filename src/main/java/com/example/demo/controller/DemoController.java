package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.SearchKey;
import com.example.demo.dto.SearchDTO;

import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class DemoController {
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

	@GetMapping("/")
	public String start() {
		return "index";
	}

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}

	@GetMapping("/all-committer")
	public String allCommitter(@RequestParam(name = "repo", required = true) String repoString,Model model) {
		model.addAttribute("repo", repoString);
		return "all-committer";
	}
	
	@GetMapping("/latest-committer")
	public String latestCommitter(@RequestParam(name = "repo", required = true) String repoString,Model model) {
		model.addAttribute("repo", repoString);
		return "latest-committer";
	}

	@GetMapping("/latest-timeline")
	public String latestTimeline(@RequestParam(name = "repo", required = true) String repoString,Model model) {
		model.addAttribute("repo", repoString);
		return "timeline-commit";
	}
	
}
