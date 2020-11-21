package syntax;

public class Error{
	private String message;
	public String token;
	public Error() {
		this.message = "Error Vertices duplicated or had a loop";
		try {
			throw new Syntax(this.message,new Exception());
		} catch (Syntax e) {
		//	e.printStackTrace();
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}
}
