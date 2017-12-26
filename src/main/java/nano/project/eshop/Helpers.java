package nano.project.eshop;

import nano.project.eshop.models.Order;
import nano.project.eshop.models.OrderMethod;
import nano.project.eshop.models.OrderStatus;

import java.awt.geom.FlatteningPathIterator;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.function.DoubleBinaryOperator;

public class Helpers {
    public static String getLabel(String status){
        switch (status){
            case OrderStatus.BEING_PREPARED :
                return "label-info";
            case OrderStatus.CANCELED :
                return "label-danger";
            case OrderStatus.ON_HOLD:
                return "label-warning";
            case OrderStatus.RECEIVED:
                return "label-success";
        }
        return "";
    }
    public static  Float getSubTotal(Order order){
        float sum =0;
        for (int i = 0; i < order.getOrderLines().size() ; i++) {
            sum +=order.getOrderLines().get(i).getProduct().getPrice() *order.getOrderLines().get(i).getQuantity();
        }
        return  sum;
    }
    public static  Float getTotalPrice(Order order){
        float sum =0;
        for (int i = 0; i < order.getOrderLines().size() ; i++) {
            sum +=order.getOrderLines().get(i).getProduct().getPrice() *order.getOrderLines().get(i).getQuantity();
        }
        sum += getMethodWorth(order.getMethod());
        return  sum;
    }
    public static  Float getMethodWorth(String method){
        switch (method){
            case OrderMethod.COCKROACH :
                return  5.0f;
            case OrderMethod.DOGGYDOGGO :
                return 15.0f;
            case OrderMethod.RAILRATS :
                return 30.0f ;
        }
        return 0.0f;
    }
    public static String dateFormater(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
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
