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
package org.apache.hadoop.conf;
import com.ctc.wstx.stax.WstxInputFactory;
import org.apache.hadoop.util.StringInterner;
import org.codehaus.stax2.XMLInputFactory2;
import org.codehaus.stax2.XMLStreamReader2;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Configuration for the project.
 */
public class Configuration implements Iterable<Map.Entry<String,String>>{

  static {
    // Add the config files in the resources fold.
    addDefaultResource("core-default.xml");
    addDefaultResource("core-site.xml");
  }

  /**
   * List of default Resources. Resources are loaded in the order of the list
   * entries
   */
  private static final CopyOnWriteArrayList<String> DEFAULT_RESOURCES =
      new CopyOnWriteArrayList<String>();

  /**
   * Specify exact input factory to avoid time finding correct one.
   * Factory is reusable across un-synchronized threads once initialized
   */
  private static final XMLInputFactory2 XML_INPUT_FACTORY =
      new WstxInputFactory();


  /**
   * The value reported as the setting resource when a key is set
   * by code rather than a file resource by dumpConfiguration.
   */
  private static final String UNKNOWN_RESOURCE = "Unknown";

  /**
   * Add a default resource. Resources are loaded in the order of the resources
   * added.
   * @param name file name. File should be present in the classpath.
   */
  public static synchronized void addDefaultResource(String name) {
    if(!DEFAULT_RESOURCES.contains(name)) {
      DEFAULT_RESOURCES.add(name);
    }
  }

  private Properties properties;
  private ClassLoader classLoader;
  {
    classLoader = Thread.currentThread().getContextClassLoader();
    if (classLoader == null) {
      classLoader = Configuration.class.getClassLoader();
    }
  }


  /** A new configuration. */
  public Configuration() {
    this(true);
  }


  private synchronized Properties getProperties() {
    if (properties == null) {
      properties = new Properties();
      loadResources(properties);
    }
    return properties;
  }

  private void loadResources(Properties properties) {
    for (String resource : DEFAULT_RESOURCES) {
      loadResource(properties, resource);
    }
  }

  private void loadResource(Properties properties, Object resource) {
    String name = UNKNOWN_RESOURCE;
    try {
      XMLStreamReader2 reader = null;
      if (resource instanceof URL) {
        reader = (XMLStreamReader2) parse((URL) resource);
      } else if (resource instanceof String) {
        URL url = getResource((String) resource);
        reader = (XMLStreamReader2) parse(url);
      } else if (resource instanceof InputStream) {
        reader = (XMLStreamReader2)parse((InputStream)resource, null);
      } else if (resource instanceof Properties) {
        overlay(properties, (Properties)resource);
      }

      StringBuilder token = new StringBuilder();
      String confName = null;
      String confValue = null;
      boolean confFinal = false;

      if (reader == null) {
        throw new RuntimeException(resource + " not found");
      }

      while (reader.hasNext()) {
        switch (reader.next()) {
        case XMLStreamConstants.START_DOCUMENT:
          String s = reader.getLocalName();
          if (s.equals("property")) {
            confName = null;
            confValue = null;
            confFinal = false;

            for (int index = 0; index < reader.getAttributeCount(); index++) {
              String propertyAttr = reader.getAttributeLocalName(index);
              if ("name".equals(propertyAttr)) {
                confName =
                    StringInterner.weakIntern(reader.getAttributeValue(index));
              } else if ("value".equals(propertyAttr)) {
                confValue =
                    StringInterner.weakIntern(reader.getAttributeValue(index));
              } else if ("final".equals(propertyAttr)) {
                confFinal = "true".equals(reader.getAttributeValue(index));
              }
            }
          }
          break;
        }
      }


    } catch (XMLStreamException ex) {

    } catch (IOException e) {
    }
  }

  private void overlay(Properties to, Properties from) {
    for (Map.Entry<Object, Object> entry: from.entrySet()) {
      to.put(entry.getKey(), entry.getValue());
    }
  }

  private XMLStreamReader parse(URL url)
      throws IOException, XMLStreamException {
    if (url == null) {
      return null;
    }

    URLConnection connection = url.openConnection();
    if (connection instanceof JarURLConnection) {
      // Disable caching for JarURLConnection to avoid sharing JarFile
      // with other users.
      connection.setUseCaches(false);
    }
    return parse(connection.getInputStream(), url.toString());
  }

  private XMLStreamReader parse(InputStream is,
      String systemId) throws XMLStreamException {
    if (is == null) {
      return null;
    }
    return XML_INPUT_FACTORY.createXMLStreamReader(systemId, is);
  }

  /**
   * Get the {@link URL} for the named resource.
   *
   * @param name resource name.
   * @return the url for the named resource.
   */
  public URL getResource(String name) {
    return classLoader.getResource(name);
  }


  /** A new configuration where the behavior of reading from the default
   * resources can be turned off.
   *
   * If the parameter {@code loadDefaults} is false, the new instance
   * will not load resources from the default files.
   * @param loadDefaults specifies whether to load from the default files
   */
  public Configuration(boolean loadDefaults) {

  }

  public Iterator<Map.Entry<String, String>> iterator() {
    return null;
  }
}
