//source recovered from .class 7-nov-2014 jpm
import com.lowagie.text.Document;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class StackUp
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
      str2 = str2.replaceAll(".pdf\\z", "-StackUp.pdf");
    } else {
      str2 = str2.concat("-StackUp");
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
      

      Document localDocument = new Document(new Rectangle(f2 * 2.0F, f3));
      
      System.out.println("Destination Page size: " + f2 * 2.0F + "x" + f3);
      

      int j = i / 2;
      if (i % 2 > 0) {
        j++;
      }
      System.out.println("Total output pages: " + j);
      

      PdfWriter localPdfWriter = PdfWriter.getInstance(localDocument, new FileOutputStream(str2));
      

      localDocument.open();
      

      PdfContentByte localPdfContentByte = localPdfWriter.getDirectContent();
      for (int k = 1; k <= j; k++)
      {
        localDocument.newPage();
        for (int m = 0; m < 2; m++)
        {
          int n = k + j * m;
          if (n <= i)
          {
            PdfImportedPage localPdfImportedPage = localPdfWriter.getImportedPage(localPdfReader, n);
            
            float f4 = 0.0F;
            float f5 = 0.0F;
            if (m == 1) {
              f4 = f2;
            }
            localPdfContentByte.addTemplate(localPdfImportedPage, f4, f5);
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
