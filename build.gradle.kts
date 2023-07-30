plugins {
    java
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "me.mrsoulpenguin"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.bitbucket.mstrobel:procyon-compilertools:0.6.0")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
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