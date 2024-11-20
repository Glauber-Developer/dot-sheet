#!/bin/bash

# Array com os nomes dos diretórios e das imagens
services=("eureka_server" "api_gateway" "payroll_service" "attendance_service" "reporting_service" "user_service")
images=("eureka-server-image" "api-gateway-image" "payroll-service-image" "attendance-service-image" "reporting-service-image" "user-service-image")

# Construir as imagens Docker
for i in "${!services[@]}"; do
    echo "Building Docker image for ${services[$i]}..."
    cd ${services[$i]}
    docker build -t ${images[$i]} .
    cd ..
done