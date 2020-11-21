package code_generator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Generate_ASM {
	private  RandomAccessFile file;
	public Generate_ASM(String fileName) {
		try {
			String fileCreated = fileName.substring(0, fileName.indexOf(".")) +".asm";
			this.file = new RandomAccessFile(fileCreated,"rw");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createAssemblyFile(String AssemblyCode) {
		try {
			this.file.writeUTF(AssemblyCode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
