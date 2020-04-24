class Solution {
    public static String solution(String s) {
        // Your code here
        final String[] braille = new String[]{
                "100000", //a
                "110000", //b
                "100100", //c
                "100110", //d
                "100010", //e
                "110100", //f
                "110110", //g
                "110010", //h
                "010100", //i
                "010110", //j
                "101000", //k
                "111000", //l
                "101100", //m
                "101110", //n
                "101010", //o
                "111100", //p
                "111110", //q
                "111010", //r
                "011100", //s
                "011110", //t
                "101001", //u
                "111001", //v
                "010111", //w
                "101101", //x
                "101111", //y
                "101011"  //z
        };
        final String cap = "000001";
        final String sp = "000000";

        StringBuilder o = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == ' ') {
                o.append(sp);
            } else if (ch >= 'a' && ch <= 'z') {
                o.append(braille[ch - 'a']);
            } else {
                o.append(cap);
                o.append(braille[ch - 'A']);
            }
        }
        return o.toString();
    }
}