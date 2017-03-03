package Indexing;

import Analyzing.Document;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * This Class is only currently used to create a posting list file to be written
 * into storage.
 *
 * @author rl07bebb
 */
public class PostingsList{

   public static ArrayList<Postings> postingsList=new ArrayList<>();
   Document doc;
   
   /**
    * This Constructor is responsible for creating the post class. The
    * constructor takes a document because it will write to this documents file.
    *
    * @param doc
    */
   public PostingsList() {
   }

   /**
    * This Constructor is responsible for creating the post class. The
    * constructor takes a document because it will write to this documents file.
    *
    * @param doc
    */
   public PostingsList(Document doc) {
      this.doc = doc;
   }
   
   public Postings get(int i){
      return postingsList.get(i);
   }
   

   /**
    * This method is just to compare the Indexing.Terms.
    */
   private static final Comparator<String> ALPHABETICAL_ORDER = new Comparator<String>() {
      @Override
      public int compare(String str1, String str2) {
         int res = String.CASE_INSENSITIVE_ORDER.compare(str1, str2);
         if (res == 0) {
            res = str1.compareTo(str2);
         }
         return res;
      }
   };
   
   
   @Override
   public String toString(){
      String all="";
      for(Postings p:postingsList){
         all+=p.toString();
      }
      return all; 
   }

   /**
    * This method is responsible for writing the data into the file.
    *
    * @param s
    */
   public void writeList(String s) {
      Scanner scanner = null;
      PrintWriter writer = null;
      try {
         scanner = new Scanner(s);
         writer = new PrintWriter(doc.getFile(), "UTF-8");
         while (scanner.hasNextLine()) {
            writer.println(scanner.nextLine() + "\n");
         }
      } catch (IOException e) {
         System.err.println("PostingsList.writeList:Error");
         e.toString();
      } finally {
         scanner.close();
         writer.close();
      }
   }

   /**
    * This method is responsible for writing the data into the file.
    */
   public void writeList(){
      try (PrintWriter writer = new PrintWriter(doc.getFile(), "UTF-8")) {
         for(Postings post:postingsList){
            writer.println(post.toString());
         }
      } catch (FileNotFoundException| UnsupportedEncodingException e) {
         System.err.println("PostingsList.writeList:Error");
         e.toString();
      }
   }
   
   /**
    * This method is responsible for writing the data into the file.
    * @param sorted
    */
   public void writeTermList(File file){
      ArrayList<String> sorted=new  ArrayList<>();
      try (PrintWriter writer = new PrintWriter(file, "UTF-8")) {
         for(Postings post:postingsList){
            sorted.add(post.terms.get(0).getTerm());
         }
         sorted.sort(ALPHABETICAL_ORDER);
         for(String s:sorted){
         writer.println(s.toString());
         }
      } catch (FileNotFoundException| UnsupportedEncodingException e) {
         System.err.println("PostingsList.writeList:Error");
         e.toString();
      }
   }
}
