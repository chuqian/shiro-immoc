
public class Childer extends Father{

	public Childer(){
		super();
		System.out.println("childer");
	}
	
	public static void main(String[] args) {
		Father father = new Father();
		Childer childer = new Childer();
	}
}
