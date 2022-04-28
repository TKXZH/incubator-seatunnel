/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.seatunnel.api.sink;

import org.apache.seatunnel.api.serialization.Serializer;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Sink<IN, StateT, CommitInfoT, AggregatedCommitInfoT> extends Serializable {

    SinkWriter<IN, StateT> createWriter(SinkWriter.Context context) throws IOException;

    default SinkWriter<IN, StateT> restoreWriter(SinkWriter.Context context, List<StateT> states) throws IOException {
        return createWriter(context);
    }

    default Optional<Serializer<StateT>> getWriterStateSerializer() {
        return Optional.empty();
    }

    default Optional<SinkCommitter<CommitInfoT>> createCommitter() throws IOException {
        return Optional.empty();
    }

    default Optional<Serializer<CommitInfoT>> getCommitInfoSerializer() {
        return Optional.empty();
    }

    default Optional<SinkAggregatedCommitter<CommitInfoT, AggregatedCommitInfoT>> createAggregatedCommitter() throws IOException {
        return Optional.empty();
    }

    default Optional<Serializer<AggregatedCommitInfoT>> getAggregatedCommitInfoSerializer() {
        return Optional.empty();
    }
}
