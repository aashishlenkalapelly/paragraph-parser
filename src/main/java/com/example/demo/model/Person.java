package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * Represents Person entity in the database.
 */
@Entity
@SequenceGenerator(name = "personIdGen", initialValue = 6)
public class Person
{
	@Id
	@GeneratedValue(generator = "personIdGen")
	private Long personId;
	private String firstName;
	private String lastName;

	public Person()
	{
	}

	public Person(Long personId, String firstName, String lastName)
	{
		this.personId = personId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Long getPersonId()
	{
		return personId;
	}

	public void setPersonId(Long personId)
	{
		this.personId = personId;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	@Override
	public String toString()
	{
		return "Person [personId=" + personId + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
}
