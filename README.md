Summary:

The project is a Coupon system that contains coupons being uploaded by Companies,
and can be viewed and bought by Customers using the client side, which is a website built with AngularJS, Html and CSS.

The server side is built uaing Java, Spring and JDBC.

And for the Database SQLServer is used.

Main Features:
-- Login system based on a token that is being requested from the Website(client) and managed by the Server,the server generates a 
random token using a java.util.UUID Library. 

-- Coupon cleanup thread - deletes all the expired coupons from the Database(check the end date of the coupon each day)

--three way system(Facade system):
1.Administrator.
2.Company.
3.Customer.

Project Structure:

Back-End:

At the base of the Project there is the Database:SQLServer

there are 3 classes that are connected to him, Admin, Company and Customer Repositories -- they have all the commands that speak directly 
to the Database like C.U.R.D(Create, Update, Read, Delete).

then there are 3 classes that are DBDAO (connection between the facades and the repositories), Admin, Company and Customer DBDAO -- 
thay have methods that use the repository's methods to talk to the Database using Spring Framework.

then there are 3 classes that are the Facades(the side that is closest to the client) -- their methods are most of the functionality and methodology all the validations the checks the flagging happends in the facades, it is the heart of the server.

then there are 3 classes that are Controllers(the connector between the requests from the client-side and the responses of the server-side using REST) -- they have methods that are being used by the client-side.

Front-End(still under development, version of the website in the files is not updated)

it is a website being built with HTML, CSS and AngularJS(Type Script).

Main Features:


