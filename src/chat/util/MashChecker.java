package chat.util;

import chat.util.ArraySizeMismatchException;
import java.io.*;
import chat.util.MashCheckerTrainer;

public class MashChecker {
	
	String[] patterns;
	double[] weights;
	int[] matchCount;
	
	/**
	 * Constructor for Neural Net
	 * @param patterns The patterns to later check the string against, effectively the nodes of the network
	 */
	public MashChecker(String[] patterns) {
		this.patterns = patterns;
		this.weights = new double[patterns.length];
		this.matchCount = new int[patterns.length];
		
		// Initializes weights[]
		for (int i = 0; i < patterns.length; i++) {
			weights[i] = patterns[i].length();
		}
		
	}
	
	/**
	 * Constructor for Neural Net
	 * @param file the file of the training data relative to project path
	 * @throws FileNotFoundException If the file does not exist or you dont have permission to access it
	 */
	public MashChecker(String file) throws FileNotFoundException {
		try{
		MashCheckerTrainer trainer = new MashCheckerTrainer(new FileInputStream(file));
		trainer.train();
		this.weights = trainer.getWeights();
		this.patterns = trainer.getPatterns();
		this.matchCount = new int[weights.length];
		}catch(FileNotFoundException e){
			throw new FileNotFoundException();
		}
		
	}
	
	/**
	 * Constructor for Neural Net
	 * @param patterns The patterns to later check the string against, effectively the nodes of the network
	 * @param weights The weights of the nodes
	 * @throws ArraySizeMismatchException If patterns.length!=weights.length
	 */
	public MashChecker(String[] patterns, double[] weights) throws ArraySizeMismatchException {
		this.patterns = patterns;
		this.weights = weights;
		this.matchCount = new int[patterns.length];
		
		if (patterns.length != weights.length)
			throw new ArraySizeMismatchException();
		
	}
	
	/**
	 * Runs the input String through the network
	 * @param sample The value to run through the network
	 * @return an arbitrary value representing the likelihood that the string matches
	 */
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
		System.out.println(mashProbability);
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
