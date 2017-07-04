package perfectHash;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

import perfectHash.ArrayNode;
import perfectHash.ChainNode;

public class HashingWithChain {
	ChainNode[] chainedHashTable = new ChainNode[20000];
	ArrayNode[] primaryHashTable = new ArrayNode[20000];
	
	static int hashFunction( int x, int m, int a, int p, int b ){
		
		x =  ( ( ( (a*x) + b ) % p ) % m );
		return x;
	}
	
	void printPrimaryHash(){
		int n = this.chainedHashTable.length;
		int i=0;
		while(i<n){
			System.out.print(i + " - ");
			if(this.chainedHashTable[i] == null){
				System.out.println();
			}
			else{
				ChainNode head = this.chainedHashTable[i];
				while(head !=null ){
					System.out.print(head.value + " -> ");
					head = head.next;
				}
				System.out.println();
			}
			i++;
		}
	}
	void printPrimarySecondaryHashTable(){
		int n = this.primaryHashTable.length;
		int i=0,index,Ni;
		while(i<n){
			System.out.print(i + " - ");
			if(this.primaryHashTable[i] == null){
				System.out.println();
			}
			else{
				Ni= this.primaryHashTable[i].count;
				Ni = Ni * Ni;
				index = 0;
				while(index<Ni){
					if(this.primaryHashTable[i].bucket[index] != 0)
						System.out.print(this.primaryHashTable[i].bucket[index] + " -> ");
					index++;
				}
				System.out.println();
			}
			i++;
		}
	}
	
	
	void insert (int hashIndex, int value){
		if(this.chainedHashTable[hashIndex] == null){
			this.chainedHashTable[hashIndex] = new ChainNode();
			this.chainedHashTable[hashIndex].value = value;
			this.chainedHashTable[hashIndex].count = 1;
			this.chainedHashTable[hashIndex].next = null;
		}
		else{
			int tempcount = this.chainedHashTable[hashIndex].count;
			ChainNode temp = new ChainNode();
			temp.value = value;
			temp.count = tempcount + 1;
			temp.next = this.chainedHashTable[hashIndex];
			this.chainedHashTable[hashIndex] = temp;
		}
	}

	int countNi(){
		int n = this.chainedHashTable.length;
		int i=0,count = 0;
		while(i<n){
			if(this.chainedHashTable[i] != null){
				count += ((this.chainedHashTable[i].count) * (this.chainedHashTable[i].count));
			}
			i++;
		}
		return count;
	}
	
	/*To execute this fie stand-alone change the following function to main()*/
	
	public void driver() throws IOException {
		DataInputStream dis = null;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		Random rand = new Random();
		int a=rand.nextInt(10),b=rand.nextInt(1000),m=20000,p=36713, n = 10000;
		int hashIndex,iterator,x,totalNi2 = m+1;
		while(totalNi2 > m){
			iterator = 0;
			
			try{
				fos = new FileOutputStream("outputOfPF.txt");
				fis = new FileInputStream("outputforPF.txt");
				dis = new DataInputStream(fis);
				PrintStream ps = new PrintStream(fos);
				System.setOut(ps);
				while(iterator<n){
					x= dis.readInt();
//					System.out.println(x);
					iterator++;
					hashIndex = hashFunction(x, m, a, p, b);
//					System.out.println(hashIndex);
					this.insert( hashIndex, x);
				}
			
//			System.out.print(hashIndex);
			
			totalNi2=this.countNi();
			}catch(IOException e){
				System.out.println(e.getLocalizedMessage());
			}finally{
				if(fis != null)
					fis.close();
				if(dis != null)
					dis.close();
			}
		}
		System.out.println("Chained Hash");
//		We've found the primary Hash now lets move on to secondary hashing
		this.printPrimaryHash();
		System.out.println("Summation of Ni^2 is " + totalNi2);
		iterator = 0;
		int presentNi, Ni2,toBeHashed;
		while(iterator<m){
			if(this.chainedHashTable[iterator] != null){
				presentNi = this.chainedHashTable[iterator].count;
				Ni2 = presentNi * presentNi ;//nisquare
				a=1+rand.nextInt(10); 
				b=1+rand.nextInt(50);  
				p=36713;

				this.primaryHashTable[iterator] = new ArrayNode(Ni2);
//the next 3 lines are To keep track of a,b,p values for secondary hash in every bucket
				this.primaryHashTable[iterator].a = a;
				this.primaryHashTable[iterator].b = b;
				this.primaryHashTable[iterator].p = p;
				this.primaryHashTable[iterator].count = presentNi;//count copying
				
				ChainNode head = this.chainedHashTable[iterator];
				while(head!=null){
					toBeHashed = head.value;
					hashIndex = hashFunction(toBeHashed, Ni2, a, p, b);
					this.primaryHashTable[iterator].bucket[hashIndex] = toBeHashed;
					head = head.next;
				}
			}
			iterator++;
		}
		
		System.out.println("Done Baby! :)");
		
		this.printPrimarySecondaryHashTable();
		
		
		
		if(fos!=null)
			fos.close();
	}
	
}
