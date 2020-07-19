package com.abc.lib.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.abc.lib.utils.CustomIdGenerator;

@Entity
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator")
	@GenericGenerator(name = "member_seq_generator", strategy = "com.abc.lib.utils.CustomIdGenerator", parameters = {
			@Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CustomIdGenerator.VALUE_PREFIX_PARAMETER, value = "MEMBER_"),
			@Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d") })
	@Column(name="member_id",updatable=false,nullable=false)
	private String memberId;

	private String memberFirstName;

	private String memberLastName;

	private String memberAddress;

	private String memberAadhaarNumber;

	private Integer memberAge;

	private String memberGender;

	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH }, orphanRemoval = false)
	@JoinColumn(name="fk_memberId",nullable=false)
	private List<Contact> contacts;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="cred_id")
	private MemberCredentials memberCredentials;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberFirstName() {
		return memberFirstName;
	}

	public void setMemberFirstName(String memberFirstName) {
		this.memberFirstName = memberFirstName;
	}

	public String getMemberLastName() {
		return memberLastName;
	}

	public void setMemberLastName(String memberLastName) {
		this.memberLastName = memberLastName;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public String getMemberAadhaarNumber() {
		return memberAadhaarNumber;
	}

	public void setMemberAadhaarNumber(String memberAadhaarNumber) {
		this.memberAadhaarNumber = memberAadhaarNumber;
	}

	public Integer getMemberAge() {
		return memberAge;
	}

	public void setMemberAge(Integer memberAge) {
		this.memberAge = memberAge;
	}

	public String getMemberGender() {
		return memberGender;
	}

	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}

	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}

	public MemberCredentials getMemberCredentials() {
		return memberCredentials;
	}

	public void setMemberCredentials(MemberCredentials memberCredentials) {
		this.memberCredentials = memberCredentials;
	}

}
