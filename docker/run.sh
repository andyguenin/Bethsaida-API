#!/bin/bash

docker build . -t runner
docker run --rm -p 3000:3000 -d runner