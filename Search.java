/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Searching;

import Analyzing.Document;
import Analyzing.Tokenizer;
import Indexing.Dictionary;
import Indexing.Postings;
import Indexing.PostingsList;
import Indexing.Terms;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class operates two searching types. One Search is from the driver and
 * will output a response in the Console. The other search type will read a
 * search file and output the file into the posting file that it receives.
 *
 * @author rl07bebb
 */
public class Search {

   private Pattern pattern = Pattern.compile("[\\W\\d\\s]");
   private Dictionary localdic;
   private Tokenizer token;

   public Search(File stopList, PostingsList postingsList) {
      localdic = new Dictionary(postingsList);
      token = new Tokenizer(stopList);
   }

   /**
    * This method is used to search the terms from the search file contained in
    * the dictionary.
    *
    * @param query the user search request.
    * @param postings
    * @return
    */
   public String searchFromPosting(File query, File postings) {
      token.readFile(new Document(query), localdic);
      String results="";
      int count = 0;
      try (Scanner scanner = new Scanner(query)) {
         while (scanner.hasNext()) {
            count++;
            localdic.checkNewTerm(scanner.next(), new Document(query), count);
         }

         Scanner scan = new Scanner(postings);
         while (scan.hasNextLine()) {
            String word = scan.next();
            String words = scan.nextLine();
            Matcher match = pattern.matcher(word);
            if (match.find()) {
               word = match.group();
            }
            Terms term = localdic.getTermName(word);
            if (term != null) {
               results += word + "\n";
            }
         }
         scan.close();
      } catch (Exception e) {
         System.err.println("Tokenizer.readFile():Error");
         e.printStackTrace();
      }
      return results;
   }
   
   /**
    * This method is used to search the terms from the search file contained in
    * the dictionary.
    *
    * @param query the user search request.
    * @param full
    * @param postings
    * @return
    */
   public String searchFromDictionary(File query, Dictionary full) {
      String results = "";
      token.readFile(new Document(query), localdic);
      int count = 0;
      try (Scanner scanner = new Scanner(query)) {
         while (scanner.hasNext()) {
            count++;
            localdic.checkNewTerm(scanner.next(), new Document(query), count);
         }
         Iterator i= (Iterator)localdic.getHash().entrySet().iterator();
         while(i.hasNext()){
            Iterator n=(Iterator) i.next();
            if(full.getHash().containsValue(n)){
               results+=n.toString();
            }
         }
      } catch (IOException e) {
         System.err.println("Tokenizer.readFile():Error");
         e.toString();
      }
      return results;
   }

   /**
    * This method is used to search the dictionary of the terms sent from the
    * query made in the Driver class.
    *
    * @param query the terms sent from the query made by the user
    * @return the resulting posting file
    */
   public Dictionary search(String query) {
      int count = 0;
      token.readQuery(query, localdic);
      Scanner scanner = new Scanner(query);
      while (scanner.hasNext()) {
         String word = scanner.next().toLowerCase();
         word = pattern.matcher(word).replaceAll("");
         localdic.checkNewTerm(word, new Document(query), count);
      }
      return localdic;
   }

   /**
    * This method rights the search into a resulting file.
    *
    * @param results
    * @param doc
    */
   public void toFile(Dictionary results, Document doc) {
      new PostingsList(doc).writeList(results.toString());
   }
}
