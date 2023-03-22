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
                  environment {
                    DOCKER_TAG="${GIT_BRANCH.tokenize('/').pop()}-${GIT_COMMIT.substring(0,7)}"
                    DOCKER_SCAN_SUGGEST=false
                  }
                  steps {
                sh "docker build -t ${DOCKER_IMAGE} ."
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
                sh '''docker login -u congtusoma146 --password 123456789'''
                /* powershell "echo ${DOCKERHUB_CREDENTIALS_USR}"
                powershell '''echo ['DOCKERHUB_CREDENTIALS_PSW']|docker login -u $env:DOCKERHUB_CREDENTIALS_USR --password-stdin ''' */
                //withCredentials([usernamePassword(credentialsId: 'docker', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
                  // available as an env variable, but will be masked if you try to print it out any which way
                  // note: single quotes prevent Groovy interpolation; expansion is by Bourne Shell, which is what you want
                  //powershell 'echo ${PASSWORD}|docker login -u ${USERNAME} --password-stdin'
                  // also available as a Groovy variable
                  //echo USERNAME
                  // or inside double quotes for string interpolation
                  //echo "username is $USERNAME"
                
            }
            post{
                failure{
                    echo "Error in login"
                }
            }
            }
        stage('Push') {

            steps {
                sh "docker push ${DOCKER_IMAGE}"
                sh "docker image rm ${DOCKER_IMAGE}"
                      
            }
            post{
                failure{
                    echo "Error in push"
                }
            }
        }
    post {
        always {
            /* bat '''docker logout''' */
            sh 'docker logout'
        }
    }
}
}