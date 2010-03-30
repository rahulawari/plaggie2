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
    public List<String> methodList;
    public File file;
    
    
    public void parseConfig(String path){
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(path));
            System.out.println("Parsujem subor.");
            doc.getDocumentElement().normalize();
            System.out.println("Po zaciatku parsovania");
            
            NodeList min = doc.getElementsByTagName("methodInvocationName");            
            methodInvocationName = Boolean.parseBoolean(min.item(0).getFirstChild().getNodeValue());
                        
            NodeList mio = doc.getElementsByTagName("methodInvocationObject");
            methodInvocationObject = Boolean.parseBoolean(mio.item(0).getFirstChild().getNodeValue());
            
            NodeList mp = doc.getElementsByTagName("methodPairing");
            methodPairing = Boolean.parseBoolean(mp.item(0).getFirstChild().getNodeValue());
            
            NodeList ml = doc.getElementsByTagName("methodName");
            methodList = new ArrayList<String>();
            for (int i = 0; i < ml.getLength(); i++) {
                methodList.add(ml.item(i).getFirstChild().getNodeValue());
            }
            
            System.out.println("Doparsoval som subor");
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Chyba pri parsovani");
        }        
    }
    
    public void printConfig(){
        System.out.println("Vypisujem Konfiguraciu: ");
        System.out.println("Method invocation naming is set to: " + methodInvocationName);
        System.out.println("Method invocation object is set to: " + methodInvocationObject);
        System.out.println("Method pairing is set to: " + methodPairing);
        System.out.println("Methods to compare:");
        System.out.println(methodList.toString());
    }
    
    public static void main(String[] args) {
        TokenizationConfig config = new TokenizationConfig();
        config.parseConfig("skuska.xml");
        config.printConfig();

    }
    

}
