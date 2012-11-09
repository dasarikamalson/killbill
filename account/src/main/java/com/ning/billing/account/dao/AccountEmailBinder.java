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

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.skife.jdbi.v2.SQLStatement;
import org.skife.jdbi.v2.sqlobject.Binder;
import org.skife.jdbi.v2.sqlobject.BinderFactory;
import org.skife.jdbi.v2.sqlobject.BindingAnnotation;

import com.ning.billing.account.api.AccountEmail;

@BindingAnnotation(AccountEmailBinder.AccountEmailBinderFactory.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface AccountEmailBinder {

    public static class AccountEmailBinderFactory implements BinderFactory {

        @Override
        public Binder<AccountEmailBinder, AccountEmail> build(final Annotation annotation) {
            return new Binder<AccountEmailBinder, AccountEmail>() {
                @Override
                public void bind(@SuppressWarnings("rawtypes") final SQLStatement q, final AccountEmailBinder bind, final AccountEmail accountEmail) {
                    q.bind("id", accountEmail.getId().toString());
                    q.bind("accountId", accountEmail.getAccountId().toString());
                    q.bind("email", accountEmail.getEmail());
                }
            };
        }
    }
}
