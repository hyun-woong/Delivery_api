package com.sparta.delivery_api.Controller;

import com.sparta.delivery_api.Dto.StoreInfoDto;
import com.sparta.delivery_api.Entity.StoreInfo;
import com.sparta.delivery_api.Service.StoreInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor // final, NoneNull이 붙은 필드에 대해 생성자를 생성해 줌
public class StoreListController {

    private final StoreInfoService storeInfoService;

    @PostMapping("/restaurant/register")
    public StoreInfo storeInfo (@RequestBody @Valid StoreInfoDto storeInfoDto){
        return storeInfoService.storeInfo(storeInfoDto);
    }

    @GetMapping("/restaurants")
    public List<StoreInfo> getAllStoreInfo(){
        return storeInfoService.getAllStoreInfo();
    }
}
