## Use cases 

|Use case|1|
|---------------|---|
|**Name**           |Browse products|
|**Actor**          |Customer, Product Manager|
|**Description**    |A User browses the products on the application.|
|**Pre-condition**  |None.|
|**Scenario**       |1. User chooses to browse products.<br>2. System displays list of products, filtering option and an option to view popular products.|
|**Result**         |User has seen the browsing options.|
|**Extensions**     |None.|
|**Exceptions**     |None.| 

|Use case|2|
|---|---|
|**Name**|View popular products|
|**Actor**|Customer|
|**Description**|Customer views popular products.|
|**Pre-condition**|None.|
|**Scenario**|1. Customer <ins>browses products</ins>.<br>2. Customer chooses to view popular products.<br>3. System shows a list of the most popular products.|
|**Result**|Customer has access to the list of most popular products.|
|**Extensions**|3a. Customer chooses to view a product's details.<br> 1. System shows the details of the chosen product.<br>3a. Customer chooses to request price alerts for a product.<br>1. System displays confirmation of the request and asks the user for maximum price input.<br>2. User provides the input.<br>3. System displays a confirmation.<br>3a. Customer chooses to purchase a product.<br>1. System redirects the user to an external website where the order can be completed.|
|**Exceptions**|None.|

|Use case|3|
|---|---|
|**Name**|View product's details|
|**Actor**|User|
|**Description**|User views product's details.|
|**Pre-condition**|None.|
|**Scenario**|1. User <ins>browses products</ins>.<br>2. User chooses to view a product's details.<br>3. System shows the details of the chosen product, including the product's location.|
|**Result**|User has access to a product's details.|
|**Extensions**|None.|
|**Exceptions**|None.|

|Use case|4|
|---|---|
|**Name**|Filter products based on a criteria|
|**Actor**|User|
|**Description**|User filters products based on a certain criteria.|
|**Pre-condition**|None.|
|**Scenario**|1. User <ins>browses products</ins>.<br>2. User chooses the filter option.<br>3. System displays an option for input. <br>4. User enters keyword for filter.<br>5. System shows lists of products matching the input.|
|**Result**|User has access to a list of products that match given filter criteria.|
|**Extensions**|5a. Customer chooses to view a product's details.<br> 1. System shows the details of the chosen product.<br>5a. Customer chooses to request price alerts for a product.<br>1. System displays confirmation of the request and asks the user for maximum price input.<br>2. User provides the input.<br>3. System displays a confirmation.<br>5a. Customer chooses to purchase a product.<br>1. System redirects the user to an external website where the order can be completed.|
|**Exceptions**|5. No products matching filter criteria.|

|Use case|5|
|---|---|
|**Name**|Request price alert|
|**Actor**|Customer|
|**Description**|Customer requests price alerts for a product.|
|**Pre-condition**|User must be logged in as a Customer.|
|**Scenario**|1. Customer <ins>browses products</ins>.<br>2. Customer chooses to request price alerts for a product.<br>3. System displays confirmation of the request and asks the user for maximum price input.<br>4. User provides the input.<br>5. System displays a confirmation.|
|**Result**|Customer has requested price alerts for a product and has given a maximum price they would pay for it.|
|**Extensions**|None.|
|**Exceptions**|None.|

|Use case|6|
|---|---|
|**Name**|Add product|
|**Actor**|Product Manager|
|**Description**|Product Manager adds a new product to the applcation.|
|**Pre-condition**|User must be logged in as a Product Manager.|
|**Scenario**|1. Product Manager chooses an option to add a product.<br>2. System asks the user for description, title and price of the product and displays an option to take a picture.<br>3. Product Manager inputs the information and confirms.<br>4. System displays a confirmation and shows the product's details.|
|**Result**|Product Manager has added a new product to the application.|
|**Extensions**|3a. User chooses to take a picture of the product.<br>1. System opens camera.<br>2. User chooses to take a picture.<br>3. System displays confirmation of the picture taken and asks if the location of the picture should be stored.<br>4. User chooses to store or not store the location.<br>5. System displays confirmation of the choice. Return to step 3.|
|**Exceptions**|4. System message: "Insufficient information!".<br>4.1 Use case ends here.|

|Use case|7|
|---|---|
|**Name**|Purchase product|
|**Actor**|Customer|
|**Description**|Customer purchases a product.|
|**Pre-condition**|User must be logged in as a Customer.|
|**Scenario**|1. Customer <ins>browses products</ins>.<br>2. Customer chooses to purchase a product.<br>3. System redirects the user to an external website where the order can be completed.|
|**Result**|Customer has purchased a product.|
|**Extensions**|None.|
|**Exceptions**|None.|

|Use case|8|
|---|---|
|**Name**|Log in|
|**Actor**|Customer/Product Manager|
|**Description**|User logs in the application.|
|**Pre-condition**|User must have an account (be registered).|
|**Scenario**|1. User chooses the log in option.<br>2. System displays option for input of an email and password.<br>3. User inputs information.<br>4. System displays home page with functionalities based on the type of user.|
|**Result**|User has logged in.|
|**Extensions**|None.|
|**Exceptions**|4. System message: "Incorrect login credentials!".<br>4.1 Use case ends here.|

|Use case|9|
|---|---|
|**Name**|Register|
|**Actor**|Customer/Product Manager|
|**Description**|User registers on the application.|
|**Pre-condition**|None.|
|**Scenario**|1. User chooses the register option.<br>2. System displays option for input of register details.<br>3. User inputs information.<br>4. System displays login.|
|**Result**|User has registered.|
|**Extensions**|None.|
|**Exceptions**|4. System message: "Insufficient input!".<br>4.1 Use case ends here.|

|Use case|10|
|---|---|
|**Name**|Receive price alert|
|**Actor**|Customer|
|**Description**|Customer receives price alert.|
|**Pre-condition**|User must be logged in as a Customer.|
|**Scenario**|1. |
|**Result**|Customer has received a price alert.|
|**Extensions**|None.|
|**Exceptions**|None.|

|Use case|11|
|---|---|
|**Name**|Send price alert (change product price)|
|**Actor**|Product Manager|
|**Description**|Product Manager sends a price alert.|
|**Pre-condition**|User must be logged in as a Product Manager.|
|**Scenario**|1. |
|**Result**|Product Manager has sent a price alert.|
|**Extensions**|None.|
|**Exceptions**|None.|
