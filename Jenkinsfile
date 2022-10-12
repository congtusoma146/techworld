pipeline{

	agent none

	environment {
		VERSION = "v-0.${env.BUILD_ID}"
		APP_NAME = "techworld"
		REPO_NAME = "congtusoma146"
		DOCKER_IMAGE = "${REPO_NAME}/${APP_NAME}:${VERSION}"
		DOCKERHUB_CREDENTIALS=credentials('docker-hub')
	}

	stages {
			stage("build") {
      			agent { node {label 'master'}}
      			environment {
        			DOCKER_TAG="${GIT_BRANCH.tokenize('/').pop()}-${GIT_COMMIT.substring(0,7)}"
					DOCKER_SCAN_SUGGEST=false
      			}
      			steps {
        			bat "docker build -t ${DOCKER_IMAGE}  ."
        			/* bat "docker tag ${DOCKER_IMAGE} ${DOCKER_IMAGE}:latest"
        			bat "docker image ls | grep ${DOCKER_IMAGE}" */
				/* post{
					failure{
						echo "Error in build"
					}
				} */
        		//clean to save disk
				/* at'''docker image rm ${DOCKER_IMAGE}'''  */
      				}
    		}

		stage('Login') {
			steps {
            bat '''echo huyenthanh93 | docker login -u congtusoma146 --password-stdin'''
			}
			post{
				failure{
					echo "Error in login"
				}
			}
		}

		stage('Push') {

			steps {
				bat "docker push ${DOCKER_IMAGE}"
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
