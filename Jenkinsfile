pipeline{
	agent none 	
	stages 
	{

		stage('build')
		{
			
			steps {
				echo 'build...'	
			}
			
		}
		post {
				failure {
					echo "build error"
				}
			}
	}
}