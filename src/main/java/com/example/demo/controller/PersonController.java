package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;

/**
 * Controller for accepting HTTP requests related to {@link Person Person}
 * entity.
 */
@RestController
public class PersonController
{
	Logger logger = LoggerFactory.getLogger(PersonController.class);

	@Autowired
	PersonService personService;

	/**
	 * Accepts HTTP Get requests towards /persons for displaying all the
	 * {@link Person person} records in the database.
	 * 
	 * @return An {@link ResponseEntity object} which either has a {@link List list}
	 *         containing all the {@link Person person} objects in the database or a
	 *         {@link String string} containing the error message and HTTP status
	 *         code.
	 */
	@GetMapping(value = "/persons")
	public ResponseEntity<?> getPersons()
	{
		logger.debug("In getPersons");
		try
		{
			List<Person> persons = personService.getAllPersons();
			if (persons.size() > 0)
			{
				return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
			}
			return new ResponseEntity<String>("There are no person entries in the database at the moment..",
					HttpStatus.OK);
		} catch (Exception exception)
		{
			logger.error("Error while getting person list from database.", exception);
			throw new RuntimeException(exception);
		}

	}

	/**
	 * Accepts HTTP Get requests towards /person/personId to get the details of the
	 * provided personId.
	 * 
	 * @param personId whose details should be returned.
	 * @return An {@link ResponseEntity object} which either has the {@link Person
	 *         person} object for the provided personId or a {@link String string}
	 *         containing the error message and HTTP status code.
	 */
	@GetMapping("/person/{personId}")
	public ResponseEntity<?> getPerson(@PathVariable("personId") Long personId)
	{
		logger.debug("In getPerson");
		try
		{
			Person person = personService.getPersonById(personId);
			if (person != null)
			{
				return new ResponseEntity<Person>(person, HttpStatus.OK);
			}
			logger.debug("Provided personId: " + personId + "doesn't exist in the database");
			return new ResponseEntity<String>("The provided Person ID doesn't exist in the database yet..",
					HttpStatus.BAD_REQUEST);
		} catch (IllegalArgumentException illegalArgumentException)
		{
			return new ResponseEntity<String>("Person ID should not be null.", HttpStatus.BAD_REQUEST);
		} catch (Exception exception)
		{
			logger.error("Error while fetching the person from database for personId: " + personId, exception);
			throw new RuntimeException(exception);
		}
	}

	/**
	 * Accepts HTTP Post requests towards /person to add a person to the database.
	 * 
	 * @param person {@link Person Person} object which should be added to the
	 *               database.
	 * @return An {@link ResponseEntity object} contains HTTP status code and either
	 *         a {@link Person person} object or a {@link String string} containing
	 *         the error message.
	 */
	@PostMapping("/person")
	public ResponseEntity<?> addPerson(@RequestBody Person person)
	{
		logger.debug("In addOrUpdatePerson");
		try
		{
			return new ResponseEntity<Person>(personService.addPerson(person), HttpStatus.OK);
		} catch (IllegalArgumentException illegalArgumentException)
		{
			return new ResponseEntity<String>("Please provide a valid input.", HttpStatus.BAD_REQUEST);
		} catch (Exception exception)
		{
			logger.error("Error while saving the person to the database.\n", exception);
			throw new RuntimeException(exception);
		}
	}
}
