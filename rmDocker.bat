@ECHO OFF
docker rm -f hangman-client
docker rm -f hangman-server
docker rmi -f hangman-client-image
docker rmi -f hangman-server-image