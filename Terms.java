/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Indexing;

/**
 *
 * @author rl07bebb
 */
import Analyzing.Document;
import java.io.Serializable;

/**
 * This class is responsible for creating the data that holds the terms and
 * their related information
 */
public class Terms implements Serializable{

   private Document doc;
   private final String TERM;
   private int frequency;
   private int firstOccurence;

   /**
    * This Constructor Creates the terms with the first instance of the term in
    * a documents and its location.
    *
    * @param s
    * @param d
    * @param l
    */
   public Terms(String s, Document d, int l) {
      firstOccurence = l;
      TERM = s;
      doc = d;
      frequency = 1;
   }

   /**
    * This method is responsible for incrementing the method and checking if the
    * document is new one.
    *
    * @param d the document the term is found in
    * @param l the word location that the word is found in
    *
    */
   public void incrementFreq() {
      frequency++;
   }
   
   /**
    * This method is responsible for getting the Term's name as a String.
    *
    * @return
    */
   public String getTerm() {
      return TERM;
   }
   
   /**
    * This method is responsible for getting the Term's Freq as a number.
    *
    * @return
    */
   public int getFreq() {
      return frequency;
   }
   
   /**
    * This method is responsible for getting the Term's First Occurence as a number.
    *
    * @return
    */
   public int getFOccurence() {
      return firstOccurence;
   }
   
   /**
    * This method is responsible for getting the Term's doc as a String.
    *
    * @return
    */
   public String getDocID() {
      return doc.getDocID();
   }
}
