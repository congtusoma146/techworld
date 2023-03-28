def call(Map config) {
    def awsCreds = config.awsCreds ?: 'AWS-Access'
    def awsRegion = config.awsRegion ?: 'ap-southeast-1'
    def family = config.family
    def containerDefinitions = config.containerDefinitions

    withCredentials([[
            $class: 'AmazonWebServicesCredentialsBinding',
            accessKeyVariable: 'AWS_ACCESS_KEY_ID',
            credentialsId: awsCreds,
            secretKeyVariable: 'AWS_SECRET_ACCESS_KEY'
    ]]) {
        withAWS(region: awsRegion, credentials: awsCreds) {
            def taskDefinition = ecs.describeTaskDefinition(new DescribeTaskDefinitionRequest().withTaskDefinition(family))
            def containerDefinition = taskDefinition.taskDefinition.containerDefinitions[0]
            containerDefinitions.each { definition ->
                if (definition.name == containerDefinition.name) {
                    definition.image = definition.image ?: containerDefinition.image
                }
            }
            taskDefinition.taskDefinition.containerDefinitions = containerDefinitions
            def registerResponse = ecs.registerTaskDefinition(new RegisterTaskDefinitionRequest().withFamily(family).withContainerDefinitions(containerDefinitions))
            println "Updated task definition ${registerResponse.taskDefinition.family}:${registerResponse.taskDefinition.revision}"
            return registerResponse.taskDefinition.taskDefinitionArn
        }
    }
}
