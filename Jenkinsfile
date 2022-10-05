pipeline{
	agent none 	
	stages 
	{

		stage('build')
		{
			
			steps {
				cd ~/app
				sh 'npm install'
				sh 'node server.js'				
			}
			
		}
		post {
				failure {
					echo "build error"
				}
			}
	}
}