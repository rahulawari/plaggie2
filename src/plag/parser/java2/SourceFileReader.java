package plag.parser.java2;

import java.io.*;
import java.util.ArrayList;

/**
 * Trieda zapuzdrujuca kompletny pristup k zdrojakom v subore - riadky, cisla riadkov, atd.
 */
public class SourceFileReader {
	
	private int[] lineStarts = null;
	
	private ArrayList<String> lines = new ArrayList<String>();;
	
	public SourceFileReader(File sourceFile) throws FileNotFoundException {
		BufferedReader r = new BufferedReader(new FileReader(sourceFile));
		try {
			String line;
			while ((line = r.readLine()) != null) 
				lines.add(line);
		} catch (Exception e) {

		} finally {
			try {
				r.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		lineStarts = new int[lines.size()];
		lineStarts[0] = 0;
		for (int i=1; i<lineStarts.length; i++)
			lineStarts[i] = lineStarts[i-1] + lines.get(i-1).length(); 
	}
	
	public String getLine(int lineNr) {
		return lines.get(lineNr-1);
	}
	
	public int getCharPos(int line, int column) {
		return lineStarts[line-1]+column-1;
	}
}
