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
		saveButton = new JButton("Save", new ImageIcon("sec/chat/view/Save-icon.png"));
		tweetButton = new JButton("Tweet", new ImageIcon("src/chat/view/Twitter.png"));
		loadButton = new JButton("Load", new ImageIcon("src/chat/view/loading.gif"));
		searchButton = new JButton("Search");
		
		chatScrollPane = new JScrollPane();
			layout.putConstraint(SpringLayout.WEST, chatScrollPane, 10, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.EAST, chatScrollPane, -125, SpringLayout.EAST, this);
		submitButton = new JButton("Submit");
		layout.putConstraint(SpringLayout.NORTH, submitButton, 252, SpringLayout.NORTH, this);
			layout.putConstraint(SpringLayout.EAST, submitButton, -22, SpringLayout.EAST, this);
		inputBox = new JTextField();
			layout.putConstraint(SpringLayout.NORTH, inputBox, 0, SpringLayout.NORTH, submitButton);
			layout.putConstraint(SpringLayout.WEST, inputBox, 10, SpringLayout.WEST, this);
			layout.putConstraint(SpringLayout.SOUTH, inputBox, 25, SpringLayout.NORTH, submitButton);
			layout.putConstraint(SpringLayout.EAST, inputBox, -19, SpringLayout.WEST, submitButton);
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
