package guru.springframework.repositories;

import guru.springframework.domain.Product;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * Created by jt on 1/10/17.
 */
public interface ProductRepository extends CrudRepository<Product, UUID> {

    @Query(value= "select * from products")
    public List<Product> findAll();

    @Query(value="select * from products where id=?0")
    public List<Product> findProductById(UUID id);

    @Query("update products p set p.description = :description,p.price = :price,p.imageUrl = :imageUrl where p.id = :id")
    int update(@Param("name") String description,
               @Param("status") Integer price,
               @Param("name") String imageUrl);

    @Query(
            value =
                    "insert into products (description, price, imageUrl) values (:description, :price, :imageUrl)")
    void insertProduct(@Param("description") String description,
                       @Param("price") Integer price,
                       @Param("imageUrl") String imageUrl);

    @Query("delete from products p where p.id=:id")
     void deleteProductById(@Param("id") UUID id);
}
