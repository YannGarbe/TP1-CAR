package readTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import exception.NonExistentFileException;
import misc.globalData;
import read.ReadFileConfiguration;


import java.io.IOException;
import java.util.HashMap;

public class ReadFileConfigurationTest {

	private ReadFileConfiguration readConfig;
	
	@Before
	public void setUp() throws NonExistentFileException, IOException {
		this.readConfig = new ReadFileConfiguration(globalData.DEFAULT_CONFIG_PATH);
		globalData.resetData();
	}
	
	@Test
	public void test_read_configuration_when_initialisation_successfull(){
		assertEquals(globalData.DEFAULT_CONFIG_PATH, readConfig.getPath());
	}
	
	@Test(expected=NumberFormatException.class)
	public void test_set_configuration_when_error_port_value(){
		HashMap <String, String> configMap = new HashMap <String, String>();
		configMap.put("PORT", "ERROR");
		
		readConfig.setDataMap(configMap);
		readConfig.setConfigurationData();
	}
	
	@Test
	public void test_set_configuration_when_bad_port_value(){
		HashMap <String, String> configMap = new HashMap <String, String>();
		configMap.put("PORT", "1000");
		
		readConfig.setDataMap(configMap);
		readConfig.setConfigurationData();
		
		assertEquals(globalData.DEFAULT_PORT, globalData.used_port);
	}
	
	@Test
	public void test_set_configuration_when_good_port_value(){
		HashMap <String, String> configMap = new HashMap <String, String>();
		configMap.put("PORT", "1968");
		
		readConfig.setDataMap(configMap);
		readConfig.setConfigurationData();
		
		assertEquals(1968, globalData.used_port);
	}
	
	@Test
	public void test_set_configuration_when_bad_userPath_value(){
		
		HashMap <String, String> configMap = new HashMap <String, String>();
		configMap.put("HOME", "BAD");
		
		readConfig.setDataMap(configMap);
		readConfig.setConfigurationData();
		
		assertEquals(globalData.DEFAULT_USER_FILE_SYSTEM, globalData.user_file_system);
	}
	
	@Test
	public void test_set_configuration_when_good_userPath_value(){
		HashMap <String, String> configMap = new HashMap <String, String>();
		configMap.put("HOME", "/");
		
		readConfig.setDataMap(configMap);
		readConfig.setConfigurationData();
		
		assertEquals("/", globalData.user_file_system);
	}
}
