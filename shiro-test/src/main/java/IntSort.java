import java.util.Scanner;

public class IntSort {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int number = in.nextInt();
		int[] a = new int[10];
		for(int i=0; i<number; i++)
			a[i] = in.nextInt();
		a = sort(a);
		
		in.close();
	}
	
	public static int[] sort(int[] a){
		int length = a.length;
		for(int i=0; i<length-1; i++)
			for(int j=i+1; j<length; j++)
				if(a[i] < a[j]){
					int temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
		return a;
	}
}
