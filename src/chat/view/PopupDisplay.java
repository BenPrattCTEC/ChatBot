package chat.view;


import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class PopupDisplay {
	
	private ImageIcon icon;
	private String windowTitle;
	
	public PopupDisplay(){
		
		icon = new ImageIcon(getClass().getResource("Images/Chatbot.png"));
		windowTitle = "Chatbot Says: ";
		
	}
	
	public void displayText(String text){
		JOptionPane.showMessageDialog(null, text, windowTitle, JOptionPane.INFORMATION_MESSAGE, icon);
	}
	
	public String displayQuestion(String text){
		return (String)JOptionPane.showInputDialog(null, text, windowTitle, JOptionPane.PLAIN_MESSAGE, icon, null, "");
	}
	
}
