package krusGreedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;


public class Kruskal {
	PriorityQueue<Edge> minheap;
	int[] V2color;//get the colour of each vertex
	int[] color2Cardinality;//get the number of vertex per colour
	Vertex[] color2V;//arrayList holding the vertices of given colour
	
	static int max(int a,int b){
		return (a>b)?a:b;
	}
	
	private int  getInputs(){
		System.out.println("Enter the number of vertices");
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		Edge temp;
/*Initialise adjlist and visited with number of vertices*/
/* Start of edge  inputs*/
		int i,v,w,u;
		System.out.println("Enter "+n+" if no more edges");
		for(i=0;i<n;i++){
			u=i;
			System.out.println("Enter the edges for "+i+"th vertex as vertex<space>weight");
			v=scan.nextInt();
			while(v!=n){
				w = scan.nextInt();
				temp=new Edge(u, v, w);
				minheap.add(temp);
				System.out.println("Next edge as v <space> w");
				v=scan.nextInt();
			}
		}
		scan.close();
		return n;
	}
	
	void initQueue(){
		int n=1;//initial size, since it can dynamically increase, no probs
		Comparator<Edge> weightComp = new Comparator<Edge>(){
			@Override
			public int compare(Edge e1,Edge e2){
				return (e1.weight-e2.weight);
			}
		};
		this.minheap = new PriorityQueue<>(n,weightComp);
	}
	
	void initialiseAllArrays(int n){
		int i;
		this.V2color=new int[n];
		this.color2Cardinality = new int[n];
		this.color2V = new Vertex[n];
		Arrays.fill(color2Cardinality, 1);//all colours have 1 vertex
		for(i=0;i<n;i++)
			this.V2color[i]=i;//al vertices hav different color based on their index
		for(i=0;i<n;i++){
			Vertex temp = new Vertex(i);
			this.color2V[i] = temp;
		}
	}
	
	void kruskalMST(int numberOfVertices){
		this.initialiseAllArrays(numberOfVertices);
		Iterator<Edge> i = this.minheap.iterator();
		int u,v,countOfEdges=0,v2changeColor,colorWithMoreV,colorU,colorV,colorWithLessV;
		Vertex temp,temp2,headOfMoreV,headOfLessV;
		while(i.hasNext() && countOfEdges<numberOfVertices){
			Edge nowMinEdge = this.minheap.poll();
			u=nowMinEdge.u;//source of edge
			v=nowMinEdge.v;//dest of edge
			colorU=V2color[u];//color of source vertex
			colorV=V2color[v];//color of dest vertex
			if(colorU!=colorV){
				//next line finds the max card of colour
				colorWithMoreV=max(color2Cardinality[colorU],color2Cardinality[colorV]);
				//next line actually finds the colour associated with the max cardinality
				colorWithMoreV=(colorWithMoreV == color2Cardinality[colorU])?colorU:colorV;
				colorWithLessV=(colorWithMoreV == colorU)?colorV:colorU;
				headOfMoreV = color2V[colorWithMoreV];//LinkedList Head
				headOfLessV = color2V[colorWithLessV];
				while(headOfLessV!=null){
					temp = headOfMoreV.next;//present 2nd vertex of more colour
					temp2 = headOfLessV.next;
					v2changeColor=headOfLessV.v;
					this.V2color[v2changeColor]=colorWithMoreV;//update v2color
					headOfLessV.next=temp;
					//headOfMoreV-->headOfLessV-->headOfMoreV.next
					headOfMoreV.next=headOfLessV;
					//headOfLessV=headOfLessV.next(temp2)
					headOfLessV=temp2;
					color2V[colorWithLessV]=temp2;
				}
				//update value of colorcardinality
				color2Cardinality[colorWithMoreV]+=color2Cardinality[colorWithLessV];
				color2Cardinality[colorWithLessV]=0;
				countOfEdges+=1;
				System.out.println(u +" - "+ v +" weight " + nowMinEdge.weight);
			}
		}
	}
	
	void printMinHeap(){
		Iterator<Edge> i = this.minheap.iterator();
		Edge e;
		while(i.hasNext()){
			e=minheap.poll();
			System.out.println(e.u+" - "+e.v+" with "+e.weight);
		}
	}
	
	public static void main(String[] args) {
		Kruskal k=new Kruskal();
		int i;
		k.initQueue();
		int numberOfVertices = k.getInputs();
		k.kruskalMST(numberOfVertices);
		for(i=0;i<numberOfVertices;i++){
			if(k.color2V[i]!=null)
				System.out.println(k.V2color[i] + " " + k.color2V[i].v+" " +k.color2Cardinality[i]);
		}
		System.out.println();
		k.printMinHeap();
	}

}
