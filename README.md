![GitHub Cards Preview](https://t3.ftcdn.net/jpg/05/51/97/18/360_F_551971815_nXv1fCga04nd9fkjYr0rV0lbu5mG4lHk.jpg)

# MusicWiki

MusicWiki is an unofficial Last.fm app that contains information about different music
genres, the albums, artists and tracks listed under the genre.

## Features of MusicWiki app:
1. Display list of top 10 genres initially and allow users to expand the list to show all genres available on LastFm API.
2. Show genre details page with genre title, description, and sections for top albums, top tracks, and top artists.
3. Uses LastFm API to fetch all necessary information for the app.
4. App follows MVVM architecture.
5. Clicking on an album displays title, artist information, description, and genres.
6. Clicking on an artist displays their image, title, play count, followers, description, list of top tracks and albums, and genres.
7. Clicking on the album listed under an artist shows its information.
8. Clean and readable code

## Decisions and Assumptions
- Upon receiving the assignment, I dedicated the first day to carefully consider the flow of the application and tested the APIs using Postman to ensure their proper functionality and relevant data retrieval.

- Given the time constraints, I decided to use the design provided in the assignment instead of thinking our own.

- To display the fragments, I utilized ViewPager2 since ViewPager contained deprecated methods pertaining to fragments.

- Due to the fact that the API did not consistently provide images with each request, I implemented a placeholder for any image views where no image was returned.

- As several API responses shared similar class names, I separated them into distinct packages in order to prevent any potential conflicts.

### Try the app here [MusicWiki](https://drive.google.com/uc?export=download&id=1kh14oOHO8yqqjf6HBlHASHEzNrg-I0MI)
Turn off the app lock for google play and choose `install anyhow` when propmpt occures while installing the app.
