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

public class DataNodeId {

  private String ipAddr;     // IP address
  private String hostName;   // hostname claimed by datanode
  private String peerHostName; // hostname from the actual connection
  private int xferPort;      // data streaming port
  private int infoPort;      // info server port
  private int infoSecurePort; // info server port
  private int ipcPort;       // IPC server port
  private String xferAddr;

  /**
   * UUID identifying a given datanode. For upgraded Datanodes this is the
   * same as the StorageID that was previously used by this Datanode.
   * For newly formatted Datanodes it is a UUID.
   */
  private final String datanodeUuid;

  /**
   * Create a DatanodeID
   * @param ipAddr IP
   * @param hostName hostname
   * @param datanodeUuid data node ID, UUID for new Datanodes, may be the
   *                     storage ID for pre-UUID datanodes. NULL if unknown
   *                     e.g. if this is a new datanode. A new UUID will
   *                     be assigned by the namenode.
   * @param xferPort data transfer port
   * @param infoPort info server port
   * @param ipcPort ipc server port
   */
  public DataNodeId(String ipAddr, String hostName, String datanodeUuid,
      int xferPort, int infoPort, int infoSecurePort, int ipcPort) {
    setIpAndXferPort(ipAddr, xferPort);
    this.hostName = hostName;
    this.datanodeUuid = checkDatanodeUuid(datanodeUuid);
    this.infoPort = infoPort;
    this.infoSecurePort = infoSecurePort;
    this.ipcPort = ipcPort;
  }

  private void setIpAndXferPort(String ipAddr, int xferPort) {
    // build xferAddr string to reduce cost of frequent use
    this.ipAddr = ipAddr;
    this.xferPort = xferPort;
    this.xferAddr = ipAddr + ":" + xferPort;
  }

  private String checkDatanodeUuid(String uuid) {
    if (uuid == null || uuid.isEmpty()) {
      return null;
    } else {
      return uuid;
    }
  }

}
