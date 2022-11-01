#! bin/sh
docker-compose -f docker-compose.yaml up
cd /home/app
npm install
node server.js
