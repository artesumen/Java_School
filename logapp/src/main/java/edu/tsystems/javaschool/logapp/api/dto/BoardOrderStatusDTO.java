package edu.tsystems.javaschool.logapp.api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BoardOrderStatusDTO {
    private Integer orderId;
    private boolean isCompleted;
    private List<String> drivers;
    private String truck;
    private Integer numOfPointsTotal;
    private Integer numOfCompletedPoints;

}
