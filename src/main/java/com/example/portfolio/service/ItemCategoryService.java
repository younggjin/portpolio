package com.example.portfolio.service;

import com.example.portfolio.dto.ItemCategoryDTO;
import com.example.portfolio.entity.ItemCategoryEntity;
import com.example.portfolio.repository.ItemCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemCategoryService {


    private final ItemCategoryRepository itemCategoryRepository;

    public List<ItemCategoryDTO> category_first(){

        List<ItemCategoryDTO> itemCategoryDTOList = new ArrayList<>();
        List<ItemCategoryEntity> itemCategoryEntities = itemCategoryRepository.findFirst();
        for(ItemCategoryEntity itemCategoryEntity : itemCategoryEntities)
        {
            itemCategoryDTOList.add(ItemCategoryDTO.itemCategoryDTO(itemCategoryEntity));
        }

        return itemCategoryDTOList;
    }

    public void category_save(ItemCategoryDTO itemCategoryDTO){
        ItemCategoryEntity itemCategoryEntity = ItemCategoryEntity.itemCategoryEntity(itemCategoryDTO);
        itemCategoryRepository.save(itemCategoryEntity);
    }

    public List<ItemCategoryDTO> category_second(String category1) // category1 = pidx1(String)
    {
        List<ItemCategoryEntity> entityList = itemCategoryRepository.findSecond(category1);
        List<ItemCategoryDTO> itemCategoryDTOList = new ArrayList<>();

        for(ItemCategoryEntity itemCategoryEntity : entityList)
        {
            itemCategoryDTOList.add(ItemCategoryDTO.itemCategoryDTO(itemCategoryEntity));
        }

        return itemCategoryDTOList;
    }

    public List<ItemCategoryDTO> category_third(String category1, String category2) {
        List<ItemCategoryDTO> itemCategoryDTOList = new ArrayList<>();
        List<ItemCategoryEntity> itemCategoryEntities = itemCategoryRepository.findThird(category1, category2);

        for(ItemCategoryEntity itemCategoryEntity : itemCategoryEntities){
            itemCategoryDTOList.add(ItemCategoryDTO.itemCategoryDTO(itemCategoryEntity));
        }

        return itemCategoryDTOList;
    }
    public List<ItemCategoryDTO> categoryFirstList() {
        List<ItemCategoryEntity> categoryEntityList = itemCategoryRepository.findFirstName();
        List<ItemCategoryDTO> itemCategoryDTOList = new ArrayList<>();
        for(ItemCategoryEntity itemCategoryEntity : categoryEntityList){
            itemCategoryDTOList.add(ItemCategoryDTO.itemCategoryDTO(itemCategoryEntity));
        }
        return itemCategoryDTOList;
    }
    public List<ItemCategoryDTO> categorySecondList(String category1) {
        List<ItemCategoryEntity> categoryEntityList = itemCategoryRepository.findSecondName(category1);
        List<ItemCategoryDTO> itemCategoryDTOList = new ArrayList<>();
        for(ItemCategoryEntity itemCategoryEntity : categoryEntityList){
            itemCategoryDTOList.add(ItemCategoryDTO.itemCategoryDTO(itemCategoryEntity));
        }
        return itemCategoryDTOList;

    }
    public List<ItemCategoryDTO> categoryThirdList(String category1, String category2) {
        List<ItemCategoryEntity> categoryEntityList = itemCategoryRepository.findThirdName(category1, category2);
        List<ItemCategoryDTO> itemCategoryDTOList = new ArrayList<>();
        for(ItemCategoryEntity itemCategoryEntity : categoryEntityList){
            itemCategoryDTOList.add(ItemCategoryDTO.itemCategoryDTO(itemCategoryEntity));
        }
        return itemCategoryDTOList;
    }
    public List<ItemCategoryDTO> findAll(){
        List<ItemCategoryEntity> categoryEntityList = itemCategoryRepository.findAll();
        List<ItemCategoryDTO> itemCategoryDTOList = new ArrayList<>();
        for(ItemCategoryEntity itemCategoryEntity : categoryEntityList){
            itemCategoryDTOList.add(ItemCategoryDTO.itemCategoryDTO(itemCategoryEntity));
        }
        return itemCategoryDTOList;
    }
}
