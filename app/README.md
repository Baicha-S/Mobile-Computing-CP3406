# PawTrack (Pet Health and Wellness Tracker)
## This repository is a part of CP3406 (Mobile Computing)
Develop by Baicha (Student id: 14473015)

### Application details
PawTrack is an Android app developed using Kotlin and Jetpack Compose. The app serves busy Pet Owners, 
allow user to store all pet information and manage all appointment in one place. 
The core features are:
- Store Pet information including general information and medical history.
- Alert and Reminder which display up coming appointments.
- Pet weekly exercise tracker which show your pet exercise progress
minor features
- Some fun fact about difference pet breeds

### Developing details
This app apply MVVM architecture for a clear separate concerns and Koin to manages dependencies between each layer.
How data pass through in each layer is from:
View (UI) >> View Model >> Model (Repositories/ Data Layer) >> DAO >> Room.
Moreover, the app utilize Room to persist data locally and fetch some data from internet.
There are error handling for many scenario like when database is empty or when users is offline, so users are able 
to you even without connecting to internet. 

### Reference 
Interactive calendar - https://youtu.be/QS-iOdm0pMs?si=J5ZbjzKio9bhweSs
Free API source - https://dogapi.dog/docs/api-v2?ref=public_apis&utm_medium=website
