pluginManagement {
    //includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "EcoSnap"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "EcoSnap"
include(":app")
include(":camera:domain")
include(":camera:data")
include(":camera:presentation")
include(":home:presentation")
include(":home:data")
include(":home:domain")
include(":cleaning:data")
include(":cleaning:presentation")
include(":cleaning:domain")
include(":core:data")
include(":core:presentation")
include(":core:domain")
include(":core:presentation:designsystem")
include(":core:presentation:ui")
