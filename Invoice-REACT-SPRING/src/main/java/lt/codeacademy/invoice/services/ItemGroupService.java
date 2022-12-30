package lt.codeacademy.invoice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lt.codeacademy.invoice.entities.ItemGroup;
import lt.codeacademy.invoice.repositories.ItemGroupRepository;

@Service
public class ItemGroupService {
	@Autowired
	ItemGroupRepository itemGroupRepository;
	
	public List<ItemGroup> getItemGroupList(){
		return itemGroupRepository.findAll();
	}
	
	public ItemGroup addItemGroup(ItemGroup itemGroup) {
		return itemGroupRepository.save(itemGroup);
	}
	
	public ItemGroup getItemGroupById(Long id) {
		Optional<ItemGroup> itemGroup = itemGroupRepository.findById(id);
		
		if (itemGroup.isEmpty()) {
			return null;
		}
		return itemGroup.get();
	}
	
	public ItemGroup updateItemGroupById(Long id, ItemGroup itemGroup) {
		return itemGroupRepository.save(itemGroup);
	}

	public String deleteItemGroupById(Long id) {
		boolean exists = itemGroupRepository.existsById( id );

		if (exists) {
			itemGroupRepository.deleteById( id );
			return "ItemGroup was deleted with id: " + id;
		}
		return "ItemGroup does not exist";
	}
}
