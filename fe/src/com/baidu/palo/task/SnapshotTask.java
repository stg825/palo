// Copyright (c) 2017, Baidu.com, Inc. All Rights Reserved

// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package com.baidu.palo.task;

import com.baidu.palo.thrift.TResourceInfo;
import com.baidu.palo.thrift.TSnapshotRequest;
import com.baidu.palo.thrift.TTaskType;

public class SnapshotTask extends AgentTask {
    private long jobId;

    private long version;
    private long versionHash;

    private int schemaHash;

    private long timeout;

    public SnapshotTask(TResourceInfo resourceInfo, long backendId, long jobId, long dbId, long tableId,
                        long partitionId, long indexId, long tabletId, long version, long versionHash,
                        int schemaHash, long timeout) {
        super(resourceInfo, backendId, TTaskType.MAKE_SNAPSHOT, dbId, tableId, partitionId, indexId, tabletId);

        this.jobId = jobId;

        this.version = version;
        this.versionHash = versionHash;
        this.schemaHash = schemaHash;

        this.timeout = timeout;
    }

    public long getJobId() {
        return jobId;
    }

    public long getVersion() {
        return version;
    }

    public long getVersionHash() {
        return versionHash;
    }

    public int getSchemaHash() {
        return schemaHash;
    }

    public long getTimeout() {
        return timeout;
    }

    public TSnapshotRequest toThrift() {
        TSnapshotRequest request = new TSnapshotRequest(tabletId, schemaHash);
        request.setVersion(version);
        request.setVersion_hash(versionHash);
        return request;
    }
}