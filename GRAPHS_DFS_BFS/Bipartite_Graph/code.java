import java.util.*;

class Solution {
    public boolean dfs(ArrayList<ArrayList<Integer>> adj,int[] vist,int start,int color)
    {
       vist[start]=color;
       
       int sendColor;
       if(color==1)
       sendColor=2;
       else
       sendColor=1;
       
       boolean res=false;
       for(Integer it:adj.get(start))
       {
           if(vist[it]!=0)
           {
               if(color==vist[it])
               return true;
           }
           else
           {
              res= dfs(adj,vist,it,sendColor);
           }
       }
       return res;
    }
    public boolean isBipartite(ArrayList<ArrayList<Integer>> adj) {
        int n=adj.size();
        int[] vist=new int[n];
        for(int i=0;i<n;i++)
        {
            if(vist[i]==0)
            {
                if(dfs(adj,vist,i,1))
                return false;
            }
        }
        return true;
    }
}