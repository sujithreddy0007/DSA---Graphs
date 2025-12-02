# 🏝️ Number of Enclaves (Counting Isolated Land Cells) using DFS

This document provides the intuition, approach, code logic, and complexity analysis for solving the "Number of Enclaves" problem.

---

## 🧠 Intuition & Approach

The goal is to count the number of land cells ('1's) that are completely isolated and **cannot** reach the boundary of the grid in any number of moves. This is equivalent to finding the land cells that are **not connected** to any edge.

### The Key Insight: Disqualifying Boundary-Connected Land

Instead of trying to locate and count the truly isolated regions directly, it is far simpler to identify and **disqualify** all land cells that *can* reach the boundary. The remaining land cells ('1's) must then be the enclaves we are looking for.

**The Strategy (Two-Pass Approach):**

1.  **Identify and Disqualify (DFS):** We perform a DFS traversal starting from **every land cell ('1') located on the board's boundary**. Any '1' reached by this traversal is connected to the outside and is *disqualified* from being an enclave.
2.  **Mark Disqualified Cells:** We use a `vist` array to mark all these connected '1' cells as "safe" or "disqualified."
3.  **Final Count:** After the DFS is complete, we iterate through the entire matrix and count every remaining '1' that was **not** marked as visited. These are the enclaves.



---

## 💻 Code Logic (Based on Provided DFS Structure)

The solution uses the common DFS pattern of marking connected components.

### Phase 1: Boundary Traversal (Disqualification)

1.  **Boundary Iteration:** The `solve` (or main) function iterates over the **four edges** (rows 0, $n-1$, and columns 0, $m-1$) of the matrix.
2.  **Start DFS:** The condition `if(vist[i][j]==0 && board[i][j]=='O')` (adapted to use '1's for the enclaves problem, though the provided code uses 'O') starts the DFS. **Every land cell on the border triggers a DFS** if it hasn't been visited.
3.  **`dfs` Function:** This function marks the current land cell as visited (`vist[row][col]=1`) and recursively explores all adjacent **unvisited land cells** in the 4-directions. This process tags all land cells that are part of a boundary-connected component.

### Phase 2: Counting Enclaves

1.  After the boundary DFS completes, the code iterates over the **entire** $N \times M$ matrix.
2.  **Enclave Condition:** A counter is incremented for every cell `(i, j)` that meets two conditions:
    * It is a **land cell ('1')** (or `'O'` as per your code structure).
    * It was **not visited** (`vist[i][j] == 0`) by the boundary DFS.

The final count represents the total number of land cells that are completely enclosed (Enclaves).

---

## ⏱️ Complexity Analysis

The complexity is derived in terms of $N$ (number of rows) and $M$ (number of columns).

### Time Complexity

The time complexity is **$O(N \cdot M)$**.

* **Phase 1 (Boundary DFS):** The DFS starts from the edges and visits every reachable land cell once. The total time for this graph traversal on a grid is proportional to $O(V + E)$, which simplifies to **$O(N \cdot M)$**.
* **Phase 2 (Counting):** Iterating through the entire matrix to count the enclaves takes **$O(N \cdot M)$**.

The total time complexity is dominated by these two linear traversals: **$O(N \cdot M)$**.

### Space Complexity

The auxiliary space complexity is **$O(N \cdot M)$**.

* **Visited Array (`vist`):** An $N \times M$ array is used to track the boundary-connected cells: $O(N \cdot M)$.
* **Recursion Stack Space:** In the worst-case scenario (a single large path of land cells), the recursion depth of the DFS can be up to $N \cdot M$. This stack space is **$O(N \cdot M)$**.

The auxiliary space is dominated by the `vist` array and the recursion stack.