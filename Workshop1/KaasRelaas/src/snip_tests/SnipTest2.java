package snip_tests;



public class SnipTest2 {

	public static void main(String[] args)  {
		
		String s = " 1254 sD ";
		StringBuilder postcode =new StringBuilder (s.toUpperCase().replaceAll("\\s",""));
		System.out.println(postcode);
		System.out.println("is valid format : "+postcode.toString().matches("^\\d{4}[A-Z]{2}"));
	}

}
