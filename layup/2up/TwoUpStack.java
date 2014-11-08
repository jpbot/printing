import java.io.FileOutputStream;
import java.util.Date;

import com.lowagie.text.Document;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.BaseFont;

public class TwoUpStack{
   public static void main(String[] args){
      long startTime = System.currentTimeMillis();

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
         outputFile = outputFile.replaceAll(".pdf\\z", "-2U.pdf");
      } else {
         outputFile = outputFile.concat("-2U");
      }

      try {
         //new reader
         PdfReader inPDF = new PdfReader(inputFile);
      
         //last page
         n = lastPage(inPDF);

         System.out.println("Processing PDF file: " + inputFile);
         System.out.println(n + " pages.");
         System.out.println("Output file: " + outputFile);

         //retrieve first page
         Rectangle psize = inPDF.getPageSize(1);

         //Size of first page
         float width = psize.getWidth();
         float height = psize.getHeight();

         //Size of output page
         float outWidth;
         float outHeight;

         //Choose 2-up orientation
         if(width < height){
            outWidth = width * 2;
            outHeight = height;
         } else {
            outWidth = width;
            outHeight = height * 2;
         }

         System.out.println("Source Page Size: " + width + "x" + height);

         //new Document
         Document outputPDF = new Document(new Rectangle(outWidth, outHeight));

         System.out.println("Destination Page size: " + outWidth + "x" + outHeight);

         System.out.println("Total pages: " + n);

         //writer for output PDF file
         PdfWriter outPDF = PdfWriter.getInstance(outputPDF, new FileOutputStream(outputFile));

         //open output document
         outputPDF.open();

         //Add content
         PdfContentByte cb = outPDF.getDirectContent();

         //process
         for(int i = 1; i <= n; i++){
            //new page for little ones
            outputPDF.newPage();

            //fill the page
            PdfImportedPage p1 = outPDF.getImportedPage(inPDF, i);

            float x = 0f;
            float y = 0f;

            //position 0 copy
            cb.addTemplate(p1, x, y);

            //calculate position 1 on page
            if(width < height){
               x = width;
            }else{
               y = height;
            }
            cb.addTemplate(p1, x, y);
         }

         //close the new document
         outputPDF.close();
      }
      catch (Exception de){
         de.printStackTrace();
      }

      //calculate and display run time
      float processTime = (float)(System.currentTimeMillis() - startTime) / 1000;
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
