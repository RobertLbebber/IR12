/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analyzing;

import Indexing.Dictionary;
import Indexing.PostingsList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * @author rl07bebb
 */
public class DocumentAnalyzer {

   public DocumentAnalyzer(File stoplist, File PostingFile, File termsList, File... args) {
      PostingsList postIndex = new PostingsList(new Document(PostingFile));
      Dictionary fullDictionary = new Dictionary(postIndex);
      Tokenizer token = new Tokenizer(stoplist);
      for (File arg : args) {
         token.readFile(new Document(arg, arg.getName(), arg.length()), fullDictionary);
      }
      /*
       for (int i = 0; i < 100; i++) {
       token.readFile(new Document(args[i], args[i].getName(), args[i].length()), fullDictionary);
       }
       */
      postIndex.writeList();
      //postIndex.writeTermList(termsList);/*
      /*
      try {
         //FileOutputStream out = new FileOutputStream("output.ser");
         //ObjectOutputStream oout = new ObjectOutputStream(out);
         //oout.writeObject(fullDictionary);
         //oout.flush();
         //oout.close();
      } catch (Exception e) {
         System.err.println("DocumentAnalyzer.Constructor: FileStream");
         e.printStackTrace();
      }*/
   }
}
