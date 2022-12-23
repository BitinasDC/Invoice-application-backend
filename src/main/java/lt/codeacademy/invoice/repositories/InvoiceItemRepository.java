package lt.codeacademy.invoice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import lt.codeacademy.invoice.entities.InvoiceItem;


	@Repository
	public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {
}
