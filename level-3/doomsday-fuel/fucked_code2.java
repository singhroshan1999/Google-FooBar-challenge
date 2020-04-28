/**
 * CREATED BY ROSHAN SINGH
 *
 */
import java.io.*;
import java.util.*;
import java.math.BigInteger;

class fraction{
    int num,den;
    double dec;
    public fraction(int a,int b){
        this.num = a;
        this.den = b;
        dec = ((double)a)/b;
    }
    fraction multiply(fraction o){
        int a = o.num*this.num;
        int b = o.den*this.den;
        int hcf = getGCD(a,b);
        a = a/hcf;
        b = b/hcf;
        return new fraction(a,b);
    }
    static int getGCD(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return getGCD(n2, n1 % n2);
    }
}
class Solution {
    static  int[] fracion(double x){
//    if (x < 0){
//        return "-" + fracion(-x);
//    }
        double tolerance = 1.0E-6;
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

    public static double[][] invert(double a[][]) {
        int n = a.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        int index[] = new int[n];
        for (int i=0; i<n; ++i)
            b[i][i] = 1;
        gaussian(a, index);
        for (int i=0; i<n-1; ++i)
            for (int j=i+1; j<n; ++j)
                for (int k=0; k<n; ++k)
                    b[index[j]][k]
                            -= a[index[j]][i]*b[index[i]][k];
        for (int i=0; i<n; ++i)
        {
            x[n-1][i] = b[index[n-1]][i]/a[index[n-1]][n-1];
            for (int j=n-2; j>=0; --j)
            {
                x[j][i] = b[index[j]][i];
                for (int k=j+1; k<n; ++k)
                {
                    x[j][i] -= a[index[j]][k]*x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return x;
    }
    public static void gaussian(double a[][], int index[])
    {
        int n = index.length;
        double c[] = new double[n];
        for (int i=0; i<n; ++i)
            index[i] = i;
        for (int i=0; i<n; ++i) {
            double c1 = 0;
            for (int j=0; j<n; ++j)
            {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }
        for(double[] i : a) System.out.println(Arrays.toString(i));

        int k = 0;
        for (int j=0; j<n-1; ++j)
        {
            double pi1 = 0;
            for (int i=j; i<n; ++i)
            {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1)
                {
                    pi1 = pi0;
                    k = i;
                }
            }
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i=j+1; i<n; ++i)
            {
                double pj = a[index[i]][j]/a[index[j]][j];
                System.out.println(pj);
                a[index[i]][j] = pj;
                for (int l=j+1; l<n; ++l)
                    a[index[i]][l] -= pj*a[index[j]][l];
            }
        }
    }

    public static int[] solution(int[][] m) {
        // Your code here
        double[][] p = new double[m.length][m.length];
        double[][] p2 = new double[m.length][m.length];
        double[] summ = new double[m.length];
//        Map<Integer, Integer> map = new HashMap<>();
        int[] map = new int[m.length];
        for(int i = 0;i<map.length;i++) map[i] = -1;

        for (int i = 0; i < m.length; i++) {
            double sum = 0;
            for (int j = 0; j < m.length; j++) sum += m[i][j];
            if (sum == 0) {
                summ[i] = 0;
                p[i][i] = 1.0;
                p2[i][i] = 1.0;
                continue;
            }
            for (int j = 0; j < m.length; j++) {
                p[i][j] = m[i][j] / sum;
                p2[i][j] = p[i][j];
                summ[i] = sum;
            }
        }
        int count = 0;
        for (int i = 0; i < m.length; i++) {
            if (summ[i] == 0) {
//                p[count][count] = 1.0;
                map[i] = count;
//                map.put(i, count);
                count++;
            }
        }
        int div = count;
        for (int i = 0; i < m.length; i++) {
            if (map[i] == -1) {
                map[i] = count;
                count++;
            }
        }
        double[][] p3 = new double[m.length][m.length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                p3[map[i]][map[j]] = p2[i][j];
            }
        }
//        for (double[] i : p) System.out.println(Arrays.toString(i));
//        System.out.println("`````````````"+map);
//        for (double[] i : p3) System.out.println(Arrays.toString(i));
        double[][] Q = new double[m.length-div][m.length-div];
        double[][] R = new double[m.length-div][div];
        for (int i = 0; i < m.length-div; i++) {
            for (int j = 0; j < m.length-div; j++) {
                Q[i][j] = p3[div+i][div+j];
            }
        }
        for (int i = 0; i < m.length-div; i++) {
            for (int j = 0; j < div; j++) {
                R[i][j] = p3[div+i][j];
            }
        }
        for (int i = 0; i < Q.length; i++) {
            for (int j = 0; j < Q.length; j++) {
                Q[i][j] = (i==j)?1:0 - Q[i][j];
            }
        }
//        double[][] F = null;
//        Solution.N = F.length;
        double[][] F = invert(Q);//inverse(Q,F);
//        for (double[] i : F) System.out.println(Arrays.toString(i));
        double[][] FR = new double[F.length][div];
        for (int i = 0; i < F.length; i++) {
            for (int j = 0; j < div; j++) {
                for (int k = 0; k < F.length; k++) {
                    double aaa = F[i][k];
                    double xxx = aaa*R[k][j];
                    FR[i][j] += xxx;
                }
            }
        }
//        for (double[] i : FR) System.out.println(Arrays.toString(i));
        int[][] num = new int[FR[0].length][2];
        int lcm = 1;
        for (int i = 0; i < FR[0].length; i++) {
            int[] temp = fracion(FR[0][i]);
            num[i][0] = temp[0];
            num[i][1] = temp[1];
            lcm = (lcm*temp[1])/getGCD(lcm,temp[1]);
        }
//        for(int[] i : num)System.out.println(i[0]+" "+i[1]);
        int[] result = new int[num.length+1];
        for (int i = 0; i < num.length; i++) {
            result[i] = num[i][0]*(lcm/num[i][1]);
        }
        result[result.length-1] = lcm;
//        int[] result = new int[ll.size()];
//        int size = ll.size();
////        System.out.println(ll);
//        for (int i = 0; i < size; i++) {
//            result[i] = ll.get(i);
//        }
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