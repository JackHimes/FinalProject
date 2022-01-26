package com.skilldistillery.rollthedice.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("eventtags")
	public List<EventTag> index(HttpServletResponse res) {
		List<EventTag> eventTags = eventTagSvc.getAllEventTags();
		if (eventTags.size() == 0) {
			res.setStatus(404);
		}
		return eventTags;
	}
	
	@GetMapping("eventtags/{id}")
	public EventTag singleEventTag(@PathVariable int id, HttpServletResponse res) {
		EventTag eventTag = eventTagSvc.getEventTagById(id);
		if (eventTag == null) {
			res.setStatus(404);
			return null;
		}
		return eventTag;
	}
	
	@PostMapping("eventtags")
	public EventTag createEventTag(@RequestBody EventTag eventTag, HttpServletRequest req, HttpServletResponse res) {
		EventTag newEventTag = null;
		try {
			newEventTag = eventTagSvc.createEventTag(eventTag);
			res.setStatus(201);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(newEventTag.getId());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("INVALED EVENT TAG SENT");
			res.setStatus(400);
		}
		return newEventTag;
	}
	
	@PutMapping("eventtags/{id}")
	public EventTag updateEventTag(@RequestBody EventTag eventTag, @PathVariable int id, HttpServletResponse res	 ) {
		EventTag newEventTag = null;
		try {
			newEventTag = eventTagSvc.updateEventTag(eventTag, id);
			if (newEventTag == null) {
				res.setStatus(404);
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
			System.err.println("INVALID EVENT TAG SENT");
		}
		return newEventTag;
	}
	
	@DeleteMapping("eventtags/{id}")
	public void deleteEventTag(@PathVariable int id, HttpServletResponse res) {
		boolean deleted = eventTagSvc.deleteEventTag(id);
		if (deleted) {
			res.setStatus(HttpStatus.NO_CONTENT.value());
		} else {
			res.setStatus(404);
		}
	}
	
}
