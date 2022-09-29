pipeline{

	agent none 

	environment {
		DOCKER_IMAGE = "congtusoma146/techworld"
	}
	
	stages {

		stage("Test")
		{
			agent{
				docker {
					image 'node:13-alpine'
					args '-u 0:0 -v /tmp:/root/.cache'
				}
			}
			steps {
				sh "pip instal npm"
				sh "npm install"				
			}
		}

		stage('Build'){
			steps {
				echo 'Hello build......'
			}
		}
		
		stage('Deploy'){
			steps {
				echo 'Hello deploy.....'
			}
		}
	} 
}