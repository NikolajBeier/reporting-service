#!/bin/bash
set -e
mvn clean package
docker-compose build reporting-service
docker-compose up -d reporting-service
