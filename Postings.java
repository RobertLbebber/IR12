/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Indexing;

import Analyzing.Document;
import java.util.ArrayList;

/**
 *
 * @author rl07bebb
 */
public class Postings{

   public ArrayList<Terms> terms = new ArrayList<>();
   public Document lastDocument;

   public Postings(String s, Document d, int l) {
      terms.add(new Terms(s,d,l));
      lastDocument=d;
   }

   public int last() {
      return terms.size() - 1;
   }
   
   public boolean isNewDocument(Document d){
      return lastDocument!=d;
   }
   
   @Override 
   public String toString(){
      String out=terms.get(0).getTerm();
      for(Terms t:terms){
         out+=" = <Doc = "+t.getDocID()+" | Freq = "+t.getFreq()+" | First Occurence = "+t.getFOccurence()+">\t";
      }
      return out+"\n";
   }
}
