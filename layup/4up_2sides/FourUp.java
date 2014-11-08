import java.io.FileOutputStream;
import java.util.Date;

import com.lowagie.text.Document;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.BaseFont;

public class FourUp{
   public static void main(String[] args){
      long startTime = System.currentTimeMillis();

	  float destWidth = 0f;
	  float destHeight = 0f;

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
         outputFile = outputFile.replaceAll(".pdf\\z", "-4U.pdf");
      } else {
         outputFile = outputFile.concat("-4U");
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

         System.out.println("Source Page Size: " + width + "x" + height);

         //new Document
		 destWidth = width * 2;
		 destHeight = height * 2;

         Document outputPDF = new Document(new Rectangle(destWidth, destHeight));

         System.out.println("Destination Page size: " + destWidth + "x" + destHeight);

         //number of output pages
         int pcount = n/8;
         if(n % 8 > 0){
            pcount+=2;
         }
		 pcount*=2;

         System.out.println("Total output pages: " + pcount);

         //writer for output PDF file
         PdfWriter outPDF = PdfWriter.getInstance(outputPDF, new FileOutputStream(outputFile));

         //open output document
         outputPDF.open();

         //Add content
         PdfContentByte cb = outPDF.getDirectContent();

         //process
         for(int i = 1; i <= pcount; i++){
            //new page for little ones
            outputPDF.newPage();

            //fill the page (side 1)
            for(int pos = 0; pos < 4; pos++){
               int page = i + pcount * pos;
               if(page <= n){
                  PdfImportedPage p1 = outPDF.getImportedPage(inPDF, page);

                  float x = 0f;
                  float y = 0f;

                  //calculate position on page
                  int humanPos = 3 - pos;
                  if(humanPos>1){
                     y = height * (humanPos-2);
					 if(i % 2 > 0){
						x = width;
					 }
                  }else{
				     if(i % 2 == 0){
						x = width;
				     }
                     y = height * humanPos;
                  }
                  cb.addTemplate(p1, x, y);

                  //label the page
                  cb.beginText();
                  BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                  cb.setFontAndSize(bf, 8);
                  String pageNumber = String.valueOf(page);

                  //put the text 24 points right and 16 points above the bottom of the page position
                  cb.setTextMatrix(x + 24, y + 16);
                  cb.showText(pageNumber);
                  cb.endText();
               }
            }
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
