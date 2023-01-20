package Advanced_DSA_3.Queues;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class PerfectNumbers {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(isr);
        int A=Integer.parseInt(br.readLine());
        System.out.println(solve(A));
    }
    public static String solve(int A) {
        return method5(A);
    }
    private static String method5(int A){
        //two digitis - 2
        //four digits - 2^2
        //six digits -  2^3
        int term=(int)(Math.log(A+1)/Math.log(2));
        int noOfDigits=2*term;
        int pos=A-2*((1<<(term-1))-1);
        StringBuilder sb=new StringBuilder();
        int noOfPossibleElements=1<<term;
        for(int i=1;i<=noOfDigits/2;i++){
            if(pos<=noOfPossibleElements/2)
                sb.append("1");
            else {
                sb.append("2");
                pos-=noOfPossibleElements/2;
            }
            noOfPossibleElements=noOfPossibleElements/2;
        }
        int len=sb.length();
        for(int i=len-1;i>=0;i--){
            sb.append(sb.charAt(i));
        }
        return sb.toString();
    }
    //visited only valid numbers
    private static String method4(int A){
        if(A==1)
            return "11";
        else if(A==2)
            return "22";
        Queue<String> q=new LinkedList<>();
        q.add("11");
        q.add("22");
        while(!q.isEmpty()){
            String element=q.poll();
            String firstHalf=element.substring(0,element.length()/2);
            String secondHalf=element.substring(element.length()/2);
            String firstNeighbor=firstHalf+"11"+secondHalf;
            A--;
            if(A==0)
                return firstNeighbor;
            q.add(firstNeighbor);
            String secondNeighbor=firstHalf+"22"+secondHalf;
            A--;
            if(A==0)
                return secondNeighbor;
            q.add(secondNeighbor);
        }
        return "";
    }
    //visiting all the numbers and checking for the valid ones
    //issue is too many objects and too much time
    private static String method3(int A){
        Queue<Long> q=new LinkedList<>();
        q.add(1L);
        q.add(2L);
        while(!q.isEmpty()){
            long element=q.poll();
            long first=element*10+1;
            System.out.println(first);
            if(isValid3(first)){
                A--;
            }
            if(A==0)
                return Long.toString(first);
            q.add(first);
            long second=element*10+2;
            System.out.println(second);
            if(isValid3(second)){
                A--;
            }
            if(A==0)
                return Long.toString(second);
            q.add(second);
        }
        return "";
    }
    //visiting all the numbers and checking for the valid ones
    //issue is too many objects and too much time
    private static StringBuilder method2(int A){
        Queue<StringBuilder> q=new LinkedList<>();
        q.add(new StringBuilder("1"));
        q.add(new StringBuilder("2"));
        Set<StringBuilder> s=new HashSet<>();
        while(!q.isEmpty()){
            StringBuilder element=q.poll();
            StringBuilder first=new StringBuilder(element).append("1");
            if(isValid2(first,s)){
                s.add(first);
                A--;
            }
            if(A==0)
                return first;
            q.add(first);
            StringBuilder second=new StringBuilder(element).append("2");
            if(isValid2(second,s)){
                s.add(second);
                A--;
            }
            if(A==0)
                return second;
            q.add(second);
        }
        return new StringBuilder();
    }
    //visiting all the numbers and checking for the valid ones
    //issue is too many objects and too much time
    private static String method1(int A){
        Queue<String> q=new LinkedList<>();
        q.add("1");
        q.add("2");
        while(!q.isEmpty()){
            String element=q.poll();
            String first=element+"1";
            if(isValid(first))
                A--;
            if(A==0)
                return first;
            q.add(first);
            String second=element+"2";
            if(isValid(second))
                A--;
            if(A==0)
                return second;
            q.add(second);
        }
        return "";
    }
    private static boolean isValid(String str){
        int n=str.length();
        if(n%2!=0)
            return false;
        for(int i=0;i<n/2;i++){
            if(str.charAt(i)!=str.charAt(n-1-i)){
                return false;
            }
        }
        return true;
    }
    private static boolean isValid2(StringBuilder str, Set<StringBuilder> s){
        int n=str.length();
        if(n%2!=0)
            return false;
        if(str.charAt(0)!=str.charAt(n-1))
            return false;
        StringBuilder substr=new StringBuilder(str.substring(1,n-1));
        return substr.length()==0 || s.contains(substr);
    }

    private static boolean isValid3(long num){
        int cnt=0;
        long reverse=0;
        long m=num;
        while(num>0){
            long x=num%10;
            num/=10;
            cnt++;
            reverse=reverse*10+x;
        }
        return cnt%2==0 && reverse==m;
    }
}
