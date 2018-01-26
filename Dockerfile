FROM gradle:latest

MAINTAINER Justin N

USER root
RUN mkdir /var/log/broker -m 777
