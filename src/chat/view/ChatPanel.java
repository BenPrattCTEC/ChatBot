package chat.view;

import chat.controller.ChatbotController;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class ChatPanel extends JPanel {
	
	private ChatbotController controller;
	
	private JButton submitButton;
	private JTextArea historyTextBox;
	private JTextField inputBox;
	private SpringLayout layout;
	
	public ChatPanel(ChatbotController controller) {
		super();
		this.controller = controller;
		
		layout = new SpringLayout();
		
		submitButton = new JButton("Submit");
		inputBox = new JTextField();
		layout.putConstraint(SpringLayout.SOUTH, inputBox, -23, SpringLayout.SOUTH, this);
		inputBox.setToolTipText("Enter Text Here");
		layout.putConstraint(SpringLayout.WEST, inputBox, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, inputBox, -125, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, submitButton, 0, SpringLayout.NORTH, inputBox);
		layout.putConstraint(SpringLayout.WEST, submitButton, 19, SpringLayout.EAST, inputBox);
		inputBox.setEnabled(true);
		historyTextBox = new JTextArea(10, 25);
		layout.putConstraint(SpringLayout.NORTH, inputBox, 11, SpringLayout.SOUTH, historyTextBox);
		layout.putConstraint(SpringLayout.NORTH, historyTextBox, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.SOUTH, historyTextBox, -59, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, historyTextBox, 0, SpringLayout.EAST, submitButton);
		layout.putConstraint(SpringLayout.WEST, historyTextBox, 0, SpringLayout.WEST, inputBox);
		historyTextBox.setEditable(false);
		historyTextBox.setLineWrap(true);
		
		setupPanel();
		setupLayout();
		setupListeners();
	}
	
	/**
	 * Sets up the Panel
	 */
	private void setupPanel() {
		this.setBackground(Color.darkGray);
		this.setLayout(layout);
		this.add(inputBox);
		this.add(submitButton);
		this.add(historyTextBox);
	}
	
	/**
	 * sets up the Layout
	 */
	private void setupLayout() {
		
	}
	
	/**
	 * sets up all of the listeners
	 */
	private void setupListeners() {
		
		//checks if you press enter while in the textBox
		inputBox.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER)
					submit();
			}
		});
		
		//checks if you press submit
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
				submit();
			}
		});
		
	}
	
	/**
	 * returns a random color
	 * @return a random color
	 */
	private Color randomColor() {
		return (new Color((int) (Math.random() * 256), (int) (Math.random() * 256), (int) (Math.random() * 256)));
	}
	
	/**
	 * submits the contents of inputBox to the chatbot
	 */
	private void submit() {
		String text =controller.interactWithChatbot(inputBox.getText());
		historyTextBox.setText(
				historyTextBox.getText() +
				"\n>>>" + inputBox.getText() + "\n" + 
				"<<< " + text);
		inputBox.setText("");
	}
}
