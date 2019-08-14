package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Person;

/**
 * DAO for accessing all the DB operations related to {@link Person Person}.
 */
public interface PersonDAO extends JpaRepository<Person, Long>
{

}
