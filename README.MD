# 📊 DSA Graphs: Traversal Algorithms (DFS & BFS)

This repository serves as a focused collection of solutions for fundamental Graph Data Structure and Algorithm (DSA) problems. The solutions heavily leverage the two core traversal techniques: **Depth-First Search (DFS)** and **Breadth-First Search (BFS)**.

---

## 🗺️ Understanding Graph Traversal

Graphs are essential non-linear data structures. The way we explore (or traverse) them determines how we solve specific problems.

### 1. Depth-First Search (DFS) 🧠

DFS explores as far as possible along a single branch before backtracking. It's like exploring a maze by following one path until a dead end, then returning to the last fork to try the next path.

| Characteristic | Key Use Case | How it Works |
| :--- | :--- | :--- |
| **Data Structure** | Recursion Stack | Uses the system stack (or an explicit stack) to manage recursive calls. |
| **Exploration Style** | Deep | Prioritizes depth over breadth. |
| **Best For** | **Topological Sort**, **Cycle Detection** (especially directed graphs), finding connected components, and pathfinding where the path length isn't critical. |

### 2. Breadth-First Search (BFS) 🌊

BFS explores all neighbor nodes at the current level before moving to the nodes at the next depth level. It expands outward in layers, like ripples in a pond.

| Characteristic | Key Use Case | How it Works |
| :--- | :--- | :--- |
| **Data Structure** | Queue | Uses a queue to store nodes to be visited in the order they were discovered. |
| **Exploration Style** | Wide/Level-by-Level | Prioritizes breadth over depth. |
| **Best For** | **Shortest Path** in unweighted graphs, **Minimum Distance** problems, finding all nodes within $K$ steps, and copying/cloning graphs. |

---

## 🗂️ Repository Concepts and Algorithms

The problems in this repository demonstrate the strategic application of DFS and BFS.

### Core Traversal Problems

| Folder Name | Algorithm Used | Traversal Insight |
| :--- | :--- | :--- |
| **`Cycle_Detection_undirected_graph`** | **BFS or DFS** | Uses traversal while tracking the **parent node**. A cycle exists if a visited node is encountered that is NOT the parent. |
| **`Cycle_Detection_directed_Graph`** | **DFS** | Requires two states (`visited` and `pathVisited`). A cycle exists if a neighbor is marked as visited **and** is currently on the recursion stack (`pathVisited`). |
| **`Bipartite_Graph`** | **DFS or BFS** | Uses traversal to perform **two-coloring**. If an adjacent node is found with the same color, the graph is not bipartite (it contains an odd-length cycle). |

---

### Grid/Matrix Problems (Implicit Graphs)

These problems treat each cell in the matrix as a **node** and the 4-directional adjacencies as **edges**.

| Folder Name | Algorithm Used | Traversal Insight |
| :--- | :--- | :--- |
| **`0/matrix 0_1`** | **Multi-Source BFS** | All '0' cells are the starting points. BFS guarantees finding the **shortest distance** to any '0' because it expands layer by layer. |
| **`Rotten_Oranges`** | **Multi-Source BFS** | All rotten oranges are the starting points. BFS tracks the maximum time (layers/distance) required for the infection to reach the last fresh orange. |
| **`Surrounded_Regions`** | **DFS** | Solved by **protection**. DFS is used to find and mark all 'O' regions connected to the **boundary**. All remaining 'O's are truly surrounded and are captured. |
| **`Number_of_Enclaves`** | **DFS** | Solved by **disqualification**. DFS is used to find and disqualify all '1' (land) cells connected to the **boundary**. The final count is the number of remaining '1's. |

---

### Connected Components Problems (Graph Search)

| Folder Name | Algorithm Used | Traversal Insight |
| :--- | :--- | :--- |
| **`Number_Of_Provinces`** | **DFS or BFS** | The traversal (starting from an unvisited node) explores an entire **connected component** (a province). The number of times a new traversal must be started equals the number of components. |
| **`Flood_Fill`** | **DFS** | Used to quickly explore a connected area (region of same-colored pixels) by immediately going deep into the component and recursively changing the color. |

---

## 🛠️ How to Navigate the Repository

1.  **Select a folder** based on the concept (e.g., `Bipartite_Graph`).
2.  Review the **`intuition.md`** file for the high-level strategy, code logic, and complexity analysis.
3.  Examine the **code file** to see the algorithm implemented in practice.