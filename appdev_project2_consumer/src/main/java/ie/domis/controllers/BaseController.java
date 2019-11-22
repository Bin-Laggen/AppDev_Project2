package ie.domis.controllers;

import java.nio.charset.StandardCharsets;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;

public class BaseController {
	
	HttpHeaders createBasicAuthHeaders(String username, String password) {
		String auth = username + ":" + password;
		byte[] encodeStringIntoBytes = auth.getBytes(StandardCharsets.UTF_8);
		byte[] encodedAuth = Base64.encodeBase64(encodeStringIntoBytes);
		String authHeader = "Basic " + new String(encodedAuth);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authHeader);
		return headers;
	}

}
