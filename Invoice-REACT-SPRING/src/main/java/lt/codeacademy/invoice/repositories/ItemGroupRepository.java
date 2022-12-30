package lt.codeacademy.invoice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.codeacademy.invoice.entities.ItemGroup;


@Repository
public interface ItemGroupRepository extends JpaRepository<ItemGroup, Long>{

}
