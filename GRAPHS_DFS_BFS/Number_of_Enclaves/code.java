class Solution {
    public void dfs(int[][] grid,int[][] vist,int row,int col)
    {
        int n=grid.length;
        int m=grid[0].length;
        vist[row][col]=1;
        int[] rowArr={-1,0,1,0};
        int[] colArr={0,1,0,-1};
        for(int i=0;i<4;i++)
        {
            int nrow=row+rowArr[i];
            int ncol=col+colArr[i];
            if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && vist[nrow][ncol]==0 && grid[nrow][ncol]==1)
            {
                dfs(grid,vist,nrow,ncol);
            }
        }
    }
    public int numEnclaves(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        int[][] vist=new int[n][m];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(i==0 || i==n-1 || j==0 || j==m-1)
                {
                    if(vist[i][j]==0 && grid[i][j]==1)
                    dfs(grid,vist,i,j);
                }
            }
        }
        int ans=0;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(grid[i][j]==1 && vist[i][j]==0)
                ans++;
            }
        }
        return ans;
    }
}