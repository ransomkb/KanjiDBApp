/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kanjidbapp;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

/**
 *
 * @author Admin
 */
public class KanjiDBApp {

     private static final String xmlFilePath = "C:\\Users\\Admin\\Programming\\kanjidic2.xml";
     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("defaultCharacterEncoding by code after updating file.encoding : " + getDefaultCharEncoding());
        try 
        {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            saxParserFactory.setFeature("http://xml.org/sax/features/validation", false);
            saxParserFactory.setValidating(false);
            
            SAXParser saxParser = saxParserFactory.newSAXParser();
            
            File xmlFile = new File(xmlFilePath);
            InputStream inputStream = new FileInputStream(xmlFile);
            InputStreamReader inputReader = new InputStreamReader(inputStream, "UTF-8");
            System.out.println("inputReader encoding : " + inputReader.getEncoding());
            InputSource inputSource = new InputSource(inputReader);
            
            System.out.println("inputSource encoding before setting: " + inputSource.getEncoding());
            inputSource.setEncoding("UTF-8");
            System.out.println("inputSource encoding after: " + inputSource.getEncoding());
            
            KanjiHandler handler;
            handler = new KanjiHandler();
            saxParser.parse(inputSource, handler);
            
            handler.closePersistStuff();
            
            System.out.println("Parsed, out and closed");
            System.out.println("inputSource encoding finally: " + inputSource.getEncoding());
        }  
        catch (SAXNotRecognizedException | SAXNotSupportedException e) 
        {
            System.out.println(e.getMessage());
        }
        catch (ParserConfigurationException e) 
        {
            System.out.println(e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        System.out.println("defaultCharacterEncoding by code after updating file.encoding : " + getDefaultCharEncoding());
   
    }
    
    public static String getDefaultCharEncoding()
    {
        byte [] bArray = {'w'};
        InputStream is = new ByteArrayInputStream(bArray);
        InputStreamReader reader = new InputStreamReader(is);
        String defaultCharacterEncoding = reader.getEncoding();
        return defaultCharacterEncoding;
    }
}
