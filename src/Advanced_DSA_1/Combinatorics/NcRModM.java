package Advanced_DSA_1.Combinatorics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
public class NcRModM {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(isr);
        String []str=br.readLine().split(" ");
        int A=Integer.parseInt(str[0]);
        int B=Integer.parseInt(str[1]);
        int C=Integer.parseInt(str[2]);
        System.out.println(solve(A,B,C));

    }
    public static int solve(int A, int B, int C) {
        Map<Integer,Integer> mp1=primeFactorization(A);
        Map<Integer,Integer> mp2=primeFactorization(A-B);
        Map<Integer,Integer> mp3=primeFactorization(B);
        long ans=1;
        for(Integer key:mp1.keySet()){
            int cnt1=mp1.get(key);
            int cnt2=0;
            int cnt3=0;
            if(mp2.containsKey(key))
                cnt2=mp2.get(key);
            if(mp3.containsKey(key))
                cnt3=mp3.get(key);
            ans=(ans*mypow(key,cnt1-cnt2-cnt3,C))%C;
        }
        return (int)(ans%C);
    }
    private static Map<Integer,Integer> primeFactorization(int A){
        Map<Integer,Integer> mp=new HashMap<>();
        boolean []isComposite=new boolean[A+1];
        for(int i=2;i*i<=A;i++){
            if(!isComposite[i]){
                int []power=new int[A+1];
                power[i]=1;
                int ans=1;
                for(int j=2*i;j<=A;j+=i){
                    isComposite[j]=true;
                    power[j]=1+power[j/i];
                    ans+=power[j];
                }
                mp.put(i,ans);
            }
        }
        for(int i=2;i<=A;i++){
            if(!isComposite[i] && !mp.containsKey(i)){
                mp.put(i,A/i);
            }
        }

        return mp;
    }
    private static long mypow(long A, int B, int C){
        long res=1;
        while(B>0){
            if((B&1)>0){
                res=(res*A)%C;
            }
            A=(A*A)%C;
            B=B/2;
        }
        return res;
    }
}
