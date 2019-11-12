FROM alpine
RUN apk add openjdk11

ADD . /root/bethsaida
WORKDIR /root/bethsaida

ENTRYPOINT java -cp ClientMonitorApi-assembly-0.1.jar -Dinterface=0.0.0.0 -Dport=3000 org.downtowndailybread.bethsaida.ApiMain
EXPOSE 3000