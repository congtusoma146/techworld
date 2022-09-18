pipeline{

	agent any // biến toàn cục
	/* environment{
		DOCKER_IMAGE = "congtusoma146/techworld" */
}
	stages {

		statge ("build"){
			steps {
				echo 'Hello build'
		}
		stage("test")
		{
			/* agent 
			{
				docker {
					image 'node:13-alpine'
					args '-u 0:0 -v /tmp:/root/.cache'
					}
				
			} */
			steps {
				echo 'Hello test'
				/* sh "pip npm install"
				sh "npm install" */
				/* sh "npm run test" */
			}
		}
		statge ("deploy"){
			steps {
				echo 'Hello deploy'
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