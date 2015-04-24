/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.kuujo.copycat.io.serializer;

import net.kuujo.copycat.io.Buffer;

/**
 * Provides custom object serialization.
 * <p>
 * This interface can be implemented to provide custom serializers to {@link Serializer}.
 * Users can register a {@link ObjectWriter} in a couple of ways. The first method registers
 * a serializer by creating a file in the `META-INF/services/net/kuujo/copycat/io/serializer` directory.
 * {@link Serializer} will scan this directory for serializer registrations
 * when a new instance is created. The file should contain an {@code id}, the serializable {@code class}, and the
 * {@code serializer} class. For example:
 * <pre>
 * {@code
 * id=1
 * class=net.kuujo.copycat.raft.protocol.AppendRequest
 * serializer=net.kuujo.copycat.raft.protocol.AppendRequest.Serializer
 * }
 * </pre>
 * Similarly, serializers can be registered in code via {@link Serializer#register(Class, int, ObjectWriter)}.
 *
 * @author <a href="http://github.com/kuujo">Jordan Halterman</a>
 */
public interface ObjectWriter<T> {

  /**
   * Writes the object to the given buffer.
   *
   * @param object The object to write.
   * @param buffer The buffer to which to write the object.
   * @param serializer The Copycat serializer.
   */
  void write(T object, Buffer buffer, Serializer serializer);

  /**
   * Reads the object from the given buffer.
   *
   * @param type The type to read.
   * @param buffer The buffer from which to read the object.
   * @return The read object.
   * @param serializer The Copycat serializer.
   */
  T read(Class<T> type, Buffer buffer, Serializer serializer);

}
