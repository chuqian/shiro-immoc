import java.util.Scanner;

public class StrRev {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			String str = in.nextLine();
			str = func(str);
			System.out.println(str);
		}
		in.close();
	}
	
	public static String func(String str){
		StringBuffer sb = new StringBuffer(str.length());
		for(int i=str.length()-1; i>=0; i--)
			sb.append(str.charAt(i));
		return sb.toString();
	}
}
