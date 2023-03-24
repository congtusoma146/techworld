/* groovylint-disable NglParseError */
/* groovylint-disable-next-line CompileStatic, NglParseError */
pipeline {
    agent any
    stages{
        stage('publish to ecr')
        {
            steps{
                /* groovylint-disable-next-line LineLength */
                withEnv(["AWS_ACCESS_KEY_ID=${env.AWS_ACCESS_KEY_ID}", "AWS_SECRET_ACCESS_KEY=${env.AWS_SECRET_ACCESS_KEY}", "AWS_DEFAULT_REGION=${env.AWS_DEFAULT_REGION}", "AWS_FORMAT=${env.AWS_FORMAT}"]) {
                    /* groovylint-disable-next-line LineLength */
                    sh 'aws ecr get-login-password --region ap-southeast-1 | docker login --username AWS --password-stdin 100521722927.dkr.ecr.ap-southeast-1.amazonaws.com'
                    sh 'docker build-t jenkins .'
                    sh 'docker tag jenkins:latest 100521722927.dkr.ecr.ap-southeast-1.amazonaws.com/jenkins:latest'
                    sh 'docker push 100521722927.dkr.ecr.ap-southeast-1.amazonaws.com/jenkins:latest'
                }
            }
        }
    }
}