package test.companyName.api.resource.tests;

import static test.companyName.api.service.Route.APPLICATION_JSON;
import static test.companyName.api.service.Route.CONTENT_TYPE;
import static test.companyName.api.service.Route.CreateResourceLockConfigRule_Payload_PATH;
import static test.companyName.api.service.Route.CreateResourceTagRemediation_Payload_PATH;
import static test.companyName.api.service.Route.GetInventoryDetails_Payload_PATH;
import static test.companyName.api.service.Route.GetResourceListing_Payload_PATH;
import static test.companyName.api.service.Route.Get_Inventory_Count_Payload_PATH;
import static test.companyName.api.service.Route.ListResourcesTagsFilter_Payload_PATH;
import static test.companyName.api.service.Route.ListTagsfromResources_Payload_PATH;
import static test.companyName.api.service.Route.List_Inventory_Filter_Payload_PATH;
import static test.companyName.api.service.Route.POST;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import test.companyName.api.applicationApi.Inventoryapi;
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
 * @author Shanumuka
 *
 */
@Epic("companyName Resource Rest API")
@Feature("Resource API Suite")
public class Resource_Inventorys extends APIHook {
	@Story("Get Inventory Count")
	@Description("Get inventory category count based on the cloud and cloud account will list categories & Get inventory resource count based on the cloud and cloud account will list all resources with resource_category, resource_type, resource and will list only the count on number of resources available in particular category")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Code", priority = 1, enabled = true)
	public void GetInventoryCount() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String url = "/resource/inventory/" + tenantId + "/count";
		String GetInventoryCountPayload = new String(Files.readAllBytes(Paths.get(Get_Inventory_Count_Payload_PATH)));
		JSONObject resourceStandardObject = new JSONObject(GetInventoryCountPayload);
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(POST, url,
				resourceStandardObject.toString());
		AssertionMethods.Assertion_200(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}

	@Story("Get Inventory Count")
	@Description("Get inventory - Bad request")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 400 Code", priority = 2, enabled = true)
	public void GetInventoryCountBadRequest() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String url = "/resource/inventory/" + tenantId + "/count";
		String GetInventoryCountPayload = new String(Files.readAllBytes(Paths.get(Get_Inventory_Count_Payload_PATH)));
		JSONObject resourceStandardObject = new JSONObject(GetInventoryCountPayload);
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(POST, url);
		String name = FakerUtils.generateName();
		JSONArray arr = new JSONArray();
		arr.put(name);
		resourceStandardObject.put("name", name);
		AssertionMethods.Assertion_400(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}

	@Story("Get Inventory Count")
	@Description("Get inventory category - 401 Unauthenticated")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 401 Code", priority = 3, enabled = true)
	public void GetInventoryCount401() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String url = "/resource/inventory/" + tenantId + "/count";
		String GetInventoryCountPayload = new String(Files.readAllBytes(Paths.get(Get_Inventory_Count_Payload_PATH)));
		JSONObject resourceStandardObject = new JSONObject(GetInventoryCountPayload);
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setHeader("X-Auth-User", "")
				.invokeRestCall(POST, url, resourceStandardObject.toString());
		AssertionMethods.Assertion_401(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}

	@Story("List Inventory Filters")
	@Description("Get all the available filters for the corresponding cloud.Listing filter attributes will help us in getting resource details with the filters listed can be applied on the resource details call and this api is only to list filtersfor ex: AWS will have regions and tags as filters available and Azure will have resource groups and tags as filters")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Code", priority = 4, enabled = true)
	public void ListInventoryFilter() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String Clouddetails[] = Inventoryapi.GetExistingCloudidandCloudname("AWS");
		String CloudAccntName = Clouddetails[0];
		String CloudAccntId = Clouddetails[1];
		String url = "/resource/inventory/" + tenantId + "/filters?service_name=AWS";
		String ListInventoryFilterPayload = new String(
				Files.readAllBytes(Paths.get(List_Inventory_Filter_Payload_PATH)));
		JSONObject resourceStandardObject = new JSONObject(ListInventoryFilterPayload);
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(POST, url,
				resourceStandardObject.toString());
		AssertionMethods.Assertion_200(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}

	@Story("List Inventory Filters")
	@Description("List Inventory Filters -400 Bad request")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 400 Code", priority = 5, enabled = false)
	public void ListInventoryFilter400BadRequest() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String Clouddetails[] = Inventoryapi.GetExistingCloudidandCloudname("AWS");
		String CloudAccntName = Clouddetails[0];
		String CloudAccntId = Clouddetails[1];
		String url = "/resource/inventory/" + tenantId + "/filters?service_name=NIUHHHH.";
		String ListInventoryFilterPayload = new String(
				Files.readAllBytes(Paths.get(List_Inventory_Filter_Payload_PATH)));

		JSONObject resourceStandardObject = new JSONObject(ListInventoryFilterPayload);
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(POST, url,
				resourceStandardObject.toString());
		AssertionMethods.Assertion_400(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}

	@Story("List Inventory Filters")
	@Description("List Inventory Filters -401")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 401 Code", priority = 6, enabled = true)
	public void ListInventoryFilter401() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String Clouddetails[] = Inventoryapi.GetExistingCloudidandCloudname("AWS");
		String CloudAccntName = Clouddetails[0];
		String CloudAccntId = Clouddetails[1];
		String url = "/resource/inventory/" + tenantId + "/filters?service_name=AWS";
		String ListInventoryFilterPayload = new String(
				Files.readAllBytes(Paths.get(List_Inventory_Filter_Payload_PATH)));
		JSONObject resourceStandardObject = new JSONObject(ListInventoryFilterPayload);
		resourceStandardObject.getJSONArray("cloud_account").getJSONObject(0).put("name", CloudAccntName);
		resourceStandardObject.getJSONArray("cloud_account").getJSONObject(0).put("id", CloudAccntId);
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).setHeader("X-Auth-User", "")
				.invokeRestCall(POST, url, resourceStandardObject.toString());
		AssertionMethods.Assertion_401(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}

	@Story("Get Resource Listing")
	@Description("Listing the Resource details -Specify the tenant ID for the Resource. This is a unique ID and can be retrieved using the List Tenants API.")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Code", priority = 7, enabled = true)
	public void GetResourceListing() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String Clouddetails[] = Inventoryapi.GetExistingCloudidandCloudname("AWS");
		String CloudAccntId = Clouddetails[1];
		String url = "/resource/inventory/" + tenantId + "/resource_listing";
		String Get_Inventory_Count_Payload = new String(Files.readAllBytes(Paths.get(GetResourceListing_Payload_PATH)));
		JSONObject resourceStandardObject = new JSONObject(Get_Inventory_Count_Payload);
		resourceStandardObject.put("cloud_account_id", CloudAccntId);

		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(POST, url,
				resourceStandardObject.toString());
		AssertionMethods.Assertion_200(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}

	@Story("Get Resource Listing")
	@Description("Listing the Resource details-Bad request")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 400 Code", priority = 8, enabled = true)
	public void GetResourceListing400() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String url = "/resource/inventory/" + tenantId + "/resource_listing";
		String Get_Inventory_Count_Payload = new String(Files.readAllBytes(Paths.get(GetResourceListing_Payload_PATH)));
		JSONObject resourceStandardObject = new JSONObject(Get_Inventory_Count_Payload);
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(POST, url,
				resourceStandardObject.toString());
		AssertionMethods.Assertion_400(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}

	@Story("Get Resource Listing")
	@Description("Listing the Resource details401 Unauthenticated")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 401 Code", priority = 9, enabled = true)
	public void GetResourceListing401() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String url = "/resource/inventory/" + tenantId + "/resource_listing";
		String Get_Inventory_Count_Payload = new String(Files.readAllBytes(Paths.get(GetResourceListing_Payload_PATH)));
		JSONObject resourceStandardObject = new JSONObject(Get_Inventory_Count_Payload);
		RestResponse response = restAssuredCore.setHeader("X-Auth-User", "").setHeader(CONTENT_TYPE, APPLICATION_JSON)
				.invokeRestCall(POST, url, resourceStandardObject.toString());
		AssertionMethods.Assertion_401(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();

	}

	@Story("Get Inventory Details")
	@Description("Get Inventory Details- Get resource details based on the cloud accounts, category, component, resource and filters can be applied based on the cloud and cloud account for ex: AWS will have regions in filters and Azure will have resource group as filters for listing resources.")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Code", priority = 10, enabled = true)
	public void GetInventoryDetails() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String Clouddetails[] = Inventoryapi.GetExistingCloudidandCloudname("AWS");
		String CloudAccntName = Clouddetails[0];
		String CloudAccntId = Clouddetails[1];
		String url = "/resource/inventory/" + tenantId + "/resources";
		String Get_Inventory_Count_Payload = new String(
				Files.readAllBytes(Paths.get(GetInventoryDetails_Payload_PATH)));
		JSONObject resourceStandardObject = new JSONObject(Get_Inventory_Count_Payload);
		resourceStandardObject.getJSONObject("filters").getJSONArray("cloud_account").getJSONObject(0).put("name",
				CloudAccntName);
		resourceStandardObject.getJSONObject("filters").getJSONArray("cloud_account").getJSONObject(0).put("id",
				CloudAccntId);
		resourceStandardObject.getJSONObject("filters").put("category", "Compute");
		resourceStandardObject.getJSONObject("filters").put("component", "EC2");
		resourceStandardObject.getJSONObject("filters").put("operation_id", "DescribeInstances");
		resourceStandardObject.getJSONObject("filters").put("resource", "Instances");

		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(POST, url,
				resourceStandardObject.toString());
		AssertionMethods.Assertion_200(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}

	@Story("Get Inventory Details")
	@Description("Get Inventory Details- Get resource details based on the cloud accounts, category, component, resource and filters can be applied based on the cloud and cloud account for ex: AWS will have regions in filters and Azure will have resource group as filters for listing resources.")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 400 Code", priority = 11, enabled = true)
	public void GetInventoryDetails400() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String url = "/resource/inventory/" + tenantId + "/resources";
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(POST, url);
		AssertionMethods.Assertion_400(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}

	@Story("Get Inventory Details")
	@Description("Get Inventory Details- Get resource details based on the cloud accounts, category, component, resource and filters can be applied based on the cloud and cloud account for ex: AWS will have regions in filters and Azure will have resource group as filters for listing resources.")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 401 Code", priority = 12, enabled = true)
	public void GetInventoryDetails401() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();

		String url = "/resource/inventory/" + tenantId + "/resources";
		String Get_Inventory_Count_Payload = new String(
				Files.readAllBytes(Paths.get(GetInventoryDetails_Payload_PATH)));
		JSONObject resourceStandardObject = new JSONObject(Get_Inventory_Count_Payload);
		String Clouddetails[] = Inventoryapi.GetExistingCloudidandCloudname("AWS");
		String CloudAccntName = Clouddetails[0];
		String CloudAccntId = Clouddetails[1];
		resourceStandardObject.getJSONObject("filters").getJSONArray("cloud_account").getJSONObject(0).put("name",
				CloudAccntName);
		resourceStandardObject.getJSONObject("filters").getJSONArray("cloud_account").getJSONObject(0).put("id",
				CloudAccntId);
		resourceStandardObject.getJSONObject("filters").put("category", "Compute");
		resourceStandardObject.getJSONObject("filters").put("component", "EC2");
		resourceStandardObject.getJSONObject("filters").put("operation_id", "DescribeInstances");
		resourceStandardObject.getJSONObject("filters").put("resource", "Instances");
		RestResponse response = restAssuredCore.setHeader("X-Auth-User", "").setHeader(CONTENT_TYPE, APPLICATION_JSON)
				.invokeRestCall(POST, url, resourceStandardObject.toString());
		AssertionMethods.Assertion_401(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}

	@Story("Create Resource Lock Config Rule")
	@Description("Create Resource Lock Config Rule -Creates a new lock config ruleunder a companyName")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Code", priority = 13, enabled = true)
	public void CreateResourceLockConfigRule() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String url = "/resource/locks/" + tenantId + "/lock_config_rule";
		String CreateResourceLockConfigRule_Payload_PATH_Payload = new String(
				Files.readAllBytes(Paths.get(CreateResourceLockConfigRule_Payload_PATH)));
		String Clouddetails[] = Inventoryapi.GetExistingCloudidandCloudname("AWS");
		String CloudAccntName = Clouddetails[0];
		String CloudAccntId = Clouddetails[1];
		JSONObject resourceStandardObject = new JSONObject(CreateResourceLockConfigRule_Payload_PATH_Payload);
		resourceStandardObject.put("cloud_account_name", CloudAccntName);
		resourceStandardObject.put("cloud_account_id", CloudAccntId);
		resourceStandardObject.getJSONArray("tenant_ids").put(tenantId);
		System.out.println(resourceStandardObject.toString());
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(POST, url,
				resourceStandardObject.toString());
		AssertionMethods.Assertion_200(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}

	@Story("Create Resource Lock Config Rule")
	@Description("Create Resource Lock Config Rule -Creates a new lock config ruleunder a companyName")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 400 Code", priority = 14, enabled = true)
	public void CreateResourceLockConfigRule400() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String url = "/resource/locks/" + tenantId + "/lock_config_rule";
		String CreateResourceLockConfigRule_Payload_PATH_Payload = new String(
				Files.readAllBytes(Paths.get(CreateResourceLockConfigRule_Payload_PATH)));
		String Clouddetails[] = Inventoryapi.GetExistingCloudidandCloudname("AWS");
		String CloudAccntName = Clouddetails[0];
		String CloudAccntId = Clouddetails[1];
		JSONObject resourceStandardObject = new JSONObject(CreateResourceLockConfigRule_Payload_PATH_Payload);
		resourceStandardObject.put("cloud_account_name", CloudAccntName);
		resourceStandardObject.put("cloud_account_id", CloudAccntId);
		resourceStandardObject.put("tenant_ids", tenantId);
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(POST, url,
				resourceStandardObject.toString());
		AssertionMethods.Assertion_400(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}

	@Story("Create Resource Lock Config Rule")
	@Description("Create Resource Lock Config Rule -Creates a new lock config ruleunder a companyName")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 401 Code", priority = 15, enabled = true)
	public void CreateResourceLockConfigRule401() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String url = "/resource/locks/" + tenantId + "/lock_config_rule";
		String CreateResourceLockConfigRule_Payload_PATH_Payload = new String(
				Files.readAllBytes(Paths.get(CreateResourceLockConfigRule_Payload_PATH)));
		String Clouddetails[] = Inventoryapi.GetExistingCloudidandCloudname("AWS");
		String CloudAccntName = Clouddetails[0];
		String CloudAccntId = Clouddetails[1];
		JSONObject resourceStandardObject = new JSONObject(CreateResourceLockConfigRule_Payload_PATH_Payload);
		resourceStandardObject.put("cloud_account_name", CloudAccntName);
		resourceStandardObject.put("cloud_account_id", CloudAccntId);
		resourceStandardObject.getJSONArray("tenant_ids").put(tenantId);
		RestResponse response = restAssuredCore.setHeader("X-Auth-User", "").setHeader(CONTENT_TYPE, APPLICATION_JSON)
				.invokeRestCall(POST, url, resourceStandardObject.toString());
		AssertionMethods.Assertion_401(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}

	@Story("List Tags from Resources")
	@Description("List Tags from Resources -Tag filters for tenant wise")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Code", priority = 16, enabled = true)
	public void ListTagsfromResources() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String url = "/resource/tags/" + tenantId + "/get_tag_keys";
		String ListTagsfromResources_Payload = new String(
				Files.readAllBytes(Paths.get(ListTagsfromResources_Payload_PATH)));
		JSONObject resourceStandardObject = new JSONObject(ListTagsfromResources_Payload);
		resourceStandardObject.put("cloud", "AWS");
		resourceStandardObject.getJSONArray("tenant_ids").put(tenantId);
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(POST, url,
				resourceStandardObject.toString());
		AssertionMethods.Assertion_200(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}

	@Story("List Tags from Resources")
	@Description("List Tags from Resources -Tag filters for tenant wise")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 400 Code", priority = 17, enabled = true)
	public void ListTagsfromResources400() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String url = "/resource/tags/" + tenantId + "/get_tag_keys";
		String ListTagsfromResources_Payload = new String(
				Files.readAllBytes(Paths.get(ListTagsfromResources_Payload_PATH)));
		JSONObject resourceStandardObject = new JSONObject(ListTagsfromResources_Payload);
		resourceStandardObject.put("cloud", "AWS");
		resourceStandardObject.put("tenant_ids", tenantId);
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(POST, url);
		AssertionMethods.Assertion_400(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}

	@Story("List Tags from Resources")
	@Description("List Tags from Resources -Tag filters for tenant wise")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 401 Code", priority = 18, enabled = true)
	public void ListTagsfromResources401() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String url = "/resource/tags/" + tenantId + "/get_tag_keys";
		String ListTagsfromResources_Payload = new String(
				Files.readAllBytes(Paths.get(ListTagsfromResources_Payload_PATH)));
		JSONObject resourceStandardObject = new JSONObject(ListTagsfromResources_Payload);
		resourceStandardObject.put("cloud", "AWS");
		resourceStandardObject.getJSONArray("tenant_ids").put(tenantId);
		RestResponse response = restAssuredCore.setHeader("X-Auth-User", "").setHeader(CONTENT_TYPE, APPLICATION_JSON)
				.invokeRestCall(POST, url, resourceStandardObject.toString());
		AssertionMethods.Assertion_401(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}

	@Story("List Resources Tags Filter")
	@Description("List Resources Tags Filter - Resources filters for tenant wise")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Code", priority = 19, enabled = true)
	public void ListResourcesTagsFilter() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String url = "/resource/tags/" + tenantId + "/get_tenant_resources";
		String ListResourcesTagsFilter_Payload = new String(
				Files.readAllBytes(Paths.get(ListResourcesTagsFilter_Payload_PATH)));
		JSONObject resourceStandardObject = new JSONObject(ListResourcesTagsFilter_Payload);
		resourceStandardObject.put("cloud", "AWS");
		resourceStandardObject.getJSONArray("tenant_ids").put(tenantId);
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(POST, url,
				resourceStandardObject.toString());
		AssertionMethods.Assertion_200(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}

	@Story("List Resources Tags Filter")
	@Description("List Resources Tags Filter - Resources filters for tenant wise")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 400 Code", priority = 20, enabled = true)
	public void ListResourcesTagsFilter400() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String url = "/resource/tags/" + tenantId + "/get_tenant_resources";
		String ListResourcesTagsFilter_Payload = new String(
				Files.readAllBytes(Paths.get(ListResourcesTagsFilter_Payload_PATH)));
		JSONObject resourceStandardObject = new JSONObject(ListResourcesTagsFilter_Payload);
		resourceStandardObject.put("cloud", "AWS");
		resourceStandardObject.put("tenant_ids", tenantId);
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(POST, url);
		AssertionMethods.Assertion_400(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}

	@Story("List Resources Tags Filter")
	@Description("List Resources Tags Filter - Resources filters for tenant wise")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 401 Code", priority = 21, enabled = true)
	public void ListResourcesTagsFilter401() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String url = "/resource/tags/" + tenantId + "/get_tenant_resources";
		String ListResourcesTagsFilter_Payload = new String(
				Files.readAllBytes(Paths.get(ListResourcesTagsFilter_Payload_PATH)));
		JSONObject resourceStandardObject = new JSONObject(ListResourcesTagsFilter_Payload);
		resourceStandardObject.put("cloud", "AWS");
		resourceStandardObject.getJSONArray("tenant_ids").put(tenantId);
		RestResponse response = restAssuredCore.setHeader("X-Auth-User", "").setHeader(CONTENT_TYPE, APPLICATION_JSON)
				.invokeRestCall(POST, url, resourceStandardObject.toString());
		AssertionMethods.Assertion_401(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}

	@Story("Create Resource Tag Remediation")
	@Description("Create Resource Tag Remediation - Specify the tenant ID. This is a unique ID and can be retrieved using the List Tenants API.")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 200 Code", priority = 22, enabled = true)
	public void CreateResourceTagRemediation() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String url = "/resource/tags/" + tenantId + "/remediation";
		String CreateResourceTagRemediation_Payload = new String(
				Files.readAllBytes(Paths.get(CreateResourceTagRemediation_Payload_PATH)));
		JSONObject resourceStandardObject = new JSONObject(CreateResourceTagRemediation_Payload);
		resourceStandardObject.put("cloud", "AWS");
		RestResponse response = restAssuredCore.setHeader(CONTENT_TYPE, APPLICATION_JSON).invokeRestCall(POST, url,
				resourceStandardObject.toString());
		AssertionMethods.Assertion_200(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}

	// Only one time working
	@Story("Create Resource Tag Remediation")
	@Description("Create Resource Tag Remediation - Specify the tenant ID. This is a unique ID and can be retrieved using the List Tenants API.")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 400 Code", priority = 23, enabled = true)
	public void CreateResourceTagRemediation400() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String url = "/resource/tags/" + tenantId + "/remediation";
		String CreateResourceTagRemediation_Payload = new String(
				Files.readAllBytes(Paths.get(CreateResourceTagRemediation_Payload_PATH)));
		JSONObject resourceStandardObject = new JSONObject(CreateResourceTagRemediation_Payload);
		resourceStandardObject.put("cloud", "AZURE");
		RestResponse response = restAssuredCore.setHeader("X-Auth-User", "").setHeader(CONTENT_TYPE, APPLICATION_JSON)
				.invokeRestCall(POST, url, resourceStandardObject.toString());

		AssertionMethods.Assertion_400(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}

	@Story("Create Resource Tag Remediation")
	@Description("Create Resource Tag Remediation - Specify the tenant ID. This is a unique ID and can be retrieved using the List Tenants API.")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Should Show 401 Code", priority = 24, enabled = true)
	public void CreateResourceTagRemediation401() throws JSONException, IOException {
		String tenantId = ConfigLoader.getInstance().gettenant_id();
		String url = "/resource/tags/" + tenantId + "/remediation";
		String CreateResourceTagRemediation_Payload = new String(
				Files.readAllBytes(Paths.get(CreateResourceTagRemediation_Payload_PATH)));
		JSONObject resourceStandardObject = new JSONObject(CreateResourceTagRemediation_Payload);
		resourceStandardObject.put("cloud", "AWS");
		RestResponse response = restAssuredCore.setHeader("X-Auth-User", "").setHeader(CONTENT_TYPE, APPLICATION_JSON)
				.invokeRestCall(POST, url, resourceStandardObject.toString());
		AssertionMethods.Assertion_401(response);
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertAll();
	}
}
