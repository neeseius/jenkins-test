package foo.bar

class Deploy {
    def script = """
        @Library(["SharedLib"])

        def appSpec = [
            appName: "${APP_NAME}",
            domain: "test.com",
            envName: "dev",
            appEnv: "dev",
        ]

        def deploymentSpec = [
            kubeNamespace: "test-api-dev",
            replicas: 1,
            affinity: ["zone": "phx"],
            deployment: "\${appSpec.appName}-\${appSpec.envName}",
            tolerations: null,
            containerPorts: [
                [port: 8443, proto: "TCP"],
                [port: 8444, proto: "TCP"]
            ]
        ]

        testContainerDeploy(
            deploymentSpec: deploymentSpec,
            appSpec: appSpec,
            template: "test/test-service.j2.yml",
            gitUrl: "git@bitbucket.org:test/\${appSpec.appName}.git",
            imageFromBuild: "test/kube-build/\${appSpec.appName}/containers"
        )
    """.stripIdent()
}
