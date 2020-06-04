# vault-demo
Demo project for demonstrating how HashiCorp Vault can be used as Certificate Authority.

## Getting Started

These instructions will get you a copy of the project up and running on your virtual machines for development and testing purposes.

The project contains 3 modules: config-server, vault-cert-client, vault-client-gateway
I used GCP Compute Engine VM instances running Linux Debian to deploy these modules, with the following internal IP addresses:

* 10.128.0.2 - Vault
* 10.128.0.3 - config-server, vault-cert-client
* 10.128.0.4 - vault-client-gateway

### Installing

1. Install Vault on a VM (10.128.0.2 in my case) and configure vault-pki
2. Clone the repository
3. Run ```mvn package``` for each module or use the pre-built JAR files from the target folders
4. Copy the jar files to the other VMs and run ```java -jar <name_of_jar>``` for each
5. Test it by executing the following command on one of the machines ```curl https://10.128.0.4:8444/gateway/hello/world```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Used technologies

* Java
* Spring Boot
* Spring Cloud Config
* Spring Cloud Vault
* HashiCorp Vault
