package parser;

import java.util.ArrayList;
import java.util.LinkedList;
import code_generator.Generator;
import lexical.Keyword;
import lexical.TAG;
import lexical.TK_Table;
import syntax.Match;

//@SuppressWarnings("unused")
public class Parsing {
	private boolean EXTADR = true;
	private LinkedList<String> Vars;
	private Generator generator;
	private int ListCount = 0;
	private ArrayList<String> tempVars;
	private ArrayList<String> Labels;
	private Match match;
	private TK_Table table;
	private LinkedList<Node> tree = new LinkedList<Node>();
	private ArrayList<Keyword> ReservedWords;
	private int itemIterator = 0;

	public Parsing(ArrayList<Keyword> ReservedWords) {
		this.ReservedWords = (ArrayList<Keyword>) ReservedWords.clone();
		table.getInstance();
		match = new Match();
		generator = Generator.getInstance();
		Vars = new LinkedList<String>();
		Labels = new ArrayList<String>();
		tempVars = new ArrayList<String>();
	}

	public void startParsing() {

		prog();
		generator.display();
		// display();
		if (itemIterator < ReservedWords.size()) {
			match.throwError(currentToken_lexema());
			System.exit(1);
		}

	}


	private void prog() {
		if (currentToken_lexema().equals("PROGRAM")) {
			tree.add(new Node("PROGRAM"));

		}
		match.matchLexical(currentToken_lexema(), "PROGRAM");
		itemIterator++;
		prog_name();

		if (currentToken_lexema().equals("VAR")) {
			tree.add(new Node("VAR"));
		}
		match.matchLexical(currentToken_lexema(), "VAR");
		itemIterator++;
		dec_list();

		if (currentToken_lexema().equals("BEGIN")) {
			tree.add(new Node("BEGIN"));
		}

		match.matchLexical(currentToken_lexema(), "BEGIN");
		itemIterator++;

		stmt_list();

		if (currentToken_lexema().equals("END$")) {
			tree.add(new Node("END$"));
		}

		match.matchLexical(currentToken_lexema(), "END$");
		generator.generate("\t\t\tLDL\t\t\tRETADR");
		generator.generate("\t\t\tRSUB");
		for (int i = 0; i < tempVars.size(); i++) {
			generator.generate(tempVars.get(i) + "\t\t\tRESW\t\t\t1");
		}
		generator.generate("\t\t\tEND");
		itemIterator++;

	}

	private void prog_name() {
		if (currentToken_tag() == TAG.ID) {
			tree.add(new Node(currentToken_lexema()));
		}

		match.matchTag(currentToken_tag(), TAG.ID, currentToken_lexema());
		generator.generate(currentToken_lexema() + "\t\t\tSTART\t\t\t0");
		generator.generate("\t\t\tEXTREF\t\t\tXREAD,XWRITE");
		generator.generate("\t\t\tSTL\t\t\tRETADR");
		generator.generate("\t\t\tJ\t\t\tEXADDR");
		generator.generate("RETADR\t\t\tRESW\t\t\t1");
		EXTADR = false;
		itemIterator++;

	}

	private void dec_list() {
		dec();
		dec_list_tail();
	}

	private void dec_list_tail() {
		if (currentToken_lexema().equals(";")) {
			tree.add(new Node(";"));
			match.matchLexical(currentToken_lexema(), ";");
			itemIterator++;
			dec_list();

		}
	}

	private void dec() {
		ListCount = 0;
		id_list();
		if (currentToken_lexema().equals(":")) {
			tree.add(new Node(":"));
		}
		match.matchLexical(currentToken_lexema(), ":");
		itemIterator++;
		type();

		for (int i = 0; i < ListCount; i++) {
			if (Vars.getFirst().length() > 6) {
				generator.generate(Vars.getFirst() + "\t\tRESW\t\t\t1");
				Vars.removeFirst();
			} else {
				generator.generate(Vars.getFirst() + "\t\t\tRESW\t\t\t1");
				Vars.removeFirst();
			}
		}
		ListCount = 0;
	}

	private void type() {
		if (currentToken_tag() == TAG.INTEGER) {
			tree.add(new Node(currentToken_lexema()));
		}
		match.matchTag(currentToken_tag(), TAG.INTEGER, currentToken_lexema());
		itemIterator++;
	}

	private void id_list() {
		if (currentToken_tag() == TAG.ID) {
			tree.add(new Node(currentToken_lexema()));
		}

		match.matchTag(currentToken_tag(), TAG.ID, currentToken_lexema());

		Vars.add(currentToken_lexema());
		ListCount++;

		itemIterator++;

		id_list_tail();

	}

	private void id_list_tail() {
		if (currentToken_lexema().equals(",")) {
			tree.add(new Node(","));
			match.matchLexical(currentToken_lexema(), ",");
			itemIterator++;
			id_list();
		}
	}

	private void stmt_list() {

		stmt();
		stmt_list_tail();
	}

	private void stmt_list_tail() {
		if (currentToken_lexema().equals(";")) {
			tree.add(new Node(";"));
			match.matchLexical(currentToken_lexema(), ";");
			itemIterator++;
			stmt_list();
		}
	}

	private void stmt() {
		if (currentToken_lexema().equals("READ")) {
			read();
			return;
		} else if (currentToken_lexema().equals("WRITE")) {
			write();
			return;
		} else if (currentToken_lexema().equals("FOR")) {
			for_loop();
			return;
		} else if (currentToken_tag() == TAG.ID) {
			assign();
			return;
		}

	}

	int countTempVariables = 1;

	private void assign() {

		Vars.clear();
		String assingString = "";
		if (currentToken_tag() == TAG.ID) {
			tree.add(new Node(currentToken_lexema()));
		}
		match.matchTag(currentToken_tag(), TAG.ID, currentToken_lexema());
		assingString = currentToken_lexema();
		itemIterator++;
		if (currentToken_lexema().equals(":=")) {
			tree.add(new Node(":="));
		}
		match.matchLexical(currentToken_lexema(), ":=");
		itemIterator++;
		Vars.clear();
		Labels.clear();
		exp();
		if((countFirstElement > 1 ) && (REGA != "")) {
		Vars.set(0, REGA);}
		GETA();
		String op;
		for (int i = 0; i < Labels.size(); i++) {
			op = (String) Labels.get(i);
			if (op.equals("*")) {
				Vars.removeFirst();
			} else if (op.equals("DIV")) {
				Vars.removeFirst();
			} else if (op.equals("+")) {
				if (checkDigit(Vars.getFirst())) {
					generator.generate("\t\t\tADD\t\t\t#" + Vars.getFirst());
				} else {
					generator.generate("\t\t\tADD\t\t\t" + Vars.getFirst());
				}
				Vars.removeFirst();
			} else if (op.equals("-")) {
				if (checkDigit(Vars.getFirst())) {
					generator.generate("\t\t\tSUB\t\t\t#" + Vars.getFirst());
				} else {
					generator.generate("\t\t\tSUB\t\t\t" + Vars.getFirst());
				}
				Vars.removeFirst();
			} 
		}
		Labels.clear();
		Vars.clear();
		generator.generate("\t\t\tSTA\t\t\t" + assingString);
	}

	private void exp() {
		term();
		exp_tail();
	}

	
	private void exp_tail() {
		if (currentToken_lexema().equals("+")) {
			tree.add(new Node("+"));
			match.matchLexical(currentToken_lexema(), "+");
			Labels.add("+");
			itemIterator++;
			
			if((countVars > 1) && (index != 0 )) {
				Vars.set(index-1, LA);
			}
			countVars = 0;
			Vars.set(Vars.size()-1, Vars.getLast());
			if((countFirstElement > 1) && (flag == true)) {
				REGA = Vars.getLast();
				flag = false;
			}

			exp();
		} else if (currentToken_lexema().equals("-")) {
			tree.add(new Node("-"));
			match.matchLexical(currentToken_lexema(), "-");
			Labels.add("-");
			itemIterator++;	
			
			if((countVars > 1) && (index != 0 )) {
				Vars.set(index-1, LA);
			}
			countVars = 0;
			Vars.set(Vars.size()-1, Vars.getLast());
			if((countFirstElement > 1) && (flag == true)) {
				REGA = Vars.getLast();
				flag = false;
			}
			

			exp();

		}

	}

	private void term() {
		factor();
		term_tail();
	}
	int index = 0;
	String LA = "";
	int countVars = 0;
	int countFirstElement = 0;
	boolean flag = true;
	private void term_tail() {
		if (currentToken_lexema().equals("*")) {
			tree.add(new Node("*"));
			match.matchLexical(currentToken_lexema(), "*");
			Labels.add("*");
			itemIterator++;
			

			Vars.addFirst(Vars.getLast());
			Vars.removeLast();
			GETA();
			
			if (checkDigit(currentToken_lexema())) {
				generator.generate("\t\t\tMUL\t\t\t#" + currentToken_lexema());
			} else {
				generator.generate("\t\t\tMUL\t\t\t" + currentToken_lexema());
			}
			generator.generate("\t\t\tSTA\t\t\tT" + countTempVariables);
			ReservedWords.get(itemIterator).setLexema("T"+countTempVariables);

			Vars.addLast("T" + countTempVariables);	
			LA = Vars.getLast();
			tempVars.add("T" + countTempVariables);
			countTempVariables++;
			if(flag == true) {
				countFirstElement ++;} 
			
			if(countVars == 1) {
				index = Vars.size()-1;
			}
			countVars ++;
			term();

		} else if (currentToken_lexema().equals("DIV")) {
			tree.add(new Node("DIV"));
			match.matchLexical(currentToken_lexema(), "DIV");
			Labels.add("DIV");
			itemIterator++;
			
	
			Vars.addFirst(Vars.getLast());
			Vars.removeLast();
			GETA();
		
			if (checkDigit(currentToken_lexema())) {
				generator.generate("\t\t\tDIV\t\t\t#" + currentToken_lexema());
			} else {
				generator.generate("\t\t\tDIV\t\t\t" +currentToken_lexema());
			}
			generator.generate("\t\t\tSTA\t\t\tT" + countTempVariables);
			ReservedWords.get(itemIterator).setLexema("T"+countTempVariables);

			
			Vars.addLast("T" + countTempVariables);
			LA = Vars.getLast();
			tempVars.add("T" + countTempVariables);
			countTempVariables++;
			
			if(flag == true) {
				countFirstElement ++;} 

			
			if(countVars == 1) {
				index = Vars.size()-1;
			}
			countVars ++;
			term();
		}

	}
	String REGA = "";
	boolean flagmmm = true;
	private void factor() {
		if (currentToken_tag() == TAG.ID) {
			tree.add(new Node(currentToken_lexema()));
			match.matchTag(currentToken_tag(), TAG.ID, currentToken_lexema());
		
			Vars.add(currentToken_lexema());
		
			itemIterator++;
		} else if (currentToken_lexema().equals("INT")) {
			tree.add(new Node("INT"));
			match.matchLexical(currentToken_lexema(), "INT");
			itemIterator++;
		} else if (currentToken_lexema().equals("(")) {
			tree.add(new Node("("));
			match.matchLexical(currentToken_lexema(), "(");
			itemIterator++;
			exp();
			if (currentToken_lexema().equals(")")) {
				tree.add(new Node(")"));
				match.matchLexical(currentToken_lexema(), ")");
				itemIterator++;
			}
		} else {
			match.throwError(currentToken_lexema());
		}
	}

	private void read() {
		ListCount = 0;
		if (currentToken_lexema().equals("READ")) {
			tree.add(new Node("READ"));

		}
		match.matchLexical(currentToken_lexema(), "READ");
		itemIterator++;
		if (currentToken_lexema().equals("(")) {
			tree.add(new Node("("));

		}
		match.matchLexical(currentToken_lexema(), "(");
		itemIterator++;
		id_list();
		if (currentToken_lexema().equals(")")) {
			tree.add(new Node(")"));

		}
		match.matchLexical(currentToken_lexema(), ")");
		itemIterator++;

		generator.generate("\t\t\t+JSUB\t\t\tXREAD");
		generator.generate("\t\t\tWORD\t\t\t" + ListCount);
		for (int i = 0; i < ListCount; i++) {
			generator.generate("\t\t\tWORD\t\t\t" + Vars.getLast());
			Vars.removeLast();
		}
		ListCount = 0;
	}

	private void write() {
		ListCount = 0;
		if (currentToken_lexema().equals("WRITE")) {
			tree.add(new Node("WRITE"));

		}
		match.matchLexical(currentToken_lexema(), "WRITE");
		itemIterator++;
		if (currentToken_lexema().equals("(")) {
			tree.add(new Node("("));

		}
		match.matchLexical(currentToken_lexema(), "(");
		itemIterator++;
		id_list();
		if (currentToken_lexema().equals(")")) {
			tree.add(new Node(")"));

		}
		match.matchLexical(currentToken_lexema(), ")");
		itemIterator++;

		generator.generate("\t\t\t+JSUB\t\t\tXWRITE");
		generator.generate("\t\t\tWORD\t\t\t" + ListCount);
		for (int i = 0; i < ListCount; i++) {
			generator.generate("\t\t\tWORD\t\t\t" + Vars.getLast());
			Vars.removeLast();
		}
		ListCount = 0;
	}

	private int loopsCounter = 1;

	private void for_loop() {
		Vars.clear();
		if (currentToken_lexema().equals("FOR")) {
			tree.add(new Node("FOR"));

		}
		match.matchLexical(currentToken_lexema(), "FOR");
		itemIterator++;
		index_exp();
		if (currentToken_lexema().equals("DO")) {
			tree.add(new Node("DO"));

		}
		match.matchLexical(currentToken_lexema(), "DO");
		itemIterator++;
		body();
		generator.generate("\t\t\tLDA\t\t\t" + tempID);
		generator.generate("\t\t\tADD\t\t\t#1");
		generator.generate("\t\t\tJ\t\t\tL" + loopsCounter);
		loopsCounter++;
	}

	private String tempID = "";

	private void index_exp() {

		if (currentToken_tag() == TAG.ID) {
			tree.add(new Node(currentToken_lexema()));
			tempID = currentToken_lexema();
		}
		match.matchTag(currentToken_tag(), TAG.ID, currentToken_lexema());
		itemIterator++;
		if (currentToken_lexema().equals(":=")) {
			tree.add(new Node(":="));

		}
		match.matchLexical(currentToken_lexema(), ":=");
		itemIterator++;
		exp();
		Vars.addLast(tempID);
		GETA();
		generator.generate("{L" + loopsCounter + "}\t\t\tSTA\t\t\t" + Vars.getLast());
		Vars.removeLast();
		if (currentToken_lexema().equals("TO")) {
			tree.add(new Node("TO"));

		}
		match.matchLexical(currentToken_lexema(), "TO");
		itemIterator++;
		exp();
		if (checkDigit(Vars.getLast())) {
			generator.generate("\t\t\tCOMP\t\t\t#" + Vars.getLast());
		} else {
			generator.generate("\t\t\tCOMP\t\t\t" + Vars.getLast());
		}
		Vars.removeLast();
		Vars.clear();

		generator.generate("\t\t\tJGT\t\t\t{L" + (loopsCounter + 1) + "}");
	}

	private void body() {
		if (currentToken_lexema().equals("BEGIN")) {
			tree.add(new Node("BEGIN"));

			match.matchLexical(currentToken_lexema(), "BEGIN");
			itemIterator++;
			stmt_list();
			if (currentToken_lexema().equals("END")) {
				tree.add(new Node("END"));

				match.matchLexical(currentToken_lexema(), "END");
				itemIterator++;
			}
		} else {
			stmt();
		}

	}


	private void GETA() {

		if (checkDigit(Vars.getFirst())) {
			if (loopsCounter % 2 == 0) {
				generator.generate("{L" + loopsCounter + "}\t\t\tLDA\t\t\t#" + Vars.getFirst());
				loopsCounter++;
			} else {
				if (EXTADR == false) {
					generator.generate("{EXADDR}\t\tLDA\t\t\t#" + Vars.getFirst());
					EXTADR = true;
				} else
					generator.generate("\t\t\tLDA\t\t\t#" + Vars.getFirst());
			}
		} else {
			if (loopsCounter % 2 == 0) {
				generator.generate("{L" + loopsCounter + "}\t\t\tLDA\t\t\t" + Vars.getFirst());
				loopsCounter++;
			} else {
				if (EXTADR == false) {
					generator.generate("{EXADDR}\t\tLDA\t\t\t" + Vars.getFirst());
					EXTADR = true;
				} else
					generator.generate("\t\t\tLDA\t\t\t" + Vars.getFirst());
			}
		}
		Vars.removeFirst();

	}

	private String currentToken_lexema() {
		if (itemIterator == ReservedWords.size()) {
			match.throwError(ReservedWords.get(tree.size() - 1).getLexema());
			System.exit(1);
		}

		String currentToken = ReservedWords.get(itemIterator).getLexema();
		return currentToken;
	}

	private int currentToken_tag() {
		if (itemIterator == ReservedWords.size()) {
			match.throwError(ReservedWords.get(ReservedWords.size() - 1).getLexema());
			System.exit(1);
		}

		int currentToken = ReservedWords.get(itemIterator).getTAG();
		return currentToken;
	}

	public boolean checkDigit(String word) {

		for (int i = 0; i < word.length(); i++) {
			if (!Character.isDigit(word.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	public void display() {
		System.out.println("-------------------------------------------");
		System.out.println("-----------------Parse tree--------------------------");
		System.out.println("-------------------------------------------");
		for (int i = 0; i < tree.size(); i++) {
			System.out.println(tree.get(i).getLexema());
		}
	}

}
