package com.shoesclick.pipeline.strategy

class LinuxCmd implements SystemCmd {

    def steps
    LinuxCmd(steps) { this.steps = steps }

    def cmd(command) {
        steps.sh(command)
    }

    def cmdReturn(command) {
        return steps.sh(script: "${command}", returnStdout: true)
    }

    def steps() {
        return steps
    }

}
