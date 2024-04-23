package com.example.portfolio.service;

import com.example.portfolio.dto.ItemDTO;
import com.example.portfolio.dto.ItemListDTO;
import com.example.portfolio.entity.ItemCategoryEntity;
import com.example.portfolio.entity.ItemEntity;
import com.example.portfolio.entity.ItemFileEntity;
import com.example.portfolio.repository.ItemCategoryRepository;
import com.example.portfolio.repository.ItemFileRepository;
import com.example.portfolio.repository.ItemRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class ItemService {

    @Value("${save.path}")
    private String filepath;

    private final ItemRepository itemRepository;
    private final ItemCategoryRepository itemCategoryRepository;
    private final ItemFileRepository itemFileRepository;

    public ItemService(ItemRepository itemRepository, ItemCategoryRepository itemCategorizeRepository, ItemCategoryRepository itemCategoryRepository, ItemFileRepository itemFileRepository) {
        this.itemRepository = itemRepository;
        this.itemCategoryRepository = itemCategoryRepository;
        this.itemFileRepository = itemFileRepository;
    }

    @Transactional
    public void saveItem(ItemDTO itemDTO) throws IOException {

        if(itemDTO.getMainFile().isEmpty() || itemDTO.getItemFile().isEmpty()) { //파일이 없을 때
            ItemEntity itemEntity = ItemEntity.toSaveEntity(itemDTO);

            ItemCategoryEntity itemCategoryEntity = itemCategoryRepository.findById(itemDTO.getCategory3()).orElse(null);

            itemEntity.setItemCategoryEntity(itemCategoryEntity);

            itemRepository.save(itemEntity);

        }else{ //파일이 있을 때
            ItemEntity itemEntity = ItemEntity.toSaveFileEntity(itemDTO);

            ItemCategoryEntity itemCategoryEntity = itemCategoryRepository.findById(itemDTO.getCategory3()).orElse(null);
            itemEntity.setItemCategoryEntity(itemCategoryEntity);

            long saveIdx = itemRepository.save(itemEntity).getIdx();

            ItemEntity item = itemRepository.findById(saveIdx).get();

            MultipartFile mainFile = itemDTO.getMainFile(); // 1번 파일
            String originalFilename = mainFile.getOriginalFilename(); // 1번파일
            String storedFilename = System.currentTimeMillis() + "_" + originalFilename; // 1번

            String savePath = filepath + storedFilename; // 1번
            mainFile.transferTo(new File(savePath));//IOException 추가

            String thumbsname= storedFilename; //썸네일
            String save = filepath + "/thum/" + thumbsname;

            //썸네일 생성 (복사할파일위치,썸네일생성경로,가로,세로)
            Thumbnailator.createThumbnail(new File(savePath),new File(save),150, 150);

            ItemFileEntity MainFileEntity = ItemFileEntity.toMainFileEntity(item, originalFilename, storedFilename);
            itemFileRepository.save(MainFileEntity);

            for(MultipartFile itemFile : itemDTO.getItemFile()){
                String originalFilename1 = itemFile.getOriginalFilename();
                String storedFilename1 = System.currentTimeMillis() + "_" + originalFilename1;
                String savePath1 = filepath + storedFilename1;
                itemFile.transferTo(new File(savePath1));

                ItemFileEntity itemFileEntity = ItemFileEntity.toItemFileEntity(item, originalFilename1, storedFilename1);
                itemFileRepository.save(itemFileEntity);

            }

        }
    }
    @Transactional
    public Page<ItemDTO> paging(Pageable pageable){
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 8;

        Page<ItemEntity> itemEntities = itemRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "idx")));

        Page<ItemDTO> itemDTOS = itemEntities.map(item -> new ItemDTO(item.getIdx(), item.getItem_name(), item.getItem_price(), item.getItem_quantity(), item.getRegdate(), Collections.singletonList(item.getItemFileEntityList().get(0).getStoredFileName())));
        return itemDTOS;
    }

    @Transactional
    public Page<ItemListDTO> categoryPaging(Pageable pageable, Long idx){
        int page = pageable.getPageNumber() -1;
        int pageLimit = 8;

        ItemCategoryEntity itemCategoryEntity = itemCategoryRepository.findById(idx).orElse(null);
        List<ItemEntity> itemEntityList = itemCategoryEntity.getItemEntityList();

        List<ItemListDTO> itemDTOS = new ArrayList<>();
        for (ItemEntity item : itemEntityList){
            ItemListDTO itemListDTO = ItemListDTO.toItemListDTO(item);
            itemDTOS.add(itemListDTO);
        }

        Page<ItemListDTO> itemListDTOPage = new PageImpl<>(itemDTOS, PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "regdate")), itemDTOS.size());

        return itemListDTOPage;
    }

    @Transactional
    public ItemDTO findById(Long idx){ //item_view 보여줌

        List<ItemCategoryEntity> first = itemCategoryRepository.findFirstName();

        Optional<ItemEntity> itemEntity = itemRepository.findById(idx);
        if(itemEntity.isPresent()){
            ItemEntity item = itemEntity.get();
            ItemDTO itemDTO = ItemDTO.toItemDTO(item);
            return itemDTO;
        }else{
            return null;
        }
    }


    public void itemmodify(ItemDTO itemDTO) throws IOException {
        List<MultipartFile> itemFile = itemDTO.getItemFile(); //수정하는 파일
        ItemEntity itemidx = itemRepository.findById(itemDTO.getIdx()).get();
        List<String> check1 = itemDTO.getCheck1(); //체크박스 checked 인지 unchecked인지


        // 체크가 있을 때  
        for (int i = 0; i < check1.size(); i++) {// 체크박스 사이즈 체크
            if (check1.get(i).equals("checked")) {//체크박스 value값이 checked일 때
                if (!itemFile.get(i).isEmpty()) {//파일이 있을 경우

                    List<ItemFileEntity> item = itemFileRepository.findByItemEntity_Idx(itemDTO.getIdx());

                    //원래 있던 파일 삭제
                    String filePath = item.get(i).getStoredFileName();
                    File file = new File(filepath + filePath);

                    if (file.exists()) {
                        file.delete();
                    }

                    MultipartFile NewFile = itemDTO.getItemFile().get(i);
                    String originalFileName = itemFile.get(i).getOriginalFilename();
                    String storedFileName = System.currentTimeMillis() + "_" + originalFileName;

                    String savePath = filepath + storedFileName;
                    NewFile.transferTo(new File(savePath));

                    if(i == 0){ //첫번째 파일을 수정할 때 썸네일 삭제하고 새로 만들기
                        String thumfile = item.get(0).getStoredFileName();
                        File thumbfile = new File(filepath+"/thum/" + thumfile);

                        if (thumbfile.exists()) {
                            thumbfile.delete();
                        }

                        String thumbsname = storedFileName; //썸네일
                        String save = filepath + "/thum/" + thumbsname;

                        //썸네일 생성 (복사할파일위치,썸네일생성경로,가로,세로)
                        Thumbnailator.createThumbnail(new File(savePath),new File(save),150, 150);
                    }

                    item.get(i).setOriginalFileName(originalFileName);
                    item.get(i).setStoredFileName(storedFileName);

                    itemFileRepository.save(item.get(i));

                } else {//파일이 없을 경우
                    if (i == 0) { //첫번째 파일이 없을 경우
                        //돌아가
                        log.info("썸네일 읎으면 안된다");

                    } else { //수정하는 파일이 없을 경우 삭제
                        //삭제  db 삭제 세이브
                        List<ItemFileEntity> item = itemFileRepository.findByItemEntity_Idx(itemDTO.getIdx());

                        String filePath = item.get(i).getStoredFileName();
                        File file = new File(filepath + filePath);

                        if (file.exists()) {
                            file.delete();
                        }

                        item.get(i).setOriginalFileName("Null");
                        item.get(i).setStoredFileName("Null");
                        itemFileRepository.save(item.get(i));
                    }
                }
            }
        }
        //채크가 없을 때
        //변경 내용 세이브

        itemidx.setItem_name(itemDTO.getItem_name());
        itemidx.setItem_price(itemDTO.getItem_price());
        itemidx.setItem_quantity(itemDTO.getItem_quantity());
        itemidx.setItem_content(itemDTO.getItem_content());

        ItemCategoryEntity itemCategoryEntity = itemCategoryRepository.findById(itemDTO.getCategory3()).orElse(null);

        itemidx.setItemCategoryEntity(itemCategoryEntity);

        itemRepository.save(itemidx);
    }

    @Transactional
    public ItemEntity itemdelete(Long idx){
        Optional<ItemEntity> itemEntity = itemRepository.findById(idx);

        int filesize = itemEntity.get().getItemFileEntityList().size();

        for(int a=0; a<filesize; a++) {
           String filename = itemEntity.get().getItemFileEntityList().get(a).getStoredFileName();

           File file = new File(filepath + filename);

            if (file.exists()) {
                file.delete();
            }

        }
        String thumfile = itemEntity.get().getItemFileEntityList().get(0).getStoredFileName();
        File thumbfile = new File(filepath +"/thum/" + thumfile);

        if (thumbfile.exists()) {
            thumbfile.delete();
        }
        itemRepository.delete(itemEntity.get());

        return null;
    }
}
