package Indexing;

import Analyzing.Document;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class has creates the data structure used to hold the terms which has
 * the reference to the content in the file.
 *
 * @author rl07bebb
 */
public class Dictionary{

   private HashMap<String, PostingIndex> dictionary;
   private PostingsList postingsList;
   private int termCount;

   /**
    * This constructor is only used to create a instantiate version of the
    * dictionary for lesser dictionaries.
    * @param p
    */
   public Dictionary(PostingsList p) {
      dictionary = new HashMap<>();
      postingsList=p;
   }
   
   public PostingsList getPostingsList(){
      return postingsList;
   }

   public HashMap<String, PostingIndex> getHash() {
      return dictionary;
   }

   /**
    * This method is responsible for getting the terms from the dictionary
    *
    * @param s
    * @return
    */
   public ArrayList<Terms> getTerm(String s) {
      int loc = dictionary.get(s).getLocationInPL();
      Postings postings = postingsList.get(loc);
      return postings.terms;
   }
   
   public Terms getTermName(String s) {
      PostingIndex pI=dictionary.get(s);
      if(pI==null){return null;}
      int loc = pI.getLocationInPL()-1;
      Postings postings = postingsList.get(loc);
      return postings.terms.get(0);
   }

   /**
    * This method is used to check if a term is new and if so it will create and
    * add the the new term or it will update that term with the new occurrence.
    * This version of the method is for instantiated Dictionaries.
    *
    * @param s the String of term to be checked
    * @param d the document of th originating word
    * @param location the location of the term in the document
    */
   public void checkNewTerm(String s, Document d, int location) {
      if (dictionary.containsKey(s)) {
         PostingIndex postingIndex = dictionary.get(s);
         Postings post = postingsList.get(postingIndex.getLocationInPL()-1);
         if (post.isNewDocument(d)) {
            post.terms.add(new Terms(s,d,location));
            return;
         }
         Terms term = post.terms.get(post.last());
         term.incrementFreq();
      } else {
         termCount++;
         dictionary.put(s, new PostingIndex(s, d, termCount, location));
      }
   }
   
   public int size(){
      return dictionary.size();
   }
   
   @Override
   public String toString(){
      return postingsList.toString(); 
   }
}
