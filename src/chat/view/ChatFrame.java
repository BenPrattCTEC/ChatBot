package chat.view;

import java.awt.*;
import javax.swing.JFrame;
import chat.controller.ChatbotController;
import chat.view.ChatPanel;

public class ChatFrame extends JFrame {
	private ChatbotController controller;
	private ChatPanel panel;
	
	public ChatFrame(ChatbotController controller){
		super();
		
		this.controller = controller;
		this.panel = new ChatPanel(controller);
		
		setupFrame();
		
	}
	
	/**
	 * Initializes JFrame
	 */
	private void setupFrame(){
		
		this.setContentPane(panel);
		this.setTitle(controller.getChatbot().getName() + " the Chatbot");
		this.setSize(900, 500);
		this.setResizable(false);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		this.setVisible(true);
		panel.setFocusToInputBox();
	}
	
	/**
	 * gets the instance of ChatbotController
	 * @return instance of ChatbotController
	 */
	public ChatbotController getBaseController(){
		return getAppController();
	}
	
	/**
	 * gets the instance of ChatbotController
	 * @return instance of ChatbotController
	 */
	public ChatbotController getAppController(){
		return controller;
	}
	
	public ChatPanel getContentPane(){
		return panel;
	}
	
}
