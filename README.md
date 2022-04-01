# Game Service App

This project seeks to implement a basic video game distribution app, being inspired by others like it such as Steam or Epic Games.

The classes used are:
- User
- Player
- Developer
- Game
- Item
- Inventory
- Review

The User class contains login credentials and a few pieces of information such as the account balance. It is a parent class of Player and Developer, which are the two types of user contained by our app. Players have their game libraries, their wishlists as well reviews for the games they played and an inventory with all their special items from these games. Developers are the ones that create the games.

The Game class contains attributes such as a list of the developers who worked on it, its storage size, a list of reviews and an average rating, calculated as the average of all the ratings it got from its reviews.

The Item class refers to items that can be won and used in certain games, and the four main types are KEY, CRATE, WEAPON and COSMETIC. These items are stored in an inventory, each belonging to one player

The Review class is pretty self explanatory, it is an object associated with one player and one game detailing the player's personal experience playing the game as well as an overall rating and recommendation.

Finally, a database class has containers for all of our objects and an interactive menu, which can answer different queries about our data.
