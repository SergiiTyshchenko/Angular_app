def defaultEnvironment(){


	def jobType
	def SCRIPTS_DIR
	def app_DEPLOY
	def CATALINA_HOME
	

		jobType = "_CI"	

	
	def appHome = "/opt/apache-tomcat-8.5.5/webapps/AreaDTool"	
	def javaHome
	def antHome

	if ("${jobType}" == "Deploy") {
		javaHome = "/usr/lib/jvm/java-7-oracle/jre"
		//antHome = "${appHome}/bin/platform/apache-ant-1.8.2"
	} else {
		javaHome = "/usr/lib/jvm/java-8-oracle/jre"
		//antHome = "${appHome}/bin/platform/apache-ant-1.9.1"
	}
	

	["PATH=${env.PATH}:${javaHome}/bin:${antHome}/bin:${appHome}:${SCRIPTS_DIR}:${app_DEPLOY}:${CATALINA_HOME}",
	"JAVA_HOME=${javaHome}", "ANT_HOME=${antHome}", "ANT_OPTS=-Xms1024m -Xmx2048M -XX:PermSize=600m -XX:MaxPermSize=1024m", "app_HOME=${appHome}",
	"SCRIPTS_DIR=${SCRIPTS_DIR}", "CATALINA_HOME=${CATALINA_HOME}", "app_DEPLOY=${app_DEPLOY}"]
}


return this;