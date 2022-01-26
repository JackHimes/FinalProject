package com.skilldistillery.rollthedice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.rollthedice.entities.EventTag;

public interface EventTagRepository extends JpaRepository<EventTag, Integer>{

}
