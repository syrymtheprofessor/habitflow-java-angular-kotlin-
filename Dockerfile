FROM ubuntu:latest
LABEL authors="Kazan"

ENTRYPOINT ["top", "-b"]