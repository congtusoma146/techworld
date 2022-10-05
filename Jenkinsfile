pipeline{

	agent any 
	
	stages {

		stage("build")
		{
			agent{
				docker {
					image 'node:13-alpine'
					/* args '-u 0:0 -v /tmp:/root/.cache' */
				}
			}
			steps {
				cd ~/app
				sh 'npm install'
				sh 'node server.js'				
			}
			post {
				failure {
					echo "build error"
				}
			}
		}
	}
}