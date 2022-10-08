pipeline{

	agent none

	environment {
		DOCKERHUB_CREDENTIALS=credentials('docker-hub')
		VERSION = "v-0.${env.BUILD_ID}"
		APP_NAME = "techworld"
		REPO_NAME = "congtusoma146"
	}

	stages {

		stage('Gitclone') {
			steps {
				git 'https://github.com/congtusoma146/techworld.git'
			}
			post{
				failure{
					echo "Error in gitclone"
				}
			}
		}

		stage('Build') {
			agent {
				node {label "$env.BRANCH_NAME"}
			}
			steps {
				script {
					IMAGE_NAME = "${REPO_NAME}/${APP_NAME}:${VERSION}"
				}
				echo "Running ${VERSION} on ${env.JENKINS_URL}"
            	echo "for branch ${env.BRANCH_NAME}"
            	sh "docker build -t ${IMAGE_NAME} ."

			}
			post{
				failure{
					echo "Error in build"
				}
			}
		}

		stage('Login') {
			steps {
				sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
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
