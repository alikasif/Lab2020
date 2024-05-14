package bookapi.repository;

import bookapi.models.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Integer> {

    Iterable<Author> findALLByBookId(Integer bookId);
}