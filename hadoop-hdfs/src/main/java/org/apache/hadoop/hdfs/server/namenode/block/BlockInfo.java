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
package org.apache.hadoop.hdfs.server.namenode.block;

import org.apache.hadoop.hdfs.server.common.Block;
import org.apache.hadoop.hdfs.server.common.BlockCollection;
import org.apache.hadoop.hdfs.server.namenode.datanode.DataNodeStorageInfo;

/**
 * For a given block (or an erasure coding block group), BlockInfo class
 * maintains 1) the {@link BlockCollection} it is part of, and 2) datanodes
 * where the replicas of the block, or blocks belonging to the erasure coding
 * block group, are stored.
 */
public class BlockInfo extends Block {

  // Storage this block is replicated on
  private DataNodeStorageInfo[] storageInfos;


  public BlockInfo(short size) {

  }
}
