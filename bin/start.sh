#!/bin/sh


keytool -genkeypair -alias api-self-sgined -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore api-self-sgined.p12 -validity 3650  \
   -dname "CN=appserver, OU=ID, O=openvpn, L=Morrisville, S=NC, C=US"  -ext san=dns:appserver  -storepass changeit

java -Xlog:gc -jar $JAVA_OPTS app.jar --spring.datasource.initialization-mode=always \
      --server.ssl.enabled=true --server.port=443 --server.ssl.key-alias=api-self-sgined  \
      --server.ssl.key-store=./api-self-sgined.p12 --server.ssl.key-store-type=PKCS12 --server.ssl.key-password=changeit  --server.ssl.key-store-password=changeit