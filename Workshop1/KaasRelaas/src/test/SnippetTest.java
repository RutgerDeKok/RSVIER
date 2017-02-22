package test;

import connection_pool.Password;

public class SnippetTest {

	public static void main(String[] args)  {
		String password = "123456";
		String stored ="ddvzPuB7YkWh2dJKh2D0HQ==$lIVJtguZUs8=";
		try {
			System.out.println(Password.getSaltedHash(password.toCharArray()));
			System.out.println(Password.check(password.toCharArray(), stored));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
