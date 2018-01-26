pipeline {
    agent { label 'docker' }

        stage('Run Gradle') {
            agent { 
                dockerfile {
                    reuseNode true
                    dockerfile 'Dockerfile'
                }
            }
            steps {
                sh 'touch /var/log/broker/file'
            }
        }
}
