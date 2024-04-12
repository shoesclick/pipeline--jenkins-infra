package com.shoesclick.pipeline.strategy

interface SystemCmd {

    def cmd(command)

    def cmdReturn(command)

    def steps()

}