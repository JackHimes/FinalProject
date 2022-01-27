package com.skilldistillery.rollthedice.services;

import java.util.List;

import com.skilldistillery.rollthedice.entities.EventTag;


public interface EventTagService {

	List<EventTag> getAllEventTags();

	EventTag getEventTagById(int id);

	EventTag createEventTag(EventTag eventTag, String username);

	EventTag updateEventTag(EventTag eventTag, int id, String username);

	boolean deleteEventTag(int id, String username);

}
