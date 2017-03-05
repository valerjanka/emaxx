package com.val.emaxx.string;

import static org.junit.Assert.*;
import org.junit.Test;

public class SubStringAlgorithmsTest {

    @Test
    public void testsubstringAmountAAinAAAA() {
        assertEquals(3, SubStringAlgorithms.substringAmount("aaaa".toCharArray(), "aa".toCharArray()));
    }
    
    @Test
    public void testsubstringAmountAABAinABAABAABAAB() {
        assertEquals(2, SubStringAlgorithms.substringAmount("abaabaabaab".toCharArray(), "aaba".toCharArray()));
    }
    
    @Test
    public void testsubstringAmountACinAABCABC() {
        assertEquals(0, SubStringAlgorithms.substringAmount("aabcabc".toCharArray(), "ac".toCharArray()));
    }
    
    
    @Test
    public void testleastCompressionAAAA() {
        assertEquals(1, SubStringAlgorithms.leastCompression("aaaa".toCharArray()));
    }
    
    @Test
    public void testleastCompressionABAABAABAABA() {
        assertEquals(3, SubStringAlgorithms.leastCompression("abaabaabaaba".toCharArray()));
    }
    
    @Test
    public void testleastCompressionAABA() {
        assertEquals(0, SubStringAlgorithms.leastCompression("aaba".toCharArray()));
    }
    
    @Test
    public void testleastCompressionA() {
        assertEquals(0, SubStringAlgorithms.leastCompression("a".toCharArray()));
    }
}
