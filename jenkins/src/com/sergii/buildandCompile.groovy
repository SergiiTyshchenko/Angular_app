	def environmentDetails () {

		def buildEnv = load 'new_strategy/vars/systemEnvironment.groovy'		
		
		withEnv(buildEnv.defaultEnvironment()) {
					
			println "***************Get Environment Details*****************"
			println "Java Path:" + "${JAVA_HOME}"					
			println "Java version: "
			sh "java -version 2>&1 | head -n 1 | awk -F '\"' '{print \$2}'"
			println "Ant Path:" + "${ANT_HOME}"
			println "Ant version: "
			sh "ant -version 2>&1 | tail -1 | awk '{print \$4}'"	
		}
	}





	def configureForCI (MAVEN_COMMAND) {

		def buildEnv = load 'new_strategy/vars/systemEnvironment.groovy'		
		
		withEnv(buildEnv.defaultEnvironment()) {
			
			println "***************Run Ant to Configure Profile*****************"			
			//sh "chmod +x /scripts/*.sh"
			//sh "rm -rf ${WORKSPACE}/config/*"		
			//TODO: hybris needs to be packaged and copied here			
			//sh "cd ${APP_HOME}/bin/platform"
			sh "${MAVEN_COMMAND}"
		}
	}



return this;