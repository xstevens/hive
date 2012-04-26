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
package org.apache.hadoop.hive.serde2.objectinspector.primitive;

import org.apache.hadoop.io.Text;

/**
 * A JavaStringObjectInspector inspects a Java String Object.
 */
public class JavaStringObjectInspector extends
    AbstractPrimitiveJavaObjectInspector implements
    SettableStringObjectInspector {

  JavaStringObjectInspector() {
    super(PrimitiveObjectInspectorUtils.stringTypeEntry);
  }

  private String getSafeString(Object o) {
    if (o == null) {
      return null;
    }

    String s = null;
    if (o instanceof String) {
      s = (String)o;
    } else {
      s = String.valueOf(o);
    }

    return s;
  }
  
  @Override
  public Text getPrimitiveWritableObject(Object o) {
    return o == null ? null : new Text(getSafeString(o));
  }
  
  @Override
  public String getPrimitiveJavaObject(Object o) {
    return getSafeString(o);
  }

  @Override
  public Object create(Text value) {
    return value == null ? null : value.toString();
  }

  @Override
  public Object set(Object o, Text value) {
    return value == null ? null : value.toString();
  }

  @Override
  public Object create(String value) {
    return value;
  }

  @Override
  public Object set(Object o, String value) {
    return value;
  }
}
