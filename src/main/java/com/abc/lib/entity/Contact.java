package com.abc.lib.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.abc.lib.utils.CustomIdGenerator;

@Entity
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_seq_generator")
	@GenericGenerator(name = "contact_seq_generator", strategy = "com.abc.lib.utils.CustomIdGenerator", parameters = {
			@Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CustomIdGenerator.VALUE_PREFIX_PARAMETER, value = "CONTACT_"),
			@Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d") })
	@Column(name="contact_id",updatable=false,nullable=false)
	private String contactId;
	private String contactType;
	private String mobileNumber;
	private String emailId;

	/*@ManyToOne
	//@JoinColumn(name = "member_id", nullable = false)
	private Member member;*/

	
	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

/*	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}*/

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}
