package Advanced_DSA_3.Tree.LCA;

import Advanced_DSA_3.Tree.TreeNode;
import Advanced_DSA_3.Tree.TreeOperations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class RecoverBinarySearchTree {
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
        RecoverBinarySearchTree obj=new RecoverBinarySearchTree();
        System.out.println(obj.recoverTree(tn));
    }
    public ArrayList<Integer> recoverTree(TreeNode A) {
        return method2(A);
    }
    private ArrayList<Integer> method1(TreeNode A){
        ArrayList<Integer> ans=new ArrayList<>();
        Stack<TreeNode> s=new Stack<>();
        addAllLeftMostNodes(A,s);
        ArrayList<Integer> inorder=new ArrayList<>();
        while(!s.isEmpty()){
            TreeNode sn=s.pop();
            inorder.add(sn.val);
            addAllLeftMostNodes(sn.right,s);
        }
        int idx=-1;
        int smallest=-1;
        for(int i=0;i<inorder.size();i++){
            if(idx==-1) {
                if (i<inorder.size()-1 && inorder.get(i) > inorder.get(i + 1)) {
                    idx = i;
                    ans.add(inorder.get(idx));
                }
            } else {
                if(smallest==-1 || smallest>inorder.get(i))
                    smallest=inorder.get(i);
            }
        }
        ans.add(smallest);
        Collections.sort(ans);
        return ans;
    }
    private ArrayList<Integer> method2(TreeNode A) {
        ResultNode nd=new ResultNode();
        ArrayList<Integer> ans=new ArrayList<>();
        TreeNode curr=A;
        int prev=-1;
        while(curr != null){
            if(curr.left != null){
                //get the right most node of left subtree
                TreeNode leftSubtreeRoot=curr.left;
                while(leftSubtreeRoot.right != null && leftSubtreeRoot.right != curr)
                    leftSubtreeRoot=leftSubtreeRoot.right;
                if(leftSubtreeRoot.right == null){
                    leftSubtreeRoot.right=curr;
                    curr=curr.left;
                }else{
                    leftSubtreeRoot.right=null;
                    processInorderTraversalNodeValue(nd, prev, curr.val, ans);
                    prev=curr.val;
                    curr=curr.right;
                }
            }else{
                processInorderTraversalNodeValue(nd, prev, curr.val, ans);
                prev=curr.val;
                curr=curr.right;
            }
        }
        if(nd.smallest != -1)
            ans.add(nd.smallest);
        Collections.sort(ans);
        return ans;
    }

    private void addAllLeftMostNodes(TreeNode A, Stack<TreeNode> s){
        while(A!=null){
            s.push(A);
            A=A.left;
        }
    }
    private void processInorderTraversalNodeValue(ResultNode rn, int prev, int nodeVal, ArrayList<Integer> ans){
        if(!rn.isMaximaFound) {
            if(prev != -1) {
                if (nodeVal < prev) {
                    rn.isMaximaFound = true;
                    ans.add(prev);
                    rn.smallest=nodeVal;
                }
            }
        }else{
            if(rn.smallest>nodeVal)
                rn.smallest=nodeVal;
        }
    }
    class ResultNode{
        boolean isMaximaFound;
        int smallest;
        ResultNode(){
            isMaximaFound=false;
            smallest=-1;
        }
    }
}
