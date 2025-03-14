/*
 * Copyright 2017-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.cloud.spring.data.datastore.core.mapping;

import org.springframework.data.mapping.PersistentProperty;

/**
 * Persistent property for Google Cloud Datastore.
 *
 * @since 1.1
 */
public interface DatastorePersistentProperty
    extends PersistentProperty<DatastorePersistentProperty> {

  /**
   * Get the name of the field to store this property in Datastore.
   *
   * @return the string name of the field.
   */
  String getFieldName();

  /**
   * Whether the property contains entities that are related to this entity via the Cloud Datastore
   * Ancestor relationship and have this entity as their ancestor.
   *
   * @return {@code true} if the property contains child entities. {@code false} otherwise.
   */
  boolean isDescendants();

  /**
   * True if the property should be excluded from indexes.
   *
   * @return true if the property should be indexed
   */
  boolean isUnindexed();

  /**
   * Get the {@link EmbeddedType} of the property indicating what what type of embedding pathway
   * will be used to store the property.
   *
   * @return the embedded type.
   */
  EmbeddedType getEmbeddedType();

  /**
   * True if the property is stored within Datastore entity.
   *
   * @return true if the property is stored within Datastore entity
   */
  boolean isColumnBacked();

  /**
   * Return whether this property is a lazily-fetched one.
   *
   * @return {@code true} if the property is lazily-fetched. {@code false} otherwise.
   */
  boolean isLazyLoaded();

  /**
   * Return whether to skip null value, i.e., skip insertion if value is null.
   *
   * @return {@code true} if the null value is skipped. {@code false} otherwise.
   */
  default boolean isSkipNullValue() {
    return false;
  }
}
