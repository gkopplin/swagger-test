package co.ga.bookstore;

import io.swagger.annotations.ApiOperation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface BookRepository extends CrudRepository<Book, Long> {

    @RestResource(path = "byTitle")
    @ApiOperation("find all books that contain the string q in their title, ignoring case")
    List<Book> findByTitleIgnoreCaseContaining(@Param("q") String q);

    @ApiOperation(value = "find book by id", notes = "This method will return a book with the matching id")
    @RestResource(path = "byId")
    @Override
    Optional<Book> findById(Long id);

    @ApiOperation(value = "find books with max title length", notes = "This method will return a list of books with the longest title length")
    @RestResource(path = "max")
    @Query("FROM Book WHERE LENGTH(title) = (SELECT MAX(LENGTH(title)) FROM Book)")
    List<Book> findBooksWithMaxTitle();
}

