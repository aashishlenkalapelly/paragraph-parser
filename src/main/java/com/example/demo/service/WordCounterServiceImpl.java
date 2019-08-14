package com.example.demo.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiFunction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.model.InputParagraph;
import com.example.demo.model.WordCount;

@Service
public class WordCounterServiceImpl implements WordCounterService
{
	Logger logger = LoggerFactory.getLogger(WordCounterServiceImpl.class);

	@Override
	public List<WordCount> getWordCount(InputParagraph paragraph) // TODO
	{
		logger.debug("In getWordCount");
		if (paragraph == null || StringUtils.isEmpty(paragraph.getPara().trim()))
		{
			logger.error("Input paragraph is either empty or null.");
			throw new IllegalArgumentException("Input para is not valid.");
		}
		String[] words = paragraph.getPara().split(" ");
		Map<String, Integer> wordCountMap = new TreeMap<>();
		BiFunction<String, Integer, Integer> biFunction = (k, v) -> v + 1;
		for (String word : words)
		{
			if (wordCountMap.containsKey(word))
			{
				wordCountMap.computeIfPresent(word, biFunction);
			} else
			{
				wordCountMap.put(word, 1);
			}
		}
		return getWordCountList(wordCountMap);
	}

	private List<WordCount> getWordCountList(Map<String, Integer> wordCountMap)
	{
		List<WordCount> wordCountList = new LinkedList<>();
		for (Map.Entry<String, Integer> entry : wordCountMap.entrySet())
		{
			wordCountList.add(new WordCount(entry.getKey(), entry.getValue()));
		}
		return wordCountList;
	}
}
