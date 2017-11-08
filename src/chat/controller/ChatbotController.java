package chat.controller;

import chat.view.*;
import chat.model.*;
import java.util.Scanner;
import java.io.*;

public class ChatbotController {
	
	private Chatbot bot;
	private PopupDisplay pop;
	private Scanner inp;
	
	
	
	
	public ChatbotController() {
		bot = new Chatbot("Ben");
		pop = new PopupDisplay();
		inp = new Scanner(System.in);
		
	}
	
	public void start() {
		String input = pop.displayQuestion("Hello! What would you like to talk about?");
		try {
			while (!bot.quitChecker(input)) {// && bot.lengthChecker(input)){
				input = pop.displayQuestion(bot.processConversation(input));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		pop.displayText("Goodbye!");
		System.exit(1);
	}
	
	public Chatbot getChatbot() {
		return bot;
	}
	
	public PopupDisplay getDisplay() {
		return pop;
	}
	
	public String interactWithChatbot(String message) {
		return null;
	}
	
	public ChatFrame getChatFrame() {
		// TODO Auto-generated method stub
		return null;
	}
}
