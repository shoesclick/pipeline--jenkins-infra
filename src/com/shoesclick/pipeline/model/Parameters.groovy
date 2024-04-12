package com.shoesclick.pipeline.model;

class Parameters{

    def workspaceJob;
    def projectName;
    def k8sServiceName;
    def k8sNamespace;

    Parameters(workspaceJob, projectName, k8sServiceName, k8sNamespace){
        this.workspaceJob = workspaceJob;
        this.projectName = projectName;
        this.k8sServiceName = k8sServiceName;
        this.k8sNamespace = k8sNamespace;
    }


}