package Advanced_DSA_1.Combinatorics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SortedPermutationRank {
    final static int MOD=1000003;
    public static int findRank(String A) {
        int n=A.length();
        long ans=0;
        for(int i=0;i<n;i++){
            int pos=A.charAt(i)-'a';
            int cnt=0;
            //getting to know how many characters less than A.charAt(i) are still not visited
            for(int j=i+1;j<n;j++){
                int posj=A.charAt(j)-'a';
                if(posj<pos){
                    cnt++;
                }
            }
            if(cnt>0){
                long prod=cnt;
                for(int j=i+1;j<n;j++){
                    prod=(prod*(n-j))%MOD;
                }
                ans=(ans+prod)%MOD;
            }
        }
        ans=(ans+1)%MOD;
        return (int)ans;
    }
    public static void main(String[] args) throws IOException {
        InputStreamReader isr=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(isr);
        String s=br.readLine();
        System.out.println(findRank(s));
    }
}
