package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Person;

/**
 * Service for handling all the required operations related to {@link Person
 * Person} entity.
 */
public interface PersonService
{
	/**
	 * Gets the {@link Person person} entity for the given personId.
	 * 
	 * @param personId ID of the person whose data should be fetched.
	 * @return {@link Person Person} object for the given personId.
	 * @throws IllegalArgumentException when the provided personId is not positive.
	 */
	public Person getPersonById(Long personId) throws IllegalArgumentException;

	/**
	 * Gets all the {@link Person person} entities in the database.
	 * 
	 * @return {@link List list} of {@link Person person} objects.
	 */
	public List<Person> getAllPersons();

	/**
	 * Adds the provided {@link Person person} entity to the database.
	 * 
	 * @param person entity which needs to be added.
	 * @return {@link Person person} object which got added to the database.
	 */
	public Person addPerson(Person person);
}
