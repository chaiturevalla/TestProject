package test.companyName.api.utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static test.companyName.api.service.Route.*;

public class TokenManager {
	private static String access_token;
	private static Instant expiry_time;

	public synchronized static String getToken() {
		try {
			if (ConfigLoader.getInstance().getEnvironment().equalsIgnoreCase("sandbox")) {
				if (access_token == null || Instant.now().isAfter(expiry_time)) {
					System.out.println("Renewing token ...");
					Response response = renewToken();
					access_token = response.path("token.access_token");
					String issued_at = response.path("token.issued_at");
					System.out.println("Issued at " + issued_at);
					String expires_at = response.path("token.expires_at");
					System.out.println("Issued at " + expires_at);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
					Date startDate = sdf.parse(issued_at);
					Date endDate = sdf.parse(expires_at);
					long difference_In_Seconds = (endDate.getTime() - startDate.getTime()) / 1000;
					System.out.println("difference_In_Seconds " + difference_In_Seconds);
					expiry_time = Instant.now().plusSeconds(difference_In_Seconds - 3600);
					System.out.println("expiry_time " + expiry_time);
				} else {
					System.out.println("Token is good to use");
				}
			} else {
				if (access_token == null || Instant.now().isAfter(expiry_time)) {
					System.out.println("Renewing token ...");
					Response response = renewToken();
					access_token = response.path("token.access_token");
					String issued_at = response.path("token.issued_at");
					System.out.println("Issued at " + issued_at);
					String expires_at = response.path("token.expires_at");
					System.out.println("Issued at " + expires_at);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
					Date startDate = sdf.parse(issued_at);
					Date endDate = sdf.parse(expires_at);
					long difference_In_Seconds = (endDate.getTime() - startDate.getTime()) / 1000;
					System.out.println("difference_In_Seconds " + difference_In_Seconds);
					expiry_time = Instant.now().plusSeconds(difference_In_Seconds - 3600);
					System.out.println("expiry_time " + expiry_time);
				} else {
					System.out.println("Token is good to use");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ABORT!!! Failed to get token");
		}
		return access_token;
	}

	public static Response renewToken() throws JSONException {
		RestAssured.baseURI = ConfigLoader.getInstance().getBaseURL();
		RestAssured.basePath = ConfigLoader.getInstance().getAuthBasePath();
		JSONObject requestParams = new JSONObject();
		requestParams.put("access_key", ConfigLoader.getInstance().getaccess_key());
		requestParams.put("secret_key", ConfigLoader.getInstance().getSecret_key());
		Response response = RestAssured.given().header("Content-type", "application/json").and()
				.body(requestParams.toString()).when().post(AUTH_TOKEN).then().extract().response();
		if (response.statusCode() != 201) {
			throw new RuntimeException("ABORT!!! Renew Token failed");
		}
		return response;
	}
}