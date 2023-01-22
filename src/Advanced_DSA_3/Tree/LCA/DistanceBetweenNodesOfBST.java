package Advanced_DSA_3.Tree.LCA;

import Advanced_DSA_3.Tree.TreeNode;
import Advanced_DSA_3.Tree.TreeOperations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DistanceBetweenNodesOfBST {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String []str=br.readLine().split(" ");
        ArrayList<Integer> list=new ArrayList<>();
        for (String value : str) list.add(Integer.parseInt(value));
        int B=Integer.parseInt(br.readLine());
        int C=Integer.parseInt(br.readLine());
        TreeOperations treeOperations=new TreeOperations();
        TreeNode tn=treeOperations.createTree(list);
        DistanceBetweenNodesOfBST obj=new DistanceBetweenNodesOfBST();
        System.out.println(obj.solve(tn,B,C));
    }
    public int solve(TreeNode A, int B, int C) {
        return method2(A,B,C);
    }
    //works for any binary tree
    private int method1(TreeNode A, int B, int C){
        return method1Util(A,B,C,0).ans;
    }
    private Node method1Util(TreeNode A, int B, int C, int height){
        if(A==null)
            return new Node(-1,-1,-1);
        Node left=method1Util(A.left,B,C,height+1);
        Node right=method1Util(A.right,B,C,height+1);
        if(left.ans!=-1)
            return left;
        else if(right.ans!=-1)
            return right;
        int ans=-1;
        if(A.val==B)
            left.Bheight=height;
        if(A.val==C)
            left.Cheight=height;
        if(left.Bheight!=-1){
            if(right.Cheight!=-1)
                ans=dist(height,left.Bheight)+dist(height,right.Cheight);
            else if(left.Cheight !=-1)
                ans=abs(left.Bheight-left.Cheight);
        }else if(right.Bheight!=-1){
            if(left.Cheight!=-1)
                ans=dist(height,right.Bheight)+dist(height,left.Cheight);
            else if(right.Cheight != -1)
                ans=abs(right.Bheight-right.Cheight);
        }
        int Bheight=left.Bheight!=-1?left.Bheight:right.Bheight;
        int Cheight=left.Cheight!=-1?left.Cheight:right.Cheight;
        return new Node(Bheight,Cheight,ans);
    }
    class Node{
        int Bheight;
        int Cheight;
        int ans;
        Node(int Bheight, int Cheight,int ans){
            this.Bheight=Bheight;
            this.Cheight=Cheight;
            this.ans=ans;
        }
    }
    int abs(int val){
        return val<0?-val:val;
    }
    int dist(int h1, int h2){
        return h2-h1;
    }
    //for BST
    private int method2(TreeNode A, int B, int C){
        if(A==null)
            return -1;
        if((B<=A.val && A.val<=C) || (C<=A.val && A.val<=B)) {
            int Bheight=height(A,B,0);
            int Cheight=height(A,C,0);
            return Bheight + Cheight;
        }else if(B<A.val)
            return method2(A.left,B,C);
        else
            return method2(A.right,B,C);
    }
    private int height(TreeNode A, int key, int val){
        if(A.val==key)
            return val;
        if(A.val>key)
            return height(A.left,key,val+1);
        return height(A.right,key,val+1);

    }
}
