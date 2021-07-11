package com.sfl.cafe.model.dto;


import com.sfl.cafe.model.Table;
import com.sfl.cafe.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WaiterTable {

    private int tableId;
    private int waiterId;
}
