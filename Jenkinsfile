/* groovylint-disable NglParseError, UnusedVariable */
/* groovylint-disable-next-line CompileStatic, NglParseError */

/* groovylint-disable-next-line NglParseError */
pipeline{
    agent any
    stages{
        stage('publish to ecr')
        {
            steps{
                /* Tạo Env để kết nối đến AWS  */
                /* groovylint-disable-next-line LineLength */
                withEnv(["AWS_ACCESS_KEY_ID=${env.AWS_ACCESS_KEY_ID}", "AWS_SECRET_ACCESS_KEY=${env.AWS_SECRET_ACCESS_KEY}", "AWS_DEFAULT_REGION=${env.AWS_DEFAULT_REGION}", "AWS_FORMAT=${env.AWS_FORMAT}"]) {
                    /* Tạo kết nối đến ECR */
                    /* groovylint-disable-next-line LineLength */
                    sh 'aws ecr get-login-password --region ap-southeast-1 | docker login --username AWS --password-stdin 100521722927.dkr.ecr.ap-southeast-1.amazonaws.com'
                    // Build docker image
                    sh "docker build -t techworld:${env.BUILD_ID} ."
                    // Tạo Docker tag
                    /* groovylint-disable-next-line LineLength */
                    sh "docker tag techworld:${env.BUILD_ID} 100521722927.dkr.ecr.ap-southeast-1.amazonaws.com/techworld:${env.BUILD_ID}"
                    // Push Docker lên ECR
                    sh "docker push 100521722927.dkr.ecr.ap-southeast-1.amazonaws.com/techworld:${env.BUILD_ID}"
                    /* groovylint-disable-next-line LineLength */
                    ecsTaskDefinition(
                        taskDefinitionArn: 'arn:aws:ecs:us-west-2:123456789012:task-definition/techworld:1',
                        containerDefinitions: [
                        ecsContainerDefinition(
                            name: 'techworld-cont',
                            image: '100521722927.dkr.ecr.ap-southeast-1.amazonaws.com/techworld:${env.BUILD_ID}',
                            portMappings: [
                            ecsPortMapping(
                                containerPort: 80,
                                hostPort: 80
                                )
                            ]
                        )
                        ]
                    )
                    //push lên ECS
                    /* groovylint-disable-next-line LineLength */
                    sh 'aws ecs update-service --cluster techworld-serv --service techworld-serv --force-new-deployment --region ap-southeast-1'
                }
            }
        }
    }
}