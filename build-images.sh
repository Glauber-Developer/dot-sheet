#!/bin/bash

# Array com os nomes dos diretórios e das imagens
services=("eureka-server" "api-gateway" "payroll-service" "attendance-service" "reporting-service" "user-service")
images=("eureka-server-image" "api-gateway-image" "payroll-service-image" "attendance-service-image" "reporting-service-image" "user-service-image")

# Construir as imagens Docker
for i in "${!services[@]}"; do
    echo "Building Docker image for ${services[$i]}..."
    cd ${services[$i]}
    docker build -t ${images[$i]} .
    cd ..
done