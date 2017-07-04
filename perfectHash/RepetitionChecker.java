package perfectHash;

import java.io.PrintStream;
import java.util.Arrays;

public class RepetitionChecker {

	public static void main(String[] args) throws Exception {
		PrintStream ps_console = new PrintStream(System.out);
		HashingWithChain hT = new HashingWithChain();
		hT.driver();
		int iterator = 0,Ni,innerIterator = 0,n=10000,m=2*n,check = 0;
		while((iterator<m)){
			if(hT.primaryHashTable[iterator]!=null){
				Ni = hT.primaryHashTable[iterator].count;
				Arrays.sort(hT.primaryHashTable[iterator].bucket);
				for(innerIterator = 0;innerIterator<(Ni - 1);innerIterator++){
					if((hT.primaryHashTable[iterator].bucket[innerIterator] == hT.primaryHashTable[iterator].bucket[innerIterator+1]) &&(hT.primaryHashTable[iterator].bucket[innerIterator] != 0) ){
						System.out.println(hT.primaryHashTable[iterator].bucket[innerIterator]);
						check++;
					}
				}
			}
			iterator++;
		}
		System.setOut(null);

		System.setOut(ps_console);
		if(check == 0){
			System.out.println("Hurray! No repetitions found! ");
		}
		else
			System.out.println("gotchA!");
	}

}
