package com.example.lib;

/**
 * Created by renanbenattidias on 01/04/18.
 */

class BinaryTree {
    private Node head;
    private int size;

    BinaryTree() {
        this.head = null;
        this.size = 0;
    }

    public void add(double value) {
        if (this.head == null) {
            this.head = new Node(null, null, null, value);
        } else {
            add(value, head);
        }
        this.size++;
    }

    private void add(double value, Node no) {
        double currentValue = no.getValue();
        if (value < currentValue) {
            if (no.getPrev() != null) {
                add(value, no.getPrev());
            } else {
                no.setPrev(new Node(no, null, null, value));
            }
        } else if (value > currentValue) {
            if (no.getNext() != null) {
                add(value, no.getNext());
            } else {
                no.setNext(new Node(no, null, null, value));
            }
        } else {
            no.setReserve(no.getReserve() + 1);
        }
    }

    public Node getHead() {
        return this.head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public int getSize() {
        return this.size;
    }

    public Node getMin() {
        return getMin(this.head);
    }

    public Node getMax() {
        return getMax(this.head);
    }

    private Node getMin(Node no) {
        if (no.getPrev() != null) {
            return getMin(no.getPrev());
        } else {
            return no;
        }
    }

    private Node getMax(Node no) {
        if (no.getNext() != null) {
            return getMax(no.getNext());
        } else {
            return no;
        }
    }

    public void remove(Node no) {
        int reserve = no.getReserve();
        Node upNode = no.getUp();
        if (reserve > 0) {
            no.setReserve(reserve - 1);
        } else if (upNode == null) {
            this.head = null;
        } else if (no.getPrev() == null && no.getNext() == null) {
            if (upNode.getNext().equals(no)) {
                upNode.setNext(null);
            } else {
                upNode.setPrev(null);
            }
            no = null;
        } else if (no.getUp() == null) {
            this.head = null;
            no = null;
        } else if (no.getPrev() == null) {
            no.getUp().setPrev(no.getNext());
        } else {
            no.getUp().setNext(no.getPrev());
        }
        this.size--;
    }
}
