plugins {
    java
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "me.mrsoulpenguin"
version = "1.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.bitbucket.mstrobel:procyon-compilertools:0.6.0")
}

tasks {
    test {
        useJUnitPlatform()
    }

    jar {
        enabled = false
        manifest {
            attributes["Main-Class"] = "me.mrsoulpenguin.DrainCheck"
        }
    }

    build {
        finalizedBy(shadowJar)
    }

    shadowJar {
        archiveClassifier.set("")
    }

}