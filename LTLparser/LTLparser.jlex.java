import java_cup.runtime.*;
class TokenVal {
  // fields
    int linenum;
    int charnum;
  // constructor
    TokenVal(int line, int ch) {
        linenum = line;
        charnum = ch;
    }
}
class IdTokenVal extends TokenVal {
  // new field: the value of the identifier
    String idVal;
  // constructor
    IdTokenVal(int line, int ch, String val) {
        super(line, ch);
    idVal = val;
    }
}
// the current token starts on its line.
class CharNum {
    static int num=1;
}


class Yylex implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
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
		/* 32 */ YY_NOT_ACCEPT,
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
		/* 69 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"41:9,23,22,41:2,0,41:18,23,37,41:4,38,41,33,34,41:3,40,41,17,21:10,41:2,35," +
"41,36,41:2,20:5,32,29,20:10,27,20:2,24,26,25,28,20:2,30,18,31,41,21,41,6,19" +
":3,4,5,19:2,11,19:2,7,12,16,19,13,14,2,8,1,3,15,9,19,10,19,41,39,41:3,42:2")[0];

	private int yy_rmap[] = unpackFromString(1,70,
"0,1,2,3,4,3:10,5,6,3:9,7:6,8,9,10,11,3,12,13,14,15,16,17,18,19,7,20,21,22,2" +
"3,24,25,26,27,28,29,30,31,32,33,34,35,36,37,7,38,39,40,41,42")[0];

	private int yy_nxt[][] = unpackFromString(43,43,
"-1,1,64,65,66,67,68,64:4,69,64:5,2,34,64,36:2,3,4,5,6,7,8,9,10,38,36,11,12," +
"13,40,36,14,15,16,42,36,17,-1,64,44,64:14,-1:2,64:2,45,-1:2,64:6,-1:2,64,-1" +
":28,18,-1:90,4,-1:57,22,-1:43,23,-1:4,64:16,-1:2,64:2,45,-1:2,64:6,-1:2,64," +
"-1:46,25,-1:7,64:3,26,64:12,-1:2,64:2,45,-1:2,64:6,-1:2,64,-1:27,19,-1:26,6" +
"4:6,27,64:9,-1:2,64:2,45,-1:2,64:6,-1:2,64,-1:11,64:3,28,64:12,-1:2,64:2,45" +
",-1:2,64:6,-1:2,64,-1:41,20,-1:12,64:7,29,64:8,-1:2,64:2,45,-1:2,64:6,-1:2," +
"64,-1:46,21,-1:3,32,-1:3,64:7,30,64:8,-1:2,64:2,45,-1:2,64:6,-1:2,64,-1:46," +
"24,-1:7,31,64:15,-1:2,64:2,45,-1:2,64:6,-1:2,64,-1:11,64:2,33,64:13,-1:2,64" +
":2,45,-1:2,64:6,-1:2,64,-1:11,51,64:15,-1:2,64:2,45,-1:2,64:6,-1:2,64,-1:11" +
",64:2,52,64:13,-1:2,64:2,45,-1:2,64:6,-1:2,64,-1:11,64:6,53,64:9,-1:2,64:2," +
"45,-1:2,64:6,-1:2,64,-1:11,64:8,54,64:7,-1:2,64:2,45,-1:2,64:6,-1:2,64,-1:1" +
"1,64:12,55,64:3,-1:2,64:2,45,-1:2,64:6,-1:2,64,-1:11,64:10,35,64:5,-1:2,64:" +
"2,45,-1:2,64:6,-1:2,64,-1:11,64:10,56,64:5,-1:2,64:2,45,-1:2,64:6,-1:2,64,-" +
"1:11,64:7,37,64:8,-1:2,64:2,45,-1:2,64:6,-1:2,64,-1:11,64:5,57,64:10,-1:2,6" +
"4:2,45,-1:2,64:6,-1:2,64,-1:11,64:6,58,64:9,-1:2,64:2,45,-1:2,64:6,-1:2,64," +
"-1:11,64:14,59,64,-1:2,64:2,45,-1:2,64:6,-1:2,64,-1:11,64:9,39,64:6,-1:2,64" +
":2,45,-1:2,64:6,-1:2,64,-1:11,64:10,60,64:5,-1:2,64:2,45,-1:2,64:6,-1:2,64," +
"-1:11,64:5,61,64:10,-1:2,64:2,45,-1:2,64:6,-1:2,64,-1:11,64:3,41,64:12,-1:2" +
",64:2,45,-1:2,64:6,-1:2,64,-1:11,64:6,62,64:9,-1:2,64:2,45,-1:2,64:6,-1:2,6" +
"4,-1:11,64:3,63,64:12,-1:2,64:2,45,-1:2,64:6,-1:2,64,-1:11,64:15,43,-1:2,64" +
":2,45,-1:2,64:6,-1:2,64,-1:11,64:15,46,-1:2,64:2,45,-1:2,64:6,-1:2,64,-1:11" +
",64:13,47,64:2,-1:2,64:2,45,-1:2,64:6,-1:2,64,-1:11,64:5,48,64:10,-1:2,64:2" +
",45,-1:2,64:6,-1:2,64,-1:11,64:6,49,64:9,-1:2,64:2,45,-1:2,64:6,-1:2,64,-1:" +
"11,64:11,50,64:4,-1:2,64:2,45,-1:2,64:6,-1:2,64,-1:10");

	public java_cup.runtime.Symbol next_token ()
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

return new Symbol(sym.EOF);
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
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -2:
						break;
					case 2:
						{ ErrMsg.fatal(yyline+1, CharNum.num,
                         "illegal character ignored: " + yytext());
            CharNum.num++;
          }
					case -3:
						break;
					case 3:
						{ CharNum.num = 1; }
					case -4:
						break;
					case 4:
						{ CharNum.num += yytext().length(); }
					case -5:
						break;
					case 5:
						{ Symbol S = new Symbol(sym.UNTIL, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return S;
          }
					case -6:
						break;
					case 6:
						{ Symbol S = new Symbol(sym.WEAKUNTIL, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return S;
          }
					case -7:
						break;
					case 7:
						{ Symbol S = new Symbol(sym.DUAL, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return S;
          }
					case -8:
						break;
					case 8:
						{ Symbol S = new Symbol(sym.RELEASE, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return S;
          }
					case -9:
						break;
					case 9:
						{ Symbol S = new Symbol(sym.NEXT, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return S;
          }
					case -10:
						break;
					case 10:
						{ Symbol S = new Symbol(sym.ALWAYS, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return S;
          }
					case -11:
						break;
					case 11:
						{ Symbol S = new Symbol(sym.EVENTUALLY, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return S;
          }
					case -12:
						break;
					case 12:
						{ Symbol S = new Symbol(sym.LPAREN, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return S;
          }
					case -13:
						break;
					case 13:
						{ Symbol S = new Symbol(sym.RPAREN, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return S;
          }
					case -14:
						break;
					case 14:
						{ Symbol S = new Symbol(sym.NEGATION, new TokenVal(yyline+1, CharNum.num));
            CharNum.num++;
            return S;
          }
					case -15:
						break;
					case 15:
						{ Symbol S = new Symbol(sym.AND, new TokenVal(yyline+1, CharNum.num));
            CharNum.num+=2;
            return S;
          }
					case -16:
						break;
					case 16:
						{ Symbol S = new Symbol(sym.OR, new TokenVal(yyline+1, CharNum.num));
            CharNum.num+=2;
            return S;
          }
					case -17:
						break;
					case 17:
						
					case -18:
						break;
					case 18:
						{ Symbol S = new Symbol(sym.AND, new TokenVal(yyline+1, CharNum.num));
            CharNum.num+=2;
            return S;
          }
					case -19:
						break;
					case 19:
						{ Symbol S = new Symbol(sym.OR, new TokenVal(yyline+1, CharNum.num));
            CharNum.num+=2;
            return S;
          }
					case -20:
						break;
					case 20:
						{ Symbol S = new Symbol(sym.ALWAYS, new TokenVal(yyline+1, CharNum.num));
            CharNum.num+=2;
            return S;
          }
					case -21:
						break;
					case 21:
						{ Symbol S = new Symbol(sym.EVENTUALLY, new TokenVal(yyline+1, CharNum.num));
            CharNum.num+=2;
            return S;
          }
					case -22:
						break;
					case 22:
						{ Symbol S = new Symbol(sym.AND, new TokenVal(yyline+1, CharNum.num));
            CharNum.num+=2;
            return S;
          }
					case -23:
						break;
					case 23:
						{ Symbol S = new Symbol(sym.OR, new TokenVal(yyline+1, CharNum.num));
            CharNum.num+=2;
            return S;
          }
					case -24:
						break;
					case 24:
						{ Symbol S = new Symbol(sym.IMPLICATION, new TokenVal(yyline+1, CharNum.num));
            CharNum.num+=2;
            return S;
          }
					case -25:
						break;
					case 25:
						{ Symbol S = new Symbol(sym.EQUIVALENCE, new TokenVal(yyline+1, CharNum.num));
            CharNum.num+=3;
            return S;
          }
					case -26:
						break;
					case 26:
						{
				Symbol S = new Symbol(sym.TRUE, new TokenVal(yyline+1, CharNum.num));
				CharNum.num +=yytext().length();
				return S;
			}
					case -27:
						break;
					case 27:
						{
				Symbol S = new Symbol(sym.UNTIL, new TokenVal(yyline+1, CharNum.num));
				CharNum.num +=yytext().length();
				return S;
			}
					case -28:
						break;
					case 28:
						{
				Symbol S = new Symbol(sym.FALSE, new TokenVal(yyline+1, CharNum.num));
				CharNum.num +=yytext().length();
				return S;
			}
					case -29:
						break;
					case 29:
						{
				Symbol S = new Symbol(sym.ALWAYS, new TokenVal(yyline+1, CharNum.num));
				CharNum.num +=yytext().length();
				return S;
			}
					case -30:
						break;
					case 30:
						{
				Symbol S = new Symbol(sym.IMPLICATION, new TokenVal(yyline+1, CharNum.num));
				CharNum.num +=yytext().length();
				return S;
			}
					case -31:
						break;
					case 31:
						{
				Symbol S = new Symbol(sym.EQUIVALENCE, new TokenVal(yyline+1, CharNum.num));
				CharNum.num +=yytext().length();
				return S;
			}
					case -32:
						break;
					case 33:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -33:
						break;
					case 34:
						{ ErrMsg.fatal(yyline+1, CharNum.num,
                         "illegal character ignored: " + yytext());
            CharNum.num++;
          }
					case -34:
						break;
					case 35:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -35:
						break;
					case 36:
						{ ErrMsg.fatal(yyline+1, CharNum.num,
                         "illegal character ignored: " + yytext());
            CharNum.num++;
          }
					case -36:
						break;
					case 37:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -37:
						break;
					case 38:
						{ ErrMsg.fatal(yyline+1, CharNum.num,
                         "illegal character ignored: " + yytext());
            CharNum.num++;
          }
					case -38:
						break;
					case 39:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -39:
						break;
					case 40:
						{ ErrMsg.fatal(yyline+1, CharNum.num,
                         "illegal character ignored: " + yytext());
            CharNum.num++;
          }
					case -40:
						break;
					case 41:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -41:
						break;
					case 42:
						{ ErrMsg.fatal(yyline+1, CharNum.num,
                         "illegal character ignored: " + yytext());
            CharNum.num++;
          }
					case -42:
						break;
					case 43:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -43:
						break;
					case 44:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -44:
						break;
					case 45:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -45:
						break;
					case 46:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -46:
						break;
					case 47:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -47:
						break;
					case 48:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -48:
						break;
					case 49:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -49:
						break;
					case 50:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -50:
						break;
					case 51:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -51:
						break;
					case 52:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -52:
						break;
					case 53:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -53:
						break;
					case 54:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -54:
						break;
					case 55:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -55:
						break;
					case 56:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -56:
						break;
					case 57:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -57:
						break;
					case 58:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -58:
						break;
					case 59:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -59:
						break;
					case 60:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -60:
						break;
					case 61:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -61:
						break;
					case 62:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -62:
						break;
					case 63:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -63:
						break;
					case 64:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -64:
						break;
					case 65:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -65:
						break;
					case 66:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -66:
						break;
					case 67:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -67:
						break;
					case 68:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -68:
						break;
					case 69:
						{
            Symbol S = new Symbol(sym.ID, 
                             new IdTokenVal(yyline+1, CharNum.num, yytext()));
            CharNum.num += yytext().length();
            return S;
          }
					case -69:
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
