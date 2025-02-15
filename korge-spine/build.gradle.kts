description = "Multiplatform Game Engine written in Kotlin"

kotlin {
    jvm {
        jvm {
            //val main by compilations.getting {
            compilations.all {
                kotlinOptions {
                    // Setup the Kotlin compiler options for the 'main' compilation:
                    //jvmTarget = "1.8"
                    freeCompilerArgs = listOf("-Xjvm-default=enable")
                }
            }
        }
        //withJava()
    }
}

dependencies {
    //add("commonMainApi", project(":korgw"))
    add("commonMainApi", project(":kds"))
    add("commonMainApi", project(":korio"))
    add("commonMainApi", project(":korge"))
}
