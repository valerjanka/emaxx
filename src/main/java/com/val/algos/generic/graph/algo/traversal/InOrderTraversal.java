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
        if (root == null) return result;

        Deque<BinaryTreeNode> stack = new ArrayDeque<>();
        BinaryTreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.getLeft();
            }
            curr = stack.pop();
            result.add(curr.getVal());
            curr = curr.getRight();
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
        private final Deque<BinaryTreeNode> stack = new ArrayDeque<>();
        private BinaryTreeNode current;

        public InOrderIterator(BinaryTreeNode root) {
            // Objects.requireNonNull(root); // Original code required root.
            // If root is null, iterator should be empty.
            // But original: stack.push(root) -> throws NPE if root is null?
            // Objects.requireNonNull(root) throws NPE.
            // So behavior was: constructor throws NPE if root is null.
            // I should preserve this behavior or handle null gracefully?
            // "Objects.requireNonNull(root)" implies intended behavior.
            Objects.requireNonNull(root);
            this.current = root;
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty() || current != null;
        }

        @Override
        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            while (current != null) {
                stack.push(current);
                current = current.getLeft();
            }
            BinaryTreeNode node = stack.pop();
            Integer result = node.getVal();
            current = node.getRight();
            return result;
        }
    }
}
