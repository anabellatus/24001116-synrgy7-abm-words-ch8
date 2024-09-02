# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Keep the data classes
-keep class com.anabell.words.data.** { *; }

# Keep the local data source implementations
-keep class com.anabell.words.data.datasource.local.** { *; }

# Keep the remote data source implementations
-keep class com.anabell.words.data.datasource.remote.** { *; }

# Keep the repository implementations
-keep class com.anabell.words.data.repository.** { *; }

# Keep the Room database and DAO classes
-keep class com.anabell.words.data.datasource.local.room.** { *; }

# Keep the Retrofit service and factory classes
-keep class com.anabell.words.data.datasource.remote.retrofit.** { *; }

# Keep the DataStore factory classes
-keep class com.anabell.words.data.datasource.local.DataStoreFactoryKt { *; }

-dontwarn java.lang.invoke.StringConcatFactory

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile