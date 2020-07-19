package com.abc.lib.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.abc.lib.constants.Constant;
import com.abc.lib.entity.Member;
import com.abc.lib.exceptions.MemberNotFoundException;
import com.abc.lib.repository.MembershipRepository;
import com.abc.lib.service.MemberServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
public class MemberServiceImplTest {

	@Mock
	static MembershipRepository membershipRepositoryMock;

	static ResourceLoader resourceLoader;
	static ObjectMapper objectMapper;

	static Member member;
	static String memberId;
	static List<Member> members;
	static List<Member> membersByFirstName;

	static String message = Constant.MEMBER_ADDED_SUCCESS_MESSAGE;
	static String pathmemberByFirstNamePayload = "memberByFirstName.json";
	static String pathMemberPayload = "memberPayload.json";
	static String pathMembersPayload = "membersPayload.json";
	static String pathEmptyMemberPayload = "emptyMemberPayload.json";

	@InjectMocks
	MemberServiceImpl memberServiceImpl = new MemberServiceImpl();

	
	@BeforeClass
	public static void setUp() throws IOException {
		resourceLoader = new DefaultResourceLoader();
		objectMapper = new ObjectMapper();
		member = objectMapper.readValue(resourceLoader.getResource(pathMemberPayload).getFile(), Member.class);
		members = objectMapper.readValue(resourceLoader.getResource(pathMembersPayload).getFile(), new TypeReference<List<Member>>() {
		});
		membersByFirstName = objectMapper.readValue(resourceLoader.getResource(pathmemberByFirstNamePayload).getFile(),
				new TypeReference<List<Member>>() {
				});
	}

	@Test
	public void addMemberTest() {
		

		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>(
				message + member.getMemberFirstName(), HttpStatus.CREATED);

		when(membershipRepositoryMock.save(member)).thenReturn(member);
		assertEquals(expectedResponseEntity, memberServiceImpl.addMember(member));
	}

	@Test
	public void fetchMemberTest() {

		Optional<Member> optionalMember = Optional.of(member);

		ResponseEntity<Member> expectedResponseEntity = new ResponseEntity<Member>(member, HttpStatus.OK);

		when(membershipRepositoryMock.findById(member.getMemberId())).thenReturn(optionalMember);
		assertEquals(expectedResponseEntity, memberServiceImpl.fetchMember(member.getMemberId()));

	}

	@Test(expected = MemberNotFoundException.class)
	public void fetchMemberInvalidTest() {

		String invalidMemberId = "MEMBER_00011111";

		when(membershipRepositoryMock.findById(invalidMemberId)).thenReturn(Optional.empty());
		memberServiceImpl.fetchMember(invalidMemberId);
		
		
		
	}

	@Test
	public void fetchMembersTest() {

		ResponseEntity<List<Member>> expectedResponseEntity = new ResponseEntity<List<Member>>(members, HttpStatus.OK);

		when(membershipRepositoryMock.findAll()).thenReturn(members);
		assertEquals(expectedResponseEntity, memberServiceImpl.fetchMembers());
	}

	@Test(expected = MemberNotFoundException.class)
	public void fetchMembersInvalidTest() {
		List<Member> emptyList = new ArrayList<Member>();

		when(membershipRepositoryMock.findAll()).thenReturn(emptyList);
		memberServiceImpl.fetchMembers();
	}

	@Test
	public void fetchByMemberFirstNameTest() {

		String memberFirstNameContains = "shubh";

		ResponseEntity<List<Member>> expectedResponseEntity = new ResponseEntity<List<Member>>(membersByFirstName,
				HttpStatus.OK);
		when(membershipRepositoryMock.findByMemberFirstNameContaining(memberFirstNameContains))
				.thenReturn(membersByFirstName);

		assertEquals(expectedResponseEntity,
				memberServiceImpl.findByMemberFirstNameContaining(memberFirstNameContains));

	}
	
	@Test
	public void removeMemberTest() {

		ResponseEntity<String> expectedResponseEntity=new ResponseEntity<String>(Constant.MEMBER_DELETED_SUCCESS_MESSAGE + memberId, HttpStatus.OK);

		
		Optional<Member> optional=Optional.of(member);
		
		
		when(membershipRepositoryMock.findById(memberId)).thenReturn(optional);
	     doNothing().when(membershipRepositoryMock).deleteById(memberId);
	     assertEquals(expectedResponseEntity, memberServiceImpl.removeMember(memberId));
	}
	
	@Test(expected = MemberNotFoundException.class)
	public void removeMemberInvalidTest() {
		
		String invalidId="MEMBER_000000888";
		
		when(membershipRepositoryMock.findById(invalidId)).thenReturn(Optional.empty());
		
		memberServiceImpl.removeMember(invalidId);
	}
	
	@Test
	public void updateMemberTest() {
		
		ResponseEntity<Member> expectedResponseEntity = new ResponseEntity<Member>(member, HttpStatus.OK);
		Optional<Member> optional= Optional.of(member); 
		when(membershipRepositoryMock.findById(member.getMemberId())).thenReturn(optional);
		assertEquals(expectedResponseEntity, memberServiceImpl.updateMember(member));
	}
	
	@Test(expected = MemberNotFoundException.class)
	public void updateMemberInvalidTest() {
		
		String memberInvalidId="MEMBER_9772662";
		
		when(membershipRepositoryMock.findById(memberInvalidId)).thenReturn(Optional.empty());
		
		memberServiceImpl.updateMember(member);
	}
}
