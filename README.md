Summary:

The project is a Coupon system that contains coupons being uploaded by Companies, and can be viewed and bought by Customers using the client side.

Client side: HTML, CSS and AngularJS(Type Script).

Server side: Java, Spring and JDBC.

Database: SQLServer.

 **Project Structure:**

The Project is divided to 2 main parts: Back-end and Front-end.

  **Back-End:**

    The back-end has 5 layers:

  1.The first layer is the Database(SQLServer) -- There are 5 tables in the Database:

     # Companies Table - the information of all the companies that have registered into the website.
     # Customers Table - the information of all the customers that have registered into the website.
     # Coupons Table - the information of all the coupons that have been created by the companies.
     # Customers_VS_Coupons - the information of all the Purchases of coupons that have been made by customers.
     # Categories - the information of all the categories available for the Coupon system.


  2.The second layer is the Repositories -- there are 3 Repositories, one for each user type: 
     1. Admin
     2. Company
     3. Customer

     All the 3 of the classes extend the Class JPARepository

  3.The third layer is the DBDAO -- there are 3 DBDAO, one for each user type:
    1. Admin
    2. Company
    3. Customer

    All the classes @Autowire their user type repositories, and use the built methods in the repository to make basic actions 
    on the Database like CRUD(Create, Read, Update, Delete):

    Using methods like save(), findById(), findAll() and some custom query.

   4.The fourth layer is the Facade -- there are 3 Facades, one for each user type:
      1. Admin
      2. Company
      3. Customer

      All the 3 Facade classes extend a main facade class that @Autowire all the 3 DBDAO.

      The Facade is the main layer of the project, it holds all the validations and all the methodology of the server side like:

        1. Login Method that checks the Database(admin login is hard coded), generates a token and returns it.
        2. Adding new coupons, companies or customers.
        3. Updating coupons, companies or customers.
        4. Deleting coupons, companies or customers.
        5. Extracting information about coupons, companies or customers.

   5. The fifth and last layer is the Controllers(Rest) -- there are 3 Controllers, one for each user type:

        The controllers has a method for each method that exists in the facades but it connects the server to the website 
        using rest.

   More Features:

      # Login Manager - handles the login to the server, using the facades login Methods, based on a token that is being requested from the     
      Website (client) and managed by the Server, the server generates a random token using a java.util.UUID Library and returns it to the 
      client side.

      # Daily Thread - checks each day for Coupons that are expired.


Front-End: (still under development, version of the website in the files is not updated)

