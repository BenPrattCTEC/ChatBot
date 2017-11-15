package chat.view;

import chat.controller.ChatbotController;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ChatPanel extends JPanel {
	
	private ChatbotController controller;
	
	private JButton quitButton;
	private JButton submitButton;
	private JTextArea historyTextBox;
	private JTextField textBox;
	private SpringLayout layout;
	
	public ChatPanel(ChatbotController controller) {
		super();
		this.controller = controller;
		
		layout = new SpringLayout();
		
		quitButton = new JButton("Quit");
		layout.putConstraint(SpringLayout.WEST, quitButton, 352, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, quitButton, -59, SpringLayout.SOUTH, this);
		submitButton = new JButton("Submit");
		layout.putConstraint(SpringLayout.NORTH, submitButton, 11, SpringLayout.SOUTH, quitButton);
		layout.putConstraint(SpringLayout.EAST, submitButton, 0, SpringLayout.EAST, quitButton);
		textBox = new JTextField();
		layout.putConstraint(SpringLayout.NORTH, textBox, 0, SpringLayout.NORTH, submitButton);
		layout.putConstraint(SpringLayout.WEST, textBox, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, textBox, 0, SpringLayout.SOUTH, submitButton);
		layout.putConstraint(SpringLayout.EAST, textBox, -6, SpringLayout.WEST, submitButton);
		textBox.setEnabled(true);
		historyTextBox = new JTextArea(10, 25);
		layout.putConstraint(SpringLayout.NORTH, historyTextBox, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, historyTextBox, 0, SpringLayout.WEST, textBox);
		layout.putConstraint(SpringLayout.SOUTH, historyTextBox, 0, SpringLayout.SOUTH, quitButton);
		layout.putConstraint(SpringLayout.EAST, historyTextBox, 0, SpringLayout.EAST, textBox);
		historyTextBox.setEditable(false);
		historyTextBox.setLineWrap(true);
		
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	private void setupPanel() {
		this.setBackground(Color.darkGray);
		this.setLayout(layout);
		this.add(quitButton);
		this.add(textBox);
		this.add(submitButton);
		this.add(historyTextBox);
	}
	
	private void setupLayout() {
		
	}
	
	private void setupListeners() {
		
		//checks if you press enter while in the textBox
		textBox.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER)
					submit();
			}
		});
		
		//checks if you press quit
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
				System.exit(0);
			}
		});
		
		//checks if you press submit
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
				submit();
			}
		});
	}
	
	private Color randomColor() {
		return (new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)));
	}
	
	private void submit() {
		String text =controller.interactWithChatbot(textBox.getText());
		historyTextBox.setText(text);
		textBox.setText("");
	}
}
