package com.val.algos.generic.structure;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AsciiTrieTest {
    private static final String[] words = new String[]{"abc", "ac", "apple", "an", "abcccc", "bc"};

    @Test
    public void maxSubstringWord() {
        AsciiTrie trie = new AsciiTrie(words);
        System.out.println(trie);
        assertNull(trie.maxSubstringWord("", 0));
        assertNull(trie.maxSubstringWord("", 1));
        assertNull(trie.maxSubstringWord("a", 0));
        assertNull(trie.maxSubstringWord("a", 1));
        assertNull(trie.maxSubstringWord("ac", 1));
        assertNull(trie.maxSubstringWord("abc", 2));

        assertEquals("ac", trie.maxSubstringWord("ac", 0));
        assertEquals("ac", trie.maxSubstringWord("acb", 0));
        assertEquals("abc", trie.maxSubstringWord("abc", 0));
        assertEquals("bc", trie.maxSubstringWord("abc", 1));
        assertEquals("abcccc", trie.maxSubstringWord("abccccadsda", 0));
    }
}