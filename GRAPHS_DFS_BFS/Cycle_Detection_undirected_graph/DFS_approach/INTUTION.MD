# 🔄 Cycle Detection in an Undirected Graph (using DFS)

This document provides the intuition, approach, code logic, and complexity analysis for detecting a cycle in an undirected graph using the Depth-First Search (DFS) algorithm.

---

## 🧠 Intuition & Approach

The fundamental principle for cycle detection in an undirected graph is to use a traversal algorithm (like DFS) that tracks the **parent** of each node.

### The Cycle Condition

In an undirected graph, an edge between two nodes $(u, v)$ means we can move $u \to v$ and $v \to u$. When performing DFS:

1.  We move from a **parent** node ($P$) to a **current** node ($C$).
2.  The DFS explores a neighbor ($N$) of the current node ($C$).

A cycle is confirmed if and only if we find a neighbor $N$ that is **already marked visited** and is **not the immediate parent** ($P$) of the current node ($C$).

* If $N$ is the parent $P$, it is the trivial path back along the undirected edge, which we must **ignore**.
* If $N$ is visited and $N \neq P$, it means $N$ was reached via a **different path** earlier. This forms a **closed loop**, confirming a cycle.



### Handling Multiple Components

The graph may be disconnected. The main function iterates through all vertices ($0$ to $V-1$) to ensure that if a node is **unvisited**, a new DFS traversal is initiated for its entire connected component. If a cycle is detected in **any** component, the overall function immediately returns `true`.

---

## 💻 Code Logic

### `isCycle` Function (Setup and Component Handler)

1.  **Adjacency List Construction:** The input edge array is used to build the **Adjacency List** (`adj`). For every edge `(a, b)`, both `b` is added to `a`'s list and `a` is added to `b`'s list to represent the undirected nature.
2.  **Initialization:** A `vist` array (of size $V$) is created to track visited nodes.
3.  **Traversal Loop:** It iterates through all nodes. If a node `i` is unvisited, the recursive `dfs` is called:
    * `dfs(adj, vist, i, -1)`: The starting node `i` is passed with a parent of **$-1$** (denoting the start of a path).

### `dfs` Function (Recursive Traversal)

This is the core recursive function for Depth-First Search:

1.  **Mark Current Node:** `vist[start] = 1;`
2.  **Explore Neighbors:** The function loops through every neighbor (`it`) of the current `start` node.
3.  **Cycle Check (Visited and Not Parent):**
    ```java
    if(vist[it]==1 && it!=par)
    {
        return true;
    }
    ```
    This is the **critical logic**. If the neighbor `it` is visited **AND** is not the immediate parent `par`, we have found a back-edge to an ancestor, confirming a cycle.
4.  **Explore Unvisited Node (Recursive Step):**
    ```java
    if(vist[it]==0)
    {
        if(dfs(adj,vist,it,start))
        return true;
    }
    ```
    If the neighbor is unvisited, a recursive call is made. The **current node (`start`) is passed as the new parent** to the neighbor (`it`). If this recursive branch finds a cycle (`return true`), we immediately propagate the `true` up the call stack.
5.  **Return False:** If the loop finishes without finding any cycles in the current branch, `false` is returned.

---

## ⏱️ Complexity Analysis

The complexity is derived in terms of $V$ (number of vertices) and $E$ (number of edges).

### Time Complexity

The time complexity is **$O(V + E)$**.

* **Adjacency List Construction:** $O(E)$.
* **DFS Traversal:** DFS visits every vertex and processes every edge exactly once across all components.
    * Visiting all vertices: $O(V)$.
    * Processing all edges (in the adjacency list): $O(E)$.

The total time complexity is the sum of these operations: **$O(V + E)$**.

### Space Complexity

The auxiliary space complexity is **$O(V)$**.

* **Visited Array (`vist`):** Stores $V$ integers: $O(V)$.
* **Recursion Stack Space:** The depth of the recursive calls can be up to $V$ (in the case of a single long path). This stack space is **$O(V)$**.
* **Adjacency List (`adj`):** The input representation takes $O(V + E)$ space, but this is typically excluded from auxiliary space analysis.

The auxiliary space is dominated by the `vist` array and the recursion stack.