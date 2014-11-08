//source recovered from .class file 7-nov-2014 jpm
import com.lowagie.text.Document;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class ThreeLatteral
{
  public static void main(String[] paramArrayOfString)
  {
    long l = System.currentTimeMillis();
    
    int i = 0;
    if (paramArrayOfString.length != 1)
    {
      System.out.println("Requires PDF filename as command line arg.");
      System.exit(1);
    }
    String str1 = new String(paramArrayOfString[0]);
    String str2 = new String(str1);
    if (str2.toLowerCase().endsWith(".pdf")) {
      str2 = str2.replaceAll(".pdf\\z", "-3U.pdf");
    } else {
      str2 = str2.concat("-3U");
    }
    try
    {
      PdfReader localPdfReader = new PdfReader(str1);
      

      i = lastPage(localPdfReader);
      
      System.out.println("Processing PDF file: " + str1);
      System.out.println(i + " pages.");
      System.out.println("Output file: " + str2);
      







      Rectangle localRectangle = localPdfReader.getPageSize(1);
      

      float f2 = localRectangle.getWidth();
      float f3 = localRectangle.getHeight();
      
      System.out.println("Source Page Size: " + f2 + "x" + f3);
      

      Document localDocument = new Document(new Rectangle(f2, f3 * 3.0F));
      
      System.out.println("Destination Page size: " + f2 + "x" + f3 * 2.0F);
      

      int j = i / 3;
      if (i % 3 > 0) {
        j++;
      }
      System.out.println("Total output pages: " + j);
      

      PdfWriter localPdfWriter = PdfWriter.getInstance(localDocument, new FileOutputStream(str2));
      

      localDocument.open();
      

      PdfContentByte localPdfContentByte = localPdfWriter.getDirectContent();
      for (int k = 1; k <= j; k++)
      {
        localDocument.newPage();
        for (int m = 0; m < 3; m++)
        {
          int n = k + j * m;
          if (n <= i)
          {
            PdfImportedPage localPdfImportedPage = localPdfWriter.getImportedPage(localPdfReader, n);
            
            float f4 = 0.0F;
            float f5 = 0.0F;
            

            int i1 = 2 - m;
            f5 = f3 * i1;
            localPdfContentByte.addTemplate(localPdfImportedPage, f4, f5);
            

            localPdfContentByte.beginText();
            BaseFont localBaseFont = BaseFont.createFont("Courier-Bold", "Cp1252", false);
            localPdfContentByte.setFontAndSize(localBaseFont, 10.0F);
            String str3 = String.valueOf(n);
            

            localPdfContentByte.setTextMatrix(f4 + 480.0F, f5 + 16.0F);
            localPdfContentByte.showText(str3);
            localPdfContentByte.endText();
          }
        }
      }
      localDocument.close();
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    float f1 = (float)(System.currentTimeMillis() - l) / 1000.0F;
    System.out.println("Processing Time: " + f1);
  }
  
  private static int lastPage(PdfReader paramPdfReader)
  {
    int i = paramPdfReader.getNumberOfPages();
    
    return i;
  }
}
