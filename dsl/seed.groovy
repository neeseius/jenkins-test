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

def createBuildJob(appName, appEnv) {
    multibranchPipelineJob("build-${appName}-${appEnv}-${version}") {
        branchSources {
            git {
                remote("git@bitbucket.org:test/${appName}")
            }
        }
    }
}

def buildJobs() {
    createBuildJob(env.appName, it)

    environments = ["dev", "stg", "prd"]
    environments.each { it ->
        createBuildJob(env.appName, it)
    }
}

buildJobs()
