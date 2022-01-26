package com.skilldistillery.rollthedice.services;

import java.util.List;

import com.skilldistillery.rollthedice.entities.EventTag;


public interface EventTagService {

	List<EventTag> getAllEventTags();

	EventTag getEventTagById(int id);

	EventTag createEventTag(EventTag eventTag);

	EventTag updateEventTag(EventTag eventTag, int id);

	boolean deleteEventTag(int id);

}
