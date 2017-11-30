package chat.controller;

import chat.view.ChatFrame;
import chat.view.PopupDisplay;
import chat.model.*;
import java.util.Scanner;
import java.io.*;

/**
 * Manages the chatbot app
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
		frame = new ChatFrame(this);
		
	}
	
	public void start() {
		String input = pop.displayQuestion("Hello! What would you like to talk about?");
		try {
			while (!bot.quitChecker(input)) {
				input = pop.displayQuestion(bot.processConversation(input));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		pop.displayText("Goodbye!");
		System.exit(0);
	}
	
	public Chatbot getChatbot() {
		return bot;
	}
	
	public PopupDisplay getDisplay() {
		return pop;
	}
	
	public String interactWithChatbot(String message) {
		if(!bot.quitChecker(message)){
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
