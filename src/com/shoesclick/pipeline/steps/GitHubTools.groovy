package com.shoesclick.pipeline.steps

import com.shoesclick.pipeline.strategy.SystemCmd

class GitHubTools {

    def systemCmd

    GitHubTools(SystemCmd systemCmd) { this.systemCmd = systemCmd }


    def checkoutSCM() {


        systemCmd.steps().stage('Checkout SCM') {

            systemCmd.steps().checkout([
                    $class                           : 'GitSCM',
                    branches                         : systemCmd.steps().scm.branches,
                    doGenerateSubmoduleConfigurations: systemCmd.steps().scm.doGenerateSubmoduleConfigurations,
                    extensions                       : systemCmd.steps().scm.extensions,
                    userRemoteConfigs                : systemCmd.steps().scm.userRemoteConfigs
            ]);
        }
    }

    def getRevision() {
        return systemCmd.cmdReturn("git rev-parse HEAD")
    }
}
