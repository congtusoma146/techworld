pipeline{
    agent any
    stages{
            stage('Check for changes') {
                steps {
                    script {
                        def changes = sh (
                        script: "find ${env.SRC_DIR} -type f -newer ${env.LAST_BUILD_TIME}",
                        returnStdout: true
                        ).trim()
                    if (changes) {
                        echo "Changes found in ${env.SRC_DIR}: ${changes}"
                    } else {
                        error "No changes found in ${env.SRC_DIR}"
                    }
                    }
                }
            }
    }
}
