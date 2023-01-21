package Advanced_DSA_3.Tree.BST;

import Advanced_DSA_3.Tree.TreeNode;
import Advanced_DSA_3.Tree.TreeOperations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SortedArrayToBalancedBST {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String []str=br.readLine().split(" ");
        ArrayList<Integer> list=new ArrayList<>();
        for(int i=0;i<str.length;i++)
            list.add(Integer.parseInt(str[i]));
        SortedArrayToBalancedBST obj=new SortedArrayToBalancedBST();
        TreeNode tn=obj.sortedArrayToBST(list);
        TreeOperations treeOperations=new TreeOperations();
        treeOperations.preorderTraversal(tn);
    }
    public TreeNode sortedArrayToBST(final List<Integer> A) {
        return method1(A);
    }
    private TreeNode method1(final List<Integer> A){
        return method1Util(A,0,A.size()-1);
    }
    private TreeNode method1Util(final List<Integer> A, int i, int j){
        if(i>j)
            return null;
        int mid=(i+j)/2;
        TreeNode tn=new TreeNode(A.get(mid));
        tn.left=method1Util(A,i,mid-1);
        tn.right=method1Util(A,mid+1,j);
        return tn;
    }
}
