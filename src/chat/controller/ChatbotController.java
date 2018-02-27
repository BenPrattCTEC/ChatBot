package chat.controller;

import chat.view.ChatFrame;
import chat.view.PopupDisplay;
import twitter4j.*;
import chat.model.*;
import chat.util.FileIO;

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
	ChatbotTwitter twitter;
	
	public ChatbotController() {
		bot = new Chatbot("Ben");
		pop = new PopupDisplay();
		inp = new Scanner(System.in);
		// frame = new ChatFrame(this);
		twitter = new ChatbotTwitter(this);
		frame = new ChatFrame(this);
		
	}
	
	public void start() {
		try {
//			System.out.println(FileIO.load("commonWords.txt"));
//			FileIO.save("/home/ben/file.txt", "File test");
		}
		catch (Exception e) {
			handleErrors(e);
		}
		
	}
	
	public void handleErrors(Exception e){
		pop.displayText(e.getMessage());
	}
	
	public Chatbot getChatbot() {
		return bot;
	}
	
	public PopupDisplay getDisplay() {
		return pop;
	}
	
	public void tweet(String text){
		twitter.tweet(text);
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
