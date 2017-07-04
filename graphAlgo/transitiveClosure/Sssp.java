package transitiveClosure;

import java.util.Arrays;
import java.util.Scanner;

import graph.AdjList;
import graph.Edge;

public class Sssp {
	AdjList alist;
	int[] parent;
	int[] distance;
	static int infinity = 10000;
	
	public int getInputs(){
		System.out.println("Enter the number of vertices");
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		System.out.println("Is it a directed Graph: true / false");
		boolean directed = scan.nextBoolean();
		/*Initialise adjlist and visited with number of vertices*/
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
		return n;
	}
	
	void initialiseDistance(int n, int source){
		int v,u=source;
		this.parent = new int[n];
		this.distance = new int[n];
		Arrays.fill(distance, infinity);
		Arrays.fill(parent, -1);
		Edge e;
		e=this.alist.list[source];
		while(e!=null){
			v=e.vertex;
			this.distance[v] = e.weight;
			this.parent[v] = u;
			e=e.next;
		}
	}
	
	void compute(int n,int source){
		int k,i,u,v,thisPath,prevPath;
		Edge head;
		for(k=2;k<n-1;k++){
			for(i=0;i<n;i++){
				head=this.alist.list[i];
				u=i;
				while(head!=null){
					v=head.vertex;
					thisPath = this.distance[u] + head.weight;
					prevPath = this.distance[v];
					if(thisPath<prevPath){
						this.distance[v]=thisPath;
						this.parent[v] = u;
					}
					head=head.next;
				}
			}
		}
	}
	
	void printArray(int[] a){
		for(int i=0;i<a.length;i++){
			System.out.println(a[i]+" | ");
		}
	}
	
	public static void main(String[] args) {
		Sssp sp = new Sssp();
		int source = 0;
		int n = sp.getInputs();
		sp.initialiseDistance(n, source);
		sp.compute(n,source);
		sp.printArray(sp.parent);
	}

}
