/*
 * Copyright 2018-2019 OVO Energy Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fs2.kafka

/**
  * [[Timestamp]] is a timestamp value representing either
  * the creation time of a record, or alternatively, the
  * time when a record was appended to the log.
  */
sealed abstract class Timestamp {

  /**
    * Returns the timestamp value, if it's representing
    * the creation time of a record. If the timestamp
    * is [[logAppendTime]], returns `None`.
    */
  def createTime: Option[Long]

  /**
    * Returns the timestamp value, if it's representing
    * the time when a record was appended to the log. If
    * the timestamp is [[createTime]], returns `None`.
    */
  def logAppendTime: Option[Long]
}

object Timestamp {

  /**
    * Creates a new [[Timestamp]] instance from the specified
    * timestamp value representing the creation time of the
    * record.
    */
  def createTime(value: Long): Timestamp =
    new Timestamp {
      override val createTime: Option[Long] = Some(value)
      override val logAppendTime: Option[Long] = None
      override def toString: String = s"Timestamp(createTime = $value)"
    }

  /**
    * Creates a new [[Timestamp]] instance from the specified
    * timestamp value representing the time when the record
    * was appended to the log.
    */
  def logAppendTime(value: Long): Timestamp =
    new Timestamp {
      override val createTime: Option[Long] = None
      override val logAppendTime: Option[Long] = Some(value)
      override def toString: String = s"Timestamp(logAppendTime = $value)"
    }
}
