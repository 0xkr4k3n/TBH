package com.tbh.backend.service;


import com.tbh.backend.kubernetesClient.KubernetesUtils;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class KubernetesService {

    private final AppsV1Api appsV1Api;
    private final CoreV1Api coreV1Api;


    @Autowired
    public KubernetesService(AppsV1Api appsV1Api, CoreV1Api coreV1Api) {
        this.appsV1Api = appsV1Api;
        this.coreV1Api = coreV1Api;
    }

    public AppsV1Api getAppsV1Api() {
        return appsV1Api;
    }

    public CoreV1Api getCoreV1Api() {
        return coreV1Api;
    }

    public V1Service createServiceFromFile(String namespace, String fileName, String documentIndex) throws IOException {
        V1Service service = KubernetesUtils.loadServiceFromFile(fileName, documentIndex);
        try {
            return coreV1Api.createNamespacedService(
                    namespace,
                    service
            ).execute();
        } catch (ApiException e) {
            throw new IOException("Failed to create service: " + e.getMessage(), e);
        }
    }

    public V1Deployment createDeploymentFromFile(String namespace, String fileName, String documentIndex) throws IOException {
        V1Deployment deployment = KubernetesUtils.loadDeploymentFromFile(fileName, documentIndex);
        try {
            return appsV1Api.createNamespacedDeployment(
                    namespace,
                    deployment
            ).execute();
        } catch (ApiException e) {
            throw new IOException("Failed to create deployment: " + e.getMessage(), e);
        }
    }
}
