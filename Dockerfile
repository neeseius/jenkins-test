FROM alpine

MAINTAINER Justin N

RUN apk update
RUN apk install apache2 python
COPY auth/ /var/www/auth
COPY config.json /var/www/auth/resources/config/config.json
COPY env.ini /var/www/auth/resources/config/config.json
