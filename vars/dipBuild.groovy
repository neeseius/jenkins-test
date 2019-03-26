def call(Map params) {

    Pipeline {
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
