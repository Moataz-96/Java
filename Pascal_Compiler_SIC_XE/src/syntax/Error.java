package syntax;

public class Error{
	private String message;
	public String token;
	public Error(String token) {
		this.message = "Syntax Error ^" + token + " is not expected";
		try {
			throw new Syntax(this.message,new Exception());
		} catch (Syntax e) {
		//	e.printStackTrace();
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
}
