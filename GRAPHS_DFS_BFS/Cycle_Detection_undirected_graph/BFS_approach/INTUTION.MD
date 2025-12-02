# 🚴 Cycle Detection in an Undirected Graph (using BFS)

This document provides the intuition, approach, code logic, and complexity analysis for detecting a cycle in an undirected graph using the Breadth-First Search (BFS) algorithm.

---

## 🧠 Intuition & Approach

The fundamental idea to detect a cycle in an **undirected graph** is to use a **traversal algorithm** like Breadth-First Search (**BFS**) while tracking the **parent** of each node.

### The Cycle Condition

In an undirected graph, an edge between two nodes $(u, v)$ means we can move from $u \to v$ and $v \to u$. When traversing the graph:

1.  We move from a **parent** node ($P$) to a **current** node ($C$).
2.  The neighbor ($N$) of the current node ($C$) is checked.

A cycle is confirmed if and only if we encounter a neighbor $N$ that is **already visited** and is **not the immediate parent** ($P$) of the current node ($C$).

If $N$ is the parent $P$, it is simply the way we came (the non-cyclic return path for the undirected edge), which must be ignored. If $N$ is visited and $N \neq P$, it means there exists an **alternative path** that leads back to $N$ without using the immediate parent-child edge, thus forming a closed loop (a cycle).



### Handling Multiple Components

The problem states the graph can have multiple disjoint components. To handle this, the main logic iterates through **all** vertices ($0$ to $V-1$). If a vertex is unvisited, a new component is found, and a new BFS traversal is initiated. If a cycle is detected in **any** component, the overall process terminates immediately and returns `true`.

---

## 💻 Code Logic

### Data Structure: `Pair`

The custom `Pair` class is essential for the BFS cycle detection strategy. It stores the necessary context for each element in the queue:

* **`num`**: The current **node's index**.
* **`par`**: The **parent node's index** from which `num` was reached.

### `checkBFS` Function (Traversal Logic)

This function performs the Breadth-First Search on a single component:

1.  **Initialization:** The starting node (`start`) is placed in the queue with a parent of **$-1$**.
2.  **Parent Check (Line 42):**
    ```java
    if(temp.get(i)==par)
        continue;
    ```
    This is the **critical filter**. For every neighbor, we first check if it's the parent. If it is, we skip it (`continue`) to avoid treating the simple back-and-forth nature of an undirected edge as a cycle.
3.  **Visited Check (Cycle Detection) (Line 43):**
    ```java
    if(vist[temp.get(i)]==1)
        return true;
    ```
    If the neighbor is **visited** (and we've already established it's not the immediate parent), we have found the alternative path that closes the loop. **A cycle is detected.**
4.  **Explore Unvisited Node (Line 47):** If a neighbor is unvisited, it is marked visited (`vist[...]=1`) and added to the queue for processing, with the current node (`num`) designated as its new parent.

### `isCycle` Function (Component Handler)

This function manages the traversal across the entire graph, potentially handling multiple components:

1.  It iterates through all vertices (`i=0` to `v-1`).
2.  If a vertex `i` is **unvisited** (`vist[i]==0`), it means a new component is found. `checkBFS` is called to explore this component.
3.  If `checkBFS` finds a cycle, `isCycle` immediately returns `true`.
4.  If the loop finishes without finding any cycle, it returns `false`.

---

## ⏱️ Complexity Analysis

The complexity is derived in terms of $V$ (number of vertices) and $E$ (number of edges).

### Time Complexity

The time complexity is **$O(V + E)$**.

* **Traversal:** BFS visits every vertex once and checks every edge once.
    * Visiting all vertices: $O(V)$
    * Checking all edges: In an adjacency list for an undirected graph, every edge $(u, v)$ is stored and processed twice (once when exploring $u$'s neighbors, and once when exploring $v$'s neighbors), leading to $O(2E)$, which is simplified to $O(E)$.
* Since the entire graph (all components) is traversed exactly once, the total time complexity is the sum of these operations.

### Space Complexity

The space complexity is **$O(V)$**.

* **Visited Array (`vist`):** An array of size $V$ is used to track visited nodes: $O(V)$.
* **Queue (`queue`):** In the worst case (a non-cyclic path or a star graph), the queue may hold up to $V$ vertices: $O(V)$.
* **Adjacency List:** The input is provided as an adjacency list, which uses $O(V+E)$ space, but this is usually excluded from the auxiliary space analysis.

The auxiliary space is dominated by the `vist` array and the `queue`.