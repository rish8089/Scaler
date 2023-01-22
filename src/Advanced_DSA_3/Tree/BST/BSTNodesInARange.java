package Advanced_DSA_3.Tree.BST;

import Advanced_DSA_3.Tree.TreeNode;
import Advanced_DSA_3.Tree.TreeOperations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BSTNodesInARange {
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
        int C=Integer.parseInt(br.readLine());
        //treeOperations.preorderTraversal(tn);
        BSTNodesInARange obj=new BSTNodesInARange();
        System.out.println(obj.solve(tn,B,C));
    }
    public int solve(TreeNode A, int B, int C) {
        return method1(A,B,C);
    }
    private int method1(TreeNode A, int B, int C){
        if(A==null)
            return 0;
        if(A.val<=B){
            if(A.val==B)
                return 1+method1(A.right,B+1,C);
            else
                return method1(A.right,B,C);
        }else if(A.val>=C){
            if(A.val==C)
                return 1+method1(A.left,B,C-1);
            else
                return method1(A.left,B,C);
        }else{
            return 1+method1(A.left,B,A.val-1)+method1(A.right,A.val+1,C);
        }
    }
}
