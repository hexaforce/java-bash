package io.hexaforce.sip;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class Bash {

	private final String CURRENT_DIR = ".";

	protected String bash(String cmd) throws ExecuteExeption {
		return bash(cmd, Collections.emptyMap(), CURRENT_DIR);
	}

	protected String bash(String cmd, Map<String, String> env) throws ExecuteExeption {
		return bash(cmd, env, CURRENT_DIR);
	}

	protected String bash(String cmd, String workDir) throws ExecuteExeption {
		return bash(cmd, Collections.emptyMap(), workDir);
	}

	/**
	 * 
	 * @param workDir
	 * @param env
	 * @param cmd
	 * @return
	 * @throws ExecuteExeption
	 * 
	 */
	protected String bash(String cmd, Map<String, String> env, String workDir) throws ExecuteExeption {
		try {

			String[] cmdarray = new String[] { "/bin/bash", "-c", cmd };
			String[] envp = null;
			if (env != null && !env.isEmpty())
				envp = env.entrySet().stream().map(e -> String.format("%s=%s", e.getKey(), e.getValue())).collect(Collectors.toList()).toArray(String[]::new);
			File dir = new File(workDir);
			if (!dir.exists())
				dir.mkdir();

			Process process = Runtime.getRuntime().exec(cmdarray, envp, dir);

			try {
				process.waitFor();
				if (process.exitValue() == 0)
					return read(process.getInputStream());
				throw new ExecuteExeption(read(process.getErrorStream()));
			} catch (InterruptedException e) {
				throw new ExecuteExeption(e.getMessage());
			} finally {
				process.destroy();
			}

		} catch (IOException e) {
			throw new ExecuteExeption(e.getMessage());
		}
	}

	/**
	 * 
	 * @param inputStream
	 * @return
	 * @throws ExecuteExeption
	 * 
	 */
	String read(InputStream inputStream) throws ExecuteExeption {
		try {
			return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8).trim();
		} catch (IOException e) {
			throw new ExecuteExeption(e.getMessage());
		}
	}

	public static class ExecuteExeption extends Exception {
		private static final long serialVersionUID = 1L;

		public ExecuteExeption(String message) {
			super(message);
		}
	}

}