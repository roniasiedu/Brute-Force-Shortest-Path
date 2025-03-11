// Name: Roni Asiedu
// Brute-Force Shortest Path Algorithm with Recursion Depth Tracking
// Finds the shortest path from (0,0) to (m-1, n-1) in a grid

public class ShortestPath {
    private int minSteps = Integer.MAX_VALUE; // Stores the shortest path found
    private int recursionDepth = 0; // Tracks the recursion depth

    public int findShortestPath(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        dfs(grid, 0, 0, 0, visited);
        System.out.println("Max Recursion Depth: " + recursionDepth);
        return minSteps == Integer.MAX_VALUE ? -1 : minSteps;
    }

    private void dfs(int[][] grid, int x, int y, int steps, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;

        // If out of bounds, an obstacle, or already visited, return
        if (x < 0 || y < 0 || x >= m || y >= n || grid[x][y] == 1 || visited[x][y]) {
            return;
        }

        // If at destination, update minSteps
        if (x == m - 1 && y == n - 1) {
            minSteps = Math.min(minSteps, steps);
            return;
        }

        // Track recursion depth
        recursionDepth = Math.max(recursionDepth, steps);

        // Mark as visited
        visited[x][y] = true;

        // Explore four possible directions (Right, Down, Left, Up)
        dfs(grid, x, y + 1, steps + 1, visited);
        dfs(grid, x + 1, y, steps + 1, visited);
        dfs(grid, x, y - 1, steps + 1, visited);
        dfs(grid, x - 1, y, steps + 1, visited);

        // Backtrack
        visited[x][y] = false;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {0, 0, 0},
            {1, 1, 0},
            {0, 0, 0}
        };

        ShortestPath sp = new ShortestPath();
        int result = sp.findShortestPath(grid);
        System.out.println(result == -1 ? "No Path Found" : "Shortest Path Length: " + result);
    }
}
