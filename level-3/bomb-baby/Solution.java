import java.math.BigInteger;
public class Solution {
    public static String solution(String x, String y) {
        // Your code here
        BigInteger bx = new BigInteger(x);
        BigInteger by = new BigInteger(y);
        BigInteger max = (bx.compareTo(by) >= 0)?bx:by;
        BigInteger min = (bx.compareTo(by) < 0)?bx:by;
        BigInteger one = new BigInteger("1");
        BigInteger zero = new BigInteger("0");
        BigInteger count = new BigInteger("0");
        BigInteger temp = new BigInteger("0");
        while(min.compareTo(one) == 1 && max.compareTo(one) == 1){
            temp = (max.compareTo(min) >= 0)?max:min;
            min = (max.compareTo(min) < 0)?max:min;
            max = temp;
            if(max.mod(min).compareTo(zero) == 0) return "impossible";
            count = count.add(max.divide(min));
            max = max.mod(min);

//            System.out.println(count);
        }
        temp = (max.compareTo(min) >= 0)?max:min;
        min = (max.compareTo(min) < 0)?max:min;
        max = temp;
        count = count.add(max).subtract(one);
        return count.toString();
    }
}