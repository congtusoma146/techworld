pipeline{

	agent none

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
        			sh "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} . "
        			sh "docker tag ${DOCKER_IMAGE}:${DOCKER_TAG} ${DOCKER_IMAGE}:latest"
        			sh "docker image ls | grep ${DOCKER_IMAGE}"
        			withCredentials([usernamePassword(credentialsId: 'docker-hub', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
            		sh 'echo $DOCKER_PASSWORD | docker login --username $DOCKER_USERNAME --password-stdin'
            		sh "docker push ${DOCKER_IMAGE}:${DOCKER_TAG}"
            		sh "docker push ${DOCKER_IMAGE}:latest"
        			}
				post{
					failure{
						echo "Error in build"
					}
				}
        		//clean to save disk
        		sh "docker image rm ${DOCKER_IMAGE}:${DOCKER_TAG}"
        		sh "docker image rm ${DOCKER_IMAGE}:latest"
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
