package com.potato.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.potato.core.entity.Calendar;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Integer> {

	Calendar findByClSeq(int clSeq);
	void deleteByClSeq(int clSeq);
}
