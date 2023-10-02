plugins {
    kotlin("jvm") version "1.7.21"
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven (url = "https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("com.github.javaparser:javaparser-symbol-solver-core:3.25.1")
    implementation("com.github.javaparser:javaparser-core:3.25.1")
    implementation("com.github.javaparser:javaparser-core-serialization:3.25.1")
    implementation("edu.cmu.sphinx:sphinx4-core:5prealpha-SNAPSHOT")
    implementation("edu.cmu.sphinx:sphinx4-data:5prealpha-SNAPSHOT")
    //implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(files("C:\\Users\\alexc\\IdeaProjects\\javardise\\build\\dist\\javardise-windows.jar"))
    implementation("commons-codec:commons-codec:1.15")
    // https://mvnrepository.com/artifact/de.sciss/ws4j
    implementation("de.sciss:ws4j:0.1.0")
    // https://mvnrepository.com/artifact/com.javax0/levenshtein
    implementation("com.javax0:levenshtein:1.0.0")
    implementation("com.github.steveash.jg2p:jg2p-pipe-cmu:1.1.0")
    implementation("pl.allegro.finance:tradukisto:1.12.0")
    implementation("org.antlr:antlr4:4.12.0")
}

tasks.test {
    useJUnitPlatform()
}

