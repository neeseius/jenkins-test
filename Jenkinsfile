pipeline {
    agent { label 'docker' }
    
    stages {
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
}
