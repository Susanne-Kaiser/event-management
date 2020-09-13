# Event Management Service of Microservices Workshop

## Prerequisites

Following software has to be installed:

- Node / npm
- TypeScript
- Java 11
- Docker

## Setup

```bash
docker-compose up -d
```

## Start Service

```bash
mvn spring-boot:run

```

## Sample Requests

### Health check (with Actuator)

```bash
curl -i -X GET http://localhost:8080/actuator/health
```


### Valid request to create a new event

```bash
curl -i  -X POST http://localhost:8080/api/v1/events -H 'Content-Type: application/json' -d '{
"name": "New Event",
"startDate": "2020-09-24T11:00:00.689Z",
"endDate": "2020-09-25T11:00:00.689Z"
}'
```

### Invalid Request (missing mandatory fields)
```bash
curl -i  -X POST http://localhost:8080/api/v1/events -H 'Content-Type: application/json' -d '{
"name": "New Event"
}'
```

## Dockerize the service

### Build a Docker image

To build the executable jar-Archive, run:
```bash
mvn clean package
```

To build the Docker image, run `docker build -t username/imagename:tag .`, e.g.

```bash
docker build -t susannekaiser/service-event-management:latest .
```

### Create a Docker container instance by running the image

```bash
docker run --rm --name event_management -p 8080:8080 -e DATABASE_HOST=YOUR_HOST_IP susannekaiser/service-event-management
```

## Deploy your containerized service to a Kubernetes cluster

### Upload the Docker image to a Docker repository

Upload your Docker image to a Docker repository, that the Kubernetes cluster can access. In our example we are using the Docker Hub repository
at `https://hub.docker.com`. When building your Docker image (as described in the previous step), use your username instead for the `-t` option.

```bash
docker login
docker push susannekaiser/service-session-handling:latest
```

### Setup your Kubernetes cluster

We are setting up a Kubernetes cluster at Google Cloud Platform. A Google Cloud Platform account is required.

- Install gcloud => https://cloud.google.com/sdk/install
- Install kubectl => either with https://kubernetes.io/docs/tasks/tools/install-kubectl/ or  `gcloud components install kubectl`
- Install helm => https://helm.sh/docs/intro/install/

Recommended: bash completion => https://kubernetes.io/docs/tasks/tools/install-kubectl/#optional-kubectl-configurations


#### Setup Cluster on GCP
Login to https://console.cloud.google.com
And create a new project, e.g. workshop-microservices

After you have created the project, run

```bash
gcloud auth login
gcloud config set project PROJECT_ID # e.g. gloud config set project workshop-microservices
gcloud config set compute/zone COMPUTE_ZONE # e.g. gcloud config set compute/zone us-central1-a
gcloud container clusters create CLUSTER_NAME --num-nodes=2 # gcloud container clusters create conference-app --num-nodes=2
gcloud container clusters get-credentials CLUSTER_NAME, # e.g. gcloud container clusters get-credentials conference-app
```

### Install MongoDB on Kubernetes cluster

Usually, you are using a cloud provided database, but for this example, we are running a MongoDB in our cluster

```bash
helm install mongodb stable/mongodb
```

### Deploy service on Kubernetes cluster

```bash
kubectl apply -f ./k8s/deployment.yml
kubectl get all
```

### Make service accessible from outside the cluster by creating a Ingress resource and Nginx-ingress-controller

#### Install Ngninx-ingress-controller
```bash
helm repo add nginx-stable https://helm.nginx.com/stable
helm repo update
helm install nginx-ingress-controller-release nginx-stable/nginx-ingress
kubectl get service nginx-ingress-controller-release-nginx-ingress # to retrieve the external ip (EXTERNAL-IP column)
```

#### Create Ingress resource
```bash
kubectl apply -f ./k8/ingress.yml
```
Configure DNS of event-management.example.org pointing to the external ip from nginx-ingress-controller-release-nginx-ingress.
In our example, we are adding it to `/etc/hosts` file (just for demonstration purposes! Do not edit it in production)
as a `EXTERNAL-IP event-management.example.org`, e.g. `35.192.165.42  event-management.example.org` entry.

Now, you can run the above requests again, but with `event-management.example.org` as host and port `80` as port, e.g.

```bash
curl -i  -X POST http://event-management.example.org/api/v1/events -H 'Content-Type: application/json' -d '{
"name": "New Event",
"startDate": "2020-09-24T11:00:00.689Z",
"endDate": "2020-09-25T11:00:00.689Z"
}'

```


