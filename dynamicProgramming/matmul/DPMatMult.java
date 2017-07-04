package matmul;

import java.util.*;

public class DPMatMult {
	
	static int infinity = 1000000;
	static int[][] m;
	int[][] cut;
	int[] order;
	
	void computeTable(int numberOfMat){
		int length,i,hereMin,j,k;
		for(i=0;i<numberOfMat;i++){
			m[i][i] = 0;
		}
		for(length=2;length<numberOfMat;length++){
			for(i=0;i<(numberOfMat-length+1);i++){
				j=i+length-1;
				m[i][j] = infinity;
				for(k=i;k<j;k++){
					hereMin = m[i][k] + m[k+1][j] + (this.order[i]*this.order[k+1]*this.order[j+1]);
					if (hereMin < m[i][j]){
						m[i][j] = hereMin;
						this.cut[i][j] = k;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of matrices");
		int numberOfMat = scan.nextInt();
		int temp = 0,i=0;
		DPMatMult dp=new DPMatMult();
		dp.order = new int[numberOfMat + 1];
		while(i<numberOfMat){
			temp = scan.nextInt();
			dp.order[i++] = temp;
			temp = scan.nextInt();
		}
		dp.order[i] = temp;
		System.out.println(Arrays.toString(dp.order));
		dp.cut = new int[numberOfMat][numberOfMat];
		m = new int[numberOfMat][numberOfMat];
		dp.computeTable(numberOfMat);
		for(i=0;i<numberOfMat;i++){
			System.out.println(Arrays.toString(m[i]));
		}
		System.out.println("End");
		for(i=0;i<numberOfMat;i++){
			System.out.println(Arrays.toString(dp.cut[i]));
		}
		scan.close();
	}

}
