package com.tbh.backend;

import com.tbh.backend.service.KubernetesService;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1Deployment;
import io.kubernetes.client.openapi.models.V1Service;
import jakarta.ws.rs.core.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

    @Autowired
    private KubernetesService kubernetesService;

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Override
    public void run(String... args) {
        String namespace = "default";  // Change this as necessary
        String filePath = "/home/kraken/Code/TBH/Challenges/challenge_1_basic_xss/challenge_1_deployment.yaml";  // Adjust path as necessary

        try {
            V1Deployment deployment = kubernetesService.createDeploymentFromFile(namespace, filePath, "0");
            System.out.println("Deployment created successfully: " + deployment.getMetadata().getName());

            V1Service service = kubernetesService.createServiceFromFile(namespace, filePath, "1");
            System.out.println("Service created successfully: " + service.getMetadata().getName());
        } catch (Exception e) {
            System.err.println("Failed to create resources: " + e.getMessage());
            e.printStackTrace();
        }


    }
}