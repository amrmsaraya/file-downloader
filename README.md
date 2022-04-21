<p align="center">
  <img src="https://raw.githubusercontent.com/amrmsaraya/file-downloader/master/app/src/main/res/mipmap-xxxhdpi/ic_launcher.png">
</p>

# Nagwa Android Assignment - File Downloader

## Features

- Parse local json file
- Download any file
- Track download progress in-app

## Libraries

- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Declarative UI Framework
- [Material You](https://m3.material.io) - Design System
- [Splash Screen](https://developer.android.com/reference/android/window/SplashScreen) - Newly
  introduced splash screen API
- [Coroutines](https://kotlinlang.org/docs/coroutines-guide.html) - Asynchronous Programming
- [Kotlin Serlization](https://github.com/Kotlin/kotlinx.serialization) - Kotlin Multiplatform
  Serialization
- [Hilt](http://google.github.io/hilt/) - Dependency Injection
- [JUnit](https://junit.org/junit4) - Unit Testing
- [Truth](https://truth.dev) - Fluent Assertions
- [Mokk](https://mockk.io/) - Mocking

## Architecture and Design Patterns

- [Clean Architecture](https://koenig-media.raywenderlich.com/uploads/2019/02/Clean-Architecture-Bob-650x454.png)
	- Application architecture pattern
		- Presentation layer - Contains UI related code and dependency injection
		- Data layer - Contains DTOs, Mapper, Data sources and Repository Implementation
		- Domain layer - Contains Use Cases, Repository interfaces and Models (Entities)
- [MVI](https://miro.medium.com/max/5152/1*iFis87B9sIfpsgQeFkgu8Q.png) - Model-View-Intent design
  pattern
