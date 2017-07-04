package graphTraversals;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;


import graph.AdjList;
import graph.Edge;
public class MinHeapTraversalDJK {
	AdjList alist; 
	Queue <PriorityVertex> minHeap;
	boolean[] Visited; 
	int[] parentOfVertex;  
	int[] orderOfVisit; 
	static int infinity = 100000;
	
	private int  getInputs(){
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
		return n;
	}
	
	private void initQueue(int n){
		Comparator<PriorityVertex> distComparator = new Comparator<PriorityVertex>(){
			@Override
			public int compare(PriorityVertex o1, PriorityVertex o2) {
				return (o1.priority - o2.priority);
			}
		};
		this.minHeap = new PriorityQueue<>(n,distComparator);
		int i;
		PriorityVertex temp = new PriorityVertex();
		for(i=0;i<n;i++){
			temp = new PriorityVertex(i, infinity);
			this.minHeap.add(temp);
		}
	}
	
	public void traverse(int startVertex){
//		PriorityVertex temp = new PriorityVertex(this.alist.list[startVertex].vertex,this.alist.list[startVertex].weight);
//		this.minHeap.add(temp);
		this.comparePriDecreased(startVertex, 0, 0);
		this.parentOfVertex[startVertex] = -1;
		int i=0;
		while(this.minHeap.size() !=0){
			PriorityVertex thisVer=this.minHeap.poll();
			int u = thisVer.vertex,v;
			orderOfVisit[i++] = u;
			Visited[u] = true;
			Edge head = this.alist.list[u];
			while(head != null){//
				v = head.vertex;
				if(this.comparePriDecreased(v, head.weight, thisVer.priority )){
					parentOfVertex[v] = u;
				}
				head = head.next;
			}
		}
	}
	
	private boolean comparePriDecreased(int v, int edgeW_UV, int u_pri){
		int relaxationValue = edgeW_UV + u_pri;
		boolean toret = false;
		java.util.Iterator<PriorityVertex> it = this.minHeap.iterator();
		while(it.hasNext()){
			PriorityVertex head = it.next();
			if(head.vertex==v){
				if(head.priority>relaxationValue){
					toret = true;
					this.minHeap.remove(head);
					head = new PriorityVertex(v,relaxationValue);
					this.minHeap.add(head);
					break;
				}
			}
		}
		return toret;
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
		
		MinHeapTraversalDJK abc = new MinHeapTraversalDJK();
		int n = abc.getInputs();
		abc.initQueue(n);
		abc.traverse(0);
		abc.printParentArray(abc.parentOfVertex);
		abc.printVisitArray(abc.orderOfVisit);
		
	}

}
