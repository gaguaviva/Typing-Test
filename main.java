import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;


public class main {
	
	public static void main (String[] args) throws IOException {
		
	    String test = "The quick brown fox jumped over the lazy dog"; // this needs to be converted to a character array
	    Reader type = new InputStreamReader(System.in);
	    Stack<Character> input = new Stack<>();
	    
	    int position = 0;
	    int mistake = 0;
	    int correct = 0;
		float accuracy;
	    
	    System.out.println("Enter the following");
	    System.out.println(test);
	    
	    while (position < test.length()) {
	    	int value = type.read();
	    		
	    	if (value == -1) {		// breaks so that 
	    		break;
	    	}
	    	
	    	char typedChar = (char) value;
	    	
	    	input.add(typedChar);
	    	
	    	if (input.peek() != test.charAt(correct)) {
	    		mistake++;
	    		System.out.println("-1");
	    	} else correct++;
	    	position++;
	    }
	    
	    if (correct - mistake <= 0) {
	    	accuracy = 0;
	    } else 	accuracy = ((float) correct / (correct + mistake)) * 100;
	    		    
	    System.out.println("Well done!");
	    System.out.println("Correct: " + correct);
	    System.out.println("Mistakes: " + mistake);
	    System.out.println("Accuracy: " + accuracy + "%");
	}	
}
