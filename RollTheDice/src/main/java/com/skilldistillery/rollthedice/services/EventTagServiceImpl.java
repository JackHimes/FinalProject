package com.skilldistillery.rollthedice.services;

import java.util.List;

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

}
