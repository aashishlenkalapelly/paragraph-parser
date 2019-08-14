package com.example.demo.model;

import com.example.demo.controller.WordCountController;

/**
 * Represents the output JSON object of {@link WordCountController#getWordCount(InputParagraph)}
 */
public class WordCount
{
	private String w;
	private int n;

	public WordCount(String w, int n)
	{
		this.w = w;
		this.n = n;
	}

	public String getW()
	{
		return w;
	}

	public void setW(String w)
	{
		this.w = w;
	}

	public int getN()
	{
		return n;
	}

	public void setN(int n)
	{
		this.n = n;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + n;
		result = prime * result + ((w == null) ? 0 : w.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WordCount other = (WordCount) obj;
		if (n != other.n)
			return false;
		if (w == null)
		{
			if (other.w != null)
				return false;
		} else if (!w.equals(other.w))
			return false;
		return true;
	}
}
