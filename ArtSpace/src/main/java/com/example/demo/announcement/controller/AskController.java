package com.example.demo.announcement.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.announcement.dto.AskDto;
import com.example.demo.announcement.dto.NoticeDto;
import com.example.demo.announcement.service.AskService;
import com.example.demo.announcement.service.NoticeService;

@Controller
@RequestMapping("/ask")
public class AskController {

	@Autowired
	private AskService askService;
	
	@GetMapping("")
	public String showboardPage1(Model model) {
		List<AskDto> askList = askService.getAsk();
		model.addAttribute("askList", askList);
		return "html/announcement/ask";	
	}
	
	@GetMapping("/write")
	public String showWrite1() {
		return "html/announcement/ask_notice";
	}
	
	@PostMapping("/insert")
	public String insertUser1(@ModelAttribute AskDto askDTO) {
		askDTO.setCreate_date(LocalDate.now());
		askService.insert1(askDTO);
		return "html/announcement/ask_notice";
		
	}


}
