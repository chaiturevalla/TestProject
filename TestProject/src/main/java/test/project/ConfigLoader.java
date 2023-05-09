package test.companyName.api.utils;

import java.io.File;
import java.util.Properties;

public class ConfigLoader {
	private final Properties properties;
	private static ConfigLoader configLoader;
	private String environment;
	Properties propertyFile = PropertyUtils.propertyLoader("src/test/resources/config.properties");

	private ConfigLoader() {

		if (System.getProperty("tier") != null && !System.getProperty("tier").isEmpty()) {
			propertyFile.setProperty("tier", System.getProperty("tier"));
			environment = propertyFile.getProperty("tier");
			System.out.println("Environment set from System Variable as " + environment);

		} else {
			environment = propertyFile.getProperty("tier");
			System.out.println("Environment set to default property file as" + environment);
		}

		if (environment.equalsIgnoreCase("sandbox")) {
			properties = PropertyUtils.propertyLoader("src" + File.separator + "test" + File.separator + "resources"
					+ File.separator + "Environments" + File.separator + "env.sandbox.properties");
			properties.setProperty("tier", "sandbox");
			System.out.println("Environment set to Sandbox ");
		} 
		else if (environment.equalsIgnoreCase("qa")) {
			properties = PropertyUtils.propertyLoader("src" + File.separator + "test" + File.separator + "resources"
					+ File.separator + "Environments" + File.separator + "env.qa.properties");
			properties.setProperty("tier", "qa");
			System.out.println("Environment set to QA ");
		}			
		  else if (environment.equalsIgnoreCase("dev3")) {
				properties = PropertyUtils.propertyLoader("src" + File.separator + "test" + File.separator + "resources"
						+ File.separator + "Environments" + File.separator + "env.dev3.properties");
				properties.setProperty("tier", "dev3");
				System.out.println("Environment set to dev3 ");
		} 
		  else if (environment.equalsIgnoreCase("dev2")) {
				properties = PropertyUtils.propertyLoader("src" + File.separator + "test" + File.separator + "resources"
						+ File.separator + "Environments" + File.separator + "env.dev2.properties");
				properties.setProperty("tier", "dev2");
				System.out.println("Environment set to dev2 ");
		} 
		  else if (environment.equalsIgnoreCase("production")) {
			properties = PropertyUtils.propertyLoader("src" + File.separator + "test" + File.separator + "resources"
					+ File.separator + "Environments" + File.separator + "env.production.properties");
			properties.setProperty("tier", "production");
			System.out.println("Environment set to Production ");
		} 
		 else if (environment.equalsIgnoreCase("internalwafsandbox")) {
			properties = PropertyUtils.propertyLoader("src" + File.separator + "test" + File.separator + "resources"
					+ File.separator + "Environments" + File.separator + "env.internalwafsandbox.properties");
			properties.setProperty("tier", "internalwafsandbox");
			System.out.println("Environment set to internalwafsandbox ");

		} 
		 else if (environment.equalsIgnoreCase("internalwafqa")) {
				properties = PropertyUtils.propertyLoader("src" + File.separator + "test" + File.separator + "resources"
						+ File.separator + "Environments" + File.separator + "env.internalwafqa.properties");
				properties.setProperty("tier", "internalwafqa");
				System.out.println("Environment set to internalwafqa ");
			}
		
		 else if (environment.equalsIgnoreCase("internalinventoryqa")) {
				properties = PropertyUtils.propertyLoader("src" + File.separator + "test" + File.separator + "resources"
						+ File.separator + "Environments" + File.separator + "env.internalInventoryqa.properties");
				properties.setProperty("tier", "internalinventoryqa");
				System.out.println("Environment set to internalinventoryqa ");
			}
		 else {
			properties = PropertyUtils.propertyLoader("src" + File.separator + "test" + File.separator + "resources"
					+ File.separator + "Environments" + File.separator + "env.qa.properties");
			properties.setProperty("tier", "qa");
			System.out.println("Environment set to QA ");

		}
	}

	public static ConfigLoader getInstance() {
		if (configLoader == null) {
			configLoader = new ConfigLoader();
		}
		return configLoader;
	}

	public String getAuthURI() {
		String prop = properties.getProperty("authURI");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property tokenURL is not specified in the config.properties file");
	}

	public String getEnvironment() {
		String prop = properties.getProperty("tier");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property tokenURL is not specified in the config.properties file");
	}

	public String getBaseURL() {
		String prop = properties.getProperty("baseURL");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property tokenURL is not specified in the config.properties file");
	}

	public String getBasePath() {
		String prop = properties.getProperty("basePath");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property basePath is not specified in the config.properties file");
	}

	public String getAuthBasePath() {
		String prop = properties.getProperty("baseAuthPath");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property basePath is not specified in the config.properties file");
	}

	public String getXAuthUser() {
		String prop = properties.getProperty("X-Auth-User");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property X-Auth-User is not specified in the config.properties file");
	}

	public String getLogFilePath() {
		String prop = properties.getProperty("logConfigFile");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property logConfigFile is not specified in the config.properties file");
	}

	public String getaccess_key() {
		String prop = properties.getProperty("access_key");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property access_key is not specified in the config.properties file");
	}

	public String getSecret_key() {
		String prop = properties.getProperty("secret_key");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property secret_key is not specified in the config.properties file");
	}

	public String getaccount_id() {
		String prop = properties.getProperty("account_id");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property account_id is not specified in the config.properties file");
	}

	public String gettenant_id() {
		String prop = properties.getProperty("tenant_id");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property tenant_id is not specified in the config.properties file");
	}
	
	public String getEnvironmentId() {
		String prop = properties.getProperty("environment_Id");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property environment_Id is not specified in the config.properties file");
	}
	
	public String getTagPatternID() {
		String prop = properties.getProperty("tagPattern_ID");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property environment_Id is not specified in the config.properties file");
	}
	
	public String getuser_id() {
		String prop = properties.getProperty("user_id");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property user_id is not specified in the config.properties file");
	}
	
	public String getWAFBaseURL() {
		String prop = properties.getProperty("wafBaseURL");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property wafBaseURL is not specified in the config.properties file");
	}
	
	public String getowner_id() {
		String prop = properties.getProperty("owner_id");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property owner_id is not specified in the config.properties file");
	}
	
	public String getapprover_id() {
		String prop = properties.getProperty("approver_id");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property approver_id is not specified in the config.properties file");
	}
	public String getwaf_cloudaccount_id() {
		String prop = properties.getProperty("waf_cloudaccount_id");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property waf_cloudaccount_id is not specified in the config.properties file");
	}
	
	public String getwaf_cloudprovider() {
		String prop = properties.getProperty("waf_cloudprovider");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property waf_cloudprovider is not specified in the config.properties file");
	}
	
	public String getInternalBaseURL() {
		String prop = properties.getProperty("InternalBaseURL");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property InternalBaseURL is not specified in the config.properties file");
	}
	
	public String getInventoryBaseURL_Tags() {
		String prop = properties.getProperty("InventoryBaseURL_Tags");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property InventoryBaseURL_Tags is not specified in the config.properties file");
	}
	
	public String getInventoryPortID_Tags() {
		String prop = properties.getProperty("InventoryPortID_Tags");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property InventoryPortID_Tags is not specified in the config.properties file");
	}
	
	public String getpassword() {
		String prop = properties.getProperty("password");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property password is not specified in the config.properties file");
	}
	
	public String getUpdateFname() {
		String prop = properties.getProperty("UpdateFname");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property UpdateFname is not specified in the config.properties file");
	}
	
	public String getUpdateLname() {
		String prop = properties.getProperty("UpdateLname");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property UpdateLname is not specified in the config.properties file");
	}
	
	public String getUpdateEmailId() {
		String prop = properties.getProperty("UpdateEmailId");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property UpdateEmailId is not specified in the config.properties file");
	}
	
	public String getUpdateUserid() {
		String prop = properties.getProperty("UpdateUserid");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property UpdateUserid is not specified in the config.properties file");
	}
	
	public String getResetEmailid() {
		String prop = properties.getProperty("ResetEmailId");
		if (prop != null)
			return prop;
		else
			throw new RuntimeException("property ResetEmailId is not specified in the config.properties file");
	}
}
