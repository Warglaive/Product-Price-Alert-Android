# Product Price Alert 
Android Java(Front-end) application with Loopback 4 as Back-end to handle HTTP/S Requests to/from a MongoDB Datasource, hosted on Heroku cloud.

# Dependency
# Works with https://github.com/Warglaive/Android-LB4-Backend 
# Goal 
Potential customers that are interested in certain products, but do not want to buy the product, 
because of a high price, can get alerted when the price gets lowered or the product is offered with a 
discount. 
Products 
The products are located in a database. The database gets updated with price changes several times 
a day.  
# Browsing products  
The customer is able to browse the available products using an app. Browsing is done by several 
screens that give the customer the option to:

• View popular products 

• Filter products on several criteria 

• View details of a product 

When they see a product they like, they can choose to directly buy via the app or request a price 
alert. 
In case the user goes for a price alert, he provides the app with a maximum price he wants to pay for 
the product. 
The application listens for price changes. Whenever the price of the product decreases to an amount 
that is below or equal to the maximum amount given by the customer, the app will alert the 
customer with a notification.  
# Buying products 
When the customer chooses to buy the app, he will be redirected to a mobile website, which fulfills 
the order. 
