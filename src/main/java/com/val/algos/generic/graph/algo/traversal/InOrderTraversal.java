package com.val.algos.generic.graph.algo.traversal;

import com.val.algos.generic.graph.BinaryTreeNode;

import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class InOrderTraversal {

    public Iterator<Integer> inOrderIterator(BinaryTreeNode root) {
        return new InOrderIterator(root);
    }

    public Stream<Integer> inOrderStream(BinaryTreeNode root) {
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(new InOrderIterator(root),
                Spliterator.ORDERED), false);
    }

    public List<Integer> inOrderList(BinaryTreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<BinaryTreeNode> stack = new LinkedList<>();
        Set<BinaryTreeNode> visited = new HashSet<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BinaryTreeNode node = stack.peek();
            if (!visited.contains(node)) {
                visited.add(node);
                if (node.getLeft() != null) {
                    stack.push(node.getLeft());
                }
            } else {
                stack.pop();
                result.add(node.getVal());
                if (node.getRight() != null) {
                    stack.push(node.getRight());
                }
            }
        }
        return result;
    }

    public List<Integer> inOrderRecursiveList(BinaryTreeNode root) {
        List<Integer> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    private void inOrder(BinaryTreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        inOrder(node.getLeft(), result);
        result.add(node.getVal());
        inOrder(node.getRight(), result);
    }

    static class InOrderIterator implements Iterator<Integer> {
        private final Deque<BinaryTreeNode> stack = new LinkedList<>();
        private final Set<BinaryTreeNode> visited = new HashSet<>();

        public InOrderIterator(BinaryTreeNode root) {
            Objects.requireNonNull(root);
            stack.push(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Integer next() {
            while (!visited.contains(stack.peek())) {
                BinaryTreeNode node = stack.peek();
                visited.add(node);
                if (node.getLeft() != null) {
                    stack.push(node.getLeft());
                }
            }
            BinaryTreeNode next = stack.pop();
            if (next.getRight() != null) {
                stack.push(next.getRight());
            }
            return next.getVal();
        }
    }
}
