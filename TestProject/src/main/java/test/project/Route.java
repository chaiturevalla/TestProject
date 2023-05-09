package test.companyName.api.service;

import test.companyName.api.utils.ConfigLoader;
import test.companyName.api.utils.FakerUtils;

public class Route {
	public static final String BASE_AUTOHRIZE_URI = "/v1";
	public static final String BASE_PATH = "/v1";

	public static final String AUTH_TOKEN = "/auth/tokens";
	public static final String TOKEN = "/token";
	public static final String TENANTS = "/tenants";
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String APPLICATION_JSON = "application/json";
	public static final String ROLES = "/roles";
	public static final String CREATE_TENANT_PAYLOAD = "src\\test\\resources\\Payloads\\Identity\\createTenantPayload.json";
	public static final String UPDATE_TENANT_PAYLOAD = "src\\test\\resources\\Payloads\\Identity\\UpdateTenantPayload.json";
	public static final String SUSPEND_TENANT_PAYLOAD = "src\\test\\resources\\Payloads\\Identity\\SuspendTenantPayload.json";
	public static final String UPDATE_TENANT_WITH_INVALID_PAYLOAD = "src\\test\\resources\\Payloads\\Identity\\UpdateTenantWithInvalidpayload.json";
	public static final String CHANGE_PASSWORD_WITH_INVALID_CURRENTPASSWORD = "src\\test\\resources\\Payloads\\Identity\\UpdatePasswordWithInvalidCurrentPasswordPayload.json";
	public static final String CHANGE_PASSWORD = "src\\test\\resources\\Payloads\\Identity\\UpdatePassword.json";
	public static final String INVALID_CHANGE_REQUEST_PAYLOAD = "src\\test\\resources\\Payloads\\Identity\\InvalidChangeRequestPayload.json";
	public static final String CHANGE_TIMEZONE = "src\\test\\resources\\Payloads\\Identity\\UpdateTimezone.json";
	public static final String CHANGE_PASSWORD_WITH_INVALID_CURRENTTIMEZONE = "src\\test\\resources\\Payloads\\Identity\\UpdateTimezoneWithInvalidCurrentTimezonePayload.json";
	public static final String EQUAL_TO = "equalTo";
	public static final String CONTAINS_STRING = "containsString";
	public static final String TENANT_ID = "tenant_id";
	public static final String STATUS = "status";
	public static final String ACTIVE = "active";
	public static final String SUSPENDED = "suspended";
	public static final String INPUT_PAYLOAD_VALIDATION_FAILED = "Input payload validation failed";
	public static final String TENANT_NOT_FOUND = "Tenant not found.";
	public static final String TENANT_DELETED_SUCCESSFULLY = "Tenant deleted successfully.";

	public static final String ACTIVATE_PASSWORD_JSON_STRING = "{\"password\":\"companyName@123\"}";
	public static final String USERNAME = "username";
	public static final String EMAIL = "email";
	public static final String AT_THE_RATE = "@";
	public static final String USERS_URL = "/users";
	public static final String ACTIVATE_URL = "/activate_user";
	public static final String VERIFY_USER = "/verify_user";
	public static final String FORWARD_SLASH = "/";
	public static final String USER_DELETE_URL = "/users/delete/";
	public static final String USER_CHANGE_PASSWORD_URL = "/users/change_password/";
	public static final String USER_CHANGE_TIMEZONE_URL = "/users/change_time_zone/";
	public static final String STRING_TYPE = "string";
	public static final String POST = "POST";
	public static final String DELETE = "DELETE";
	public static final String GET = "GET";
	public static final String USER_ID = "user_id";
	public static final String USER_CREATE_URL = "/users/create";
	public static final String EXISTING_ACTIVATED_USER_ID = "622621554e3cab90dc7c12d9";
	public static final String VALID_AUTH_TOKEN = "05a6c7a0-e79d-4502-af06-c653db4b1de";
	public static final String INVALID_AUTH_TOKEN = "5edd6e-b3a8-4342-93c1-77d09957f94-invalid";
	public static final String INVALID_TENANT_ID = "61431c3eb6e0d7e43298";
	public static final String VALID_TENANT_ID = "6216011a91075eba0f179a62";

	public static final String EXISTING_ACTIVATED_USER_ID_QA = "6142cfd9b6e0d7e432980e04";
	public static final String VALID_AUTH_TOKEN_QA = "638698cb-d8e8-4d7f-bc31-e37b0a8c94a3";
	public static final String EXISTING_ACTIVATED_USER_ID_SANDBOX = "624029679cb21788fbab17eb";
	public static final String VALID_AUTH_TOKEN_SANDBOX = "1f47cd54-728b-4977-a3c9-1ea1daf84064";

	public static final String USERS_RESEND_TOKEN = "/users/resend_token/";
	public static final String VERIFY_USER_URL = "/users/6142cfd9b6e0d7e432980e04/verify_user/638698cb-d8e8-4d7f-bc31-e37b0a8c94a3";

	public static final String CREATE_USER_PAYLOAD_PATH = "src\\test\\resources\\Payloads\\Identity\\createUserPayload.json";
	public static final String CREATE_SUMMARY_POSTURE_BY_POLICIES_PATH = "src\\test\\resources\\Payloads\\Compliance\\ComplianceSummaryPostureByPolicies.json";
	public static final String LIST_USERS_URL = "/users/list";
	public static final String LIST_PRODUCTBUNDLE_URL = "/users/product-bundles";
	public static final String VIEW_USER_PROFILE = "/users/profile/"+ ConfigLoader.getInstance().getuser_id();
	public static final String FORGET_PASSWORD = "/users/forget_password/";
	public static final String USER_UPDATE_URL = "/users/update/";
	public static final String PUT = "PUT";
	public static final String USER_VIEW_URL = "/users/view/";
	public static final String USER_DELETED = "User deleted";
	public static final String MESSAGE = "message";
	public static final String TENANT_ID_VALUE = ConfigLoader.getInstance().gettenant_id();
	public static final String USERID_ID_VALUE = ConfigLoader.getInstance().getuser_id();
	public static final String COMPLIANCE = "compliance";
	public static final String STANDARDS = "standards";
	public static final String QUESTION_MARK = "?";
	public static final String TYPE = "type";
	public static final String IS_EQUAL_TO_SYMBOL = "=";
	public static final String MARKETPLACE = "marketplace";
	public static final String CONTROLS = "controls";
	public static final String LIST = "list";
	public static final String LIMIT = "limit";
	public static final String AMPERSCEND = "&";
	public static final String PERCENTILE = "%";
	public static final String COMPLIANCE_URI = "compliance_uri";
	public static final String MULTIPART_FORMDATA = "multipart/form-data";

	public static final String BAD_REQUEST_COMPLIANCE_CONTROL_MAPPING_URI = "/compliance/613a1b651bde67f3e6d8bb9f/mapping/list?limit=10&page=200&caller=controls&compliance_uri=SOC2/0017";

	public static final String BAD_REQUEST_COMPLIANCE_CONTROL_URI = "compliance/" + TENANT_ID_VALUE
			+ "/controls/list?limit=1&page=2&compliance_uri=CIS-Azure%2F0015%2F";

	public static final String ASSESSMENT_HISTORYLIST_URL = "compliance/" + TENANT_ID_VALUE
			+ "/assessment_history/list";
	public static final String MAPPING = "mapping";
	public static final String CALLER = "caller";
	public static final String CONTROL = "control";
	public static final String SUCCESS = "success";
	public static final String COMPLIANCE_CONTROL_DEFINITIONS_LIST = "compliance control definitions list";
	public static final String BAD_REQUEST_COMPLIANCE_URI = "compliance/60641ed224bcbf5b5852f7df/controls/list?limit=1&page=2&compliance_uri=CIS-Azure%2F0015%2F";

	public static final String COMPLIANCE_POSTURE_URL = "compliance/posture_summary/list";
	public static final String BADCOMPLIANCE_POSTURE_URL = "compliance/posture_summary/list%/%";
	public static final String BADASSESSMENT_HISTORYLIST_URL = "compliance/" + TENANT_ID_VALUE + 345
			+ "/assessment_history/list%/%";
	public static final String X_AUTH_TOKEN = "X-Auth-Token";
	public static final String INVALID = "invalid";
	public static final String ASSESSMENT_HIST = "assessment_history";
	public static final String ASSESSMENT_HISTORYLIST_LIMIT_PAGE_URL = "compliance/" + TENANT_ID_VALUE
			+ "/assessment_history/list?limit=10&page=1";
	public static final String ASSESSMENT_HISTORYLIST_LIMIT_URL = "compliance/" + TENANT_ID_VALUE
			+ "/assessment_history/list?limit=5";
	public static final String ASSESSMENT_HISTORYLIST_AUDIT_URL = "compliance/" + TENANT_ID_VALUE
			+ "/compliance_control_mapping/" + "get_on_audit_entries?assessment_job_number=";
	public static final String BADASSESSMENT_HISTORYLIST_AUDIT_URL = "compliance/" + TENANT_ID_VALUE
			+ "/compliance_control_mapping/%";
	public static final String SEARCH = "search";
	public static final String FILTER_POLICY_URL = "compliance_posture/policy_filters";
	public static final String CLOUD_ACCOUNTS_URL = "governance/account/" + TENANT_ID_VALUE + "/get/cloud_accounts";
	public static final String COMPLAINCE_DEMAND_EXECUTE = "/compliance/" + TENANT_ID_VALUE
			+ "/control_mapping/on_demand_execute_standard";

	public static final String CREATE_STANDARD_EXECUTE_QA_PATH = "src\\test\\resources\\Payloads\\Compliance\\createStandardsExecutePayloadqa.json";
	public static final String CREATE_STANDARD_EXECUTE_SANDBOX_PATH = "src\\test\\resources\\Payloads\\Compliance\\createStandardsExecutePayloadsandbox.json";
	public static final String VALID_ACCOUNT_ID_QA = "6193a04152721d6c701892b3";
	public static final String VALID_ACCOUNT_ID_SANDBOX = "6193a04152721d6c701892b3";
	public static final String SUMMARY_BYACCOUNTS_URL = "compliance_posture/summary_by_accounts";
	public static final String SUMMARY_BYACCOUNTS_PATH = "src\\test\\resources\\Payloads\\Compliance\\createSummaryByAccounts.json";
	public static final String COMPLIANCE_POSTUREPOLICY_FILTERS_URL = "/compliance_posture/policy_filters?tenant_id="
			+ TENANT_ID_VALUE;
	public static final String LIST_COMPLIANCE_FILTER_URL = "/compliance_posture/filters?tenant_id=" + TENANT_ID_VALUE;
	public static final String LIST_COMPLIANCE_FILTER_FOR_HEATSTACK_URL = "/compliance_posture/filters/by_policy?tenant_id="
			+ TENANT_ID_VALUE;
	public static final String VISIBILITY = "visibility";
	public static final String SERVICE_ACCOUNT_ID = "5fbe677ac0ce4756d54a5f39";
	public static final String COMPLIANCE_VISIBILITY_TREND = "visibility_trend?compliance_uri=AC3-v-01/0000";
	public static final String COMPLIANCE_POSTURE = "compliance_posture";
	public static final String FILTERS = "filters";
	public static final String BY_POLICY = "by_policy";
	public static final String COMPLIANCE_POSTURE_SUMMARY_BY_POLICIES = "compliance_posture/summary_by_policies";
	public static final String COMPLIANCE_POSTURE_SUMMARY_BY_POLICIES_INVALIDREQUEST = "compliance_posture/summary_by_policies%";
	public static final String MY_STANDARD = "my_standard";
	public static final String VALID_SERVICE_TYPE = "valid_service_type";
	public static final String AWS = "AWS";
	public static final String AZURE = "Azure";
	public static final String AZURE_CSP_DIRECT = "Azure_CSP-Direct";
	public static final String AZURE_EA = "Azure_EA";
	public static final String GCP = "GCP";
	public static final String GROUP_BY = "group_by";
	public static final String CONTROL_FAMILY = "control_family";
	public static final String COUNT_BY = "count_by";
	public static final String CREATE_STANDARD_EXECUTE_PATH = "src\\test\\resources\\Payloads\\Compliance\\createStandardsExecutePayload.json";
	public static final String BADCOMPLIANCE_POSTUREPOLICY_FILTERS_URL = "/compliance_posture/%policy_filters?tenant_id="
			+ TENANT_ID_VALUE;
	public static final String POLICYID = "5d39838b765aa0ce208dba5d";
	public static final String COMPLIANCEPOSTURE_DETAILSFORPOLICIESQA_PATH = "src\\test\\resources\\Payloads\\Compliance\\CompliancePostureDetailsForPoliciesQA.json";
	public static final String COMPLIANCEPOSTURE_DETAILSFORPOLICIES_URL = "/compliance_posture/detail_for_policy/"
			+ POLICYID;
	public static final String COMPLIANCEPOSTURE_DETAILSFORPOLICIES_EXPECTED = "Compliance Posture By Policy Detail";
	public static final String COMPLIANCEFILTER_EXPECTED = "compliance posture filters.";
	public static final String COMPLIANCEFILTERFORHEATSTACK_EXPECTED = "Compliance Posture By Policy Filters.";
	public static final String COMPLIANCE_VISIBILITY_TREND_EXPECTED = "Compliance Standard Trend";

	public static final String COST_EXECDASHBOARD_BUDGET_DRIFT_URL = "/cost/executive_dashboard/budget_drift";
	public static final String COST_EXECDASHBOARD_COMMONPAYLOAD_PATH = "src\\test\\resources\\Payloads\\Cost\\Executivedashboard\\CommonPayload.json";
	public static final String GEO_COORDINATES_FOR_REGIONS_COMMONPAYLOAD_PATH = "src\\test\\resources\\Payloads\\Cost\\Executivedashboard\\Geo_Coordinates_For_Region_Payload.json";
	public static final String COST_EXECDASHBOARD_OPTIMIZATION_BY_OPTIMZATIONTYPESREQUEST_URL = "/cost/executive_dashboard/cost_optimization_by_optimization_types";
	public static final String COST_EXECDASHBOARD_OPTIMIZATION_BY_OPTIMZATIONTYPESREQUEST_PATH = "src\\test\\resources\\Payloads\\Cost\\Executivedashboard\\optimizationbyoptimizationrequest.json";
	public static final String COST_EXECDASHBOARD_SPEND_BY_CLOUD_URL = "/cost/executive_dashboard/spend-by-cloud";
	public static final String COST_EXECDASHBOARD_SPEND_BY_CLOUD_PATH = "src\\test\\resources\\Payloads\\Cost\\Executivedashboard\\spendbycloudtenant.json";
	public static final String COST_EXECDASHBOARD_SPEND_BY_CLOUD_TENANT_URL = "/cost/executive_dashboard/spend_by_cloud_tenant";
	public static final String CLOUD_FORECASTER_FOR_SIXMONTHS_URL = "/cost/executive_dashboard/cloud_forecaster_for_six_months";
	public static final String COST_TREND_BY_CLOUD_URL = "/cost/executive_dashboard/cost_trend_by_cloud";
	public static final String COST_SPEND_BY_CLOUD_ACCOUNT_URL = "/cost/executive_dashboard/spend-by-cloud-account";
	public static final String COST_EXECDASHBOARD_TOP_BUDGETSBYTENANT = "/cost/executive_dashboard/top_budgets";
	public static final String COST_SPEND_BY_REGION_URL = "/cost/executive_dashboard/spend_by_region";
	public static final String TOP_COST_OPTIMIZATIONS_BY_PRODUCT_CATEGORY_URL = "/cost/executive_dashboard/top_product_cost_optimization";
	public static final String COST_EXECDASHBOARD_GET_COST_SPEND_BY_BILLING_TYPE = "/cost/executive_dashboard/spend-by-billing-type";
	public static final String COST_EXECDASHBOARD_GET_TAGGED_AND_UNTAGGED_SPEND_CLOUDWISE= "/cost/executive_dashboard/tagged_and_untagged_spend_by_cloud";
	public static final String COST_SCHEDULE_RECOMMENDATION_DASHBOARD_SUMMARY = "src\\test\\resources\\JSONSchema_Source_Files\\Cost\\CostSavings\\CostScheduleRecommendationsDashboardSummary.json";

	public static final String GET_COST_ANOMALIES_BY_CLOUD_URL = "/cost/executive_dashboard/cost_anomalies_by_cloud";
	public static final String GET_COST_SPEND_BY_CLOUD_AND_RESOURCE_CATEGORY_URL = "/cost/executive_dashboard/spend_by_cloud_resource_category";
	public static final String GET_TAGGED_AND_UNTAGGED_RESOURCE_BY_PRODUCT_CATEGORY_URL = "/cost/executive_dashboard/tagged_and_untagged_resources";
	public static final String GET_TAGGED_AND_UNTAGGED_CLOUDWISE_TREND_URL = "/cost/executive_dashboard/tagged_untagged_trend_cost_trend";
	public static final String TOP_COST_OPTIMIZATIONS_BY_REGIONS_URL = "/cost/executive_dashboard/top_region_cost_optimization";
	public static final String GEO_COORDINATES_FOR_REGIONS_URL = "/cost/executive_dashboard/geo_coordinates_for_regions";
	public static final String GEO_COORDINATES_FOR_REGIONS_NOTFOUND_URL = "/cost/executive_dashboard/geo_coordinates_for_regions/hwbabf";
	public static final String CLOUD_ACCOUNTS_SUMMARY_URL = "/governance/account/"+ ConfigLoader.getInstance().gettenant_id() + "/get/validate&summary";
	public static final String CLOUD_ACCOUNTS_SUMMARY_BAD_REQUEST_URL = "/governance/account/"+ ConfigLoader.getInstance().gettenant_id() + "/get/validate&summary";
	public static final String CLOUD_ACCOUNTS_SUMMARY_NOTFOUND_URL = "/governance/account/"+ ConfigLoader.getInstance().gettenant_id() + "/validate&summary/";
	public static final String LIST_CLOUD_ACCOUNTS_URL = "/governance/account/"+ ConfigLoader.getInstance().gettenant_id() + "/get/cloud_accounts";
	public static final String LIST_CLOUD_ACCOUNTS_BAD_REQUEST_URL = "/governance/account/"+ ConfigLoader.getInstance().gettenant_id() + "/get/validate";
	public static final String LIST_CLOUD_ACCOUNTS_NOTFOUND_URL = "/governance/account/"+ ConfigLoader.getInstance().gettenant_id() + "/cloud_accounts";
	public static final String DESCRIBE_CLOUD_ACCOUNT_URL = "/governance/account/5fda041fc0ce4702bdb06692/get/6274cbfe2f7ab6a701beaf30";
	public static final String DESCRIBE_CLOUD_ACCOUNT_BAD_REQUEST_URL = "/governance/account/"+ ConfigLoader.getInstance().gettenant_id() + "/get/validate";
	public static final String DESCRIBE_CLOUD_ACCOUNT_NOTFOUND_URL = "/governance/account/"+ ConfigLoader.getInstance().gettenant_id() + "/describe_cloud_account";
	public static final String LIST_CUSTOMERS_URL = "/governance/account/" + ConfigLoader.getInstance().gettenant_id()+ "/get/list_customers";
	public static final String BAD_REQUEST_LIST_CUSTOMERS_URL = "governance/account/"+ ConfigLoader.getInstance().gettenant_id() + "/get/list_customer";
	public static final String INVALID_REQUEST_LIST_CUSTOMERS_URL = "/governance/account/"+ ConfigLoader.getInstance().gettenant_id() + "/list_customers";
	public static final String INVALID_REQUEST_LIST_SCRIPT_JOBS_URL = "/operation/automation/scripts/"+ ConfigLoader.getInstance().gettenant_id() + "/list";
	public static final String BAD_REQUEST_LIST_SCRIPT_JOBS_URL = "/operation/automation/scripts/"+ FakerUtils.InvalidTenantID() + "/jobs/list";
	public static final String LIST_TEMPLATE_SCHEDULES_URL = "/operations/automation/"+ ConfigLoader.getInstance().gettenant_id() + "/list?type=template";
	public static final String LIST_SCRIPT_SCHEDULES_URL = "/operations/automation/"+ ConfigLoader.getInstance().gettenant_id() + "/list?type=script";
	public static final String INVALID_REQUEST_LIST_TEMPLATE_AND_SCRIPT_SCHEDULES_URL = "/operations/automation/"+ ConfigLoader.getInstance().gettenant_id() + "";
	public static final String BAD_REQUEST_LIST_TEMPLATE_AND_SCRIPT_SCHEDULES_URL = "/operations/automation/"
			+ ConfigLoader.getInstance().gettenant_id() + "/list";
	public static final String ACCESS_TOKEN_URL = "v1/auth/tokens";

	// JSON Schema File Paths
	public static final String GET_COST_SPEND_BY_REGION_JSON_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Cost\\ExecutiveDashboard\\GetCostSpendByRegion.json";
	public static final String TOP_COST_OPTIMIZATIONS_BY_REGIONS_JSON_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Cost\\ExecutiveDashboard\\TopCostOptimizations_by_Region.json";
	public static final String GEO_COORDINATES_FOR_REGIONS_JSON_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Cost\\ExecutiveDashboard\\GeoCoordinatesForRegions.json";
	public static final String CLOUD_FORECASTER_FOR_SIXMONTHS_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Cost\\ExecutiveDashboard\\CloudForecasterForSixMonths.json";
	public static final String COST_TREND_BY_CLOUD_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Cost\\ExecutiveDashboard\\costTrendByCloud.json";
	public static final String COST_OPTIMIZATION_BY_OPTIMIZATION_TYPE_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Cost\\ExecutiveDashboard\\CostOptimizationByOptimizationType.json";
	public static final String GET_ACTUAL_OR_FORECAST_BUDGET_DRIFT_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Cost\\ExecutiveDashboard\\GetActualOrForecastBudgetDrift.json";
	public static final String GET_COST_SPEND_BY_CLOUD_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Cost\\ExecutiveDashboard\\GetCostSpendByCloud.json";
	public static final String GET_COST_SPEND_BY_CLOUD_ACCOUNTS_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Cost\\ExecutiveDashboard\\GetCostSpendByCloudAccounts.json";
	public static final String GET_COST_SPEND_BY_CLOUD_AND_RESOURCE_CATEGORY_SPEND_BY_CLOUD_RESOURCE_CATEGORY_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Cost\\ExecutiveDashboard\\GetCostSpendByCloudAndResourceCategory_spend_by_cloud_resource_category.json";
	public static final String GET_COST_SPEND_BY_CLOUD_AND_RESOURCE_CATEGORY_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Cost\\ExecutiveDashboard\\GetCostSpendByCloudAndResourceCategory.json";
	public static final String GET_COST_SPEND_BY_CLOUD_AND_TENANT_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Cost\\ExecutiveDashboard\\GetCostSpendByCloudAndTenant.json";
	public static final String GET_COST_TREND_BY_CLOUD_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Cost\\ExecutiveDashboard\\GetCostTrendByCloud.json";
	public static final String GET_TAGGED_AND_UNTAGGED_RESOURCES_BY_PRODUCT_CATEGORY_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Cost\\ExecutiveDashboard\\GettaggedAndUntaggedResourcesByProductCategory.json";
	public static final String GET_TOP_BUDGET_BY_TENANT_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Cost\\ExecutiveDashboard\\GetTopBudgetByTenant.json";
	public static final String TOP_OPTIMIZATION_BY_PRODUT_CATEGORY_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Cost\\ExecutiveDashboard\\TopOptimizationByProdutCategory.json";

	// JSON Schema File Paths for Compliance Module
	public static final String LIST_COMPLIANCE_CONTROLS_WITH_ZER0_LENGTH_JSON__SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Compliance\\ListComplianceControlsWithZeroLength.json";
	public static final String LIST_COMPLIANCE_CONTROLS_JSON__SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Compliance\\ListComplianceControls.json";
	public static final String COMPILANCE_ASSESMENT_POSTURE_SUMMARY_JSON__SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Compliance\\CompilanceAssesmentPostureSummary.json";
	public static final String LIST_COMPLIANCE_CONTROL_ASSESSMENT_HISTORY_JSON__SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Compliance\\ListComplianceControlAssessmentHistory.json";
	public static final String LIST_COMPLIANCE_CONTROL_GET_AUDIT_ENTRIES_JSON__SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Compliance\\ListComplianceControlGetonAuditEntries.json";
	public static final String LIST_COMPLIANCE_FILTER_POLICY_JSON__SCHEMA_SANDBOX = "src\\test\\resources\\JSONSchema_Source_Files\\Compliance\\ListComplainceFilterPolicysandbox.json";
	public static final String LIST_COMPLIANCE_FILTER_POLICY_JSON__SCHEMA_QA = "src\\test\\resources\\JSONSchema_Source_Files\\Compliance\\ListComplainceFilterPolicyQA.json";
	public static final String COMPLIANCE_ON_DEMAND_STANDARD_EXECUTE_JSON__SCHEMA_QA = "src\\test\\resources\\JSONSchema_Source_Files\\Compliance\\ComplianceOnDemandStandardExecuteQA.json";
	public static final String COMPLIANCE_ON_DEMAND_STANDARD_EXECUTE_JSON__SCHEMA_SANDBOX = "src\\test\\resources\\JSONSchema_Source_Files\\Compliance\\ComplianceOnDemandStandardExecuteSandbox.json";
	public static final String COMPLIANCE_POSTURE_SUMMARY_BY_ACCOUNTS_JSON__SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Compliance\\CompliancePostureSummaryByAccounts.Json";
	public static final String LIST_COMPLIANCE_CONTROL_MAPPINGS_JSON_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Compliance\\ListComplianceControlMappings.json";
	public static final String LIST_COMPLIANCE_STANDARDS_MARKETPLACE_JSON_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Compliance\\ListCompilanceStandardsMarketplace.json";
	public static final String LIST_COMPLIANCE_SUMMARY_POLICIES_JSON_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Compliance\\ListCompilancePostureSummaryByPolicies.json";
	public static final String LIST_COMPLIANCE_VISIBILITY_JSON_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Compliance\\ListComplianceVisibility.json";
	public static final String LIST_COMPLIANCE_POSTURE_DETAILS_FOR_POLICIES_JSON_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Compliance\\ListCompilancePostureDetailsForPolicies.json";
	public static final String LIST_COMPLIANCE_FILTER_JSON_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Compliance\\ListComplianceFilter.json";
	public static final String LIST_COMPLIANCE_FILTER_FOR_HEATSTACK_JSON_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Compliance\\ListComplianceFilterForHeatStack.json";
	public static final String COMPLIANCE_VISIBILITY_TREND_JSON_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Compliance\\ComplianceVisibilityTrend.json";
	public static final String COMPLIANCE_POSTURE_VISIBILITY_JSON_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Compliance\\CompliancePostureVisibility.json";

	// JSON Schema File Paths for AccountGovernance Module
	public static final String GET_ALERT_CONFIGURATIONS_POST_ONBOARDING_JSON__SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\AccountGovernance\\GetAlertConfigurationsPostOnboarding.json";
	public static final String GET_LIST_OF_CLOUD_ACCOUNT_SUMMARY_JSON_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\AccountGovernance\\GetListOfCloudAccountSummary.json";
	public static final String GET_LIST_OF_CLOUD_ACCOUNTS_JSON_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\AccountGovernance\\ListCloudAccounts.json";
	public static final String DESCRIBE_CLOUD_ACCOUNT_RESULTS_JSON_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\AccountGovernance\\DescribeCloudAccountResultsJsonSchema.json";
	public static final String LIST_CUSTOMERS_JSON_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\AccountGovernance\\ListCustomersJsonSchema.json";
	public static final String MetricSync_Frequency_Summary_JSON_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\AccountGovernance\\MetricSyncFrequencySummarySchema.json";

	// schema variable
	public static final String GET_TENANT_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Identity\\Gettenant.json";
	public static final String GET_TENANTROLES_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Identity\\Gettenantvalidtenantid.json";
	public static final String FORGETPASSWORD_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Identity\\Forgetpasswordschema.json";
	public static final String LISTOFUSERS_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Identity\\listofuserschema.json";

	// Automation module
	public static final String LIST_SCRIPT_JOBS_URL = "/operation/automation/scripts/"
			+ ConfigLoader.getInstance().gettenant_id() + "/jobs/list";
	// public static final String DESCRIBE_SCRIPT_JOBS_BADREQUES_URL=
	// "/operation/automation/scripts/"+
	// ConfigLoader.getInstance().gettenant_id()+"/get/628f4e78d4f497cd655dbc";
	public static final String DESCRIBE_SCRIPT_JOBS_BADREQUEST_URL = "/operation/automation/scripts/"
			+ ConfigLoader.getInstance().gettenant_id() + "/get/628f4e78d4f497cd655dbc1";
	public static final String DESCRIBE_SCRIPT_JOBS_NOTFOUND_URL = "/operation/automation/scripts/"
			+ ConfigLoader.getInstance().gettenant_id() + "/628f4e78d4f497cd655dbc1a";
	public static final String LIST_TEMPLATES_JSON_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Automation\\ListTemplatesjobs.json";
	public static final String GET_LIST_TEMPLATES_JSON_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Automation\\ListTemplates.json";
	public static final String DESCRIBE_TEMPLATE_JSON_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Automation\\DescribeTemplate.json";
	public static final String LIST_BLUEPRINTS_URL = "/operations/automation/blueprints/"
			+ ConfigLoader.getInstance().gettenant_id() + "/list";
	public static final String VIEW_BLUEPRINTS_BADREQUEST_URL = "/operations/automation/blueprints/"
			+ ConfigLoader.getInstance().gettenant_id() + "/get/5f564582c0ce477a1072185";
	public static final String VIEW_BLUEPRINTS_NOTFOUND_URL = "/operations/automation/blueprints/"
			+ ConfigLoader.getInstance().gettenant_id() + "/5f564582c0ce477a1072185";
	// json schema file path
	public static final String DESCRIBE_SCRIPTJOB_JSON_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Automation\\Describescriptjob.json";
	public static final String VIEW_BLUEPRINTS_JSON_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Automation\\Viewblueprints.json";
	public static final String VIEW_TEMPLATE_SCRIPR_JSON_SCHEMA_QA = "src\\test\\resources\\JSONSchema_Source_Files\\Automation\\ViewTemplateScriptSchedulesQA.json";
	public static final String VIEW_TEMPLATE_SCRIPR_JSON_SCHEMA_SDBOX = "src\\test\\resources\\JSONSchema_Source_Files\\Automation\\ViewTemplateScriptSchedulesSdbox.json";
	public static final String VIEW_TEMPLATE_JOB = "src\\test\\resources\\JSONSchema_Source_Files\\Automation\\ViewTemplateJob.json";

	// Cost listreservation module
	public static final String COST_LISTRESERVATIONUTILIZATION_URL = "/cost/ri/utilization/reservations?date_filter=current_month";

	// JSON Schema File Paths for AccountGovernance Module
	public static final String GET_LIST_OF_SCRIPTS_JSON__SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Automation\\ListOfScripts.json";
	public static final String GOVERANCE_ASSESSMENT_RESULTS_JSON_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\AccountGovernance\\GoveranceAllAssessmentResults.json";

	// Operations module

	public static final String LIST_ACTIVTY_INSIGHTS_USER = "src\\test\\resources\\JSONSchema_Source_Files\\Operations\\ListActivtyInsightsUser.json";
	public static final String LIST_ACTIVTY_INSIGHTS_CATEGORY = "src\\test\\resources\\JSONSchema_Source_Files\\Operations\\ListActivtyInsightsCategory.json";
	public static final String LIST_METRIC_ANOMALIES_CLUD_ACC = "src\\test\\resources\\JSONSchema_Source_Files\\Operations\\ListMetricAnomaliesCloudAccount.json";
	public static final String LIST_ANOMALIES_IMPACTED_RESOURCES = "src\\test\\resources\\JSONSchema_Source_Files\\Operations\\ListAnomaliesImpactedResources.json";
	public static final String LIST_OPERATION_POSTURE_DETAILS = "/operation/posture/dashboard_summary/list";
	public static final String LIST_METRIC_ANOMALIES_CATEGORY = "src\\test\\resources\\JSONSchema_Source_Files\\Operations\\ListMetricAnomaliesCategory.json";
	public static final String GET_ACTIVITY_CONFIGURATION_SUMMARY = "/operations/activity/"
			+ ConfigLoader.getInstance().gettenant_id() + "/get_configuration_summary?action=summary&";
	public static final String GET_NOTIFICATION_BASED_ONACTIVITY_CONFIGURATION_SUMMARY = "/operations/activity/5fda041fc0ce4702bdb06692/get_notification/6274cbfe2f7ab6a701beaf30";
	public static final String LIST_OPERATIONS_ALERT_CONF_CLUD_ACC_JSN_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Operations\\ListOperationsAlertConfgCludAccount.json";
	public static final String GET_SERVICE_DETAILS_ALERT_CONFG = "src\\test\\resources\\JSONSchema_Source_Files\\Operations\\GetServiceDetailsOprtnsAlertConfg.json";
	public static final String GET_NOTIFICATIONS_BASED_ON_ACTIVITY_CONFIGURATION = "src\\test\\resources\\JSONSchema_Source_Files\\Operations\\GetNotificationBasedOnActivityConfiguration.json";
	public static final String LIST_OPERATIONS_ALERT_CONF_TEMPLATES_JSN_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Operations\\ListOperationsAlertConfgTemplates.json";
	public static final String LIST_OPERATION_POSTURE_DETAILS_NOTFOUNDREQUEST= "/operation/posture/dashboard_summary/";
	public static final String LIST_OPERATION_POSTURE_DETAILS_BADREQUEST="/operation/posture/dashboard_summary";

	// Security Module

	public static final String LIST_SECURITY_VULNERABILITY_DETAILS_URL = "/security/posture/visibility/"
			+ TENANT_ID_VALUE + "/get/vulnerability";
	public static final String LIST_SECURITY_VISIBILITY_DETAILS_URL = "/security/posture/visibility/" + TENANT_ID_VALUE
			+ "/get/";
	public static final String LIST_SECURITY_POSTURE_DETAILS_URL = "/security/posture/" + TENANT_ID_VALUE
			+ "/get/securityops";

	// Cost Module
	public static final String GET_TENANT_DETAILS_URL = "/cost/costoptimizer/optimizeusage/tenant";
	public static final String GET_LIST_BUDGETS_URL = "/budget/dashboard/list_budgets";
	public static final String COST_LIST_BUDGETS_PATH = "src\\test\\resources\\Payloads\\Cost\\List_Budgets.json";
	public static final String COST_COUNT_BUDGET_SUMMARY = "src\\test\\resources\\Payloads\\Cost\\View_Count_Budget_Summary.json";
	public static final String COST_VIEW_BUDGET_DASHBOARD = "src\\test\\resources\\Payloads\\Cost\\View_Budget_Dashboard.json";
	public static final String COST_BUDGET_FILTER_VALUES = "src\\test\\resources\\Payloads\\Cost\\Budget_Filter_Values.json";
	public static final String COST_LIST_SAVINGS_ACC_SUMMARY = "src/test/resources/Payloads/Cost/List_Cost_Savings_Account_Summary.json";
	public static final String COST_LIST_SAVINGS_RECOMMENDATION = "src/test/resources/Payloads/Cost/List_Cost_Savings_Recommendation.json";
	public static final String COST_CREATE_BUDGET = "src/test/resources/Payloads/Cost/Create_Budget.json";
	public static final String COST_CLOUD_ACCOUNT_SUMMARY = "src/test/resources/Payloads/Cost/Cloud_Account_Summary.json";
	public static final String COST_MARKUP_RULE = "src/test/resources/Payloads/Cost/Cost_Markup_Rule.json";
	public static final String COST_OVERALL_CLOUD_ACCOUNT_SUMMARY_QA = "src/test/resources/Payloads/Cost/OverallCloudAccountSummary/Overall_Cloud_account_summary_qa.json";
	public static final String COST_OVERALL_CLOUD_ACCOUNT_SUMMARY_SANDBOX = "src/test/resources/Payloads/Cost/OverallCloudAccountSummary/Overall_Cloud_account_summary_sandbox.json";
	public static final String COST_OVERALL_CLOUD_ACCOUNT_SUMMARY_DEV3 = "src/test/resources/Payloads/Cost/OverallCloudAccountSummary/Overall_Cloud_account_summary_dev3.json";
	public static final String COST_LIST_DASHBOARD_SUMMARY_ACCOUNT = "src/test/resources/Payloads/Cost/List_Dashboard_Summary.json";
	public static final String CREATE_ALERT_CONFIGURATION_POST_ONBOARDING = "src\\test\\resources\\Payloads\\AccountGovernance\\CreateAlertConfigurationPostOnboarding.json";
	public static final String COST_LICENSE_BENIFIT = "src/test/resources/Payloads/Cost/LicenseBenifit.json";
	public static final String COST_LICENSE_BENIFIT_SAVING_REQUEST = "src/test/resources/Payloads/Cost/LicenseBenifitSavReq.json";
	public static final String COST_FORECAST_BUDGET = "src/test/resources/Payloads/Cost/ForecastBudget.json";
	
	public static final String LIST_COST_RESERVATIONUTILIZATION_URL = "/cost/ri/utilization/reservations?date_filter=current_month";
	public static final String LIST_COST_RESERVATION_UTLIZATION_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Cost\\ExecutiveDashboard\\Listreservationutilization.json";

	// Json Schema path for Cost Savings Module
	public static final String LIST_COST_SAVINGS_RECOMMENDATIONS_JSON_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Cost\\CostSavings\\ListCostSavingRecommendations.json";
	public static final String VIEW_COST_SAVINGS_RECOMMENDATIONS_JSON_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Cost\\CostSavings\\ViewCostSavingRecommendations.json";

	// Reports module
	public static final String ComplianceControl_Mapping_Report_URL = "/compliance/" + TENANT_ID_VALUE
			+ "/compliance_control_mapping/compliance_assessment_summary";

	//Resource
	public static final String LIST_BUSINESS_APPLICATION = "src\\test\\resources\\JSONSchema_Source_Files\\Resource\\ListBusinessApplications.json";
	public static final String GET_CLOUD_ACCOUNT_DETAILS_OF_TENANT = "src\\test\\resources\\JSONSchema_Source_Files\\Resource\\GetCloudAccountDetails.json";
	public static final String LIST_COST_CENTER_IN_BUSINESS_APPLICATION = "/resources/business_applications/cost_center/list/" + TENANT_ID_VALUE;
	public static final String LIST_ENVIRONMENT_IN_BUSINESS_APPLICATION = "/resources/business_applications/environment/list/" + TENANT_ID_VALUE;
	public static final String VIEW_ENVIRONMENT_IN_BUSINESS_APPLICATION = "/resources/business_applications/environment/" + TENANT_ID_VALUE + "/get/";
	public static final String LIST_TAG_PATTERN_IN_BUSINESS_APPLICATION = "/resources/business_applications/tag_pattern/list/" + TENANT_ID_VALUE+"/";
	public static final String VIEW_TAG_PATTERN_IN_BUSINESS_APPLICATION = "/resources/business_applications/tag_pattern/" + TENANT_ID_VALUE+"/get/"; // +tag_pattern_id
	
	public static final String Create_Application_Business_Application_PATH ="src\\test\\resources\\Payloads\\Resource\\createBusinessApplication.json";
	public static final String Get_Inventory_Count_Payload_PATH ="src\\test\\resources\\Payloads\\Resource\\GetInventoryCount.json";
	public static final String List_Inventory_Filter_Payload_PATH ="src\\test\\resources\\Payloads\\Resource\\ListInventoryFilter.json";
	public static final String GetInventoryDetails_Payload_PATH ="src\\test\\resources\\Payloads\\Resource\\GetInventoryDetails.json";
	public static final String GetResourceListing_Payload_PATH ="src\\test\\resources\\Payloads\\Resource\\GetResourceListing.json";
	public static final String CreateResourceLockConfigRule_Payload_PATH ="src\\test\\resources\\Payloads\\Resource\\CreateResourceLockConfigRule.json";
	public static final String ListResourcesTagsFilter_Payload_PATH ="src\\test\\resources\\Payloads\\Resource\\ListResourcesTagsFilter.json";
	public static final String ListTagsfromResources_Payload_PATH ="src\\test\\resources\\Payloads\\Resource\\ListTagsfromResources.json";
	public static final String GetResourceTagRemediationStatus_Payload_PATH ="src\\test\\resources\\Payloads\\Resource\\GetResourceTagRemediationStatus.json";
	public static final String CreateResourceTagRemediation_Payload_PATH ="src\\test\\resources\\Payloads\\Resource\\CreateResourceTagRemediation.json";
	public static final String CreateResourceTagConfigRule_Payload_PATH ="src\\test\\resources\\Payloads\\Resource\\CreateResourceTagConfigRule.json";
	public static final String CreateApplicationGroupBusinessApplication_PATH ="src\\test\\resources\\Payloads\\Resource\\CreateApplicationGroupBusinessApplication.json";
	public static final String Create_CostCenter_Business_Application_PATH ="src\\test\\resources\\Payloads\\Resource\\CreateCostCenterGroupBusinessApplication.json";
	public static final String Create_Environment_Business_Application_PATH ="src\\test\\resources\\Payloads\\Resource\\CreateEnvironmentBusinessApplication.json";
	public static final String Create_TagPattern_Business_Application_PATH ="src\\test\\resources\\Payloads\\Resource\\CreateTagPatternBusinessApplication.json";
	
	// SelfService module
	public static final String SELFSERVICE_CUSTOMER_DASHBOARD_URL = "/self_service/dashboard/" + TENANT_ID_VALUE;
	public static final String SELFSERVICE_ORDER_HISTORY_URL = "/self_service/orders/" + TENANT_ID_VALUE;
	public static final String SELFSERVICE_LIST_RESOURCE_CATALOG_URL = "/self_service/resource_catalogs/" + TENANT_ID_VALUE+"/list";
	public static final String SELFSERVICE_LIST_WORKSPACE_URL = "/self_service/workspace/" + TENANT_ID_VALUE;
	public static final String SELFSERVICE_CREATE_SERVICE_CATALOG_URL = "/self_service/" + TENANT_ID_VALUE+"/catalog";
	
	public static final String SELFSERVICE_CREATE_RESOURCE_CATALOG_URL = "/self_service/resource_catalogs/"+ TENANT_ID_VALUE+"/create";
	public static final String SELFSERVICE_UPDATE_RESOURCE_CATALOG_URL = "/self_service/resource_catalogs/" + TENANT_ID_VALUE;
	public static final String SELFSERVICE_DELETE_RESOURCE_CATALOG_URL = "/self_service/resource_catalogs/" + TENANT_ID_VALUE;
	public static final String SELFSERVICE_DELETE_SERVICE_CATALOG_URL = "/self_service/"+ TENANT_ID_VALUE+"/catalog";

	public static final String Create_Resource_Cata1og_PATH ="src\\test\\resources\\Payloads\\Selfservice\\createResourceCatalog.json";
	
	//Access module
	public static final String GET_ACCESS_SUMMARY_COUNT_URL = "/access/posture/governance_summary/list";
	public static final String GET_ACCESS_SUMMARY_COUNT_URL_NOT_FOUND = "/access/posture/governance_/list";
	public static final String GET_ACCESS_VIOLATIONS_URL = "/access/posture/view_violations";
	public static final String GET_ACCESS_VIOLATIONS_URL_NOT_FOUND = "/access/posture/view_";
	public static final String GET_ACCESS_VISIBILITY_FILTER_URL = "/access/visibility/filter";
	public static final String GET_ACCESS_VISIBILITY_FILTER_URL_NOT_FOUND = "/access/visibility/_filter";
	public static final String GET_ACCESS_SUMMARY_COUNT_AWS_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Access\\Get_Access_Summary_Count_AWS.json";
	public static final String GET_ACCESS_SUMMARY_COUNT_AZURE_GCP_OCI_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Access\\Get_Access_Summary_Count_Azure_GCP_OCI.json";
	public static final String GET_ACCESS_VIOLATIONS_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Access\\Get_Access_Violations_schema.json";
	public static final String GET_ACCESS_VISIBILITY_FILTER_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\Access\\Get_Access_Visibility_Filter.json";
	
	//Recommendation
	public static final String Resolve_Definition_Recommendations_URL = "/recommendations/{definition_id}/resolve_preexecute";
	public static final String LIST_RECOMMENDATION_BY_ACCOUNT = "src\\test\\resources\\JSONSchema_Source_Files\\Recommendation\\ListRecommendationbyAccount.json";
	public static final String LIST_RECOMMENDATIONS = "src\\test\\resources\\JSONSchema_Source_Files\\Recommendation\\ListRecommendations.json";
	public static final String Resolve_Definition_Recommendations_Payload = "src\\test\\resources\\Payloads\\Recommendation\\resolve_definition_recommendations.json";
	
	//Dashboard Definition module
	public static final String GET_ALL_DASHBOARD_DEFINITIONS_URL = "/v1/executive_dashboard/all";
	public static final String GET_DASHBOARD_DEFINITION_URL = "/v1/executive_dashboard/item/";
	public static final String GET_ACTIVE_DASHBOARD_DEFINITION_URL = "/v1/executive_dashboard/active";
	public static final String LIST_ALL_ACCESSIBLE_PORTALS = " /v1/executive_dashboard/portals/list";
	public static final String RESOLVE_PORTAL_PATH_DASHBOARD_DEFINITION = "/v1/executive_dashboard/portals/resolve";

	//Dashboard Definition Versions module
	public static final String GET_DASHBOARD_DEFINITION_VERSION_URL = "/v1/executive_dashboard_version/item/";
	
	// Well Architechted Framework (WAF)
	public static final String FILE_UPLOAD_SAMPLE1 = "src\\test\\resources\\Payloads\\Waf\\SampleDocForUpload1.docx";
	public static final String WAF_README_FILE_JSON_SCHEMA = "src\\test\\resources\\JSONSchema_Source_Files\\WAF\\ReadMe_Files.json";
	public static final String WAF_CREATE_WORKLOAD = "src\\test\\resources\\Payloads\\Waf\\CreateWorkloadPayload.json";
	public static final String WAF_UPDATE_WORKLOAD = "src\\test\\resources\\Payloads\\Waf\\UpdateWorkloadPayload.json";
	
	
	//404 error message
	public static final String Error_Msg_404 = "The requested URL was not found on the server. If you entered the URL manually please check your spelling and try again.";
	
	public static final String PATH_ONBOARD_AZURE_ACCOUNT = "src\\test\\resources\\Payloads\\AccountGovernance\\Onboarding_Azure_Account_Payload.json";
	public static final String PATH_ONBOARD_AWS_ACCOUNT = "src\\test\\resources\\Payloads\\AccountGovernance\\Onboarding_Aws_Account_Payload.json";
	public static final String PATH_ONBOARD_GCP_ACCOUNT = "src\\test\\resources\\Payloads\\AccountGovernance\\Onboarding_GCP_Account_Payload.json";
	public static final String PATH_ONBOARD_OCI_ACCOUNT = "src\\test\\resources\\Payloads\\AccountGovernance\\Onboarding_OCI_Account_Payload.json";
	public static final String ONBOARD_CLOUD_ACCOUNT_URL = "governance/account/" + TENANT_ID_VALUE + "/create/cloud_accounts";
	public static final String PATH_GET_RESOURCE_LIST_PAYLOAD = "src\\test\\resources\\Payloads\\Inventory\\GetResourceList.json";
	public static final String PATH_AZURE_GET_RESOURCE_LIST_PAYLOAD = "src\\test\\resources\\Payloads\\Inventory\\Azure_GetResourceList.json";
	public static final String PATH_ADD_TAG_PAYLOAD = "src\\test\\resources\\Payloads\\Inventory\\Azure_AddTag_Instance.json";
	public static final String PATH_GET_CLOUD_ACCOUNTS_INTERNAL_API = "src\\test\\resources\\Payloads\\Inventory\\Internal_Get_Cloud_Account.json";
	public static final String PATH_UPDATE_USER = "src\\test\\resources\\Payloads\\Identity\\UpdateUserPayload.json";
	public static final String PATH_CREATE_POLICY_SCHEDULE = "src\\test\\resources\\Payloads\\Guardrails\\CreatePolicySchedule.json";
	public static final String PATH_LIST_RECOMMENDATIONS = "src\\test\\resources\\Payloads\\Cost\\ListRecommendations.json";
	public static final String PATH_CREATE_POLICY = "src\\test\\resources\\Payloads\\Guardrails\\CreatePolicy.json";
	public static final String PATH_EXECUTE_POLICY = "src\\test\\resources\\Payloads\\Guardrails\\ExecutePolicy.json";
	public static final String PATH_EXECUTE_POLICY_RECOMMENDATION = "src\\test\\resources\\Payloads\\Guardrails\\ExecutePolicyRecommendation.json";
	public static final String PATH_GET_ASSESSMENT_JOB_DETAILS = "src\\test\\resources\\Payloads\\Compliance\\GetAssessmentJobDetails.json";
	public static final String PATH_VALDIATE_AUTH_CREDENTIALS_AWS = "src\\test\\resources\\Payloads\\AccountGovernance\\AWS_Validate_Auth_Credentials.json";
	public static final String PATH_VALDIATE_AUTH_CREDENTIALS_Azure = "src\\test\\resources\\Payloads\\AccountGovernance\\Azure_Validate_Auth_Credentials.json";
	public static final String PATH_VALDIATE_AUTH_CREDENTIALS_GCP = "src\\test\\resources\\Payloads\\AccountGovernance\\GCP_Validate_Auth_Credentials.json";
	public static final String PATH_VALDIATE_AUTH_CREDENTIALS_OCI = "src\\test\\resources\\Payloads\\AccountGovernance\\OCI_Validate_Auth_Credentials.json";
	
	//Inventory Payloads
	public static final String	PATH_UTILIZATION_API = "src\\test\\resources\\Payloads\\Inventory\\Inventory_Resources_Utilization_data.json";
	public static final String	PATH_GCP_ACTION_START_API = "src\\test\\resources\\Payloads\\Inventory\\Inventory_GCP_Action_Start.json";
	
	
	
	//Wrapper Locks and Tags API
	public static final String PATH_CREATE_RESOURCE_LOCKCONFIG_RULE = "src\\test\\resources\\Payloads\\Inventory\\CreateResourceLockConfigRule.json";
	public static final String PATH_CREATE_RESOURCE_TAGCONFIG_RULE = "src\\test\\resources\\Payloads\\Inventory\\CreateResourceTagConfigRule.json";
	public static final String PATH_LIST_TAGS_FROM_RESOURCES = "src\\test\\resources\\Payloads\\Inventory\\ListTagsfromResources.json";
	public static final String PATH_LIST_TAGS_TENANTS_FROM_RESOURCES = "src\\test\\resources\\Payloads\\Inventory\\ListTagsTenantsfromResources.json";
	public static final String PATH_GET_RESOURCE_TAG_REMEDIATION_STATUS = "src\\test\\resources\\Payloads\\Inventory\\GetResourceTagRemediationStatus.json";
	
}
