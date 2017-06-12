/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.hdfs.server.common;

import org.apache.hadoop.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Defines the types of supported storage media. The default storage
 * medium is assumed to be DISK.
 */
public enum StorageType {
  // sorted by the speed of the storage types, from fast to slow
  RAM_DISK(true),
  SSD(false),
  DISK(false),
  ARCHIVE(false);

  private final boolean isTransient;

  public static final StorageType DEFAULT = DISK;

  public static final StorageType[] EMPTY_ARRAY = {};

  private static final StorageType[] VALUES = values();

  StorageType(boolean isTransient) {
    this.isTransient = isTransient;
  }

  public boolean isTransient() {
    return isTransient;
  }

  public boolean supportTypeQuota() {
    return !isTransient;
  }

  public boolean isMovable() {
    return !isTransient;
  }

  public static List<StorageType> asList() {
    return Arrays.asList(VALUES);
  }

  public static List<StorageType> getMovableTypes() {
    return getNonTransientTypes();
  }

  public static List<StorageType> getTypesSupportingQuota() {
    return getNonTransientTypes();
  }

  public static StorageType parseStorageType(int i) {
    return VALUES[i];
  }

  public static StorageType parseStorageType(String s) {
    return StorageType.valueOf(StringUtils.toUpperCase(s));
  }

  private static List<StorageType> getNonTransientTypes() {
    List<StorageType> nonTransientTypes = new ArrayList<StorageType>();
    for (StorageType t : VALUES) {
      if (!t.isTransient) {
        nonTransientTypes.add(t);
      }
    }
    return nonTransientTypes;
  }
}