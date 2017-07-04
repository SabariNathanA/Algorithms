package graphTraversals;

import java.util.Scanner;
import java.util.Stack;

import graph.AdjList;
import graph.Edge;

public class DFSrecursive {
	
	Stack <Integer> finishList = new Stack<Integer>();
	boolean[] Visited; //replacement for colouring
	int[] parentOfVertex; //pi function 
	AdjList alist; // alist for representation of graph
	int[] discovery;
	int[] finish;
	static int time = 0;
	private void  getInputs(){
		System.out.println("Enter the number of vertices");
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		System.out.println("Is it a directed Graph: true / false");
		boolean directed = scan.nextBoolean();
		/*Initialise adjlist and visited with number of vertices*/
		this.Visited = new boolean[n];
		this.parentOfVertex = new int[n];
		this.discovery = new int[n];
		this.finish = new int[n];
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
	
	public void dfsrHelper(int source){
		this.parentOfVertex[source] = -1;
		dfsr(source);
	}
	
	private void dfsr(int source){
		this.Visited[source] = true;
		this.discovery[source] = time++;
		int v,u = source;
		Edge head = this.alist.list[u];
		while(head != null){
			v = head.vertex;
			if((this.Visited[v] == false)){
				this.parentOfVertex[v] = u;
				this.dfsr(v);
			}
			head=head.next;
		}
		this.finish[u]=time++;
		this.finishList.push(u);
	}
	private void printParentArray(int[] a){
		int i,n = a.length;
		for(i=0;i<n;i++){
			System.out.println(i + "'s parent is " + a[i]+" start at "+this.discovery[i]+" end at "+this.finish[i]);
		}
	}
	
	private void printFinishList(Stack<Integer> a){
		while(a.empty() != true){
			System.out.print(a.pop() +" -> ");
		}
	}
	
	public static void main(String[] args) {
		DFSrecursive dfs = new DFSrecursive();
		dfs.getInputs();
		dfs.dfsrHelper(0);
		System.out.println("The parent of every vertex is listed");
		dfs.printParentArray(dfs.parentOfVertex);
		System.out.println("The order in which the DFS discovered the vertices is listed below");
		dfs.printFinishList(dfs.finishList);
	}

}
