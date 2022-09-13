pipeline{

	agent none // biến toàn cục
	environment{
		DOCKER_IMAGE = "congtusoma146/test-jenkins",
		ENV MONGO_DB_USERNAME=admin \
    	MONGO_DB_PWD=password
}
	stages {
		stage("Test")
		{
			agent 
			{
				docker {
					image 'node:13-alpine'
					args '-u 0:0 -v /tmp:/root/.cache'
					}
				
			}
			steps {
				sh "pip install npm"
				sh "install npm"
				sh "npm run test"
			}
		}
		}
post {
    success {
      echo "SUCCESSFUL"
    }
    failure {
      echo "FAILED"
    }
  }
}