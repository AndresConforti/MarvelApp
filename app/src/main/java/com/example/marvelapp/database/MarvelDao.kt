package com.example.marvelapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.marvelapp.database.entity.CharacterEntity

@Dao
interface MarvelDao {
    @Query("SELECT * FROM marvel_characters")
    fun getDBCharacters(): List<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(characterEntity: CharacterEntity)
}
