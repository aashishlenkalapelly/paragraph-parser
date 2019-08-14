package com.example.demo.service;

import java.util.List;

import com.example.demo.model.InputParagraph;
import com.example.demo.model.WordCount;

/**
 * Service for calculating the word count in the provided paragraph.
 */
public interface WordCounterService
{
	/**
	 * Calculates the word count in the provided paragraph.
	 * 
	 * @param paragraph whose word count should be calculated.
	 * @return {@link List List} of {@link WordCount word count} objects which
	 *         represent a word and its count in the provided paragraph.
	 */
	public List<WordCount> getWordCount(InputParagraph paragraph);
}
