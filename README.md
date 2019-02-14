# youtubedl-android
Android library wrapper for [youtube-dl](https://github.com/rg3/youtube-dl) executable

## Credits
*  [youtubedl-java](https://github.com/sapher/youtubedl-java) by [sapher](https://github.com/sapher), youtubedl-android adds android compatibility to youtubedl-java.


## Installation

### Gradle
Step 1 : Add jitpack repository to your project build file
```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
Step 2: Add the dependency
```gradle
dependencies {
    implementation 'com.github.yausername:youtubedl-android:0.1.0'
}
```


## Usage

* youtube-dl executable and python 3.7 are bundled in the library.
* Initialize library, preferably in `onCreate`.

```java
try {
    YoutubeDL.getInstance().init(getApplication());
} catch (YoutubeDLException e) {
    Log.e(TAG, "failed to initialize youtubedl-android", e);
}
```


* Downloading / custom command (A detailed example can be found in the [sample app](app/src/main/java/com/yausername/youtubedl_android_example/DownloadingExampleActivity.java))
```java
File youtubeDLDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "youtubedl-android");
YoutubeDLRequest request = new YoutubeDLRequest("https://vimeo.com/22439234");
request.setOption("-o", youtubeDLDir.getAbsolutePath() + "/%(title)s.%(ext)s");
YoutubeDL.getInstance().execute(request, (progress, etaInSeconds) -> {
    System.out.println(String.valueOf(progress) + "% (ETA " + String.valueOf(etaInSeconds) + " seconds)");
});
```


* Get stream info (equivalent to `--dump-json` of youtube-dl)
```java
VideoInfo streamInfo = YoutubeDL.getInstance().getInfo("https://vimeo.com/22439234");
System.out.println(streamInfo.getTitle());
```

* youtube-dl supports myriad different options which be seen [here](https://github.com/rg3/youtube-dl)


# Sample app

![Download Example](https://media.giphy.com/media/LpDmy1nS4JjERk39xS/giphy.gif)
![Streaming Example](https://media.giphy.com/media/1qXGlSPB3pqRQ7dLxx/giphy.gif)