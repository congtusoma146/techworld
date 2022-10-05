pipeline{

	agent none 
	
	stages {

		stage('Build'){
			steps {
				cd ~/app
				sh 'npm install'
				sh 'node server.js'
			}
		}
		stage('Test')
		{
			steps {
				echo 'Hello test.....'
			}
		}
		stage('Deploy'){
			steps {
				echo 'Hello deploy.....'
			}
		}
	}
}