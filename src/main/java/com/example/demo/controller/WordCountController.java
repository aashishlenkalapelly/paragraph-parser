package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.InputParagraph;
import com.example.demo.model.WordCount;
import com.example.demo.service.WordCounterService;

/**
 * Controller for accepting HTTP requests related to word counting.
 */
@RestController
public class WordCountController
{
	Logger logger = LoggerFactory.getLogger(WordCountController.class);

	@Autowired
	WordCounterService wordCounterService;

	/**
	 * Accepts HTTP Get requests towards /wordCount for displaying all the words and
	 * theirs counts from the provided paragraph.
	 * 
	 * @param paragraph para for which unique words and their counts should be
	 *                  calculated.
	 * @return An {@link ResponseEntity object} which either has a {@link List list}
	 *         containing the {@link WordCount WordCount} objects, each representing
	 *         a unique word from the paragraph and it's count or a {@link String
	 *         string} containing the error message and HTTP status code.
	 */
	@GetMapping("/wordCount")
	@ResponseBody
	public ResponseEntity<?> getWordCount(@RequestBody InputParagraph paragraph)
	{
		logger.debug("In getWordCount");

		try
		{
			List<WordCount> wordCountList = wordCounterService.getWordCount(paragraph);
			return new ResponseEntity<List<WordCount>>(wordCountList, HttpStatus.OK);
		} catch (IllegalArgumentException illegalArgumentException)
		{
			logger.error("Input para is either null or empty.");
			return new ResponseEntity<String>("Input para is not valid.", HttpStatus.BAD_REQUEST);
		} catch (Exception exception)
		{
			logger.error("Error while calculating the word count for given paragraph.", exception);
			throw new RuntimeException(exception);
		}
	}
}
