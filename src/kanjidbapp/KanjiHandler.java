/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kanjidbapp;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import jpa.entities.Kanjis;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Admin
 */
class KanjiHandler extends DefaultHandler
{
    boolean bLiteral = false; // Kanji character exists
    boolean bReadingOn = false; // On-reading exist(s
    boolean bReadingKun = false; // Kun-reading exist(s)
    boolean bMeaning = false; // Eng meaning exists
    
    private int count;
    
    String literal = "";
    String onReadings = "";
    String kunReadings = "";
    String meanings = "";
    // Constructs a KanjiEntry object
    private KanjiEntry ent = null;
    // List to hold KanjiEntry object(s)
    private List<KanjiEntry> entList = null;
    
    // To persist a new entity
    // persistence unit name KanjiDBPU from persistence.xml
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("KanjiDBAppPU");
    EntityManager em = emf.createEntityManager();
    
    
    //private final KanjiController kanjiController;
    
    public KanjiHandler() throws UnsupportedEncodingException
    {
        this.count = 0;
        entList = new ArrayList<>();
        
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
    }
    
    /**
     *shut down the EntityManager and factory
     */
    public void closePersistStuff()
    {
        em.close();
        emf.close();
    }
    //getter method for employee list
    public List<KanjiEntry> getEntryList()
    {
        return entList;
    }
    
    @Override
   public void startElement(String uri, String localName, String qName, Attributes attributes)
           throws SAXException
   {
       String elementName = qName;
       
       if(elementName == null)
           return;
       
       switch(elementName.toLowerCase()) {
           case "character":
               ent = new KanjiEntry();
               break;
           case "literal":
               bLiteral = true;
               break;
           case "reading":
               String type = attributes.getValue("r_type");
           
            switch (type) {
                case "ja_on":
                    bReadingOn = true;
                    break;
                case "ja_kun":
                    bReadingKun = true;
                    break;
            }
               break;
           case "meaning":
               String lang = attributes.getValue("m_lang");
           
                if(lang == null || lang.equalsIgnoreCase("en"))
                {
                    bMeaning = true;
                }
               break;
           default:
               break;
       }
   }
   
   @Override
   public void endElement(String uri, String localName, String qName)
   {
       if(qName.equalsIgnoreCase("character"))
       {
           //add KanjiEntry object to list
           if(ent.isbRead() && ent.isbMean())
           {
                literal = ent.getLiter();
                onReadings = ent.listToString(ent.readingOnList);
                kunReadings = ent.listToString(ent.readingKunList);
                meanings = ent.listToString(ent.meaningList);

                count++;
                
                em.getTransaction().begin();

                Kanjis k = new Kanjis();
                k.setId(count);
                k.setKanji(literal);
                k.setKunyomi(kunReadings);
                k.setOnyomi(onReadings);
                k.setEng(meanings);

                em.persist(k);
                em.getTransaction().commit();
                
                    
                System.out.println(count);
                System.out.println(literal);
                System.out.println(onReadings);
                System.out.println(kunReadings);
                System.out.println(meanings);
           }
           entList.add(ent);
       }
   }
   
   // if an element has characters between tags, this is called automatically by DefaultHandler
   @Override
   public void characters(char ch[], int start, int length) throws SAXException
   {
       if(bLiteral)
       {
           ent.setLiter(new String(ch, start, length));
           bLiteral = false;
           //System.out.println(ent.getLiter());
       }
       else if(bReadingOn)
       {
           ent.setbRead(bReadingOn);
           ent.readingOnList.add(new String(ch, start, length));
           bReadingOn = false;
           //System.out.println(new String(ch, start, length));
           //System.out.println(ent.listToString(ent.readingList));
       }
       else if(bReadingKun)
       {
           ent.setbRead(bReadingKun);
           ent.readingKunList.add(new String(ch, start, length));
           bReadingKun = false;
           //System.out.println(new String(ch, start, length));
           //System.out.println(ent.listToString(ent.readingList));
       }
       else if(bMeaning)
       {
           ent.setbMean(bMeaning);
           ent.meaningList.add(new String(ch, start, length));
           bMeaning = false;
           //System.out.println(new String(ch, start, length));
           //System.out.println(ent.listToString(ent.meaningList));
       }
   }
}
