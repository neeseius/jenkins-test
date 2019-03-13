import utilities.Util
version = "0.5"

def createDeployJob(appName, appEnv) {
    util = new Util()
    util.appName = appName
    util.version = version

    def jobScript = util.getScript()

    pipelineJob("deploy-${appName}-${appEnv}-${version}") {
        definition {
            cps {
                script(jobScript)
                sandbox()
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
