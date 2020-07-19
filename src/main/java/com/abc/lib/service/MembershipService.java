package com.abc.lib.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.abc.lib.entity.Member;

public interface MembershipService {

	
	ResponseEntity<String> addMember(Member member);
	
	ResponseEntity<Member> fetchMember(String memberId);
	
	ResponseEntity<List<Member>> fetchMembers();
	
    ResponseEntity<List<Member>> findByMemberFirstNameContaining(String memberFirstName);
    
    ResponseEntity<String> removeMember(String memberId);
    
    ResponseEntity<Member> updateMember(Member member);
}
