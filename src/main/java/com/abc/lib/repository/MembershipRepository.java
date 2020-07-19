package com.abc.lib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abc.lib.entity.Member;

@Repository
public interface MembershipRepository extends JpaRepository<Member, String>{

	List<Member> findByMemberFirstNameContaining(String memberFirstName);


//	String findById(String memberId);
}
