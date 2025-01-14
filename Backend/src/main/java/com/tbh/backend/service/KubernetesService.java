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

    @Autowired
    public KubernetesService(AppsV1Api appsV1Api){
        this.appsV1Api = appsV1Api;
    }

    public V1Deployment createDeploymentFromFile(String namespace, String filePath) throws ApiException, IOException {
        V1Deployment deployment = KubernetesUtils.loadDeploymentFromFile(filePath);
        return appsV1Api.createNamespacedDeployment(namespace, deployment).execute();
    }
}
