package com.val.algos.generic.graph.algo.traversal;

import com.val.algos.generic.graph.BinaryTreeNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class InOrderTraversalTest {

    @Test
    public void inOrderIterator() {
        BinaryTreeNode root = createTree();
        assertEquals(getInOrder(), new ArrayList<Integer>() {
            {
                new InOrderTraversal().inOrderIterator(root).forEachRemaining(this::add);
            }
        });
    }

    @Test
    public void inOrderStream() {
        BinaryTreeNode root = createTree();
        assertEquals(getInOrder(), new InOrderTraversal().inOrderStream(root).collect(Collectors.toList()));
    }

    @Test
    public void inOrderList() {
        BinaryTreeNode root = createTree();
        assertEquals(getInOrder(), new InOrderTraversal().inOrderList(root));
    }

    @Test
    public void inOrderRecursiveList() {
        BinaryTreeNode root = createTree();
        assertEquals(getInOrder(), new InOrderTraversal().inOrderRecursiveList(root));
    }

    private List<Integer> getInOrder() {
        return Arrays.asList(5, 8, 10, 12, 15);
    }

    // 5, 8, 10, 12, 15
    private BinaryTreeNode createTree() {
        BinaryTreeNode root = new BinaryTreeNode(10);
        root.setLeft(new BinaryTreeNode(5));
        root.setRight(new BinaryTreeNode(15));

        root.getLeft().setRight(new BinaryTreeNode(8));
        root.getRight().setLeft(new BinaryTreeNode(12));
        return root;
    }
}