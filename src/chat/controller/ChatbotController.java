package chat.controller;

import chat.view.*;
import chat.model.*;

public class ChatbotController {
	
	private Chatbot bot;
	private PopupDisplay pop;
	
	public void start(){
		
		bot = new Chatbot("Ben");
		pop = new PopupDisplay();
		
		pop.displayQuestion("FLHAFBKHASDJFNj;kans;djksadas");
		
	}
	public Chatbot getChatbot(){
		return bot;
	}
	public PopupDisplay getDisplay(){
		return pop;
	}
		
	public String interactWithChatbot(String message){
		return null;
	}

	public ChatFrame getChatFrame() {
		// TODO Auto-generated method stub
		return null;
	}
}











