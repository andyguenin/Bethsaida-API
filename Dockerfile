FROM alpine
RUN apk add openjdk11 bash
RUN wget https://piccolo.link/sbt-1.3.3.tgz && tar -zxf sbt-1.3.3.tgz;

ADD . /root/bethsaida
VOLUME /root/assembly
WORKDIR /root/bethsaida

RUN /sbt/bin/sbt assembly

CMD cp /root/bethsaida/target/scala-2.12/ClientMonitorApi-assembly-0.1.jar /root/assembly/docker; chmod 777 /root/assembly/docker/ClientMonitorApi-assembly-0.1.jar
