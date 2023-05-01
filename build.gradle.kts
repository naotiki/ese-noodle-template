import me.naotiki.ese.plugin.esePlugin

plugins {
    kotlin("jvm") version "1.8.20"
    // Apply Plugin
    id("me.naotiki.ese.noodle-plugin") version "0.0.1-dev2"
}

group = "me.naotiki"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}
//Configure Plugin
esePlugin {
    // NDL File will be generated to build/ese/<pluginName>.ndl
    pluginName.set("Example")
    pluginClass.set("ExamplePlugin")
}