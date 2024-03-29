/*
 * This code was written by Bear Giles <bgiles@coyotesong.com> and he
 * licenses this file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Any contributions made by others are licensed to this project under
 * one or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * 
 * Copyright (c) 2013 Bear Giles <bgiles@coyotesong.com>
 */
package com.invariantproperties.sandbox.student.webservice.client.impl;

import com.invariantproperties.sandbox.student.domain.Instructor;
import com.invariantproperties.sandbox.student.domain.TestRun;
import com.invariantproperties.sandbox.student.webservice.client.AbstractManagerRestClientImpl;
import com.invariantproperties.sandbox.student.webservice.client.InstructorManagerRestClient;

/**
 * Implementation of InstructorManagerRestClient.
 * 
 * @author Bear Giles <bgiles@coyotesong.com>
 */
public class InstructorManagerRestClientImpl extends AbstractManagerRestClientImpl<Instructor> implements
        InstructorManagerRestClient {

    /**
     * Constructor.
     * 
     * @param instructorResource
     */
    public InstructorManagerRestClientImpl(final String resource) {
        super(resource, Instructor.class);
    }

    /**
     * Create JSON string.
     * 
     * @param name
     * @return
     */
    String createJson(final String name, final String emailAddress) {
        return String.format("{ \"name\": \"%s\", \"emailAddress\": \"%s\" }", name, emailAddress);
    }

    /**
     * Create JSON string.
     * 
     * @param name
     * @param testRun
     * @return
     */
    String createJson(final String name, final String emailAddress, final TestRun testRun) {
        return String.format("{ \"name\": \"%s\", \"emailAddress\": \"%s\", \"testUuid\": \"%s\" }", name,
                emailAddress, testRun.getUuid());
    }

    /**
     * @see com.invariantproperties.sandbox.student.webservice.client.InstructorManagerRestClient#createInstructor(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public Instructor createInstructor(final String name, final String emailAddress) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("'name' is required");
        }

        if (emailAddress == null || emailAddress.isEmpty()) {
            throw new IllegalArgumentException("'emailAddress' is required");
        }

        return createObject(createJson(name, emailAddress));
    }

    /**
     * @see com.invariantproperties.sandbox.student.webservice.client.InstructorManagerRestClient#createInstructorForTesting(java.lang.String,
     *      com.invariantproperties.sandbox.student.common.TestRun)
     */
    @Override
    public Instructor createInstructorForTesting(final String name, final String emailAddress, final TestRun testRun) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("'name' is required");
        }

        if (emailAddress == null || emailAddress.isEmpty()) {
            throw new IllegalArgumentException("'emailAddress' is required");
        }

        if (testRun == null || testRun.getUuid() == null || testRun.getUuid().isEmpty()) {
            throw new IllegalArgumentException("'testRun' is required");
        }

        return createObject(createJson(name, emailAddress, testRun));
    }

    /**
     * @see com.invariantproperties.sandbox.student.webservice.client.InstructorManagerRestClient#updateInstructor(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    @Override
    public Instructor updateInstructor(final String uuid, final String name, final String emailAddress) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("'name' is required");
        }

        if (emailAddress == null || emailAddress.isEmpty()) {
            throw new IllegalArgumentException("'emailAddress' is required");
        }

        return super.updateObject(createJson(name, emailAddress), uuid);
    }

    /**
     * @see com.invariantproperties.sandbox.student.webservice.client.InstructorManagerRestClient#deleteInstructor(java.lang.String)
     */
    @Override
    public void deleteInstructor(final String uuid) {
        super.deleteObject(uuid);
    }
}
