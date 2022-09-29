pipeline{

	agent none 
	
	stages {

		stage('Test')
		{
			agent{
				docker {
					image 'node:13-alpine'
					args '-u 0:0 -v /tmp:/root/.cache'
				}
			}
			steps {
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