# README

### About The Project
> - 카테고리별 브랜드 아이템을 관리한다.
> - 특정 조건의 api를 제공한다.


### 개발환경
- Java 17
- Spring Boot 3.1.1
- Spring Data JPA 3.1.1
- Spring Validation 3.1.1
- h2 Database
- Junit5
- mockito

### 실행방법
1. Clone the repo 
2. open -> build.gradle 선택 -> open as project
3. Configuration MainApplication api실행  
3. Configuration AdminMainApplication admin실행 
 
## 구현 범위
###  multi module
- core, api모듈, admin모듈로 구성

###  init data
- schema.sql 파일을 사용하여 DB 스키마를 정의
- runner를 이용하여 item-data.csv로 data 저장

###  조회 API
1. 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API
    - url : `/api/v1/items/lowest-in-category`
    - ex> http://localhost:8080/api/v1/items/lowest-in-category 
    - 카테고리별 최저가격 상품을 가져와 조합.
      - 응답값 
          ```json
          {
              "httpStatus": 200,
              "message": "OK",
              "data": {
                  "itemList": [
                      {
                          "categoryName": "상의",
                          "brandName": "C",
                          "price": "10,000"
                      },
                      {
                          "categoryName": "아우터",
                          "brandName": "E",
                          "price": "5,000"
                      },
                      {
                          "categoryName": "바지",
                          "brandName": "D",
                          "price": "3,000"
                      },
                      {
                          "categoryName": "스니커즈",
                          "brandName": "G",
                          "price": "9,000"
                      },
                      {
                          "categoryName": "가방",
                          "brandName": "A",
                          "price": "2,000"
                      },
                      {
                          "categoryName": "모자",
                          "brandName": "D",
                          "price": "1,500"
                      },
                      {
                          "categoryName": "양말",
                          "brandName": "I",
                          "price": "1,700"
                      },
                      {
                          "categoryName": "액세서리",
                          "brandName": "F",
                          "price": "1,900"
                      }
                  ],
                "totalPrice": "34,100"
              }
          }      
            
          ```
    
2. 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API
    - url : `/api/v1/items/lowest-by-brand`
    - ex> http://localhost:8080/api/v1/items/lowest-by-brand
    - querydsl 이용
      - 응답값 
          ```json
            {
                "httpStatus": 200,
                "message": "OK",
                "data": {
                    "brandName": "D",
                    "categoryList": [
                        {
                            "categoryName": "상의",
                            "price": "10,100"
                        },
                        {
                            "categoryName": "아우터",
                            "price": "5,100"
                        },
                        {
                            "categoryName": "바지",
                            "price": "3,000"
                        },
                        {
                            "categoryName": "스니커즈",
                            "price": "9,500"
                        },
                        {
                            "categoryName": "가방",
                            "price": "2,500"
                        },
                        {
                            "categoryName": "모자",
                            "price": "1,500"
                        },
                        {
                            "categoryName": "양말",
                            "price": "2,400"
                        },
                        {
                            "categoryName": "액세서리",
                            "price": "2,000"
                        }
                    ],
                    "totalPrice": "36,100"
                }
            }
          ``` 
    
3. 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API 
    - url : `/api/v1/items/lowest-hightest-by-category`
    - RequestParam categoryType 
    - ex> http://localhost:8080/api/v1/items/lowest-hightest-by-category?categoryType=TOP
    - 해당 카테고리의 최저가 & 최고가 상품 조회
      - 응답값
        ```json
            {
                "httpStatus": 200,
                "message": "OK",
                "data": {
                    "categoryName": "상의",
                    "lowestItem": {
                        "brandName": "C",
                        "price": "10,000"
                    },
                    "highestItem": {
                        "brandName": "I",
                        "price": "11,400"
                    }
                }
            }
         ```
4. 실패응답
   ```json
      {
          "httpStatus": 500,
          "message": "서버에러입니다. 잠시 후 다시 시도해주세요.",
          "data": null
      }
   ```
   
###  admin API

1. Brand
   - url : `/admin/api/v1/brand`
   - 생성: POST 
     ```json
      {
         "name": "K1"
       }
     ```
   - 수정: PUT
     ```json
      {
         "brandId" : 1,
          "name": "TEST"          
       }
     ```
   - 삭제: DELETE
     ```json
      {
          "brandId" : 1
       }
     ```

2. BrandItem
    - url : `/admin/api/v1/brandItem`
    - 생성: POST
      ```json
       {
          "category": "TOP",
          "price": 12000,
          "brandId": 1
        }
      ```
    - 수정: PUT
      ```json
       {
          "brandItemId" : 1,
          "category": "BAG",
          "price": 12000
        }
      ```
    - 삭제: DELETE
      ```json
       {
           "brandItemId" : 1
        }
      ```

3. 실패응답
   ```json
    {
        "httpStatus": 400,
        "message": "이미 존재하는 브랜드명입니다.",
        "data": null
    }
   ```

### entity 구현

- Brand와 BrandItem 1:N 양방향 연관관계
- 삭제는 `soft delete`로 구현

### GlobalExceptionHandler

- 모든 컨트롤러 예외처리 
  - CustomException CollectionRuntimeException 추가
- ResultCode 추가 
