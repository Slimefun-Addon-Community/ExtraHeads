plugins {
    id("java")
    id("maven-publish")
    id("io.freefair.lombok") version "8.7.1"
    id("com.gradleup.shadow") version "8.3.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
}

repositories {
    mavenCentral()
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21-R0.1-SNAPSHOT")
    compileOnly("com.github.Slimefun:Slimefun4:e02a0f61d1")
    compileOnly("me.clip:placeholderapi:2.11.6")
    implementation("org.bstats:bstats-bukkit:3.0.3")
    implementation("com.google.code.findbugs:jsr305:3.0.2")
    implementation("net.guizhanss:guizhanlib-all:2.0.0-SNAPSHOT")
}

group = "io.github.thebusybiscuit"
version = "UNOFFICIAL"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.shadowJar {
    fun doRelocate(from: String) {
        val last = from.split(".").last()
        relocate(from, "io.github.thebusybiscuit.extraheads.libs.$last")
    }
    doRelocate("org.bstats")
    doRelocate("javax.annotation")
    minimize()
    archiveClassifier = ""
}

bukkit {
    main = "io.github.thebusybiscuit.extraheads.ExtraHeads"
    apiVersion = "1.18"
    authors = listOf("TheBusyBiscuit", "ybw0014")
    description = "A Slimefun Addon that adds heads of mobs"
    website = "https://github.com/Slimefun-Addon-Community/ExtraHeads"
    depend = listOf("Slimefun")
    softDepend = listOf("PlaceholderAPI")
}
