package com.potato.member.pay.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.potato.core.entity.PaymentsHistory;

@Repository
public interface PaymentsHistoryRepository extends JpaRepository<PaymentsHistory, Integer> {

	@Query("SELECT p FROM PaymentsHistory p JOIN FETCH p.membership WHERE p.phSeq = :phSeq")
	PaymentsHistory findByPhSeq(@Param("phSeq") int phSeq);

	List<PaymentsHistory> findByMemberSeqOrderByRegDateDesc(int memberSeq);

}
