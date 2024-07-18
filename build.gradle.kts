plugins {
    id("java")
}

group = "com.msa2024"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("com.google.code.gson:gson:2.9.0")
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
   testImplementation("com.fasterxml.jackson.core:jackson-databind:2.17.1")

}

tasks.test {
    useJUnitPlatform()
}