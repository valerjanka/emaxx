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

1. **[Euclid](http://e-maxx.ru/algo/euclid_algorithm)**
   - GCD(a,b) (greatest common division) recursive and iterative (`O(log min(a,b))`)
   - [Extended Euclidean algorithm](http://e-maxx.ru/algo/extended_euclid_algorithm): find coefficients of `ax+by=gcd(a,b)`
   - [Diophantine Equation](http://e-maxx.ru/algo/diofant_2_equation) `ax+by=c`
   - LCM (the least common multiplier)
   - [Inverse element](http://e-maxx.ru/algo/reverse_element) `a^(-1) by modulo m` using gcd
   - All inverse elements by modulo m to m (`O(m)`)
2. **[Binary Multiplication](http://e-maxx.ru/algo/binary_pow)**
   - Pow: `n^k by modulo m` (`O(log k)`)
   - Multiply: `(a * b) by modulo m`  (`O(log b)`)
3. **[Fibonacci](http://e-maxx.ru/algo/fibonacci_numbers)**
   - Calculates Fibonacci numbers by using fast binary calculation and matrix
4. **Primes**
   - [Lowest prime divisor](http://e-maxx.ru/algo/prime_sieve_linear) for all numbers to n (`O(n)`)
   - [Factorization](http://e-maxx.ru/algo/factorization) (`O(sqrt(n))`)
   - [Sieve of Eratosthenes](http://e-maxx.ru/algo/eratosthenes_sieve) (`O(n log log n)`)
5. **[Binary Search](http://e-maxx.ru/algo/binary_search)**
6. **[Euler's Totient Function](http://e-maxx.ru/algo/euler_function)**
   - Calculate phi(n) (`O(sqrt(n))`)
   - Calculate phi from 1 to n (`O(n log log n)`)
7. **[Discrete Logarithm](http://e-maxx.ru/algo/discrete_log)**
   - Baby-step Giant-step algorithm to solve `a^x = b (mod m)` (`O(sqrt(m))`)
8. **[Primitive Root](http://e-maxx.ru/algo/primitive_root)**
   - Find generator of multiplicative group modulo p (`O(log^c p)`)
9. **[Chinese Remainder Theorem](http://e-maxx.ru/algo/chinese_theorem)**
   - Garner's Algorithm for system of congruences (`O(k^2)`)

### String

1. **[Z-function](http://e-maxx.ru/algo/z_function)**
   - Least Compression - find cycles
   - Count occurrences of a specific substring within a string
2. **[Prefix function](http://e-maxx.ru/algo/prefix_function)**

### Structures

1. Binary Heap - int minimum binary heap (similar to PriorityQueue)
2. **[Fenwick Tree Sum](http://e-maxx.ru/algo/fenwick_tree)** - log(n) update elements and get prefix sum
3. **[Mo's Sqrt Decomposition](http://e-maxx.ru/algo/sqrt_decomposition)** - offline calculation of query's results N^1.5

## Implemented Generic Algorithms in com.val.algos.generic

### Algebra

1. **[Binary Multiplication](http://e-maxx.ru/algo/binary_pow)** (BinaryUtils class)
   - Generic operations that can be divided into binary multiplication tasks
   - Pow for long: `n^k by modulo m` (`O(log k)`)
   - Multiply for long: `(a * b) by modulo m` (`O(log b)`)
2. **[Fibonacci](http://e-maxx.ru/algo/fibonacci_numbers)** (Fibonacci class)
   - Calculates fibonacci numbers by using generic approach for fast binary calculation and matrix
3. **[Binary Search](http://e-maxx.ru/algo/binary_search)**
   - Search for specific element in an array or list
   - Search by condition in an array or list

### Graphs

1. **[BFS](http://e-maxx.ru/algo/bfs)**
   - Standard BFS for connected and non-connected graphs
2. **[DFS](http://e-maxx.ru/algo/dfs)**
   - Implemented using generic approach and is used to solve below problems
      - [Find associated components](http://e-maxx.ru/algo/connected_components)
      - Find DFS paths (parentIds), postOrder
      - Find reachable vertices
3. **Diameter and Center of Tree**
4. **Two centers of tree**

### Structures

1. **Binary Heap**
   - For Comparable elements
2. **[SQRT Decomposition](http://e-maxx.ru/algo/sqrt_decomposition)**
   - Works with generic elements and implements Item Selector for comparisons
3. **[Mo's Sqrt Decomposition](http://e-maxx.ru/algo/sqrt_decomposition)**
   - Offline query results calculation (`O(N^(1.5))`). Uses a generic function to move left and right boundaries.

## Acknowledgements

The http://e-maxx.ru is an excellent resource, providing detailed explanations, analyses and implementations of various
algorithms in C++. Special thanks to e-maxx aka maxdiver aka иванов максим for their valuable contributions!