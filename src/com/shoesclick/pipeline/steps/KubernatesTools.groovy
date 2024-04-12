package com.shoesclick.pipeline.steps

import com.shoesclick.pipeline.model.Parameters
import com.shoesclick.pipeline.strategy.SystemCmd
import com.shoesclick.pipeline.utils.FileUtils

class KubernatesTools {

    def systemCmd

    KubernatesTools(SystemCmd systemCmd) { this.systemCmd = systemCmd }

    def deployK8s(Parameters model, serviceName, servicePath) {
        systemCmd.steps().stage('Deploy '+ serviceName) {
            replaceGatewayFile(model)
            systemCmd.steps().dir(model.workspaceJob +'/'+ servicePath) {
                systemCmd.cmd('kubectl apply -k .')
            }
        }
    }

    def replaceGatewayFile(Parameters model){
        def fileUtils = new FileUtils("${model.workspaceJob}/namespace-gateway.yaml");
        fileUtils.replaceGatewayNamespace(model);
    }

}
