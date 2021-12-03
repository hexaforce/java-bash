package io.hexaforce.sip;

import java.util.ResourceBundle;

public class Curl extends Bash {

	private final ResourceBundle properties = ResourceBundle.getBundle(Curl.class.getName());
	private final String CURL_PATH = properties.getString("CURL_PATH");

	protected String get(String url) throws ExecuteExeption {
		String cmd = String.format("%s -X GET %s --silent", CURL_PATH, url);
		return bash(cmd);
	}

}
