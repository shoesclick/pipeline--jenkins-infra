import com.shoesclick.pipeline.steps.GitHubTools
import com.shoesclick.pipeline.steps.KubernatesTools
import com.shoesclick.pipeline.model.Parameters
import com.shoesclick.pipeline.strategy.LinuxCmd
import com.shoesclick.pipeline.strategy.WinCmd

def call(body) {

    def params = [:]

    def sysCmd;
    switch (env.SYSTEM_OPERATION) {
        case "Windows":
            sysCmd = new WinCmd(this)
            break
        case "Linux":
            sysCmd = new LinuxCmd(this)
            break
        default:
            currentBuild.result = 'FAILURE'
            error('deliveryPipeline => Missing required env: SYSTEM_OPERATION\n')
    }

    def gitHubTools = new GitHubTools(sysCmd)
    def kubernatesTools = new KubernatesTools(sysCmd)

    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = params
    body()

    if (!params.package_manager) {
        currentBuild.result = 'FAILURE'
        error('deliveryPipeline => Missing required param: package_manager\n')
    }

    node {

        gitHubTools.checkoutSCM()

        def revision = gitHubTools.getRevision()

        def parameters = new Parameters(
                env.WORKSPACE,
                env.PROJECT_NAME,
                env.K8S_SERVICE_NAME,
                env.K8S_NAMESPACE,
        )

        println("revision: ${revision}")
        params.package_manager.each{ stepName, stepDir ->  kubernatesTools.deployK8s(parameters,stepName,stepDir) }

     }


}
