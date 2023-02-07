/*
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sharedtest

import com.google.android.ground.model.*
import com.google.android.ground.model.geometry.*
import com.google.android.ground.model.job.Job
import com.google.android.ground.model.locationofinterest.LocationOfInterest
import com.google.android.ground.model.submission.Submission
import com.google.android.ground.model.task.MultipleChoice
import com.google.android.ground.model.task.Task
import com.google.android.ground.ui.map.Feature
import com.google.android.ground.ui.map.FeatureType
import com.google.android.ground.ui.map.gms.FeatureClusterItem

/**
 * Shared test data constants. Tests are expected to override existing or set missing values when
 * the specific value is relevant to the test.
 */
object FakeData {
  // TODO: Replace constants with calls to newFoo() methods.
  val TERMS_OF_SERVICE: TermsOfService =
    TermsOfService("TERMS_OF_SERVICE", "Fake Terms of Service text")

  val JOB = Job(name = "Job", id = "JOB")

  val USER = User("user_id", "user@gmail.com", "User")

  val SURVEY: Survey =
    Survey(
      "SURVEY",
      "Survey title",
      "Test survey description",
      mapOf(),
      listOf(),
      mapOf(Pair(USER.email, "data-collector"))
    )

  val LOCATION_OF_INTEREST =
    LocationOfInterest(
      "loi id",
      SURVEY.id,
      JOB,
      null,
      "loi name",
      AuditInfo(USER),
      AuditInfo(USER),
      Point(Coordinate(0.0, 0.0))
    )

  val LOCATION_OF_INTEREST_FEATURE =
    Feature(
      id = LOCATION_OF_INTEREST.id,
      type = FeatureType.LOCATION_OF_INTEREST.ordinal,
      geometry = LOCATION_OF_INTEREST.geometry
    )

  val LOCATION_OF_INTEREST_CLUSTER_ITEM = FeatureClusterItem(LOCATION_OF_INTEREST_FEATURE)

  val VERTICES: List<Point> =
    listOf(
      Point(Coordinate(0.0, 0.0)),
      Point(Coordinate(10.0, 10.0)),
      Point(Coordinate(20.0, 20.0)),
      Point(Coordinate(0.0, 0.0)),
    )

  private val AUDIT_INFO = AuditInfo(USER)

  val AREA_OF_INTEREST: LocationOfInterest =
    LocationOfInterest(
      "loi id",
      SURVEY.id,
      JOB,
      "",
      "",
      AUDIT_INFO,
      AUDIT_INFO,
      Polygon(LinearRing(VERTICES.map { it.coordinate })),
    )

  val COORDINATE = Coordinate(42.0, 18.0)

  fun newTask(
    id: String = "",
    type: Task.Type = Task.Type.TEXT,
    multipleChoice: MultipleChoice? = null
  ): Task = Task(id, 0, type, "", false, multipleChoice)

  private const val SUBMISSION_ID = "789"
  const val TASK_1_NAME = "task 1"
  const val TASK_2_NAME = "task 2"

  val SUBMISSION: Submission =
    Submission(
      SUBMISSION_ID,
      SURVEY.id,
      LOCATION_OF_INTEREST,
      JOB.copy(
        id = "taskId",
        tasks =
          mapOf(
            Pair("field id", Task("field id", 0, Task.Type.TEXT, TASK_1_NAME, true)),
            Pair("field id 2", Task("field id 2", 1, Task.Type.TEXT, TASK_2_NAME, true))
          )
      ),
      AUDIT_INFO,
      AUDIT_INFO
    )
}