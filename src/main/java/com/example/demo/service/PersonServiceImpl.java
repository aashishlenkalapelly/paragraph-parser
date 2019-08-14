package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PersonDAO;
import com.example.demo.model.Person;

@Service
public class PersonServiceImpl implements PersonService
{
	Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

	@Autowired
	PersonDAO personDAO;

	@Override
	public Person getPersonById(Long personId) throws IllegalArgumentException
	{
		logger.debug("In getPersonById");
		// make sure that personId is not null and positive.
		if (personId == null)
		{
			logger.error("Got null personId.");
			throw new IllegalArgumentException("Person ID should not be null.");
		}
		Optional<Person> person = personDAO.findById(personId);
		if (person.isPresent())
		{
			return person.get();
		}
		logger.debug("No entries found in database for personId: " + personId);
		return null;
	}

	@Override
	public List<Person> getAllPersons()
	{
		logger.debug("In getAllPersons");
		return personDAO.findAll();
	}

	@Override
	public Person addPerson(Person person)
	{
		logger.debug("In addOrUpdate");
		if (person == null)
		{
			throw new IllegalArgumentException("Person object should not be null.");
		}
		return personDAO.save(person);
	}
}
