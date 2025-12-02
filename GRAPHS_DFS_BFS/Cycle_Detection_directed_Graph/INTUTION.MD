# 🔁 Cycle Detection in a Directed Graph (using DFS)

This document provides the intuition, approach, code logic, and complexity analysis for detecting a cycle in a **directed** graph using Depth-First Search (DFS).

---

## 🧠 Intuition & Approach

Detecting a cycle in a directed graph requires identifying a **back edge**: an edge from a node currently being explored (the descendant) back to an **ancestor** node that is still on the current recursion stack.

### The Dual Tracking Mechanism

To implement this, we use two tracking arrays in parallel:

1.  **`vist` (Visited Array):** Tracks every node that has **ever been visited** across all components. This prevents redundant processing of nodes.
2.  **`pathVist` (Path Visited Array):** Tracks every node that is currently **active in the recursion stack** for the ongoing DFS path. This represents the set of ancestors.

### The Cycle Condition

A directed cycle is confirmed if, from the current node $U$, we find an edge leading to a neighbor $V$ such that:
* $V$ is marked as **visited globally** (`vist[V] = 1`).
* $V$ is also marked as **visited in the current path** (`pathVist[V] = 1`).

If both conditions are met, the edge $U \to V$ is a **back edge** that closes a directed loop. 

---

## 💻 Code Logic

### `dfs` Function (Traversal and Cycle Check)

1.  **Marking:** The current node is marked in both arrays upon entry:
    ```java
    vist[start]=1;
    pathVist[start]=1;
    ```

2.  **Neighbor Traversal:** The function iterates through all neighbors (`it`).

3.  **Case 1: Unvisited Neighbor:** If a neighbor is unvisited, a recursive call is made. If that call returns `true` (cycle found), the current execution stops, and `true` is propagated upwards.

4.  **Case 2: Visited Neighbor (Cycle Check):**
    ```java
    else {
        if(vist[it]==1 && pathVist[it]==1)
        return true; // Cycle detected: Back edge found to an ancestor
    }
    ```
    If the neighbor is visited, we check the crucial `pathVist` array. If the neighbor is in the current path, we have found a cycle and return `true`.

5.  **Backtracking:**
    ```java
    pathVist[start]=0;
    ```
    **Crucial Step:** Before the function returns, the current node is **unmarked** from the `pathVist` array. This is essential because the node is no longer an active part of the recursion path; it is merely an already-visited node for any future, separate paths that might encounter it.

### `isCyclic` Function (Component Handler)

1.  **Initialization:** Both `vist` and `pathVist` arrays are initialized to 0.
2.  **Iterate Components:** The function ensures every unvisited node starts a new DFS.
3.  **Result:** If any `dfs` call returns `true` (cycle detected), the main function immediately returns `true`. If the loop completes without finding a cycle, `false` is returned.

---

## ⏱️ Complexity Analysis

The complexity is derived in terms of $V$ (number of vertices) and $E$ (number of edges).

### Time Complexity

The time complexity is **$O(V + E)$**.

* **Traversal:** Since every vertex is visited at most once, and every directed edge is traversed at most once across all components, the time complexity is proportional to the number of vertices plus the number of edges.

### Space Complexity

The auxiliary space complexity is **$O(V)$**.

* **Visited Arrays:** Two arrays of size $V$ (`vist` and `pathVist`) are used: $O(V)$.
* **Recursion Stack Space:** In the worst case (a long acyclic path), the depth of the DFS recursion can be up to $V$. This stack space is **$O(V)$**.

The auxiliary space is dominated by the tracking arrays and the recursion stack.