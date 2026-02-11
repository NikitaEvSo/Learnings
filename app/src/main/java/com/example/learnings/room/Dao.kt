    package com.example.learnings.room

    import androidx.room.Dao
    import androidx.room.Insert
    import androidx.room.OnConflictStrategy
    import androidx.room.Query
    import androidx.room.Transaction
    import kotlinx.coroutines.flow.Flow

    @Dao
    interface ProductDao {
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(product: ProductEntity): Long

        @Query("SELECT * FROM products")
        fun getAll(): Flow<List<ProductEntity>>

        @Query("DELETE FROM products")
        suspend fun clear()

        @Query("SELECT * FROM products WHERE id = :id")
        fun getById(id: Int): Flow<ProductEntity?>
    }
    @Dao
    interface CartDao{
        @Query("SELECT amount FROM user_cart WHERE productId = :id")
        fun getAmount(id: Int): Flow<Int?>
        @Query("SELECT * FROM user_cart WHERE productId = :id")
        suspend fun getCartItem(id: Int): CartEntity?
        @Query("SELECT * FROM user_cart")
        fun getAllCartItems(): Flow<List<CartEntity>>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(cart: CartEntity): Long
        @Query("UPDATE user_cart SET amount = amount + 1 WHERE productId = :id")
        suspend fun increment(id: Int)
        @Transaction
        suspend fun addOrIncrement(id: Int) {
            val item = getCartItem(id)
            if (item == null) {
                insert(CartEntity(productId = id, 1))
            } else {
                increment(id)
            }
        }
        @Query("DELETE FROM user_cart")
        suspend fun clear()

        @Query("DELETE FROM user_cart WHERE productId = :id")
        suspend fun remove(id: Int)

    }