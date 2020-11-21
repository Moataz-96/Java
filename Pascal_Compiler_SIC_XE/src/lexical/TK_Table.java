package lexical;

import java.util.Hashtable;

public class TK_Table {
	
	private static TK_Table tb = null;
	private static Hashtable TokenTable = new Hashtable();
	private TK_Table() {}
	
	// Singeleton
	public static TK_Table getInstance() {
		if(tb == null){
			tb = new TK_Table();
			tb.initializeTable();
		}
		return tb;
	}
	public String getToken(int KEY) {
		return (String) TokenTable.get(KEY);
	}
	
	private void initializeTable() {
		TokenTable.put(TAG.ERROR, "ERROR !");
		TokenTable.put(TAG.PROGRAM, "PROGRAM");
		TokenTable.put(TAG.VAR,"VAR" );
		TokenTable.put(TAG.BEGIN,"BEGIN" );
		TokenTable.put(TAG.END,"END");
		TokenTable.put(TAG.END$,"END$" );	
		TokenTable.put(TAG.INTEGER,"INTEGER" );
		TokenTable.put(TAG.FOR,"FOR" );		
		TokenTable.put(TAG.READ,"READ" );
		TokenTable.put(TAG.WRITE,"WRITE" );		
		TokenTable.put(TAG.TO,"TO" );
		TokenTable.put(TAG.DO,"DO" );		
		TokenTable.put(TAG.SCOLON,";" );
		TokenTable.put(TAG.COLON, ":");
		TokenTable.put(TAG.COMMA,"," );
		TokenTable.put(TAG.COLONEQ,":=" );
		TokenTable.put(TAG.PLUS,"+" );
		TokenTable.put(TAG.MINUS,"-");
		TokenTable.put(TAG.MUL,"*" );
		TokenTable.put(TAG.DIV,"/" );
		TokenTable.put(TAG.LPAREN, "(");
		TokenTable.put(TAG.RPAREN,")" );
		TokenTable.put(TAG.ID,"ID" );
		TokenTable.put(TAG.INT,"INT" );
	}
}
