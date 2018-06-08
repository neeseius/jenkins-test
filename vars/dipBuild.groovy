def call(Map params) {
    pipeline {
        agent { label 'dip' }

        stages {
            stage('clone repo') {
                steps {
                    echo params.thing1
                    echo params.thing2
                }
            }
        }
    }
}
