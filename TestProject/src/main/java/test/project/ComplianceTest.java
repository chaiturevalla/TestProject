package test.companyName.api.compliance.tests;


import static test.companyName.api.service.Route.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.SkipException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import test.companyName.api.applicationApi.AccountGovernanceapi;
import test.companyName.api.applicationApi.Complianceapi;
import test.companyName.api.hooks.APIHook;
import test.companyName.api.service.AssertionMethods;
import test.companyName.api.service.RestResponse;
import test.companyName.api.service.Route;
import test.companyName.api.utils.ConfigLoader;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("companyName Compliance Rest API")
@Feature("Compliance API Suite")

public class ComplianceTest extends APIHook {
	static List<Integer> givenList = Arrays.asList(1, 10, 25, 50);
	static Random rand = new Random();
	static int randomIndex = rand.nextInt(givenList.size());
	static Integer RequestedLimit = givenList.get(randomIndex);

	@Story("List Compliance Controls")
	@Description("Get List of Compliance Controls with Valid Data and with Mandatory Filters")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 1)
	public void ListComplianceControlsWithMandatoryFilters() throws JSONException {
		Complianceapi.ComplianceControls("No");
	}

	@Story("List Compliance Controls")
	@Description("Get List of Compliance Controls with all fields - Schema Validation")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 2, enabled = false)
	public void ListComplianceControlsSchemaValidation() throws JSONException {
		if (ConfigLoader.getInstance().getEnvironment().equalsIgnoreCase("qa")) {
			String url = "/compliance/" + ConfigLoader.getInstance().gettenant_id() + "/controls/list";
			Map<String, String> queryparamlist = new HashMap<String, String>();
			queryparamlist.put("limit", Integer.toString(RequestedLimit));
			queryparamlist.put("compliance_uri", Complianceapi.GetRandomComplianceURI());
			RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON)
					.setURLEncodingStatus(false).setMultiQueryParams(queryparamlist)
					.invokeRestCall(GET, url.toString());
			String jsonresp = response.getAPIResponseAsString();
			System.out.println(jsonresp);
			JSONObject json = new JSONObject(jsonresp);
			int Actual_Compliance_control_uri_limit = json.getJSONArray("Control_records").length();
			if (Actual_Compliance_control_uri_limit == 0) {
				response.validateJsonSchema(new File(LIST_COMPLIANCE_CONTROLS_WITH_ZER0_LENGTH_JSON__SCHEMA));
			} else if (Actual_Compliance_control_uri_limit > 0) {
				response.validateJsonSchema(new File(LIST_COMPLIANCE_CONTROLS_JSON__SCHEMA));
			}
		} else {
			throw new SkipException("This API is not running in " + ConfigLoader.getInstance().getEnvironment()
					+ " env. Hence Skipping this Testcase instead of failing");
		}
	}

	@Story("List Compliance Controls")
	@Description("Get List of Compliance Controls with Valid Data Including Optional Filters")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 3)
	public void ListComplianceControlsWithPageFilter() throws JSONException {
			Complianceapi.ComplianceControls("Yes");
	}

	@Story("List Compliance Controls")
	@Description("Get List of Compliance Controls with Invalid Request")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "Should Show 400 Response Code", priority = 4)
	public void ListComplianceControlsWithInvalidRequest() throws JSONException {
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON)
				.invokeRestCall(GET, BAD_REQUEST_COMPLIANCE_URI.toString())
				.hamcrestResponseBodyValidation(EQUAL_TO, MESSAGE, "Invalid Page Number");
		AssertionMethods.Assertion_400(response);
	}

	@Story("List Compliance Controls")
	@Description("Get List of Compliance Controls with Invalid Authorization")
	@Severity(SeverityLevel.MINOR)
	@Test(description = "Should Show 401 Response Code", priority = 5)
	public void ListComplianceControlsWithInvalidAuthorization() throws JSONException {
		String url = "/compliance/" + ConfigLoader.getInstance().gettenant_id() + "/controls/list";
		Map<String, String> queryparamlist = new HashMap<String, String>();
		queryparamlist.put("limit", Integer.toString(RequestedLimit));
		queryparamlist.put("page", "1");
		queryparamlist.put("compliance_uri", Complianceapi.GetRandomComplianceURI());
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setURLEncodingStatus(false)
				.setMultiQueryParams(queryparamlist).setHeader("X-Auth-User", "").invokeRestCall(GET, url.toString())
				.hamcrestResponseBodyValidation(EQUAL_TO, MESSAGE, "Invalid username in X-Auth-user");
		AssertionMethods.Assertion_401(response);
	}

	@Story("List Compliance Controls Mappings")
	@Description("Get List of Compliance Controls Mappings with Valid Data and with Mandatory Filters")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 6)
	public void ListComplianceControlsMappingsWithMandatoryFilters() throws JSONException {
		Complianceapi.ComplianceControlMappings("No");
	}

	@Story("List Compliance Controls Mappings")
	@Description("Get List of Compliance Controls Mappings with Valid Data, Mandatory Filters, Optional Limit Filter")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 7)
	public void ListComplianceControlsMappingsWithLimitFilter() throws JSONException {
		Complianceapi.ComplianceControlMappings("Yes");
	}

	@Story("List Compliance Controls Mappings")
	@Description("Get List of Compliance Controls Mappings - Schema Validation")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 8, enabled = false)
	public void ListComplianceControlsMappingsSchemaValidation() throws JSONException {
		String url = "/compliance/" + ConfigLoader.getInstance().gettenant_id() + "/mapping/list";
		Map<String, String> queryparamlist = new HashMap<String, String>();
		queryparamlist.put("caller", "control");
		queryparamlist.put("limit", Integer.toString(RequestedLimit));
		queryparamlist.put("compliance_uri", Complianceapi.GetRandomComplianceURI());
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setURLEncodingStatus(false)
				.setMultiQueryParams(queryparamlist).invokeRestCall(GET, url.toString());
		String jsonresp = response.getAPIResponseAsString();
		JSONObject json = new JSONObject(jsonresp);
		int total_count = json.getInt("Total_count");
		if (total_count == 0) {
			response.validateJsonSchema(new File(LIST_COMPLIANCE_CONTROLS_WITH_ZER0_LENGTH_JSON__SCHEMA));
		} else if (total_count > 0) {
			response.validateJsonSchema(new File(LIST_COMPLIANCE_CONTROL_MAPPINGS_JSON_SCHEMA));
		}

	}

	@Story("List Compliance Controls Mappings")
	@Description("Get List of Compliance Controls Mappings with Invalid Request")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "Should Show 400 Response Code", priority = 9)
	public void ListComplianceControlsMappingsWithInvalidRequest() throws JSONException {
		String url = "/compliance/" + ConfigLoader.getInstance().gettenant_id() + "/mapping/list";
		Map<String, String> queryparamlist = new HashMap<String, String>();
		queryparamlist.put("caller", "control");
		queryparamlist.put("limit", Integer.toString(RequestedLimit));
		queryparamlist.put("page", "200");
		queryparamlist.put("compliance_uri", Complianceapi.GetRandomComplianceURI());
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setURLEncodingStatus(false)
				.setMultiQueryParams(queryparamlist).invokeRestCall(GET, url.toString())
				.hamcrestResponseBodyValidation(EQUAL_TO, MESSAGE, "Invalid Page Number");
		AssertionMethods.Assertion_400(response);
	}

	@Story("List Compliance Controls Mappings")
	@Description("Get List of Compliance Controls Mappings with Invalid Authorization")
	@Severity(SeverityLevel.MINOR)
	@Test(description = "Should Show 401 Resonse Code", priority = 10)
	public void ListComplianceControlsMappingsWithInvalidAuthorization() throws JSONException {
		String url = "/compliance/" + ConfigLoader.getInstance().gettenant_id() + "/mapping/list";
		Map<String, String> queryparamlist = new HashMap<String, String>();
		queryparamlist.put("caller", "control");
		queryparamlist.put("compliance_uri", Complianceapi.GetRandomComplianceURI());
		queryparamlist.put("limit", Integer.toString(RequestedLimit));
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setHeader("X-Auth-User", "").setURLEncodingStatus(false)
				.setMultiQueryParams(queryparamlist).invokeRestCall(GET, url.toString())
				.hamcrestResponseBodyValidation(EQUAL_TO, MESSAGE, "Invalid username in X-Auth-user");
		AssertionMethods.Assertion_401(response);
	}

	@Story("List Compliance Standards")
	@Description("Get List of Compliance Standards with Type as Marketplace")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 11)
	public void GetListComplianceStandardsWithTypeasMarketPlace() throws JSONException {
		Complianceapi.ComplianceStandardsWithSingleParam("marketplace");
	}

	@Story("List Compliance Standards")
	@Description("Get List of Compliance Standards with Type as Marketplace - Schema Valdiation")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 12, enabled = false)
	public void GetListComplianceStandardsWithTypeasMarketPlaceSchemaValdiation() throws JSONException {
		String url = FORWARD_SLASH + COMPLIANCE + FORWARD_SLASH + TENANT_ID_VALUE + FORWARD_SLASH + STANDARDS
				+ QUESTION_MARK + TYPE + IS_EQUAL_TO_SYMBOL + MARKETPLACE;
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setURLEncodingStatus(false)
				.invokeRestCall(GET, url.toString()).hamcrestResponseBodyValidation(EQUAL_TO, STATUS, SUCCESS)
				.hamcrestResponseBodyValidation(EQUAL_TO, MESSAGE, COMPLIANCE_CONTROL_DEFINITIONS_LIST);
		response.validateJsonSchema(new File(LIST_COMPLIANCE_STANDARDS_MARKETPLACE_JSON_SCHEMA));
	}

	@Story("List Compliance Standards")
	@Description("Get List of Compliance Standards with Type as My_Standard")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 13)
	public void GetListComplianceStandardsWithTypeasMystandard() throws JSONException {
		Complianceapi.ComplianceStandardsWithSingleParam("my_standard");
	}

	@Story("List Compliance Standards")
	@Description("Get List of Compliance Standards with Type as Marketplace and Service Type as AWS")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 14)
	public void GetListComplianceStandardsWithTypeasMarketPlaceAndServiceTypeAsAWS() throws JSONException {
		Complianceapi.ComplianceStandardsWithMultiParams("marketplace","AWS");
	}

	@Story("List Compliance Standards")
	@Description("Get List of Compliance Standards with Type as Marketplace and Service Type as AZURE")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 15)
	public void GetListComplianceStandardsWithTypeasMarketPlaceAndServiceTypeAsAzure() throws JSONException {
		Complianceapi.ComplianceStandardsWithMultiParams("marketplace","Azure");
	}

	@Story("List Compliance Standards")
	@Description("Get List of Compliance Standards with Type as Marketplace and Service Type as Azure_CSP-Direct")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 16)
	public void GetListComplianceStandardsWithTypeasMarketPlaceAndServiceTypeAsAzureCSPDirect() throws JSONException {
		Complianceapi.ComplianceStandardsWithMultiParams("marketplace","Azure_CSP-Direct");
	}

	@Story("List Compliance Standards")
	@Description("Get List of Compliance Standards with Type as Marketplace and Service Type as AZURE_EA")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 17)
	public void GetListComplianceStandardsWithTypeasMarketPlaceAndServiceTypeAsAzureEA() throws JSONException {
		Complianceapi.ComplianceStandardsWithMultiParams("marketplace","Azure_EA");
	}

	@Story("List Compliance Standards")
	@Description("Get List of Compliance Standards with Type as Marketplace and Service Type as GCP")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 18)
	public void GetListComplianceStandardsWithTypeasMarketPlaceAndServiceTypeAsGCP() throws JSONException {
		Complianceapi.ComplianceStandardsWithMultiParams("marketplace","GCP");
	}

	@Story("List Compliance Standards")
	@Description("Get List of Compliance Standards with Invalid Authorization")
	@Severity(SeverityLevel.MINOR)
	@Test(description = "Should Show 401 Response Code", priority = 19)
	public void GetListComplianceStandardsWithInvalidAuthorization() throws JSONException {
		String url =  "/compliance/"+ ConfigLoader.getInstance().gettenant_id() +"/standards";
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setHeader("X-Auth-User", "").setURLEncodingStatus(false)
				.setQueryParam("type", "marketplace").invokeRestCall(GET, url.toString())
				.hamcrestResponseBodyValidation(EQUAL_TO, MESSAGE, "Invalid username in X-Auth-user");
		AssertionMethods.Assertion_401(response);
	}
	
	@Story("List Compliance Posture Summary By Policies")
	@Description("Get List Compliance Posture Summary By Policies by using account as search query")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 20)
	public void PostCompliancePostureSummaryByPoliciesByAccount() throws JSONException, IOException {
		Complianceapi.ListCompliancePostureSumamryBypolicies("account");
	}

	@Story("List Compliance Posture Summary By Policies")
	@Description("Get List Compliance Posture Summary By Policies by using service as search query")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 21)
	public void PostCompliancePostureSummaryByPoliciesByService() throws JSONException, IOException {
		Complianceapi.ListCompliancePostureSumamryBypolicies("service");
	}

	@Story("List Compliance Posture Summary By Policies")
	@Description("Get List Compliance Posture Summary By Policies by using standard as search query")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 22)
	public void PostCompliancePostureSummaryByPoliciesByStandard() throws JSONException, IOException {
		Complianceapi.ListCompliancePostureSumamryBypolicies("standard");
	}

	@Story("List Compliance Posture Summary By Policies")
	@Description("Get List Compliance Posture Summary By Policies With Valid Request - Schema Validation")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 23, enabled = false)
	public void PostCompliancePostureSummaryByPoliciesScehmaValdiation() throws JSONException, IOException {
		if (ConfigLoader.getInstance().getEnvironment().equalsIgnoreCase("qa")) {
			String complainceStanardObjectPayload = new String(
					Files.readAllBytes(Paths.get(CREATE_SUMMARY_POSTURE_BY_POLICIES_PATH)));
			JSONObject complainceStandardObject = new JSONObject(complainceStanardObjectPayload);
			JSONArray arr = new JSONArray();
			arr.put(ConfigLoader.getInstance().gettenant_id());
			complainceStandardObject.put("tenant_id", arr);
			RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(POST,
					COMPLIANCE_POSTURE_SUMMARY_BY_POLICIES, complainceStandardObject.toString());
			response.validateJsonSchema(new File(LIST_COMPLIANCE_SUMMARY_POLICIES_JSON_SCHEMA));
		} else {
			throw new SkipException("This API is not running in " + ConfigLoader.getInstance().getEnvironment()
					+ " env. Hence Skipping this Testcase instead of failing");
		}
	}

	@Story("List Compliance Posture Summary By Policies")
	@Description("Get List Compliance Posture Summary By Policies With INVALID Request")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "Should Show 404 Response Code", priority = 24)
	public void PostCompliancePostureSummaryByPoliciesWithBadRequest() throws JSONException, IOException {
		String complainceStanardObjectPayload = new String(
				Files.readAllBytes(Paths.get(CREATE_SUMMARY_POSTURE_BY_POLICIES_PATH)));
		JSONObject complainceStandardObject = new JSONObject(complainceStanardObjectPayload);
		JSONArray arr = new JSONArray();
		arr.put(INVALID_TENANT_ID);
		complainceStandardObject.put("tenant_id", arr);
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(POST,
				COMPLIANCE_POSTURE_SUMMARY_BY_POLICIES_INVALIDREQUEST, complainceStandardObject.toString());
		AssertionMethods.Assertion_404(response);
	}

	@Story("List Compliance Posture Summary By Policies")
	@Description("Get List Compliance Posture Summary By Policies With INVALID Token")
	@Severity(SeverityLevel.MINOR)
	@Test(description = "Should Show 401 Response Code", priority = 25)
	public void PostCompliancePostureSummaryByPoliciesWithInvalidToken() throws JSONException, IOException {
		String complainceStanardObjectPayload = new String(
				Files.readAllBytes(Paths.get(CREATE_SUMMARY_POSTURE_BY_POLICIES_PATH)));
		JSONObject complainceStandardObject = new JSONObject(complainceStanardObjectPayload);
		JSONArray arr = new JSONArray();
		arr.put(INVALID_TENANT_ID);
		complainceStandardObject.put("tenant_id", arr);
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON)
				.setHeader("X-Auth-User", "raviteja.challa")
				.invokeRestCall(POST, COMPLIANCE_POSTURE_SUMMARY_BY_POLICIES, complainceStandardObject.toString());
		AssertionMethods.Assertion_401(response);
	}

	@Story("List Compliance Visibility")
	@Description("Get List of Compliance Visibility with Valid Request -objective is to return all of the compliance visibility given, the tenant as input")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 26)
	public void GetListComplianceVisibility() throws JSONException {
		String url = FORWARD_SLASH + COMPLIANCE + FORWARD_SLASH + TENANT_ID_VALUE + FORWARD_SLASH + VISIBILITY;
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setURLEncodingStatus(false)
				.invokeRestCall(GET, url.toString()).hamcrestResponseBodyValidation(EQUAL_TO, STATUS, SUCCESS);
		AssertionMethods.Assertion_200(response);
	}

	@Story("List Compliance Visibility")
	@Description("Get List of Compliance Visibility with Valid Request - Schema Valdiation")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 27)
	public void GetListComplianceVisibilitySchemaValdiation() throws JSONException {
		String url = FORWARD_SLASH + COMPLIANCE + FORWARD_SLASH + TENANT_ID_VALUE + FORWARD_SLASH + VISIBILITY;
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setURLEncodingStatus(false)
				.invokeRestCall(GET, url.toString()).hamcrestResponseBodyValidation(EQUAL_TO, STATUS, SUCCESS);
		response.validateJsonSchema(new File(LIST_COMPLIANCE_VISIBILITY_JSON_SCHEMA));
	}

	@Story("List Compliance Visibility")
	@Description("Get List of Compliance Visibility with Invalid Request")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "Should Show 400 Response Code", priority = 28, enabled = false)
	public void GetListComplianceVisibilityWithInvalidRequest() throws JSONException {
		String url = FORWARD_SLASH + COMPLIANCE + FORWARD_SLASH + TENANT_ID_VALUE + FORWARD_SLASH + VISIBILITY
				+ "Invalid";
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setURLEncodingStatus(false)
				.invokeRestCall(GET, url.toString()).hamcrestResponseBodyValidation(EQUAL_TO, STATUS, SUCCESS);
		AssertionMethods.Assertion_400(response);
	}

	@Story("List Compliance Visibility")
	@Description("Get List of Compliance Visibility with Not Found Request")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "Should Show 404 Response Code", priority = 29)
	public void GetListComplianceVisibilityWithNotFoundRequest() throws JSONException {
		String url = FORWARD_SLASH + COMPLIANCE + FORWARD_SLASH + TENANT_ID_VALUE + FORWARD_SLASH + VISIBILITY
				+ "Invalid";
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setURLEncodingStatus(false)
				.invokeRestCall(GET, url.toString());
		AssertionMethods.Assertion_404(response);
	}

	@Story("List Compliance Visibility")
	@Description("Get List of Compliance Visibility with Invalid Authorization Request")
	@Severity(SeverityLevel.MINOR)
	@Test(description = "Should Show 401 Response Code", priority = 30)
	public void GetListComplianceVisibilityWithInvalidAuthorization() throws JSONException {
		String url = FORWARD_SLASH + COMPLIANCE + FORWARD_SLASH + TENANT_ID_VALUE + FORWARD_SLASH + VISIBILITY;
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setHeader("X-Auth-User"," ").setURLEncodingStatus(false)
				.invokeRestCall(GET, url.toString())
				.hamcrestResponseBodyValidation(EQUAL_TO, MESSAGE, "Invalid username in X-Auth-user");
		AssertionMethods.Assertion_401(response);
	}

	@Story("List Compliance posture DetailsForPolicies")
	@Description("Get List of Compliance posture DetailsForPolicies with Valid Request -objective is to return all of the compliance posture DetailsForPolicies")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 31)
	public void GetListCompliancePostureDetailsForPolicies() throws JSONException, IOException {
			String url = "/compliance_posture/detail_for_policy/" + Complianceapi.CompliancePosture_GetRandomPolicyId();
			String compliancePostureDetailsForPoliciesPayload = new String(
					Files.readAllBytes(Paths.get(Route.COMPLIANCEPOSTURE_DETAILSFORPOLICIESQA_PATH)));
			JSONObject complainceStandardObject = new JSONObject(compliancePostureDetailsForPoliciesPayload);
			JSONArray arr = new JSONArray();
			arr.put(AccountGovernanceapi.GetRandomCloudAccountID());
			complainceStandardObject.put("cloud_accounts", arr);
			RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON)
									.invokeRestCall(POST, url, complainceStandardObject.toString())
									.hamcrestResponseBodyValidation(EQUAL_TO, STATUS, SUCCESS)
									.hamcrestResponseBodyValidation(EQUAL_TO, MESSAGE, COMPLIANCEPOSTURE_DETAILSFORPOLICIES_EXPECTED);
			AssertionMethods.Assertion_200(response);
	}

	@Story("List Compliance posture DetailsForPolicies")
	@Description("Get List of Compliance posture DetailsForPolicies with Valid Request - Schema Validation")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 32)
	public void GetListCompliancePostureDetailsForPoliciesSchemaValidation() throws JSONException, IOException {
			String url = "/compliance_posture/detail_for_policy/" + Complianceapi.CompliancePosture_GetRandomPolicyId();
			String compliancePostureDetailsForPoliciesPayload = new String(
					Files.readAllBytes(Paths.get(Route.COMPLIANCEPOSTURE_DETAILSFORPOLICIESQA_PATH)));
			JSONObject complainceStandardObject = new JSONObject(compliancePostureDetailsForPoliciesPayload);
			JSONArray arr = new JSONArray();
			arr.put(AccountGovernanceapi.GetRandomCloudAccountID());
			complainceStandardObject.put("cloud_accounts", arr);
			RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON)
									.invokeRestCall(POST, url, complainceStandardObject.toString())
									.hamcrestResponseBodyValidation(EQUAL_TO, STATUS, SUCCESS)
									.hamcrestResponseBodyValidation(EQUAL_TO, MESSAGE, COMPLIANCEPOSTURE_DETAILSFORPOLICIES_EXPECTED);
			response.validateJsonSchema(new File(LIST_COMPLIANCE_POSTURE_DETAILS_FOR_POLICIES_JSON_SCHEMA));
		} 

	@Story("List Compliance posture DetailsForPolicies")
	@Description("Get List of Compliance posture DetailsForPolicies with Not Found Request")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "Should Show 404 Response Code", priority = 33)
	public void GetListCompliancePostureDetailsForPoliciesyWithNotFoundRequest() throws JSONException, IOException {
		String url = "/compliance_posture/detail/" + Complianceapi.CompliancePosture_GetRandomPolicyId();
		String compliancePostureDetailsForPoliciesPayload = new String(
				Files.readAllBytes(Paths.get(Route.COMPLIANCEPOSTURE_DETAILSFORPOLICIESQA_PATH)));
		JSONObject complainceStandardObject = new JSONObject(compliancePostureDetailsForPoliciesPayload);
		JSONArray arr = new JSONArray();
		arr.put(AccountGovernanceapi.GetRandomCloudAccountID());
		complainceStandardObject.put("cloud_accounts", arr);
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(POST,url ,complainceStandardObject.toString());
		AssertionMethods.Assertion_404(response);
	}

	@Story("List Compliance posture DetailsForPolicies")
	@Description("Get List of Compliance posture DetailsForPolicies with bad Request")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "Should Show 400 Response Code", priority = 34)
	public void GetListCompliancePostureDetailsForPoliciesyWithBadRequest() throws JSONException, IOException {
		String url = "/compliance_posture/detail_for_policy/" + Complianceapi.CompliancePosture_GetRandomPolicyId();
		String compliancePostureDetailsForPoliciesPayload = new String(
				Files.readAllBytes(Paths.get(Route.COMPLIANCEPOSTURE_DETAILSFORPOLICIESQA_PATH)));
		JSONObject complainceStandardObject = new JSONObject(compliancePostureDetailsForPoliciesPayload);
		JSONArray arr = new JSONArray();
		arr.put(AccountGovernanceapi.GetRandomCloudAccountID());
		complainceStandardObject.put("cloud_accounts", arr);
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON)
								.invokeRestCall(POST,url+ "Invalid", complainceStandardObject.toString());
		AssertionMethods.Assertion_400(response);
	}

	@Story("List Compliance posture DetailsForPolicies")
	@Description("Get List of Compliance posture DetailsForPolicies with Invalid Authorization Request")
	@Severity(SeverityLevel.MINOR)
	@Test(description = "Should Show 401 Response Code", priority = 35)
	public void GetListCompliancePostureDetailsForPoliciesWithInvalidAuthorization() throws JSONException, IOException {
		String url = "/compliance_posture/detail_for_policy/" + Complianceapi.CompliancePosture_GetRandomPolicyId();
		String compliancePostureDetailsForPoliciesPayload = new String(
				Files.readAllBytes(Paths.get(Route.COMPLIANCEPOSTURE_DETAILSFORPOLICIESQA_PATH)));
		JSONObject complainceStandardObject = new JSONObject(compliancePostureDetailsForPoliciesPayload);
		JSONArray arr = new JSONArray();
		arr.put(AccountGovernanceapi.GetRandomCloudAccountID());
		complainceStandardObject.put("cloud_accounts", arr);
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON)
								.setHeader("X-Auth-User"," ").invokeRestCall(POST,url, complainceStandardObject.toString())
								.hamcrestResponseBodyValidation("equalTo", "message", "Invalid username in X-Auth-user");
		AssertionMethods.Assertion_401(response);
	}

	@Story("List Compliance Filter")
	@Description("Get List Compliance Filter with Valid Request -objective is to return all of the compliance Filter given, the tenant as input")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Code", priority = 36)
	public void GetListComplianceFilter() throws JSONException {
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setURLEncodingStatus(false)
								.invokeRestCall(GET, LIST_COMPLIANCE_FILTER_URL.toString())
								.hamcrestResponseBodyValidation(EQUAL_TO, STATUS, SUCCESS)
								.hamcrestResponseBodyValidation(EQUAL_TO, MESSAGE, COMPLIANCEFILTER_EXPECTED);
		AssertionMethods.Assertion_200(response);
	}

	@Story("List Compliance Filter")
	@Description("Get List Compliance Filter with Valid Request - Schema Validation")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Code", priority = 37)
	public void GetListComplianceFilterSchemaValidation() throws JSONException {
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setURLEncodingStatus(false)
								.invokeRestCall(GET, LIST_COMPLIANCE_FILTER_URL.toString())
								.hamcrestResponseBodyValidation(EQUAL_TO, STATUS, SUCCESS)
								.hamcrestResponseBodyValidation(EQUAL_TO, MESSAGE, COMPLIANCEFILTER_EXPECTED);
		response.validateJsonSchema(new File(LIST_COMPLIANCE_FILTER_JSON_SCHEMA));
		AssertionMethods.Assertion_200(response);
	}

	@Story("List Compliance Filter")
	@Description("Get List Compliance Filter with Not Found Request")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "Should Show 404 Response Code", priority = 38)
	public void GetListComplianceFilterWithNotFoundRequest() throws JSONException {
		String url = FORWARD_SLASH + COMPLIANCE_POSTURE + FORWARD_SLASH + FILTERS + "Invalid";
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setURLEncodingStatus(false)
								.invokeRestCall(GET, url);
		AssertionMethods.Assertion_404(response);
	}

	
	@Story("List Compliance Filter")
	@Description("Get List Compliance Filter with Invalid Authorization Request")
	@Severity(SeverityLevel.MINOR)
	@Test(description = "Should Show 401 Error Code", priority = 39)
	public void GetListComplianceFilterWithInvalidAuthorization() throws JSONException {
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON)
								.setHeader("X-Auth-User"," ").setURLEncodingStatus(false)
								.invokeRestCall(GET, LIST_COMPLIANCE_FILTER_URL)
								.hamcrestResponseBodyValidation("equalTo", "message", "Invalid username in X-Auth-user");;
		AssertionMethods.Assertion_401(response);
	}

	// List Compliance Filter For Heatstack

	@Story("List Compliance Filter For Heatstack")
	@Description("Get List Compliance Filter For Heatstack with Valid Request -objective is to return all of the compliance Filter given, the tenant as input")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Code", priority = 40)
	public void GetListComplianceFilterForHeatstack() throws JSONException {
		Complianceapi.CompliancePosture_GetRandomPolicyId();
	}

	@Story("List Compliance Filter For Heatstack")
	@Description("Get List Compliance Filter For Heatstack with Valid Request - Schema Validation")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Code", priority = 41)
	public void GetListComplianceFilterForHeatstackSchemaValidation() throws JSONException {
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setURLEncodingStatus(false)
				.invokeRestCall(GET, LIST_COMPLIANCE_FILTER_FOR_HEATSTACK_URL.toString())
				.hamcrestResponseBodyValidation(EQUAL_TO, STATUS, SUCCESS)
				.hamcrestResponseBodyValidation(EQUAL_TO, MESSAGE, COMPLIANCEFILTERFORHEATSTACK_EXPECTED);
		response.getAPIResponseAsPrettyPrint();
		response.validateJsonSchema(new File(LIST_COMPLIANCE_FILTER_FOR_HEATSTACK_JSON_SCHEMA));
		AssertionMethods.Assertion_200(response);
	}

	@Story("List Compliance Filter For Heatstack")
	@Description("Get List Compliance Filter For Heatstack with Notfound Request")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "Should Show 404 Code", priority = 42)
	public void GetListComplianceFilterForHeatstackwithNotfound() throws JSONException {
		String url = FORWARD_SLASH + COMPLIANCE_POSTURE + FORWARD_SLASH + FILTERS + FORWARD_SLASH + BY_POLICY+ "invalid";
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setURLEncodingStatus(false)
				.invokeRestCall(GET, url.toString());
		AssertionMethods.Assertion_404(response);
	}

	@Story("List Compliance Filter For Heatstack")
	@Description("Get List Compliance Filter For Heatstack with Invalid Authorization Request")
	@Severity(SeverityLevel.MINOR)
	@Test(description = "Should Show 401 Code", priority = 43)
	public void GetListComplianceFilterForHeatstackWithInvalidAuthorization() throws JSONException {
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setHeader("X-Auth-User"," ")
								.setURLEncodingStatus(false)
								.invokeRestCall(GET, LIST_COMPLIANCE_FILTER_FOR_HEATSTACK_URL)
								.hamcrestResponseBodyValidation("equalTo", "message", "Invalid username in X-Auth-user");
		AssertionMethods.Assertion_401(response);
	}

	// compliance visibility trend
	@Story("Compliance Visibility Trend")
	@Description("Get List of Compliance Visibility Trend with Valid Request -objective is to return all of the compliance visibility given, the tenant as input")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 44)
	public void GetComplianceVisibilityTrend() throws JSONException {
		String url = FORWARD_SLASH + TENANT_ID_VALUE + FORWARD_SLASH + COMPLIANCE_POSTURE + FORWARD_SLASH
				+ SERVICE_ACCOUNT_ID + FORWARD_SLASH + COMPLIANCE_VISIBILITY_TREND;
		System.out.println(url);
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setURLEncodingStatus(false)
				.invokeRestCall(GET, url.toString()).hamcrestResponseBodyValidation(EQUAL_TO, STATUS, SUCCESS)
				.hamcrestResponseBodyValidation(EQUAL_TO, MESSAGE, COMPLIANCE_VISIBILITY_TREND_EXPECTED);
		AssertionMethods.Assertion_200(response);
	}

	@Story("Compliance Visibility Trend")
	@Description("Get List of Compliance Visibility Trend with Valid Request -Schema Validation")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 45)
	public void GetComplianceVisibilityTrendSchemavalidation() throws JSONException {
		String url = FORWARD_SLASH + TENANT_ID_VALUE + FORWARD_SLASH + COMPLIANCE_POSTURE + FORWARD_SLASH
				+ SERVICE_ACCOUNT_ID + FORWARD_SLASH + COMPLIANCE_VISIBILITY_TREND;
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setURLEncodingStatus(false)
				.invokeRestCall(GET, url.toString()).hamcrestResponseBodyValidation(EQUAL_TO, STATUS, SUCCESS)
				.hamcrestResponseBodyValidation(EQUAL_TO, MESSAGE, COMPLIANCE_VISIBILITY_TREND_EXPECTED);
		response.validateJsonSchema(new File(COMPLIANCE_VISIBILITY_TREND_JSON_SCHEMA));
		AssertionMethods.Assertion_200(response);
	}

	@Story("Compliance Visibility Trend")
	@Description("Get List of Compliance Visibility Trend with Not Found Request")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "Should Show 404 Response Code", priority = 46)
	public void GetComplianceVisibilityTrendWithNotFoundRequest() throws JSONException {
		String url = FORWARD_SLASH + FORWARD_SLASH + COMPLIANCE_POSTURE + FORWARD_SLASH
				+ SERVICE_ACCOUNT_ID + FORWARD_SLASH + COMPLIANCE_VISIBILITY_TREND;
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setURLEncodingStatus(false)
				.invokeRestCall(GET, url.toString());
		AssertionMethods.Assertion_404(response);
	}

	@Story("Compliance Visibility Trend")
	@Description("Get List of Compliance Visibility Trend with Invalid Request")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "Should Show 400 Response Code", priority = 47)
	public void GetComplianceVisibilityTrendWithInvalidRequest() throws JSONException {
		String url = FORWARD_SLASH + TENANT_ID_VALUE + FORWARD_SLASH + COMPLIANCE_POSTURE + FORWARD_SLASH
				+ SERVICE_ACCOUNT_ID + FORWARD_SLASH + "visibility_trend?vv=XYZ";
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setURLEncodingStatus(false)
				.invokeRestCall(GET, url.toString());
		AssertionMethods.Assertion_400(response);
	}

	@Story("Compliance Visibility Trend")
	@Description("Get List of Compliance Visibility Trend with Invalid Authorization Request")
	@Severity(SeverityLevel.MINOR)
	@Test(description = "Should Show 401 Response Code", priority = 48)
	public void GetComplianceVisibilityTrendWithInvalidAuthorization() throws JSONException {
		String url = FORWARD_SLASH + TENANT_ID_VALUE + FORWARD_SLASH + COMPLIANCE_POSTURE + FORWARD_SLASH
				+ SERVICE_ACCOUNT_ID + FORWARD_SLASH + COMPLIANCE_VISIBILITY_TREND;
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setHeader("X-Auth-User", "")
								.setURLEncodingStatus(false)
								.invokeRestCall(GET, url.toString())
								.hamcrestResponseBodyValidation("equalTo", "message", "Invalid username in X-Auth-user");
		AssertionMethods.Assertion_401(response);
	}

	@Story("Compliance Posture Visibility")
	@Description("Get List of Compliance Posture Visibility")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 49)
	public void GetListCompliancePostureVisibility() throws JSONException, IOException {
		Complianceapi.CompliancePostureVisibility();
	}

	@Story("Compliance Posture Visibility")
	@Description("Get List of Compliance Posture Visibility -Schema Validation")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 50)
	public void GetListCompliancePostureVisibilitySchemavalidation() throws JSONException, IOException {
		String url = "/"+ConfigLoader.getInstance().gettenant_id() +"/compliance_posture/"+ Complianceapi.GetRandomServiceAccountId()+"/visibility";
		Map<String, String> queryparamlist4 = new HashMap<String, String>();
		queryparamlist4.put("compliance_uri", Complianceapi.GetRandomComplianceURI());
		queryparamlist4.put("group_by", "control_family");
		queryparamlist4.put("count_by", "control");
			RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setMultiQueryParams(queryparamlist4)
					.setURLEncodingStatus(false).invokeRestCall(GET, url.toString());
			response.validateJsonSchema(new File(COMPLIANCE_POSTURE_VISIBILITY_JSON_SCHEMA));
			AssertionMethods.Assertion_200(response);
		} 

	@Story("Compliance Posture Visibility")
	@Description("Get List of Compliance Posture Visibility With Bad Request")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "Should Show 400 Response Code", priority = 51,enabled=false)
	public void GetListCompliancePostureVisibilityWithBadRequest() throws JSONException {
		String url = "/"+ConfigLoader.getInstance().gettenant_id() +"/compliance_posture/"+ Complianceapi.GetRandomServiceAccountId()+"/visibility";
		Map<String, String> queryparamlist4 = new HashMap<String, String>();
		queryparamlist4.put("compliance_uri", Complianceapi.GetRandomComplianceURI()+"2309");
		queryparamlist4.put("group_by", "control_family");
		queryparamlist4.put("count_by", "control");
			RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setMultiQueryParams(queryparamlist4)
					.setURLEncodingStatus(false).invokeRestCall(GET, url.toString());
		AssertionMethods.Assertion_400(response);
	}

	@Story("Compliance Posture Visibility")
	@Description("Get List of Compliance Posture Visibility with Invalid Authorization")
	@Severity(SeverityLevel.MINOR)
	@Test(description = "Should Show 401 Response Code", priority = 52)
	public void GetListCompliancePostureVisibilityWithInvalidAuthorization() throws JSONException {
		String url = "/"+ConfigLoader.getInstance().gettenant_id() +"/compliance_posture/"+ Complianceapi.GetRandomServiceAccountId()+"/visibility";
		Map<String, String> queryparamlist4 = new HashMap<String, String>();
		queryparamlist4.put("compliance_uri", Complianceapi.GetRandomComplianceURI());
		queryparamlist4.put("group_by", "control_family");
		queryparamlist4.put("count_by", "control");
			RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON) .setHeader("X-Auth-User", "")
									.setMultiQueryParams(queryparamlist4)
									.setURLEncodingStatus(false).invokeRestCall(GET, url.toString())
									.hamcrestResponseBodyValidation("equalTo", "message", "Invalid username in X-Auth-user");
			AssertionMethods.Assertion_401(response);
		} 
	
	@Story("Get compliance assessment summary by assessment job number")
	@Description("Get compliance assessment summary by assessment job number - Valid Request")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 53)
	public void GetComplianceAssessmentHistory_200() throws JSONException {
		String url = "/compliance/compliance_control_mapping/assessment_summary_by_job";
		String assessmentJobNumber = Complianceapi.GetRandomComplianceAssessmentJobNumber();
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setURLEncodingStatus(false)
				.setQueryParam("assessment_job_number", assessmentJobNumber)
				.invokeRestCall(GET, url.toString());
		AssertionMethods.Assertion_200(response);
		SoftAssert softassert = new SoftAssert();
		JSONObject jsonObj = new JSONObject(response.getAPIResponseAsString());
		softassert.assertEquals(jsonObj.getString("status"), "success");
		softassert.assertTrue(jsonObj.has("assessment_job_status"));
		softassert.assertAll();
	}

	@Story("Get compliance assessment summary by assessment job number")
	@Description("Get compliance assessment summary by assessment job number - Invalid Url")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "Should Show 404 Response Code", priority = 54)
	public void GetComplianceAssessmentHistory_404() throws JSONException {
		String url = "/compliance/compliance_control_mapping/assessment_summary_by_jobs";
		String assessmentJobNumber = Complianceapi.GetRandomComplianceAssessmentJobNumber();
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setURLEncodingStatus(false)
				.setQueryParam("assessment_job_number", assessmentJobNumber)
				.invokeRestCall(GET, url.toString());
		AssertionMethods.Assertion_404(response);
		String res = response.getAPIResponseAsString();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(res.contains(Error_Msg_404));
		softAssert.assertAll();
	}

	@Story("Get compliance assessment summary by assessment job number")
	@Description("Get compliance assessment summary by assessment job number - Invalid Request")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "Should Show 400 Response Code", priority = 55)
	public void GetComplianceAssessmentHistory_400() throws JSONException {
		String url = "/compliance/compliance_control_mapping/assessment_summary_by_job";
		String assessmentJobNumber = Complianceapi.GetRandomComplianceAssessmentJobNumber();
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setURLEncodingStatus(false)
				.setQueryParam("assessment_job_number", assessmentJobNumber+"fdsdfsd")
				.invokeRestCall(GET, url.toString());
		AssertionMethods.Assertion_400(response);
		SoftAssert softassert = new SoftAssert();
		JSONObject jsonObj = new JSONObject(response.getAPIResponseAsString());
		softassert.assertEquals(jsonObj.getString("message"), "Assessment job number is not valid.");
		softassert.assertAll();
	}

	@Story("Get compliance assessment summary by assessment job number")
	@Description("Get compliance assessment summary by assessment job number - Invalid Authorization")
	@Severity(SeverityLevel.MINOR)
	@Test(description = "Should Show 401 Response Code", priority = 56)
	public void GetComplianceAssessmentHistory_401() throws JSONException {
		String url = "/compliance/compliance_control_mapping/assessment_summary_by_job";
		String assessmentJobNumber = Complianceapi.GetRandomComplianceAssessmentJobNumber();
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON)
				.setHeader("X-Auth-User", "fdfd")
				.setURLEncodingStatus(false)
				.setQueryParam("assessment_job_number", assessmentJobNumber)
				.invokeRestCall(GET, url.toString());
		AssertionMethods.Assertion_401(response);
		SoftAssert softAssert = new SoftAssert();
		JSONObject jsonobj = new JSONObject(response.getAPIResponseAsString());
		softAssert.assertEquals(jsonobj.get("message"), "Invalid username in X-Auth-user");
		softAssert.assertAll();

	}

}
