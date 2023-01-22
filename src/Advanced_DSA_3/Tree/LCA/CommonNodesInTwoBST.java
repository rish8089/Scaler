package Advanced_DSA_3.Tree.LCA;

import Advanced_DSA_3.Tree.TreeNode;
import Advanced_DSA_3.Tree.TreeOperations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class CommonNodesInTwoBST {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String []str=br.readLine().split(" ");
        ArrayList<Integer> list=new ArrayList<>();
        for (String value : str) list.add(Integer.parseInt(value));
        String []str2=br.readLine().split(" ");
        ArrayList<Integer> list2=new ArrayList<>();
        for (String s : str2) list2.add(Integer.parseInt(s));
        TreeOperations treeOperations=new TreeOperations();
        TreeNode tn=treeOperations.createTree(list);
        TreeNode tn2=treeOperations.createTree(list2);
        //treeOperations.preorderTraversal(tn);
        CommonNodesInTwoBST obj=new CommonNodesInTwoBST();
        System.out.println(obj.solve(tn,tn2));
    }
    final int MOD=1000000007;
    public int solve(TreeNode A, TreeNode B) {
        return method2(A,B);
    }
    //works for any binary tree
    private int method1(TreeNode A, TreeNode B){
        return method1Util(A,B,new HashSet<>());
    }
    private int method1Util(TreeNode A, TreeNode B, HashSet<Integer> set){
        if(A==null && B==null)
            return 0;
        int sum=0;
        if(A!=null){
            if(set.contains(A.val))
                sum=madd(sum,A.val);
            else
                set.add(A.val);
        }
        if(B!=null){
            if(set.contains(B.val))
                sum=madd(sum,B.val);
            else
                set.add(B.val);
        }
        int sum1=method1Util(A==null?null:A.left, B==null?null:B.left, set);
        int sum2=method1Util(A==null?null:A.right, B==null?null:B.right, set);
        return madd(sum,madd(sum1,sum2));
    }
    private int madd(int a, int b){
        return (a+b)%MOD;
    }

    //works for BST
    private int method2(TreeNode A, TreeNode B){
        int sum=0;
        Stack<TreeNode> s1=new Stack<>();
        Stack<TreeNode> s2=new Stack<>();
        TreeNode curr1=A;
        TreeNode curr2=B;
        while((curr1!=null || s1.size()>0) && (curr2!=null || s2.size()>0)){
            if(curr1 != null || curr2 != null){
                while(curr1!=null) {
                    s1.push(curr1);
                    curr1 = curr1.left;
                }
                while(curr2!=null){
                    s2.push(curr2);
                    curr2=curr2.left;
                }
            }else{
              TreeNode nd1=s1.peek();
              TreeNode nd2=s2.peek();
              if(nd1.val==nd2.val){
                  s1.pop();
                  s2.pop();
                  curr1=nd1.right;
                  curr2=nd2.right;
                  sum=madd(sum,nd1.val);
              }else if(nd1.val<nd2.val){
                  s1.pop();
                  curr1=nd1.right;
              }else{
                  s2.pop();
                  curr2=nd2.right;
              }
            }
        }
        return sum;
    }

}
