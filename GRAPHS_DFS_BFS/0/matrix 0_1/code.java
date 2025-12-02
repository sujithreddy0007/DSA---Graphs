class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int n=mat.length;
        int m=mat[0].length;
        int[][] vist=new int[n][m];
        Queue<int[]>queue=new LinkedList<>();
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(mat[i][j]==0)
                queue.offer(new int[]{i,j,0});
            }
        }
        while(!queue.isEmpty())
        {
            int[] ele=queue.poll();
            int row=ele[0];
            int col=ele[1];
            int score=ele[2];
            int[] rowArr={-1,0,1,0};
            int[] colArr={0,1,0,-1};
            mat[row][col]=score;
            for(int i=0;i<4;i++)
            {
                int nrow=row+rowArr[i];
                int ncol=col+colArr[i];
                if(nrow>=0 && nrow<n && ncol>=0 && ncol<m && vist[nrow][ncol]==0 && mat[nrow][ncol]==1)
                {
                    vist[nrow][ncol]=1;
                    queue.offer(new int[]{nrow,ncol,score+1});
                }
            }
        }
        return mat;
    }
}