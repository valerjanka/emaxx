# emaxx
This repo contains implementation of the most emaxx algorithm implementations on Java (http://e-maxx.ru).  
Current list of implemented algorithms:  
## Algebra
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
  * Binary generic operations: solved tasks that could be divided into binary multiplication with their one operator
3. Fibonacci - calculates fibonacci numbers by using fast binary calculation and matrix
4. Primes
  * Lowest prime divisor for all numbers to n O(n)

## String
1. Z-function
  * Least Compression - find cycles
  * Amount of specific substring in current string
2. Prefix function

## Graphs
1. BFS
2. DFS
3. Diameter and Center of Tree
4. Two centers of tree

(http://e-maxx.ru) is very good russian site with a good explanations, analysis and implementations (on C++) of many kinds of algorithms.
Thanks to emaxx for this!
