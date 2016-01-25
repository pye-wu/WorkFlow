package junit;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

public class makeDatabse {

	/**
	 * 使用代码创建工作流需要的23张表
	 */
	@Test
	public void makedb(){
		ProcessEngineConfiguration csr = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		//链接数据库的配置
		csr.setJdbcDriver("com.mysql.jdbc.Driver");
		csr.setJdbcUrl("jdbc:mysql://localhost:3306/activit");
		csr.setJdbcUsername("root");
		csr.setJdbcPassword("123");
		/**
		 * 允许代码创建数据库
		 * ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE 如果表不存在自动创建表
		 */
		csr.setDatabaseSchema(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		ProcessEngine pe= csr.buildProcessEngine();
		System.out.println(pe.getName());
	}
	
}
