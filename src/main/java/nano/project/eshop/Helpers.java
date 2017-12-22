package nano.project.eshop;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Helpers {
    public static String dateFormater(Date date,String format){
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat(format);
        return  simpleDateFormat.format(date);
    }
}
