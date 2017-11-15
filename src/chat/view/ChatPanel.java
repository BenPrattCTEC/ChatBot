package chat.view;

import chat.controller.ChatbotController;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ChatPanel extends JPanel {
	
	private ChatbotController controller;
	
	private JButton button;
	private JButton colorChangeButton;
	private SpringLayout layout;
	
	public ChatPanel(ChatbotController controller) {
		super();
		this.controller = controller;
		
		layout = new SpringLayout();
		
		button = new JButton("Button");
		layout.putConstraint(SpringLayout.WEST, button, 193, SpringLayout.WEST, this);
		
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private void setupPanel() {
		this.setBackground(Color.darkGray);
		this.setLayout(layout);
		this.add(button);
		this.add(colorChangeButton);
	}
	
	private void setupLayout() {
		
	}
	
	private void setupListeners() {
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
				System.exit(0);
			}
		});
	}
	
	
	
	private void randomBackgroundColor(){
		this.setBackground(new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)));
	}
	
}
