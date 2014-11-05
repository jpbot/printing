import java.net.*;
import java.io.*;

public class Socket2FileThread extends Thread{
   private Socket socket = null;
   private String fileName = null;

   public Socket2FileThread(Socket socket, String fileName) {
      super("Socket2FileThread");
      this.socket = socket;
      this.fileName = fileName;
   }

   public void run() {
      try {
         //Create a file
         FileWriter fstream = new FileWriter(fileName);
         BufferedWriter out = new BufferedWriter(fstream);

         BufferedReader in = new BufferedReader(
                                 new InputStreamReader(
                                 socket.getInputStream()));

         //String inputLine;
         int inputChar;

         while ((inputChar = in.read()) >= 0) {
            out.write(inputChar);
         }
         out.close();
         in.close();
         socket.close();

      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
