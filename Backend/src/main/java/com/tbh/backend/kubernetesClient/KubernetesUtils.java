package com.tbh.backend.kubernetesClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.kubernetes.client.openapi.models.V1Deployment;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.io.IOException;

public class KubernetesUtils {

    public static V1Deployment loadDeploymentFromFile(String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        return mapper.readValue(new File(fileName), V1Deployment.class);
    }

}
