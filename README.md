# README

## Play framework

1. Lataa Play Framework 2.2.3
        http://downloads.typesafe.com/play/2.2.3/play-2.2.3.zip
2. Pura ladattu ZIP-paketti
3. Lisää puretun kansion sijainti PATH-ympäristömuuttujaan (varmista myös, että Java on ympäristömuuttujassa)
        http://geekswithblogs.net/renso/archive/2009/10/21/how-to-set-the-windows-path-in-windows-7.aspx
        http://www.linuxheadquarters.com/howto/basic/path.shtml
4. Mene komentorivillä hakemistoon, jossa web-sovellus sijaitsee
5. Käännä web-sovellus
        play clean compile
6. Käynnistä web-sovellus
        play run
7. Konsultoi jatkossa dokumentaatiota http://www.playframework.com/documentation/2.2.x/Home

## Create a standalone version

`play dist`

## Generate javadoc and scaladoc

`play unidoc`

    target
    ├── javaunidoc
    └── scala-2.10
        └── unidoc

## Docker

1. Install
   - http://docs.docker.io/en/latest/installation/
1. Build docker container
   - `docker build -t play .`
2. Run docker container
   - `docker run -v $(pwd):/src -i -t play`
   - `docker run -v $(pwd):/src -i -t play dist`
   - `docker run -p 9000:9000 -v $(pwd):/src -i -t play run`

## Bootstrap production environment

`sudo sh bootstrap.sh`
