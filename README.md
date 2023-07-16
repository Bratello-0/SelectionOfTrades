# Selection Of Trades 1.0

Для запуска требуется установленные java, maven, postgresSql.
В посгрес заранее должна быть создана база данных.
Приложение запускалось на процессоре AMD FX6300, 8gb ОЗУ DDR3 частатой 1333mg, nVideo gts 450 512mb
и жестком диске объемом 1 терабайт.

Для запуска приложения необходимо создать .bat файл со следующими параметрами:
pause
java -jar SelectionOfTrades-1.0-SNAPSHOT.jar --spring.datasource.url=jdbc:postgresql://localhost:5432/Trade 
--spring.datasource.username=postgres 
--spring.datasource.password=fullPrice --spring.jpa.hibernate.ddl-auto=update
pause

spring.datasource.url - указывается адресс сервера базы данных postgres.

spring.datasource.username - имя пользователя в бд.

spring.datasource.password - пароль от пользователя.

spring.jpa.hibernate.ddl-auto - протокол запуска, для первого запуска нужно прописать значение update.