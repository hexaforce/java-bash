package io.hexaforce.sip.bbb;

import io.hexaforce.sip.Curl;

public class GetIP extends Curl {

	public static void main(String[] args) {
		new GetIP().bbb();
	}

	void bbb() {
		try {
			String result = get("inet-ip.info");
			System.out.println(result);
		} catch (ExecuteExeption e) {
			e.printStackTrace();
		}

	}
}
