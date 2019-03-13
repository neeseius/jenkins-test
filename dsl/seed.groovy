import utilities.Util
version = "0.5"

def createDeployJob(appName, appEnv) {
    util = new Util()
    def jobScript = util.script

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
                script(jobScript)
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
