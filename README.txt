README
======

## Play framework ##

1. Lataa Play Framework 2.1.1
        http://downloads.typesafe.com/play/2.1.1/play-2.1.1.zip
2. Pura ladattu ZIP-paketti
3. Lisää puretun kansion sijainti PATH-ympäristömuuttujaan (varmista myös, että Java on ympäristömuuttujassa)
        http://geekswithblogs.net/renso/archive/2009/10/21/how-to-set-the-windows-path-in-windows-7.aspx
        http://www.linuxheadquarters.com/howto/basic/path.shtml
4. Mene komentorivillä hakemistoon, jossa web-sovellus sijaitsee
5. Käännä web-sovellus
        play clean compile
6. Käynnistä web-sovellus
        play run
7. Konsultoi jatkossa dokumentaatiota http://www.playframework.com/documentation/2.1.1/Home

## Creating a standalone version ##

sbt dist

tai

play dist

## Genereting javadoc & scaladoc ##

play app-docs
