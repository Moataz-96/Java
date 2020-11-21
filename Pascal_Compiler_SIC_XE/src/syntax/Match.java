package syntax;

public class Match {

	public Match() {}
	
	
	public void matchLexical(String syntaxLexical,String grammerLexical) {
		if(syntaxLexical.equals(grammerLexical)) {
		//	System.out.println(syntaxLexical + " matched !");
		}
		else {
			new Error(syntaxLexical);
		}
	}
	
	public void matchTag(int syntaxTag,int grammerTag , String syntax) {
		if(syntaxTag == grammerTag) {
		//	System.out.println(syntax + " matched !");
		}
		else {
			new Error(String.valueOf(syntax));
		}
	}
	
	public void throwError(String token) {
		new Error(token);
	}

}
