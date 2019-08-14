package com.example.demo.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;

public class PersonControllerTest
{
	Long mockPersonId;
	Person mockPerson;
	@InjectMocks
	private PersonController personController;

	@Mock
	private PersonService mockPersonService;

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
		mockPersonId = 1L;
		mockPerson = new Person(mockPersonId, "testFirstName", "testLastName");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetPersonsHappyPath()
	{
		List<Person> listOfPersons = new ArrayList<>();
		listOfPersons.add(mockPerson);
		when(mockPersonService.getAllPersons()).thenReturn(listOfPersons);
		ResponseEntity<List<Person>> responseEntity = (ResponseEntity<List<Person>>) personController.getPersons();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		// as it is the same List object, hashcodes should be equal
		assertEquals(listOfPersons, responseEntity.getBody());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetPersonsWhenThereAreNoEntriesInDB()
	{
		when(mockPersonService.getAllPersons()).thenReturn(new ArrayList<>());
		ResponseEntity<String> responseEntity = (ResponseEntity<String>) personController.getPersons();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		// as it is the same List object, hashcodes should be equal
		assertEquals("There are no person entries in the database at the moment..", responseEntity.getBody());
	}

	@Test
	public void testGetPersonsErrorScenario()
	{
		exceptionRule.expect(RuntimeException.class);
		// Mockito doesn't like it if we throw a checked exception. So, throwing a
		// RuntimeException would hit the piece of code that we need to test.
		when(mockPersonService.getAllPersons()).thenThrow(RuntimeException.class);
		personController.getPersons();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetPersonHappyPath()
	{
		when(mockPersonService.getPersonById(mockPersonId)).thenReturn(mockPerson);
		ResponseEntity<Person> responseEntity = (ResponseEntity<Person>) personController.getPerson(mockPersonId);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(mockPerson, responseEntity.getBody());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetPersonWhenIdDoesntExistInDB()
	{
		when(mockPersonService.getPersonById(mockPersonId)).thenReturn(null);
		ResponseEntity<String> responseEntity = (ResponseEntity<String>) personController.getPerson(mockPersonId);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		assertEquals("The provided Person ID doesn't exist in the database yet..", responseEntity.getBody());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetPersonWithNullPersonId()
	{
		when(mockPersonService.getPersonById(null)).thenThrow(IllegalArgumentException.class);
		ResponseEntity<String> responseEntity = (ResponseEntity<String>) personController.getPerson(null);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		assertEquals("Person ID should not be null.", responseEntity.getBody());
	}

	@Test
	public void testGetPersonErrorScenario()
	{
		exceptionRule.expect(RuntimeException.class);
		// Mockito doesn't like it if we throw a checked exception. So, throwing a
		// RuntimeException would hit the piece of code that we need to test.
		when(mockPersonService.getPersonById(mockPersonId)).thenThrow(RuntimeException.class);
		personController.getPerson(mockPersonId);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAddPersonHappyPath()
	{
		when(mockPersonService.addPerson(mockPerson)).thenReturn(mockPerson);
		ResponseEntity<Person> responseEntity = (ResponseEntity<Person>) personController.addPerson(mockPerson);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(mockPerson, responseEntity.getBody());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAddPersonWithNullPersonObject()
	{
		when(mockPersonService.addPerson(null)).thenThrow(IllegalArgumentException.class);
		ResponseEntity<String> responseEntity = (ResponseEntity<String>) personController.addPerson(null);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		assertEquals("Please provide a valid input.", responseEntity.getBody());
	}

	@Test
	public void testAddPersonErrorScenario()
	{
		exceptionRule.expect(RuntimeException.class);
		// Mockito doesn't like it if we throw a checked exception. So, throwing a
		// RuntimeException would hit the piece of code that we need to test.
		when(mockPersonService.addPerson(null)).thenThrow(RuntimeException.class);
		personController.addPerson(null);
	}
}
