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

import org.apache.hadoop.io.LongWritable;

/**
 * A JavaLongObjectInspector inspects a Java Long Object.
 */
public class JavaLongObjectInspector extends
    AbstractPrimitiveJavaObjectInspector implements SettableLongObjectInspector {

  JavaLongObjectInspector() {
    super(PrimitiveObjectInspectorUtils.longTypeEntry);
  }

  /**
   * Method to do a safety check and promotion in case of integer
   * @param o
   * @return
   */
  private Long getSafeLong(Object o) {
  	if (o == null) {
  	  return null;
  	}

  	// if this is an integer attempt to promote it
  	Long l = null;
  	if (o instanceof Integer) {
  	  l = new Long(((Integer) o).intValue());
  	} else {
  	  l = (Long)o;
  	}

  	return l;
  }

  @Override
  public Object getPrimitiveWritableObject(Object o) {
  	return o == null ? null : new LongWritable(getSafeLong(o));
  }

  @Override
  public long get(Object o) {
  	return getSafeLong(o);
  }

  @Override
  public Object create(long value) {
  	return Long.valueOf(value);
  }

  @Override
  public Object set(Object o, long value) {
  	return Long.valueOf(value);
  }
}
