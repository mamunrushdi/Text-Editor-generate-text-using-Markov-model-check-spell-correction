package textgen;

import java.util.Random;

public class MarkovTextGeneratorLoLTest {

	public static void main(String[] args) 
	{
		Random myRandom = new Random();
		
		MarkovTextGeneratorLoL  tester = new MarkovTextGeneratorLoL(myRandom);
		
		String text = "this is a test, i'm I'm a very simple test this is";
		
		//text = "hi there hi Leo";
		//text = "";
		tester.train(text);
		
		System.out.println(tester.toString());
		
		String markovText = tester.generateText(0);
		System.out.println(markovText);
		
	}

}
