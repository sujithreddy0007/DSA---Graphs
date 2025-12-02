import java.util.*;
class Pair
{
    int num;
    int par;
    Pair(int num,int par)
    {
        this.num=num;
        this.par=par;
    }
}
class Solution {
    // Function to detect cycle in an undirected graph.
    public boolean checkBFS(ArrayList<ArrayList<Integer>> adj,Queue<Pair>queue,int[] vist,int start)
    {
        queue.offer(new Pair(start,-1));
        while(!queue.isEmpty())
        {
            Pair p=queue.poll();
            int num=p.num;
            int par=p.par;
            ArrayList<Integer>temp=adj.get(num);
            for(int i=0;i<temp.size();i++)
            {
                if(temp.get(i)==par)
                continue;
                if(vist[temp.get(i)]==1)
                {
                return true;
                }
                else
                {
                    vist[temp.get(i)]=1;
                    queue.offer(new Pair(temp.get(i),num));
                }
                
            }
        }
        return false;
    }
    public boolean isCycle(ArrayList<ArrayList<Integer>> adj) {
        int v=adj.size();
        int[] vist=new int[v];
        Queue<Pair>queue=new LinkedList<>();
        for(int i=0;i<v;i++)
        {
            if(vist[i]==0)
            {
                if(checkBFS(adj,queue,vist,i))
                return true;
            }
        }
        return false;
        
    }
}