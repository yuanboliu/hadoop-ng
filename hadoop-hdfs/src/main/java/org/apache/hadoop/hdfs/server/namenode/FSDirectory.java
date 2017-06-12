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
package org.apache.hadoop.hdfs.server.namenode;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.server.ThreadCollection;
import org.apache.hadoop.hdfs.server.namenode.inode.INodeDirectory;
import org.apache.hadoop.hdfs.server.namenode.inode.INodeId;
import org.apache.hadoop.hdfs.util.DFSUtils;
import org.apache.hadoop.util.StringUtils;

public class FSDirectory implements ThreadCollection {

  final static byte[] ROOT_NAME = StringUtils.string2Bytes("nn1");
  private static INodeDirectory createRoot() {
    return new INodeDirectory(INodeId.ROOT_INODE_ID, ROOT_NAME, 0L, 0L);
  }

  public static byte[][] getPathNameBytes(String path) {
    if (path == null || !path.startsWith(Path.SEPARATOR)) {
      throw new AssertionError("Absolute path required, but got '"
          + path + "'");
    }
    return DFSUtils.getPathComponents(path);
  }

  private final FSNameSystem nameSystem;
  private final INodeId iNodeId = new INodeId();
  private final INodeDirectory rootDir;

  FSDirectory(FSNameSystem nameSystem) {
    this.nameSystem = nameSystem;
    this.rootDir = createRoot();
  }

  /** Allocate a new inode ID. */
  public long allocateNewINodeId() {
    return iNodeId.nextValue();
  }

  /**
   * {@inheritDoc}
   */
  public void startThreads() throws Exception {

  }

  /**
   * {@inheritDoc}
   */
  public void stopThreads() throws Exception {

  }
}
