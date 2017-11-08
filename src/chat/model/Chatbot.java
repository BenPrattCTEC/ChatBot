package chat.model;

import chat.util.*;
import java.util.List;
import java.io.FileInputStream;
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
	private LocalTime currentTime;
	
	private MashChecker mash;
	
	private final double mashProbabilityThreshhold = 9;
	
	
	public Chatbot(String username) {
		this.movieList = new ArrayList<Movie>();
		this.shoppingList = new ArrayList<String>();
		this.cuteAnimalMemes = new ArrayList<String>();
		this.username = username;
		this.content = "I am some content";
		this.currentTime = LocalTime.now();
		
		buildVerbs();
		buildShoppingList();
		buildMovieList();
		buildCuteAnimals();
		buildQuestions();
		buildTopics();
		initMashChecker();
	}
	
	private void initMashChecker(){
		try{
			FileInputStream inFile = new FileInputStream("src/chat/util/mashTrainingData.txt");
			MashCheckerTrainer mashTrainer = new MashCheckerTrainer(inFile);
			mashTrainer.train();
			mash = new MashChecker(mashTrainer.getPatterns(), mashTrainer.getWeights());
		}catch(Exception e){
			System.out.println("Could not find mashTrainingData.txt");
			System.exit(0);
		}

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
		// Movie(String title, String genre, String ratingMPAA, String review, int length, LocalDate releaseDate, double starScore)
		movieList.add(new Movie("Spiderman", "Action", "PG-13", "Clever, funny, and true to the Spider-Man spirit.", 133, LocalDate.of(2017, 6, 28), 4));
		movieList.add(new Movie("Hidden Figures", "History/Drama", "PG", "Hidden Figures inspires as it entertains. It acknowledges racial divisions while insisting that there's more than one way to fix them.", 127, LocalDate.of(2016, 12, 25), 4));
		movieList.add(new Movie("Movie Name", "Documentary", "R", "The worst movie i have ever seen", 120, LocalDate.now(), 1.2));
		movieList.add(new Movie("Movie Name", "Thriller", "R", "The worst movie i have ever seen", 120, LocalDate.now(), 1.2));
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
	
	//what is this and why am i writing it?
	private void buildCuteAnimals() {
		cuteAnimalMemes.add("pupper");
		cuteAnimalMemes.add("otter");
		cuteAnimalMemes.add("kittie");
		cuteAnimalMemes.add("floofer");
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
		
//		if(((int)Math.random()*1024)%8 == 0)
//			response+="\n" + movieList.get((int)(Math.random()*movieList.size())).getTitle() + " Is a great movie"; 
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
		
		// an array that stores the location of the four tag openings/closings (< & >)
		int[] markers = new int[4];
		
		// the code tries to parse the tag. If it throws an error, that means tag is invalid
		try {
			input = input.toLowerCase().trim();
			
			//return false if the first character doesn't open a tag
			if (!input.startsWith("<"))
				return false;
			
			// index of opening tag beginning (First <)
			markers[0] = 0;
			// index of opening tag end (First >)
			markers[1] = input.indexOf(">");
			
			// assigns the string in the opening tag
			String openingTag = input.substring(markers[0] + 1, markers[1]);
			
			// if the content of the first tag is p, ignore the rest and return true because
			//<p> doesen't need a close because the stupid HTML people decided to not be consistent
			if (openingTag.equals("p"))
				return true;
			
			// index of closing tag beginning (Second <)
			markers[2] = (input.substring(markers[1]).indexOf("<")) + markers[1];
			
			// index of closing tag end (Second >)
			markers[3] = (input.substring(markers[2]).indexOf(">")) + markers[2];
			
			// finds the data in the other tags and content
			String content = input.substring(markers[1] + 1, markers[2]);
			String closingTag = input.substring(markers[2] + 1, markers[3]);
			
			//----------------------------------------------------------------------------
			// now that we know the tag is in the correct format, we can check the content
			
			if (openingTag.contains("href")) {
				// checks if the href has an equals sign after it
				if (!openingTag.substring(openingTag.indexOf("href") + 4).startsWith("="))
					return false;
			}
			
			// checks if the opening tag and closing tag are the same type and that the closing
			// tag begins with a '/'
			if (!openingTag.startsWith(closingTag.substring(1)) && closingTag.startsWith("/"))
				return false;
			
			//returns true if it hasen't thrown and exception or returned false yet.
			return true;
		}
		catch (Exception e) {
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
//		contentCheck=contentCheck.trim();
		if(contentCheck == null)
			return false;
		if(contentCheck.equals(" "))
			return false;
		if(contentCheck.contains(content))
			return true;
		return false;
	}
	
	public boolean cuteAnimalMemeChecker(String input) {
		input = input.trim();
		for (int i = 0; i < cuteAnimalMemes.size(); i++) {
			if (cuteAnimalMemes.get(i).contains(input))
				return true;
		}
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
		title = title.trim().toLowerCase();
		for(int i = 0; i<movieList.size(); i++){
			if(title.equals(movieList.get(i).getTitle().toLowerCase()))
				return true;
		}
		return false;
	}
	
	public boolean movieGenreChecker(String genre) {
		if(genre.equals(""))
			return false;
		genre = genre.trim().toLowerCase();
		for(int i = 0; i<movieList.size(); i++){
			if(movieList.get(i).getGenre().toLowerCase().contains(genre))
				return true;
		}
		return false;
	}
	
	public boolean quitChecker(String exitString) {
		if (exitString == null)
			return false;
		if (exitString.equalsIgnoreCase("quit") || exitString.equalsIgnoreCase("cancel"))
			return true;
		return false;
	}
	
	public boolean keyboardMashChecker(String sample) {
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
		currentTime = LocalTime.now();
		return currentTime;
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
