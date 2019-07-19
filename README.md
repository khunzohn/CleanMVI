## Clean MVI
This project started out as a follow up sample project for [our talk at DroidYangon 2019](https://speakerdeck.com/khunzohn/separate-your-concerns-with-clean-mvi). The app includes
handling of reactive friendly use cases in clean mvi way: toggling favourite status, filtering and sorting for example. 
It also showcases the application of the following important concepts:

* Immutability (No var. Data can't be modified once emitted from the Model)
* Unidirectional data flow (view -> intent -> model -> view )
* Single source of truth (Only one source of data emission: The Model)
* Dependency Inversion (Business rule depends on data sources? NO. Data sources depend on abstraction defined by Business Rule)

> Model here means a combination of Interactor and Repository

It also leverages epoxy lib to construct view sections. With kotlin declarative syntax, it's an awesome 
experience to build complex ui with only a single recyclre view.

Not necessary a requirement but using mosby lib helps you hit the ground running a lot faster.
The lib provides retaining presenter which survives device orientation changes and some MVI related goodies.
With a bit more work, we can definitely use google's viewmodel in place of mosby presenter though. Just a matter of personal 
preferences. Don't forget to change orientation while testing and see the result.

The selling point of MVI is state management. This little sample wouldn't worth a penny if it falls short of it. To make the game 
a bit more competitive, offline support is added to the mix, showing progressBar is split into two modes: 
Circle progress[when data is empty] and horizontal progress[when data is not empty]. Showing error also has two modes. 
Just turn off network data to see the error. Well, have fun architecting!

### Screen Shots
Apple Products | Favourite Filter | Favourite + New Filter
----------------------------------|---------------------|--------------------------
![products_loading](https://user-images.githubusercontent.com/9302746/61556580-25534880-aa88-11e9-9f92-e8486bb16ee1.png) | ![mac_fav](https://user-images.githubusercontent.com/9302746/61556531-03f25c80-aa88-11e9-8784-0114392ae626.png) | ![mac_new_fav](https://user-images.githubusercontent.com/9302746/61556539-09e83d80-aa88-11e9-8ba2-502c9671c84e.png)
