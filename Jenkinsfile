pipeline{

	agent any

	environment {
		VERSION = "v-0.${env.BUILD_ID}"
		APP_NAME = "techworld"
		REPO_NAME = "congtusoma146"
		DOCKER_IMAGE = "${REPO_NAME}/${APP_NAME}:${VERSION}"
	}

	stages {
			stage("build") {
      			agent { node {label 'master'}}
      			environment {
        			DOCKER_TAG="${GIT_BRANCH.tokenize('/').pop()}-${GIT_COMMIT.substring(0,7)}"
      			}
      			steps {
        			bat "docker build -t ${DOCKER_IMAGE}  ."
        			/* bat "docker tag ${DOCKER_IMAGE} ${DOCKER_IMAGE}:latest"
        			bat "docker image ls | grep ${DOCKER_IMAGE}" */
				post{
					failure{
						echo "Error in build"
					}
				}
        		//clean to save disk
      				}
    		}

		stage('Login') {
			steps {
				withCredentials([usernamePassword(credentialsId: 'docker-hub', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
            bat '''echo $DOCKER_PASSWORD | docker login --username $DOCKER_USERNAME --password-stdin'''
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
				bat '''docker push ${IMAGE_NAME}'''
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
			bat '''docker logout'''
		}
	}

}
