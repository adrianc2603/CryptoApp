# CryptoApp

This application allows you to access information on cryptocurrencies. It uses the 
CoinMarketCap REST API to access this. Information on a cryptocurrency can also be
sent as an SMS to a phone number. This is done using the Twilio REST API. The 
CoinMarketCap API is known as the input API; the Twilio API is known as the output API.

With this app, the user can perform 4 functionalities:
1. get a list of cryptocurrencies
2. get info on a given cryptocurrency
    - can be retrieved from a database cache (if it is already stored)
    - can be retrieved from the API (which will update the database cache)  
3. convert between two currencies with a given amount
4. send a cryptocurrency information report to a configured phone number
    - from the database (if it is already stored)
    - from the API (which updates the database)

## Using this Application
Please note that there are two versions of each API - an online one and an offline one.
To access the online versions of the APIs, a user must enter their respective API 
authentication tokens. This can be stored in the config.json file located in
src/main/resources. The online APIs will provide the user with real-time cryptocurrency 
information, whereas the offline APIs will provide stubbed, hardcoded information.

Please note that this application requires the Gradle build tool, and was developed using Java 11. To run the application, type in to the command line "gradle run --args="input output", replacing both 'input' 
and 'output' with either 'online' or 'offline', depending on which version of the 
input/output APIs you wish to use. 

1. [Install Gradle](https://gradle.org/install/)

2. Run using offline input API and offline output API:

            gradle run --args="offline offline"
            
3. Run using offline input API and online output API:

            gradle run --args="offline online"
            
4. Run using online input API and offline output API:

            gradle run --args="online offline"
            
5. Run using online input API and online output API:

            gradle run --args="online online"            

When the application launches, the user is asked to enter a balance. This balance 
represents the upperbound on an amount the user can convert with. Once a valid balance 
is entered, the user can then access the four functionalities of the application.

## Any Quirks to Running Application
On the application's first run, the database file database.db will be created - located 
in src/main/resources/. This database stores cryptocurrency information retrieved from 
the input API and is persistent across multiple runs (until the database.db file is 
physically deleted).

When a user is asked to enter any fields in the GUI, such as a cryptocurrency
symbol or amount to convert, they must enter the field in the respective
text box and click the corresponding "Submit" button. Once this is done for
each of the required fields, the user can then press the "Enter" button
displayed at the bottom of the window. Failure to Submit all fields will result
in failure to process the users request - and the user will again be asked to
enter their fields. Additionally, please note that when required to enter a
cryptocurrency symbol, this is case-insensitve. For example, a user wishing to
get info on bitcoin can enter either "BTC" or "btc".

When a report is sent on a cryptocurrency, it will contain the conversion from the 
base currency (with amount equal to the balance set upon application launch) to the 
cryptocurrency being reported on. This base currency is initially not set, and can 
only be set through the "convert" functionality (it is indicated which currency will 
be set as the base currency). If this is not set, the sent report will not contain a 
conversion, but will instead contain a message stating the base currency was not set. 
Additionally, if an error occurs while converting, the sent report will instead contain
a message stating that something went wrong and a conversion could not be made.

## Citations for Code I Did Not Write
I have included code used to access APIs with Apache HttpClient. Although not
a direct copy, my code was influenced by information found on the following
website: https://zetcode.com/java/getpostrequest/. This code is located in the
OnlineInput.java and OnlineOutput.java files. I have indicated the beginning
and end of the copied code blocks within these files.

I have also included code used to create and access my SQLite database. Although
not a direct copy, my code was influenced by information found on the following
website: https://github.com/xerial/sqlite-jdbc. This code is located in the
DatabaseImp.java file. I have indicated the beginning and end of the copied code
block within this file.

