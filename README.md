# MVVM-Project

Self-study repository to understand Android MVVM architecture.

![alt text](https://raw.githubusercontent.com/femosso/MVVM-Project/master/MVVM.jpg)

**Model:** manipulates all logic related to the data and business logic of the application. The content is downloaded from an own-created dataset hosted in: https://raw.githubusercontent.com/femosso/products-dataset/master/data.txt. The data is downloaded with Retrofit library and inflated in the View through the Presenter layer.

**View:** binds to Observable variables and actions exposed by ViewModel layer.

**ViewModel:** exposes all methods and commands that will keep View layer state. It also interfaces with Model layer according to the actions captured by the View in order to prepare the Observable data to be shown.
