package code_generator;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedList;

import lexical.Keyword;
import parser.Node;

public class Generator {
	private static Generator gen = null;
	private String assemblyCode = "";
	private int lineNumber = 1;
	private FileWriter file ;
	private Generator() {
	}
	public static Generator getInstance() {
		if(gen == null) {
			gen = new Generator();
		}
		return gen;	
	}
	public void generate(String snippet) {
		
			assemblyCode +=lineNumber + "\t\t\t\t" + snippet + "\n";
			lineNumber ++;
			
		
	}

	public void display() {
		System.out.println(assemblyCode);
	}
	public String getAssemblyCode() {
		return this.assemblyCode;
	}
	
}
