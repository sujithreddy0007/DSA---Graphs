# 🪟 Surrounded Regions (Capture the 'O's) using DFS

This document provides the intuition, approach, code logic, and complexity analysis for solving the "Surrounded Regions" problem, which captures all 'O' regions that are not connected to the board's edge.

---

## 🧠 Intuition & Approach

The core challenge is distinguishing between 'O' regions that are **surrounded** by 'X's (and should be flipped) and 'O' regions that are **connected to the boundary** (and should be protected).

### The Key Insight: Protection, not Capture

Instead of trying to find surrounded regions, it's easier and more efficient to find the **un-surrounded** regions, which are the ones connected to the board's four edges.

A region of 'O's **cannot be captured** (flipped to 'X') if **any** 'O' in that connected region touches the boundary of the matrix. If one 'O' is on the edge, the entire group connected to it is "safe" because it's connected to the "outside" or "un-surrounded" area.

**The Strategy (Two-Pass Approach):**

1.  **Identify and Protect (DFS):** We start a traversal (DFS) from **every 'O' located on the board's boundary**. Any 'O' reached by this traversal is part of a "safe" region. We use the `vist` array to mark all these safe 'O' cells.
2.  **Capture (Final Pass):** We iterate through the entire board again. Any 'O' that was **not** marked as visited (`vist[i][j] == 0`) must belong to a region completely surrounded by 'X's and isolated from the edge. We flip these truly surrounded 'O's to 'X's.



---

## 💻 Code Logic

### `dfs` Function (Protection Phase)

This is a standard Depth-First Search used to recursively mark all 'O' cells connected to a starting boundary 'O'.

1.  **Mark Current Cell:** `vist[row][col]=1;` marks the current 'O' cell as **visited** (i.e., **safe**).
2.  **Explore Neighbors:** It iterates through the four adjacent cells using the direction arrays.
3.  **Recursive Call Condition:** A recursive call is made only if the neighbor is:
    * **In-bounds**.
    * **Unvisited** (`vist[...]==0`).
    * An **'O' cell** (`board[nrow][ncol]=='O'`).

### `solve` Function (Main Logic)

This function orchestrates the two main phases.

1.  **Initialization:** Initializes the $N \times M$ `vist` array to track safe regions.
2.  **Phase 1: Boundary Traversal (Protection)**
    * The code iterates only over the **four edges** of the board (rows 0 and $n-1$; columns 0 and $m-1$).
    * If a boundary cell is an 'O' and hasn't been visited, `dfs` is called to mark its entire connected region as safe.
3.  **Phase 2: Capture Unprotected Regions**
    * The code iterates over the **entire** $N \times M$ matrix.
    * **Capture Condition:** `if(board[i][j]=='O' && vist[i][j]==0)`
        * If a cell is still an 'O', AND
        * It was **not** marked as safe in Phase 1,
        * It is captured and flipped: `board[i][j]='X';`

---

## ⏱️ Complexity Analysis

The complexity is derived in terms of $N$ (number of rows) and $M$ (number of columns).

### Time Complexity

The time complexity is **$O(N \cdot M)$**.

* **Phase 1 (Boundary Traversal & DFS):** The DFS starts from the edges and visits every reachable 'O' cell once. The total time for this graph traversal is proportional to $O(V + E)$, which simplifies to **$O(N \cdot M)$**.
* **Phase 2 (Capture):** Iterating through the entire matrix to perform the flips takes **$O(N \cdot M)$**.

The total time complexity is dominated by the two full traversals, resulting in **$O(N \cdot M)$**.

### Space Complexity

The auxiliary space complexity is **$O(N \cdot M)$**.

* **Visited Array (`vist`):** An $N \times M$ integer array is used to track the safe regions: $O(N \cdot M)$.
* **Recursion Stack Space:** In the worst-case scenario (a long path of 'O's), the recursion depth of the DFS can be up to $N \cdot M$. This stack space is **$O(N \cdot M)$**.

The auxiliary space is dominated by the `vist` array and the recursion stack.