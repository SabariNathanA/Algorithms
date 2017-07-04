package graphTraversals;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import graph.AdjList;
import graph.Edge;

public class BFS {

	Queue <Integer> waitingToVisit = new LinkedList<Integer>();
	boolean[] Visited; //replacement for colouring
	int[] parentOfVertex; //pi function 
	AdjList alist; // alist for representation of graph
	int[] orderOfVisit;//to debug 
	
	private void  getInputs(){
		System.out.println("Enter the number of vertices");
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		System.out.println("Is it a directed Graph: true / false");
		boolean directed = scan.nextBoolean();
/*Initialise adjlist and visited with number of vertices*/
		this.Visited = new boolean[n];
		this.parentOfVertex = new int[n];
		this.orderOfVisit = new int[n];
		alist = new AdjList(n, directed);
/* Start of edge  inputs*/
		int i,v,w,u;
		System.out.println("Enter "+n+" if no more edges");
		for(i=0;i<n;i++){
			u=i;
			System.out.println("Enter the edges for "+i+"th vertex as vertex<space>weight");
			v=scan.nextInt();
			while(v!=n){
				w = scan.nextInt();
				alist.addEdge(u, v, w);
				System.out.println("Next edge as v <space> w");
				v=scan.nextInt();
			}
		}
		alist.printAdjList();
		scan.close();
	}
	
	public void traverse(int startVertex){
		this.waitingToVisit.add(startVertex);
		this.parentOfVertex[startVertex] = -1;
		this.Visited[startVertex] = true;
		int i=0;
		while(waitingToVisit.peek() != null){
			int u = waitingToVisit.poll(),v;
			orderOfVisit[i++] = u;
			Edge head = this.alist.list[u];
			while(head != null){
				v = head.vertex;
				if(Visited[v] == false){
					waitingToVisit.add(v);
					Visited[v] = true;
					parentOfVertex[v] = u;
				}
				head = head.next;
			}
		}
		
	}
	
	private void printArray(int[] a){
		int i,n = a.length;
		for(i=0;i<n;i++){
			System.out.println(i + " - " + a[i]);
		}
	}
	
	public static void main(String[] args) {
		BFS bfs = new BFS();
		bfs.getInputs();
		bfs.traverse(0);
		/*add print the parent list to see if it works*/
		System.out.println("The parent of every vertex is listed");
		bfs.printArray(bfs.parentOfVertex);
		System.out.println("The order in which the BFS discovered the vertices is listed below");
		bfs.printArray(bfs.orderOfVisit);
		
	}

}
/*
while(count>0){
    while((i<N)&&(visited[i]==true)) i++;
    waiting.add(i);
    visited[i] = true;
    while(waiting.empty()!=true){
        int u = waiting.poll();
        Edge head= e[u];
        while(head!=null){
            int v=head.v;
            visited[v]=true;
            count--;
            head=head.next;
        }
    }
    
}
*/