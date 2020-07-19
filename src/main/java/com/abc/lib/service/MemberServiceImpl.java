package com.abc.lib.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.abc.lib.constants.Constant;
import com.abc.lib.constants.ErrorConstant;
import com.abc.lib.entity.Member;
import com.abc.lib.exceptions.MemberNotFoundException;
import com.abc.lib.repository.MembershipRepository;

@Service
public class MemberServiceImpl implements MembershipService {

	@Autowired
	MembershipRepository membershipRepository;

	@Override
	public ResponseEntity<String> addMember(Member member) {
		membershipRepository.save(member);

		return new ResponseEntity<String>(Constant.MEMBER_ADDED_SUCCESS_MESSAGE + member.getMemberFirstName(),
				HttpStatus.CREATED);

	}

	@Override
	public ResponseEntity<Member> fetchMember(String memberId) {
	
	Optional<Member> optionalMember=membershipRepository.findById(memberId);
		
	if(optionalMember.isPresent()) {
		
		Member member=optionalMember.get();
		
		return new ResponseEntity<Member>(member,HttpStatus.OK);
	}
	throw new MemberNotFoundException(ErrorConstant.MEMBER_NOT_FOUND_EXCEPTION_MESSAGE + memberId);
}

	@Override  
	public ResponseEntity<List<Member>> fetchMembers() {
		List<Member> listOfMember=membershipRepository.findAll();
		
		if(!listOfMember.isEmpty()) {
		return new ResponseEntity<List<Member>>(listOfMember,HttpStatus.OK);
	}
		throw new MemberNotFoundException(ErrorConstant.MEMBER_NOT_FOUND_EXCEPTION_MESSAGE + listOfMember);

	}
	

	@Override
	public ResponseEntity<List<Member>> findByMemberFirstNameContaining(String memberFirstName) {
		 List<Member> membersList=membershipRepository.findByMemberFirstNameContaining(memberFirstName);
		 if(!membersList.isEmpty()) {
			 return new ResponseEntity<List<Member>>(membersList,HttpStatus.OK);
		 }
		throw new MemberNotFoundException(ErrorConstant.MEMBER_NOT_FOUND_EXCEPTION_MESSAGE + memberFirstName);
	}

	@Override
	public ResponseEntity<String> removeMember(String memberId) {
	  Optional<Member> optionalMemberId=membershipRepository.findById(memberId);
	  
	  if(optionalMemberId.isPresent()) {
		  membershipRepository.deleteById(memberId);
		  
		  return new ResponseEntity<String>(Constant.MEMBER_DELETED_SUCCESS_MESSAGE + memberId, HttpStatus.OK);
	  }
			  
	    throw new MemberNotFoundException(ErrorConstant.MEMBER_NOT_FOUND_EXCEPTION_MESSAGE + memberId);
	}

	@Override
	public ResponseEntity<Member> updateMember(Member member) {
		Optional<Member> optional=membershipRepository.findById(member.getMemberId());
		if(optional.isPresent()) {
			membershipRepository.save(member);
			
			return new ResponseEntity<Member>(member,HttpStatus.OK);
		}
		throw new MemberNotFoundException(ErrorConstant.MEMBER_NOT_FOUND_EXCEPTION_MESSAGE + member.getMemberId());
	
	}

	
	
	
}


