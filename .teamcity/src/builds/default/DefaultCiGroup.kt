package builds.default

import addTestArtifacts
import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.script
import junit

class DefaultCiGroup(val ciGroup: Int) : BuildType({
  id("DefaultCiGroup_$ciGroup")
  name = "CI Group $ciGroup"
  paused = true

  params {
    param("env.KBN_NP_PLUGINS_BUILT", "true")
  }

  steps {
    script {
      name = "Default CI Group $ciGroup"
      scriptContent =
        """
                #!/bin/bash
                ./.ci/teamcity/default/ci_group.sh $ciGroup
        """.trimIndent()
    }
  }

  features {
    junit()
  }

  dependencies {
    defaultBuild()
  }

  addTestArtifacts()
})
