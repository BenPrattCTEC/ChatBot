package chat.controller;

import chat.view.ChatFrame;
import chat.view.PopupDisplay;
import twitter4j.*;
import chat.model.*;
import java.util.Scanner;
import java.io.*;

/**
 * Manages the chatbot app
 * 
 * @author Ben Pratt
 */

public class ChatbotController {
	
	private Chatbot bot;
	private PopupDisplay pop;
	private Scanner inp;
	private ChatFrame frame;
	
	public ChatbotController() {
		bot = new Chatbot("Ben");
		pop = new PopupDisplay();
		inp = new Scanner(System.in);
		// frame = new ChatFrame(this);
		Twitter twitter = TwitterFactory.getSingleton();
		// try {
		// twitter.updateStatus("Second Tweet! @ChatbotCTEC");
		// System.out.println("updated status");
		// }
		// catch (TwitterException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		
	}
	
	public void start() {
		try {
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void handleErrors(Exception e){
		
	}
	
	public Chatbot getChatbot() {
		return bot;
	}
	
	public PopupDisplay getDisplay() {
		return pop;
	}
	
	public String interactWithChatbot(String message) {
		if (!bot.quitChecker(message)) {
			return bot.processConversation(message);
		}
		System.exit(0);
		return "";
	}
	
	public ChatFrame getChatFrame() {
		// TODO Auto-generated method stub
		return null;
	}
}
