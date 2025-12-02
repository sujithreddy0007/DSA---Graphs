# 🎨 Is Graph Bipartite? (Two-Coloring using DFS)

This document provides the intuition, approach, code logic, and complexity analysis for determining if an undirected graph is bipartite using the Depth-First Search (DFS) and a two-coloring scheme.

---

## 🧠 Intuition & Approach

A graph is **bipartite** if its nodes can be divided into two disjoint sets, $A$ and $B$, such that every edge connects a node in set $A$ to one in set $B$. Critically, there are **no edges** within set $A$ or within set $B$.

### The Two-Coloring Theorem

A graph is bipartite **if and only if** it is 2-colorable. This means we can assign one of two colors to every node such that no two **adjacent nodes** share the same color.

**The Strategy (Coloring):**

1.  **Traversal:** We use **Depth-First Search (DFS)** to traverse the connected components of the graph.
2.  **Constraint:** When moving from a node to its neighbor, the neighbor **must** be assigned the **opposite color** .
3.  **Violation:** If, during the traversal, we find an edge connecting the current node to a neighbor that is **already colored** and has the **same color** as the current node, the bipartite condition is violated. This indicates the presence of an **odd-length cycle**, which prevents two-coloring.

### Handling Disconnected Components

Since the graph may have multiple components, the main function iterates through all nodes. If an **uncolored node** is found, it triggers a new DFS, ensuring that every part of the graph is checked for the bipartite property.

---

## 💻 Code Logic

The solution uses an integer array `vist` to track both the visited status and the color of each node (0: unvisited/uncolored, 1: Color 1, 2: Color 2).

### `dfs` Function (Coloring and Conflict Check)

This function performs the recursive coloring and validation.

1.  **Coloring Current Node:** `vist[start]=color;` assigns the color inherited from the parent.
2.  **Determine Next Color:** The logic prepares the **opposite color** (`sendColor`) for all adjacent nodes.
3.  **Neighbor Traversal:** The code loops through all neighbors (`it`) of the current node (`start`).
4.  **Constraint Check (Violation Detected):**
    ```java
    if(vist[it]!=0) {
        if(color==vist[it])
        return true; // Conflict found (Not Bipartite)
    }
    ```
    If a neighbor is already colored, we check if its color is the **same** as the current node's color. If they match, a **conflict** is found, and `true` is returned to signal that the graph is **not** bipartite.
5.  **Recursive Coloring:**
    ```java
    else {
        res = dfs(adj, vist, it, sendColor);
    }
    ```
    If the neighbor is uncolored, a recursive DFS is launched, passing the **opposite color**. Any conflict found deeper in the recursion is propagated upward by returning `true`.

### `isBipartite` Function (Component Handler)

1.  **Initialization:** The `vist` array is initialized to 0s.
2.  **Iteration:** It loops through all nodes.
3.  **DFS Call:** If a node `i` is uncolored (`vist[i]==0`), a DFS is initiated, starting with Color **1**.
4.  **Result:** If any DFS call returns `true` (conflict/not bipartite), the main function immediately returns `false`. If all components are successfully colored without conflict, `true` is returned.

---

## ⏱️ Complexity Analysis

The complexity is derived in terms of $N$ (number of nodes/vertices) and $E$ (number of edges).

### Time Complexity

The time complexity is **$O(N + E)$**.

* **Traversal:** The DFS ensures every vertex is visited once and every edge is processed once across all connected components.
    * Visiting all vertices: $O(N)$.
    * Processing all edges (in the adjacency list): $O(E)$.

The total time complexity is the sum of these operations: **$O(N + E)$**.

### Space Complexity

The auxiliary space complexity is **$O(N)$**.

* **Color/Visited Array (`vist`):** An array of size $N$ is used to store the color/status of each node: $O(N)$.
* **Recursion Stack Space:** In the worst-case scenario (a single long path), the recursion depth of the DFS can be up to $N$. This stack space is **$O(N)$**.

The auxiliary space is dominated by the `vist` array and the recursion stack.