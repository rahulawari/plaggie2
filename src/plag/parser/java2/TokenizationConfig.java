package plag.parser.java2;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class TokenizationConfig {
    
    public boolean methodInvocationName;
    public boolean methodInvocationObject;
    public boolean methodPairing;
    public boolean plagiarismProtection;
    public List<String> methodList;    
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
	                        
	            NodeList mio = doc.getElementsByTagName("methodInvocationObject");
	            methodInvocationObject = Boolean.parseBoolean(mio.item(0).getFirstChild().getNodeValue());
	            
	            NodeList pp = doc.getElementsByTagName("plagiarismProtection");
	            plagiarismProtection = Boolean.parseBoolean(pp.item(0).getFirstChild().getNodeValue());
	            
	            NodeList mp = doc.getElementsByTagName("methodPairing");
	            methodPairing = Boolean.parseBoolean(mp.item(0).getFirstChild().getNodeValue());
	            
	            NodeList ml = doc.getElementsByTagName("methodName");
	            methodList = new ArrayList<String>();
	            for (int i = 0; i < ml.getLength(); i++) {
	                methodList.add(ml.item(i).getFirstChild().getNodeValue());
	            }  
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
}
