package perfectHash;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;


public class RandomNumberGeneration {

	public static void main(String[] args) throws IOException {
		int NumberOfNumberToBeGenerated = 10000,iterator,temp;
		int[] a= new int[10000];//10^4
		DataOutputStream dos = null;
		FileOutputStream fos = null;
		DataInputStream in = null;
		FileInputStream fis = null;
		try{
			fos = new FileOutputStream("outputforPF.txt");
			dos = new DataOutputStream(fos);
			
			Random ran = new Random();
			for(iterator=NumberOfNumberToBeGenerated;iterator>0;iterator--){
				temp = 100000000 + ran.nextInt(100000000); //random number between 10^8 - 10^9
				dos.writeInt(temp);
			}
			temp=0;
	 		dos.flush();
	 		System.out.println("Done Baby! :) ");
/*Stuffs for debugging*/
	 		fis = new FileInputStream("outputforPF.txt");
	 		in = new DataInputStream(fis);
	 		
	 		while(iterator<NumberOfNumberToBeGenerated){
	 			temp=in.readInt();
	 			//System.out.printf("%d\n",iterator);
	 			a[iterator] = temp;
	 			iterator++;
	 		}
	 		temp=0;
	 		while(iterator>0){
	 			System.out.println(a[--iterator]);
	 			temp++;
	 		}
	 		System.out.print(temp);
		}catch(IOException e){
			System.out.println(e.getLocalizedMessage());
		}finally{
			if(dos!=null)
				dos.close();
			if(fos!= null)
				fos.close();
			if(in != null)
				in.close();
			if(fis != null)
				fis.close();
		}
	}
}