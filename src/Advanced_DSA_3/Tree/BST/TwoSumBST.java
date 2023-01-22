package Advanced_DSA_3.Tree.BST;

import Advanced_DSA_3.Tree.TreeNode;
import Advanced_DSA_3.Tree.TreeOperations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class TwoSumBST {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String []str=br.readLine().split(" ");
        ArrayList<Integer> list=new ArrayList<>();
        for(int i=0;i<str.length;i++)
            list.add(Integer.parseInt(str[i]));
        TreeOperations treeOperations=new TreeOperations();
        TreeNode tn=treeOperations.createTree(list);
        int B=Integer.parseInt(br.readLine());
        //treeOperations.preorderTraversal(tn);
        TwoSumBST obj=new TwoSumBST();
        System.out.println(obj.t2Sum(tn,B));
    }
    public int t2Sum(TreeNode A, int B) {
        return method3(A,B);
    }
    private int method1(TreeNode A, int B){
        HashMap<Integer,Integer> map=new HashMap<>();
        method1Util(A,B,map);
        for(Integer key:map.keySet()){
            if(key<B){
                if(key!=B-key){
                    if(map.containsKey(B-key) && map.get(B-key)>=1)
                        return 1;
                }
            }
        }
        return 0;
    }
    private int method2(TreeNode A, int B){
        return method2Util(A,B,new HashSet<>())?1:0;
    }
    private boolean method2Util(TreeNode A, int B, HashSet<Integer> set){
        if(A==null)
            return false;
        if(A.val<B && A.val!=B-A.val && set.contains(B-A.val))
            return true;
        set.add(A.val);
        return method2Util(A.left,B,set) || method2Util(A.right,B,set);
    }
    private void method1Util(TreeNode A, int B, HashMap<Integer,Integer> mp){
        if(A==null)
            return;
        if(!mp.containsKey(A.val))
            mp.put(A.val,1);
        else
            mp.put(A.val,mp.get(A.val)+1);
        method1Util(A.left,B,mp);
        method1Util(A.right,B,mp);
    }
    private int method3(TreeNode A, int B){
        Stack<TreeNode> s1=new Stack<>();
        Stack<TreeNode> s2=new Stack<>();
        TreeNode c1=A;
        TreeNode c2=A;
        while((s1.size()>0 || c1!=null) && (s2.size()>0 || c2!= null)){
            if(c1!=null || c2!=null){
                while(c1!=null){
                    s1.add(c1);
                    c1=c1.left;
                }
                while(c2!=null){
                    s2.add(c2);
                    c2=c2.right;
                }
            }else{
                TreeNode tn1=s1.peek();
                TreeNode tn2=s2.peek();
                int sum=tn1.val+tn2.val;
                if(sum==B){
                    if(tn1.val==tn2.val)
                        return 0;
                    return 1;
                }
                else if(sum<B){
                    s1.pop();
                    c1=tn1.right;
                }else{
                    s2.pop();
                    c2=tn2.left;
                }
            }
        }
        return 0;
    }
}
