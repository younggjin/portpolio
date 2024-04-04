package com.example.portfolio.dto;

import com.example.portfolio.entity.ItemEntity;
import com.example.portfolio.entity.ItemFileEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private Long idx;
    private int category1;
    private int category2;
    private Long category3;
    private String categoryname;
    private String item_name;
    private int item_price;
    private int item_quantity;
    private String item_content;
    private LocalDateTime regdate;

    private List<String> check;
    private List<String> check1;

    private MultipartFile mainFile; // 1번

    private List<MultipartFile> itemFile; //item_write.html -> controller 파일 담는 용도
    private List<String> originalFileName; //원본 파일 이름
    private List<String> storedFileName; //서버 저장용 파일 이름
    private int fileAttached; //파일 첨부 여부(첨부1, 미첨부0)

    public ItemDTO(Long idx, String itemName, int itemPrice, int itemQuantity, LocalDateTime regdate, List<String> storedFileName) {
        this.idx = idx;
        this.item_name = itemName;
        this.item_price = itemPrice;
        this.item_quantity = itemQuantity;
        this.regdate = regdate;
        this.storedFileName = storedFileName;
    }


    public static ItemDTO toItemDTO(ItemEntity itemEntity){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setIdx(itemEntity.getIdx());

        itemDTO.setCategory1(itemEntity.getItemCategoryEntity().getPidx1());
        itemDTO.setCategory2(itemEntity.getItemCategoryEntity().getPidx2());
        itemDTO.setCategory3((long) itemEntity.getItemCategoryEntity().getPidx3());
        itemDTO.setCategoryname(itemEntity.getItemCategoryEntity().getCategory_name());
        
        itemDTO.setItem_name(itemEntity.getItem_name());
        itemDTO.setItem_price(itemEntity.getItem_price());
        itemDTO.setItem_quantity(itemEntity.getItem_quantity());
        itemDTO.setItem_content(itemEntity.getItem_content());
        itemDTO.setRegdate(itemEntity.getRegdate());

        if(itemEntity.getFileAttached() == 0){ //파일이 없을 떄
            itemDTO.setFileAttached(itemEntity.getFileAttached());

        }else if(itemEntity.getFileAttached() == 1){ //파일이 있을 때
            List<String> originalFileNameList = new ArrayList<>();
            List<String> storedFileNameList = new ArrayList<>();

            for(ItemFileEntity itemFileEntity : itemEntity.getItemFileEntityList()){
                originalFileNameList.add(itemFileEntity.getOriginalFileName());
                storedFileNameList.add(itemFileEntity.getStoredFileName());

            }

            itemDTO.setOriginalFileName(originalFileNameList);
            itemDTO.setStoredFileName(storedFileNameList);

        }

        return itemDTO;
    }


}
