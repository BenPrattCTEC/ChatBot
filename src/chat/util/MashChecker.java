package chat.util;

import chat.util.ArraySizeMismatchException;

public class MashChecker {
	
	String[] patterns;
	int[] weights;
	int[] matchCount;
	
	public MashChecker(String[] patterns) {
		this.patterns = patterns;
		this.weights = new int[patterns.length];
		this.matchCount = new int[patterns.length];
		
		// Initializes weights[]
		for (int i = 0; i < patterns.length; i++) {
			weights[i] = patterns[i].length();
		}
		
	}
	
	public MashChecker(String[] patterns, int[] weights) throws ArraySizeMismatchException {
		this.patterns = patterns;
		this.weights = weights;
		this.matchCount = new int[patterns.length];
		
		if (patterns.length != weights.length)
			throw new ArraySizeMismatchException();
		
	}
	
	public double check(String sample) {
		
		sample = sample.toLowerCase();
		double mashProbability = 0;
		int stringLength = sample.length();
		int patternCountAccumulator = 0;
		
		// populates patternCount[]
		for (int i = 0; i < patterns.length; i++) {
			matchCount[i] = countOccurrences(patterns[i], sample);
			patternCountAccumulator += (matchCount[i]*weights[i]);
		}
		
		mashProbability = (double) patternCountAccumulator / (double) stringLength;
		
		return mashProbability;
	}
	
	private int countOccurrences(String pattern, String sample) {
		int index = sample.indexOf(pattern);
		int count = 0;
		while (index != -1) {
			count++;
			sample = sample.substring(index + 1);
			index = sample.indexOf(pattern);
		}
		return count;
	}
	
}
