# Clover Assignment
Clover assignment

● What architectural decisions did you make and why? Elaborate on strengths and weaknesses.
  Architecture -> MVVM (Keeps code clean and proper seperation of concern)z
  Used PAGING3 from Jetpack for loading the charaters
  
● How did you handle error cases? Eg: botched response, no response, etc.
  Not handled yet but can be handled via LoadStateAdapter for ERROR case
  PAGING3 also handles the ERROR and RETRY efficiently without developer to put extra efforts
  
● How did you test the app?
  No effort on testing for now. Toooo busy with office work :( + fever

● What third party libraries/ external code snippets did you use, if any?
  Image loading -> GLIDE
  GSON for JSON to POJO conversion
  RETROFIT2
  
● If you had more time, what would you have done differently?
  Would have added
   -> Shimmering effect for loading list
   -> Proper documents
   -> Proper layouts design
   -> Proper testing 
   -> Base classes
