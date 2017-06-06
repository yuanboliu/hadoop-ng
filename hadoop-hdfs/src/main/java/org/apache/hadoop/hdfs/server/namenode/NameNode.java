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

import org.apache.hadoop.hdfs.server.EntryDaemon;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * NameNode daemon.
 */
public class NameNode implements EntryDaemon {

  enum Role {
    NN, SNN, VIEW
  }

  private AtomicBoolean running = new AtomicBoolean(true);

  private Role role;

  // NameNode name, for example, we can set
  // this value as NN1, NN2, NN3...
  // It's important for supporting multiple NameNodes.
  private String name;

  /**
   * NameNode process entrance.
   */
  public static void main(String[] args) throws Exception {
  }

  /**
   * Format a new filesystem. Destroys any filesystem
   * that may already exist at this location.
   *
   * @throws Exception
   */
  public static void format() throws Exception {

  }

  // *************************************************
  // *
  // * Implement the interface EntryDaemon.java
  // *
  // *************************************************

  /**
   * Stop name node server.
   */
  public synchronized void stop() {
    running.set(false);
    notifyAll();
  }

  /**
   * Get the running state of NameNode daemon.
   *
   * @return whether NameNode is running.
   */
  public boolean isRunning() {
    return running.get();
  }

  /**
   * Keep name node alive.
   * @throws Exception
   */
  public synchronized void join() throws Exception {
    while (isRunning()) {
      wait();
    }
  }

  /**
   * Start threads for NameNode.
   * @throws Exception
   */
  public void startThreads() throws Exception {
  }

  /**
   * Stop threads for NameNode.
   * @throws Exception
   */
  public void stopThreads() throws Exception {
  }
}
