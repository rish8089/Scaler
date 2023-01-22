package Advanced_DSA_3.Tree.LCA;

import Advanced_DSA_3.Tree.TreeNode;
import Advanced_DSA_3.Tree.TreeOperations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LCAInBST {
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
        LCAInBST obj=new LCAInBST();
        System.out.println(obj.solve(tn,B,C));
    }
    public int solve(TreeNode A, int B, int C) {
        return method1(A,B,C);
    }
    private int method1(TreeNode A, int B, int C){
        if(A==null)
            return -1;
        if((B<=A.val && A.val<=C) || (C<=A.val && A.val<=B)) {
            return A.val;
        }else if(A.val>B)
            return method1(A.left,B,C);
        else
            return method1(A.right,B,C);
    }
}
