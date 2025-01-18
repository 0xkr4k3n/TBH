package com.tbh.backend.kubernetesClient;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.kubernetes.client.openapi.models.V1Deployment;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.kubernetes.client.openapi.models.V1Service;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class KubernetesUtils {

    private static final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    static {
        mapper.findAndRegisterModules();
    }

    public static V1Deployment loadDeploymentFromFile(String fileName, String documentIndex) throws IOException {
        Map<String, Object> resourceMap = loadYamlDocument(fileName, documentIndex);
        return mapper.convertValue(resourceMap, V1Deployment.class);
    }

    public static V1Service loadServiceFromFile(String fileName, String documentIndex) throws IOException {
        Map<String, Object> resourceMap = loadYamlDocument(fileName, documentIndex);
        return mapper.convertValue(resourceMap, V1Service.class);
    }

    private static Map<String, Object> loadYamlDocument(String fileName, String documentIndex) throws IOException {
        MappingIterator<Map<String, Object>> iterator = mapper.readerFor(new TypeReference<Map<String, Object>>() {})
                .readValues(new File(fileName));

        int currentIndex = 0;
        while (iterator.hasNext()) {
            Map<String, Object> document = iterator.next();
            if (currentIndex == Integer.parseInt(documentIndex)) {
                return document;
            }
            currentIndex++;
        }
        throw new IOException("Document index " + documentIndex + " not found in file");
    }

}
