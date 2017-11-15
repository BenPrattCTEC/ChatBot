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
	
	private void setupFrame(){
		
		this.setContentPane(panel);
		this.setTitle("Temporary Title");
		this.setSize(900, 500);
		this.setResizable(false);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		this.setVisible(true);
	}
	
	public ChatbotController getBaseController(){
		return controller;
	}
	public ChatbotController getAppController(){
		return controller;
	}
	
	public ChatPanel getContentPane(){
		return panel;
	}
	
}
