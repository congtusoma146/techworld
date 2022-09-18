pipeline{

	agent any // biến toàn cục
	environment{
		DOCKER_IMAGE = "congtusoma146/techworld"
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
				echo 'Hello world'
				/* sh "pip npm install"
				sh "npm install" */
				/* sh "npm run test" */
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