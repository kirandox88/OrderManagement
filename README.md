# E Commerce Order Management

Your task is to develop a simple RESTful webservice that implements a common e-commerce scenario, placing and modifying orders. We have provided you with an mavenized starter template that provides you with the necessary dependencies to build, test and run a webservice using the following:

- Core tools:
    - JDK 7
    - Maven 3
- Compile scope:
    - Guava
    - slf4j/logback
    - Spring
    - Hibernate
    - Jersey / JAX-RS
    - Jackson
    - H2 embedded database
- Test scope:
    - JUnit/Hamcrest
    - Mockito
    - PowerMock
    - Spring Test
    - dbUnit

You may add additional dependencies, tools and maven plugins as you deem necessary to complete your application. 

# REST API

Your service should consist of two RESTful resources:

## Products
	
which should implement two methods:
		
- list product catalog
- read a single product by its id
			
## Orders
	
which should implement four methods:
		
- place an order
- modify an existing order
- list placed orders
- read an existing order by its id
			
Your service should use JSON as its representation format for objects
			
You will need to create an appropriate relational schema to persist your entities, along with any required mappings.

### Delivery Instructions ###

1. You must provide his BitBucket username. A free BitBucket account can be created at http://bitbucket.org
1. The recruiter will give you read permission to a repository named **java-challenge**, at https://bitbucket.org/ac-recruitment/java-challenge
1. You must fork this repository into a private repository on your own account and push your code in there.
1. Once finished, you must give the user **ac-recruitment** read permission on your repository so that you can be evaluated. Then, please contact back your recruiter and he will get an engineer to evaluate your test.
1. After you are evaluated, the recruiter will remove your read permission from the original repository.

### Format ###

* This assessment must be delivered within 2 days.
* You must provide a README.txt (plain text) or a README.md (Markdown) file at the root of your repository, explaining:
    * How to compile and run the application.
    * How to run the suite of automated tests.
    * Mention anything that was asked but not delivered and why, and any additional comments.
* Any questions, please send an email to **recruitment.engineering@avenuecode.com**

Thank you,
The AvenueCode Recruiting Team




Please follow below steps for compiling and running the application.

- Download the project.
- Export it into eclipse/STS as a maven project.
- Go to root directory of the project in the teminal and execute below command.
mvn clean install -DskipTest=true
- I have created the appilcation in Spring Boot so please right click on class OrderManagementDemoApplication > Run As > Spring boot app.
- Default tomcat will be started.
- Use any client like poster/postman to call below RESTful webservices.
- Use below link for accessing H2 DB.
  http://localhost:8080/h2
- If tables are not present please execute the below attached queries(ideally it wont,not sure whether tables and data persists after exiting the session.)
 
 Note : I could not write the test cases for all the classes as i didnt get much time.

******************************************************************************
For Accessing DB:
http://localhost:8080/h2

******************************************************************************
DB Scripts:

CREATE SEQUENCE Order_Seq;
CREATE SEQUENCE Product_Seq;
CREATE TABLE OrderDetails(orderId bigint default Order_Seq.nextval PRIMARY KEY ,
							OrderNumber VARCHAR(255),
							OrderDescription VARCHAR(255),
							OrderDate Date, 
							ShippingAddress VARCHAR(255), 
							OrderStatus VARCHAR(255), 
	 						productId INT, 
	 						FOREIGN KEY (productId) REFERENCES ProductDetails(productId));
	 						
	 						
CREATE TABLE ProductDetails(ProductId bigint default Product_Seq.nextval PRIMARY KEY,
							ProductDescription VARCHAR(255),
							ProdcutSeller VARCHAR(255),
							ProductPrice Long);
							
INSERT INTO OrderDetails(OrderNumber,OrderDescription,OrderDate,ShippingAddress,OrderStatus,productId) values (1234, 'Shoe Order', CURRENT_DATE(), '123 San Jose', 'Shipped', 1);
INSERT INTO OrderDetails(OrderNumber,OrderDescription,OrderDate,ShippingAddress,OrderStatus,productId) values (2234, 'Shoe Order', CURRENT_DATE(), '34 Santa Clara', 'Shipped', 2);

INSERT INTO PRODUCTDETAILS (PRODUCTDESCRIPTION ,PRODCUTSELLER ,PRODUCTPRICE ) VALUES ('Adidas Shoe', 'ABC seller',80.9);
INSERT INTO PRODUCTDETAILS (PRODUCTDESCRIPTION ,PRODCUTSELLER ,PRODUCTPRICE ) VALUES ('Nike Shoe', 'DEF seller',85);


******************************************************************************

1.Fetch Product Catalog List
http://localhost:8080/product/product_catalog_list

******************************************************************************

2.Fetch Product by Id:
http://localhost:8080/product/fetch_Product

{
	
"productId" : "1"
}

******************************************************************************

3.Place Order:
http://localhost:8080/order/place_order

{
	
"orderDescription" : "Shoe order",
"shippingAddress":"123 Las Gatos",
"productId":"2"

}

******************************************************************************
4. Fetch Order List:
http://localhost:8080/order/order_list

******************************************************************************

5. Read order by its id.

http://localhost:8080/order/fetch_Order
{	
"orderId" : "10"
}

******************************************************************************

6. Modify an order
http://localhost:8080/order/modify_order

{
"orderNumber": "1234",
"orderDescription": "Shoe Order",
"shippingAddress": "456 San Jose",
"productId": 2
            
}
******************************************************************************
