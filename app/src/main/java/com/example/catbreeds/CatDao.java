package com.example.catbreeds;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CatDao {
    @Query("SELECT * FROM catbreed")
    List<CatBreed> getAll();

    @Query("SELECT * FROM catbreed WHERE id = :id")
    CatBreed findCatById(String id);

    @Query("SELECT * FROM catbreed WHERE name LIKE :search")
    List<CatBreed> findCatsWithName(String search);

    @Query("UPDATE catbreed SET isFavorite = :isFavorite")
    void setFav(int isFavorite);

    @Query("UPDATE catbreed SET isFavorite = :isFavorite WHERE id = :id")
    void updateFav(int isFavorite, String id);

    @Query("SELECT * FROM catbreed WHERE isFavorite = :isFavorite")
    List<CatBreed> findFavCats(int isFavorite);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CatBreed> cats);


}
