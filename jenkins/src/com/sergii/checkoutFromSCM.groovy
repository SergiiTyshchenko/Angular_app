def checkoutFromRepository(creds, url) {

	
checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: "${creds}", url: "${url}"]]])
		
}		

return this;	