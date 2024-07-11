plugins {
    id("java")
}

group = "it.unicam.cs.pa2024.formula1"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation ("org.mockito:mockito-core:3.6.28")
    testImplementation ("org.mockito:mockito-junit-jupiter:3.6.28")

}

tasks.test {
    useJUnitPlatform()
}
