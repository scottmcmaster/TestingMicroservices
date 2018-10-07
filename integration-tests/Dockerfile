FROM mhart/alpine-node
RUN mkdir -p /integration
WORKDIR /integration

RUN apk add --no-cache bash

COPY integration-tests.json /integration/
COPY dockercompose.postman_environment.json /integration/

ADD wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

RUN npm install -g newman

CMD [ "newman", "run", "integration-tests.json", "-e", "dockercompose.postman_environment.json" ]
