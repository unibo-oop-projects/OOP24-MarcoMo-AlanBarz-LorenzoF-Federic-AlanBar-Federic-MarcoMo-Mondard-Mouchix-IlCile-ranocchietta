rootProject.name = "oop-24-ranocchietta"
plugins {
    id("com.gradle.develocity") version "4.0.2"
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

develocity {
    buildScan {
        termsOfUseUrl = "https://gradle.com/terms-of-service"
        termsOfUseAgree = "yes"
        uploadInBackground = !System.getenv("CI").toBoolean()
        buildScanPublished {
            file("scan-journal.log").writeText("$buildScanId - $buildScanUri\n")
        }
    }
}
