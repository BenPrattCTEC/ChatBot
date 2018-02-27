package chat.model;

import chat.controller.ChatbotController;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class ChatbotTwitter {
	private ChatbotController controller;
	private Twitter twitter;
	
	public ChatbotTwitter(ChatbotController controller){
		this.controller = controller;
		this.twitter = TwitterFactory.getSingleton();
	}
	
	public void tweet(String text){
		try {
			twitter.updateStatus(text + "@ChatbotCTEC");
		}
		catch (TwitterException e) {
			// TODO Auto-generated catch block
			controller.handleErrors(e);
		}
	}
		
}
