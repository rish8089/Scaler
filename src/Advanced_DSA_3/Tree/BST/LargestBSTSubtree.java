package Advanced_DSA_3.Tree.BST;

import Advanced_DSA_3.Tree.TreeNode;
import Advanced_DSA_3.Tree.TreeOperations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LargestBSTSubtree {
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
        LargestBSTSubtree obj=new LargestBSTSubtree();
        System.out.println(obj.solve(tn));
    }
    public int solve(TreeNode A) {
        return method2(A);
    }
    private int method1(TreeNode A){
        if(A==null)
            return 0;
        Node res=method1Util(A);
        return res.largestBSTSize;
    }
    private Node method1Util(TreeNode A){
        int newMin=A.val;
        int newMax=A.val;
        int totalSize=1;
        int largestBSTSize=0;
        Node left=null;
        if(A.left != null){
            left=method1Util(A.left);
            totalSize+=left.totalSize;
            newMin=Math.min(newMin,left.min);
            newMax=Math.max(newMax,left.max);
            largestBSTSize=Math.max(largestBSTSize, left.largestBSTSize);
        }
        Node right=null;
        if(A.right != null){
            right=method1Util(A.right);
            totalSize+=right.totalSize;
            newMin=Math.min(newMin,right.min);
            newMax=Math.max(newMax,right.max);
            largestBSTSize=Math.max(largestBSTSize, right.largestBSTSize);
        }
        if(left != null && right!= null){
            if(left.largestBSTSize==left.totalSize && right.largestBSTSize==right.totalSize){
                if(A.val>left.max && A.val<right.min){
                    largestBSTSize=left.totalSize+right.totalSize+1;
                }
            }
        }else if(left != null) {
            if(left.largestBSTSize == left.totalSize){
                if(A.val>left.max){
                    largestBSTSize=left.totalSize+1;
                }
            }
        }else if(right != null){
            if(right.largestBSTSize == right.totalSize){
                if(A.val<right.min){
                    largestBSTSize=right.totalSize+1;
                }
            }
        }else{
            largestBSTSize=1;
        }

        return new Node(newMin,newMax,totalSize,largestBSTSize);
    }
    private int method2(TreeNode A){
        return method2Util(A).largestBSTSize;
    }
    private Node method2Util(TreeNode A){
        if(A==null){
            return new Node(Integer.MIN_VALUE, Integer.MAX_VALUE, 0,0);
        }
        if(A.left == null && A.right==null){
            return new Node(A.val, A.val, 1,1);
        }
        Node left=method2Util(A.left);
        Node right=method2Util(A.right);
        int totalSize=left.totalSize+right.totalSize+1;
        if(left.largestBSTSize==left.totalSize && right.largestBSTSize==right.totalSize && A.val>left.max && A.val<right.min) {
            return new Node(left.min, right.max, totalSize,totalSize);
        }
        int newMin=Math.min(Math.min(left.min, right.min),A.val);
        int newMax=Math.max(Math.max(left.max, right.max),A.val);
        return new Node(newMin,newMax,totalSize,Math.max(left.largestBSTSize,right.largestBSTSize));
    }
    class Node{
        int min;
        int max;
        int totalSize;
        int largestBSTSize;
        Node(int min, int max, int totalSize, int largestBSTSize){
            this.min=min;
            this.max=max;
            this.totalSize=totalSize;
            this.largestBSTSize=largestBSTSize;
        }
    }
}
