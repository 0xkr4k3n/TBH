```bash=
~/Documents/TBH/Kubernetes main +2 !2                                                                                                          57s ⎈ minikube 09:15:41
❯ kubectl apply -f deployment.yaml
deployment.apps/backend-app-deployment created

~/Documents/TBH/Kubernetes main +2 !2                                                                                                              ⎈ minikube 09:16:08
❯  kubectl apply -f service.yaml
service/backend-app-service created


#This command lists all deployments in the current namespace, showing the desired and current number of replicas, as well as their availability status.
❯ kubectl get deployments
NAME                     READY   UP-TO-DATE   AVAILABLE   AGE
backend-app-deployment   0/2     2            0           64s
hello-minikube           1/1     1            1           13h

#This command lists all pods in the current namespace, showing their status, readiness, and age.
~/Documents/TBH/Kubernetes main +3 !3                                                                                                              ⎈ minikube 09:17:12
❯ kubectl get pods       
NAME                                      READY   STATUS             RESTARTS        AGE
backend-app-deployment-568fccb9fb-6wlp2   0/1     ImagePullBackOff   0               2m19s
backend-app-deployment-568fccb9fb-d7n8z   0/1     ImagePullBackOff   0               2m19s
hello-minikube-66fcf97965-2kqnb           1/1     Running            1 (3m29s ago)   13h


❯ kubectl port-forward service/backend-app-service 8080:80



```