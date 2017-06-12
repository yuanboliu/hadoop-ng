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
package org.apache.hadoop.hdfs.util;

import com.google.common.base.Preconditions;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.StringUtils;

import java.util.Arrays;

public class DFSUtils {

  public static final byte[] EMPTY_BYTES = {};

  /**
   * Convert a UTF8 string to an array of byte arrays.
   */
  public static byte[][] getPathComponents(String path) {
    // avoid intermediate split to String[]
    final byte[] bytes = StringUtils.string2Bytes(path);
    return bytes2byteArray(bytes, bytes.length, (byte) Path.SEPARATOR_CHAR);
  }


  /**
   * Splits first len bytes in bytes to array of arrays of bytes
   * on byte separator
   * @param bytes the byte array to split
   * @param len the number of bytes to split
   * @param separator the delimiting byte
   */
  public static byte[][] bytes2byteArray(byte[] bytes,
      int len,
      byte separator) {
    Preconditions.checkPositionIndex(len, bytes.length);
    if (len == 0) {
      return new byte[][]{null};
    }
    // Count the splits. Omit multiple separators and the last one by
    // peeking at prior byte.
    int splits = 0;
    for (int i = 1; i < len; i++) {
      if (bytes[i-1] == separator && bytes[i] != separator) {
        splits++;
      }
    }
    if (splits == 0 && bytes[0] == separator) {
      return new byte[][]{null};
    }
    splits++;
    byte[][] result = new byte[splits][];
    int nextIndex = 0;
    // Build the splits.
    for (int i = 0; i < splits; i++) {
      int startIndex = nextIndex;
      // find next separator in the bytes.
      while (nextIndex < len && bytes[nextIndex] != separator) {
        nextIndex++;
      }
      // reuse empty bytes for root.
      result[i] = (nextIndex > 0)
          ? Arrays.copyOfRange(bytes, startIndex, nextIndex) : EMPTY_BYTES;
      do { // skip over separators.
        nextIndex++;
      } while (nextIndex < len && bytes[nextIndex] == separator);
    }
    return result;
  }

}
