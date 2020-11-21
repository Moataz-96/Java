package lexical;

import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Lexical {
	private RandomAccessFile file;
	private ArrayList<Keyword> ReservedWords = new ArrayList<Keyword>();
	private TK_Table table;
	private int file_iter = 0;

	private void reserve(int key, String value) {
		ReservedWords.add(new Keyword(key, value));
	}

	public Lexical(RandomAccessFile file) {
		this.file = file;
	}

	public ArrayList getToken() throws Exception {
		String Identifier = "";
		int character = 0;
		table = TK_Table.getInstance();
		char ch = ' ';
		boolean flag = false;
		while ((character = file.read()) != -1) {
			ch = (char) character;
			file_iter++;
			while (Character.isSpaceChar(ch) || ch == '\n' || (int) ch == 13) {
				ch = (char) file.read();
				file_iter++;
				file.seek(file_iter);
			}
			file.seek(file_iter);
			if (ch == table.getToken(TAG.LPAREN).toCharArray()[0]) {
				reserve(TAG.LPAREN, table.getToken(TAG.LPAREN));
			} else if (ch == table.getToken(TAG.RPAREN).toCharArray()[0]) {
				reserve(TAG.RPAREN, table.getToken(TAG.RPAREN));
			} else if (ch == table.getToken(TAG.SCOLON).toCharArray()[0]) {
				reserve(TAG.SCOLON, table.getToken(TAG.SCOLON));
			} else if (ch == table.getToken(TAG.COLON).toCharArray()[0]) {

				if ((ch = (char) file.read()) == '=') {
					reserve(TAG.COLONEQ, table.getToken(TAG.COLONEQ));
				} else {
					reserve(TAG.COLON, table.getToken(TAG.COLON));
				}
				file_iter++;
				file.seek(file_iter);
			} else if (ch == table.getToken(TAG.PLUS).toCharArray()[0]) {
				reserve(TAG.PLUS, table.getToken(TAG.PLUS));
			} else if (ch == table.getToken(TAG.MINUS).toCharArray()[0]) {
				reserve(TAG.MINUS, table.getToken(TAG.MINUS));
			} else if (ch == table.getToken(TAG.MUL).toCharArray()[0]) {
				reserve(TAG.MUL, table.getToken(TAG.MUL));
			} else if (ch == table.getToken(TAG.DIV).toCharArray()[0]) {
				reserve(TAG.DIV, table.getToken(TAG.DIV));
			} else if (ch == table.getToken(TAG.COMMA).toCharArray()[0]) {
				reserve(TAG.COMMA, table.getToken(TAG.COMMA));
			}

			else if (Character.isLetter(ch)) {
				Identifier += ch;
				ch = (char) file.read();
				file_iter++;
				while ((Character.isLetterOrDigit(ch))) {
					Identifier += ch;
					ch = (char) file.read();
					file_iter++;
				}
				file_iter--;
				file.seek(file_iter);
				String endCheck = Identifier + String.valueOf(ch);
				if (endCheck.equals("END.")) {
					file_iter++;
					reserve(TAG.END$, table.getToken(TAG.END$));
					Identifier = "";
					continue;
				}
				checkReserved(Identifier);
				Identifier = "";
			}

			else if (Character.isDigit(ch)) {
				Identifier += ch;
				ch = (char) file.read();
				file_iter++;
				while (Character.isDigit(ch)) {
					Identifier += ch;
					ch = (char) file.read();
					file_iter++;
				}
				file_iter--;
				file.seek(file_iter);
				reserve(TAG.ID, Identifier);
				Identifier = "";

			}

			// else if((int) ch != 13) { // if ch != \n
			// reserve(TAG.ERROR,table.getToken(TAG.ERROR)); }
		}
		// file.seek(file_iter);
		// while((int) ch == 13 || ch == ' ' ) {
		// ch = (char) file.read();
		// if((int)ch == -1)
		// return;}
		// reserve(TAG.ERROR,table.getToken(TAG.ERROR));
		
		return ReservedWords;
	}

	public void checkReserved(String tokenTAG) {
		switch (tokenTAG) {
		case "PROGRAM":
			reserve(TAG.PROGRAM, table.getToken(TAG.PROGRAM));
			break;
		case "VAR":
			reserve(TAG.VAR, table.getToken(TAG.VAR));
			break;
		case "BEGIN":
			reserve(TAG.BEGIN, table.getToken(TAG.BEGIN));
			break;
		case "END":
			reserve(TAG.END, table.getToken(TAG.END));
			break;
		case "INTEGER":
			reserve(TAG.INTEGER, table.getToken(TAG.INTEGER));
			break;
		case "FOR":
			reserve(TAG.FOR, table.getToken(TAG.FOR));
			break;
		case "READ":
			reserve(TAG.READ, table.getToken(TAG.READ));
			break;
		case "WRITE":
			reserve(TAG.WRITE, table.getToken(TAG.WRITE));
			break;
		case "TO":
			reserve(TAG.TO, table.getToken(TAG.TO));
			break;
		case "DO":
			reserve(TAG.DO, table.getToken(TAG.DO));
			break;
		case "INT":
			reserve(TAG.INT, table.getToken(TAG.INT));
			break;
		default:
			reserve(TAG.ID, tokenTAG);
			break;

		}
	}

	public void displayTable() {
		int i = 0;
		for (i = 0; i < ReservedWords.size(); i++) {
			System.out.println("Line :" + ReservedWords.get(i).getTAG() + " ## "
					+ String.valueOf(ReservedWords.get(i).getLexema()));
		}
		System.out.println(i);
	}
}
