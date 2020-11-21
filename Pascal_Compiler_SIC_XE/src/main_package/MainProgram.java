package main_package;
import java.io.RandomAccessFile;
import java.util.Scanner;

import code_generator.Generate_ASM;
import code_generator.Generator;
import lexical.Lexical;
import parser.Parsing;

public class MainProgram {
	
	static RandomAccessFile  file ;
	static Parsing parse;
	
	public static void main(String[] args) {
	//	Scanner s = new Scanner(System.in);
	//	String input = s.nextLine();
		String input = "InputTest.txt";
		try {
		file = new RandomAccessFile(input,"r");

		}catch(Exception io) {
			System.out.println("Error Exception Main");
		}
		
		Lexical l = new Lexical(file);

		
		try {
			 parse = new Parsing(l.getToken());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("error here");
		}
		//l.displayTable();
		parse.startParsing();
		Generator generator = Generator.getInstance();
		Generate_ASM gen = new Generate_ASM(input);
		String AssemblyCode = generator.getAssemblyCode();
		gen.createAssemblyFile(AssemblyCode);
	}

}
