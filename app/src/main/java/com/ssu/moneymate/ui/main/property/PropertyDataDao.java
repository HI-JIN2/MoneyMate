package com.ssu.moneymate.ui.main.property;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.ssu.moneymate.ui.main.fixed.FixedData;

import java.util.List;

@Dao
public interface PropertyDataDao {
    @Insert(onConflict = REPLACE)
    void insert(PropertyData data);

    @Query("SELECT * FROM property_table")
    List<PropertyData> getAll();
}
