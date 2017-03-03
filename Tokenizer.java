/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analyzing;

import Indexing.Dictionary;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This class is used to remove stop words and remove punctuation of words.
 *
 * @author rl07bebb
 */
public class Tokenizer {

   ArrayList<String> stopWords;
   Pattern pattern = Pattern.compile("[\\W\\d]");

   public Tokenizer() {
   }

   /**
    * This method is responsible for creating a list of words to be ignored
    *
    * @param stopList
    */
   public Tokenizer(File stopList) {
      stopWords = new ArrayList<>();
      Scanner scanner = null;
      try {
         scanner = new Scanner(stopList);
         while (scanner.hasNext()) {
            stopWords.add(pattern.matcher(scanner.next()).replaceAll(""));
         }
      } catch (IOException e) {
         System.err.println("Tokenizer.Constructor:Error");
         e.toString();
      } finally {
         if (scanner != null) {
            scanner.close();
         }
      }
   }

   /**
    * This method reads from the doc file and accounts for ever term and calls
    * the proceeding methods for the inclusion of the terms to the dictionary.
    *
    * @param doc the document to be read from
    * @param dictionary the dictionary to be used. static Dictionary by default
    */
   public void readFile(Document doc, Dictionary dictionary) {
      int count = 0;
      try (Scanner scanner = new Scanner(doc.getFile())) {
         while (scanner.hasNext()) {
            String word = scanner.next().toLowerCase();
            word = pattern.matcher(word).replaceAll("");
            if (word.length() > 0) {
               count++;
               if (stopWords.contains(word)) {
               } else {
                  dictionary.checkNewTerm(word, doc, count);
               }
            }
         }
      } catch (IOException e) {
         System.err.println("Tokenizer.readFile():Error");
         e.toString();
      }
   }

   /**
    * This method is suppose to preform the same operations as readFile, but
    * includes a local dictionary and is pertaining to a query. See readFile.
    *
    * @param doc
    * @param dictionary
    */
   public void readQuery(String doc, Dictionary dictionary) {
      int count = 0;
      if (doc.equals("")) {
         return;
      }
      Scanner scanner = new Scanner(doc);
      while (scanner.hasNext()) {
         String word = scanner.next().toLowerCase();
         word = pattern.matcher(word).replaceAll("");
         if (word.length() > 0) {
            count++;
            if (stopWords.contains(word)) {
               continue;
            } else {
               dictionary.checkNewTerm(word, null, count);
            }
         }
      }
      scanner.close();
   }

   /**
    * This method returns all elements in the stoplist to string.
    *
    * @return
    */
   public String toStringAll() {
      String all = "";
      if (stopWords == null) {
         return "stopWords = null";
      }
      for (String s : stopWords) {
         all += s;
      }
      return all;
   }

   /**
    * This method prints all elements in the stoplist to string.
    */
   public void toPrintAll() {
      System.out.println(toStringAll());
   }
}
