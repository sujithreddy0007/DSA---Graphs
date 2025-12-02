
import java.util.*;

/*Complete the function below*/

class Solution {
    // Function to detect cycle in a directed graph.
    public boolean dfs(ArrayList<ArrayList<Integer>> adj,int[] vist,int[] pathVist,int start)
    {
        vist[start]=1;
        pathVist[start]=1;
        boolean res=false;
        for(Integer it:adj.get(start))
        {
            if(vist[it]==0)
            {
                res=dfs(adj,vist,pathVist,it);
                if(res==true)
                break;
            }
            else
            {
                if(vist[it]==1 && pathVist[it]==1)
                return true;
            }
        }
        pathVist[start]=0;
        return res;
    }
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
       int[] vist=new int[V];
       int[] pathVist=new int[V];
       for(int i=0;i<V;i++)
       {
           if(vist[i]==0)
           {
               if(dfs(adj,vist,pathVist,i))
               return true;
           }
       }
       return false;
    }
}