/*
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

package org.apache.flink.runtime.scheduler;

import org.apache.flink.annotation.VisibleForTesting;
import org.apache.flink.runtime.clusterframework.types.ResourceProfile;
import org.apache.flink.runtime.jobmanager.scheduler.SlotSharingGroup;
import org.apache.flink.runtime.scheduler.strategy.ExecutionVertexID;
import org.apache.flink.util.Preconditions;

import javax.annotation.Nonnull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/** Represents execution vertices that will run the same shared slot. */
public class ExecutionSlotSharingGroup {

    private final Set<ExecutionVertexID> executionVertexIds;

    @Nonnull private final SlotSharingGroup slotSharingGroup;

    public ExecutionSlotSharingGroup(@Nonnull SlotSharingGroup slotSharingGroup) {
        this.slotSharingGroup = Preconditions.checkNotNull(slotSharingGroup);
        this.executionVertexIds = new HashSet<>();
    }

    public void addVertex(final ExecutionVertexID executionVertexId) {
        executionVertexIds.add(executionVertexId);
    }

    @VisibleForTesting
    @Nonnull
    SlotSharingGroup getSlotSharingGroup() {
        return slotSharingGroup;
    }

    @Nonnull
    ResourceProfile getResourceProfile() {
        return slotSharingGroup.getResourceProfile();
    }

    public Set<ExecutionVertexID> getExecutionVertexIds() {
        return Collections.unmodifiableSet(executionVertexIds);
    }

    @Override
    public String toString() {
        return "ExecutionSlotSharingGroup{"
                + "executionVertexIds="
                + executionVertexIds
                + ", slotSharingGroup="
                + slotSharingGroup
                + '}';
    }
}
