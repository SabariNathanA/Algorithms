package transitiveClosure;
import java.util.*;


public class WarshallPathFinder {

	int adjMat[][];
	int path[][];
	int parent[][];
	
	int getInputs(){
		System.out.println("Enter the number of vertices");
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		this.adjMat=new int[n][n];
/* Start of edge  inputs*/
		int i,v,w,u;
		System.out.println("Enter "+n+" if no more edges");
		for(i=0;i<n;i++){
			u=i;
			System.out.println("Enter the edges for "+i+"th vertex as vertex<space>weight");
			v=scan.nextInt();
			while(v!=n){
				w = scan.nextInt();//weight
				this.adjMat[u][v] = 1 + (w*0); //bcos we care only about existance of path.
				System.out.println("Next edge as v <space> w");
				v=scan.nextInt();
			}
		}
		scan.close();
		return n;	
	}
	
	void compute(int numberOfV){
		int i,j,k;
		this.path = new int[numberOfV][numberOfV];
		this.path = this.adjMat;
		this.parent = new int[numberOfV][numberOfV];
		for(i=0;i<numberOfV;i++)
		Arrays.fill(this.parent[i], -1);
		/* i and j represent the source and Destination. while k
		 * represents the number of vertices considered while 
		 * creation at any point in time. if k=3, then first 
		 * 3 vertices are considered */
		for(k=1;k<numberOfV;k++){
			for(i=0;i<numberOfV;i++){
				for(j=0;j<numberOfV;j++){
					if((this.path[i][k]==1) &&(this.path[k][j]==1)){
						this.path[i][j]=1;
						this.parent[i][j] = k;
					}
				}
			}
		}
	}
	
	void printAdjMat(int n,int[][] a){
		int i,j;
		for(i=0;i<n;i++){
			for(j=0;j<n;j++){
				System.out.print(a[i][j]+" | ");
			}
		System.out.println();
		}
	}
	
	public static void main(String[] args) {
		WarshallPathFinder wp = new WarshallPathFinder();
		int n = wp.getInputs();
		wp.compute(n);
		System.out.println("adj mat");
		wp.printAdjMat(n,wp.adjMat);
		System.out.println("Parent");
		wp.printAdjMat(n, wp.parent);
		System.out.println("Path");
		wp.printAdjMat(n, wp.path);
	}

}
