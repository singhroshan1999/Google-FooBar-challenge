/**
 * CREATED BY ROSHAN SINGH
 *
 */
import java.io.*;
import java.util.*;
import java.math.BigInteger;
class Solution {
    static  int[] fracion(double x){
//    if (x < 0){
//        return "-" + fracion(-x);
//    }
        double tolerance = 1.0E-12;
        double h1=1; double h2=0;
        double k1=0; double k2=1;
        double b = x;
        do {
            double a = Math.floor(b);
            double aux = h1; h1 = a*h1+h2; h2 = aux;
            aux = k1; k1 = a*k1+k2; k2 = aux;
            b = 1/(b-a);
        } while (Math.abs(x-h1/k1) > x*tolerance);

        return new int[]{(int)h1,(int)k1};
    }

    static int getGCD(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return getGCD(n2, n1 % n2);
    }
    static boolean check(double[][] a,double[][] b){
        boolean result = true;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                result = (a[i][j] == b[i][j])?true:false;
            }
        }
        return result;
    }
    static void multiply(double mat1[][],
                         double mat2[][], double res[][],int N)
    {
        int i, j, k;
        for (i = 0; i < N; i++)
        {
            for (j = 0; j < N; j++)
            {
                res[i][j] = 0;
                for (k = 0; k < N; k++)
                    res[i][j] += mat1[i][k]
                            * mat2[k][j];
            }
        }
    }

    public static int[] solution(int[][] m) {
        // Your code here
        double[][] p = new double[m.length][m.length];
        double[][] p2 = new double[m.length][m.length];
        double[] summ = new double[m.length];
        for (int i = 0; i < m.length; i++) {
            double sum = 0;
            for (int j = 0; j < m.length; j++) sum += m[i][j];
            if(sum == 0){
                summ[i] = 0;
                p[i][i] = 1.0;
                p2[i][i] = 1.0;
                continue;
            }
            for (int j = 0; j < m.length; j++) {
                p[i][j] = m[i][j]/sum;
                p2[i][j] = p[i][j];
                summ[i] = sum;
            }
        }
        boolean flag = false;
        while(!flag){
//            flag = true;
            double[][] temp = new double[m.length][m.length];
            multiply(p,p2,temp,m.length);
            flag = check(p2,temp);
            p2 = temp;
        }
        double[] x = new double[m.length];
        double[] x2 = new double[m.length];
        x[0] = 1.0;
        for (int i = 0; i < m.length; i++) {
            double sum = 0.0;
            for (int j = 0; j < m.length; j++) {
                sum+= x[j]*p2[j][i];
            }
            x2[i] = sum;
        }
        System.out.println(Arrays.toString(p2[0]));
        System.out.println(Arrays.toString(x2));

        int[][] num = new int[m.length][2];
        int lcm = 1;
        for (int i = 0; i < m.length; i++) {
            int[] temp = fracion(x2[i]);
            num[i][0] = temp[0];
            num[i][1] = temp[1];
            lcm = (lcm*temp[1])/getGCD(lcm,temp[1]);
        }

        for(int[] i : num)System.out.println(Arrays.toString(i));
        LinkedList<Integer> ll = new LinkedList<>();
        int summm = 0;
        for (int i = 0; i < m.length; i++) {
            if(summ[i] == 0){
                int temppp = num[i][0]*(lcm/num[i][1]);
                summm+= temppp;
                ll.add(temppp);
            }
        }
        ll.add(lcm);
        int[] result = new int[ll.size()];
        int size = ll.size();
//        System.out.println(ll);
        for (int i = 0; i < size; i++) {
            result[i] = ll.get(i);
        }
        return result;
    }
}
class Main{
    static class FastScanner {  // for string + number
        BufferedReader br;
        StringTokenizer st;
        public FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        public FastScanner(String file)  throws IOException{
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        }
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException  e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt() {
            return Integer.parseInt(next());
        }
        long nextLong() {
            return Long.parseLong(next());
        }
        double nextDouble() {
            return Double.parseDouble(next());
        }
        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
    public static void main(String[] args) throws IOException {
        FastScanner s = new FastScanner();
//        FastScanner s = new FastScanner("INPUT");
        OutputStream outputStream = System.out;
//        OutputStream outputStream = new FileOutputStream("OUTPUT");
        PrintWriter o = new PrintWriter(outputStream);
        // start
//        int t = s.nextInt();
//        while(t-->0){
//            int n = s.nextInt();
//            String[] st = new String[n];
//        for (int i = 0; i < n; i++) {
//            st[i] = s.next();
//        }
        long time =  System.nanoTime();
        int[] out = Solution.solution(new int[][]{{0, 1, 0, 0, 0, 1}, {4, 0, 0, 3, 2, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}});
        long escap = System.nanoTime()  - time;
        o.println(Arrays.toString(out));
        o.println((escap/1000000)+"ms");
//        }
        // end
        o.close();
    }
}