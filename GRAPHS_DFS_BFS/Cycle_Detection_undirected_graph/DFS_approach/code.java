import java.until.*;
class Solution {
    public boolean dfs(List<List<Integer>>adj,int[] vist,int start,int par)
    {
        vist[start]=1;
        for(int it:adj.get(start))
        {
            if(vist[it]==1 && it!=par)
            {
                return true;
            }
            if(vist[it]==0)
            {
            if(dfs(adj,vist,it,start))
            return true;
            }
        }
        return false;
    }
    public boolean isCycle(int V, int[][] edges) {
       List<List<Integer>>adj=new ArrayList<>();
       for(int i=0;i<V;i++)
       {
           adj.add(new ArrayList<>());
       }
       for(int i=0;i<edges.length;i++)
       {
            int a=edges[i][0];
            int b=edges[i][1];
            adj.get(a).add(b);
            adj.get(b).add(a);
       }
       int[] vist=new int[V];
       for(int i=0;i<V;i++)
       {
           if(vist[i]==0)
           {
               if(dfs(adj,vist,i,-1))
               return true;
           }
       }
       return false;
    }
}