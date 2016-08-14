

/** The class include methods which format/convert a string.
    The methods include
	* Convert between a phone number (int) and string.
	* Remove/add leading trailing spaces.
	* Remove extra spaces.
	* Convert between string and tokens.
*/
import java.io.*;
import java.sql.*;
import java.text.*;

public class ScreenIO {

     private ScreenIO() {}
     public static final int	left = 0;
     public static final int	middle = 1;
     public static final int	right = 2;

     public static final void output(int v, int width ) {
	String fmt = getFormatString("d", width,
			Integer.toString(v).length(), right);
	System.out.printf( fmt, v);
     }

     public static final void output(int v, int width, int align ) {
	String fmt = getFormatString("d", width,
			Integer.toString(v).length(), align);
	System.out.printf( fmt, v);
     }

     public static final void output(String s, int width ) {
	String fmt = getFormatString("s", width, s.length(), right );
	System.out.printf( fmt, s);
     }

     public static final void output(String s, int width, int align ) {
	String fmt = getFormatString("s", width, s.length(), align);
	System.out.printf( fmt, s);
     }

     public static final String getFormatString(String type, int width, int sLen, int align ) {
	int half = ( width - sLen ) / 2;
	String fmt = "%";
	if ( align == middle ) {
		fmt = makeString(' ', width-sLen-half) + fmt;
		width = sLen+half;
		align = left; 
	} 
	fmt += (align == left ? "-" : "" ) + width + type ;
	//System.out.println(fmt);
	return fmt;
     }

     public static void printInMiddle (String s) { showInMiddle(s); }

     public static void showInMiddle (String s) {
	System.out.print(makeString(' ', (80 - s.length()) / 2) + s);
     }

     public static int stringToInt(String s) {
	int i, j, k =s.trim().length() ;
	if ( k==0 ) return 0;
	char c, t[] = new char[k];
	for ( i=0, j=0; j < k ; j++) {
		c = s.charAt(j) ;
		if ( c >= '0' && c <= '9' ) t[i++] = c;
	}
	return Integer.parseInt( new String(t, 0, i));
      }
		
     public static long stringToLong(String s) {
	int i, j, k =s.trim().length() ;
	if ( k == 0 ) return 0L;
	char c, t[] = new char[k];
	for ( i=0, j=0; j < k ; j++) {
		c = s.charAt(j) ;
		if ( c >= '0' && c <= '9' ) t[i++] = c;
	}
	return Long.parseLong( new String(t, 0, i));
      }

// ----------------------------------------------------------------
//   Expand string by padding a char string or space.
// ----------------------------------------------------------------
     public static String makeString(char c, int len) {
	char buf[] = new char[len];
	for ( int i = 0; i < len; i++ ) buf[i] = c;
	String t = new String(buf);
	return t;
     }

     public static String space(int len) {
	return makeString(' ', len);
     }

// ----------------------------------------------------------------
//   Format string to a fixed length string by either padding spaces
//   to the right/left of string or to shorten the string.
// ----------------------------------------------------------------
     public static String formatString(String s, int len, int align) {
	return expandString(s, len, align);
     }

     public static String formatString(String s, int len) {
	return expandString(s, len, left);
     }

     public static String formatString(int len, String s) {
	return expandString(s, len, right);
     }

     public static String expand(String s, int len) {
	return expandString(s, len, left);
     }

     public static String expand(int len, String s) {
	return expandString(s, len, right);
     }

     public static String expand(String s, int width, int align ) {
	return expand(s, width, align);
     }

     public static String expandString(String s, int len) {
	return expandString(s, len, left);
     }

     public static String expandString(int len, String s) {
	return expandString(s, len, right);
     }

     public static String expandString(String s, int width, int align ) {
	int sLength = 0;

	if (s == null ) s = "";  else sLength = s.trim().length();
	int halfSpace = (int) ((width - sLength) / 2);

	if ( sLength >= width ) return s.substring(0, width);
	else  return  align == right ?
			makeString(' ', width - sLength) + s:
			align == left ? s + makeString(' ', width - sLength) 
			     : makeString(' ', halfSpace ) + s +
				makeString(' ', width - sLength - halfSpace);
     }

     public static String capitalize(String s) {
	if ( s == null ) return s;
	return s.substring(0,1).toUpperCase() + s.substring(1, s.length()).toLowerCase();
     }

     public static String escapeDoubleQuotes(String s) {
	String t = "";
	int start =0, k = s.indexOf('\"');
	while ( k >= 0 ) {
		t = t + s.substring(0, k) + "\\\"";
		s = s.substring(k+1, s.length()); 
		k = s.indexOf('\"');
	}
	return t + s;
     }
		
     public static String removeExtraBlanks (String s) {
	if ( s == null ) return "";
	s = s.trim();
	int idx1 = 0, idx2 = 0, len =s.length() ;
	char c, t[] = new char[len];

	boolean spaceBefore = false;

	for ( idx1=0, idx2=0; idx1 < len ; idx1++) {
		c = s.charAt(idx1) ;
		if (c == ' ' || c == '\t' || c == '\n') {
			if ( !spaceBefore ) {
				t[idx2++] = c;
				spaceBefore = true; } 
		} else {
			t[idx2++] = c;
			spaceBefore = false;
		  }
	}
	return new String(t, 0, idx2) ;
      }
		
      public static String formatPhone(long ph) {
	if ( ph == 0 ) return "";
	String s0 = "", s1, s2;
	long cd = ph;
	if ( ph >= 10000000 ) {
		cd = ph / 10000000;
		s0 = cd>=100? cd+"-": cd>=10 ? "0"+cd+"-": "00"+cd+"-";
		ph = ph % 10000000;
	}
	cd = ph/10000;
	s1= cd>=100? cd+"-": cd>=10 ? "0"+cd+"-": "00"+cd+"-";
	cd = ph%10000;
	s2 = cd>=1000? cd+"": cd>=100 ? "0"+cd: cd>=10 ? "00"+cd : "000"+cd ;
	return s0+s1+s2 ;
      }

      public static String formatSSN(long ssn) {
	return String.format("%03d-%02d-%04d",
			ssn/1000000, ssn/10000 % 100, ssn%10000);
      }

      public static String formatDouble(double d, int prec) {
	String fmt = makeString('0', prec);
	DecimalFormat df = new DecimalFormat("#,##0." + fmt);
	StringBuffer   res = new StringBuffer();
	df.format(d, res, new FieldPosition(prec));
	return new String(res);
      }

      public static String formatFloat( float f, int prec ) { return formatDouble ( (double) f, prec ) ; }

	// all float, double are truncated, not rounded.
      public static String formatCurrency( double d) { return '$' + formatDouble ( d,  2) ; }
      public static String formatCurrency( float f) { return '$' + formatDouble ( (double) f, 2) ; }
      public static String formatCurrency( double d, int prec ) { return '$' + formatDouble ( d, prec ) ; }
      public static String formatCurrency( float f, int prec ) { return '$' + formatDouble ( (double) f, prec ) ; }

      public static String formatInt( int i ) { return formatLong( (long) i ) ; }

      public static String formatLong(long d) {
	DecimalFormat df = new DecimalFormat("#,###");
	StringBuffer   res = new StringBuffer();
	df.format(d, res, new FieldPosition(0));
	return new String(res);
      }

// ----------------------------------------------------------------
//   Prompt for continuation of listing.
// ----------------------------------------------------------------
      public static boolean continueToList() {
	byte ans[] = new byte[10]; char c = ' ';
	System.out.print("\nPress Q/q to quit, any other key to continue listing ... ");
	try { System.in.read(ans); } catch (IOException e) {return true;}
	try { c = (new String(ans)).trim().charAt(0); }
	catch (StringIndexOutOfBoundsException e) { return true;}
	if ( c == 'Q' || c == 'q' ) return false;
	return true;
     }

// ----------------------------------------------------------------
//   Print a prompt message for input data.
// ----------------------------------------------------------------
     public static String promptForString( String prompt ) {
	/* byte ans[] = new byte[120];
	System.out.print(makeString(' ', (80 - prompt.length()) / 2) + prompt);
	try { System.in.read(ans); } catch (IOException e) { return null; }
	return (new String(ans)).trim();
	*/
	return promptForString( prompt, ScreenIO.middle);
     }

     public static String promptForString( String prompt, int dir ) {
	int n = dir == ScreenIO.left ? 0 :
		dir == right ? 80 - prompt.length() :
		(80 - prompt.length() ) / 2;
	byte ans[] = new byte[120];
	System.out.print(makeString(' ', n) + prompt);
	try { System.in.read(ans); } catch (IOException e) { return null; }
	return (new String(ans)).trim();
     }

     public static char promptForChar( String prompt ) {
	byte ans[] = new byte[120]; char c;
	System.out.print(makeString(' ', Math.abs(80 - prompt.length()) / 2) + prompt);
	try { System.in.read(ans); } catch (IOException e) { return ' '; }
	try { c = (new String(ans)).trim().charAt(0); }
	catch (StringIndexOutOfBoundsException e) { return ' '; }
	return c;
     }

     public static int promptForInt( String prompt ) {
	byte ans[] = new byte[120];
	System.out.print(makeString(' ', (80 - prompt.length()) / 2) + prompt);
	try { System.in.read(ans); } catch (IOException e) { return 0; }
	return Integer.parseInt((new String(ans)).trim());
     }

     public static boolean quit( String prompt, char q ) {
	char ch = promptForChar( prompt );
	return Character.toUpperCase(q) == Character.toUpperCase(ch) ;
     }
// ----------------------------------------------------------------
//   Print a menu passed as an array of strings.
// ----------------------------------------------------------------
     public static void showMenu( String menu[] ) {
	System.out.println();
	for ( int i = 0; i <  menu.length; i ++ )
		System.out.println(menu[i]);
	System.out.println();
     }

     public static void showMenu(String linePrefix, String menu[] ) {
	System.out.println();
	for ( int i = 0; i <  menu.length; i ++ )
		System.out.println( linePrefix + menu[i]);
	System.out.println();
     }

// ----------------------------------------------------------------
//   Display a result on screen.
// ----------------------------------------------------------------
    public static void displayResultSet(ResultSet res ) {
	int widthArr[] = null,
	    colCnt = 0, rowCount = 0; 
	ResultSetMetaData meta;
	String columnHeaders = null;

	try {
		meta = res.getMetaData();
		colCnt = meta.getColumnCount();
		widthArr = getColumnWidths(colCnt, meta);
		columnHeaders = getColumnHeaders( colCnt, widthArr, meta); 
		rowCount = 0;
		System.out.printf("%s", columnHeaders);
		while ( res.next() ) {
			for ( int i = 1; i <= colCnt; i ++ )
				System.out.print(ScreenIO.expandString( res.getString(i), widthArr[i-1] + 1)) ;
			System.out.println();
			// if ( ++rowCount % 30 == 0 && ! ScreenIO.continueToList() ) break;
			if ( ++rowCount % 30 == 0 ) System.out.printf("\n%s", columnHeaders);
		}
	} catch (SQLException e) { e.printStackTrace();  /*System.exit(-1);*/ }
	     catch (NullPointerException e ) {}

        System.out.printf("\n%d rows selected.\n\n", rowCount);
	return;
    }

    static String getColumnHeaders(int cnt, int [] widthArr, ResultSetMetaData meta) {
	try {
	        StringBuffer buf = new StringBuffer(2000);
		for ( int i = 1; i <= cnt; i ++ )
			buf.append ( ScreenIO.expandString(meta.getColumnName(i), widthArr[i-1] + 1));
		buf.append("\n");
		for ( int i = 1; i <= cnt; i ++ )
			buf.append( ScreenIO.makeString('=', widthArr[i-1]) + ' ');
		buf.append( "\n" );
		return buf.toString();
	} catch (SQLException e ) {}

	return "";	
    }

    static int [] getColumnWidths(int colCnt, ResultSetMetaData meta) {
	int [] widthArr = new int[colCnt];
	for ( int i = 1; i <= colCnt; i ++ ) {
	   try {
		widthArr[i-1] = meta.getColumnDisplaySize(i); 
		switch( meta.getColumnType(i) ) {
		   case Types.NUMERIC: widthArr[i-1] = 10; break;
		   case Types.DECIMAL: widthArr[i-1] = 10; break;
		   case Types.DATE: widthArr[i-1] = 10; break;
		   case Types.TIME: widthArr[i-1] = 10; break;
		   case Types.TIMESTAMP: widthArr[i-1] = 10; break;
	   	}
	    } catch (SQLException e) {}
	}
	return widthArr;
    }
// -------------- End of displayResultSet ------------------------------
// -------------- ANSI Control Sequence   ------------------------------

final int Tab 		= 9;
final int BottomX 	= 25;
final int TopX 		= 1;
final int MiddleX	= 12;
final int MiddleY	= 30;
final int MessageLen	= 80;

    public static void  bell() { System.out.printf("%c", (char)(7)); }
    public static void  moveTo(int x, int y ) { System.out.printf("%s", (char)(27) + "[" + x  +";" + y +"H") ; }

    // Clear screen and position cursor at home.
    public static void  clear() { System.out.printf("%s", (char)(27) +"[2J") ; }

/*  TO DO ....
    public static void  cursorUpNLines(int x) { System.out.printf (char)(27) << "[" << x << ";A"; }
    public static void  cursorUp() { System.out.printf (char)(27) << "[" << ";A"; }
    public static void  cursorDownNLines(int x) { System.out.printf (char)(27) << "[" << x << ";B"; }

    // output device status report
    public static void reportDSR() { System.out.printf (char)(27) << "[6n"; }

    // report cursor position ; not working?
    public static void reportCursor(int x, int y) { System.out.printf (char)(27) << "[" << x << ";" << y << "R"; }

    // save cursor position for restoring late.
    public static void saveCursor() { System.out.printf (char)(27) << "[s"; }

    public static void  restoreCursor() { System.out.printf (char)(27) << "[u"; }

    // delete chars from curor position to the eoln.
    public static void eraseLine() { System.out.printf (char)(27) << "[K"; }
    public static void setAllOff() { System.out.printf (char)(27) << "[0m"; }

    public static void setBoldOff() { System.out.printf (char)(27) << "=1|"; }

    public static void setBold() { System.out.printf (char)(27) << "[1m"; }

    public static void setFaint() { System.out.printf (char)(27) << "[2m"; }

    //  Set italic on
    public static void setItalic() { System.out.printf (char)(27) << "[3m"; }

    //  Set blinking on.
    public static void setBlinking() { System.out.printf (char)(27) << "[5m"; }

    // { Rapid blinking on. }
    public static void  setFastBlinking() { System.out.printf (char)(27) << "[6m"; }

    // { Reverse the video. }
    public static void setReverseVideo() { System.out.printf (char)(27) << "[7m"; }

    // { Concealed on, ISO 6429 standard }
    public static void concealedOn() { System.out.printf (char)(27) << "[8m"; }

    //{ Set the foreground black, ISO 6429 standard. }
    public static void  setBlackForeground() { System.out.printf (char)(27) << "[30m"; }

    // {  Red foreground, ISO 6429 standard. }
    public static void setRedForeground() { System.out.printf (char)(27) << "[31m"; }

    // {  Green foreground, ISO 6429 standard. }
    public static void setGreenForeground() { System.out.printf (char)(27) << "[32m"; }

    // {  Yellow foreground, ISO 6429 standard. }
    public static void setYellowForeground() { System.out.printf (char)(27) << "[33m"; }

    // {  Blue forefround, ISO 6429 standard. }
    public static void setBlueForeground() { System.out.printf (char)(27) << "[34m"; }

    // {  Magenta foreground, ISO 6429 standard. }
    public static void setMagentaForeground() { System.out.printf (char)(27) << "[35m"; }

    // {  Cyan foreground, ISO 6429 standard. }
    public static void setCyanForeground() { System.out.printf (char)(27) << "[36m"; }

    // {  White foreground, ISO 6429 standard. }
    public static void setWhiteForeground() { System.out.printf (char)(27) << "[37m"; }

    // {  Black background, ISO 6429 standard. }
    public static void setBlackBackground() { System.out.printf (char)(27) << "[40m"; }
	
    // {  Red background, ISO 6429 standard. }
    public static void setRedBackground(){ System.out.printf (char)(27) << "[41m" ; }

    // { Green background, ISO 6429 standard. }
    public static void setGreenBackground() { System.out.printf (char)(27) << "[42m"; }

    // { Yellow background, ISO 6429 standard. }
    public static void setYellowBackground() { System.out.printf (char)(27) << "[43m"; }

    // { Blue background, ISO 6429 standard. }
    public static void setBlueBackground() { System.out.printf (char)(27) << "[44m"; }

    // { Magenta background, ISO 6429 standard. }
    public static void setMagentaBackground() { System.out.printf (char)(27) << "[45m"; }

    // { Cyan background, ISO 6429 standard. }
    public static void setCyanBackground() { System.out.printf (char)(27) << "[46m"; }

    // { White background, ISO 6429 standard. }
    public static void setWhiteBackground() { System.out.printf (char)(27) << "[47m"; }

    // {  Set the subscript. }
    public static void setSubscript() { System.out.printf (char)(27) << "[48m"; }

    // {  Set superscript. }
    public static void setSuperscript() { System.out.printf (char)(27) << "[49m"; }

    // { It sets the screen mode according above definition. }
    public static void  setScreenMode( int x ) { System.out.printf (char)(27)<< "[="<< x<<"h"; }

    // { It resets the screen mode according above definition. }
    public static void  resetScreenMode( int x ) { System.out.printf (char)(27)<<"[="<<x<<"l"; }

    // { Wrap at the end of line. }
    public static void  setLinewrap() { System.out.printf (char)(27) << "[?7h"; }

    // { Wrap at the end of line. }
    public static void  resetLinewrap() { System.out.printf (char)(27) << "[?7l"; }

    public static void messageAt(char * Message, int x,  int y) {
		moveCursorTo(x, y);
		eraseLine();
		System.out.printf (Message); }

    public static void messageAt( char * Message, int x,  int y,
					bool reverse, bool bold,  bool blinking) {
		moveCursorTo(x, y);
		eraseLine();
		if (reverse) setReverseVideo();
		if (bold) setBold();
		if (blinking) setBlinking();
		System.out.printf (Message);
		System.out.printf (" "); }

    public static void   eraseMessageAt(int x, int y) {
         moveCursorTo(x, y);
         eraseLine();
    //     drawBox(1, 1, 80, 25);
    }
    
public static  char hold(int x=14, int y = 24, char * msg = "Press ENTER to continue, Q/q to quit ... ") {
		eraseLine();
		messageAt(msg, x, y);
		char trash[10] ;
		cin.getline(trash, 10);
		if ( strlen(trash) > 0 ) return trash[0];
		return ' ';
    }

*/
}

