def version = "0.5"

def createDeployJob(appName, appEnv) {
    pipelineJob("deploy-${appname}-${appEnv}-${version}") {
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

def createBuildJob(appName) {
    multibranchPipelineJob("build-${appName}-${appEnv}") {
        branchSources {
            git {
                remote("git@bitbucket.org:test/${appName}")
            }
        }
    }
}

def buildJobs() {
    createBuildJob(APP_NAME)

    environments = ["dev", "stg", "prd"]
    environments.each { it ->
        createDeployJob(APP_NAME, it)
    }
}

buildJobs()
