HubrickChallenge
============

Android+Kotlin implementation of the Hubrick's feed challenge. This project comes with a tester app to popuplate the database in real time, so the use of two devices/simulators is recommended.

### Setup ###

* Download latest version of Android Studio
* Download and install Build Tools and API SDK (editor ask for that during project import)
* Open InitialActivity.kt: There's an hardcoded flag "isTesting"
	* Running the app with true will start the tester app
	* Running the app with false will start the main app

* To run the automated tests
	* With command line:
		* Navigate to project folder
		* Execute *./gradlew test*

