package com.val.emaxx.string;

import static org.junit.Assert.*;
import org.junit.Test;

public class StringUtilsTest {
    private static final char[] TEST_AAAAA = "aaaaa".toCharArray();
    private static final int[] TEST_AAAAA_RESULT = new int[] { 0, 4, 3, 2, 1 };

    private static final char[] TEST_ABACABA = "abacaba".toCharArray();
    private static final int[] TEST_ABACABA_RESULT = new int[] { 0, 0, 1, 0, 3, 0, 1 };

    private static final char[] TEST_AAABAAB = "aaabaab".toCharArray();
    private static final int[] TEST_AAABAAB_RESULT = new int[] { 0, 2, 1, 0, 2, 1, 0 };

    private static final char[] TEST_ABCABCD = "abcabcd".toCharArray();
    private static final int[] TEST_ABCABCD_PREFIX_FUNCTION_RESULT = new int[] {0, 0, 0, 1, 2, 3, 0};

    @Test
    public void testZFunctionWithNull() {
        assertNull(StringUtils.zFunction(null));
    }

    @Test
    public void testZFunctionWithEmpty() {
        int[] result = StringUtils.zFunction(new char[0]);
        assertNotNull(result);
        assertEquals(0, result.length);
    }

    @Test
    public void testZFunctionWithOneElement() {
        int[] result = StringUtils.zFunction(new char[] { 'a' });
        assertNotNull(result);
        assertArrayEquals(new int[]{0}, result);
    }

    @Test
    public void testZFunctionWithAAAAA() {
        int[] result = StringUtils.zFunction(TEST_AAAAA);
        assertNotNull(result);
        assertArrayEquals(TEST_AAAAA_RESULT, result);
    }

    @Test
    public void testZFunctionWithABACABA() {
        int[] result = StringUtils.zFunction(TEST_ABACABA);
        assertNotNull(result);
        assertArrayEquals(TEST_ABACABA_RESULT, result);
    }
    
    @Test
    public void testZFunctionWithAAABAAB() {
        int[] result = StringUtils.zFunction(TEST_AAABAAB);
        assertNotNull(result);
        assertArrayEquals(TEST_AAABAAB_RESULT, result);
    }

    @Test
    public void testPrefixFuntionWithEmptyString() {
        assertArrayEquals(new int[0], StringUtils.prefixFunction("".toCharArray()));
    }

    @Test
    public void testPrefixFuntionWithABCABCD() {
        assertArrayEquals(TEST_ABCABCD_PREFIX_FUNCTION_RESULT, StringUtils.prefixFunction(TEST_ABCABCD));
    }
}
