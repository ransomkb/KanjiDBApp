/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kanjidbapp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Ransom Barber
 */
class KanjiEntry 
{
    private boolean bRead = false; //Reading value of an entry exists
    private boolean bMean = false; //English meaning of an entry exists
    
    //private String charact = "character";
    private String liter;
    //private String reading_meaning;
    //private String rmgroup;
    public List<String> readingOnList = new ArrayList<>();
    public List<String> readingKunList = new ArrayList<>();
    public List<String> meaningList = new ArrayList<>();

    
    public boolean isbRead()
    {
        return bRead;
    }

    public void setbRead(boolean bRead)
    {
        this.bRead = bRead;
    }

    public boolean isbMean()
    {
        return bMean;
    }

    public void setbMean(boolean bMean)
    {
        this.bMean = bMean;
    }
    
    public String getLiter()
    {
        return liter;
    }

    public void setLiter(String liter)
    {
        this.liter = liter;
    }
 
    @Override
    public String toString()
    {
        return "Entry: Literal = "+liter;
    }
    
    // Creates a CSV of a list of either the reading(s) or the meaning(s) of a Kanji Entry
    public String listToString(List<String> list)
    {
        // Using this aggregate stream as Iterator should only be used for removing
        String listCat = list.stream().collect(Collectors.joining(", "));
       
        return listCat;
    }
}
