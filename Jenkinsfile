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
        			bat "docker build -t ${DOCKER_IMAGE}:${DOCKER_TAG} . "
        			bat "docker tag ${DOCKER_IMAGE}:${DOCKER_TAG} ${DOCKER_IMAGE}:latest"
        			bat "docker image ls | grep ${DOCKER_IMAGE}"
        			withCredentials([usernamePassword(credentialsId: 'docker-hub', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
            		bat 'echo $DOCKER_PASSWORD | docker login --username $DOCKER_USERNAME --password-stdin'
            		bat "docker pubat ${DOCKER_IMAGE}:${DOCKER_TAG}"
            		bat "docker push ${DOCKER_IMAGE}:latest"
        			}
				post{
					failure{
						echo "Error in build"
					}
				}
        		//clean to save disk
        		bat "docker image rm ${DOCKER_IMAGE}:${DOCKER_TAG}"
        		bat "docker image rm ${DOCKER_IMAGE}:latest"
      				}
    		}

		stage('Login') {
			steps {
				withCredentials([usernamePassword(credentialsId: 'docker-hub', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
            batell '''echo $DOCKER_PASSWORD | docker login --username $DOCKER_USERNAME --password-stdin'''
			}
			}
			post{
				failure{
					echo "Error in login"
				}
			}
		}

		stage('Pubat') {

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
