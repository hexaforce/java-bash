package io.hexaforce.sip;

import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MySQL extends Bash {

	private final ResourceBundle properties = ResourceBundle.getBundle(MySQL.class.getName());
	private final String BASIC_MYSQL_COMMAND = properties.getString("MYSQL_CLIENT") + " -u ${MYSQL_USER} -p${MYSQL_PASSWORD} -h ${MYSQL_HOST} -P ${MYSQL_PORT} -D ${MYSQL_DATABASE}";

	private final Map<String, String> env = Arrays.asList(//
			"MYSQL_HOST", //
			"MYSQL_PORT", //
			"MYSQL_DATABASE", //
			"MYSQL_USER", //
			"MYSQL_PASSWORD"//
	).stream().collect(Collectors.toMap(k -> k, v -> properties.getString(v)));

	protected String execute(String SQL) throws ExecuteExeption {
		String cmd = String.format("%s --execute '%s;' --silent --skip-column-names", BASIC_MYSQL_COMMAND, SQL);
		return bash(cmd, env);
	}

}
