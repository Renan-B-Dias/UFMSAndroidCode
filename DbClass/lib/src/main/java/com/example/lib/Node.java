package com.example.lib;

/**
 * Created by renanbenattidias on 01/04/18.
 */

class Node {
    private Node up;
    private Node next;
    private Node prev;
    private double value;
    private int reserve;

    Node(Node up, Node next, Node prev, double value) {
        this.up = up;
        this.next = next;
        this.prev = prev;
        this.value = value;
        this.reserve = 0;
    }

    public Node getUp() {
        return this.up;
    }

    public void setUp(Node up) {
        this.up = up;
    }

    public Node getNext() {
        return this.next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return this.prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getReserve() {
        return this.reserve;
    }

    public void setReserve(int reserve) {
        this.reserve = reserve;
    }
}
