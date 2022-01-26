package com.skilldistillery.rollthedice.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.rollthedice.entities.EventTag;
import com.skilldistillery.rollthedice.services.EventTagService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4300"})
public class EventTagController {

	
	@Autowired
	private EventTagService eventTagSvc;
	
	@GetMapping("eventTag")
	public List<EventTag> index(HttpServletResponse res) {
		List<EventTag> eventTags = eventTagSvc.getAllEventTags();
		if (eventTags.size() == 0) {
			res.setStatus(404);
		}
		return eventTags;
	}
}
