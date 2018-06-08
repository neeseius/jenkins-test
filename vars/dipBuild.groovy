def call(Map params) {
    pipeline {
        agent { label 'dip' }
    
        stages {
            stage('clone repo') {
                steps{
                    checkout scm:[
                        $class: 'GitSCM', 
                        userRemoteConfigs: [[url: params.gitUrl,
                        credentialsId: params.credentialsId]], 
                        branches: [[name: params.gitBranch]],
                        poll: true
                    ]
                }
            }
            
            stage('gradle clean build') {
                agent {
                    dockerfile {
                        reuseNode true
                        filename 'Dockerfile'
                        args '-e "HOME=$HOME" -e "_JAVA_OPTIONS=-Duser.home=$HOME" -v "$HOME:/$HOME"'
                    }
                }
                steps {
                    sh 'gradle clean build -Dspring.profiles.active=test'
                }
            }
        }
        
        post {
            always {
                junit 'build/test-results/test/*.xml'
            }
            success {
                archiveArtifacts artifacts: 'build/libs/*.jar', onlyIfSuccessful: true
                slackSend botUser: true, channel: 'dip-jenkins', color: 'good',
                message: "SUCCESS: Built ${JOB} ${params.environ} ${BUILD_URL}"
            }
            #failure {
            #    slackSend botUser: true, channel: 'dip-jenkins', color: 'danger',
            #    message: "FAILURE: sion} to ${host}\n${BUILD_URL}"
            #}
        }
    }
