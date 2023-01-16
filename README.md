Ionita Cosmin 324 CA
@2022

*** Firstly, I want to say that the task is written so much better for this stage (https://www.youtube.com/watch?v=yNwYo6NwS2c), I actually understood all I needed to do by reading it, no need to search and ask for extra info.

*** Once again, let's get to the main flow of the program (https://www.youtube.com/watch?v=fVQT3638TZA)
*
*
	Since now there are multiple action types (backup and database), I changed the if/else into a switch accordingly, in the ThePopcorn (https://www.youtube.com/watch?v=t3S0PR8_C2w) from the WhereIs class. The "back" case goes back to the previous page, using a stack and, if we go back to the movies page, we need to show all the movies again. The add/delete actions are handled by the class DatabaseActions.
*** The DatabaseActions class
	has the method modifyDatabase that adds or deletes a movie from the database. if a movie is added, we need to check for duplicates and show error if there are, and if not we most notify all users accordingly. If the movie deleted however, we must check it exists, and if it does we must remove it from all the lists of the users (watched, purchased, liked rated) as well. (https://www.youtube.com/shorts/kIruFsgHsRs)
*** DETAILS ABOUT IMPLEMENTATION
*
*
*** Utility classes
	I added the utility class Recommendations (https://www.youtube.com/watch?v=Jgl5zMfNGN8) that gets the favorite genre and a recommended movie for the user. DabaseActions is also an utility class
*** Other classes
	I also added the Nofici class which holds the information for a notification (https://www.youtube.com/watch?v=4-lY0NT-bJs)
*** Other details
	Not much has changed since the first stage of the project. What has changed however is if the user wants to buy/watch/like/rate the same movie twice. Purchase will give an error, watch and like will NOT put a duplicate in the list (as they did with the original implementation), and now rate changes the rating without adding another. I thought that the rate change was gonna happen, so why I made the system using a hashtable initially, but didn't implement the actual rate change until now. Also added some extra constants to different classes for convinience.
*** Conclusion
	This is the big picture of the program. For more details, there are comments in the code, and the code itself also provides some information.

	With all due respect, 
	https://www.youtube.com/shorts/Rz-dS1it-0U

#     # ####### ######  #       ######     ####### ######  ###  #####   #####  ####### ######  
#  #  # #     # #     # #       #     #       #    #     #  #  #     # #     # #       #     # 
#  #  # #     # #     # #       #     #       #    #     #  #  #       #       #       #     # 
#  #  # #     # ######  #       #     #       #    ######   #  #  #### #  #### #####   ######  
#  #  # #     # #   #   #       #     #       #    #   #    #  #     # #     # #       #   #   
#  #  # #     # #    #  #       #     #       #    #    #   #  #     # #     # #       #    #  
 ## ##  ####### #     # ####### ######        #    #     # ###  #####   #####  ####### #     #  