package cu.tissca.something.repos;

import cu.tissca.something.MyProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface MyProductRepository extends JpaRepository<MyProduct, Long> {
}