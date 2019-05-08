@ECHO OFF
docker rm -f hangman-client
docker rm -f hangman-server
docker rmi -f hangman-client-image
docker rmi -f hangman-server-image

@ECHO ON
docker build -f Dockerfile-server -t hangman-server-image . 
docker run -dit --name hangman-server hangman-server-image
docker build -f Dockerfile-client -t hangman-client-image .
docker run -it --name hangman-client --link hangman-server:server hangman-client-image

@ECHO OFF
PAUSE