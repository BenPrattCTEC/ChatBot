package chat.view;


import javax.swing.JOptionPane;

public class PopupDisplay {
	
	public void displayText(String text){
		JOptionPane.showMessageDialog(null, text);
	}
	
	public String displayQuestion(String text){
		return JOptionPane.showInputDialog(null, text);
	}
	
}
