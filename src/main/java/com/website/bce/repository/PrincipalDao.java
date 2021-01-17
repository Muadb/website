package com.website.bce.repository;

import com.website.bce.model.Principal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrincipalDao extends JpaRepository<Principal, Long> {

}
