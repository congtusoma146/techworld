pipeline{

	agent any

	environment {
		VERSION = "v-0.${env.BUILD_ID}"
		APP_NAME = "techworld"
		REPO_NAME = "congtusoma146"
		DOCKER_IMAGE = "${REPO_NAME}/${APP_NAME}:${VERSION}"
	}

	stages {

		stage('Build') {
			agent {  label 'master'}
			steps {
				echo "Running ${VERSION} on ${env.JENKINS_URL}"
            	echo "for branch ${env.BRANCH_NAME}"
				shell '''docker build -t ${DOCKER_IMAGE} . '''
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
            shell '''echo $DOCKER_PASSWORD | docker login --username $DOCKER_USERNAME --password-stdin'''
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
				shell '''docker push ${IMAGE_NAME}'''
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
			shell '''docker logout'''
		}
	}

}
