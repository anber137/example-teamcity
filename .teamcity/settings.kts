package _Self.buildTypes

import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.triggers.vcs

object Build : BuildType({
    name = "Build"

    vcs {
        root(ExampleTeamcityTest)
    }

steps {
    maven {

        conditions {
            contains("teamcity.build.branch", "master")
        }
        goals = "clean package"
        runnerArgs = "-Dmaven.test.failure.ignore=true"
    }
    maven {
        name = "test"

        conditions {
            doesNotContain("teamcity.build.branch", "master")
        }
        goals = "clean test"
        runnerArgs = "-Dmaven.test.failure.ignore=true"
    }
}

    triggers {
        vcs {
        }
    }
})
