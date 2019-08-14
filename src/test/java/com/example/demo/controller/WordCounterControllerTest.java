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

import com.example.demo.model.InputParagraph;
import com.example.demo.model.WordCount;
import com.example.demo.service.WordCounterService;

public class WordCounterControllerTest
{
	@InjectMocks
	private WordCountController wordCountController;

	@Mock
	private WordCounterService mockWordCounterService;

	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Before
	public void init()
	{
		MockitoAnnotations.initMocks(this);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetWordsCountHappyPath()
	{
		List<WordCount> wordCountList = new ArrayList<>();
		wordCountList.add(new WordCount("test", 2));
		String paragraph = "test";
		InputParagraph inputParagraph = new InputParagraph();
		inputParagraph.setPara(paragraph);
		when(mockWordCounterService.getWordCount(inputParagraph)).thenReturn(wordCountList);
		ResponseEntity<List<WordCount>> responseEntity = (ResponseEntity<List<WordCount>>) wordCountController
				.getWordCount(inputParagraph);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(wordCountList, responseEntity.getBody());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetWordsCountWhenParagraphIsNull()
	{
		when(mockWordCounterService.getWordCount(null)).thenThrow(IllegalArgumentException.class);
		ResponseEntity<String> responseEntity = (ResponseEntity<String>) wordCountController.getWordCount(null);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		assertEquals("Input para is not valid.", responseEntity.getBody());
	}

	@Test
	public void testGetWordsCountErrorScenario()
	{
		exceptionRule.expect(RuntimeException.class);
		// Mockito doesn't like it if we throw a checked exception. So, throwing a
		// RuntimeException would hit the piece of code that we need to test.
		when(mockWordCounterService.getWordCount(null)).thenThrow(RuntimeException.class);
		wordCountController.getWordCount(null);
	}
}
