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

public abstract class INode implements INodeAttr {

  private INode parent = null;
  private short replication;
  private long modificationTime;
  private long accessTime;

  // INods's id and name are immutable once they're given.
  private final long id;
  private final byte[] name;


  INode(long id, byte[] name, short replication,
      long modificationTime, long accessTime) {
    this.id = id;
    this.name = name;
    this.replication = replication;
    this.modificationTime = modificationTime;
    this.accessTime = accessTime;
  }

  public void setParent(INodeDirectory parent) {
    this.parent = parent;
  }

  public INode getParent() {
    return this.parent;
  }

  /**
   * Check whether it's a file.
   */
  public boolean isFile() {
    return false;
  }

  /**
   * Check whether it's a linked file/directory.
   */
  public boolean isLinked() {
    return false;
  }

  /**
   * Check whether it's a directory
   */
  public boolean isDirectory() {
    return false;
  }

  public long getINodeId() {
    return this.id;
  }

  /**
   * Get the name of INode.
   *
   * @return String.
   */
  public byte[] getName() {
    return this.name;
  }


  public INodeFile asFile() {
    throw new IllegalStateException("Current inode is not a file");
  }

  public INodeDirectory asDirectory() {
    throw new IllegalStateException("Current inode is not a directory");
  }

  public LinkedINodeFile asLinkedFile() {
    throw new IllegalStateException("Current inode is not a linked file");
  }

  public LinkedINodeDirectory asLinkedDirectory() {
    throw new IllegalStateException("Current inode is not a linked directory");
  }

  public long getModificationTime() {
    return this.modificationTime;
  }

  public long getAccessTime() {
    return this.accessTime;
  }

}
