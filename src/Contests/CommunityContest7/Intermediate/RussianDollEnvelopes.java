package Contests.CommunityContest7.Intermediate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class RussianDollEnvelopes {
        public static int solve(ArrayList<ArrayList<Integer>> A) {
            Collections.sort(A,(l1, l2)->{
                int h1=l1.get(0);
                int w1=l1.get(1);
                int h2=l2.get(0);
                int w2=l2.get(1);
                if(h1<h2)
                    return -1;
                else if(h1==h2)
                    return Integer.compare(w1,w2);
                else
                    return 1;
            });
            //System.out.println(A.get(A.size()-1).get(0)+" "+A.get(A.size()-1).get(1));
            int []cnt=new int[A.size()];
            cnt[A.size()-1]=1;
            int ans=cnt[A.size()-1];
            for(int i=A.size()-2;i>=0;i--){
                int maxCnt=0;
                int hi=A.get(i).get(0);
                int wi=A.get(i).get(1);
                for(int j=i+1;j<A.size();j++){
                    int hj=A.get(j).get(0);
                    int wj=A.get(j).get(1);
                    if(hj>hi && wj>wi) {
                        if(maxCnt<cnt[j])
                            maxCnt=cnt[j];
                    }
                }
                cnt[i]=1+maxCnt;
                if(ans<cnt[i])
                    ans=cnt[i];
            }
            //   for(int i=0;i<A.size();i++)
            //      System.out.print(cnt[i]+" ");
            //   System.out.println();
            return ans;
        }

    public static void main(String[] args) throws IOException {
        InputStreamReader isr=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(isr);
        int n=Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> arr=new ArrayList<>();
        for(int i=0;i<n;i++) {
            String[] str = br.readLine().split(" ");
            ArrayList<Integer> list = new ArrayList<>();
            int h = Integer.parseInt(str[0]);
            int w = Integer.parseInt(str[1]);
            list.add(h);
            list.add(w);
            arr.add(list);
        }
        System.out.println(solve(arr));
    }

}
