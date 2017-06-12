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

import java.util.ArrayList;
import java.util.List;

/**
 * Directory INode class. Only directory has child iNodes.
 */
public class INodeDirectory extends INode {

  public static INodeDirectory valueOf(INode iNode) {
    if (iNode == null) {
      return null;
    }
    return iNode.asDirectory();
  }

  private static final int DEFAULT_FILES_PER_DIRECTORY = 5;

  private final List<INode> children =
      new ArrayList<INode>(DEFAULT_FILES_PER_DIRECTORY);

  public INodeDirectory(long id, byte[] name, long modificationTime,
      long accessTime) {
    super(id, name, (short) 0, modificationTime, accessTime);
  }

  public void addChild(INode node) {
    node.setParent(this);
    children.add(node);
  }

  @Override
  public INodeDirectory asDirectory() {
    return this;
  }

  @Override
  public boolean isDirectory() {
    return true;
  }

  public short getReplication() {
    return 0;
  }
}
