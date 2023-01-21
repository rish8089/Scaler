package Advanced_DSA_3.Tree.BST;

import Advanced_DSA_3.Tree.TreeNode;
import Advanced_DSA_3.Tree.TreeOperations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DeleteNodeInBST {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String []str=br.readLine().split(" ");
        ArrayList<Integer> list=new ArrayList<>();
        for(int i=0;i<str.length;i++)
            list.add(Integer.parseInt(str[i]));
        TreeOperations treeOperations=new TreeOperations();
        TreeNode tn=treeOperations.createTree(list);
        int nodeToBeDeleted=Integer.parseInt(br.readLine());
        //treeOperations.preorderTraversal(tn);
        DeleteNodeInBST obj=new DeleteNodeInBST();
        tn=obj.solve(tn,nodeToBeDeleted);
        treeOperations.preorderTraversal(tn);
    }
    public TreeNode solve(TreeNode A, int B) {
        if(A==null)
             return null;
        if(A.val>B)
            A.left=solve(A.left,B);
        else if(A.val<B)
            A.right=solve(A.right,B);
        else{
            if(A.left != null){
                if(A.right!=null){
                  TreeNode ip=getInorderPrecessor(A);
                  A.val=ip.val;
                  A.left=solve(A.left,ip.val);
                }else
                    return A.left;
            }else{
                if(A.right!=null){
                    return A.right;
                }else
                    return null;
            }
        }
        return A;
    }
    private TreeNode getInorderPrecessor(TreeNode A){
        A=A.left;
        while(A.right!=null)
            A=A.right;
        return A;
    }
}
