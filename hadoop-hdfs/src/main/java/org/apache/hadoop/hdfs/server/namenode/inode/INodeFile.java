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
package org.apache.hadoop.hdfs.server.namenode.inode;

import org.apache.hadoop.hdfs.server.common.BlockCollection;
import org.apache.hadoop.hdfs.server.namenode.block.BlockInfo;

public class INodeFile extends INode {

  public static INodeFile valueOf(INode iNode) {
    if (iNode == null) {
      return null;
    }
    return iNode.asFile();
  }

  // Only file contains blocks.
  // file is split into several blocks, and the blocks
  // are stored in order.
  // BlockInfo contains info where the replicas of the block are.
  BlockCollection<BlockInfo> blocks = new BlockCollection<BlockInfo>();

  INodeFile(long id, String name, short replication,
      long modificationTime, long accessTime) {
    super(id, name, replication, modificationTime, accessTime);
  }

  @Override
  public INodeFile asFile() {
    return this;
  }

  @Override
  public boolean isFile() {
    return true;
  }

  public short getReplication() {
    return 0;
  }
}
