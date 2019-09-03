Summary:

The project is a Coupon system that contains coupons being uploaded by Companies,
and can be viewed and bought by Customers using the client side, which is a website built with AngularJS, Html and CSS.

The server side is built uaing Java, Spring and JDBC.

And for the Database SQLServer is used.

Main Features:
-- Login system based on a token that is being requested from the Website(client) and managed by the Server,the server generates a 
random token using a (library import from java put here!!!! ). 

--three way system:
1.Administrator.
2.Company.
3.Customer.

Project Structure:

At the base of the Project there is the Database:SQLServer

there are 3 classes that are connected to him, Admin, Company and Customer Repositories -- they have all the commands that speak directly 
to the Database like C.U.R.D(Create, Update, Read, Delete).

then there are 3 classes that are DBDAO (connection between the facades and the repositories), Admin, Company and Customer DBDAO -- 
(insert info about the DBDAO).

then there are 3 classes that are the Facades(the side that is closest to the client) -- they have commands that are being used in the
controllers throught requests from the client-side(website) and they call the functions in the DBDAO.
