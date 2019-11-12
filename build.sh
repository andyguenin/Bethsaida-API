#!/bin/bash

docker build . -t assembly
docker run --rm -v `pwd`:/root/assembly assembly