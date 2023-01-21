package Advanced_DSA_3.Tree.BST;

import Advanced_DSA_3.Tree.TreeNode;
import Advanced_DSA_3.Tree.TreeOperations;
import com.sun.source.tree.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ValidBinarySearchTree {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String []str=br.readLine().split(" ");
        ArrayList<Integer> list=new ArrayList<>();
        for(int i=0;i<str.length;i++)
            list.add(Integer.parseInt(str[i]));
        TreeOperations treeOperations=new TreeOperations();
        TreeNode tn=treeOperations.createTree(list);
        //treeOperations.preorderTraversal(tn);
        ValidBinarySearchTree obj=new ValidBinarySearchTree();
        System.out.println(obj.isValidBST(tn));
    }
    public int isValidBST(TreeNode A) {
        return method2(A);
    }
    private int method1(TreeNode A){
        if(A==null)
             return 1;
        Node res=method1Util(A);
        return res==null?0:1;
    }
    private Node method1Util(TreeNode A){
        int newMin=A.val;
        int newMax=A.val;
        if(A.left != null){
            Node left=method1Util(A.left);
            if(left == null)
                return null;
            if(A.val<=left.max)
                return null;
            newMin=Math.min(newMin,left.min);
        }
        if(A.right != null){
            Node right=method1Util(A.right);
            if(right == null)
                 return null;
            if(A.val>=right.min)
                return null;
            newMax=Math.max(newMax,right.max);
        }
        return new Node(newMin,newMax);
    }
    private int method2(TreeNode A){
        return method2Util(A, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    private int method2Util(TreeNode A, long min, long max){
        if(A==null)
            return 1;
        if(A.val<=min || A.val>=max)
            return 0;
        int left=method2Util(A.left,min,A.val);
        int right=method2Util(A.right,A.val,max);
        return left==1 && right==1?1:0;
    }
    class Node{
        int min;
        int max;
        Node(int min, int max){
            this.min=min;
            this.max=max;
        }
    }
}


