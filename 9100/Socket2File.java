/*
 * Java program to listen on port 9100 and write out files
 *
 */


import java.net.*;
import java.io.*;

public class Socket2File {
   public static void main(String[] args) throws IOException {
      Integer listeningSocket = new Integer(9100);
      String fileName = new String();
      Integer connectionNumber = new Integer(0);

      ServerSocket ss = null;
      boolean listening = true;

      try {
         ss = new ServerSocket(listeningSocket);
      } catch (IOException e) {
         System.err.println("Could not listen on port: " + listeningSocket + "."
);
         System.exit(-1);
      }

      while (listening){
         fileName = getDate.now() + "-" + connectionNumber;
         new Socket2FileThread(ss.accept(), fileName).start();
         connectionNumber++;
      }
   }
}
