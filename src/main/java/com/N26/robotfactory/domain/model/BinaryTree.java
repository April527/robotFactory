package com.N26.robotfactory.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BinaryTree implements Serializable {

    private static final long serialVersionUID = 8416367861026372387L;

    Node root;

    public void add(Integer value) {
        if (value != null){
            root = addRecursive(root, value);
        }
    }

    private Node addRecursive(Node current, int value) {

        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        }

        return current;
    }

    public Node getRoot() {
        return root;
    }
}
