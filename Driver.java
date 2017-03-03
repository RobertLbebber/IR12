package Driver;

import Analyzing.Document;
import Analyzing.DocumentAnalyzer;
import Searching.Search;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author rl07bebb
 */
public class Driver {


    /**
     * THis method runs the Document Analysis class which calls create most of the data. This method also creates much
     * of the outputs and results.
     *
     * @param args
     */
   public static void main(String args[]) {
      File stopList = null;
      File dir = null;
      File postingFile = null;
      File termsList =null;
      try {
         stopList = new File("src/Analyzing/stoplist.txt");
         dir = new File("Data");
         postingFile = new File("src/Indexing/PostingFile.txt");
         postingFile.createNewFile();
         termsList = new File("src/Indexing/TextFile.txt");
      } catch (IOException e) {
         System.out.println("Exception Occurred:");
      }
      if (dir != null) {
          File[] docs = dir.listFiles((File dir1, String filename) -> filename.endsWith(".txt"));
          DocumentAnalyzer docAnal = new DocumentAnalyzer(stopList, postingFile, termsList, docs);
      }
   }
}
