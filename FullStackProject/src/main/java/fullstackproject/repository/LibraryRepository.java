
package fullstackproject.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import fullstackproject.model.Library;

//Interface extending the JPARepository Interface
public interface LibraryRepository extends JpaRepository<Library, Integer> {

}




