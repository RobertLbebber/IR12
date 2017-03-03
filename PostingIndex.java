/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Indexing;

import Analyzing.Document;
import Analyzing.DocumentAnalyzer;
import java.io.Serializable;

/**
 *
 * @author rl07bebb
 */
public class PostingIndex{

   private final String term;
   private int numDocsContainingTerm;
   private final int locationInPL;
   
   public PostingIndex(String t, Document d,int postIndexLoc,int l) {
      term=t;
      numDocsContainingTerm=1;
      locationInPL=postIndexLoc;
      Postings posting=new Postings(t,d,l);
      PostingsList.postingsList.add(posting);
   } 
   
   public void foundInNewDoc(){
      numDocsContainingTerm++;
   }
   public int getDocContainTerm(){
      return numDocsContainingTerm;
   }
   public int getLocationInPL(){
      return locationInPL;
   }
}
