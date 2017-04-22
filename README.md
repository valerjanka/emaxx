# emaxx
This repo contains implementation of the most emaxx algorithm
implementations on Java (http://e-maxx.ru) plus algorithms that is used
generic approaches to solve some type of problems (package com.val.algos.generic).

## Current list of implemented algorithms in com.val.algos.emaxx:
### Algebra
1. Euclid
  * GCD(a,b) (greatest common division) recursive and iterative O(log min(a,b))
  * Extended Euclidean algorithm: find coefficients of ax+by=gcd(a,b)
  * Diophantine Equation ax+by=c
  * LCM (least common multiplier)
  * Inverse element a^(-1) by modulo m using gcd
  * All inverse elements by modulo m to m O(m)
2. Binary multiplication
  * Pow: number^degree by modulo m O(log degree)
  * Multiply: a * b % mod  O(log b)
3. Fibonacci - calculates fibonacci numbers by using fast binary calculation and matrix
4. Primes
  * Lowest prime divisor for all numbers to n O(n)

### String
1. Z-function
  * Least Compression - find cycles
  * Amount of specific substring in current string
2. Prefix function

### Structures
1. Binary Heap - int minimum binary heap (similar to PriorityQueue)
2. Fenwick Tree Sum - log(n) update elements and get prefix sum
3. Mo's Sqrt Decomposition - offline calculation of query's results N^1.5


## Current list of implemented generic algorithms (com.val.algos.generic):
### Algebra
1. Binary multiplication (BinaryUtils class)
  * Binary generic operations: solved tasks that could be divided into binary multiplication with their one operator
  * Pow for long: number^degree by modulo m O(log degree)
  * Multiply for long: a * b % mod  O(log b)
2. Fibonacci (Fibonacci class)
  * Calculates fibonacci numbers by using generic approach for fast binary calculation and matrix
3. Binary Search
  * Search for specific element in an array or list
  * Search by condition in an array or list

### Graphs
1. BFS
  * BFS simple and for not connected graph
2. DFS
  * Implemented using generic approach and is used to solve below problems
  * Find associated components
  * Find DFS paths (parentIds), postOrder
  * Find reachable vertices
3. Diameter and Center of Tree
5. Two centers of tree

### Structures
1. Binary Heap - for Comparable elements
2. SQRT Decomposition - with generic elements and Item Selector (compare element with current result)
3. Mo's Sqrt Decomposition - offline calculation of query's results N^1.5. Uses generic function to move left/right boundaries

(http://e-maxx.ru) is very good russian site with a good explanations, analysis and implementations (on C++) of many kinds of algorithms.
Thanks to emaxx for this!
