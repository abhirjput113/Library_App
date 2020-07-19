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
public class MemberCredentials {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cred_seq_generator")
	@GenericGenerator(name = "cred_seq_generator", strategy = "com.abc.lib.utils.CustomIdGenerator", parameters = {
			@Parameter(name = CustomIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = CustomIdGenerator.VALUE_PREFIX_PARAMETER, value = "CRED_"),
			@Parameter(name = CustomIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d") })
	@Column(name="cred_id",updatable=false,nullable=false)
	private String credId;
	private String username;
	private String password;

	/*@OneToOne
	@JoinColumn(name = "member_id",nullable=false)
	private Member member;*/

	public String getCredId() {
		return credId;
	}

	public void setCredId(String credId) {
		this.credId = credId;
	}

/*	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}*/

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
