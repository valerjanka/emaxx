package com.val.algos.generic.structure;

public class AsciiTrie {
    private static final int ASCII_SIZE = 128;
    private final AsciiTrie[] children = new AsciiTrie[ASCII_SIZE];
    private String word = null;

    public AsciiTrie() {
    }

    public AsciiTrie(String[] words) {
        for (String word : words) {
            addWord(word);
        }
    }

    /**
     * Builds trie for the specified word. Skips if already exists.
     * <p>
     * Time Complexity: O(L) where L is the length of the word.
     * Space Complexity: O(L * 128) in the worst case (new nodes).
     *
     * @param word the word to add
     */
    public void addWord(String word) {
        AsciiTrie current = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (current.children[c] == null) {
                current.children[c] = new AsciiTrie();
            }
            current = current.children[c];
        }
        current.word = word;
    }

    /**
     * Searches a word with max length that is a substring of the specified string that starts from offset index.
     * <p>
     * Time Complexity: O(L) where L is the length of the string (or max depth of trie).
     * Space Complexity: O(1)
     *
     * @param string from which a word is searching.
     * @param offset start index in a string to search for a word.
     * @return a word that is a max substring that starts from the offset of the string.
     */
    public String maxSubstringWord(String string, int offset) {
        String result = word;
        AsciiTrie current = this;
        for (int i = offset; i < string.length(); i++) {
            if (current.children[string.charAt(i)] == null) {
                return result;
            }
            current = current.children[string.charAt(i)];
            if (current.word != null) {
                result = current.word;
            }
        }
        return result;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        buildString(result);
        return result.toString();
    }

    private void buildString(StringBuilder result) {
        result.append("[");
        if (word != null) {
            result.append("\n").append(word).append(": ");
        }
        result.append("{");
        for (char c = 0; c < children.length; c++) {
            if (children[c] != null) {
                result.append(c).append("->");
                children[c].buildString(result);
            }
        }
        result.append("}]");
    }
}
