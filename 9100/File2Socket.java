/*
 * Java program to send file to port
 *
 */


import java.net.*;
import java.io.*;

public class File2Socket {
   public static void main(String[] args) throws IOException {
      Integer writingPort = new Integer(9100);
      String fileName = new String();
	  String eol = new String("\r\n");

      Socket socket = null;
	  DataOutputStream Printer = null;
      BufferedReader in = null;
		
      fileName = "printme.txt";

	  BufferedReader br = new BufferedReader(new FileReader(fileName));
	
      try {
	     socket = new Socket("10.10.0.155", writingPort);   //dave
//	     socket = new Socket("10.10.0.108", writingPort);   //wrkrm
//	     socket = new Socket("localhost", writingPort);
		 Printer = new DataOutputStream(socket.getOutputStream());
      } catch (IOException e) {
         System.err.println("Could not write to port: " + writingPort + ".");
         System.exit(-1);
      }
	  
	  
      try {
        String line = br.readLine();

        while (line != null) {
            Printer.writeBytes(line);
			Printer.writeBytes(eol);
			line = br.readLine();
        }
      } finally {
        br.close();
   }


	  }
}
