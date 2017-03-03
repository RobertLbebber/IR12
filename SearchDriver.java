package Driver;

import Analyzing.Document;
import Indexing.Dictionary;
import Indexing.Postings;
import Indexing.PostingsList;
import Searching.Search;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Created by robert on 2/27/17.
 */
public class SearchDriver {

   public static void main(String args[]) {
      File search = null;
      File results = null;
      File postingFile = null;
      File stopList = null;
      PostingsList searchPost = new PostingsList();
      try {
         search = new File("src/Searching/Search.txt");
         results = new File("src/Searching/Results.txt");
         postingFile = new File("src/Indexing/PostingFile.txt");
         stopList = new File("src/Analyzing/stoplist.txt");
      } catch (Exception e) {
      }

      if (postingFile == null || postingFile.length() == 0) {
         return;
      }
      try {
         //ObjectInputStream ois = new ObjectInputStream(new FileInputStream("output.ser"));
         //Dictionary fullDictionary = (Dictionary)ois.readObject();
         Search searching = new Search(stopList, searchPost);
         Dictionary dictionary = null;
         String s;
         if (true) {
            s = searching.searchFromPosting(search, postingFile);
         } else {
            String q="to be or not to be that is the question for an old man who knows much there is Sweden still much to learn Sweden old old. -old .lo";
            dictionary = searching.search(q);
         }
         if (dictionary == null || dictionary.size() == 0) {
         }
         System.out.println(s);
         new PostingsList(new Document(results)).writeList(s);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
