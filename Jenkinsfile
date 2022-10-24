pipeline{

	agent any

	environment {
		VERSION = "v-0.${env.BUILD_ID}"
		APP_NAME = "techworld"
		REPO_NAME = "congtusoma146"
		DOCKER_IMAGE = "${REPO_NAME}/${APP_NAME}:${VERSION}"
		DOCKERHUB_CREDENTIALS=credentials('docker')
		DEPLOY_PATH = "C:\\inetpub\\wwwroot"
		WORKSPACE_PATH = "C:\\Users\\quang\\AppData\\Local\\Jenkins\\.jenkins\\workspace\\techworld"
	}

	stages {
			stage("build") {
      			/*agent { node {label 'master'}}*/
      			environment {
        			DOCKER_TAG="${GIT_BRANCH.tokenize('/').pop()}-${GIT_COMMIT.substring(0,7)}"
					DOCKER_SCAN_SUGGEST=false
      			}
      			steps {
				powershell "docker build -t ${DOCKER_IMAGE} ."
        			/* bat "docker tag ${DOCKER_IMAGE} ${DOCKER_IMAGE}:latest"
        			bat "docker image ls | grep ${DOCKER_IMAGE}" */
				/* post{
					failure{
						echo "Error in build"
					}
				} */
        		//clean to save disk
			}
    		}

		stage('Login') {
			steps {
				/* bat '''docker logout'''
            	bat '''echo $DOCKERHUB_CREDENTIALS_PSW  | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin ''' */
				/* powershell "echo 123456789  | docker login -u congtusoma146 --password-stdin" */
				powershell "echo ${DOCKERHUB_CREDENTIALS_USR}"
				powershell '''echo ${%DOCKERHUB_CREDENTIALS_PSW%}|docker login -u $env:DOCKERHUB_CREDENTIALS_USR --password-stdin '''
			}
			post{
				failure{
					echo "Error in login"
				}
			}
		}

		stage('Push') {

			steps {
				/* bat "docker push ${DOCKER_IMAGE}" */
				powershell "docker push ${DOCKER_IMAGE}"
				powershell "docker image rm ${DOCKER_IMAGE}"
      				
			}
			post{
				failure{
					echo "Error in push"
				}
			}
		}
		stage('Publish') {

			steps {
				powershell '''
					$Deploymentool = "admin.local" 
					$Site = "techworld.local"

					stop-WebappPool -Name $Deploymentool
					stop-Website -Name $Site

					Start-Sleep -Seconds 5

					robocopy "$env:WORKSPACE_PATH" "$env:DEPLOY_PATH"  /e

					start-WebappPool -Name $Deploymentool
					start-Website -Name $Site 

					exit 0
					'''				
      				
			}
			post{
				failure{
					echo "Error in publish"
				}
			}
		}
	}

	post {
		always {
			/* bat '''docker logout''' */
			powershell 'docker logout'
		}
	}

}
