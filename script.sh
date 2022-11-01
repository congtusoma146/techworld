#! bin/sh
sudo docker update
sudo service docker start
docker pull congtusoma146/techworld:v-0.77
docker-compose -f docker-compose.yaml up
cd /home/app
npm install
node server.js
