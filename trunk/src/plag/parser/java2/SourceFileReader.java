package plag.parser.java2;

import java.io.*;
import java.util.ArrayList;

/**
 * Trieda zapuzdrujuca kompletny pristup k zdrojakom v subore - riadky, cisla riadkov, atd.
 */
public class SourceFileReader {
	
	private int[] lineStarts = null;
	
	private ArrayList<String> lines = new ArrayList<String>();
	
	private File sourceFile;
	
	public SourceFileReader(File sourceFile) throws FileNotFoundException {
		this(new BufferedReader(new FileReader(sourceFile)));
		this.sourceFile = sourceFile;
	}
	
	public SourceFileReader(BufferedReader reader) {
		try {
			String line;
			while ((line = reader.readLine()) != null) 
				lines.add(line);
		} catch (Exception e) {

		} finally {
			try {
				reader.close();
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
	
	public int getCharPos(int lineNr, int column) {
		// Zo zadaneho riadku musime odratat 8xpocet tabov pred zadanym znakom
		String line = lines.get(lineNr-1);
		
		int realColumn = line.length()-1;
		for (int i=0; i<line.length(); i++) {
			if (column <= 1) {
				realColumn = i;
				break;
			}

			if (line.charAt(i) == '\t')
				column -= 8;
			else
				column--;		
		}
		
		return lineStarts[lineNr-1]+realColumn;
	}
	
	public String getContent(int fromCharPos, int toCharPos){		
		return "";
		
		// citat trebalo z lines, nie zo suboru (naco citat nieco, co je uz v pamati). Okrem toho, ak to precitame
		// s Readera, meno suboru nevieme
		
		/*String content = "";
		try {
			FileReader reader = new FileReader(sourceFile);
			int counter=0;
			while(counter <fromCharPos){
				reader.read();
				counter++;
			}			
			
			for(int i = fromCharPos; i<=toCharPos; i++)
				content+=reader.read();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return content;
		
		*/
	}
}
