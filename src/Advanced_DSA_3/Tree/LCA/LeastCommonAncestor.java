package Advanced_DSA_3.Tree.LCA;

import Advanced_DSA_3.Tree.BST.BSTNodesInARange;
import Advanced_DSA_3.Tree.TreeNode;
import Advanced_DSA_3.Tree.TreeOperations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class LeastCommonAncestor {
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
        LeastCommonAncestor obj=new LeastCommonAncestor();
        System.out.println(obj.lca(tn,B,C));
    }
    public int lca(TreeNode A, int B, int C) {
        return method3(A,B,C);
    }
    private int method1(TreeNode A, int B, int C){
        HashMap<Integer,Node> map=new HashMap<>();
        traverse(A,map,new Node(null,0,-1));
        Node nd1=map.get(B);
        Node nd2=map.get(C);
        while(nd1.height>nd2.height){
            nd1=nd1.parent;
        }
        while(nd1.height<nd2.height){
            nd2=nd2.parent;
        }
        while(nd1!=nd2){
            nd1=nd1.parent;
            nd2=nd2.parent;
        }
        return nd1.val;
    }
    private void traverse(TreeNode A, HashMap<Integer,Node> map, Node parent){
        if(A==null)
            return;
        Node nd=new Node(parent, parent.height+1,A.val);
        if(!map.containsKey(A.val))
            map.put(A.val,nd);
        traverse(A.left, map, nd);
        traverse(A.right, map, nd);
    }
    class Node{
        Node parent;
        int height;
        int val;
        Node(Node parent, int height,int val){
            this.parent=parent;
            this.height=height;
            this.val=val;
        }
    }
    private int method2(TreeNode A, int B, int C){
        return method2Util(A,B,C).lca;
    }
    private Node2 method2Util(TreeNode A, int B, int C){
        if(A==null) return new Node2(false,false,-1);
        Node2 left=method2Util(A.left,B,C);
        Node2 right=method2Util(A.right,B,C);
        Node2 nd=new Node2(left.Bexists||right.Bexists||A.val==B, left.Cexists||right.Cexists||A.val==C,-1);
        if(left.lca!=-1)
            nd.lca=left.lca;
        else if(right.lca!=-1)
            nd.lca=right.lca;
        else{
            if(nd.Bexists && nd.Cexists)
                nd.lca=A.val;
        }
        return nd;
    }

    //this method will only work if both of the nodes B and C are present in the tree
    private int method3(TreeNode A, int B, int C){
        if(A==null)
            return -1;
        if(A.val==B || A.val==C)
            return A.val;
        int leftLCA=method3(A.left,B,C);
        int rightLCA=method3(A.right,B,C);
        if(leftLCA !=-1 && rightLCA!=-1)
            return A.val;
        else if(leftLCA != -1)
            return leftLCA;
        else
            return rightLCA;

    }
    class Node2{
        boolean Bexists;
        boolean Cexists;
        int lca;
        Node2(boolean Bexists, boolean Cexists, int lca){
            this.Bexists=Bexists;
            this.Cexists=Cexists;
            this.lca=lca;
        }
    }


}
