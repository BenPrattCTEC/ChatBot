package chat.model;

import chat.model.*;

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
	
	public Chatbot(String username) {
		this.movieList = null;
		this.shoppingList = new ArrayList<String>();
		this.cuteAnimalMemes = null;
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
	}
	
	private void buildVerbs(){
		verbs = new String[] {"like", "dislike", "am ambivalent about", "am thinking about"};
	}
	
	private void buildMovieList() {
		//Movie(String title, String genre, String ratingMPAA, String review, int length, LocalDate releaseDate, double starScore)
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
		shoppingList.add("pritein");
		shoppingList.add("cats");
		
	}
	
	private void buildCuteAnimals() {
		
	}
	
	private void buildQuestions() {
		
	}
	
	public String processConversation(String input) {
		return null;
	}
	
	public boolean lengthChecker(String input) {
		//checks if null
		if(input == null)
			return false;
		
		//checks length
		if(input.length() <= 2)
			return false;
		
		return true;
	}
	
	public boolean htmlTagChecker(String input) {
		return false;
	}
	
	public boolean userNameChecker(String input) {
		if(input==null)
			return false;
		if(!input.startsWith("@"))
			return false;
		if(input.substring(1).contains("@"))
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
		for(int i = 0; i<shoppingList.size(); i++){
			if(shoppingList.get(i).equals(shoppingItem))
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
		return false;
	}
	
	public boolean keyboardMashChecker(String sample) {
		return false;
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
}
