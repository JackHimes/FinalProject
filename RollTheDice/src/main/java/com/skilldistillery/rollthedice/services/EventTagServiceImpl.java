package com.skilldistillery.rollthedice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.rollthedice.entities.EventTag;
import com.skilldistillery.rollthedice.repositories.EventTagRepository;

@Service
public class EventTagServiceImpl implements EventTagService {
	
	@Autowired
	EventTagRepository eventTagRepo;
	
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
	public EventTag createEventTag(EventTag eventTag) {
		return eventTagRepo.saveAndFlush(eventTag);
	}
	
	@Override
	public EventTag updateEventTag(EventTag eventTag, int id) {
		Optional<EventTag> updatedEventTag = eventTagRepo.findById(id);
		if (updatedEventTag.isPresent()) {
			eventTag.setId(id);
			return eventTagRepo.saveAndFlush(eventTag);
		}
		return null;
	}
	
	@Override
	public boolean deleteEventTag(int id) {
		boolean deleted = false;
		Optional<EventTag> e = eventTagRepo.findById(id);
		if (e.isPresent()) {
			EventTag eventTag = e.get();
			eventTagRepo.delete(eventTag);
			deleted = true;
		}
		return deleted;
	}
	

}
