# 🌊 01 Matrix (Nearest Zero Distance) using Multi-Source BFS

This document provides the intuition, approach, code logic, and complexity analysis for solving the "01 Matrix" problem, which finds the shortest distance from every cell to the nearest `0`.

---

## 🧠 Intuition & Approach

The problem asks for the **shortest distance** from every cell to the nearest `0`. Since the distance metric is defined by moving to adjacent cells (sharing a common edge) with a cost of 1, this is a **shortest path problem** in an unweighted grid graph, making **Breadth-First Search (BFS)** the perfect algorithm.

### Multi-Source BFS

A standard BFS starts from a single source. Here, since we want the distance to the *nearest* `0`, we must find the shortest path from a cell to *any* of the `0`s. We solve this efficiently using **Multi-Source BFS**:

1.  **Treat all `0` cells as starting points.** We initialize the queue with all cells that contain `0` (distance = 0).
2.  **Parallel Expansion:** The BFS expands outward **in parallel** from all `0` cells.
3.  **Guaranteed Shortest Path:** The first time a `1` cell is reached by the BFS (from any direction), the distance recorded is **guaranteed to be the shortest distance** to any `0`, because BFS explores the graph layer by layer, prioritizing smaller distances.

This technique transforms a complex "nearest element" search into a single, efficient traversal. 

---

## 💻 Code Logic

The solution modifies the input matrix `mat` to store the calculated distances.

### Initialization (The Sources)

1.  The code iterates through the entire $N \times M$ matrix.
2.  For every cell `(i, j)` where `mat[i][j] == 0`, the coordinates and its distance (0) are added to the queue: `queue.offer(new int[]{i, j, 0})`.
3.  A separate `vist` array is used to ensure a cell is only processed and added to the queue once.

### BFS Traversal

1.  **Dequeue & Update:** An element `ele` is extracted from the queue, containing the current `row`, `col`, and the `score` (distance). The calculated distance is immediately stored in the input matrix: `mat[row][col]=score;`.
2.  **Direction Arrays:** `rowArr` and `colArr` define the $\mathbf{4}$ **cardinal directions** ($\{(-1, 0), (0, 1), (1, 0), (0, -1)\}$).
3.  **Neighbor Checks (The Conditions):** For each neighbor `(nrow, ncol)`:
    * **Boundary Check:** Check if the neighbor is within the matrix bounds.
    * **Visited Check:** `vist[nrow][ncol]==0` ensures we process each cell only once.
    * **Target Check:** `mat[nrow][ncol]==1` targets only the cells whose distances need to be calculated (the starting `0`s are already processed).
4.  **Enqueue:** If all checks pass, the neighbor is marked visited and added to the queue with an incremented distance (`score+1`).

The final `mat` returned contains the shortest distance from every cell to the nearest `0`.

---

## ⏱️ Complexity Analysis

The complexity is derived in terms of $N$ (number of rows) and $M$ (number of columns).

### Time Complexity

The time complexity is **$O(N \cdot M)$**.

* **Initialization:** Iterating through the entire matrix takes $O(N \cdot M)$.
* **BFS Traversal:** Since every cell is added to the queue at most **once** and processed at most **once**, the traversal is highly efficient. The total time complexity for processing all vertices (cells) and all edges (connections to neighbors) is proportional to $O(V + E)$, which simplifies to **$O(N \cdot M)$**.

### Space Complexity

The auxiliary space complexity is **$O(N \cdot M)$**.

* **Visited Array (`vist`):** An $N \times M$ array is used for tracking visited cells: $O(N \cdot M)$.
* **Queue (`queue`):** In the worst case (e.g., a checkerboard pattern), the queue may hold up to $O(N \cdot M)$ cells.

The auxiliary space used by the algorithm is dominated by the `vist` array and the `queue`.