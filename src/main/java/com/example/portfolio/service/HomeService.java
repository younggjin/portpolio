package com.example.portfolio.service;

import com.example.portfolio.dto.ItemDTO;
import com.example.portfolio.entity.ItemEntity;
import com.example.portfolio.repository.ItemRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeService {

    private final ItemRepository itemRepository;

    public HomeService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<ItemDTO> newListItem(){
        Pageable pageable = PageRequest.of(0, 4, Sort.by("idx").descending());
        List<ItemEntity> itemEntityList = itemRepository.findAll(pageable).getContent();        List<ItemDTO> itemDTOList = new ArrayList<>();
        for(ItemEntity item : itemEntityList){
            itemDTOList.add(ItemDTO.toItemDTO(item));

        }
        return itemDTOList;
    }
}
