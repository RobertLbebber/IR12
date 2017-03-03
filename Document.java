/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analyzing;

import java.io.File;
import java.nio.file.Path;

/**
 * This Class is basically a simplified version of the File class that it holds.
 *
 * @author rl07bebb
 */
public class Document {

   private File file;
   private String docId;
   private long size;

   /**
    * This Constructor is used to build a Document.
    *
    * @param file
    * @param s
    * @param i
    */
   public Document(File file, String s, long i) {
      this.file = file;
      docId = s;
      size = i;
   }

   /**
    * This Constructor is used to build a Document.
    *
    * @param s
    */
   public Document(String s) {
      docId = s;
      size = s.length();
   }

   /**
    * This Constructor is used to build a Document.
    *
    * @param file
    */
   public Document(File file) {
      this.file = file;
      docId = file.getName();
      size = file.length();
   }

   /**
    * This method gets the DocId
    *
    * @return
    */
   public String getDocID() {
      return docId;
   }

   /**
    * This method gets the File
    *
    * @return
    */
   public File getFile() {
      return file;
   }

   /**
    * This method gets the Path
    *
    * @return
    */
   public String getPath() {
      return file.getPath();
   }
}
