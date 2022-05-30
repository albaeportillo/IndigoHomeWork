
## Requirements
 - App Module
    - Update `app` to allow for a Login page, a list of Bids, and a Bid details page.
 - Network Module
    - The module contains a few mock services that you can use to exercise different cases.
        - `AuthService`
            - has a `login` method.  If the email doesn't contain `indigo` you will get an error, and password must be > 5 characters
        - `BidsService`
            - has `streamingBids` which gets back a list of bids and will continue publish updated bids as prices change
            - has `streamingBid` a streaming call that will give the details of a bid given the id
 - Login
    - Using `AuthService` require the user to "login" before they can view their streaming bids.
    - The login page should display errors that are returned from the service
    - Can be dismissed after successful response
 - Bid List
    - Using `BidsService` show a list of Bid objects to the user from `streamingBids`
    - Always display the latest price of each bid as the streaming service gives values
    - Allow some Filtering/Sorting based on some bid properties (at least one of each)
    - Design is up to you, more of a focus on technical concepts and data layout then aesthetics.
 - Bid Detail
    - Using `BidsService` show the details of a Bid when it is tapped by the user
    - Display all information about the Bid.
    - Allow the user to go back and see details of another Bid
    - Design is up to you, more of a focus on technical concepts and data layout then aesthetics.
 - Extra
    - Showing your expertise in one of the following areas (you can choose your own as well, but this is an area to impress)
        - Add an animation when bid prices update (something custom)
        - Add location/mapping information to the Bid details
        - Use storage solution to cache the login so it isn't needed every launch.

## The Repo
 The project currently has some basic application setup included with it.  There is a `Network` module that you can use to pull down data as a `List<Bid>`.  You shouldn't have to write any code in the Network module.

## Timing
You have up to 5 days to accomplish this assignment.  If you don't get through everything in the 5 days, then it is ok to send in what you have.  If it takes less than a few hours to accomplish, then it is likely a good idea to spend a bit more time showing us you have a good understanding of how to build Android apps in the project or dive into some of the extra features.

 ## Write up
 A brief description describing how you went about solving the above problem.  If you were writing this as a PR, what would you write to describe what you did to your team.  A short video recording of the app working and exercising the above requirements is also helpful.

##Upgrade HomeWork Indigo
Implementing MVVM, Koint, Coroutines.

   Short Video IndigoAg 
   ![](https://github.com/albaeportillo/IndigoHomeWork/blob/main/imgs/Indigo_APortillo_HomeWork.gif)
   
By: Alba Portillo