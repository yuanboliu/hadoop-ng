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

import org.apache.hadoop.hdfs.server.ThreadCollection;

public class FSNameSystem implements ThreadCollection {

  private final BlockManager blockManager;
  private final FSDirectory fsDirectory;

  FSNameSystem() {
    blockManager = new BlockManager(this);
    fsDirectory = new FSDirectory(this);
  }


  /**
   * {@inheritDoc}
   */
  public void startThreads() throws Exception {
    blockManager.startThreads();
    fsDirectory.startThreads();
  }

  /**
   * {@inheritDoc}
   */
  public void stopThreads() throws Exception {
    blockManager.stopThreads();
    fsDirectory.stopThreads();
  }

  /** @return the block manager. */
  public BlockManager getBlockManager() {
    return blockManager;
  }

  /** @return the FSDirectory. */
  public FSDirectory getFSDirectory() {
    return fsDirectory;
  }
}
