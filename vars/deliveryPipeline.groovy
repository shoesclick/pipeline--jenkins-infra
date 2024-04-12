import com.shoesclick.pipeline.steps.GitHubTools
import com.shoesclick.pipeline.steps.KubernatesTools
import com.shoesclick.pipeline.model.Parameters
import com.shoesclick.pipeline.strategy.WinCmd

def call(body) {

    def params = [:]

    def sysCmd = new WinCmd(this)

    def gitHubTools = new GitHubTools(sysCmd)
    def dockerTools = new DockerTools(sysCmd)
    def sonarTools = new SonarTools(sysCmd)
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
        );

        println("revision: ${revision}")
        kubernatesTools.deployK8s(parameterModel,"Mysql","mysql")
        kubernatesTools.deployK8s(parameterModel,"Redis","redis")
        kubernatesTools.deployK8s(parameterModel,"Zookeeper","kafka/zookeeper")
        kubernatesTools.deployK8s(parameterModel,"Kafka Broker","kafka/kafka-broker")
        kubernatesTools.deployK8s(parameterModel,"Kafka Broker","kafka/schema-registry")
    }


}
