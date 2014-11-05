import java.io.FileOutputStream;
import java.util.Date;

import com.lowagie.text.Document;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

public class saddlestitch{
   public static void main(String[] args){
      long startTime = System.currentTimeMillis();

      int m = 1;
      int n = 0;

      //need 1 arg
      if(args.length != 1){
         System.out.println("Requires PDF filename as command line arg.");
         System.exit(1);
      }

      String inputFile = new String(args[0]);
      String outputFile = new String(inputFile);

      //Generate output filename
      if(outputFile.toLowerCase().endsWith(".pdf")){
         outputFile = outputFile.replaceAll(".pdf\\z", "-SS.pdf");
      } else {
         outputFile = outputFile.concat("-SS");
      }

      try {
         //new reader
         PdfReader inPDF = new PdfReader(inputFile);
      
         //last page
         n = lastPage(inPDF);

         System.out.println("Processing PDF file: " + inputFile);
         System.out.println(n + " pages.");
         System.out.println("Output file: " + outputFile);

         //round up to the nearest multiple of 4
         if((n-m+1)%4>0){
            n += 4 - (n-m+1)%4;
         }
         System.out.println("Processing " + (n - m + 1) + " pages for saddle stitch book.");

         //retrieve first page
         Rectangle psize = inPDF.getPageSize(m);

         //Size of first page
         float width = psize.getWidth();
         float height = psize.getHeight();

         System.out.println("Source Page Size: " + width + "x" + height);

         //new Document
         Document outputPDF = new Document(new Rectangle(width*2, height));

         //writer for output PDF file
         PdfWriter outPDF = PdfWriter.getInstance(outputPDF, new FileOutputStream(outputFile));

         //open output document
         outputPDF.open();

         //Add content
         PdfContentByte cb = outPDF.getDirectContent();

         //process
         while(m < n){
            //side A
            outputPDF.newPage();
            if(n <= lastPage(inPDF)){
               PdfImportedPage p1 = outPDF.getImportedPage(inPDF, n--);
               cb.addTemplate(p1, 0, 0);
            }else{
               n--;
            }

            PdfImportedPage p2 = outPDF.getImportedPage(inPDF, m++);
            cb.addTemplate(p2, width + 1, 0);

            //side B
            outputPDF.newPage();
            PdfImportedPage p3 = outPDF.getImportedPage(inPDF, m++);
            cb.addTemplate(p3, 0, 0);

            if(n <= lastPage(inPDF)){
               PdfImportedPage p4 = outPDF.getImportedPage(inPDF, n--);
               cb.addTemplate(p4, width + 1, 0);
            }else{
               n--;
            }
         }

         //close the new document
         outputPDF.close();
      }
      catch (Exception de){
         de.printStackTrace();
      }

      //calculate and display run time
      long processTime = (System.currentTimeMillis() - startTime) / 1000;
      System.out.println("Processing Time: " + processTime);
   }

   //-----------------------------------------------------------------
   // lastPage(PdfReader) - returns the number of pages in the PDF
   //                       file.
   //                       Today it just wraps the .getNumberOfPages
   //                       method of the PdfReader...later it will
   //                       calculate a virtual number of pages
   //                       taking in account blanks requested.
   //-----------------------------------------------------------------
   private static int lastPage(PdfReader pdfFile){
      int pages;

      pages = pdfFile.getNumberOfPages();

      return(pages);
  }
}
