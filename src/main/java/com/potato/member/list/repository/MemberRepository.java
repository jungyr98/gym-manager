package com.potato.member.list.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.potato.core.entity.WorkplaceMember;

@Repository
public interface MemberRepository extends JpaRepository<WorkplaceMember, Integer> {

	@Query("SELECT wm, ph FROM WorkplaceMember wm LEFT JOIN FETCH wm.paymentsHistory ph WHERE wm.memberSeq = :memberSeq ORDER BY ph.regDate DESC LIMIT 1")
	WorkplaceMember findByMemberSeq(@Param("memberSeq") int memberSeq);

}
