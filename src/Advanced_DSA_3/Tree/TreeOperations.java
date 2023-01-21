package Advanced_DSA_3.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TreeOperations {
    public TreeNode createTree(ArrayList<Integer> list) {
        TreeNode tn = null;
        if (list.size() == 0 || list.get(0) == -1)
            return tn;
        Queue<TreeNode> q = new LinkedList<>();
        tn = new TreeNode(list.get(0));
        q.add(tn);
        int idx = 0;
        while (!q.isEmpty()) {
            TreeNode qn = q.poll();
            if(list.get(idx+1)!=-1){
                qn.left=new TreeNode(list.get(idx+1));
                q.add(qn.left);
            }
            if(list.get(idx+2)!=-1) {
                qn.right = new TreeNode(list.get(idx + 2));
                q.add(qn.right);
            }
            idx+=2;
        }
        return tn;
    }
    public void preorderTraversal(TreeNode tn){
        if(tn==null)
            return;
        System.out.print(tn.val+" ");
        preorderTraversal(tn.left);
        preorderTraversal(tn.right);
    }
}
