Hubrick Challenge
============

Android+Kotlin implementation of the Hubrick's feed challenge. This project comes with a tester app to popuplate the database in real time, so the use of two devices/simulators is recommended.

### Setup ###

For running the apps and tests, you will need Android Studio. [Download here][1]

* Download latest version of Android Studio
* Download and install Build Tools and API SDK (editor will ask for that during project import)
* Open [InitialActivity.kt][2]: There's an hardcoded flag *isTesting* inside *onCreate()* method
	* Running the app with true will start the tester app
	* Running the app with false will start the main app


### Run automated tests ###

#### Command Line ####

With the command line, navigate to the project root folder and then execute

```
./gradlew test
```

After the build, the test results will appear as an web page you can access in *$(PROJECT_ROOT)/app/build/reports*

#### Android Studio ####

Within Android Studio, navigate to the topmost testing package that contains both *feed_consumer* and *feed_presenter* packages, right-click on it and press *Run Tests*. The results will be presented in the console.

[1]: https://developer.android.com/studio/index.html
[2]: https://github.com/joaocruz04/HubrickChallenge/blob/master/app/src/main/java/com/hubrickchallenge/android/InitialActivity.kt