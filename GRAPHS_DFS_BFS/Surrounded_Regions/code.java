class Solution {
    public void dfs(char[][] board,int[][] vist,int row,int col)
    {
        int n=board.length;
        int m=board[0].length;
        vist[row][col]=1;
        int[] rowArr={-1,0,1,0};
        int[] colArr={0,1,0,-1};
        for(int i=0;i<4;i++)
        {
            int nrow=row+rowArr[i];
            int ncol=col+colArr[i];
            if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && vist[nrow][ncol]==0 && board[nrow][ncol]=='O')
            {
                dfs(board,vist,nrow,ncol);
            }
        }
    }
    public void solve(char[][] board) {
        int n=board.length;
        int m=board[0].length;
        int[][] vist=new int[n][m];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(i==0 || j==0 || i==n-1 || j==m-1)
                {
                    if(vist[i][j]==0 && board[i][j]=='O')
                    dfs(board,vist,i,j);
                }
            }
        }
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if( board[i][j]=='O' && vist[i][j]==0)
                board[i][j]='X';
            }
        }
    }
}