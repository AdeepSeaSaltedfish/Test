package com.test.rocketmq.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * es代码示例中使用到的model
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEs {

    String id;
    String username;
    String password;
    Long docTotalNum;
    Double avgAge;
}
