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
	private JScrollPane chatScrollPane;
	
	private JButton chatButton;
	private JButton loadButton;
	private JButton saveButton;
	private JButton tweetButton;
	private JButton searchButton;
	
	public ChatPanel(ChatbotController controller) {
		super();
		this.controller = controller;
		
		layout = new SpringLayout();
		
		chatButton = new JButton("Chat", new ImageIcon("src/chat/view/Chatbot.png"));
		layout.putConstraint(SpringLayout.EAST, chatButton, -21, SpringLayout.EAST, this);
		saveButton = new JButton("Save", new ImageIcon("sec/chat/view/Save-icon.png"));
		tweetButton = new JButton("Tweet", new ImageIcon("src/chat/view/Twitter.png"));
		layout.putConstraint(SpringLayout.EAST, tweetButton, -21, SpringLayout.EAST, this);
		loadButton = new JButton("Load", new ImageIcon("src/chat/view/loading.gif"));
		layout.putConstraint(SpringLayout.SOUTH, saveButton, -6, SpringLayout.NORTH, loadButton);
		layout.putConstraint(SpringLayout.SOUTH, loadButton, -6, SpringLayout.NORTH, tweetButton);
		searchButton = new JButton("Search");
		layout.putConstraint(SpringLayout.SOUTH, searchButton, -6, SpringLayout.NORTH, saveButton);
		
		chatScrollPane = new JScrollPane();
		layout.putConstraint(SpringLayout.WEST, tweetButton, 6, SpringLayout.EAST, chatScrollPane);
		layout.putConstraint(SpringLayout.WEST, chatButton, 6, SpringLayout.EAST, chatScrollPane);
			layout.putConstraint(SpringLayout.WEST, chatScrollPane, 10, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.EAST, chatScrollPane, -125, SpringLayout.EAST, this);
		submitButton = new JButton("Submit");
		layout.putConstraint(SpringLayout.SOUTH, tweetButton, -37, SpringLayout.NORTH, submitButton);
		layout.putConstraint(SpringLayout.EAST, submitButton, -21, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.WEST, searchButton, 0, SpringLayout.WEST, submitButton);
		layout.putConstraint(SpringLayout.EAST, searchButton, 0, SpringLayout.EAST, submitButton);
		layout.putConstraint(SpringLayout.WEST, saveButton, 0, SpringLayout.WEST, submitButton);
		layout.putConstraint(SpringLayout.EAST, saveButton, 0, SpringLayout.EAST, submitButton);
		layout.putConstraint(SpringLayout.WEST, loadButton, 0, SpringLayout.WEST, submitButton);
		layout.putConstraint(SpringLayout.EAST, loadButton, 0, SpringLayout.EAST, submitButton);
		layout.putConstraint(SpringLayout.SOUTH, chatButton, -6, SpringLayout.NORTH, submitButton);
		inputBox = new JTextField();
		layout.putConstraint(SpringLayout.WEST, submitButton, 6, SpringLayout.EAST, inputBox);
		layout.putConstraint(SpringLayout.NORTH, inputBox, 11, SpringLayout.SOUTH, chatScrollPane);
		layout.putConstraint(SpringLayout.WEST, inputBox, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, inputBox, -23, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, inputBox, -125, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.NORTH, submitButton, 0, SpringLayout.NORTH, inputBox);
			inputBox.setToolTipText("Enter Text Here");
			inputBox.setEnabled(true);
		historyTextBox = new JTextArea(10, 25);
			layout.putConstraint(SpringLayout.NORTH, chatScrollPane, 10, SpringLayout.NORTH, this);
			layout.putConstraint(SpringLayout.SOUTH, chatScrollPane, -59, SpringLayout.SOUTH, this);
			historyTextBox.setEditable(false);
			historyTextBox.setLineWrap(true);
		
		setupScrollPane();
		setupPanel();
		setupLayout();
		setupListeners();
		
		//prompting message
		historyTextBox.append(">>> Hello! I am " + controller.getChatbot().getName() + ", how are you today?\n\n");
	}
	
	/*
	 * Sets up the scroll pane
	 */
	private void setupScrollPane(){
		chatScrollPane.setViewportView(historyTextBox);
		chatScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		chatScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		historyTextBox.setWrapStyleWord(true);
	}
	
	/**
	 * Sets up the Panel
	 */
	private void setupPanel() {
		this.setBackground(Color.darkGray);
		this.setLayout(layout);
		this.add(inputBox);
		this.add(submitButton);
		this.add(chatScrollPane);
		this.add(chatButton);
		this.add(tweetButton);
		this.add(loadButton);
		this.add(saveButton);
		this.add(searchButton);
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
		
		tweetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
				controller.tweet(inputBox.getText());
			}
		});
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
			}
		});
		
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
			}
		});
		
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
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
		String text = controller.interactWithChatbot(inputBox.getText());
		historyTextBox.append(
				">>> " + inputBox.getText() + "\n" + 
				"<<< " + text + "\n\n");
		inputBox.setText("");
	}
	
	public boolean setFocusToInputBox(){
		return inputBox.requestFocusInWindow();
	}
}
