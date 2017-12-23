package nano.project.eshop;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Helpers {
    public static String dateFormater(Date date,String format){
        SimpleDateFormat simpleDateFormat= new SimpleDateFormat(format);
        return  simpleDateFormat.format(date);
    }
    public static String gencode9() {
        Random rand = new Random();
        String rands = "";
        for (int i = 1; i <= 9; i++) {
            int x = rand.nextInt(3) + 1;
            switch (x) {
                case 1:
                    rands += Character.toString((char) (rand.nextInt(10) + 48));
                    break;
                case 2:
                    rands += Character.toString((char) (rand.nextInt(26) + 97));
                    break;
                case 3:
                    rands += Character.toString((char) (rand.nextInt(26) + 65));
                    break;
            }
        }
        return rands;
    }
}
