package plag.parser.java2;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * Trieda na nacitanie vlastnosti tokenizacie v konfiguracnom XML subore
 * 
 */
public class TokenizationConfig {
    
    public boolean methodInvocationName;
    public boolean methodPairing;
    public List<String> methodList;
    public boolean returnInVoid;
    public boolean emptyElse;
    public boolean forToWhile;
    public File file;
    
    public void loadFromFile(String path) {
    	file = new File(path);
    	parseConfig();
    }
    
    public void parseConfig(){
        try {
        	if (file.exists()) {
	            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	            DocumentBuilder db = dbf.newDocumentBuilder();
	            Document doc = db.parse(file);
	            doc.getDocumentElement().normalize();
	            
	            NodeList min = doc.getElementsByTagName("methodInvocationName");            
	            methodInvocationName = Boolean.parseBoolean(min.item(0).getFirstChild().getNodeValue());
	                            
	            NodeList mp = doc.getElementsByTagName("methodPairing");
	            methodPairing = Boolean.parseBoolean(mp.item(0).getFirstChild().getNodeValue());
	            
	            NodeList ml = doc.getElementsByTagName("methodName");
	            methodList = new ArrayList<String>();
	            for (int i = 0; i < ml.getLength(); i++) {
	                methodList.add(ml.item(i).getFirstChild().getNodeValue());
	            }  
	            
	            NodeList rin = doc.getElementsByTagName("returnInVoid");
	            returnInVoid = Boolean.parseBoolean(rin.item(0).getFirstChild().getNodeValue());
	           
	            NodeList ee = doc.getElementsByTagName("emptyElse");
	            emptyElse = Boolean.parseBoolean(ee.item(0).getFirstChild().getNodeValue());
	            
	            
	            NodeList forWhile = doc.getElementsByTagName("forToWhile");
	            forToWhile = Boolean.parseBoolean(forWhile.item(0).getFirstChild().getNodeValue());
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
}
