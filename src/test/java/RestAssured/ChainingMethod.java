package RestAssured;

public class ChainingMethod {

	public static void main(String[] args) {
//		ChainingMethod chain=new ChainingMethod();
		//chain.a1().a2().a3();
//		chain.a1();
//		chain.a2();
//		chain.a3();
		a1().a2().a3();
	}

	public static ChainingMethod a3() {
		// TODO Auto-generated method stub
		System.out.println("hii a3");
		return new ChainingMethod();
	}

	public static ChainingMethod a2() {
		// TODO Auto-generated method stub
		System.out.println("hii a2");
		
		return new ChainingMethod();
	}

	public static ChainingMethod a1() {
		// TODO Auto-generated method stub
		System.out.println("hii a1");
		
		return new ChainingMethod();
	}
}
