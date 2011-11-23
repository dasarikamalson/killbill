/*
 * Copyright 2010-2011 Ning, Inc.
 *
 * Ning licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.ning.billing.account.dao;

import com.ning.billing.account.api.FieldStore;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.testng.Assert.assertEquals;

@Test(groups={"account-dao"})
public class TestFieldStore extends AccountDaoTestBase {
    @Test
    public void testFieldStore() {
        UUID id = UUID.randomUUID();
        String objectType = "Test widget";

        FieldStore fieldStore = new FieldStore(id, objectType);

        String fieldName = "TestField1";
        String fieldValue = "Kitty Hawk";
        fieldStore.setValue(fieldName, fieldValue);

        fieldStore.save();

        fieldStore = FieldStore.create(id, objectType);
        fieldStore.load();

        assertEquals(fieldStore.getValue(fieldName), fieldValue);

        fieldValue = "Cape Canaveral";
        fieldStore.setValue(fieldName, fieldValue);
        assertEquals(fieldStore.getValue(fieldName), fieldValue);
        fieldStore.save();

        fieldStore = FieldStore.create(id, objectType);
        assertEquals(fieldStore.getValue(fieldName), null);
        fieldStore.load();

        assertEquals(fieldStore.getValue(fieldName), fieldValue);
    }
}
