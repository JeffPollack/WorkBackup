package textPage;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileImporter {
	String FileName="";

	FileImporter(String s){
		FileName=s;
	}
	
	public ArrayList<String> getFile() throws FileNotFoundException {
		ArrayList<String> fileString = new ArrayList<String>();

		InputStream stream = FileImporter.class.getResourceAsStream("/emailText/"+FileName);
		
		try (Scanner fileIn = new Scanner(stream)){   //stream was new File(FileName)
			
			while (fileIn.hasNextLine()){
				fileString.add(fileIn.nextLine());
			}
			fileIn.close();
		} catch (Exception fe){ //FileNotFoundException
			fe.printStackTrace();
		}
		
		return fileString;
	}
	
}
