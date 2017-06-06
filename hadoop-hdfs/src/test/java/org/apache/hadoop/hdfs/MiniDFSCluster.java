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
package org.apache.hadoop.hdfs;

/**
 * This class creates a single-process DFS cluster for junit testing.
 */
public class MiniDFSCluster implements AutoCloseable {

  /**
   * Constructor for MiniDFSCluster.
   */
  public MiniDFSCluster() throws Exception {
    initMiniDFSCluster();
  }

  /**
   * Init mini dfs cluster.
   * 
   * @throws Exception
   */
  private void initMiniDFSCluster() throws Exception {
    initNameNodes();
    initDataNodes();
  }

  /**
   * Method for initiating NameNodes for testing.
   *
   * @throws Exception
   */
  private void initNameNodes() throws Exception {

  }

  /**
   * Method for initiating DataNodes for testing.
   *
   * @throws Exception
   */
  private void initDataNodes() throws Exception {

  }

  /**
   * Close mini dfs cluster.
   *
   * @throws Exception
   */
  public void close() throws Exception {

  }
}
