#!/bin/sh

# Update OS
echo "deb mirror://mirrors.ubuntu.com/mirrors.txt trusty main universe" > /etc/apt/sources.list
apt-get update
apt-get upgrade -y

# Install dependencies
aptitude install unzip openjdk-7-jdk

# MYSQL
aptitude install mysql-server
cp configuration/etc/mysql/my.cnf /etc/mysql/my.cnf
mysql_secure_installation
mysql -uroot -p < sql/bootstrap.sql
service mysql restart

# Apache
aptitude install apache2 openssl
a2enmod rewrite ssl proxy proxy_http
rm /etc/apache2/sites-enabled/000-default.conf
cp configuration/etc/apache2/sites-enabled/omavalvonta.conf /etc/apache2/sites-enabled/omavalvonta.conf
cp configuration/testshib/etc/apache2/conf-enabled/fqdn.con /etc/apache2/conf-enabled/fqdn.con
mkdir /etc/apache2/ssl
openssl req -new -x509 -days 365 -nodes -out /etc/apache2/ssl/apache.pem -keyout /etc/apache2/ssl/apache.key -subj "/C=FI/ST=Kanta-Hame/L=Hameenlinna/O=HAMK Hameen ammattikorkeakoulu/OU=Tietohallinto/CN=omavalvonta.dy.fi"
service apache2 restart

# Shibboleth
aptitude install libapache2-mod-shib2
shib-keygen -y 3 -h omavalvonta.dy.fi -e https://omavalvonta.dy.fi/shibboleth
mkdir /etc/apache2/conf.d/
cp configuration/etc/apache2/conf.d/shib.conf /etc/apache2/conf.d/shib.conf
echo "###################################################################"
echo "Register provider to Testshib http://www.testshib.org/register.html"
echo "###################################################################"
cp configuration/testshib/etc/shibboleth/shibboleth2.xml /etc/shibboleth/shibboleth2.xml
cp configuration/testshib/etc/shibboleth/attribute-map.xml /etc/shibboleth/attribute-map.xml
service shibd restart
service apache2 restart

# Omavalvonta
useradd -m -s /bin/false omavalvonta
cp configuration/testshib/etc/init/omavalvonta.conf /etc/init/omavalvonta.conf
unzip -d /home/omavalvonta target/universal/omavalvonta-1.0.zip
cp configuration/testshib/home/omavalvonta/omavalvonta-1.0/conf/production.conf /home/omavalvonta/omavalvonta-1.0/conf/production.conf
chown -R omavalvonta:omavalvonta /home/omavalvonta/omavalvonta-1.0
chmod +x /home/omavalvonta/omavalvonta-1.0/bin/omavalvonta
mysql -uroot -p omavalvonta < sql/db.sql
service omavalvonta start