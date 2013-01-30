# Omavalvonta & OpenSuse

## OpenSusen päivittäminen

    zypper refresh
    zypper update

# MySQL

- MySQL-palvelimen asennus

    zypper install mysql-community-server

- Käynnistetään MySQL daemon automaattisesti

    chkconfig --add mysql

- Käynnistetään MySQL daemon

    rcmysql start

- Konfiguroidaan MySQL

    mysql_secure_installation

    NOTE: RUNNING ALL PARTS OF THIS SCRIPT IS RECOMMENDED FOR ALL MySQL
          SERVERS IN PRODUCTION USE!  PLEASE READ EACH STEP CAREFULLY!

    
    In order to log into MySQL to secure it, we'll need the current
    password for the root user.  If you've just installed MySQL, and
    you haven't set the root password yet, the password will be blank,
    so you should just press enter here.
    
    Enter current password for root (enter for none): 
    OK, successfully used password, moving on...
    
    Setting the root password ensures that nobody can log into the MySQL
    root user without the proper authorisation.
    
    Set root password? [Y/n] y
    New password: 
    Re-enter new password: 
    Password updated successfully!
    Reloading privilege tables..
     ... Success!
    
    
    By default, a MySQL installation has an anonymous user, allowing anyone
    to log into MySQL without having to have a user account created for
    them.  This is intended only for testing, and to make the installation
    go a bit smoother.  You should remove them before moving into a
    production environment.
    
    Remove anonymous users? [Y/n] y
     ... Success!
    
    Normally, root should only be allowed to connect from 'localhost'.  This
    ensures that someone cannot guess at the root password from the network.
    
    Disallow root login remotely? [Y/n] y
     ... Success!
    
    By default, MySQL comes with a database named 'test' that anyone can
    access.  This is also intended only for testing, and should be removed
    before moving into a production environment.
    
    Remove test database and access to it? [Y/n] y
     - Dropping test database...
     ... Success!
     - Removing privileges on test database...
     ... Success!
    
    Reloading the privilege tables will ensure that all changes made so far
    will take effect immediately.
    
    Reload privilege tables now? [Y/n] y
     ... Success!
    
    Cleaning up...
    
    
    
    All done!  If you've completed all of the above steps, your MySQL
    installation should now be secure.
    
    Thanks for using MySQL!

- Kirjaudutaan MySQL-palvelimelle

    mysql -u root -p

- Lisätään omavalvonnalle oma tietokanta

    mysql> create database omavalvonta;

- Lisätään Omavalvonnalle oma MySQL-käyttäjä

    mysql> grant CREATE,INSERT,DELETE,UPDATE,SELECT on omavalvonta.* to omavalvonta@localhost;

- Määritetään käyttäjän omavalvonta salasana

    mysql> set password for omavalvonta@localhost= password('salasana');

- Päivitetään käyttöoikeudet

    mysql> flush privileges;

## Apache

- Apachen asennus

    zypper install apache2

- Käynnistetään Apache daemon automaattisesti

    chkconfig --add apache2

- Käynnistetään Apache daemon

    rcapache2 start

### SSL-sertifikaatti

- Aktivoidaan Apchen uudelleenkirjoitus-moduuli
    a2enmod rewrite

- Aktivoidaan Apachen SSL-moduuli

    a2enmod ssl

- Tehdään SSL-sertifikaateille oma hakemisto

    mkdir /etc/apache2/ssl

- Generoidaan itseallekirjoitettu sertifikaatti

    openssl req -new -x509 -days 365 -nodes -out /etc/apache2/ssl/apache.pem -keyout /etc/apache2/ssl/apache.key

    Generating a 1024 bit RSA private key
    .....++++++
    .......++++++
    writing new private key to '/etc/apache2/ssl/apache.key'
    -----
    You are about to be asked to enter information that will be incorporated
    into your certificate request.
    What you are about to enter is what is called a Distinguished Name or a DN.
    There are quite a few fields but you can leave some blank
    For some fields there will be a default value,
    If you enter '.', the field will be left blank.
    -----
    Country Name (2 letter code) [AU]:FI
    State or Province Name (full name) [Some-State]:
    Locality Name (eg, city) []:
    Organization Name (eg, company) [Internet Widgits Pty Ltd]:HAMK Hameen ammattikorkeakoulu
    Organizational Unit Name (eg, section) []:Tietohallinto
    Common Name (eg, YOUR name) []:
    Email Address []:

### Proxy

- Aktivoidaan Apachen proxy-moduuli

    a2enmod proxy

### Konfigurointi

- 

    echo "UseCanonicalName On" >> /etc/apache2/httpd.conf

- 

    #/etc/apache2/vhosts.d/omavalvonta.conf
    LoadModule rewrite_module modules/mod_rewrite.so
    LoadModule proxy_module modules/mod_proxy.so
    
    <VirtualHost *:80>
      RewriteEngine On
      RewriteCond %{HTTPS} off
      RewriteRule .* https://%{SERVER_NAME}%{REQUEST_URI} [R,L]
    </VirtualHost>
    
    <VirtualHost *:443>
      ServerName omavalvonta.local
    
      # SSL
      SSLEngine On
      SSLCertificateFile /etc/apache2/ssl/apache.pem
      SSLCertificateKeyFile /etc/apache2/ssl/apache.key
    
      # Proxy
      ProxyPreserveHost On
      ProxyPass /Shibboleth.sso !
      ProxyPass / http://127.0.0.1:9000/
      ProxyPassReverse / http://127.0.0.1:9000/
    </VirtualHost>

- Käynnistetään Apache uudelleen

    rcapache2 restart

## Shibboleth

- Lisätään Shibboleth repo

    zypper addrepo -f http://download.opensuse.org/repositories/security://shibboleth/openSUSE_12.1/security:shibboleth.repo

- Asennetaan Shibboleth service provider

    zypper install shibboleth

- Konfiguroidaan Shibboleth toimimaan Apachen kanssa

    #/etc/apache2/conf.d/shib.conf
    LoadModule mod_shib /usr/lib/shibboleth/mod_shib_22.so
    
    <Location /Shibboleth.sso>
      SetHandler shib
    </Location>

- Käynnistetään Shibboleth-daemon uudelleen

    rcshibd restart

- Käynnistetään Apache uudelleen

    rcapache2 restart