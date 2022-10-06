pipeline{

	agent any

	environment {
		DOCKERHUB_CREDENTIALS=credentials('dockerhub')
		VERSION = "v-0.${env.BUILD_ID}"
		APP_NAME = "js-app"
		REPO_NAME = "buinguyen"
	}

	stages {

		stage('Gitclone') {
			steps {
				git 'https://github.com/nguyenbuitk/js-app-with-docker-jenkins.git'
			}
		}

		stage('Build') {

			steps {
				script {
					IMAGE_NAME = "${REPO_NAME}/${APP_NAME}:${VERSION}"
				}
				echo "Running ${VERSION} on ${env.JENKINS_URL}"
            	echo "for branch ${env.BRANCH_NAME}"
            	sh "docker build -t ${IMAGE_NAME} ."

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
