package spelling;

public class AutoCompleteMatchCaseTest {

	public static void main(String[] args) 
	{
	
		AutoCompleteMatchCase tester = new AutoCompleteMatchCase();
		
		tester.addWord("Hello");
		tester.addWord("hello");
		tester.addWord("Hello");
		tester.addWord("helloo");

		System.out.println(tester.size());
		
		System.out.println(tester.isWord("hEllo"));
	}

}
