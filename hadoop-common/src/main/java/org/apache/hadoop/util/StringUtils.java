/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

/**
 * General string utils
 */
public class StringUtils {

  // Using the charset canonical name for String/byte[] conversions is much
  // more efficient due to use of cached encoders/decoders.
  private static final String UTF8_CSN = StandardCharsets.UTF_8.name();

  /**
   * Converts all of the characters in this String to upper case with
   * Locale.ENGLISH.
   *
   * @param str  string to be converted
   * @return     the str, converted to uppercase.
   */
  public static String toUpperCase(String str) {
    return str.toUpperCase(Locale.ENGLISH);
  }

  /**
   * Converts a string to a byte array using UTF8 encoding.
   */
  public static byte[] string2Bytes(String str) {
    try {
      return str.getBytes(UTF8_CSN);
    } catch (UnsupportedEncodingException e) {
      // should never happen!
      throw new IllegalArgumentException("UTF8 decoding is not supported", e);
    }
  }

}
