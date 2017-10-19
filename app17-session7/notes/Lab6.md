# Lab 6

## Configuration

### Add a new webapp to Tomcat

* Choose `Run` and `Edit Configurations...`

* Under `Tomcat Server`, select `Tomcat` (or another name if you chose a different name for your configuration)
* Click `Deployment` to go to deployment tab
* Click `+` and select `External Source`
* Navigate to this project down to folder `web` under `src`
* Leave `Application Context` as `/`
* Save
* Restart Tomcat and navigate to http://localhost:8080/
* You should see the contents of `src/web/index.html` rendered in the browser



