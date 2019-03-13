import scripts.kubeDeployScript
import utilities.Meta

def createDeployJob(appName, appEnv) {
    meta = new Meta()
    version = meta.version

    script = new kubeDeployScript()
    script.appName = appName
    script.version = version
    
    def jobScript = script.getScript()

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
