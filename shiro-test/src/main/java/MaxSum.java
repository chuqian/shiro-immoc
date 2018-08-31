
import java.util.Scanner;

public class MaxSum {
	
	public static int maxSum(int[] a,int n){
		if(a.length == 0) 
			return 0;
		//记录前k个元素中的子数组最大和
		int max = Integer.MIN_VALUE;
		//记录以当前元素结尾的连续子数组最大和
		int curMax = 0;
		for(int i=0; i<n; ++i){
			if(curMax <= 0){
				curMax = a[i];
			}
			else{
				curMax += a[i];
			}
			if(max < curMax){
				max = curMax;
			}
		}
		return max;
	}

	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int n=in.nextInt();
		int [] a = new int [n];
		
		for(int i=0; i<n; ++i){
			a[i] = in.nextInt();
		}
		int res = maxSum(a,n);
		System.out.println(res);
	}
}