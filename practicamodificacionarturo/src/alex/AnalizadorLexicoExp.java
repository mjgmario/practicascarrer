package alex;
import errors.GestionErroresExp;


public class AnalizadorLexicoExp implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 65536;
	private final int YY_EOF = 65537;

  private ALexOperations ops;
  private GestionErroresExp errores;
  private int charline = 0;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yychar +1 - charline;}
  private void refrescaColumna() {
    charline = yychar + yytext().length();
  }public void fijaGestionErrores(GestionErroresExp errores) {
   this.errores = errores;
  }
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public AnalizadorLexicoExp (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public AnalizadorLexicoExp (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private AnalizadorLexicoExp () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;

  ops = new ALexOperations(this);
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NO_ANCHOR,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NO_ANCHOR,
		/* 130 */ YY_NO_ANCHOR,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NO_ANCHOR,
		/* 133 */ YY_NO_ANCHOR,
		/* 134 */ YY_NO_ANCHOR,
		/* 135 */ YY_NO_ANCHOR,
		/* 136 */ YY_NO_ANCHOR,
		/* 137 */ YY_NO_ANCHOR,
		/* 138 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"4:8,1:2,2,4:2,5,4:18,1,34,4,3,4:2,15,4,29,30,9,8,16,6,43,10,51,50:9,42,31,3" +
"3,32,7,4:2,48:26,46,4,47,4:3,20,27,19,13,22,17,45,24,25,49,28,26,11,39,12,4" +
"1,49,18,21,37,38,44,23,49,40,49,35,14,36,4:65410,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,139,
"0,1:3,2,1,3,4,1:3,5,6,1:4,7,8,9,1:6,10,11,1:4,12,13,1:3,14,12:6,15,12:17,16" +
",17,18,19,12,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40" +
",41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65" +
",66,67,12,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89" +
",90")[0];

	private int yy_nxt[][] = unpackFromString(91,52,
"1,2,3,4,5,3,6,7,8,9,10,11,115,125,62,12,13,129,130,131,115,132,133,134,115," +
"63,115,135,115,14,15,16,17,18,19,20,21,136,115,137,115:2,22,23,138,115,24,2" +
"5,26,115,27,64,-1:53,4,-1,4:49,-1:7,28,-1:76,29,-1:30,115,65,115,-1:3,115:1" +
"2,-1:8,115:5,-1:2,115:2,-1:2,66,115,66:2,-1:15,31,-1:68,34,-1:51,35,-1:51,3" +
"6,-1:30,26:3,-1:3,26:12,-1:8,26:5,-1:2,26:2,-1:2,26:4,-1:43,37,-1:6,27:2,-1" +
":11,115:3,-1:3,115:12,-1:8,115:5,-1:2,115:2,-1:2,66,115,66:2,-1:11,115:3,-1" +
":3,115:12,-1:8,40,115:4,-1:2,115:2,-1:2,66,115,66:2,-1:50,37:2,-1:11,115:3," +
"-1:3,115:8,110,115:3,-1:8,115:5,-1:2,115:2,-1:2,66,115,66:2,-1:14,30,-1:48," +
"115:3,-1:3,32,115:11,-1:8,115:2,33,115:2,-1:2,115:2,-1:2,66,115,66:2,-1:43," +
"37,-1:19,115:2,38,-1:3,115:12,-1:8,115:5,-1:2,115:2,-1:2,66,115,66:2,-1:11," +
"115:3,-1:3,115:12,-1:8,115,81,115:3,-1:2,115:2,-1:2,66,115,66:2,-1:11,115:3" +
",-1:3,119,115:11,-1:8,115:5,-1:2,115:2,-1:2,66,115,66:2,-1:11,115:3,-1:3,11" +
"5,39,115:10,-1:8,115:5,-1:2,115:2,-1:2,66,115,66:2,-1:11,115:3,-1:3,115:9,1" +
"27,115:2,-1:8,115:5,-1:2,115:2,-1:2,66,115,66:2,-1:11,115:3,-1:3,115:12,-1:" +
"8,82,115:4,-1:2,115:2,-1:2,66,115,66:2,-1:11,115:3,-1:3,115:4,83,115:7,-1:8" +
",115:5,-1:2,115:2,-1:2,66,115,66:2,-1:11,115:3,-1:3,115:3,84,115:8,-1:8,115" +
":5,-1:2,115:2,-1:2,66,115,66:2,-1:11,115:3,-1:3,115:8,85,115:3,-1:8,115:5,-" +
"1:2,115:2,-1:2,66,115,66:2,-1:11,115:3,-1:3,115,86,115:10,-1:8,115:5,-1:2,1" +
"15:2,-1:2,66,115,66:2,-1:11,115,90,115,-1:3,115:12,-1:8,115:5,-1:2,115:2,-1" +
":2,66,115,66:2,-1:11,115:3,-1:3,115:5,91,115:6,-1:8,115:5,-1:2,115:2,-1:2,6" +
"6,115,66:2,-1:11,115:3,-1:3,115:12,-1:8,115:4,93,-1:2,115:2,-1:2,66,115,66:" +
"2,-1:11,115:3,-1:3,115:6,41,115:5,-1:8,115:5,-1:2,115:2,-1:2,66,115,66:2,-1" +
":11,115:3,-1:3,115:2,122,115:9,-1:8,115:5,-1:2,115:2,-1:2,66,115,66:2,-1:11" +
",115:3,-1:3,115:10,123,115,-1:8,115:5,-1:2,115:2,-1:2,66,115,66:2,-1:11,115" +
":3,-1:3,115:12,-1:8,115,97,115:3,-1:2,115:2,-1:2,66,115,66:2,-1:11,115:3,-1" +
":3,115:5,42,115:6,-1:8,115:5,-1:2,115:2,-1:2,66,115,66:2,-1:11,115:3,-1:3,1" +
"15,43,115:10,-1:8,115:5,-1:2,115:2,-1:2,66,115,66:2,-1:11,115:3,-1:3,115:12" +
",-1:8,98,115:4,-1:2,115:2,-1:2,66,115,66:2,-1:11,115:3,-1:3,115:8,99,115:3," +
"-1:8,115,124,115:3,-1:2,115:2,-1:2,66,115,66:2,-1:11,115:3,-1:3,115:5,44,11" +
"5:6,-1:8,115:5,-1:2,115:2,-1:2,66,115,66:2,-1:11,45,115:2,-1:3,115:12,-1:8," +
"115:5,-1:2,115:2,-1:2,66,115,66:2,-1:11,115:3,-1:3,115:9,100,115:2,-1:8,115" +
":5,-1:2,115:2,-1:2,66,115,66:2,-1:11,115:3,-1:3,115:9,46,115:2,-1:8,115:5,-" +
"1:2,115:2,-1:2,66,115,66:2,-1:11,115:3,-1:3,115:3,101,115:8,-1:8,115:5,-1:2" +
",115:2,-1:2,66,115,66:2,-1:11,115:3,-1:3,115:5,47,115:6,-1:8,115:5,-1:2,115" +
":2,-1:2,66,115,66:2,-1:11,115:3,-1:3,115:5,102,115:6,-1:8,115:5,-1:2,115:2," +
"-1:2,66,115,66:2,-1:11,115:3,-1:3,115:9,48,115:2,-1:8,115:5,-1:2,115:2,-1:2" +
",66,115,66:2,-1:11,115:2,49,-1:3,115:12,-1:8,115:5,-1:2,115:2,-1:2,66,115,6" +
"6:2,-1:11,115:3,-1:3,115:5,50,115:6,-1:8,115:5,-1:2,115:2,-1:2,66,115,66:2," +
"-1:11,115:3,-1:3,115,106,115:10,-1:8,115:5,-1:2,115:2,-1:2,66,115,66:2,-1:1" +
"1,115:3,-1:3,115:2,107,115:9,-1:8,115:5,-1:2,115:2,-1:2,66,115,66:2,-1:11,1" +
"15:3,-1:3,115:12,-1:8,115:2,108,115:2,-1:2,115:2,-1:2,66,115,66:2,-1:11,115" +
":3,-1:3,115:5,51,115:6,-1:8,115:5,-1:2,115:2,-1:2,66,115,66:2,-1:11,115:3,-" +
"1:3,115:11,52,-1:8,115:5,-1:2,115:2,-1:2,66,115,66:2,-1:11,115:2,111,-1:3,1" +
"15:12,-1:8,115:5,-1:2,115:2,-1:2,66,115,66:2,-1:11,115,112,115,-1:3,115:12," +
"-1:8,115:5,-1:2,115:2,-1:2,66,115,66:2,-1:11,115:3,-1:3,115:5,53,115:6,-1:8" +
",115:5,-1:2,115:2,-1:2,66,115,66:2,-1:11,115:3,-1:3,115:9,113,115:2,-1:8,11" +
"5:5,-1:2,115:2,-1:2,66,115,66:2,-1:11,115:3,-1:3,115:12,-1:8,115:2,54,115:2" +
",-1:2,115:2,-1:2,66,115,66:2,-1:11,115:3,-1:3,115:7,55,115:4,-1:8,115:5,-1:" +
"2,115:2,-1:2,66,115,66:2,-1:11,115:3,-1:3,115:12,-1:8,115:5,-1:2,115,56,-1:" +
"2,66,115,66:2,-1:11,115:3,-1:3,115:12,-1:8,57,115:4,-1:2,115:2,-1:2,66,115," +
"66:2,-1:11,115:3,-1:3,58,115:11,-1:8,115:5,-1:2,115:2,-1:2,66,115,66:2,-1:1" +
"1,115:3,-1:3,115:5,114,115:6,-1:8,115:5,-1:2,115:2,-1:2,66,115,66:2,-1:11,1" +
"15:3,-1:3,115,59,115:10,-1:8,115:5,-1:2,115:2,-1:2,66,115,66:2,-1:11,115:3," +
"-1:3,115:12,-1:8,60,115:4,-1:2,115:2,-1:2,66,115,66:2,-1:11,115:3,-1:3,61,1" +
"15:11,-1:8,115:5,-1:2,115:2,-1:2,66,115,66:2,-1:11,115:3,-1:3,115:12,-1:8,1" +
"15,88,115:3,-1:2,115:2,-1:2,66,115,66:2,-1:11,115:3,-1:3,115:9,94,115:2,-1:" +
"8,115:5,-1:2,115:2,-1:2,66,115,66:2,-1:11,115:3,-1:3,115:4,87,115:7,-1:8,11" +
"5:5,-1:2,115:2,-1:2,66,115,66:2,-1:11,115:3,-1:3,115:3,121,115:8,-1:8,115:5" +
",-1:2,115:2,-1:2,66,115,66:2,-1:11,115:3,-1:3,115:8,89,115:3,-1:8,115:5,-1:" +
"2,115:2,-1:2,66,115,66:2,-1:11,115:3,-1:3,115:12,-1:8,115,105,115:3,-1:2,11" +
"5:2,-1:2,66,115,66:2,-1:11,115:3,-1:3,115:12,-1:8,103,115:4,-1:2,115:2,-1:2" +
",66,115,66:2,-1:11,115:3,-1:3,115:9,104,115:2,-1:8,115:5,-1:2,115:2,-1:2,66" +
",115,66:2,-1:11,115:3,-1:3,115:2,109,115:9,-1:8,115:5,-1:2,115:2,-1:2,66,11" +
"5,66:2,-1:11,115,67,115,-1:3,115:5,68,115:6,-1:8,115:5,-1:2,115:2,-1:2,66,1" +
"15,66:2,-1:11,115:3,-1:3,115:12,-1:8,115,92,115:3,-1:2,115:2,-1:2,66,115,66" +
":2,-1:11,115:3,-1:3,115:4,96,115:7,-1:8,115:5,-1:2,115:2,-1:2,66,115,66:2,-" +
"1:11,115:3,-1:3,115:8,95,115:3,-1:8,115:5,-1:2,115:2,-1:2,66,115,66:2,-1:11" +
",115,69,115,-1:3,115:3,70,115:8,-1:8,115:5,-1:2,115:2,-1:2,66,115,66:2,-1:1" +
"1,115:3,-1:3,115:5,71,115:6,-1:8,115:5,-1:2,115:2,-1:2,66,115,66:2,-1:11,11" +
"5:3,-1:3,115:3,72,115:3,73,115:4,-1:8,115:5,-1:2,115:2,-1:2,66,115,66:2,-1:" +
"11,115:3,-1:3,115:6,74,115:5,-1:8,75,115:4,-1:2,115:2,-1:2,66,115,66:2,-1:1" +
"1,115:3,-1:3,115:9,118,115:2,-1:8,115:2,116,115:2,-1:2,115:2,-1:2,66,115,66" +
":2,-1:11,115:3,-1:3,115:7,120,115:4,-1:8,115:5,-1:2,115:2,-1:2,66,115,66:2," +
"-1:11,115,76,115,-1:3,115,77,115:10,-1:8,115:5,-1:2,115:2,-1:2,66,115,66:2," +
"-1:11,115:3,-1:3,115,126,115:10,-1:8,115:3,78,115,-1:2,115:2,-1:2,66,115,66" +
":2,-1:11,115:3,-1:3,115:5,79,115:6,-1:8,115,117,115:3,-1:2,115:2,-1:2,66,11" +
"5,66:2,-1:11,115,128,115,-1:3,115:5,80,115:6,-1:8,115:5,-1:2,115:2,-1:2,66," +
"115,66:2");

	public UnidadLexica next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

  return ops.unidadEof();
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{}
					case -3:
						break;
					case 3:
						{refrescaColumna();}
					case -4:
						break;
					case 4:
						{refrescaColumna();}
					case -5:
						break;
					case 5:
						{ops.error();}
					case -6:
						break;
					case 6:
						{return ops.unidadResta();}
					case -7:
						break;
					case 7:
						{return ops.unidadMayor();}
					case -8:
						break;
					case 8:
						{return ops.unidadSuma();}
					case -9:
						break;
					case 9:
						{return ops.unidadMul();}
					case -10:
						break;
					case 10:
						{return ops.unidadDiv();}
					case -11:
						break;
					case 11:
						{return ops.unidadNombrev();}
					case -12:
						break;
					case 12:
						{return ops.unidadPorreferencia();}
					case -13:
						break;
					case 13:
						{return ops.unidadComa();}
					case -14:
						break;
					case 14:
						{return ops.unidadPAp();}
					case -15:
						break;
					case 15:
						{return ops.unidadPCierre();}
					case -16:
						break;
					case 16:
						{return ops.unidadPuntoycoma();}
					case -17:
						break;
					case 17:
						{return ops.unidadIgual();}
					case -18:
						break;
					case 18:
						{return ops.unidadMenor();}
					case -19:
						break;
					case 19:
						{return ops.unidadNot();}
					case -20:
						break;
					case 20:
						{return ops.unidadApertllaves();}
					case -21:
						break;
					case 21:
						{return ops.unidadCierrellaves();}
					case -22:
						break;
					case 22:
						{return ops.unidadDospuntos();}
					case -23:
						break;
					case 23:
						{return ops.unidadPunto();}
					case -24:
						break;
					case 24:
						{return ops.unidadAcorch();}
					case -25:
						break;
					case 25:
						{return ops.unidadCcorch();}
					case -26:
						break;
					case 26:
						{return ops.unidadNombret();}
					case -27:
						break;
					case 27:
						{return ops.unidadEnt();}
					case -28:
						break;
					case 28:
						{return ops.unidadFlecha();}
					case -29:
						break;
					case 29:
						{return ops.unidadMayorigual();}
					case -30:
						break;
					case 30:
						{return ops.unidadOr();}
					case -31:
						break;
					case 31:
						{return ops.unidadAnd();}
					case -32:
						break;
					case 32:
						{return ops.unidadIf();}
					case -33:
						break;
					case 33:
						{return ops.unidadIn();}
					case -34:
						break;
					case 34:
						{return ops.unidadIgualigual();}
					case -35:
						break;
					case 35:
						{return ops.unidadMenorigual();}
					case -36:
						break;
					case 36:
						{return ops.unidadDistinto();}
					case -37:
						break;
					case 37:
						{return ops.unidadReal();}
					case -38:
						break;
					case 38:
						{return ops.unidadMod();}
					case -39:
						break;
					case 39:
						{return ops.unidadFor();}
					case -40:
						break;
					case 40:
						{return ops.unidadInt();}
					case -41:
						break;
					case 41:
						{return ops.unidadNew();}
					case -42:
						break;
					case 42:
						{return ops.unidadCase();}
					case -43:
						break;
					case 43:
						{return ops.unidadChar();}
					case -44:
						break;
					case 44:
						{return ops.unidadElse();}
					case -45:
						break;
					case 45:
						{return ops.unidadEnum();}
					case -46:
						break;
					case 46:
						{return ops.unidadBool();}
					case -47:
						break;
					case 47:
						{return ops.unidadTrue();}
					case -48:
						break;
					case 48:
						{return ops.unidadNull();}
					case -49:
						break;
					case 49:
						{return ops.unidadVoid();}
					case -50:
						break;
					case 50:
						{return ops.unidadFalse();}
					case -51:
						break;
					case 51:
						{return ops.unidadWhile();}
					case -52:
						break;
					case 52:
						{return ops.unidadBreak();}
					case -53:
						break;
					case 53:
						{return ops.unidadDouble();}
					case -54:
						break;
					case 54:
						{return ops.unidadReturn();}
					case -55:
						break;
					case 55:
						{return ops.unidadSwitch();}
					case -56:
						break;
					case 56:
						{return ops.unidadString();}
					case -57:
						break;
					case 57:
						{return ops.unidadStruct();}
					case -58:
						break;
					case 58:
						{return ops.unidadElseif();}
					case -59:
						break;
					case 59:
						{return ops.unidadVector();}
					case -60:
						break;
					case 60:
						{return ops.unidadDefault();}
					case -61:
						break;
					case 61:
						{return ops.unidadTypedef();}
					case -62:
						break;
					case 62:
						{ops.error();}
					case -63:
						break;
					case 63:
						{return ops.unidadNombrev();}
					case -64:
						break;
					case 64:
						{return ops.unidadEnt();}
					case -65:
						break;
					case 65:
						{return ops.unidadNombrev();}
					case -66:
						break;
					case 66:
						{return ops.unidadNombrev();}
					case -67:
						break;
					case 67:
						{return ops.unidadNombrev();}
					case -68:
						break;
					case 68:
						{return ops.unidadNombrev();}
					case -69:
						break;
					case 69:
						{return ops.unidadNombrev();}
					case -70:
						break;
					case 70:
						{return ops.unidadNombrev();}
					case -71:
						break;
					case 71:
						{return ops.unidadNombrev();}
					case -72:
						break;
					case 72:
						{return ops.unidadNombrev();}
					case -73:
						break;
					case 73:
						{return ops.unidadNombrev();}
					case -74:
						break;
					case 74:
						{return ops.unidadNombrev();}
					case -75:
						break;
					case 75:
						{return ops.unidadNombrev();}
					case -76:
						break;
					case 76:
						{return ops.unidadNombrev();}
					case -77:
						break;
					case 77:
						{return ops.unidadNombrev();}
					case -78:
						break;
					case 78:
						{return ops.unidadNombrev();}
					case -79:
						break;
					case 79:
						{return ops.unidadNombrev();}
					case -80:
						break;
					case 80:
						{return ops.unidadNombrev();}
					case -81:
						break;
					case 81:
						{return ops.unidadNombrev();}
					case -82:
						break;
					case 82:
						{return ops.unidadNombrev();}
					case -83:
						break;
					case 83:
						{return ops.unidadNombrev();}
					case -84:
						break;
					case 84:
						{return ops.unidadNombrev();}
					case -85:
						break;
					case 85:
						{return ops.unidadNombrev();}
					case -86:
						break;
					case 86:
						{return ops.unidadNombrev();}
					case -87:
						break;
					case 87:
						{return ops.unidadNombrev();}
					case -88:
						break;
					case 88:
						{return ops.unidadNombrev();}
					case -89:
						break;
					case 89:
						{return ops.unidadNombrev();}
					case -90:
						break;
					case 90:
						{return ops.unidadNombrev();}
					case -91:
						break;
					case 91:
						{return ops.unidadNombrev();}
					case -92:
						break;
					case 92:
						{return ops.unidadNombrev();}
					case -93:
						break;
					case 93:
						{return ops.unidadNombrev();}
					case -94:
						break;
					case 94:
						{return ops.unidadNombrev();}
					case -95:
						break;
					case 95:
						{return ops.unidadNombrev();}
					case -96:
						break;
					case 96:
						{return ops.unidadNombrev();}
					case -97:
						break;
					case 97:
						{return ops.unidadNombrev();}
					case -98:
						break;
					case 98:
						{return ops.unidadNombrev();}
					case -99:
						break;
					case 99:
						{return ops.unidadNombrev();}
					case -100:
						break;
					case 100:
						{return ops.unidadNombrev();}
					case -101:
						break;
					case 101:
						{return ops.unidadNombrev();}
					case -102:
						break;
					case 102:
						{return ops.unidadNombrev();}
					case -103:
						break;
					case 103:
						{return ops.unidadNombrev();}
					case -104:
						break;
					case 104:
						{return ops.unidadNombrev();}
					case -105:
						break;
					case 105:
						{return ops.unidadNombrev();}
					case -106:
						break;
					case 106:
						{return ops.unidadNombrev();}
					case -107:
						break;
					case 107:
						{return ops.unidadNombrev();}
					case -108:
						break;
					case 108:
						{return ops.unidadNombrev();}
					case -109:
						break;
					case 109:
						{return ops.unidadNombrev();}
					case -110:
						break;
					case 110:
						{return ops.unidadNombrev();}
					case -111:
						break;
					case 111:
						{return ops.unidadNombrev();}
					case -112:
						break;
					case 112:
						{return ops.unidadNombrev();}
					case -113:
						break;
					case 113:
						{return ops.unidadNombrev();}
					case -114:
						break;
					case 114:
						{return ops.unidadNombrev();}
					case -115:
						break;
					case 115:
						{return ops.unidadNombrev();}
					case -116:
						break;
					case 116:
						{return ops.unidadNombrev();}
					case -117:
						break;
					case 117:
						{return ops.unidadNombrev();}
					case -118:
						break;
					case 118:
						{return ops.unidadNombrev();}
					case -119:
						break;
					case 119:
						{return ops.unidadNombrev();}
					case -120:
						break;
					case 120:
						{return ops.unidadNombrev();}
					case -121:
						break;
					case 121:
						{return ops.unidadNombrev();}
					case -122:
						break;
					case 122:
						{return ops.unidadNombrev();}
					case -123:
						break;
					case 123:
						{return ops.unidadNombrev();}
					case -124:
						break;
					case 124:
						{return ops.unidadNombrev();}
					case -125:
						break;
					case 125:
						{return ops.unidadNombrev();}
					case -126:
						break;
					case 126:
						{return ops.unidadNombrev();}
					case -127:
						break;
					case 127:
						{return ops.unidadNombrev();}
					case -128:
						break;
					case 128:
						{return ops.unidadNombrev();}
					case -129:
						break;
					case 129:
						{return ops.unidadNombrev();}
					case -130:
						break;
					case 130:
						{return ops.unidadNombrev();}
					case -131:
						break;
					case 131:
						{return ops.unidadNombrev();}
					case -132:
						break;
					case 132:
						{return ops.unidadNombrev();}
					case -133:
						break;
					case 133:
						{return ops.unidadNombrev();}
					case -134:
						break;
					case 134:
						{return ops.unidadNombrev();}
					case -135:
						break;
					case 135:
						{return ops.unidadNombrev();}
					case -136:
						break;
					case 136:
						{return ops.unidadNombrev();}
					case -137:
						break;
					case 137:
						{return ops.unidadNombrev();}
					case -138:
						break;
					case 138:
						{return ops.unidadNombrev();}
					case -139:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
