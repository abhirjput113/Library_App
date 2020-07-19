package com.abc.lib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abc.lib.entity.MemberCredentials;

@Repository
public interface MemberCredentialsRepository extends JpaRepository<MemberCredentials, String>{

}
