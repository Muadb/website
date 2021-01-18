package com.website.bce.repository;

import com.website.bce.model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorDao extends JpaRepository<Visitor, Long> {

}
