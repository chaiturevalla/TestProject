package test.companyName.api.hooks;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
import static test.companyName.api.service.RestAssuredCore.closeLogger;
import static test.companyName.api.service.RestAssuredCore.initializeLogger;
import static test.companyName.api.service.Route.AUTH_TOKEN;

import java.lang.reflect.Method;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.google.common.collect.ImmutableMap;

import test.companyName.api.service.RestAssuredCore;
import test.companyName.api.utils.ConfigLoader;
import test.companyName.api.utils.TokenManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APIHook {

	protected static RestAssuredCore restAssuredCore;
	protected String token;
	
	@BeforeSuite
    void setAllureEnvironment() {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Enviroment", System.getProperty("tier"))
                        .put("URL", System.getProperty("baseURL"))
                        .put("URL", System.getProperty("X-Auth-User"))
                        .build(), System.getProperty("user.dir")
                        + "/allure-results/");
        System.out.print("Settign up environment");
    }

	@BeforeMethod
	public void init() throws IOException, JSONException {
		initializeLogger();
		token = TokenManager.getToken();
		System.out.println("Token Generated " + token);
		restAssuredCore = new RestAssuredCore(ConfigLoader.getInstance().getBaseURL(),
				ConfigLoader.getInstance().getBasePath());
		restAssuredCore.setHeader("X-Auth-User", ConfigLoader.getInstance().getXAuthUser());
		restAssuredCore.setHeader("X-Auth-Token", token);
	}

	public static Response NewUserToken(String access_Key, String secret_Key) throws JSONException {
		// TODO Auto-generated method stub
		RestAssured.baseURI = ConfigLoader.getInstance().getBaseURL();
		RestAssured.basePath = ConfigLoader.getInstance().getAuthBasePath();

		JSONObject requestParams = new JSONObject();
		requestParams.put("access_key", access_Key);
		requestParams.put("secret_key", secret_Key);

		Response response = RestAssured.given().header("Content-type", "application/json").and()
				.body(requestParams.toString()).when().post(AUTH_TOKEN).then().log().all().extract().response();
		/*
		 * if (response.statusCode() != 201) { throw new
		 * RuntimeException("ABORT!!! Renew Token failed"); }
		 */
		return response;
	}
	
	
	@BeforeMethod
	public void beforeTestCase(Method m) {
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("STARTING TEST: " + m.getName());
		System.out.println("THREAD ID: " + Thread.currentThread().getId());
		System.out.println("-------------------------------------------------------------------------------");
	}

	@AfterMethod
	public void tearDown(Method m) {
		restAssuredCore.resetRestConfig("all");
		closeLogger();
		System.out.println("Ending TEST: " + m.getName());
		System.out.println("THREAD ID: " + Thread.currentThread().getId());
		System.out.println("-------------------------------------------------------------------------------");
	}

}
