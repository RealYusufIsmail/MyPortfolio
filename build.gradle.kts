// Top-level build file where you can add configuration options common to all sub-projects/modules.

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
buildscript {
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.10")
    }
}

plugins {
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.allopen") version "1.7.10"
    id("com.android.application") version "7.2.0" apply false
    id("com.android.library") version "7.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
    id("com.diffplug.spotless") version "6.10.0"
}

tasks.register<Delete>("delete") {
    delete(rootProject.buildDir)
}

subprojects {
    // Extension with name 'java' does not exis
    apply(plugin = "kotlin-android")
    apply(plugin = "kotlin-allopen")
    apply(plugin = "com.diffplug.spotless")

    repositories {
        mavenCentral()
    }

    dependencies {
    }

    tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
    }

    spotless {
        kotlin {
            // Excludes build folder since it contains generated java classes.
            targetExclude("build/**")
            ktfmt("0.39").dropboxStyle()

            licenseHeader(
                """/*
 * Copyright 2022 Yusuf Arfan Ismail.
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ """
            )
        }
    }
}