package com.musinsa.asmin.collection.service;

import com.musinsa.admin.AdminMainApplication;
import com.musinsa.admin.collection.model.dto.BrandDto;
import com.musinsa.admin.collection.repository.BrandRepository;
import com.musinsa.admin.collection.service.BrandService;
import com.musinsa.core.collection.model.Brand;
import com.musinsa.core.common.exception.CollectionRuntimeException;
import com.musinsa.core.common.type.ResultCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest(classes = AdminMainApplication.class)
public class BrandServiceTest {

    @Autowired
    private BrandRepository brandRepo;

    @Autowired
    private BrandService brandService;

    @Test
    public void createBrandTest() {

        BrandDto.BrandCreateRequest request = new BrandDto.BrandCreateRequest();
        request.setName("TEST");

        int start = this.brandRepo.findAll().size();
        brandService.createBrand(request);
        int end = this.brandRepo.findAll().size();

        assertThat(start).isEqualTo(end - 1);
    }

    @Test
    public void updateBrandTest() {
        List<Brand> brandList = brandRepo.findAll();
        Brand brand = brandList.get(0);

        String chgName = "TESTNAME";
        BrandDto.BrandUpdateRequest request = new BrandDto.BrandUpdateRequest();
        request.setBrandId(brand.getId());
        request.setName(chgName);

        brandService.updateBrand(request);

        Brand chgBrand = brandRepo.getBrandById(brand.getId());

        assertThat(chgBrand.getName()).isEqualTo(chgName);
    }

    @Test
    public void deleteBrandTest() {
        List<Brand> brandList = brandRepo.findAll();
        Brand brand = brandList.get(0);

        BrandDto.BrandDeleteRequest request = new BrandDto.BrandDeleteRequest();
        request.setBrandId(brand.getId());

        int start = this.brandRepo.findAll().size();

        brandService.deleteBrand(request);
        int end = this.brandRepo.findAll().size();

        assertThat(start).isEqualTo(end + 1);

    }

}
