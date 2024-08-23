# Testing Using Selenium Grid with Docker

# Setup 
- Download Rancher Desktop from Official Website
  https://rancherdesktop.io/

- Run the docker commands in terminal for the pulling images
   - Selenium/hub : docker pull selenium/hub
   - Chrome : docker pull selenium/standalone-chrome
   - Firefox : docker pull selenium/standalone-firefox

- To pull other browser images refer docker-hub official page https://hub.docker.com/search?image_filter=official

# Run contianers in the docker 
- Run the containers from docker-compose.yml (Run the services section)

# How to run the tests
- Right click on xml file and run as TestNG file.

# Tips
- Make sure your rancher is running and up for pulling images and running test cases.
- Check the nodes are linked with Selenium-Grid, browse the URL : https://localhost:4444/grid
