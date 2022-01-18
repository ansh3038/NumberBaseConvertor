package converter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {
    static void exit(){
        System.exit(0);

    }
    static String  from(String str, int a,int b){
        char[] chars= str.toCharArray();
        BigDecimal d1 = BigDecimal.ZERO;
        for( int i= chars.length-1; i>=0;i--){
            BigDecimal temp= new BigDecimal(Character.getNumericValue(chars[i]));
            d1= (temp.add(d1).divide(BigDecimal.valueOf(a),5, RoundingMode.HALF_DOWN)) ;

        }
        String ans= to(d1.toPlainString(),b);
        return (ans);
    }
    static String  to(String str, int b){
        BigDecimal d1= new BigDecimal(str);
        BigDecimal d2=new BigDecimal(b).setScale(0);
//        System.out.println(d1);

        String ans="";
        int count=0;
        while(count!=5){
            count++;

            d1=d1.multiply(d2);
            ans+=d1.toBigInteger().toString(b);
//            System.out.println(d1);
            BigDecimal i1=new BigDecimal(d1.toBigInteger());
            d1=d1.subtract(i1);

        }
        return  ans;




    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.print("Enter two numbers in format: {source base} { target base}(To quit type /exit)");
            try {
                String str = scanner.nextLine().trim();
                if (str.equals("/exit")){
//                    System.out.println("exit");
                    exit();
                    break;
                }
                String[] st = str.split(" ");
                int a = Integer.parseInt(st[0]);
                int b = Integer.parseInt(st[1]);
           while(true) {
               System.out.println("Enter number in base " + a + " to convert to base " + b + " (To go back type /back)");
               String num = scanner.next();
               if (num == "/back") {
                   break;
               }
               String[] numb = {num, ""};
               numb = num.split("\\.");
               if ( numb.length > 1) {
                   BigInteger number = new BigInteger(numb[0], a);
                   String b1 = number.toString(b);
                   String b2 = "";
                   for (char c : numb[1].toCharArray()) {
                       b2 += Character.getNumericValue(c);
                   }
                   b2 = "." + b2;
                   BigDecimal d1 = new BigDecimal(b2);
                   String r1 = d1.toString();
                   b2 = "." + from(numb[1], a, b);

                   System.out.println("Conversion result: " + b1 + b2);
               }
               else{
                   BigInteger number = new BigInteger(num, a);
                   String b1 = number.toString(b);
                   System.out.println("Conversion result: " + b1);


               }
           }}
            catch (NumberFormatException e){

            }

        }
    }
}
