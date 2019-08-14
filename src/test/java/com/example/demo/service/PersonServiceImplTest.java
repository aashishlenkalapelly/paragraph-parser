package com.example.demo.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.dao.PersonDAO;
import com.example.demo.model.Person;

public class PersonServiceImplTest
{

	Long mockPersonId;
	Person mockPerson;
	@InjectMocks
	private PersonServiceImpl personServiceImpl;

	@Mock
	private PersonDAO mockPersonDAO;

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		mockPersonId = 1L;
		mockPerson = new Person(mockPersonId, "testFirstName", "testLastName");
	}

	@Test
	public void testGetPersonByIdWithNullPersonId()
	{
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("Person ID should not be null.");

		personServiceImpl.getPersonById(null);
	}

	@Test
	public void testGetPersonByIdHappyPath()
	{
		doReturn(Optional.of(mockPerson)).when(mockPersonDAO).findById(mockPersonId);
		assertEquals(mockPerson, personServiceImpl.getPersonById(mockPersonId));
	}

	@Test
	public void testGetPersonByIdWithInvalidPersonId()
	{
		doReturn(Optional.empty()).when(mockPersonDAO).findById(mockPersonId);
		assertEquals(null, personServiceImpl.getPersonById(mockPersonId));
	}

	@Test
	public void testGetAllPersonsHappyPath()
	{
		List<Person> persons = new ArrayList<>();
		persons.add(mockPerson);
		doReturn(persons).when(mockPersonDAO).findAll();
		assertEquals(persons, personServiceImpl.getAllPersons());
	}

	@Test
	public void testAddPersonHappyPath()
	{
		Person returnEntity = new Person(7L, "FN", "LN");
		doReturn(returnEntity).when(mockPersonDAO).save(mockPerson);
		assertEquals(returnEntity, personServiceImpl.addPerson(mockPerson));
	}

	@Test
	public void testAddPersonWithNullArgument()
	{
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("Person object should not be null.");
		personServiceImpl.addPerson(null);
	}
}
