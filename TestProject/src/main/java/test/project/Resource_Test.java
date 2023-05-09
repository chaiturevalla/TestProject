package test.companyName.api.resource.tests;

import static test.companyName.api.service.Route.APPLICATION_JSON;
import static test.companyName.api.service.Route.CONTENT_TYPE;
import static test.companyName.api.service.Route.Create_Environment_Business_Application_PATH;
import static test.companyName.api.service.Route.DELETE;
import static test.companyName.api.service.Route.GET;
import static test.companyName.api.service.Route.LIST_ENVIRONMENT_IN_BUSINESS_APPLICATION;
import static test.companyName.api.service.Route.LIST_TAG_PATTERN_IN_BUSINESS_APPLICATION;
import static test.companyName.api.service.Route.POST;
import static test.companyName.api.service.Route.PUT;
import static test.companyName.api.service.Route.VIEW_ENVIRONMENT_IN_BUSINESS_APPLICATION;
import static test.companyName.api.service.Route.VIEW_TAG_PATTERN_IN_BUSINESS_APPLICATION;
import static test.companyName.api.service.Route.Create_TagPattern_Business_Application_PATH;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import test.companyName.api.applicationApi.Inventoryapi;
import test.companyName.api.applicationApi.ResourceAPI;
import test.companyName.api.hooks.APIHook;
import test.companyName.api.service.AssertionMethods;
import test.companyName.api.service.RestResponse;
import test.companyName.api.utils.ConfigLoader;
import test.companyName.api.utils.FakerUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

/**
 * @author shanumuka
 *
 */
@Epic("companyName Resource Rest API")
@Feature("Resource API Suite")
public class Resource_Test extends APIHook {

	String environment_id;
	String tagpattern_id;

	@Story("View Cost Center in Business Application")
	@Description("View Cost Center in Business Application - View CostCenter details")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200  Code", priority = 1, enabled = true)
	public void ViewCostcenterInBusinessApp() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String costCenterId = ResourceAPI.getRandomCostCenterId();
		String url = "/resources/business_applications/cost_center/" + tenantId + "/get/" + costCenterId;
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(GET, url);
		AssertionMethods.Assertion_200(response);
		SoftAssert softAssert = new SoftAssert();
		JSONObject jsonobj = new JSONObject(response.getAPIResponseAsString());
		softAssert.assertEquals(jsonobj.get("cost_center_id"), costCenterId);
		softAssert.assertAll();
	}

	@Story("View Cost Center in Business Application")
	@Description("View Cost Center in Business Application - Invalid Request")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 400  Code", priority = 2, enabled = true)
	public void ViewCostcenterInBusinessApp_400() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String costCenterId = "abcdef";
		String url = "/resources/business_applications/cost_center/" + tenantId + "/get/" + costCenterId;
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(GET, url);
		AssertionMethods.Assertion_400(response);
		SoftAssert softAssert = new SoftAssert();
		JSONObject jsonobj = new JSONObject(response.getAPIResponseAsString());
		//softAssert.assertEquals(jsonobj.get("message"), "'" + costCenterId
				//+ "' is not a valid ObjectId, it must be a 12-byte input or a 24-character hex string");
		softAssert.assertAll();
	}

	@Story("View Cost Center in Business Application")
	@Description("View Cost Center in Business Application - Invalid Url")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 404  Code", priority = 3, enabled = true)
	public void ViewCostcenterInBusinessApp_404() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String costCenterId = ResourceAPI.getRandomCostCenterId();
		String url = "/resources/business_applications/cost_centers/" + tenantId + "/get/" + costCenterId;
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(GET, url);
		AssertionMethods.Assertion_404(response);
		;
	}

	@Story("View Cost Center in Business Application")
	@Description("View Cost Center in Business Application - Invalid Authorization")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 401  Code", priority = 4, enabled = true)
	public void ViewCostcenterInBusinessApp_401() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String costCenterId = ResourceAPI.getRandomCostCenterId();
		String url = "/resources/business_applications/cost_center/" + tenantId + "/get/" + costCenterId;
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setHeader("X-Auth-User", "")
				.invokeRestCall(GET, url);
		AssertionMethods.Assertion_401(response);
		SoftAssert softAssert = new SoftAssert();
		JSONObject jsonobj = new JSONObject(response.getAPIResponseAsString());
		softAssert.assertEquals(jsonobj.get("message"), "Invalid username in X-Auth-user");
		softAssert.assertAll();
	}

	@Story("List Environment in Business Application")
	@Description("List Environment in Business Application with tenant Id")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200  Code", enabled = true)
	public void ListEnvironmentInBusinessApplication() throws JSONException, IOException {
		ResourceAPI.ListEnvironmentInBusinessApplication(LIST_ENVIRONMENT_IN_BUSINESS_APPLICATION, false);
	}

	@Story("List Environment in Business Application")
	@Description("List Environment in Business Application with pagelimt and page no query params")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200  Code", priority = 3, enabled = true)
	public void ListEnvironmentInBusinessApplicationPageLimit() throws JSONException, IOException {
		ResourceAPI.ListEnvironmentInBusinessApplication(LIST_ENVIRONMENT_IN_BUSINESS_APPLICATION, true);
	}

	@Story("List Environment in Business Application")
	@Description("List Environment in Business Application - Invalid Request")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 400  Code", priority = 3, enabled = true)
	public void ListEnvironmentInBusinessApplication_400() throws JSONException, IOException {

		Map<String, String> queryparamlist = new HashMap<String, String>();
		queryparamlist.put("limit", "q");
		queryparamlist.put("page", "f");
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON)
				.setMultiQueryParams(queryparamlist).invokeRestCall(GET, LIST_ENVIRONMENT_IN_BUSINESS_APPLICATION);
		AssertionMethods.Assertion_400(response);
		SoftAssert softAssert = new SoftAssert();
		JSONObject jsonobj = new JSONObject(response.getAPIResponseAsString());
//			softAssert.assertEquals(jsonobj.get("message"), "invalid literal for int() with base 10: 'f'");
		softAssert.assertEquals(jsonobj.get("message"), "Something Went Wrong!..");
		softAssert.assertAll();
	}

	@Story("List Environment in Business Application")
	@Description("List Environment in Business Application - Invalid Url")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 404  Code", priority = 3, enabled = true)
	public void ListEnvironmentInBusinessApplication_404() throws JSONException, IOException {
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(GET,
				LIST_ENVIRONMENT_IN_BUSINESS_APPLICATION + "/view");
		AssertionMethods.Assertion_404(response);
	}

	@Story("List Environment in Business Application")
	@Description("List Environment in Business Application - Invalid Authorization")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 401  Code", priority = 3, enabled = true)
	public void ListEnvironmentInBusinessApplication_401() throws JSONException, IOException {
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setHeader("X-Auth-User", "ds")
				.invokeRestCall(GET, LIST_ENVIRONMENT_IN_BUSINESS_APPLICATION);
		AssertionMethods.Assertion_401(response);
		SoftAssert softAssert = new SoftAssert();
		JSONObject jsonobj = new JSONObject(response.getAPIResponseAsString());
		softAssert.assertEquals(jsonobj.get("message"), "Invalid username in X-Auth-user");
		softAssert.assertAll();
	}

	/**
	 * Disabled the test case as Content type header is missing {@link} -
	 * https://dev.azure.com/companyName-Tech/Product_Mgmt/_workitems/edit/26566
	 */

	@Story("Create Environment in Business Application")
	@Description("Create Environment in Business Application under the tenant.")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Code", priority = 10, enabled = true)
	public void CreateEnvironmentBusinessApplication() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String Clouddetails[] = Inventoryapi.GetExistingCloudidandCloudname("AWS");
		String CloudAccntId = Clouddetails[1];
		String url = "/resources/business_applications/environment/create/" + tenantId;
		String CreateEnvironmentBusinessApplicationPayload = new String(
				Files.readAllBytes(Paths.get(Create_Environment_Business_Application_PATH)));
		CreateEnvironmentBusinessApplicationPayload = CreateEnvironmentBusinessApplicationPayload
				.replace("CloundAccountID", CloudAccntId);
		JSONObject resourceStandardObject = new JSONObject(CreateEnvironmentBusinessApplicationPayload);
		JSONArray arr = new JSONArray();
		String name = FakerUtils.generateName();
		arr.put(name);
		resourceStandardObject.put("name", name);
		System.out.println(resourceStandardObject.toString());
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(POST, url,
				resourceStandardObject.toString());
		response.getAPIResponseAsPrettyPrint();
		AssertionMethods.Assertion_200(response);
		SoftAssert softAssert = new SoftAssert();
		JSONObject jsonobj = new JSONObject(response.getAPIResponseAsString());
		environment_id = jsonobj.getString("environment_id");
		System.out.println("environment id : " + environment_id);
		softAssert.assertAll();
	}

	@Story("Create Environment in Business Application")
	@Description("Create Environment in Business Application under the tenant.")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 400 Code", priority = 10, enabled = true)
	public void CreateEnvironmentBusinessApplication400() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String Clouddetails[] = Inventoryapi.GetExistingCloudidandCloudname("AWS");
		String CloudAccntId = Clouddetails[1];
		String url = "/resources/business_applications/environment/create/" + tenantId;
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(POST, url);
		response.getAPIResponseAsPrettyPrint();
		AssertionMethods.Assertion_400(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}

	@Story("Create Environment in Business Application")
	@Description("Create Environment in Business Application under the tenant.")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 401 Code", priority = 10, enabled = true)
	public void CreateEnvironmentBusinessApplication401() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String Clouddetails[] = Inventoryapi.GetExistingCloudidandCloudname("AWS");
		String CloudAccntId = Clouddetails[1];
		String url = "/resources/business_applications/environment/create/" + tenantId;
		String CreateEnvironmentBusinessApplicationPayload = new String(
				Files.readAllBytes(Paths.get(Create_Environment_Business_Application_PATH)));
		CreateEnvironmentBusinessApplicationPayload = CreateEnvironmentBusinessApplicationPayload
				.replace("CloundAccountID", CloudAccntId);
		JSONObject resourceStandardObject = new JSONObject(CreateEnvironmentBusinessApplicationPayload);
		JSONArray arr = new JSONArray();
		String name = FakerUtils.generateName();
		arr.put(name);
		resourceStandardObject.put("name", name);
		System.out.println(resourceStandardObject.toString());
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setHeader("X-Auth-User", "")
				.invokeRestCall(POST, url, resourceStandardObject.toString());
		response.getAPIResponseAsPrettyPrint();
		AssertionMethods.Assertion_401(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}

	@Story("View Environment in Business Application")
	@Description("View Environment in Business Application View Environment details using new environment ID created.")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Code", priority = 11, enabled = true, dependsOnMethods = {
			"CreateEnvironmentBusinessApplication" })
	public void ViewEnvironmentInBusinessApplicationWithChain() throws JSONException, IOException {
		ResourceAPI.ViewEnvironmentInBusinessApplication(VIEW_ENVIRONMENT_IN_BUSINESS_APPLICATION, environment_id);
	}

	@Story("Update Environment in Business Application")
	@Description("Update Environment details if required after creation using the api, the update by a user depends on the scope of the Environment created check create Environment docs for more details on the scope")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Code", priority = 12, dependsOnMethods = {
			"CreateEnvironmentBusinessApplication" }, enabled = true)
	public void UpdateEnvironmentBusinessApplication() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String Clouddetails[] = Inventoryapi.GetExistingCloudidandCloudname("AWS");
		String CloudAccntId = Clouddetails[1];
		String url = "/resources/business_applications/environment/" + tenantId + "/update/" + environment_id;
		String CreateEnvironmentBusinessApplicationPayload = new String(
				Files.readAllBytes(Paths.get(Create_Environment_Business_Application_PATH)));
		CreateEnvironmentBusinessApplicationPayload = CreateEnvironmentBusinessApplicationPayload
				.replace("CloundAccountID", CloudAccntId);
		JSONObject resourceStandardObject = new JSONObject(CreateEnvironmentBusinessApplicationPayload);
		JSONArray arr = new JSONArray();
		String name = FakerUtils.generateName();
		arr.put(name);
		resourceStandardObject.put("name", name);
		System.out.println(resourceStandardObject.toString());
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(PUT, url,
				resourceStandardObject.toString());
		response.getAPIResponseAsPrettyPrint();
		AssertionMethods.Assertion_200(response);
		SoftAssert softAssert = new SoftAssert();
		JSONObject jsonobj = new JSONObject(response.getAPIResponseAsString());
		environment_id = jsonobj.getString("environment_id");
		System.out.println("environment id : " + environment_id);
		softAssert.assertAll();
	}

	@Story("Update Environment in Business Application")
	@Description("Update Environment details if required after creation using the api, the update by a user depends on the scope of the Environment created check create Environment docs for more details on the scope")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 400 Code", priority = 12, enabled = true)
	public void UpdateEnvironmentBusinessApplication400() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String url = "/resources/business_applications/environment/" + tenantId + "/update/" + environment_id;
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(PUT, url);
		response.getAPIResponseAsPrettyPrint();
		AssertionMethods.Assertion_400(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}

	@Story("Update Environment in Business Application")
	@Description("Update Environment details if required after creation using the api, the update by a user depends on the scope of the Environment created check create Environment docs for more details on the scope")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 401 Code", priority = 12, enabled = true)
	public void UpdateEnvironmentBusinessApplication401() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String Clouddetails[] = Inventoryapi.GetExistingCloudidandCloudname("AWS");
		String CloudAccntId = Clouddetails[1];
		String url = "/resources/business_applications/environment/" + tenantId + "/update/" + environment_id;
		String CreateEnvironmentBusinessApplicationPayload = new String(
				Files.readAllBytes(Paths.get(Create_Environment_Business_Application_PATH)));
		CreateEnvironmentBusinessApplicationPayload = CreateEnvironmentBusinessApplicationPayload
				.replace("CloundAccountID", CloudAccntId);
		JSONObject resourceStandardObject = new JSONObject(CreateEnvironmentBusinessApplicationPayload);
		JSONArray arr = new JSONArray();
		String name = FakerUtils.generateName();
		arr.put(name);
		resourceStandardObject.put("name", name);
		System.out.println(resourceStandardObject.toString());
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setHeader("X-Auth-User", "")
				.invokeRestCall(PUT, url, resourceStandardObject.toString());
		response.getAPIResponseAsPrettyPrint();
		AssertionMethods.Assertion_401(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}

	@Story("Delete Environment in Business Application")
	@Description("Delete a Environment which is no more required")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Code", priority = 14, dependsOnMethods = {
			"CreateEnvironmentBusinessApplication" }, enabled = true)
	public void DeleteEnvironmentBusinessApplication() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String url = "/resources/business_applications/environment/" + tenantId + "/delete/" + environment_id;
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(DELETE, url);
		response.getAPIResponseAsPrettyPrint();
		AssertionMethods.Assertion_200(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}

	@Story("Delete Environment in Business Application")
	@Description("Delete a Environment which is no more required")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Code", priority = 14, dependsOnMethods = {
			"CreateEnvironmentBusinessApplication" }, enabled = true)
	public void DeleteEnvironmentBusinessApplication400() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String url = "/resources/business_applications/environment/" + tenantId + "/delete/" + 1234;
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(DELETE, url);
		response.getAPIResponseAsPrettyPrint();
		AssertionMethods.Assertion_400(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}

	@Story("Delete Environment in Business Application")
	@Description("Delete a Environment which is no more required")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 401 Code", priority = 14, dependsOnMethods = {
			"CreateEnvironmentBusinessApplication" }, enabled = true)
	public void DeleteEnvironmentBusinessApplication401() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String url = "/resources/business_applications/environment/" + tenantId + "/delete/" + environment_id;
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setHeader("X-Auth-User", "")
				.invokeRestCall(DELETE, url);
		response.getAPIResponseAsPrettyPrint();
		AssertionMethods.Assertion_401(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}

	@Story("View Environment in Business Application")
	@Description("View Environment in Business Application View Environment details.")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Code", priority = 4, enabled = false)
	public void ViewEnvironmentInBusinessApplication() throws JSONException, IOException {
		String envId = ResourceAPI.getRandomEnvId();
		ResourceAPI.ViewEnvironmentInBusinessApplication(VIEW_ENVIRONMENT_IN_BUSINESS_APPLICATION, envId);
	}

	@Story("View Environment in Business Application")
	@Description("View Environment in Business Application View Environment details. - Invalid Request")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 400  Code", priority = 4, enabled = false)
	public void ViewEnvironmentInBusinessApplication_400() throws JSONException, IOException {
		String envId = "fd";
		String url = VIEW_ENVIRONMENT_IN_BUSINESS_APPLICATION + envId;
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(GET, url);
		AssertionMethods.Assertion_400(response);
		SoftAssert softAssert = new SoftAssert();
		JSONObject jsonobj = new JSONObject(response.getAPIResponseAsString());
		softAssert.assertEquals(jsonobj.get("message"),
				"'" + envId + "' is not a valid ObjectId, it must be a 12-byte input or a 24-character hex string");
		softAssert.assertAll();
	}

	@Story("View Environment in Business Application")
	@Description("View Environment in Business Application View Environment details - Invalid Url")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 404  Code", priority = 4, enabled = true)
	public void ViewEnvironmentInBusinessApplication_404() throws JSONException, IOException {
		String envId = ResourceAPI.getRandomEnvId();
		String url = VIEW_ENVIRONMENT_IN_BUSINESS_APPLICATION + 1234 + "/";
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(GET, url);
		AssertionMethods.Assertion_404(response);
	}

	@Story("View Environment in Business Application")
	@Description("View Environment in Business Application View Environment details - Invalid Authorization")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 401  Code", priority = 4, enabled = true)
	public void ViewEnvironmentInBusinessApplication_401() throws JSONException, IOException {
		String envId = ResourceAPI.getRandomEnvId();
		String url = VIEW_ENVIRONMENT_IN_BUSINESS_APPLICATION + environment_id;
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setHeader("X-Auth-User", "aa")
				.invokeRestCall(GET, url);
		AssertionMethods.Assertion_401(response);
		SoftAssert softAssert = new SoftAssert();
		JSONObject jsonobj = new JSONObject(response.getAPIResponseAsString());
		softAssert.assertEquals(jsonobj.get("message"), "Invalid username in X-Auth-user");
		softAssert.assertAll();
	}

	@Story("Create Tag Pattern in Business Application")
	@Description("Create Tag Pattern in Business Application under the tenant.")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Code", priority = 18, enabled = true)
	public void CreateTagPatternBusinessApplication() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String url = "/resources/business_applications/tag_pattern/create/" + tenantId;
		String CreateEnvironmentBusinessApplicationPayload = new String(
				Files.readAllBytes(Paths.get(Create_TagPattern_Business_Application_PATH)));
		JSONObject resourceStandardObject = new JSONObject(CreateEnvironmentBusinessApplicationPayload);
		JSONArray arr = new JSONArray();
		String name = FakerUtils.generateName();
		arr.put(name);
		resourceStandardObject.put("name", name);
		System.out.println(resourceStandardObject.toString());
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(POST, url,
				resourceStandardObject.toString());
		response.getAPIResponseAsPrettyPrint();
		AssertionMethods.Assertion_200(response);
		SoftAssert softAssert = new SoftAssert();
		JSONObject jsonobj = new JSONObject(response.getAPIResponseAsString());
		tagpattern_id = jsonobj.getString("tagpattern_id");
		System.out.println("tagpattern_id : " + tagpattern_id);
		softAssert.assertAll();
	}

	@Story("Create Tag Pattern in Business Application")
	@Description("Create Tag Pattern in Business Application under the tenant.")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 400 Code", priority = 18, enabled = true)
	public void CreateTagPatternBusinessApplication400() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String url = "/resources/business_applications/tag_pattern/create/" + tenantId;
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(POST, url);
		response.getAPIResponseAsPrettyPrint();
		AssertionMethods.Assertion_400(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}
	
	@Story("Create Tag Pattern in Business Application")
	@Description("Create Tag Pattern in Business Application under the tenant.")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 401 Code", priority = 18, enabled = true)
	public void CreateTagPatternBusinessApplication401() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String url = "/resources/business_applications/tag_pattern/create/" + tenantId;
		String CreateEnvironmentBusinessApplicationPayload = new String(
				Files.readAllBytes(Paths.get(Create_TagPattern_Business_Application_PATH)));
		JSONObject resourceStandardObject = new JSONObject(CreateEnvironmentBusinessApplicationPayload);
		JSONArray arr = new JSONArray();
		String name = FakerUtils.generateName();
		arr.put(name);
		resourceStandardObject.put("name", name);
		System.out.println(resourceStandardObject.toString());
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setHeader("X-Auth-User", "").invokeRestCall(POST, url,
				resourceStandardObject.toString());
		response.getAPIResponseAsPrettyPrint();
		AssertionMethods.Assertion_401(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}
	
	@Story("Update Tag Pattern in Business Application")
	@Description("Update Tag Pattern in Business Application under the tenant.")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Code", priority = 19, enabled = true,dependsOnMethods = {
	"CreateTagPatternBusinessApplication" })
	public void UpdateTagPatternBusinessApplication() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String url = "/resources/business_applications/tag_pattern/"+tenantId+"/update/"+tagpattern_id;
		String CreateEnvironmentBusinessApplicationPayload = new String(
				Files.readAllBytes(Paths.get(Create_TagPattern_Business_Application_PATH)));
		JSONObject resourceStandardObject = new JSONObject(CreateEnvironmentBusinessApplicationPayload);
		JSONArray arr = new JSONArray();
		String name = FakerUtils.generateName();
		arr.put(name);
		resourceStandardObject.put("name", name);
		System.out.println(resourceStandardObject.toString());
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(PUT, url,
				resourceStandardObject.toString());
		response.getAPIResponseAsPrettyPrint();
		AssertionMethods.Assertion_200(response);
		SoftAssert softAssert = new SoftAssert();
		JSONObject jsonobj = new JSONObject(response.getAPIResponseAsString());
		tagpattern_id = jsonobj.getString("tagpattern_id");
		System.out.println("tagpattern_id : " + tagpattern_id);
		softAssert.assertAll();
	}
	
	@Story("Update Tag Pattern in Business Application")
	@Description("Update Tag Pattern in Business Application under the tenant.")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 400 Code", priority = 19, enabled = true)
	public void UpdateTagPatternBusinessApplication400() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String url = "/resources/business_applications/tag_pattern/"+tenantId+"/update/"+tagpattern_id;
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(PUT, url);
		response.getAPIResponseAsPrettyPrint();
		AssertionMethods.Assertion_400(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}
	
	@Story("Update Tag Pattern in Business Application")
	@Description("Update Tag Pattern in Business Application under the tenant.")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 401 Code", priority = 19, enabled = true)
	public void UpdateTagPatternBusinessApplication401() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String url = "/resources/business_applications/tag_pattern/"+tenantId+"/update/"+tagpattern_id;
		String CreateEnvironmentBusinessApplicationPayload = new String(
				Files.readAllBytes(Paths.get(Create_TagPattern_Business_Application_PATH)));
		JSONObject resourceStandardObject = new JSONObject(CreateEnvironmentBusinessApplicationPayload);
		JSONArray arr = new JSONArray();
		String name = FakerUtils.generateName();
		arr.put(name);
		resourceStandardObject.put("name", name);
		System.out.println(resourceStandardObject.toString());
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setHeader("X-Auth-User", "").invokeRestCall(PUT, url,
				resourceStandardObject.toString());
		response.getAPIResponseAsPrettyPrint();
		AssertionMethods.Assertion_401(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}
	
	@Story("Delete Tag Pattern in Business Application")
	@Description("Delete Tag Pattern in Business Application under the tenant.")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Code", priority = 22, enabled = true)
	public void DeleteTagPatternBusinessApplication() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String url = "/resources/business_applications/tag_pattern/"+tenantId+"/delete/"+tagpattern_id;
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(DELETE, url);
		response.getAPIResponseAsPrettyPrint();
		AssertionMethods.Assertion_200(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}
	
	@Story("Delete Tag Pattern in Business Application")
	@Description("Delete Tag Pattern in Business Application under the tenant.")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 400 Code", priority = 22, enabled = true)
	public void DeleteTagPatternBusinessApplication400() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String url = "/resources/business_applications/tag_pattern/"+tenantId+"/delete/"+12344;
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(DELETE, url);
		response.getAPIResponseAsPrettyPrint();
		AssertionMethods.Assertion_400(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}
	
	@Story("Delete Tag Pattern in Business Application")
	@Description("Delete Tag Pattern in Business Application under the tenant.")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 401 Code", priority = 22, enabled = true)
	public void DeleteTagPatternBusinessApplication401() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String url = "/resources/business_applications/tag_pattern/"+tenantId+"/delete/"+tagpattern_id;
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setHeader("X-Auth-User", "").invokeRestCall(DELETE, url);
		response.getAPIResponseAsPrettyPrint();
		AssertionMethods.Assertion_401(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}
	
	
	
	@Story("List Tag Pattern in Business Application")
	@Description("List Tag Pattern in Business Application Displays the list of Tag Patterns.")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200  Code", enabled = true)
	public void ListTagPatternInBusinessApplication() throws JSONException, IOException {
		ResourceAPI.ListTagPatternInBusinessApplicationApi(LIST_TAG_PATTERN_IN_BUSINESS_APPLICATION, false);
	}

	@Story("List Tag Pattern in Business Application")
	@Description("List Tag Pattern in Business Application with Page Limit")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200  Code", enabled = true)
	public void ListTagPatternInBusinessApplicationWithPageLimit() throws JSONException, IOException {
		ResourceAPI.ListTagPatternInBusinessApplicationApi(LIST_TAG_PATTERN_IN_BUSINESS_APPLICATION, true);
	}

	@Story("List Tag Pattern in Business Application")
	@Description("List Tag Pattern in Business Application - Invalid Request")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 400  Code", enabled = true)
	public void ListTagPatternInBusinessApplication_400() throws JSONException, IOException {
		Map<String, String> queryparamlist = new HashMap<String, String>();
		queryparamlist.put("limit", "1");
		queryparamlist.put("page", "aa");
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON)
				.setMultiQueryParams(queryparamlist).invokeRestCall(GET, LIST_TAG_PATTERN_IN_BUSINESS_APPLICATION);
		AssertionMethods.Assertion_400(response);
		SoftAssert softAssert = new SoftAssert();
		JSONObject jsonobj = new JSONObject(response.getAPIResponseAsString());
//			softAssert.assertEquals(jsonobj.get("message"), "invalid literal for int() with base 10: 'aa'");
		softAssert.assertEquals(jsonobj.get("message"), "Something Went Wrong!..");
		softAssert.assertAll();
	}

	@Story("List Tag Pattern in Business Application")
	@Description("List Tag Pattern in Business Application - Invalid Url")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 404  Code", enabled = true)
	public void ListTagPatternInBusinessApplication_404() throws JSONException, IOException {
		Map<String, String> queryparamlist = new HashMap<String, String>();
		queryparamlist.put("limit", "1");
		queryparamlist.put("page", "aa");
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON)
				.setMultiQueryParams(queryparamlist)
				.invokeRestCall(GET, LIST_TAG_PATTERN_IN_BUSINESS_APPLICATION + "/");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(response.getStatusCode(), 404);
		softAssert.assertEquals(response.getStatusLine(), "HTTP/1.1 404 Not Found");
		softAssert.assertAll();
	}

	@Story("List Tag Pattern in Business Application")
	@Description("List Tag Pattern in Business Application - Invalid Authorization")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 401  Code", enabled = true)
	public void ListTagPatternInBusinessApplication_401() throws JSONException, IOException {

		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setHeader("X-Auth-User", "aa")
				.invokeRestCall(GET, LIST_TAG_PATTERN_IN_BUSINESS_APPLICATION);
		AssertionMethods.Assertion_401(response);
		SoftAssert softAssert = new SoftAssert();
		JSONObject jsonobj = new JSONObject(response.getAPIResponseAsString());
		softAssert.assertEquals(jsonobj.get("message"), "Invalid username in X-Auth-user");
		softAssert.assertAll();
	}

	/**
	 * Disabled the test case as Content type header is missing {@link} -
	 * https://dev.azure.com/companyName-Tech/Product_Mgmt/_workitems/edit/26566
	 */
	@Story("View Tag Pattern in Business Application")
	@Description("View Tag Pattern in Business Application View a particular tag pattern.")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200  Code", enabled = false)
	public void ViewTagPatterninBusinessApplication() throws JSONException, IOException {
		String tagPatternId = ResourceAPI.getTagPatternId();
		ResourceAPI.ViewTagPatterninBusinessApplicationApi(VIEW_TAG_PATTERN_IN_BUSINESS_APPLICATION, tagPatternId);
	}

	@Story("View Tag Pattern in Business Application")
	@Description("View Tag Pattern in Business Application - Invalid Request")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 400  Code", enabled = false)
	public void ViewTagPatterninBusinessApplication_400() throws JSONException, IOException {
		String tagPatternId = "ss";
		String url = VIEW_TAG_PATTERN_IN_BUSINESS_APPLICATION + tagPatternId;
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(GET, url);
		AssertionMethods.Assertion_400(response);
		SoftAssert softAssert = new SoftAssert();
		JSONObject jsonobj = new JSONObject(response.getAPIResponseAsString());
		softAssert.assertEquals(jsonobj.get("message"), "'" + tagPatternId
				+ "' is not a valid ObjectId, it must be a 12-byte input or a 24-character hex string");
		softAssert.assertAll();
	}

	@Story("View Tag Pattern in Business Application")
	@Description("View Tag Pattern in Business Application - Invalid Url")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 404  Code", enabled = false)
	public void ViewTagPatterninBusinessApplication_404() throws JSONException, IOException {
		String tagPatternId = ResourceAPI.getTagPatternId();
		String url = VIEW_TAG_PATTERN_IN_BUSINESS_APPLICATION + tagPatternId + "/";
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(GET, url);
		AssertionMethods.Assertion_404(response);

	}

	@Story("View Tag Pattern in Business Application")
	@Description("View Tag Pattern in Business Application - Invalid Authorization")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 401  Code", enabled = false)
	public void ViewTagPatterninBusinessApplication_401() throws JSONException, IOException {
		String tagPatternId = ResourceAPI.getTagPatternId();
		String url = VIEW_TAG_PATTERN_IN_BUSINESS_APPLICATION + tagPatternId;
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setHeader("X-Auth-User", "aa")
				.invokeRestCall(GET, url);
		AssertionMethods.Assertion_401(response);
		SoftAssert softAssert = new SoftAssert();
		JSONObject jsonobj = new JSONObject(response.getAPIResponseAsString());
		softAssert.assertEquals(jsonobj.get("message"), "Invalid username in X-Auth-user");
		softAssert.assertAll();
	}

}
