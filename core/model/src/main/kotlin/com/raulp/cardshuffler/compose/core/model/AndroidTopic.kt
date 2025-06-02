package com.raulp.cardshuffler.compose.core.model

data class AndroidTopic (
  val title: String,
  val description: String,
  val imageUrl: String,
  val type: String
)

//
//{
//  "Android Fundamentals": [
//  "What is the purpose of the Application class, and how does it differ from an Activity in terms of lifecycle and resource management? [1]",
//  "How do intent filters in AndroidManifest enable app interactions, and what happens if an activity class is not registered in AndroidManifest? [2]",
//  "How can you test deep links in Android, and what are some common debugging techniques to ensure they work correctly across different devices and scenarios? [4]",
//  "What is the difference between singleTask and singleInstance launch modes, and in what scenarios would you use each? [5]",
//  "What are the different activity launch modes, and how do they influence task and back stack behavior? [5]",
//  "How does onSaveInstanceState() use a Bundle to preserve UI state during configuration changes, and what types of data can be stored in a Bundle? [6]",
//  "You need to implement a feature in your Android app that downloads a large file (several hundred MBs) from a remote server. The download should continue even if the app is closed, and it should be efficient in terms of the performance and network conditions. Which background execution mechanism would you choose—WorkManager, Foreground Service, or JobScheduler—and why? [11]"
//  ],
//  "Android UI (View System)": [
//  "How does RecyclerView’s ViewHolder pattern improve performance compared to ListView? [9]",
//  "Explain the lifecycle of a ViewHolder in RecyclerView from creation to recycling. [9]",
//  "What is RecycledViewPool, and how can it be used to optimize rendering view items? [9]",
//  "Why is a paging system essential for loading large datasets, and how can it be implemented with RecyclerView? [14, 25]",
//  "Your app fetches a large dataset from an API and displays it in a RecyclerView. Howwould you implement an efficient paging system to ensure smooth scrolling and reduce memory usage? [14]",
//  "What challenges might arise when implementing a manual paging system with Recy-clerView, and how can they be mitigated to provide a seamless user experience? [14]"
//  ],
//  "Jetpack Libraries": [
//  "How does the Paging library handle errors during data loading, and what are the rec-ommended strategies for implementing error handling and retry mechanisms in a paginated data flow? [10]",
//  "In a scenario where you need to store large JSON responses from a network API for offline access, which local storage mechanism would you use, and why? [15]",
//  "Given a JSON response from an API, how would you deserialize it into a Kotlin data class? Which library would you choose for a Kotlin-first project, and why? [12]",
//  "If you need to deserialize a JSON object with missing or additional fields that are not defined in your Kotlin data class, how would you handle this scenario? [12]",
//  "Your app needs to make multiple concurrent API requests and combine their results before updating the UI. How would you achieve this efficiently using Retrofit and coroutines? [13]",
//  "How would you handle API failures and implement a retry mechanism? [13]"
//  ],
//  "Jetpack Compose Fundamentals & Runtime": [
//  "How does state is related to recomposition, and what happens during recomposition? [19]",
//  "In which scenarios would you prefer using snapshotFlow over directly observing a Flow from a ViewModel, and how would you optimize its emission behavior? [20]",
//  "What are the advantages and disadvantages of loading initial data in ViewModel.init() versus LaunchedEffect in Jetpack Compose, and when would you choose one approach over the other? If you prefer another solution, what’s that? [16]"
//  ],
//  "Jetpack Compose UI": [
//  "What is the role of trailing lambdas and higher-order functions in structuring composable functions? [18]",
//  "In a tabbed user interface with multiple screens, how would you use implement each tab retains its scroll position or input state across screen transitions without using Jetpack Navigation library? [21]",
//  "How would you optimize a composable function that takes a List as a parameter and causes unnecessary recompositions? [17]",
//  "What APIs or Compose compiler features have you used to improve recomposition efficiency in your app? [17]",
//  "Suppose you’re building a chat screen with real-time messages. how would you structure the layout to ensure smooth scrolling and minimal recomposition overhead? [22]",
//  "What APIs or state mechanisms would you use to detect when more items should be loaded? [23]",
//  "What is the purpose of the semantics modifier? [24]",
//  "How can you make a group of UI elements behave as a single accessibility node in Compose? [24]"
//  ],
//  "Performance & Optimization": [
//  "How can you detect & diagnose ANRs and improving app performance? [3]",
//  "What is the difference between a build type and a product flavor, and how do they work together to create build variants? [7]",
//  "Your app contains high-resolution images that significantly increase its APK/AAB size. How would you optimize image resources while maintaining visual quality, and which formats would you use for maximum efficiency? [8]",
//  "Your application includes multiple features, but some of them are rarely used by most users. How would you implement a solution to reduce the initial app size while still making those features available when needed? [8]"
//  ]
//}