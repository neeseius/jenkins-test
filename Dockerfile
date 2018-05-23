FROM alpine

MAINTAINER Justin N

RUN apk update
RUN apk add apache2 python
RUN mkdir -p /var/www
COPY config.json /var/www/auth/resources/config/config.json
COPY env.ini /var/www/auth/resources/config/config.json
