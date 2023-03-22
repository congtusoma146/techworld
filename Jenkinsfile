/* groovylint-disable-next-line CompileStatic */
pipeline {
    agent none
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
            stage('build') {
            environment {
                    DOCKER_TAG = "${GIT_BRANCH.tokenize('/').pop()}-${GIT_COMMIT.substring(0, 7)}"
                    DOCKER_SCAN_SUGGEST = false
            }
            steps {
                sh "docker build -t ${DOCKER_IMAGE} ."
            }
            }

            stage('Login') {
            steps {
                sh '''docker login -u congtusoma146 --password 123456789'''
            }
            post{
                failure{
                    echo 'Error in login'
                }
            }
            }
        stage('Push') {
            steps {
                sh "docker push ${DOCKER_IMAGE}"
                sh "docker image rm ${DOCKER_IMAGE}"
            }
            post{
                failure {
                    echo 'Error in push'
                }
            }
        }
        post {
            always {
                sh 'docker logout'
            }
        }
    }
}
