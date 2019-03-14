/*
 * Copyright 2018 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.spinnaker.orca.kayenta.tasks

import com.netflix.spinnaker.orca.ExecutionStatus.SUCCEEDED
import com.netflix.spinnaker.orca.ExecutionStatus.TERMINAL
import com.netflix.spinnaker.orca.Task
import com.netflix.spinnaker.orca.TaskResult
import com.netflix.spinnaker.orca.pipeline.model.Stage
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class AggregateMetricsResultsTask : Task {

  private val log = LoggerFactory.getLogger(javaClass)

  override fun execute(stage: Stage): TaskResult {

    return if (true) {
      TaskResult(SUCCEEDED, mapOf(
        "canaryScoreMessage" to "No score thresholds were specified."
      ))
    } else {
      TaskResult(TERMINAL, mapOf(
        "canaryScoreMessage" to "Had failing metrics tests"
      ))
    }
  }
}
