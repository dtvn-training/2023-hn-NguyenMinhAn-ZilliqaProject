
# Zilliqa Project

## Clone Project
You need to have git in desktop and perform these scripts  
```
  $ git clone https://github.com/dtvn-training/2023-hn-NguyenMinhAn-ZilliqaProject.git
```
## 1. Environment:
  - Install JDK 8 or later: [JDK 8 Download](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html) 

## 2. Setup Database:
- [Download MySql](https://www.mysql.com/downloads/)
- Create a MySQL database for this project
- Add to your application.properties
```
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
    spring.jpa.properties.hibernate.format_sql=true
    spring.jpa.database=mysql
    
    spring.datasource.username= [Your username here]
    spring.datasource.password= [Your password here]
    spring.datasource.url=jdbc:mysql://localhost:3306/[Your database you've create]
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```
## 3. Setup BigQuery:
- Create a project and setup:
  - Go to Google cloud console: [Google Cloud Console](https://console.cloud.google.com/) and Login
  - Create a project 
  - Go to APIs and Services -> Credentials
  - Create a credential -> Service Account -> Create a Service Account
  - Choose this account and create a key for this account
  - Download credential file
  - Go to resources -> Create a SecretKey folder -> save credential file to this folder
  - Get path to this credential file
  - Go to BigQueryService file and add path of credential file
  - Example: 
    ```
    File file = new File("D:\\Workspace\\Spring\\ZilliqaProject\\2023-hn-NguyenMinhAn-ZilliqaProject\\zilliqa_project\\src\\main\\resources\\SecretKey\\blissful-hash-405809-c65665ec1609.json");
    ```

## 4. Setup Redis:
- You need to download Redis: [Redis Download](https://redis.io/download/)
- Create a Redis database for this project
- Add to your application.properties
```
    redis.host=[Your host here]
    redis.port=[Your port here]
    redis.username=[Your username here]
    redis.password=[Your password here]
```

## 5. Run project:
- Run without argument
```
    src/main/java/com/annm/zilliqa_project/ZilliqaProjectApplication.java
```

## 6. UI:
- App will run in port 9999: http://localhost:9999/overview


## Contact

Minh An Nguyen - annm01032002@gmail.com

