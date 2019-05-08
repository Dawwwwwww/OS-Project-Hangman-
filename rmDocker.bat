@ECHO OFF
rem Remove container
docker rm -f hangman-client
docker rm -f hangman-server
rem remove images
docker rmi -f hangman-client-image
docker rmi -f hangman-server-image
