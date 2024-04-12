package com.shoesclick.pipeline.steps

import com.shoesclick.pipeline.model.Parameters
import com.shoesclick.pipeline.strategy.SystemCmd
import com.shoesclick.pipeline.utils.FileUtils

class KubernatesTools {

    def systemCmd

    KubernatesTools(SystemCmd systemCmd) { this.systemCmd = systemCmd }

    def deployK8s(Parameters model, serviceName, servicePath) {
        systemCmd.steps().stage('Deploy '+ serviceName) {
            systemCmd.steps().dir(model.workspaceJob +'/'+ servicePath) {
                systemCmd.cmd('kubectl apply -k .')
            }
        }
    }
}
