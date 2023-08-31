# proselyte-http
#### Учебный проект
    Небольшое REST API приложение, которое взаимодействует с файловым хранилищем 
    и предоставляет возможность получать доступ к файлам и истории загрузок.
#### Требуемые ресурсы:
#### - Java 11
#### - база данных:
    - url=jdbc:h2:./db/http
    - username=user
    - password=password
    использована база данных H2 для возможности сборки проекта в travis-ci на github

#### Для запуска необходимо:
    - склонировать репозиторий: git clone git@github.com:akrecev/proselyte-http.git
    - запустить сборку проекта maven -> package
    - запустить tomcat - mvn tomcat7:run

#### Для взаимодействия с программой использовать Postman:
    - коллекция запросов: postman/Proselyte-Http.postman_collection.json