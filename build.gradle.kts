import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    id("org.openjfx.javafxplugin") version "0.1.0"
}

javafx {
    modules("javafx.media", "javafx.fxml")
}

group = "com.trilulilu"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation("media.kamel:kamel-image:0.7.3")
                implementation("io.ktor:ktor-client-apache5:2.3.5")
                api("io.github.qdsfdhvh:image-loader:1.6.8")
                implementation(compose.materialIconsExtended)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Trilulilu"
            packageVersion = "1.0.0"
        }
    }
}
