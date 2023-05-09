package test.companyName.api.inventory.tests;

import static test.companyName.api.service.Constant.STATUSCODE_200;
import static test.companyName.api.service.Constant.STATUSLINE200;
import static test.companyName.api.service.Route.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import test.companyName.api.applicationApi.Inventoryapi;
import test.companyName.api.hooks.APIHook;
import test.companyName.api.service.RestResponse;
import test.companyName.api.utils.ConfigLoader;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class Inventory_Azure_Service extends APIHook {

//	Verify the Sync for the newly added tag has happened without clicking on Rediscover for azure accounts
	@Story("Inventory_Azure")
	@Description("Verify the Sync for the newly added tag has happened without clicking on Rediscover for azure accounts")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 1)
	public void SyncNewlyAddedtagWithoutClickingOnRediscoverForExistingAzureAccount() throws JSONException, IOException {
		  String CloudDeatils[] = Inventoryapi.GetResourceList("Azure","CS_SaaS_QA_INFRA"); 
		  String CloudAccntName = CloudDeatils[0]; 
		  String CloudAccntId = CloudDeatils[1]; 
		  String InventoryId = CloudDeatils[2]; 
		  String AddTagPayloadPayload = new String(Files.readAllBytes(Paths.get(PATH_ADD_TAG_PAYLOAD))); 
		  JSONObject AddTagPayloadObject = new JSONObject(AddTagPayloadPayload);
		  AddTagPayloadObject.put("service_account_name",CloudAccntName);
		  AddTagPayloadObject.put("service_account_id",CloudAccntId);
		  AddTagPayloadObject.put("inventory_id",InventoryId);
		  System.out.println(AddTagPayloadObject.toString());
		  String AddTagurl = ConfigLoader.getInstance().getInternalBaseURL() + ":18087/v2/" + TENANT_ID_VALUE +"/cloud/Azure/resources/tag_enforce";
		  System.out.println(AddTagurl);
		  RestResponse AddTagresponse = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON)
				  						.invokeRestCall(POST, AddTagurl, AddTagPayloadObject.toString())
				  						.hamcrestResponseBodyValidation("equalTo", "status", "success")
				  						.hamcrestResponseBodyValidation("equalTo", "message","Manage Tags request is submitted. It may take few minutes to configure. ");
		AddTagresponse.getAPIResponseAsPrettyPrint();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(AddTagresponse.getStatusCode(), STATUSCODE_200);
		STATUSLINE200.contains(AddTagresponse.getStatusLine());
		softAssert.assertAll();
	}

//	Verify Network inventory is syncing with the portal details for 'Public IP Address' Resource
	@Story("Inventory_Azure")
	@Description("Verify Network inventory is syncing with the portal details for 'Public IP Address' Resource")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 2)
	public void NetworkInventorySyncWithPortalDetailsForPublicIPAddress() throws JSONException, IOException {
		String ExistingCloudDeatils[] = Inventoryapi.GetExistingCloudidandCloudnameUsingInternalAPI("Azure","CS_SaaS_QA_INFRA");
		String CloudAccntName = ExistingCloudDeatils[0];
		String CloudAccntId = ExistingCloudDeatils[1];
		String GetResourceListPayload = new String(Files.readAllBytes(Paths.get(PATH_GET_RESOURCE_LIST_PAYLOAD)));
		JSONObject GetResourceListObject = new JSONObject(GetResourceListPayload);
		GetResourceListObject.getJSONObject("filters").getJSONArray("cloud_account").getJSONObject(0).put("name",CloudAccntName);
		GetResourceListObject.getJSONObject("filters").getJSONArray("cloud_account").getJSONObject(0).put("id",CloudAccntId);
		GetResourceListObject.getJSONObject("filters").put("category", "Network");
		GetResourceListObject.getJSONObject("filters").put("component", "Virtual_Networks");
		GetResourceListObject.getJSONObject("filters").put("operation_id", "PublicIPAddressesList");
		GetResourceListObject.getJSONObject("filters").put("resource", "Public_IP_Address");
		System.out.println(GetResourceListObject);
		String GetResourcelisturl = ConfigLoader.getInstance().getInternalBaseURL() + ":18087/v2/" + TENANT_ID_VALUE+ "/resources";
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(POST,GetResourcelisturl, GetResourceListObject.toString());
		String jsonresp = response.getAPIResponseAsString();
		JSONObject json = new JSONObject(jsonresp);
		int resource_list_length = json.getJSONObject("data").getJSONArray("resource_list").length();
		System.out.println(resource_list_length);
		for (int i = 0; i < resource_list_length; i++) {
			String resource = json.getJSONObject("data").getJSONArray("resource_list").getJSONObject(i).getString("resource");
			Assert.assertEquals(resource, "Public_IP_Address");
		}
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(response.getStatusCode(), STATUSCODE_200);
		STATUSLINE200.contains(response.getStatusLine());
		softAssert.assertAll();
	}

//	Verify the Pagination is working fine in Inventory
	@Story("Inventory_Azure")
	@Description("Verify the Pagination is working fine in Inventory")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 3)
	public void VerifyThePaginationInInventory() throws JSONException, IOException {
		String ExistingCloudDeatils[] = Inventoryapi.GetExistingCloudidandCloudnameUsingInternalAPI("Azure","CS_SaaS_QA_INFRA");
		String CloudAccntName = ExistingCloudDeatils[0];
		String CloudAccntId = ExistingCloudDeatils[1];
		String GetResourceListPayload = new String(Files.readAllBytes(Paths.get(PATH_AZURE_GET_RESOURCE_LIST_PAYLOAD)));
		JSONObject GetResourceListObject = new JSONObject(GetResourceListPayload);
		GetResourceListObject.getJSONObject("filters").getJSONArray("cloud_account").getJSONObject(0).put("name",CloudAccntName);
		GetResourceListObject.getJSONObject("filters").getJSONArray("cloud_account").getJSONObject(0).put("id",CloudAccntId);
		System.out.println(GetResourceListObject);
		String GetResourcelisturl = ConfigLoader.getInstance().getInternalBaseURL() + ":18087/v2/" + TENANT_ID_VALUE+ "/resources";
		RestResponse Resourcelistresponse = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(POST, GetResourcelisturl, GetResourceListObject.toString());
		int Pagecount = Integer.parseInt(Resourcelistresponse.getJsonPathReturnValue("string", "data.page_count"));
		for (int i = 0; i < Pagecount; i++) {
			String url = ConfigLoader.getInstance().getInternalBaseURL() + ":18087/v2/" + TENANT_ID_VALUE+ "/resources?page=" + i + "&limit=10";
			restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(POST, url, GetResourceListObject.toString())
			.hamcrestResponseBodyValidation("equalTo", "status", "success");
		}
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(Resourcelistresponse.getStatusCode(), STATUSCODE_200);
		STATUSLINE200.contains(Resourcelistresponse.getStatusLine());
		softAssert.assertAll();
	}

	// Verify Resource Search option is working fine in Inventory section
	@Story("Inventory_Azure")
	@Description("Verify Resource Search option is working fine in Inventory section")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 4)
	public void VerifyResourcesearchField() throws JSONException, IOException {
		List<String> VMStatus = Arrays.asList("VM deallocated", "VM running");
		Random rand = new Random();
		int randomIndex = rand.nextInt(VMStatus.size());
		String randomVMStatus = VMStatus.get(randomIndex);
		String GetResourceListPayload = new String(Files.readAllBytes(Paths.get(PATH_AZURE_GET_RESOURCE_LIST_PAYLOAD)));
		JSONObject GetResourceListObject = new JSONObject(GetResourceListPayload);
		GetResourceListObject.put("search", randomVMStatus);
		System.out.println(GetResourceListObject);
		String GetResourcelisturl = ConfigLoader.getInstance().getInternalBaseURL() + ":18087/v2/" + TENANT_ID_VALUE+ "/resources";
		RestResponse Resourcelistresponse = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(POST, GetResourcelisturl, GetResourceListObject.toString());
		String jsonresp = Resourcelistresponse.getAPIResponseAsString();
		JSONObject json = new JSONObject(jsonresp);
		int resource_list_length = json.getJSONObject("data").getJSONArray("resource_list").length();
		for (int i = 0; i < resource_list_length; i++) {
			String status = Resourcelistresponse.getJsonPathReturnValue("string",
					"data.resource_list[" + i + "].summary_details.status");
			Assert.assertEquals(status, randomVMStatus);
		}
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(Resourcelistresponse.getStatusCode(), STATUSCODE_200);
		STATUSLINE200.contains(Resourcelistresponse.getStatusLine());
		softAssert.assertAll();
	}

//	Verify Resource Filter is working fine in Inventory screen
	@Story("Inventory_Azure")
	@Description("Verify Resource Search option is working fine in Inventory section")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 5)
	public void VerifyResourceFilterOption() throws JSONException, IOException {
		String GetResourceListPayload = new String(Files.readAllBytes(Paths.get(PATH_GET_RESOURCE_LIST_PAYLOAD)));
		JSONObject GetResourceListObject = new JSONObject(GetResourceListPayload);
		GetResourceListObject.getJSONObject("filters").getJSONArray("cloud_account").getJSONObject(0).put("name","AzureAccount-CostUSDAccount");
		GetResourceListObject.getJSONObject("filters").getJSONArray("cloud_account").getJSONObject(0).put("id","5f7594c1c0ce4741f549017c");
		GetResourceListObject.getJSONObject("filters").put("category", "Management_Tools");
		GetResourceListObject.getJSONObject("filters").put("component", "Azure_Monitor");
		GetResourceListObject.getJSONObject("filters").put("operation_id", "AzureAlertsList");
		GetResourceListObject.getJSONObject("filters").put("resource", "Alerts_Config");
		GetResourceListObject.getJSONObject("filters").put("resource_group", "Reports-Validation");
		System.out.println(GetResourceListObject);
		System.out.println(GetResourceListObject);
		String GetResourcelisturl = ConfigLoader.getInstance().getInternalBaseURL() + ":18087/v2/" + TENANT_ID_VALUE+ "/resources";
		RestResponse Resourcelistresponse = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON)
				.invokeRestCall(POST, GetResourcelisturl, GetResourceListObject.toString());
		Resourcelistresponse.getAPIResponseAsPrettyPrint();
		String jsonresp = Resourcelistresponse.getAPIResponseAsString();
		JSONObject json = new JSONObject(jsonresp);
		int resource_list_length = json.getJSONObject("data").getJSONArray("resource_list").length();
		System.out.println(resource_list_length);
		for (int i = 0; i < resource_list_length; i++) {
			String id = Resourcelistresponse.getJsonPathReturnValue("string","data.resource_list[" + i + "].summary_details.id");
			id.contains("/resourceGroups/Reports-Validation/");
		}
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(Resourcelistresponse.getStatusCode(), STATUSCODE_200);
		STATUSLINE200.contains(Resourcelistresponse.getStatusLine());
		softAssert.assertAll();
	}

//	Verify Relationships is displayed with correct data linked to the Resources
	@Story("Inventory_Azure")
	@Description("Verify Relationships is displayed with correct data linked to the Resources")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Response Code", priority = 6)
	public void VerifyRelationshipdata() throws JSONException, IOException {
//		http://10.18.0.18:18087/v2/cloud/dependency/resources/6364ba385b0b331d33a10dbe
		String Relationshipurl = ConfigLoader.getInstance().getInternalBaseURL()
				+ ":18087/v2/cloud/dependency/resources/6364ba385b0b331d33a10dbe";
		RestResponse Resourcelistresponse = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON)
											.invokeRestCall(GET, Relationshipurl).hamcrestResponseBodyValidation("equalTo", "status", "success")
											.hamcrestResponseBodyValidation("equalTo", "message", "Dependent resources found successful");
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(Resourcelistresponse.getStatusCode(), STATUSCODE_200);
		STATUSLINE200.contains(Resourcelistresponse.getStatusLine());
		softAssert.assertAll();
	}
}
