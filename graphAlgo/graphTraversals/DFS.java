package graphTraversals;

import java.util.Scanner;
import java.util.Stack;
import graph.AdjList;
import graph.Edge;

public class DFS {
	Stack <Integer> waitingToVisit = new Stack<Integer>();
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
		this.waitingToVisit.push(startVertex);
		this.parentOfVertex[startVertex] = -1;
		int i=0;
		while(waitingToVisit.empty() != true){
			int u = waitingToVisit.pop(),v;
			if(Visited[u] != true){
				orderOfVisit[i++] = u;
				Visited[u] = true;
				Edge head = this.alist.list[u];
				while(head != null){
					v = head.vertex;
					if((Visited[v] == false)){
						waitingToVisit.push(v);
						parentOfVertex[v] = u;
					}
					head = head.next;
				}
			}
		}
	}
	
	private void printParentArray(int[] a){
		int i,n = a.length;
		for(i=0;i<n;i++){
			System.out.println(i + "'s parent is " + a[i]);
		}
	}
	
	private void printVisitArray(int[] a){
		int i,n = a.length;
		for(i=0;i<n-1;i++){
			System.out.print(a[i] +" -> ");
		}
		System.out.println(a[i]);
	}
	
	public static void main(String[] args) {
		DFS dfs = new DFS();
		dfs.getInputs();
		dfs.traverse(0);System.out.println("The parent of every vertex is listed");
		dfs.printParentArray(dfs.parentOfVertex);
		System.out.println("The order in which the DFS discovered the vertices is listed below");
		dfs.printVisitArray(dfs.orderOfVisit);
	}

}
