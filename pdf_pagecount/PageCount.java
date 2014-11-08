import java.io.*;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;


class PageCount {
    public static void main(String[] args) {
	int argCount = args.length;

        for(int i = 0; i < argCount; i++){
            System.out.println(args[i] + " " + pdfPageCount(args[i]));
        }
    }


    private static int pdfPageCount( String somePDF ){
        try {
            PdfReader reader = new PdfReader(somePDF);

            return reader.getNumberOfPages();
        }
        catch(Exception de){
            return 0;
        }

//        return somePDF.length();
    }
}