package com.lsl.dsp.tree;

public class Node {
    // 左节点
    private Node lefNode;
    // 右节点
    private Node rightNode;
    // 数据
    private int value;

    private boolean isDelete;

    Node getLefNode() {
        return lefNode;
    }

    void setLefNode(Node lefNode) {
        this.lefNode = lefNode;
    }

    Node getRightNode() {
        return rightNode;
    }

    void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Node() {
        super();
    }

    Node(int value) {
        this(null, null, value, false);
    }

    private Node(Node lefNode, Node rightNode, int value, boolean isDelete) {
        this.lefNode = lefNode;
        this.rightNode = rightNode;
        this.value = value;
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "Node [ value=" + value + ",lefNode=" + lefNode + ", rightNode=" + rightNode + ", isDelete=" + isDelete
            + "]";
    }
}
