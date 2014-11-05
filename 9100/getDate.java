import java.util.Calendar;
import java.text.SimpleDateFormat;

public class getDate {
   public static final String DATE_FORMAT_NOW = "yyyy-MM-dd_HH:mm:ss";

   public static String now() {
      Calendar cal = Calendar.getInstance();
      SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
      return sdf.format(cal.getTime());
   }

   public static void  main(String arg[]) {
      System.out.println("Now : " + now());
   }
}

