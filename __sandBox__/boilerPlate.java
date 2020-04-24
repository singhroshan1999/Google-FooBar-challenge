/**
 * CREATED BY ROSHAN SINGH
 *
 */
import java.io.*;
import java.util.*;
import java.math.BigInteger;
class Solution {
   static void merge(int arr[][], int l, int m, int r,int col)
    {
        int n1 = m - l + 1;
        int n2 = r - m;
        int L[][] = new int [n1][3];
        int R[][] = new int [n2][3];
        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2)
        {
            if (L[i][col] <= R[j][col])
            {
                arr[k] = L[i];
                i++;
            }
            else
            {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1)
        {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2)
        {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
   static void sort(int arr[][], int l, int r,int col)
    {
        if (l < r)
        {
            int m = (l+r)/2;
            sort(arr, l, m,col);
            sort(arr , m+1, r,col);
            merge(arr, l, m, r,col);
        }
    }
    public static String[] solution(String[] l) {
        // Your code here
        int[][] a = new int[l.length][3];
        for (int i = 0; i < l.length; i++) {
            String[] sp = l[i].split("\\.");
            a[i][0] = Integer.parseInt(sp[0]);
            a[i][1] = (sp.length < 2)?-1:Integer.parseInt(sp[1]);
            a[i][2] = (sp.length < 3)?-1:Integer.parseInt(sp[2]);
        }
        sort(a,0,a.length-1,2);
        sort(a,0,a.length-1,1);
        sort(a,0,a.length-1,0);
        String[] out = new String[l.length];
        for (int i = 0;i<l.length;i++){
            out[i] = a[i][0] + ((a[i][1] >=0)?("."+a[i][1]+((a[i][2] >= 0)?("."+a[i][2]):"")):"");
        }
        return out;
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
            String[] out = Solution.solution(new String[]{"1.11", "2.0.0", "1.2", "2", "0.1", "1.2.1", "1.1.1", "2.0"});
            long escap = System.nanoTime()  - time;
            o.println(Arrays.toString(out));
            o.println((escap/1000000)+"ms");
//        }
        // end
        o.close();
    }
}