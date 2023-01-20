package Advanced_DSA_1.Combinatorics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SortedPermutationRankWithRepeats {
    final static int MOD=1000003;
    public static int findRank(String A) {
        int n=A.length();
        int []freq=new int[52];
        for(int i=0;i<n;i++){
            int pos=getPos(A.charAt(i));
            freq[pos]++;
        }
        long []fact=new long[n+1];
        fact[0]=1;
        for(int i=1;i<=n;i++){
            fact[i]=(i*fact[i-1])%MOD;
        }
        long ans=0;
        for(int i=0;i<n;i++){
            int pos=getPos(A.charAt(i));
            long denominator=1;
            for(int j=0;j<52;j++){
                denominator=(denominator*fact[freq[j]])%MOD;
            }
            long numerator=fact[n-i-1];
            long prod=(numerator*mypow(denominator,MOD-2))%MOD;
            for(int j=0;j<pos;j++) {
                if (freq[j]>0) {
                    long val = (prod*freq[j]) % MOD;
                    ans=(ans+val)%MOD;
                }
            }
            freq[pos]--;
        }
        ans=(ans+1)%MOD;
        return (int)ans;
    }
    private static int getPos(char c){
        if(c>='a' && c<='z')
            return c-'a'+26;
        else
            return c-'A';
    }
    private static long mypow(long a, int b){
        long res=1;
        while(b>0){
            if((b&1)>0)
                res=(res*a)%MOD;
            a=(a*a)%MOD;
            b=b/2;
        }
        return res;
    }
    public static void main(String[] args) throws IOException {
        InputStreamReader isr=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(isr);
        String s=br.readLine();
        System.out.println(findRank(s));
    }
}
