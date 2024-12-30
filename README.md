# emaxx

This repo contains Java implementation of various algorithms inspired by the e-maxx (http://e-maxx.ru), plus I have
added generic implementations to some problems (package `com.val.algos.generic`).

## Table of Contents

- [Implemented Algorithms in com.val.algos.emaxx](#implemented-algorithms-in-comvalalgosemaxx)
   - [Algebra](#algebra)
   - [String](#string)
   - [Structures](#structures)
- [Implemented Generic Algorithms in com.val.algos.generic](#implemented-generic-algorithms-in-comvalalgosgeneric)
   - [Algebra](#algebra-1)
   - [Graphs](#graphs)
   - [Structures](#structures-1)
- [Acknowledgements](#acknowledgements)

## Implemented Algorithms in com.val.algos.emaxx

### Algebra

1. **Euclid**
   - GCD(a,b) (greatest common division) recursive and iterative (`O(log min(a,b))`)
   - Extended Euclidean algorithm: find coefficients of `ax+by=gcd(a,b)`
   - Diophantine Equation `ax+by=c`
   - LCM (the least common multiplier)
   - Inverse element `a^(-1) by modulo m` using gcd
   - All inverse elements by modulo m to m (`O(m)`)
2. **Binary Multiplication**
   - Pow: `n^k by modulo m` (`O(log k)`)
   - Multiply: `(a * b) by modulo m`  (`O(log b)`)
3. **Fibonacci**
   - Calculates Fibonacci numbers by using fast binary calculation and matrix
4. **Primes**
   - Lowest prime divisor for all numbers to n (`O(n)`)
5. **Binary Search**

### String

1. **Z-function**
   - Least Compression - find cycles
   - Count occurrences of a specific substring within a string
2. **Prefix function**

### Structures

1. Binary Heap - int minimum binary heap (similar to PriorityQueue)
2. Fenwick Tree Sum - log(n) update elements and get prefix sum
3. Mo's Sqrt Decomposition - offline calculation of query's results N^1.5

## Implemented Generic Algorithms in com.val.algos.generic

### Algebra

1. **Binary Multiplication** (BinaryUtils class)
   - Generic operations that can be divided into binary multiplication tasks
   - Pow for long: `n^k by modulo m` (`O(log k)`)
   - Multiply for long: `(a * b) by modulo m` (`O(log b)`)
2. **Fibonacci** (Fibonacci class)
   - Calculates fibonacci numbers by using generic approach for fast binary calculation and matrix
3. **Binary Search**
   - Search for specific element in an array or list
   - Search by condition in an array or list

### Graphs

1. **BFS**
   - Standard BFS for connected and non-connected graphs
2. **DFS**
   - Implemented using generic approach and is used to solve below problems
      - Find associated components
      - Find DFS paths (parentIds), postOrder
      - Find reachable vertices
3. **Diameter and Center of Tree**
4. **Two centers of tree**

### Structures

1. **Binary Heap**
   - For Comparable elements
2. **SQRT Decomposition**
   - Works with generic elements and implements Item Selector for comparisons
3. **Mo's Sqrt Decomposition**
   - Offline query results calculation (`O(N^(1.5))`). Uses a generic function to move left and right boundaries.

## Acknowledgements

The http://e-maxx.ru is an excellent resource, providing detailed explanations, analyses and implementations of various
algorithms in C++. Special thanks to e-maxx aka maxdiver aka иванов максим for their valuable contributions!