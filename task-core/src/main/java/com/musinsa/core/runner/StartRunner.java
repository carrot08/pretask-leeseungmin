package com.musinsa.core.runner;

import com.musinsa.core.common.type.ItemCategoryType;
import com.musinsa.core.collection.model.Brand;
import com.musinsa.core.collection.model.BrandItem;
import com.musinsa.core.collection.repository.InitBrandItemRepository;
import com.musinsa.core.collection.repository.InitBrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StartRunner implements CommandLineRunner {
    private final InitBrandItemRepository itemRepo;
    private final InitBrandRepository brandRepo;


    @Override
    public void run(String... args) {
        initData();
    }

    private void initData() {

        List<String> list =  ResourceReader.read("item-data.csv");

        for(int i=1; i<list.size(); i++) {

            String[] dataArr = list.get(i).split(",");

            Brand brand = new Brand(dataArr[0]);
            List<BrandItem> itemList = new ArrayList<>();
            for (int j=1; j< dataArr.length; j++) {
                ItemCategoryType categoryType = ItemCategoryType.getCategoryByCode(j);
                BrandItem item = new BrandItem(brand, categoryType, Integer.parseInt(dataArr[j]));
                itemList.add(item);
            }
            brandRepo.save(brand);
            itemRepo.saveAll(itemList);
        }
    }
}
