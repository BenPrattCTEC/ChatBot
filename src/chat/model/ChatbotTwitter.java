package chat.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import chat.controller.ChatbotController;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class ChatbotTwitter {
	private ChatbotController controller;
	private Twitter twitter;
	private ArrayList<String> tweetedWords;
	private ArrayList<Status> searchedTweets;
	private long totalWordCount;
	private HashMap wordsAndCount;
	
	public ChatbotTwitter(ChatbotController controller) {
		this.controller = controller;
		this.twitter = TwitterFactory.getSingleton();
		this.searchedTweets = new ArrayList<Status>();
		this.tweetedWords = new ArrayList<String>();
		this.wordsAndCount = new HashMap<String, Integer>();
		
	}
	
	public String getMostCommonWord(String username) {
		
		collectTweets(username);
		turnStatusesToWords();
		totalWordCount = tweetedWords.size();
		String[] boring = createIgnoredWordArray();
		removeBlanks();
		trimTheBoringWords(boring);
		generateWordCount();
		
		ArrayList<Map.Entry<String, Integer>> sorted = sortHashMap();
		String mostCommonWord = sorted.get(0).getKey();
		int maxWord = sorted.get(0).getValue();
		
		return mostCommonWord;
	}
	
	public void tweet(String text) {
		try {
			twitter.updateStatus(text + "@ChatbotCTEC");
		}
		catch (TwitterException e) {
			// TODO Auto-generated catch block
			controller.handleErrors(e);
		}
	}
	
	private void removeBlanks() {
		for (int i = tweetedWords.size() - 1; i >= 0; i--) {
			if (tweetedWords.get(i).trim().length() == 0) {
				tweetedWords.remove(i);
			}
		}
	}
	
	private void turnStatusesToWords() {
		for (Status currentStatus : searchedTweets) {
			String tweetText = currentStatus.getText();
			tweetText = tweetText.replace("\n", " ");
			String[] tweetWords = tweetText.split(" ");
			for (int i = 0; i < tweetWords.length; i++) {
				tweetedWords.add(removePunctuation(tweetWords[i]).trim());
			}
		}
	}
	
	private void trimTheBoringWords(String[] boringWords) {
		for (int i = tweetedWords.size() - 1; i >= 0; i--) {
			for (int removeIndex = 0; removeIndex < boringWords.length; removeIndex++) {
				if (tweetedWords.get(i).equalsIgnoreCase(boringWords[removeIndex])) {
					tweetedWords.remove(i);
					removeIndex = boringWords.length;
				}
			}
		}
	}
	
	private void generateWordCount() {
		for (String word : tweetedWords) {
			if (!wordsAndCount.containsKey(word.toLowerCase()))
				wordsAndCount.put(word.toLowerCase(), 1);
			else
				wordsAndCount.replace(word.toLowerCase(), wordsAndCount.get(word.toLowerCase()) + 1);
			
		}
	}
	
	private ArrayList<Map.Entry<String, Integer>> sortHashMap() {
		ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(wordsAndCount.entrySet());
		entries.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));
		
		return entries;
		
	}
}
