# Introduction
This is a repo for a three node rabbitMQ cluster for the luminate project.  It is designed to be ran as an HA cluster with 3 nodes.  It has the following characteristics:

* Able to continue operating with a loss of one one.
* Node will be placed and remain in a no-operational state if they detect they are not part of the required quorum cluster count or peers
* The queues are replicated one node. 
* Loss of either the primary or replica node will result in the data being duplicated to the remaining node as required in a similar manaer to the Elasticsearch replicas would be handled
* Should two nodes fail, the cluster will be completely unavailable
* Network partitions and other failure modes can be tested with the docker-compose file included as part of this repo.  This type of scenario can be described as a split brain.
* The stack file here is designed to work with both compose and swarm.
* There is a script for generating sha256 hashed passwords in a format compatible with RabbitMQ (generate-password.sh).

# Getting Started
1.	Installation process
* Install Docker engine & compose (versions 18.06.01+ && 1.22.0 respectively)
* From a terminal, run `docker-compose up`, this will build and run the rabbit cluster and HA proxy instance
2.	Software dependencies
* Docker engine & compose (versions 18.06.01+ && 1.22.0 respectively)
3.	Latest releases
* N/A
4.	API references
* N/A

# Build and Test
* `docker-compose build`

# References
https://docs.docker.com/compose/
