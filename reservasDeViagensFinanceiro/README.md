SE NAO TIVER BAIXADO ELE JÁ RODA A IMAGEM

docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.9-management


padrao user e senha  = guest


STARTAR REDIS

redis/redis-stack-server
docker run -d --name redis-stack-server -p 6379:6379 redis/redis-stack-server:latest

redis/redis-stack
docker run -d --name redis-stack -p 6379:6379 -p 8001:8001 redis/redis-stack:latest
