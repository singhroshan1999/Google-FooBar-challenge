public class Solution {
    public static String solution(long x, long y) {
        //Your code here
        long base_indx = y + (x - 1);
        long base = ((base_indx)*(base_indx+1))/2;
        long result = base - (y - 1);
        return Long.toString(result);
    }
}