# macmillian
Quiz code entry. 

As I read the assignment, it was essentially download the Spring REST tutorial and enhance to access an H2 db. This is how I would normally start a real-world project. Once ideas are worked out this prototype may become obsolete, but it's a good way to start.

Note that the package structure is flat due to the simplicity of the project. Were it to grow, new packages would be added dividing the classes into functional groups.

To run from the command line using default port of 8090 (set in src.main/resources/application.properties):
> java -jar build/libs/simple-movie-api-0.1.0.jar

To change the port at start up:
> java -jar -Dserver.port=<port> build/libs/simple-movie-api-0.1.0.jar

There are several tests included, but to test the runing app you can use variations of the following:

curl -i -X POST -H "Content-Type:application/json" -d '{"name": "Star Wars", "genre": "Sci-fi"}' http://localhost:8090/api/movie/create

curl -i -X POST -H "Content-Type:application/json" -d '{"name": "The Avengers", "genre": "Sci-fi"}' http://localhost:8090/api/movie/create

curl -i -X PUT -H "Content-Type:application/json" -d '{"id": "1", "name": "Star Wars", "genre": "Sci-fi", "yearReleased": "1977"}' http://localhost:8090/api/movie/update

curl -i -X DELETE  http://localhost:8090/api/movie/delete/1

curl -i -X GET  http://localhost:8090/api/movie/list

curl -i -X GET  http://localhost:8090/api/timeOfDay

