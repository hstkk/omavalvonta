CloudFoundry
============

1. sudo gem install vmc
2. vmc target api.cloudfoundry.com
3. vmc login
4. play -Dconfig.resource=cloudfoundry.conf dist
5. vmc push --path=dist/omavalvonta-1.0-SNAPSHOT.zip
Application Name: omavalvonta
Detected a Play Framework Application, is this correct? [Yn]: y
Application Deployed URL [omavalvonta.cloudfoundry.com]: y
Memory reservation (128M, 256M, 512M, 1G, 2G) [256M]: 
How many instances? [1]: 
Create services to bind to 'omavalvonta'? [yN]: y
1: mongodb
2: mysql
3: postgresql
4: rabbitmq
5: redis
What kind of service?: 2
Specify the name of the service [mysql-1cbce]: 
Create another? [yN]: n
Would you like to save this configuration? [yN]: y
Manifest written to manifest.yml.
Creating Application: OK
Creating Service [mysql-1cbce]: OK
Binding Service [mysql-1cbce]: OK
Uploading Application:
  Checking for available resources: OK
  Processing resources: OK
  Packing application: OK
  Uploading (14M): OK   
Push Status: OK
Staging Application 'omavalvonta': OK                                           
Starting Application 'omavalvonta': OK

6. vmc update omavalvonta --path=dist/omavalvonta-1.0-SNAPSHOT.zip
