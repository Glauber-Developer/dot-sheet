# Array com os nomes dos diretórios e das imagens
$services = @("eureka_server", "api_gateway", "payroll_service", "attendance_service", "reporting_service", "user_service")
$images = @("eureka-server-image", "api-gateway-image", "payroll-service-image", "attendance-service-image", "reporting-service-image", "user-service-image")

# Construir as imagens Docker
for ($i = 0; $i -lt $services.Length; $i++) {
    Write-Host "Building Docker image for $services[$i]..."
    Set-Location $services[$i]
    docker build -t $images[$i] .
    Set-Location ..
}