package syntax;

public class Syntax extends Exception {

	public Syntax() {
		super();
	}

	public Syntax(String message) {
		super(message);
	}

	public Syntax(String message, Throwable cause) {
		super(message, cause);
	}

	public Syntax(Throwable cause) {
		super(cause);
	}
}
