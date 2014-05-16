# play

# Base image
FROM ubuntu:14.04

# Update OS
RUN echo "deb mirror://mirrors.ubuntu.com/mirrors.txt trusty main universe" > /etc/apt/sources.list
RUN apt-get update
RUN apt-get upgrade -y

# Install dependencies
RUN apt-get install -y wget unzip openjdk-7-jdk

# Install play
RUN wget http://downloads.typesafe.com/play/2.2.3/play-2.2.3.zip
RUN unzip play-2.2.3.zip -d /opt
RUN rm play-2.2.3.zip
RUN chmod +x /opt/play-2.2.3/play

# Set working dir
WORKDIR /src

# Set env vars
ENV PATH $PATH:/opt/play-2.2.3

# Entry command
ENTRYPOINT ["play"]