package mainProject;

import java.util.ArrayList;
import java.util.Collections;

public class GenerateNumberCode {

	String MakeNumber(){
		ArrayList<Integer> numbers = new ArrayList<>();
		for (int i=0; i<10; i++){
			numbers.add(i);
		}
	
		Collections.shuffle(numbers);
	
		String code = "";
		for(int i=0; i<4; i++){
			code += numbers.get(i).toString();
		}
		return code.toString();
	}
}
