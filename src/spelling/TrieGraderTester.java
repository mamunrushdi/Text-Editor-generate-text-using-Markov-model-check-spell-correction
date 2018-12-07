package spelling;

public class TrieGraderTester 
{

	public static void main(String[] args) 
	{
		TrieGrader tester = new TrieGrader();
		
		String res = tester.feedback.toString();
		
		System.out.println("result: "+ tester.getFeedback().toString());
	}

}
