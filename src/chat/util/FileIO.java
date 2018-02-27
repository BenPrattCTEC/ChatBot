package chat.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileIO {
	public static void save(String filename, String text) throws IOException {
		PrintWriter file = new PrintWriter(new File(filename));
		file.println(text);
		file.close();
	}
	
	public static String load(String filename) throws FileNotFoundException, IOException {
		String retBuffer = "";
		Scanner file = new Scanner(new File(filename));
		
		while (file.hasNextLine()) {
			retBuffer += file.nextLine() + "\n";
		}
		
		file.close();
		
		return retBuffer;
	}
}
