package ru.skillbench.tasks.javaapi.collections;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class TreeNodeImpl implements TreeNode {

    private Object data;
    private boolean isExpanded;
    private TreeNode parent;
    private LinkedHashSet<TreeNode> children;

    private void childrenInit(int initialCapacity,float loadFactor){
        children = new LinkedHashSet<TreeNode>(initialCapacity,loadFactor);
    }

    public TreeNodeImpl(){
        isExpanded = false;
    }

    @Override
    public TreeNode getParent() {
        return parent;
    }

    @Override
    public void setParent(TreeNode parent) {
        if (parent != null){
            this.parent = parent;
        }
    }

    private TreeNode getRootofNode(TreeNode node){
        if (node.getParent() == null) return node;
        else return getRootofNode(node.getParent());
    }

    @Override
    public TreeNode getRoot() {
        if (parent == null) return null;
        else return getRootofNode(this);
    }

    @Override
    public boolean isLeaf() {
        return ((children == null) || (children.size() == 0));
    }

    @Override
    public int getChildCount() {
        if (children == null) return 0;
        else return children.size();
    }

    @Override
    public Iterator<TreeNode> getChildrenIterator() {
        if (children != null) return children.iterator();
        else return null;
    }

    @Override
    public void addChild(TreeNode child) {
        if (child != null){
            if (isLeaf()) this.childrenInit(5,0.75f);
            children.add(child);
            child.setParent(this);
        }   
    }

    @Override
    public boolean removeChild(TreeNode child) {
       if (children != null){
           if (children.contains(child)){
               children.remove(child);
               child.setParent(null);
               return true;
           } else return false;
       } else return false;
    }

    @Override
    public boolean isExpanded() {
        return isExpanded;
    }

    @Override
    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
        for(TreeNode child:children){
            child.setExpanded(expanded);
        }
    }

    @Override
    public Object getData() {
        return data;
    }

    @Override
    public void setData(Object data) {
        if (data == null) this.data = null;
        else this.data = data;
    }

    private StringBuilder getPath(){
        StringBuilder treePath = new StringBuilder("");
        String path;
        if (parent != null){
            return treePath.append(parent.getTreePath()).append("->").append((data == null) ? "empty": data);
        } else return new StringBuilder(((data == null) ? "empty": data).toString()).append(treePath);
    }

    @Override
    public String getTreePath() {
        return getPath().toString();
    }

    @Override
    public TreeNode findParent(Object data) {
        if (parent == null){
            if (this.data.equals(data)) return this;
            else return null;
        } else{
            if (this.data.equals(data)) return this;
            else return parent.findParent(data);
        }
    }

    @Override
    public TreeNode findChild(Object data) {
        if ((getData() == null) || (data == null)){
            if (getData() == data) return this;
        } else if ((data.equals(getData()))) return this;
        TreeNode res = null;
        if (!isLeaf()){//if this node have children, then check them
            for(TreeNode child:children){
                res = child.findChild(data);
                if (res != null) break;//because we want to find first TreeNode
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode node_1 = new TreeNodeImpl();
        TreeNode node_2 = new TreeNodeImpl();
        TreeNode node_3 = new TreeNodeImpl();
        TreeNode node_4 = new TreeNodeImpl();
        TreeNode node_5 = new TreeNodeImpl();
        TreeNode node_6 = new TreeNodeImpl();
        node_1.addChild(node_2);
        node_1.addChild(node_3);
        node_2.addChild(node_4);
        node_2.addChild(node_5);
        node_5.addChild(node_6);
        System.out.println(node_6.getData());
        node_1.setData("node_1");
        node_2.setData("node_2");
        node_3.setData("node_3");
        node_4.setData("node_4");
        node_5.setData("node_5");
        Iterator<TreeNode> it = node_1.getChildrenIterator();
        System.out.println(node_1.findChild(null));
        System.out.println(node_1.getRoot());
    }
}
