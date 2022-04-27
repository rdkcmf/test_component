def call() {
    stage("shared_stage") {
        steps {
            script {
                sh """
                    echo "shared step"
                """
            }
        }
    }
}
