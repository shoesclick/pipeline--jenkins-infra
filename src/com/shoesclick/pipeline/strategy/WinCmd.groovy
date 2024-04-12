package com.shoesclick.pipeline.strategy

class WinCmd implements SystemCmd {

    def steps

    WinCmd(steps) { this.steps = steps }

    def cmd(command) {
        steps.bat(command)
    }

    def cmdReturn(command) {
        def cmdReturn = steps.bat(returnStdout: true, script: command).trim()
        return cmdReturn.readLines().drop(1).join(" ")
    }

    def steps() {
        return steps
    }
}
