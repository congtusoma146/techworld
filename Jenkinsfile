pipeline{

	agent none // biến toàn cục
	environment{
		DOCKER_IMAGE = "congtusoma146/test-jenkins"
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