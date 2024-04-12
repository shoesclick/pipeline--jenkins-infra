package com.shoesclick.pipeline.utils;

import com.shoesclick.pipeline.model.Parameters;

class FileUtils{

    def file

    FileUtils(String filePath){
        this.file = new File(filePath);
    }

    def replaceGatewayNamespace(Parameters model){
        def readContent = this.file.text
                .replaceAll('\\$k8sNamespace',"${model.k8sNamespace}")
        this.file.text = readContent;
    }

}
