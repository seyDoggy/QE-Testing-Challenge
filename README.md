
## QE Testing Challenge
##### A practical excercise for prospective Quality Engineering candidates

### Intructions 

1. The objective is to add sufficient test coverage for the native mobile Android application that was built to the below acceptance criteria.
1. The data required for each view is contained in the `/data` folder.
1. Please inlude any additional acceptance criteria, test cases or scenrarios that the acceptance criteria does not inherently cover.
1. Provide any instructions required to run your tests.
1. Instructions to share your code back with us will have been provided via email.

### Original Acceptance Criteria

**SCENARIO: The user launches your application for the first time.**   
GIVEN the user is on the home screen of their mobile device   
AND the user has never opened the application before    
WHEN the user opens the application    
THEN the user is presented with a landing page with a button labeled "Open".
___

**SCENARIO: The user taps "Open"**   
GIVEN the user is on the landing page   
WHEN the user taps the "Open" button   
THEN the user is taken to a page with a list of their accounts   
AND some data for each account    
AND the page has a "Quit" button in the navigation bar.
___

**SCENARIO: The user launches your application successive times**   
GIVEN the user is on the home screen of their mobile device    
AND the user has previously opened the application before     
AND the user has not clicked "Quit" during their previous session    
WHEN the user opens the application    
THEN the user is taken to a page with a list of their accounts    
AND some data for each account   
AND the page has a "Quit" button in the navigation bar.
___

**SCENARIO: The user wants to view the transactions of a particular account**      
GIVEN the user is viewing a list of accounts    
WHEN the user taps on a specific account    
THEN the user is presented with a view containing a transaction history for said account.
___

**SCENARIO: The user wants to stop viewing a list of accounts**      
GIVEN the user is viewing a list of accounts    
WHEN the user taps the "Quit" button    
THEN the user is presented with a landing page with a button labeled "Open".
