package com.potato.membership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.potato.core.entity.Membership;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Integer> {

	Membership findByMembershipSeq(int membershipSeq);
	void deleteByMembershipSeq(int membershipSeq);

}
