FROM ubuntu:latest
LABEL authors="wendel"


ENTRYPOINT ["top", "-b"]