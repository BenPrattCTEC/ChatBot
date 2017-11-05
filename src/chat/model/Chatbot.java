package chat.model;

import chat.model.*;
import chat.util.*;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


public class Chatbot {
	private List<Movie> movieList;
	private List<String> shoppingList;
	private List<String> cuteAnimalMemes;
	private String[] verbs;
	private String[] topics;
	private String[] followUps;
	private String[] questions;
	private String username;
	private String content;
	private String intro;
	private LocalTime currentTime;
	
	final String[] mashPatterns = {
			"df", "fp",
			"kj", "l;",
			";", "'",
			"sd", "fg",
			"hj", "gj",
			"[", "]",
			"/", "fd",
			"gm", "sg",
			"ad", "hf",
			"uj", "jf",
			"fg", "cvb",
			",.", "kjh"};
	
	private MashChecker mash = new MashChecker(mashPatterns);
	
	public Chatbot(String username) {
		this.movieList = new ArrayList<Movie>();
		this.shoppingList = new ArrayList<String>();
		this.cuteAnimalMemes = new ArrayList<String>();
		this.currentTime = null;
		this.questions = null;
		this.username = username;
		this.content = null;
		this.intro = null;
		this.currentTime = null;
		this.topics = null;
		this.verbs = null;
		this.followUps = null;
		
		buildVerbs();
		buildShoppingList();
		buildMovieList();
		buildCuteAnimals();
		buildQuestions();
		buildTopics();
	}
	
	private void buildVerbs() {
		verbs = new String[] { "like", "dislike", "am ambivalent about", "am thinking about" };
	}
	
	private void buildTopics() {
		topics = new String[] { "Cows", "The state of the world", "My pet fish, fluffy", "My own existence" };
	}
	
	private void buildQuestions() {
		questions = new String[] { 
				"What is your name?",
				"Do you like cheese?", 
				"How old are you?", 
				"what is your opinion on allowing AIs to vote?",
				"Are you a vegitarian?", 
				"Would you like to play a game?",
				"What is your name?",
				"what is your most commonly used password?",
				"What is your favorite color?",
				"What is your Mother's maiden name?"
				};
	}
	
	private void buildMovieList() {
		// Movie(String title, String genre, String ratingMPAA, String review, int
		// length, LocalDate releaseDate, double starScore)
		movieList.add(new Movie("Movie Name", "Movie", "R", "The worst movie i have ever seen", 120, LocalDate.now(), 1.2));
		movieList.add(new Movie("Movie Name", "Movie", "R", "The worst movie i have ever seen", 120, LocalDate.now(), 1.2));
		movieList.add(new Movie("Movie Name", "Movie", "R", "The worst movie i have ever seen", 120, LocalDate.now(), 1.2));
		movieList.add(new Movie("Movie Name", "Movie", "R", "The worst movie i have ever seen", 120, LocalDate.now(), 1.2));
		movieList.add(new Movie("Movie Name", "Movie", "R", "The worst movie i have ever seen", 120, LocalDate.now(), 1.2));
		movieList.add(new Movie("Movie Name", "Movie", "R", "The worst movie i have ever seen", 120, LocalDate.now(), 1.2));
		movieList.add(new Movie("Movie Name", "Movie", "R", "The worst movie i have ever seen", 120, LocalDate.now(), 1.2));
		
	}
	
	private void buildShoppingList() {
		shoppingList.add("snacks");
		shoppingList.add("money");
		shoppingList.add("veggies");
		shoppingList.add("protein");
		shoppingList.add("cats");
		shoppingList.add("frogs");
		shoppingList.add("chicken");
		shoppingList.add("rice");
		shoppingList.add("paper towels");
		shoppingList.add("dog food");
		shoppingList.add("dog");
		
	}
	
	private void buildCuteAnimals() {
		
	}
	
	public String processConversation(String input) {
		
		if (input != null) {
			if (keyboardMashChecker(input))
				return "I think that you just mashed the keyboard";
			if (input.equalsIgnoreCase("exit"))
				return "The command is quit you idiot";
		}
		
		return buildChatbotResponse();
	}
	
	private String buildChatbotResponse() {
		String response = "I ";
		response += verbs[(int) (Math.random() * verbs.length)] + " ";
		response += topics[(int) (Math.random() * topics.length)] + " ";
		response += "\n" + questions[(int) (Math.random() * questions.length)];
		return response;
	}
	
	public boolean lengthChecker(String input) {
		// checks if null
		if (input == null)
			return false;
		
		// checks length
		if (input.length() <= 2)
			return false;
		
		return true;
	}
	
	public boolean htmlTagChecker(String input) {
		int[] markers = new int[4];
		//the code tries to parse the tag, if it throws an error, the tag is invalid
		try{
			input = input.toLowerCase().trim();
			if(!input.startsWith("<"))
				return false;
			
			//index of opening tag beginning (First <)
			markers[0] = 0;
			//index of opening tag end (First >)
			markers[1] = input.indexOf(">");
			
			//assigns the string in the opening tag
			String openingTag = input.substring(markers[0]+1, markers[1]);
			System.out.println(openingTag);
			//if the content of the first tag is p, ignore the rest and return true
			if(openingTag.equals("p"))
				return true;
			
			//index of closing tag beginning (Second <)
			markers[2] = (input.substring(markers[1]).indexOf("<"))+markers[1];
			//index of closing tag end (Second >)
			markers[3] = (input.substring(markers[2]).indexOf(">"))+markers[2];
			
			//finds the data in the tags and content
			String content = input.substring(markers[1]+1, markers[2]);
			String closingTag = input.substring(markers[2]+1, markers[3]);
			
			//now that we know the tag is in the correct format, we can check the content
			
			if(openingTag.contains("href")){
				//checks if the href has an equals sign after it
				if(!openingTag.substring(openingTag.indexOf("href")+4).startsWith("="))
					return false;
			}
			
			//checks if the opening tag and closing tag are the same type and the closing tag begins with a '/'
			if(!openingTag.startsWith(closingTag.substring(1)) && closingTag.startsWith("/"))
				return false;
			return true;
		} catch(Exception e){
			return false;
		}
	}
	
	public boolean userNameChecker(String input) {
		if (input == null)
			return false;
		if (input == "")
			return false;
		if (!input.startsWith("@"))
			return false;
		if (input.substring(1).contains("@"))
			return false;
		
		return true;
	}
	
	public boolean contentChecker(String contentCheck) {
		return false;
	}
	
	public boolean cuteAnimalMemeChecker(String input) {
		return false;
	}
	
	public boolean shoppingListChecker(String shoppingItem) {
		for (int i = 0; i < shoppingList.size(); i++) {
			if (shoppingItem.toLowerCase().contains(shoppingList.get(i).toLowerCase()))
				return true;
		}
		return false;
	}
	
	public boolean movieTitleChecker(String title) {
		return false;
	}
	
	public boolean movieGenreChecker(String genre) {
		return false;
	}
	
	public boolean quitChecker(String exitString) {
		if (exitString == null)
			return false;
		if (!exitString.equalsIgnoreCase("quit") && !exitString.equalsIgnoreCase("cancel"))
			return false;
		return true;
	}
	
	public boolean keyboardMashChecker(String sample) {
		final double mashProbabilityThreshhold = .1;
		return mash.check(sample) > mashProbabilityThreshhold;
	}
	
	public List<Movie> getMovieList() {
		return movieList;
	}
	
	public List<String> getShoppingList() {
		return shoppingList;
	}
	
	public List<String> getCuteAnimalMemes() {
		return cuteAnimalMemes;
	}
	
	public String[] getQuestions() {
		return questions;
	}
	
	public String[] getVerbs() {
		return verbs;
	}
	
	public String[] getTopics() {
		return topics;
	}
	
	public String[] getFollowUps() {
		return followUps;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getIntro() {
		return null;
	}
	
	public LocalTime getCurrentTime() {
		return LocalTime.now();
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString(){
		return "";
	}
	
	
}
