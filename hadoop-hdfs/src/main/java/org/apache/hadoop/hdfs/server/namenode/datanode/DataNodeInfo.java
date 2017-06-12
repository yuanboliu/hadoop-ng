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
package org.apache.hadoop.hdfs.server.namenode.datanode;

import org.apache.hadoop.hdfs.server.common.DataNodeId;

/**
 * This class extends the primary identifier of a Datanode with ephemeral
 * state, eg usage information, current administrative state, and the
 * network location that is communicated to clients.
 */
public class DataNodeInfo extends DataNodeId {


  /**
   * Create a DataNodeInfo
   *
   * @param ipAddr         IP
   * @param hostName       hostname
   * @param datanodeUuid   data node ID, UUID for new Datanodes, may be the
   *                       storage ID for pre-UUID datanodes. NULL if unknown
   *                       e.g. if this is a new datanode. A new UUID will
   *                       be assigned by the namenode.
   * @param xferPort       data transfer port
   * @param infoPort       info server port
   * @param infoSecurePort
   * @param ipcPort        ipc server port
   */
  DataNodeInfo(String ipAddr, String hostName, String datanodeUuid,
      int xferPort, int infoPort, int infoSecurePort, int ipcPort) {
    super(ipAddr, hostName, datanodeUuid, xferPort, infoPort, infoSecurePort,
        ipcPort);
  }


}
