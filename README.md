This is a simple demo project for URL Shortener, where user can input a long URL and get a short link. By clicking that short link on the new tab opens the link of the full URL.
The project is created with spring boot and react framwork. Here on the master branch contains spring backend project and frontend branch contains react frontend project.

# Import backend project from master branch
To run the project we need Maven, JDK and IDE.In this project in memory H2 database used to store data. To configure this we need to create a data folder on project's root directory and edit project's application.properties file and add these configuration-

			spring.datasource.url=jdbc:h2:mem:devdb
			spring.datasource.driverClassName=org.h2.Driver
			spring.datasource.username=sa
			spring.datasource.password=
			spring.jpa.database-platform=org.hibernate.dialect.H2Dialect


# Rest Endpoints
To post a long URL path will be {localhost:8080/api/urlshortner} and requst body will contains a string of type text. This request returns List of all short form that was entered, which can be populated on frontend.

	RequestBody = https://www.google.com
	Respone will be 
						[
					{
						"longUrl": "https://spring.io/guides/gs/testing-restdocs/\r\n",
						"key": "sgGoOWNc"
					},
					{
						"longUrl": "https://www.google.com\r\n",
						"key": "Raabl3qV"
					}
				]
				
To get All saved short urls we need to provide a GET request to the path {localhost:8080/api/urlshortner}, that returns all the generated urls.

To redirect from any generated urls to it's original source, we need to perform a GET request with generated url as parameter like {localhost:8080/api/urlshortner/Raabl3qV} and it will redirect to the original page on a new tab;


# Import Frontend React project from frontend branch

To run react app we need To install node.js on our project environment.To connect spring backend server we need to edit package.json file and the proxy server after scripts

  "proxy": "http://localhost:8080",
  
we need to navigate to the projects app directory and run the command npm start. This will run the react server which will be deployed. By default it will be deployed on http://localhost:3000. This page contains a input textbox, a submit button and list of short links. At this stage we need to modify ShortUrlAnchor.js file and put the hardcoded spring backend absolute path.

# Frontend view will be
![scrennshot](https://user-images.githubusercontent.com/33452265/154990273-329d8eca-0022-47a8-bb12-4d05e98d3bc3.PNG)


	
