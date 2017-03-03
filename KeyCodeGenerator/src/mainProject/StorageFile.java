package mainProject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

public class StorageFile {

	private HashSet<String> dictionary = new HashSet<String>();

	String StoreNumbers() throws FileNotFoundException, IOException {
		String output = "";
		GenerateNumberCode gnc = new GenerateNumberCode();
		String genNumber = gnc.MakeNumber();
		
		//InputStream stream = StorageFile.class.getResourceAsStream("/numberStorage/NumberStorage.txt");
		//BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		
		try (Scanner fileIn = new Scanner(new File("NumberStorage.txt"))) {
		//try (Scanner fileIn = new Scanner(reader)) {	
			while (fileIn.hasNextLine()) {
				String line = fileIn.nextLine();
				dictionary.add(line);
			}
			fileIn.close();
		}

		do {
			genNumber = gnc.MakeNumber();
		} while (dictionary.contains(genNumber));

		if (dictionary.contains(genNumber)) {  // This check should always return false
			genNumber = gnc.MakeNumber();
		} else {
			try (FileWriter fw = new FileWriter("NumberStorage.txt", true);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter out = new PrintWriter(bw)) {
				out.println(genNumber);
				output = genNumber;
			} catch (IOException e) {
				// exception handling
			}
		}
		return output;
	}
}
