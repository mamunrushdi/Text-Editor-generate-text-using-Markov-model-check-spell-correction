package document;

import java.util.List;

public class EfficientDocumentTest {

	public static void main(String[] args) {

		String text = "this is a? simple test";
		
		EfficientDocument tester = new EfficientDocument(text);
		
		List<String> tokens = tester.processTextList();
		
		for(String s : tokens)
			System.out.println(s);
	}

}
