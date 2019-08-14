package com.example.demo.service;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.example.demo.model.InputParagraph;
import com.example.demo.model.WordCount;

public class WordCounterServiceImplTest
{
	@Rule
	public ExpectedException exceptionRule = ExpectedException.none();

	@Test
	public void testGetWordCountHappyPath()
	{
		InputParagraph inputParagraph = new InputParagraph();
		inputParagraph.setPara("beta alpha gamma delta alpha");
		List<WordCount> expectedWordCountList = new LinkedList<>();
		expectedWordCountList.add(new WordCount("alpha", 2));
		expectedWordCountList.add(new WordCount("beta", 1));
		expectedWordCountList.add(new WordCount("delta", 1));
		expectedWordCountList.add(new WordCount("gamma", 1));

		WordCounterService wordCounterService = new WordCounterServiceImpl();
		List<WordCount> actualWordCountList = wordCounterService.getWordCount(inputParagraph);

		assertEquals(expectedWordCountList, actualWordCountList);
	}

	@Test
	public void testGetWordCountWithNullPara()
	{
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("Input para is not valid.");
		WordCounterService wordCounterService = new WordCounterServiceImpl();
		wordCounterService.getWordCount(null);
	}

	@Test
	public void testGetWordCountWithEmptyPara()
	{
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("Input para is not valid.");
		InputParagraph inputParagraph = new InputParagraph();
		inputParagraph.setPara(" ");
		WordCounterService wordCounterService = new WordCounterServiceImpl();
		wordCounterService.getWordCount(inputParagraph);
	}
}
