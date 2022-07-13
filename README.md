# Formula_One

This is a demo app that makes use of @branchmetrics Deep linking and attribution features.
The app fetches all the details from a public api and makes use of firebase realtime storage and database for image loading.

<img src="https://user-images.githubusercontent.com/45487004/178658510-e17756b3-4136-42b0-bf82-d209930c5efa.jpg" width="300">
<img src="https://user-images.githubusercontent.com/45487004/178658523-ad9bf495-e703-4f39-837a-73390219a002.jpg" width="300">



# Branch integration


## Generating Sharable link

The user can generate and share a link of a specific driver to any other apps. The generated link is configured with deep link paths.

There are two instances when user A shares link to User B

<img src="https://user-images.githubusercontent.com/45487004/178659162-af45bcf4-76d2-4e57-8002-ce2143e5edf2.jpg" width="300">
Profile page with share option

<img src="https://user-images.githubusercontent.com/45487004/178659712-8b526cdc-b00d-4b06-904d-b7a75ef04c2d.jpg" width="300">


Generated link with custom message 

## Deep linking
"<b>Check out my favorite driver Valtteri Bottas on Formula One App. Download the app now!
https://f6rwa.test-app.link/LPeKg9MBCrb</b>"

1. The user B have the same app installed 
   The app takes the user to the specific driver profile which user A shared.
   
2. User B don't have the app.
   Branch has an option to add fallback url, so when the user dont have the app installed branch takes the user to app store to install the app and continues the journey
   once operation is over
  
  
## Content Events

<img src="https://user-images.githubusercontent.com/45487004/178661798-4de1fa4f-05a0-49aa-ab60-fc5dc8c7533f.png" width="1000">
  Using the Branch SDK the app will trigger events such as profile visit. So we can track which driver/constructor has higher engagement. This data will reflcet 
  branch dashboard
  
  How to make use of this data:
    For example some drivers will have lot of fans in certain geographic location so based on this click event we can send out push notifications to certain locations 
    that are releated to the driver who has higher engagement in that location.
    
  
## Commercial Events

<image src="https://user-images.githubusercontent.com/45487004/178661436-a2d39b90-3a89-4c0f-b203-873713799c7f.png" width ="1000">

  The app will trigger various commercial events based on user actions.
  Such as ADD_TO_CART, PURCHASE
