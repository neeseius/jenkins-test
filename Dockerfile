FROM gradle:latest

MAINTAINER Justin N

USER ROOT
RUN mkdir /var/log/broker -m 777
