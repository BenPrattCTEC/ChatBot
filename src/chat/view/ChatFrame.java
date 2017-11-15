package chat.view;

import java.awt.*;
import javax.swing.JFrame;
import chat.controller.ChatbotController;
import chat.view.ChatPanel;

public abstract class ChatFrame extends JFrame {
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
		this.setTitle("GUI Testing");
		this.setSize(500, 500);
		this.setResizable(false);
		
		this.setVisible(true);
	}
	
}
