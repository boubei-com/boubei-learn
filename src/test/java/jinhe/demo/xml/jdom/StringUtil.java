package jinhe.lt.xml.jdom;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class StringUtil {

	public static String list2String(List lst) {
		String s = "";
		for (int i = 0; i < lst.size(); i++) {
			if (!s.equals(""))
				s += "\n";
			s += lst.get(i);
		}
		return s;
	}

	public static List string2List(String txt) {
		// Convert the actors from a textarea to an ArrayList.
		// Split according to newline-chars.
		// Drop empty lines
		ArrayList lst = new ArrayList();

		if (txt != null) {
			String as = txt;

			// See if lines separated by CR or CR/LF
			String sep = null;
			if (as.indexOf("\r\n") > -1)
				sep = "\r\n";
			else if (as.indexOf("\n") > -1)
				sep = "\n";
			if (sep != null) {
				int p;
				while ((p = as.indexOf(sep)) > -1) {
					String actor = as.substring(0, p).trim();
					if (actor.length() != 0)
						lst.add(actor);
					as = as.substring(p + sep.length());
				}
			}
			as = as.trim();
			if (as.length() != 0)
				lst.add(as);
		}
		return lst;
	}

}