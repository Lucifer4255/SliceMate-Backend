package com.example.slicemate.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class FoodItemDto {
//    @NotNull
    private Integer FoodItemId;
    @NotEmpty
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String imageUrl;
    @NotNull
    private String Category;
    @NotNull
    private Double price;

}
