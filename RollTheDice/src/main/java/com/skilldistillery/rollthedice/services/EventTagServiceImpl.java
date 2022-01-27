package com.skilldistillery.rollthedice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.rollthedice.entities.EventTag;
import com.skilldistillery.rollthedice.entities.User;
import com.skilldistillery.rollthedice.repositories.EventTagRepository;
import com.skilldistillery.rollthedice.repositories.UserRepository;

@Service
public class EventTagServiceImpl implements EventTagService {
	
	@Autowired
	EventTagRepository eventTagRepo;
	@Autowired 
	UserRepository userRepo;
	
	@Override
	public List<EventTag> getAllEventTags() {
		return eventTagRepo.findAll();
	}
	
	@Override
	public EventTag getEventTagById(int id) {
		Optional<EventTag> eventTag = eventTagRepo.findById(id);
		if (eventTag.isPresent()) {
			return eventTag.get();
		}
		return null;
	}
	
	@Override
	public EventTag createEventTag(EventTag eventTag, String username) {
		User user = userRepo.findByUsername(username);
		eventTag.setUser(user);
		return eventTagRepo.saveAndFlush(eventTag);
	}
	
	@Override
	public EventTag updateEventTag(EventTag eventTag, int id, String username) {
		User loggedInUser = userRepo.findByUsername(username);
		Optional<EventTag> updatedEventTag = eventTagRepo.findById(id);
		if (updatedEventTag.isPresent() && ((loggedInUser.getId() == eventTag.getUser().getId()) || loggedInUser.getRole().equals("ROLE_ADMIN"))) {
			eventTag.setId(id);
			return eventTagRepo.saveAndFlush(eventTag);
		}
		return null;
	}
	
	@Override
	public boolean deleteEventTag(int id, String username) {
		boolean deleted = false;
		Optional<EventTag> e = eventTagRepo.findById(id);
		EventTag eventTag = e.get();
		User loggedInUser = userRepo.findByUsername(username);
		if (e.isPresent() && ((loggedInUser.getId() == eventTag.getUser().getId()) || loggedInUser.getRole().equals("ROLE_ADMIN"))) {
			eventTagRepo.delete(eventTag);
			deleted = true;
		}
		return deleted;
	}
	

}
