package chat.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MashCheckerTrainer {
	
	private List<Integer> weights = new ArrayList<Integer>();
	private List<String> patterns = new ArrayList<String>();
	private String[] finalPatterns;
	private double[] finalWeights;
	private FileInputStream in;
	private FileOutputStream out;
	
	private String trainingData;
	
	/**
	 * Constructor
	 * @param inFile The training data for the network
	 * 
	 */
	public MashCheckerTrainer(String inFile) throws FileNotFoundException {
		in = new FileInputStream(inFile);
	}
	
	/**
	 * Constructor
	 * @param fileInStream The training data for the network
	 * 
	 */
	public MashCheckerTrainer(FileInputStream fileInStream) {
		in = fileInStream;
	}
	
	/**
	 * Train the network from the file declared in the constructor 
	 */
	public void train() {
		
		// puts the contents of in into trainingData
		 System.out.println("Reading file into memory...");
		trainingData = fileToString(in).toLowerCase();
		
		System.out.println("Training...");
		
		String trainingBuffer;
		int indexInList;
		
		// counts the occurrences of every 2-3 character string in the file
		for (int charLength = 2; charLength <= 4; charLength++) {
			for (int i = 0; i < trainingData.length() - charLength; i++) {
				trainingBuffer = trainingData.substring(i, i + charLength);
				indexInList = findInList(patterns, trainingBuffer);
				if (indexInList == -1) {
					patterns.add(trainingBuffer);
					weights.add(1);
				}
				else {
					weights.set(indexInList, weights.get(indexInList) + 1);
				}
			}
		}
		
		// converts from an ArrayList to an array
		finalWeights = toIntArray(weights);
		finalPatterns = toStringArray(patterns);
		
		 System.out.println("Training Complete, finishing up Weight Calculations...");
		// multiplies the weights by the string length and divides them by the length of
		// the training data
		for (int i = 0; i < finalWeights.length; i++) {
			finalWeights[i] = ((finalWeights[i] * (double) finalPatterns[i].length() * 26) / (double) trainingData.length()) * 100;
		}
		
		System.out.println("Network trained, sorting...");
		// binary sort that keeps the weights at the same index as the patterns
		String tmpStr;
		double tmpDbl;
		boolean hasSwapped = true;
		while (hasSwapped) {
			hasSwapped = false;
			for (int i = 0; i < finalWeights.length - 1; i++) {
				if (finalWeights[i] < finalWeights[i + 1]) {
					
					// swaps values
					hasSwapped = true;
					tmpDbl = finalWeights[i];
					tmpStr = finalPatterns[i];
					
					finalWeights[i] = finalWeights[i + 1];
					finalPatterns[i] = finalPatterns[i + 1];
					
					finalWeights[i + 1] = tmpDbl;
					finalPatterns[i + 1] = tmpStr;
					
				}
			}
		}
		
//		System.out.println("Network trained, printing...");
//		for (int i = 0; i < finalPatterns.length; i++) {
//			System.out.print(finalPatterns[i] + " " + finalWeights[i] + "\n");
//		}
		
		//cleans up
		weights = null;
		patterns = null;
		trainingData = null;
		
		 System.out.println("Training Complete");
	}
	
	/**
	 * Returns the node Weight array
	 * @return Weight array
	 */
	public double[] getWeights() {
		return finalWeights;
	}
	
	/**
	 * Returns the node Pattern array
	 * @return Pattern array
	 */
	public String[] getPatterns() {
		return finalPatterns;
	}
	
	private String fileToString(FileInputStream in) {
		String outBuffer = "";
		try {
			int i = in.read();
			do {
				outBuffer += (char) i;
				i = in.read();
			}
			while (i != -1);
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		return outBuffer;
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
	
	private double[] toIntArray(List<Integer> list) {
		double[] ret = new double[list.size()];
		for (int i = 0; i < ret.length; i++)
			ret[i] = (double) list.get(i);
		return ret;
	}
	
	private String[] toStringArray(List<String> list) {
		String[] ret = new String[list.size()];
		for (int i = 0; i < ret.length; i++)
			ret[i] = list.get(i);
		return ret;
	}
	
	private int findInList(List<String> list, String text) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(text))
				return i;
		}
		return -1;
	}
}
