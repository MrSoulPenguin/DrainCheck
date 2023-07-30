plugins {
    java
}

group = "me.mrsoulpenguin"
version = "1.0"

tasks.jar {
    manifest {
        attributes["Main-Class"] = "me.mrsoulpenguin.DrainCheck"
    }
}