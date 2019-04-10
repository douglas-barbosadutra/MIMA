package com.mima.wbs.service.impl;

import com.mima.wbs.service.ItemService;
import com.mima.wbs.domain.Item;
import com.mima.wbs.repository.ItemRepository;
import com.mima.wbs.service.dto.ItemDTO;
import com.mima.wbs.service.dto.WBSDTO;
import com.mima.wbs.service.mapper.ItemMapper;
import com.mima.wbs.service.mapper.WBSMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Item.
 */
@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private final Logger log = LoggerFactory.getLogger(ItemServiceImpl.class);

    private final ItemRepository itemRepository;

    private final ItemMapper itemMapper;
    
    private final WBSMapper wbsMapper;

    public ItemServiceImpl(ItemRepository itemRepository, ItemMapper itemMapper, WBSMapper wbsMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
        this.wbsMapper = wbsMapper;
    }

    /**
     * Save a item.
     *
     * @param itemDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ItemDTO save(ItemDTO itemDTO) {
        log.debug("Request to save Item : {}", itemDTO);
        Item item = itemMapper.toEntity(itemDTO);
        item = itemRepository.save(item);
        return itemMapper.toDto(item);
    }

    /**
     * Get all the items.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ItemDTO> findAll() {
        log.debug("Request to get all Items");
        return itemRepository.findAll().stream()
            .map(itemMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one item by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ItemDTO> findOne(Long id) {
        log.debug("Request to get Item : {}", id);
        return itemRepository.findById(id)
            .map(itemMapper::toDto);
    }

    /**
     * Delete the item by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Item : {}", id);
        itemRepository.deleteById(id);
    }
    
    @Override
	public Optional<ItemDTO> findRootByWBS(Long idWBS) {
		WBSDTO wbs = new WBSDTO();
		wbs.setId((long) idWBS);
		
		List<ItemDTO> list = itemRepository.findAllByWbs(wbsMapper.toEntity(wbs)).stream()
	            .map(itemMapper::toDto)
	            .collect(Collectors.toCollection(LinkedList::new));

		for(ItemDTO item: list) {
			if(item.getFatherId() == null) {
				Optional<ItemDTO> result = Optional.of(item);
				return result;
			}
		}
		return null;
	}
}
