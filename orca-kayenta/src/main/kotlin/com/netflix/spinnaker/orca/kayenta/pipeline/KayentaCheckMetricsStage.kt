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

package com.netflix.spinnaker.orca.kayenta.pipeline

import com.netflix.spinnaker.orca.ext.withTask
import com.netflix.spinnaker.orca.kayenta.tasks.AggregateCanaryResultsTask
import com.netflix.spinnaker.orca.kayenta.tasks.AggregateMetricsResultsTask
import com.netflix.spinnaker.orca.pipeline.StageDefinitionBuilder
import com.netflix.spinnaker.orca.pipeline.TaskNode
import com.netflix.spinnaker.orca.pipeline.graph.StageGraphBuilder
import com.netflix.spinnaker.orca.pipeline.model.Stage
import org.springframework.stereotype.Component
import java.time.Clock


@Component
class KayentaCheckMetricsStage(private val clock: Clock) : StageDefinitionBuilder {

  override fun taskGraph(stage: Stage, builder: TaskNode.Builder) {
    builder.withTask<AggregateMetricsResultsTask>("aggregateMetrics")
  }


  /*override fun beforeStages(parent: Stage, graph: StageGraphBuilder) {

    val runStage = graph.append {
      it.type = RunCanaryIntervalsStage.STAGE_TYPE
      it.name = "Run Canary Intervals"
      it.context["canaryConfig"] = canaryConfig
      it.context["continuePipeline"] = parent.context["continuePipeline"]
    }
    parent.context["intervalStageId"] = runStage.id
  }*/

  /*override fun afterStages(parent: Stage, graph: StageGraphBuilder) {
    if (parent.context["deployments"] != null) {
      graph.add {
        it.type = CleanupCanaryClustersStage.STAGE_TYPE
        it.name = "Cleanup Canary Clusters"
      }
    }
  }*/

  override fun onFailureStages(stage: Stage, graph: StageGraphBuilder) {
    afterStages(stage, graph)
  }

  override fun getType() = STAGE_TYPE

  companion object {
    @JvmStatic
    val STAGE_TYPE = "kayentaCheckMetrics"
  }
}
