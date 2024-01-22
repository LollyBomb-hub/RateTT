# Для запуска  

Настоятельно рекомендуется проверить, что не CRLF а LF файлы  
Поднять сервисы. Для этого из папки docker-compose сделать вызов и дождаться успешного выполнения:  
<code>
docker compose up -f services.docker-compose.yaml --env-file=services.local.env
</code>

После этого из корня проекта  
<code>
mvn package exec:java
</code>