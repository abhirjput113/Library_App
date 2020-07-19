package com.abc.lib.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abc.lib.entity.Member;
import com.abc.lib.service.MemberServiceImpl;

@RestController
@RequestMapping("/v1/member")
public class MemberController {

	private static final Logger log = LoggerFactory.getLogger(MemberController.class);

	
	@Autowired
	private MemberServiceImpl memberServiceImpl;
	
	@PostMapping("/reg")
	public ResponseEntity<String> addMember(@RequestBody Member member){
		
		return memberServiceImpl.addMember(member);
		
	  }
	
	@GetMapping("/fetch/{memberId}")
	public ResponseEntity<Member> fetchMember(@PathVariable String memberId){
		
		log.info("########################" + "Member Searched: " + memberId + "########################");

		return memberServiceImpl.fetchMember(memberId); 
	}
	
	@GetMapping("/fetch/all")
	public ResponseEntity<List<Member>> fetchMembers(){
		return memberServiceImpl.fetchMembers();
		
	}
	@GetMapping("/fetch/by/{memberFirstName}")
	public ResponseEntity<List<Member>> fetchMembersByFirstName(@PathVariable String memberFirstName){
		System.out.println("controller");
		return memberServiceImpl.findByMemberFirstNameContaining(memberFirstName);
		
	}
	
	@DeleteMapping("/delete/by/{memberId}")
	public ResponseEntity<String> removeMember(@PathVariable String memberId){
		return memberServiceImpl.removeMember(memberId);
	}
	
    @PutMapping("/update")
    public ResponseEntity<Member> updateMember(@RequestBody Member member){
    	log.info("Request arrived in controller");
    	return memberServiceImpl.updateMember(member);
    }
}
