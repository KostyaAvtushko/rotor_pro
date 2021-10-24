#!/usr/bin/env bash

gradle clean build

echo 'Copy files...'

scp -i
    C:/Users/BVK/IdeaProjects/rotor-test/src/main/java/rotor-test-0.0.1-SNAPSHOT.jar \
    root@141.95.21.130:/home/root/rotor/

echo 'Restart server...'

ssh -i ~/.ssh/id_rsa root@141.95.21.130 << EOF

pgrep java | xargs kill -9
nohup java -jar rotor-test-0.0.1-SNAPSHOT.jar > log.txt &

EOF

echo 'Bye'