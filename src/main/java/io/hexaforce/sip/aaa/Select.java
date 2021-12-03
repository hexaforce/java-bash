package io.hexaforce.sip.aaa;

import io.hexaforce.sip.MySQL;

public class Select extends MySQL {

	public static void main(String[] args) {
		new Select().aaa();
	}

	void aaa() {

		try {
			String result = execute("show tables");
			System.out.println(result);

		} catch (ExecuteExeption e) {
			e.printStackTrace();
		}
	}

}
