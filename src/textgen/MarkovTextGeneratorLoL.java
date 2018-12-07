package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
 
		if(!wordList.isEmpty()) return;
		
		String[] words = sourceText.split("\\s+"); 
 		
		starter = words[0];
				
		for(int i = 0; i < words.length -1 ; i++)
		{
			ListNode node = new ListNode(words[i]);
					 
			if(containNode(node))
			{
				//get the node with this word
				for(ListNode n : wordList)
					if(n.getWord().equals(node.getWord())) n.addNextWord(words[i+1]);	
			}
			else
			{
				node.addNextWord(words[i+1]);
				wordList.add(node);
			}
		}
	
		//node for the last word where the very first word of the text would be the next word
		ListNode lastNode = new ListNode(words[words.length - 1]);
		lastNode.addNextWord(starter);
		wordList.add(lastNode);
	}

	//check of list already contain a node with the given word
	private boolean containNode(ListNode n)
	{
		for(int i = 0; i < wordList.size(); i++)
		{
			ListNode node = wordList.get(i);
			if(node.getWord().equals(n.getWord())) return true;
		}
		return false;
	}
	
	/** 
	 * Generate the number of words requested.
	 * set "currWord" to be the starter word
	set "output" to be ""
	add "currWord" to output
	while you need more words
   	find the "node" corresponding to "currWord" in the list
   select a random word "w" from the "wordList" for "node"
   	add "w" to the "output"
   set "currWord" to be "w" 
   increment number of words added to the list
	 */
	@Override
	public String generateText(int numWords) 
	{
		if(numWords == 0)  return "";
		if(wordList.size() == 0) return "";
		
		StringBuilder output = new StringBuilder();
		String currWord = starter;
		output.append(currWord);
		output.append(" ");
		

		while(numWords - 1 > 0)
		{
			//get node from ListNode
			ListNode node = getNode(currWord);
			
			//get next random word
			String word = node.getRandomNextWord(rnGenerator);
			output.append(word);
			output.append(" ");
			currWord = word;
			
			numWords--;
		}

		return output.toString();
	}
	
	//get node of a given word
	private ListNode getNode(String word)
	{
		for(ListNode node : wordList)
		{
			if(node.getWord().equals(word)) return node;
		}
		return null;
	}
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		 
		wordList.clear();
		train(sourceText);
		
 	}
	
 	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		//System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		//gen.train(textString2);

		
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		if(generator == null) return "";
		
		int index = generator.nextInt(nextWords.size());
		
		String word  = nextWords.get(index);
	    return word;
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


