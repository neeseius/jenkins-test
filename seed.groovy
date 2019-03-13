import org.foo.Utils
version = "0.5"

def createDeployJob(appName, appEnv) {
    pipelineJob("deploy-${appName}-${appEnv}-${version}") {
        definition {
            cpsScm {
                scm {
                    git {
                        remote {
                            url("git@bitbucket.org/test/${appName}")
                        }
                        branches('master')
                        extensions {
                            cleanBeforeCheckout()
                        }
                    }
                }
                scriptPath("Jenkinsfile")
            }
        }
    }
}

def buildJobs() {
    ["dev", "stg", "prd"].each { it ->
        createDeployJob(APP_NAME, it)
    }
}

buildJobs()
