package perfectHash;

import java.io.PrintStream;

public class Mod10differenceinHash {

	public static void main(String[] args) throws Exception{
		int mod = 10000000;
		PrintStream ps_console = new PrintStream(System.out);
		HashingWithChain hT = new HashingWithChain();
		hT.driver();
		System.setOut(null);
	
		System.setOut(ps_console);
		int iterator = 0,Ni,innerIterator = 0,temp,innerJ,n=10000,m=2*n,check = 0;
		while((iterator<m)){
			if(hT.primaryHashTable[iterator]!=null){
				Ni = hT.primaryHashTable[iterator].count;
				if(Ni>1){
					for(innerIterator = 0; innerIterator<(Ni - 1);innerIterator++){
						for(innerJ = innerIterator + 1; innerJ<Ni; innerJ++){
							if(((hT.primaryHashTable[iterator].bucket[innerIterator]) != 0) && ((hT.primaryHashTable[iterator].bucket[innerJ]) !=0))
							{
								temp = (hT.primaryHashTable[iterator].bucket[innerIterator]) - (hT.primaryHashTable[iterator].bucket[innerJ]);
							
								if((Math.abs(temp))<=mod){
									System.out.println(hT.primaryHashTable[iterator].bucket[innerIterator] + " - " + hT.primaryHashTable[iterator].bucket[innerJ] +" in bucket " + iterator);
									check++;
								}
							}
						}
					}
				}
			}
			iterator++;
		}		
		if(check == 0){
			System.out.println("Hurray! No modulo "+mod+" difference elements found! ");
		}
		else
			System.out.println("gotcha! - " + check+" items");
	
	}

}
