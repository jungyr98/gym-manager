package com.potato.visit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.potato.core.entity.VisitHistory;

@Repository
public interface VisitRepository extends JpaRepository<VisitHistory, Integer> {

	List<VisitHistory> findByMemberSeqOrderByRegDateDesc(int memberSeq);

}
