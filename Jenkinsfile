pipeline{

	agent any

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
		}

		stage('Build') {
			steps {
				script {
					IMAGE_NAME = "${REPO_NAME}/${APP_NAME}:${VERSION}"
				}
				echo "Running ${VERSION} on ${env.JENKINS_URL}"
            	echo "for branch ${env.BRANCH_NAME}"
            	sh "docker build -t ${IMAGE_NAME} --privileged ."

			}
		}

		stage('Login') {
			steps {
				sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
			}
		}

		stage('Push') {

			steps {
				sh "docker push ${IMAGE_NAME}"
			}
		}
	}

	post {
		always {
			sh 'docker logout'
		}
	}

}
