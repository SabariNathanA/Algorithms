package krusGreedy;

/*
 * Used in Kruskal to sort the edges in minHeap order based 
 * on the weight of the edge.
 */

public class Edge {
	int u;
	int v;
	int weight;
	Edge(int a,int b,int c){
		this.u=a;
		this.v=b;
		this.weight=c;
	}
}
