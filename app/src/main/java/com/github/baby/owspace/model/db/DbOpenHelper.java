/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.baby.owspace.model.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbOpenHelper extends SQLiteOpenHelper {
  private static final int VERSION = 1;

  private static final String CREATE_LIST = "";
  private static final String CREATE_ITEM = "";
  private static final String CREATE_ITEM_LIST_ID_INDEX ="";

  public DbOpenHelper(Context context) {
    super(context, "owspace.db", null, VERSION);
  }

  @Override public void onCreate(SQLiteDatabase db) {
    db.execSQL(CREATE_LIST);
    db.execSQL(CREATE_ITEM);
    db.execSQL(CREATE_ITEM_LIST_ID_INDEX);
  }

  @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
  }
}
