pipeline{

	agent none

	environment {
		VERSION = "v-0.${env.BUILD_ID}"
		APP_NAME = "techworld"
		REPO_NAME = "congtusoma146"
	}

	stages {

		stage('Build') {
			agent {  label 'master'}
			steps {
				script {
					IMAGE_NAME = "${REPO_NAME}/${APP_NAME}:${VERSION}"
				}
				echo "Running ${VERSION} on ${env.JENKINS_URL}"
            	echo "for branch ${env.BRANCH_NAME}"
            	shell '''docker build -t ${DOCKER_IMAGE}
				 '''

			}
			post{
				failure{
					echo "Error in build"
				}
			}
		}

		stage('Login') {
			steps {
				withCredentials([usernamePassword(credentialsId: 'docker-hub', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
            sh 'echo $DOCKER_PASSWORD | docker login --username $DOCKER_USERNAME --password-stdin'
			}
			}
			post{
				failure{
					echo "Error in login"
				}
			}
		}

		stage('Push') {

			steps {
				sh "docker push ${IMAGE_NAME}"
			}
			post{
				failure{
					echo "Error in push"
				}
			}
		}
	}

	post {
		always {
			sh 'docker logout'
		}
	}

}
