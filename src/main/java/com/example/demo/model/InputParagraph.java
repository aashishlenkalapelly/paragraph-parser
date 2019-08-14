package com.example.demo.model;

import com.example.demo.controller.WordCountController;

/**
 * Represents the input JSON object for {@link WordCountController#getWordCount(InputParagraph)}
 */
public class InputParagraph
{
	private String para;

	public String getPara()
	{
		return para;
	}

	public void setPara(String para)
	{
		this.para = para;
	}
}
